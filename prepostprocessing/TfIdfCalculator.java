import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TfIdfCalculator {

	public TfIdfCalculator() {

	}

	
	public void convertLibSvmToTfIdf(String inputFile, String outputFile) {

		FileReader fr1 = null;
		PrintWriter out = null;
		FileReader fr2 = null;
		
		try {
			// the input file in LIBSVM format
			fr1 = new FileReader(inputFile);
			BufferedReader br1 = new BufferedReader(fr1);
			
			//Read input file once to create the vocab and also note the total number of occurrences 
			//of each word in all the training docs 
			Map vocabMap = new TreeMap ();
			int numberOfDocs = 0;
			List docLengthList = new ArrayList();
			
			String instance=null;
			
			while((instance = br1.readLine()) != null) {
				
				String [] labels = instance.split(","); 
				int numLabels = labels.length;
				int tempSpaceIndex = 0, startIndex = 0;
				/*for(int i = 0; i < numLabels; i++){
					tempSpaceIndex = instance.indexOf(" ", startIndex);
					startIndex = tempSpaceIndex + 1;
				}*/
				
				startIndex = instance.indexOf(" ", 0);  //assuming that labels do not have spaces
				
				int labelEndIndex = startIndex;
				
				String labelToRootPath = instance.substring(0, labelEndIndex);
//				System.out.println(numberOfDocs + " "+ labelToRootPath);
				
				String vector = instance.substring(labelEndIndex+1);
				
				StringTokenizer st1 = new StringTokenizer (vector, " "); 
				
				while(st1.hasMoreTokens()){
					String[] feature = st1.nextToken().split(":");                  //split the feature key:value
					if(vocabMap.containsKey(feature[0])){
						String value = (String)vocabMap.get(feature[0]);
						int newCount =  (Integer.valueOf(value)).intValue() + 1;
						vocabMap.put(feature[0], (new Integer(newCount)).toString());
					}
					else{
						vocabMap.put(feature[0], new String("1"));
					}
				}
				numberOfDocs++;
			}
			
			// now that we have the vocab info, read the input file in LIBSVM format once again
			// to do the real processing for tf idf per training data instance
					
			fr2 = new FileReader(inputFile);
			BufferedReader br2 = new BufferedReader(fr2);
			out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile,true)));
			
			String trainingInstance=null;
			int docIndex = 0;
			while((instance = br2.readLine()) != null) {
				
				StringBuffer outputInstance = new StringBuffer("");
				
				String [] labels = instance.split(","); 
				int numLabels = labels.length;
				int tempSpaceIndex = 0, startIndex = 0;
				/*for(int i = 0; i < numLabels; i++){
					tempSpaceIndex = instance.indexOf(" ", startIndex);
					startIndex = tempSpaceIndex + 1;
				}*/
				
				startIndex = instance.indexOf(" ", 0);  //assuming that labels do not have spaces
				
				int labelEndIndex = startIndex;
				
				String labelToRootPath = instance.substring(0, labelEndIndex);
				String vector = instance.substring(labelEndIndex+1);
				
				StringTokenizer st1 = new StringTokenizer (vector, " "); 
				
				outputInstance.append(labelToRootPath); outputInstance.append(" ");
				double weightSquared = 0.0;
				while(st1.hasMoreTokens()){
					String[] feature = st1.nextToken().split(":");
					int occurrencesInCurrInstance = Math.round(Float.valueOf(feature[1]));
					String totalOccurrences = (String)vocabMap.get(feature[0]);
					int intTotalOccurrences = Integer.valueOf(totalOccurrences).intValue();
	
					Double weight, tf, idf;
					tf = (1+ Math.log(occurrencesInCurrInstance));
					idf = Math.log((numberOfDocs*1.0)/intTotalOccurrences);
					if(occurrencesInCurrInstance > 0)
						weight = tf * idf;
					else
						weight =0.0;
					outputInstance.append(feature[0]);outputInstance.append(":");outputInstance.append(weight.toString()); outputInstance.append(" ");
					weightSquared = weightSquared + weight * weight;
				}
				double weightedSqrooted = Math.sqrt(weightSquared);
				
				String outputInstanceString = new String(outputInstance);
				
				outputInstance = new StringBuffer("");
				outputInstance.append(labelToRootPath); outputInstance.append(" ");
				
				String [] labels1 = outputInstanceString.split(","); 
				int numLabels1 = labels.length;
				int tempSpaceIndex1 = 0, startIndex1 = 0;
				/*for(int i = 0; i < numLabels1; i++){
					tempSpaceIndex1 = outputInstanceString.indexOf(" ", startIndex1);
					startIndex1 = tempSpaceIndex1 + 1;
				}*/
				
				startIndex1 = outputInstanceString.indexOf(" ", 0);  //assuming that labels do not have spaces
				
				int labelEndIndex1 = startIndex1;
				
				String labelToRootPath1 = outputInstanceString.substring(0, labelEndIndex1);
				String vector1 = outputInstanceString.substring(labelEndIndex1+1);
				
				
				String [] featureArray = vector1.split(" ");
				if(featureArray.length > 1){
					for(int i = 0; i < featureArray.length;i++) {
						Double weight = Double.valueOf(featureArray[i].split(":")[1]);
						if(weightedSqrooted > 0){
							weight = weight/(weightedSqrooted);
						}
						//					System.out.println(weightedSqrooted + " " + weight);
						DecimalFormat numberFormat = new DecimalFormat("#.0000");
						weight = Double.valueOf(numberFormat.format(weight));
						outputInstance.append(featureArray[i].split(":")[0]);
						outputInstance.append(":");
						outputInstance.append(weight.toString()); 
						outputInstance.append(" ");					
					}
				}
				
				docIndex++;
//				System.out.println(docIndex);
				out.println(outputInstance);

			}
			
		} catch (IOException ex) {

			System.out.println(ex);
		} finally {
			try {
				if (fr1 != null) {
					fr1.close();
				}
				if (fr2 != null) {
					fr2.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {

				System.out.println(ex);
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TfIdfCalculator tfIdfCal = new TfIdfCalculator();
			
		String inputTrainFileInTF =  args[0];
		String outputTrainFileInTFIDF =  args[1];
		
		String inputTestFileInTF =  args[2];
		String outputTestFileInTFIDF =  args[3];
		
		
        tfIdfCal.convertLibSvmToTfIdf(inputTrainFileInTF, outputTrainFileInTFIDF);
        
        tfIdfCal.convertLibSvmToTfIdf(inputTestFileInTF, outputTestFileInTFIDF);
	}

}
