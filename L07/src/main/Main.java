package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import reflection.ReflectionHelper;
import sax.ReadXMLFileSAX;

public class Main {
	
	public static void main(String[] args){
		writeToBinFile();
		//readFromBinFile();
		//workWithReflection();
		//workWithFactory();
		//workWithSAX();
	}

	private static void workWithSAX() {
		SerializationObject object = (SerializationObject) ReadXMLFileSAX.readXML("test.xml");
		if(object != null)
			System.out.append(object.toString() + '\n');
	}

	private static void workWithFactory() {
		SerializationObject object = (SerializationObject) ObjectFactory.readObject("test.bin");
		System.out.append(object.toString() + '\n');
	}

	private static void workWithReflection() {
		SerializationObject object = (SerializationObject)ReflectionHelper.createIntance("main.SerializationObject");
		System.out.append(object.toString() + '\n');
		
		ReflectionHelper.setFieldValue(object, "name", "Kaylee");
		ReflectionHelper.setFieldValue(object, "age", "25");
		System.out.append(object.toString() + '\n');
	}

	private static void readFromBinFile() {
		System.out.append("Read from bin file\n");
		try{
	         FileInputStream fileOut = new FileInputStream("test.bin");
	         ObjectInputStream in = new ObjectInputStream(fileOut);
	         SerializationObject object = (SerializationObject) in.readObject();
	         System.out.append(object.toString() + '\n');
	         in.close();
	    }catch(IOException i){
	         i.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void writeToBinFile() {
		System.out.append("Write to bin file\n");
		try{
			 SerializationObject object = new SerializationObject("Zoe", 31);
	         FileOutputStream fileOut = new FileOutputStream("test.bin");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(object);
	         out.close();
	    }catch(IOException i){
	         i.printStackTrace();
	    }		
	}
}
