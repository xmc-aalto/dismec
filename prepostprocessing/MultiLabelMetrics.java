import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MultiLabelMetrics {
	
	public MultiLabelMetrics(){
		
	}

	public void computeNDCGAtK(String GSFile, String predictionFile, int k){
		
		FileReader fr1 = null, fr2 = null;
		int total = 0;
		double globalScore =0.0;
		
		try{
			fr1 = new FileReader(GSFile); 
			fr2 = new FileReader(predictionFile);
			BufferedReader br1 = new BufferedReader(fr1);
			BufferedReader br2 = new BufferedReader(fr2);
			String trueLabelsString, predictionLabels;
			
			while(((trueLabelsString = br1.readLine()) != null) && ((predictionLabels = br2.readLine()) != null) ) {
				double localScore = 0.0;
				String [] trueLabels = trueLabelsString.split(",");
				int numtrueLabels = trueLabels.length;
				
				int maxIndex = Math.min(k, numtrueLabels);
				
				String [] predictedLabels = predictionLabels.split(" ");
				
				for(int j=0; j< predictedLabels.length; j++){
					String predictedLabel = predictedLabels[j].trim();
					for(int i=0; i < trueLabels.length; i++){
						String trueLabel = trueLabels[i].trim();
						if(predictedLabel.equalsIgnoreCase(trueLabel)){
							localScore = localScore + (Math.log(2))/(Math.log(1+j+1));
							break;
						}
					}
				}
				double deno = 0.0;
				for(int i=0; i < maxIndex; i++){
					deno = deno + (Math.log(2))/(Math.log(i+1+1));
				}
				localScore  = localScore/deno;
				globalScore = globalScore + localScore;
				total++;
			}
			System.out.println(" ndcg at " + k + " is " + (globalScore*100.0)/(total*1.0));
		}
		catch (IOException ex) {

	           System.out.println(ex);
	        }
		finally{
			try{
				if (fr1!=null){
					fr1.close();
				}
				if(fr2!=null)
				{
					fr2.close();
				}
			}catch(IOException ex){
				System.out.println(ex);
			}
		}
		
	}

	public void computePrecAtK(String GSFile, String predictionFile, int k){
		
		FileReader fr1 = null, fr2 = null;
		int correctCount = 0, total = 0;
		
		try{
			fr1 = new FileReader(GSFile); 
			fr2 = new FileReader(predictionFile);
			BufferedReader br1 = new BufferedReader(fr1);
			BufferedReader br2 = new BufferedReader(fr2);
			String trueLabelsString, predictionLabels;
			
			while(((trueLabelsString = br1.readLine()) != null) && ((predictionLabels = br2.readLine()) != null) ) {
				String [] trueLabels = trueLabelsString.split(",");
				String [] predictedLabels = predictionLabels.split(" ");
				
				for(int j=0; j< predictedLabels.length; j++){
					String predictedLabel = predictedLabels[j].trim();
					for(int i=0; i < trueLabels.length; i++){
						String trueLabel = trueLabels[i].trim();
						if(predictedLabel.equalsIgnoreCase(trueLabel)){
							correctCount++;
							break;
						}
					}
				}
				
				total++;
			}
			System.out.println(" precision at " + k +" is " + (correctCount*100.0)/(total*k*1.0));
		}
		catch (IOException ex) {

	           System.out.println(ex);
	        }
		finally{
			try{
				if (fr1!=null){
					fr1.close();
				}
				if(fr2!=null)
				{
					fr2.close();
				}
			}catch(IOException ex){
				System.out.println(ex);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		
		String GSFile =         args[0]; // comma separated gold standard file
		
		String predictionFile1 =     args[1]; // top1 output
		String predictionFile3 =      args[2]; // space separated top3 output
		String predictionFile5 =    args[3];  // space separated top5 output
		
		
		int k;
		
		MultiLabelMetrics mlm = new MultiLabelMetrics(); 
		
		k=1;mlm.computePrecAtK(GSFile, predictionFile1, k); 	
		k=3;mlm.computePrecAtK(GSFile, predictionFile3, k); 
		k=5;mlm.computePrecAtK(GSFile, predictionFile5, k); 

		System.out.println();

		k=1;mlm.computeNDCGAtK(GSFile, predictionFile1, k); 	
		k=3;mlm.computeNDCGAtK(GSFile, predictionFile3, k); 
		k=5;mlm.computeNDCGAtK(GSFile, predictionFile5, k); 


		
	}
}
