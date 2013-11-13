package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleFileReader {
	public static void main(String args[]) throws IOException { 
		FileReader fr = new FileReader("SimpleFileReader.java"); 
		BufferedReader br = new BufferedReader(fr); 
		String currentLine; 
		while((currentLine = br.readLine()) != null) { 
			System.out.println(currentLine); 
		} 
		fr.close(); 
	} 
}
