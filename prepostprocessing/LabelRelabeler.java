import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LabelRelabeler {

	public LabelRelabeler(){
	
	}

	
	public void remapLabels(String trainInputFile, String trainOutputFile,
			String testInputFile, String testOutputFile,
			String mappingFile) throws IOException{
		BufferedReader trainReader = new BufferedReader(new FileReader(new File(trainInputFile)));
		BufferedWriter trainWriter = new BufferedWriter(new FileWriter(new File(trainOutputFile)));
		
		Map<Integer, Integer> classMapping = new HashMap<Integer, Integer>();
		String instance = "";
		
		int currClassIndex = 0; // for labels indices to start at 1
//		int currClassIndex = -1; // for labels indices to start at 0 for FastXML etc
		
		//skip the first line if using the original format
		// trainReader.readLine();
				
		int counter =1;
		
		
		while((instance = trainReader.readLine()) != null){
			
			String [] labels = instance.split(" ")[0].split(",");
			
			int startIndex = instance.indexOf(" ", 0);  //assuming that labels do not have spaces
			
			int labelEndIndex = startIndex;
			
			if(startIndex == -1){
				System.out.println("skipping instance of length 0 in counting");
				continue;
			}
			
			String vector = instance.substring(labelEndIndex+1);
			
			StringBuffer relabeledInstance = new StringBuffer();
			int newLabel = -1;
			
			for(int i=0; i < labels.length; i++){

				if(!labels[0].equalsIgnoreCase("")){
					int prevLabel = Integer.parseInt(labels[i]);
					if(prevLabel == 0){
					//	System.out.println("zero label");
					}
					if(classMapping.containsKey(prevLabel)){
						newLabel = classMapping.get(prevLabel);
					}else {
						currClassIndex++;
						newLabel = currClassIndex;
						classMapping.put(prevLabel, currClassIndex);
					}
					relabeledInstance.append(newLabel);
					if(i==labels.length-1){
						relabeledInstance.append(" " + vector);
						trainWriter.write(new String(relabeledInstance));
						trainWriter.newLine();
						trainWriter.flush();
						break;
					}
					else{
						relabeledInstance.append(",");
					}
				}
				else{
					System.out.println("no labels for line number " + counter);
				}
			}
			
			
			counter++;
		}
		
		trainReader.close();
		trainWriter.flush(); trainWriter.close();
		System.out.println("training done!, total number of classes = " + currClassIndex);
		
		
		BufferedReader testReader = new BufferedReader(new FileReader(new File(testInputFile)));
		BufferedWriter testWriter = new BufferedWriter(new FileWriter(new File(testOutputFile)));
		
		//skip the first line if using the original format
		//testReader.readLine();
				
		counter = 0;
		while((instance = testReader.readLine()) != null){
			
			String [] labels = instance.split(" ")[0].split(",");
			
			int startIndex = instance.indexOf(" ", 0);  //assuming that labels do not have spaces
			
			int labelEndIndex = startIndex;
			
			if(startIndex == -1){
				System.out.println("skipping instance of length 0 in counting");
				continue;
			}
			
			String vector = instance.substring(labelEndIndex+1);
			
			StringBuffer relabeledInstance = new StringBuffer();
			
			if(labels.length == 0){
				System.out.println("no labels present in test skipping");
			}
			for(int i=0; i < labels.length; i++){

				if(!labels[0].equalsIgnoreCase("")){
					int prevLabel = Integer.parseInt(labels[i]);
					int newLabel = -1;
					if(classMapping.containsKey(prevLabel)){
						newLabel = classMapping.get(prevLabel);
						relabeledInstance.append(newLabel);
						if(i==labels.length-1){
							relabeledInstance.append(" " + vector);
							testWriter.write(new String(relabeledInstance));
							testWriter.newLine();
							testWriter.flush();
							break;
						}
						else{
							relabeledInstance.append(",");
						}
					}else {
						System.out.println("found new label in the test set at line number " + counter+1);
						currClassIndex++;
						newLabel = currClassIndex;
						classMapping.put(prevLabel, currClassIndex);
						relabeledInstance.append(newLabel);
						if(i==labels.length-1){
							relabeledInstance.append(" " + vector);
							testWriter.write(new String(relabeledInstance));
							testWriter.newLine();
							testWriter.flush();
							break;
						}
						else{
							relabeledInstance.append(",");
						}
					}
				}	
				else{
					System.out.println("something weird at line number in the test set" + counter+1);
				}
			}
			
			counter++;
		}
		
		testReader.close();
		testWriter.flush(); testWriter.close();
		System.out.println("test also done!, total number of classes = " + currClassIndex);
	   	
		BufferedWriter mappingWriter = new BufferedWriter(new FileWriter(new File(mappingFile)));
		Iterator<Integer> iter = classMapping.keySet().iterator();
		
		while(iter.hasNext()){
			Integer prevLabel = iter.next();
			mappingWriter.write(prevLabel+" "+classMapping.get(prevLabel));
			mappingWriter.newLine();mappingWriter.flush();
		}
		mappingWriter.flush(); mappingWriter.close();
	}
	
	public static void copyLabelsFromGSToTest(String GSFilePath, String testFilePath,
			String newTestFilePath) throws IOException{
		
		BufferedWriter newTestWriter = new BufferedWriter(new FileWriter(new File(newTestFilePath)));		
		BufferedReader gsReader = new BufferedReader(new FileReader(new File(GSFilePath)));
		BufferedReader testReader = new BufferedReader(new FileReader(new File(testFilePath)));

		String instance = "";
		while((instance = testReader.readLine()) != null){
			String gsLabel = gsReader.readLine();
			String instanceWithoutLabel = instance.substring(instance.indexOf(" "));
			newTestWriter.write(gsLabel+instanceWithoutLabel);
			newTestWriter.newLine(); newTestWriter.flush();
		}

		newTestWriter.flush(); newTestWriter.close();
		gsReader.close(); 
		testReader.close();
	}
	
	public static void main(String...args) throws IOException{
		
	LabelRelabeler lr = new LabelRelabeler();
	String orgLabelsTrainFile = args[0];
	String remappedLabelsTrainFile = args[1];
	String orgLabelsTestFile = args[2];
	String remappedLabelsTestFile = args[3];
	String labelMappingsFile = args[4];

	lr.remapLabels(orgLabelsTrainFile,
			remappedLabelsTrainFile,
			orgLabelsTestFile,
			remappedLabelsTestFile,
			labelMappingsFile);
	}
	
}
