package com.opencsi.entity;

import java.io.File;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Message
{
	public static String getHTTPError(String CodeError){
		int code = Integer.valueOf(CodeError);
		if (code < 400 || code > 509)
			return null;
		/////////////////////////////////////
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try {
			 document = sxb.build(new File("messages.xml"));
			 Iterator it = document.getRootElement().getChildren("HTTP").iterator();
			 if(it.hasNext())
				 return ((Element) (it.next())).getChild("C"+CodeError).getText();
		} catch (Exception e) {
		}
		return "The Server has return a " + CodeError + " HTTP Code Error.";
	}
	
	public static String getSSLError(String message){
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try {
			 document = sxb.build(new File("messages.xml"));
			 Iterator it = document.getRootElement().getChildren("SSLError").iterator();
			 if(it.hasNext())
				 return ((Element) (it.next())).getChild(message).getText();
		} catch (Exception e) {
		}
		return "Cannot connect to the SSL Server.";
	}
	
	public static int getTimeOut(String children){
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try {
			 document = sxb.build(new File("messages.xml"));
			 Iterator it = document.getRootElement().getChildren(children).iterator();
			 if(it.hasNext())
				 return Integer.valueOf(((Element) (it.next())).getChild("Timeout").getText());
		} catch (Exception e) {
		}
		return 60000;
	}
	

}
