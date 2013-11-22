package main;

import java.io.Serializable;

public class SerializationObject extends Object implements Serializable {
	private static final long serialVersionUID = -3895203507200457732L;
	private String name;
	private int age;
	
	private Object test = new Object();
	
	//private ObjectFactory test2 = new ObjectFactory();
	
	public SerializationObject(){
		this.name = "Nobody";
		this.age = 0;
	}
	
	public SerializationObject(String name, int age){
		this.setAge(age);
		this.setName(name);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String toString(){
		return "Name: " + name + " age: " + age;
	}
}
