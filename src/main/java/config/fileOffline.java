package config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public ArrayList<String> readQuerys() {
		ArrayList<String> querys = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src"+File.separator+"querys.txt"));
			String line = reader.readLine();
			while (line != null) {
				querys.add(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("src"+File.separator+"querys.txt"));
				bw.write("");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return querys;
	}
}
