package com.opencsi.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsi.core.Core;
import com.opencsi.entity.Client;
import com.opencsi.entity.Message;

public class TClient extends Thread
{
	private Client client;
	private PrintWriter out;
	private HTTP http;
	
	public TClient(Client client,int portSSL)
	{
		this.client = client;
		System.out.print("[" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) +"]: ");
		System.out.print("A client is connected: " + client.sock.getInetAddress().getHostName());
		System.out.println(" [" + client.sock.getInetAddress().getHostAddress()+"]"   + " on port " + client.sock.getPort() + ".");
		http = new HTTP(portSSL);
		try {
			this.client.sock.setSoTimeout(Message.getTimeOut("Client"));// For security
			out = new PrintWriter(client.sock.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{		
		try {
			// Retrieve Client value:
			byte[] data = http.getResponse(client.sock.getInputStream());
			// Send to the SSL Sock using synchronized protection if there is data:
			if (data != null)
			{
				String strSend;
				try {
					strSend = new String(Core.sslclient.send(data));
					out.print(strSend);
					out.flush();
				} catch (Exception e) {
					// [FEATURE]: put here the client message here (using the Messages entity class cf. config.xml).
				}
			}
		} catch (IOException e) {
			// Connection reset or 
			//e.printStackTrace();
		}
		try {
			System.out.print("[" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) +"]: ");
			System.out.print("Client " + client.sock.getInetAddress().getHostName());
			System.out.println(" [" + client.sock.getInetAddress().getHostAddress()+"] on port "+ client.sock.getPort()+" is disconnected.");
			out.close();
			client.sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
