package com.opencsi.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Debug
{
	
	private Writer f;
	private BufferedWriter out;
	private SimpleDateFormat format;
	
	public Debug()
	{		
		try {
			f = new FileWriter((new Date())+".log");
			out = new BufferedWriter(f);
			format = new SimpleDateFormat("[dd/MM/yyyy hh:mm:ss]: ");
		} catch (IOException e) {
			System.out.println("[LOG]: Cannot create the log file.");
		}
	}
	
	public void write(String stream)
	{
		try {
			String flux  = format.format(new Date()) + stream + "\n";
			out.write(flux);
		} catch (IOException e) {
		}
	}
	
	public void close()
	{
		try {
			out.close();
		} catch (IOException e) {
		}
	}
	
	
}
