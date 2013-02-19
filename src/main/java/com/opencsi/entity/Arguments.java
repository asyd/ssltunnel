package com.opencsi.entity;

import com.beust.jcommander.Parameter;

public class Arguments
{
	@Parameter(names = "--listenPort", description = "Port to listen", required = true)
	private int listenPort;
	
	@Parameter(names = "--targetHost", description = "Host to connect", required = true)
	private String targetHost;
	
	@Parameter(names = "--targetPort", description = "Port to Host", required = true)
	private int targetPort;
	
	@Parameter(names = "--clientCertificate", description = "Client Certificate for SSL connection", required = true)
	private String clientCertificate;
	
	@Parameter(names = "--certificatePassphrase", description = "Passphrase to use the certificate client", required = true)
	private String certificatePassphrase;

	@Parameter(names = "--max", description = "Maximum client connection", required = false)
	private int max;
	
	@Parameter(names = "--debug", description = "Debug Mode.", hidden = false)
	private boolean debug = false;
	
	
	public int getListenPort()
	{
		return listenPort;
	}
	
	public int getTargetPort()
	{
		return targetPort;
	}
	
	public String getTargetHost()
	{
		return targetHost;
	}
	
	public String getClientCertificate()
	{
		return clientCertificate;
	}
	
	public String getCertificatePassphrase()
	{
		return certificatePassphrase;
	}
	
	public int getMax()
	{
		return max;
	}
	
	public boolean getDebug()
	{
		return debug;
	}
}
