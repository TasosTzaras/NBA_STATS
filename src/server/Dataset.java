package server;

import java.util.ArrayList;

import utils.SimpleCSVReader;

public class Dataset {
	
	ArrayList<enth> fileData = new ArrayList<enth>();
	SimpleCSVReader reader = new SimpleCSVReader();
	
	class enth{
		
		String datasetName; 
		String path; 
		ArrayList<String[]> dataset;
	}
	
			
	public int addData (String DatasetName, String Path,ArrayList<String[]> Dataset) {
		
		if (DatasetName == null) {
			return -1;
		}
		else if (exists(DatasetName)) {
			return -10;
		}
		else {
			enth data = new enth();
			data.datasetName = DatasetName;
			data.path = Path;
			if (Path != null) {
				data.dataset = reader.load(Path);
			}
			else {
				data.dataset = Dataset;
			}
			fileData.add(data);
			return 0;
		}
	}
	
	
	public boolean exists(String Name) {
		for (int i = 0; i < fileData.size(); i ++) {
			enth data = fileData.get(i);
			if(data.datasetName.equals(Name)) {
				return true;
			}
		}
		return false;	
	}
	
	
	public ArrayList<String []> saveData(String datasetName){
		for(int k = 0; k < fileData.size(); k ++) {
			enth data = fileData.get(k);
			if(data.datasetName.equals(datasetName)){
				return data.dataset;
			}
		}
		return null;
	}	
}
