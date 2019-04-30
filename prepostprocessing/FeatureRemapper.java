import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.io.File;

public class FeatureRemapper {

	public FeatureRemapper(){
		
	}
	
	
	
	public Map remapFeatures(String inputFile){
		
		Map featureMap = new TreeMap();
		
		FileReader fr1=null;
		
		try{
			fr1 = new FileReader(inputFile); 
			BufferedReader br1 = new BufferedReader(fr1);
			String trainingExample;
			int featureCounter=1;
			int instanceCount = 0;
			int skipped = 0;
			while((trainingExample = br1.readLine()) != null) {
				instanceCount++;
		//		System.out.println(instanceCount);
				String [] labels = trainingExample.split(","); 
				int numLabels = labels.length;
				int tempSpaceIndex = 0, startIndex = 0;
				
				/*for(int i = 0; i < numLabels; i++){
					tempSpaceIndex = trainingExample.indexOf(" ", startIndex);
					startIndex = tempSpaceIndex + 1;
				}*/
				
				startIndex = trainingExample.indexOf(" ", 0);  //assuming that labels do not have spaces
				
				int labelEndIndex = startIndex;
				
				if(startIndex == -1){
//					System.out.println("skipping instance of length 0 in counting");
					skipped++;
					continue;
				}
				
				String labelToRootPath = trainingExample.substring(0, labelEndIndex);
				String vector = trainingExample.substring(labelEndIndex+1);

				StringTokenizer featureTok = new StringTokenizer(vector," ");
				int vectorLength = featureTok.countTokens();
				while(featureTok.hasMoreTokens()){
					String[] feature = featureTok.nextToken().split(":");
					Integer key = Integer.valueOf(feature[0]);
					if(!featureMap.containsKey(key)){
						featureMap.put(key, new Integer(featureCounter));
						featureCounter++;
					}
				}

			}
			System.out.println("total skipped in file " + inputFile + " are " + skipped);
			
			System.out.println(" the number of features in file " + inputFile + "are " + featureMap.size());	
		}
		catch (IOException ex) {

	           System.out.println(ex);
	        }
		finally{
			try{
				if (fr1!=null){
					fr1.close();
				}
			}catch(IOException ex){
				System.out.println(ex);
			}
		}
		return featureMap;
	}
	
	public void rewriteRemappedFile(Map remappedFeatures, String inputFile, String outputFile){
		
		FileReader fr = null;
		try{
			fr = new FileReader(inputFile); 
			BufferedReader br = new BufferedReader(fr);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
			String instance;
			
			while((instance = br.readLine()) != null) {
				
				String [] labels = instance.split(","); 
				int numLabels = labels.length;
				int tempSpaceIndex = 0, startIndex = 0;
				/*for(int i = 0; i < numLabels; i++){
					tempSpaceIndex = instance.indexOf(" ", startIndex);
					startIndex = tempSpaceIndex + 1;
				}*/
				
				startIndex = instance.indexOf(" ", 0);  //assuming that labels do not have spaces
				
				if(startIndex == -1){
					System.out.println("skipping instance of length 0 in remapping");
					continue;
				}
				
				int labelEndIndex = startIndex;
				
				String labelToRootPath = instance.substring(0, labelEndIndex);
				String vector = instance.substring(labelEndIndex+1);
				
				StringTokenizer featureTok = new StringTokenizer(vector," ");
                int vectorLength = featureTok.countTokens();
                
                StringBuffer newFeature=new StringBuffer();
                Map newKeyMap = new TreeMap();
                
                while(featureTok.hasMoreTokens()){
                	String[] feature = featureTok.nextToken().split(":");
                	Integer key = Integer.valueOf(feature[0]);
                	String value = (String)feature[1];
                	Integer newKey = ((Integer)remappedFeatures.get(key)); //keep as Integer since we want them sorted not as strings
                	if(newKey != null){
                		newKeyMap.put(newKey, value);
                	}
                	else{
                	   // System.out.println(" new features in test set");
                	//	newKeyMap.put(key, value); //it is possible that in the test set there are some feature which were not seen in training
                	}
                }
                Set newKeySet = newKeyMap.keySet();
                Iterator iter = newKeySet.iterator();
                while(iter.hasNext()){
                	Integer newKey = ((Integer)iter.next());
                	String value = (newKeyMap.get(newKey)).toString();
                	newFeature = newFeature.append(" "+ newKey.toString()+":"+value);
                }
				String outputVector = labelToRootPath + newFeature;
				out.println(outputVector);
				out.flush();
			}
			if (out!=null){
				out.close();
			}
		}
		catch (IOException ex) {

	           System.out.println(ex);
	        }
		finally{
			try{
				if (fr!=null){
					fr.close();
				}
			}catch(IOException ex){
				System.out.println(ex);
			}
		}
		
	}
	
	
	/**
	 * @param args
	 */
	// it takes for inputs; the input training file, the output training file, the input test file, the output test file 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FeatureRemapper fRemap = new FeatureRemapper();
		
		String inputTrainingFile = args[0];
		String outputTrainingFile = args[1];
		
		String inputTestFile = args[2];
		String outputTestFile = args[3];
		
		
		Map remappedFeaturesTrain = fRemap.remapFeatures(inputTrainingFile);
		Map remappedFeaturesTest = fRemap.remapFeatures(inputTestFile);
		
		Set trainingFeatureSet = remappedFeaturesTrain.keySet();
		
		fRemap.rewriteRemappedFile(remappedFeaturesTrain, inputTrainingFile, outputTrainingFile);
		fRemap.rewriteRemappedFile(remappedFeaturesTrain, inputTestFile, outputTestFile);
			
		
		
	}

}
