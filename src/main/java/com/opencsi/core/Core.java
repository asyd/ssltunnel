package com.opencsi.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.beust.jcommander.JCommander;
import com.opencsi.entity.Arguments;
import com.opencsi.entity.Message;
import com.opencsi.service.Debug;
import com.opencsi.service.SSLClient;
import com.opencsi.service.Server;
import com.opencsi.service.TClient;

public class Core
{
	private Server server;
	static public SSLClient sslclient;
	static public Arguments arguments;
	static public Debug log;
	
	public Core(String[] args) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException
	{
		System.out.println("Get arguments for the server..");
		// [DEV MODE]:
		sslclient = new SSLClient("localhost",8443,"superadmin.p12","ejbca");
		server = new Server(8001,150);
		// [END DEV MODE]
		arguments = new Arguments();
		//new JCommander(arguments,args);
		if (arguments.getDebug())
			log = new Debug();
		System.out.println("Try to connect into the SSL Server...");
		//sslclient = new SSLClient(arguments.getTargetHost(),arguments.getTargetPort(),arguments.getClientCertificate(),arguments.getCertificatePassphrase());
		System.out.println("Done!");
		System.out.println("Create the server into the "+arguments.getListenPort()+" port with "+(arguments.getMax() == 0?150 : arguments.getMax())+" max connection...");
		//server = new Server(arguments.getListenPort(),arguments.getMax() == 0?150 : arguments.getMax());
		System.out.println("Awaiting for client connection...");
	}
	
	public void run()
	{
		while(true)
			(new TClient(server.connection(),arguments.getTargetPort())).start();
	}
}
