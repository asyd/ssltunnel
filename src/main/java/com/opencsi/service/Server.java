package com.opencsi.service;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
	private com.opencsi.entity.Server server;
	
	public Server(int port,int max)
	{
		// initialize the attributes server:
		server = new com.opencsi.entity.Server(port,max);
		// Create the server socket:
		try
		{
			// socket + bind + listen:
			server.sock = new ServerSocket(server.getPort(),server.getMax());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public com.opencsi.entity.Client connection()
	{
		try {
			return new com.opencsi.entity.Client(server.sock.accept());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void shutdown()
	{
		System.out.println("Close Server socket...");
		try {
			server.sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
