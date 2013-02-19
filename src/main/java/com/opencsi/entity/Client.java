package com.opencsi.entity;

import java.net.Socket;

public class Client
{
	private String ip;
	private int port;
	public Socket sock;
	
	public Client(Socket sock)
	{
		this.sock = sock;
		this.ip = sock.getInetAddress().getHostAddress();
		this.port = sock.getPort();
	}
	
	public String toString()
	{
		return "Host: "+this.ip +" (Port: "+port+")";
	}

}
