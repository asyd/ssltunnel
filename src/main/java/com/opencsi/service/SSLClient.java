package com.opencsi.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

import com.opencsi.core.Core;
import com.opencsi.entity.Message;

public class SSLClient
{
	//private com.opencsi.entity.SSLClient client;
	private com.opencsi.entity.SSLClient client;
	private PrintWriter out;
	private String host;
	private int port;
	private SSLContext sslContext;
	private KeyStore ks;
	
	public SSLClient(String host,int port,String certificatePath,String passphrase) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, KeyManagementException
	{
		// [REMOVE]
		MyProvider p = new MyProvider();
		java.security.Security.addProvider(p);
		java.security.Security.setProperty("ssl.TrustManagerFactory.algorithm", "TrustAllCertificates");
		// [END REMOVE]
		// Creation of the trust certificate SSL connection:
		ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(certificatePath),passphrase.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, passphrase.toCharArray());
		sslContext = SSLContext.getInstance("SSLv3");
		sslContext.init(kmf.getKeyManagers(), null, null);
		// SSL Client Socket:
		client = new com.opencsi.entity.SSLClient((SSLSocket) sslContext.getSocketFactory().createSocket(host, port), ks);
		client.sock.close();
		/////////////////
		this.host = host;
		this.port = port;
	}
	
	// [FIXME] synchronized
	public synchronized byte[] send(byte[] stream)
	{
		byte[] buffer = new byte[0];
		try{
			client = new com.opencsi.entity.SSLClient((SSLSocket) sslContext.getSocketFactory().createSocket(host, port), ks);
			if (Core.arguments.getDebug())
			{
				Core.log.write("Client Sended:");
				Core.log.write(new String(stream));
			}
			///////////////////////////////
			out = new PrintWriter(client.sock.getOutputStream());
			buffer = new byte[client.sock.getReceiveBufferSize()];
			out.print(new String(stream));
			out.flush();
			///////////////////////////////
			HTTP http = new HTTP(port);
			buffer = http.getResponse(client.sock.getInputStream());
			if (Core.arguments.getDebug())
			{
				Core.log.write("Client Received:");
				Core.log.write(new String(buffer));
			}
			///////////////////////////////
		}
		catch(ConnectException e)
    	{
    		System.out.println(Message.getSSLError("NoConnection"));
    	}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		try {
			out.close();
			client.sock.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return buffer;
	}
}
