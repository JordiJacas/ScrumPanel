package config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase encargada de gestionar el archivo en el que se almacenaran las querys ejecutadas mientras se esta online
 * @author jaimepm
 *
 */
public class fileOffline {
	
	/**
	 * Constructor que hace una referencia al archivo en el que se almacenaran las querys 
	 */
	public fileOffline() {
		File file = new File("src"+File.separator+"querys.txt");	
	}
	
	/**
	 * agrega la query pasada por parametro al archivo que las almacena
	 * @param query's string
	 */
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
	
	/**
	 * Lee el archivo de querys y devuelve un arraylist con cada una de ellas
	 * @return query's arraylist
	 */
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
