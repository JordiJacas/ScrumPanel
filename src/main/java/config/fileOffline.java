package config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileOffline {
	
	public fileOffline() {
		File file = new File("src"+File.separator+"querys.txt");	
	}
	
	public void addQuery(String query) {
		FileWriter f;
		try {
			f = new FileWriter("src"+File.separator+"querys.txt", true);
			f.write(query + System.lineSeparator());
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
}
