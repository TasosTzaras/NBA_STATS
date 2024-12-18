/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import server.Dataset;
import utils.SimpleCSVReader;
import java.net.URL;


class SimpleCSVReaderTest {

	@Test
	void test1() {
	
		SimpleCSVReader Reader = new SimpleCSVReader();
		ArrayList<String[]> data =  Reader.load(null);
		assertNull(data, "Function SimpleCSVReader fails with null input");  

	}
	
	@Test
	void test2() {
		
		String fileName = System.getProperty("user.dir")+"\\Resources\\NBA_team_stats.csv"; 		
		SimpleCSVReader Reader = new SimpleCSVReader();
		System.out.println("fileName: "+fileName);
		ArrayList<String[]> data =  Reader.load(fileName);
		
		assertNotNull(data, "Function SimpleCSVReader fails with valid file");  

	}
}
