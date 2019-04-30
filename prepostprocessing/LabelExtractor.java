import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LabelExtractor {


	public LabelExtractor(){
		
	}
	public void extractLabel(String inputFile, String outputFile){

		FileReader fr1=null;
		PrintWriter out = null;

		try{
			out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
			fr1 = new FileReader(inputFile); 
			BufferedReader br1 = new BufferedReader(fr1);
			String trainingExample;

			while((trainingExample = br1.readLine()) != null) {
				int labelEndIndex = trainingExample.indexOf(" ");
				String labelToRootPath = trainingExample.substring(0, labelEndIndex);
				
				
				out.println(labelToRootPath + " ");
			}
		}
		catch (IOException ex) {

			System.out.println(ex);
		}
		finally{
			try{
				if (fr1!=null){
					fr1.close();
				}
				if(out!=null)
				{
					out.close();
				}
			}catch(IOException ex){
				System.out.println(ex);
			}
		}
	}
	
	public void makeTestLabelZero(String inputFile, String outputFile){

		FileReader fr1=null;
		PrintWriter out = null;

		try{
			out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
			fr1 = new FileReader(inputFile); 
			BufferedReader br1 = new BufferedReader(fr1);
			String trainingExample;

			while((trainingExample = br1.readLine()) != null) {
				int labelEndIndex = trainingExample.indexOf(" ");
				String labels = trainingExample.substring(0, labelEndIndex);
				
				String vector = trainingExample.substring(labelEndIndex+1, trainingExample.length());
				
				out.println("0" + " " + vector);
			}
		}
		catch (IOException ex) {

			System.out.println(ex);
		}
		finally{
			try{
				if (fr1!=null){
					fr1.close();
				}
				if(out!=null)
				{
					out.close();
				}
			}catch(IOException ex){
				System.out.println(ex);
			}
		}
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		String inputFile = args[0];
		String zeroLabelFile = args[1];
		String labelFile = args[2];
		
		LabelExtractor le = new LabelExtractor();
		le.extractLabel(inputFile, labelFile);
		le.makeTestLabelZero(inputFile, zeroLabelFile);
		

	}

}
