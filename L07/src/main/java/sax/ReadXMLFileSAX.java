package main.java.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFileSAX { 
	public static Object readXML(String xmlFile) {	 
	    try {	 
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		 
			//SaxHandler handler = new SaxHandler();
			SaxEmptyHandler handler = new SaxEmptyHandler();
	        saxParser.parse(xmlFile, handler);
	        
	        return handler.getObject();
	 
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	    return null;
	 
	   }
	 
	
}
