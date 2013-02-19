package com.opencsi.SSLTunnel;

import com.opencsi.core.Core;

public class App 
{
	
    public static void main( String[] args )
    {    
    	try{
    		(new Core(args)).run();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("[CRITICAL]: Closing server.");
    	}
    }
}
