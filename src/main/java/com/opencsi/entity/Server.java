package com.opencsi.entity;

import java.net.ServerSocket;

public class Server
{
	public ServerSocket sock;
	private int port,max;
	
	public Server(int port, int max)
	{
		this.port = port;
		this.max = max;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public int getMax()
	{
		return max;
	}
	
	
}
