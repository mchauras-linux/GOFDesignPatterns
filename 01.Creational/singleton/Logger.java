package com.creational.singleton;

public class Logger {
	
	// Initialize the private static reference of itself
	private static Logger loggerInstance = new Logger();
	
	//private constructor
	private Logger(){		
	}
	
	// method to return the single instance 
	public static Logger getLogger()
	{		
		return loggerInstance;
	}
	
	//other methods
	public void logMsg(String priority, String message) {
	
	}
}
