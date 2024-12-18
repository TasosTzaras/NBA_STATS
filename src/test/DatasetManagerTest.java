/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


import server.DatasetManager;
import utils.SimpleCSVReader;
import server.Dataset;


class DatasetManagerTest {

	@Test
	void test1() {
		int reg;
		
		
		DatasetManager impl = new DatasetManager();
		SimpleCSVReader csvReader = new SimpleCSVReader();
		
		String fileName = System.getProperty("user.dir")+"\\Resources\\NBA_team_stats.csv"; 

		
		reg = impl.registerDataset(null, null);			
		assertEquals(-1  ,reg, "Function registerDataset fails with null DatasetName and null canonicalPath");
		
		reg = impl.registerDataset("nba", fileName);
		assertEquals(0 ,reg, "Function registerDataset fails with valid DatasetName and valid canonicalPath");
		
		reg = impl.registerDataset("nba", fileName);
		assertEquals(-10 ,reg, "Function registerDataset fails with valid DatasetName and valid canonicalPath");
		
		
		
		
		ArrayList<String[]> dataEx =  csvReader.load(fileName);
		String [] firstLineEx= dataEx.get(0);	
		
		ArrayList<String[]> dataIn = new ArrayList<String[]>() ;
		reg=impl.registerDataset("NBA", fileName);
		
		String [] firstLine=impl.retrieveDataset("NBA", dataIn);
				
		assertArrayEquals(firstLineEx,firstLine,"Function retrieveDataset fails with valid DatasetName and valid data");
		
		
		
		
		
		reg=impl.filterDataset(null, null, null, null);
		assertEquals(-1  ,reg, "Function registerDataset fails with null DatasetName and null canonicalPath");

		reg=impl.registerDataset("NBA", fileName);
		//String [] firstLineEx= dataEx.get(0);	

		int ret;
		ret=impl.filterDataset("NBA","Boston" ,"Team" ,"Boston Celtics");
		assertEquals(0  ,ret, "Function filterDataset fails with null DatasetName and null canonicalPath");
		
		ArrayList<String[]> dataBostonIn = new ArrayList<String[]>() ;

		firstLine=impl.retrieveDataset("Boston",dataBostonIn );
		assertArrayEquals(firstLineEx,firstLine,"Function retrieveDataset fails with valid DatasetName and valid data");
		
		
		
		
		
		

		/*ArrayList<String[]> lst = new ArrayList<String[]>() ;
		ArrayList<String> attributeNames = new ArrayList<String> ();
		lst= impl.getDatasetProjection(null, attributeNames);
		assertEquals(null,lst,"Function retrieveDataset fails with valid DatasetName and valid data");*/

	}

}






















