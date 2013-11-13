package io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinesReader {	
	public void read(){
		BufferedReader br = null;
		try{
			FileInputStream fstream = new FileInputStream("textfile.txt");
			DataInputStream in = new DataInputStream(fstream);
		  
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);
			
			String strLine;

			while ((strLine = br.readLine()) != null)   {
				System.out.println (strLine);
			}
		  
		} catch (Exception e){
		  System.err.println("Error: " + e.getMessage());
		} finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
		}
	}
}
