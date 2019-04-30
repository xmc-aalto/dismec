import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DistributedPredictor {

	public DistributedPredictor(){

	}

	public static int[] maxKIndex(double[] array, int top_k) {
		double[] max = new double[top_k];
		int[] maxIndex = new int[top_k];
		Arrays.fill(max, Double.NEGATIVE_INFINITY);
		Arrays.fill(maxIndex, -1);

		top: for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < top_k; j++) {
				if(array[i] > max[j]) {
					for(int x = top_k - 1; x > j; x--) {
						maxIndex[x] = maxIndex[x-1]; max[x] = max[x-1];
					}
					maxIndex[j] = i; max[j] = array[i];
					continue top;
				}
			}
		}
		return maxIndex;
	}

	// this function will read top-5 scores from individual output files stored in the inputDirectory
	// and generate the final top 5 labels in the outputFile
	public void readFiles(String inputDirectory, int top_k, String outputFile, int testSamples){

		int five = 5;

		String [] fileNames = new File(inputDirectory).list();
		String [] partialOutputFiles = new String[fileNames.length];
		FileReader [] fileReaders = new FileReader [fileNames.length];
		BufferedReader [] br = new BufferedReader [fileNames.length];

		String [] outputStrings = new String [testSamples];

		PrintWriter output = null;
		String [] topLKabels = new String[top_k];
		try{

			for(int fileIndex = 0 ; fileIndex < fileNames.length; fileIndex++){

				partialOutputFiles[fileIndex] = new String(inputDirectory+"/"+fileNames[fileIndex]);
				fileReaders[fileIndex] = new FileReader(partialOutputFiles[fileIndex]);
				br[fileIndex] = new BufferedReader(fileReaders[fileIndex]);
			}

			output = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));

			String [] scores = new String [fileNames.length];

			String [] classLabels = new String[five*fileNames.length];
			double [] scoreDouble = new double[five*fileNames.length];

			int testIndex =0;
			while((scores[0] = br[0].readLine()) != null) {

				String [] firstScore = scores[0].split(" ");
				for(int j=0; j < five; j++){
					scoreDouble[j] = Double.parseDouble(firstScore[j].split(":")[1]);
					classLabels[j] = firstScore[j].split(":")[0];
				}

				for(int i=1; i < fileNames.length;i++){

					//				System.out.println("file number is " + i);

					scores[i] = br[i].readLine();

					String [] localScores = scores[i].split(" ");  
					for(int j=0; j < five; j++){
						scoreDouble[i*five+j] = Double.parseDouble(localScores[j].split(":")[1]);
						classLabels[i*five+j] = localScores[j].split(":")[0];
					}					
				}

				int [] topKIndices = maxKIndex(scoreDouble, top_k);
				StringBuffer sb = new StringBuffer(); 
				for(int i=0;i<top_k; i++){
					topLKabels[i] = classLabels[topKIndices[i]];
					sb.append(topLKabels[i]);sb.append(" ");
				}
				outputStrings[testIndex] = new String(sb);
				testIndex++;
				if(testIndex%1000 == 0)
					System.out.println(testIndex);
			}	

			for(int i=0;i<testSamples; i++){
				output.println(outputStrings[i]);
			}
		}
		catch (IOException ex) {

			System.out.println(ex);
		}
		finally{
			try{
				for(int fileIndex = 0 ; fileIndex < fileNames.length; fileIndex++){

					if(fileReaders[fileIndex]!=null){
						fileReaders[fileIndex].close();
					}
				}
				if (output!=null){
					output.close();
				}

			}catch(IOException ex){
				System.out.println(ex);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DistributedPredictor dp = new DistributedPredictor();

		int testSamples = 3803; //number of test samples

		String inputDirectory = args[0];

		String outputFile1 =     args[1];
		String outputFile3 =     args[2];
		String outputFile5 =     args[3];


		int top_k ;

		top_k=1; dp.readFiles(inputDirectory, top_k, outputFile1, testSamples);		
		top_k=3; dp.readFiles(inputDirectory, top_k, outputFile3, testSamples);
		top_k=5; dp.readFiles(inputDirectory, top_k, outputFile5, testSamples); 

	}

}

