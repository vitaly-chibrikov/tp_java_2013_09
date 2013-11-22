package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectFactory {
	public static Object readObject(String path) {		
		try{
	         FileInputStream fileOut = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileOut);
	         Object object = in.readObject();
	         in.close();
	         return object;
	    }catch(IOException i){
	         i.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
