package server;
import java.util.ArrayList;





public class DatasetManager implements IDatasetManager{
	/**
	 * The main interface, via which the client software communicates with the server
	 * ALL the data-retrieval work needs to pass from calls of IDatasetManager
	 *   
	 * @author pvassil
	 *
	 */
	/**
	 * Requires that the first thing to do is to register a dataset with its name and (optionally) path
	 * 
	 * If the dataset name is null, or already exists returns negative value
	 * @param datasetName A name given to represent the dataset
	 * @param canonicalPath the canonical path of the dataset
	 * @return 0 if all OK, -1 if there is no name, -10 if the name already exists
	 */
	
	Dataset fileData = new Dataset();

	
	public int registerDataset(String datasetName, String canonicalPath) {
		return fileData.addData(datasetName, canonicalPath, null);
				
	}			
		/**
		 * This is a method that retrieves a specified dataset via its name (which is passed as parameter), and:
		 * (a) it feeds the input parameter data with the contents of the registered data set, and, 
		 * aplws tha valw me addAll ta stoixeia tou original dataset sto data!!!!!!!!!!!!!! + remove(line MHDEN)
		 * (b) returns the header of the data set as an array of strings
		 * 
		 * @param datasetName A String with the name of the registered dataset that we work with 
		 * @param data   An ArrayList<String []> with the data of the dataset that we pass as a parameter 
		 * @return    An Array of Strings with the names of the attributes of the columns of the dataset, or null if the dataset does not exist
		 */
		
	

	
	public String[] retrieveDataset(String datasetName, ArrayList<String[]> data) {
		ArrayList<String[]> tmpdata = fileData.saveData(datasetName);
		if(tmpdata == null) {
			return null;
		}
		data.addAll(tmpdata);
		data.remove(0);
		return tmpdata.get(0);
		
	}
	
	/**
	 * Creates a new dataset over an existing one, by applying a selection of the rows containing the value filterValue in column with name filterColumnName.
	 * 
	 * The new data set is registered too.
	 * 
	 * @param originalDatasetName A String with the name of the original data set
	 * @param newDatasetName   A String with the name of the new data set
	 * @param filterColumnName A String with the name of the column of the original data set that is used for the filtering
	 * @param filterValue A String with the value that is applied to filterColumnName 
	 * @return 0 if all OK, -1 if sth goes wrong
	 * original dataSetName = nba
	 * newDatasetName = allo onoma apo nba (px Celtics)
	 * filterColumnName = Team 
	 * filterValue = Boston Celtics
	 */
	
	
	public int filterDataset(String originalDatasetName, String newDatasetName, String filterColumnName, String filterValue) {
		
		ArrayList <String[]> newDataset = new ArrayList<String[]>();
		ArrayList <String[]> originalDataset = new ArrayList <String[]>();
		
		originalDataset = fileData.saveData(originalDatasetName);
		
		if(originalDataset == null) {
			return -1;
		}
		
		String [] header = originalDataset.get(0);		
		int pos = 0;
		for (int i = 0; i < header.length; i++) {
			if (filterColumnName.equals(header[i])) {
				pos = i;
			}
		}
		
		newDataset.add(header);
		for (int n = 1; n < originalDataset.size(); n++) {
			String [] value = originalDataset.get(n);
			if (filterValue.equals(value[pos])){
				newDataset.add(value);
			}
		}
		
		if (fileData.addData(newDatasetName, null, newDataset) < 0) {
			return -1;
		}
		return 0;
	}

	/**
	 * A method to receive only a subset of the columns -i.e., a projection- of the designated data set
	 *  
	 * @param datasetName A String with the name of the registered dataset that we work with
	 * @param attributeNames An ArrayList of Strings with the names of the attributes over which the dataset is projected
	 * @return null if sth goes wrong, a populated ArrayList of String [], where each row is a member of the list and each of its attributes populates the respective cell of the String array
	 */
	
	 public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames){
		 ArrayList<String[]> data;
		 ArrayList<String[]> retList = new ArrayList<String[]>();
		
		 data = fileData.saveData(datasetName);
		 String [] header = data.get(0);
		 for(int row = 1; row < data.size(); row ++) {
			 String [] rowData = data.get(row);
			 String [] tmpRow = new String[attributeNames.size()];
			 int p = 0;
			 for(int attribute=0; attribute <attributeNames.size(); attribute++) {
				 int column = getColumn(attributeNames.get(attribute),header);
				 if(column != -1) {
					 tmpRow[p] = rowData[column];
					 p++;
				 }else{
					 return null;
				 }
			 }
			 retList.add(tmpRow);
		 }
		 return retList;
	 }
	 private int getColumn(String attributeName,String[] header) {
		 for(int i=0; i<header.length; i++) {
			 if(attributeName.equals(header[i])) {
				 return i;
			 }
		 }
		 return -1;
	 }
}
