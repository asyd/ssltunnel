package com.opencsi.entity;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import javax.net.ssl.SSLSocket;

public class SSLClient
{
	public SSLSocket sock;
	private KeyStore certificate;
	public SocketChannel csock;
	
	public SSLClient(SSLSocket sock,KeyStore certificate) throws IOException
	{
		this.certificate = certificate;
		this.sock = sock;
		this.sock.setUseClientMode(true);
		this.sock.setSoTimeout(60000);// For security
		this.sock.startHandshake();
	}
	
	public KeyStore getCertificate()
	{
		return certificate;
	}
}
