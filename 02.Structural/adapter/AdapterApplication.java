package com.jpm.structural.adapter;

public class AdapterApplication{
	public static void main(String[] args) {
		Socket socketAdapter = new ConnectionAdapter();		
		String output = socketAdapter.getOutput(); 
		System.out.println(output);			
	}
}

class ConnectionAdapter implements Socket {
	Plug plug = new Plug();
	/**
	* Method coming from the interface
	* Socket which we have to make to
	* fit the client plug
	*
	* @return Desired output of 5 AMP
	*/
	@Override
	public String getOutput() {
		
		String output = plug.getInput();
		return output;
		}
}

interface Socket {
	/**
	* This method is used to match the input to be
	* given to the Plug
	*
	* @return Output of the Plug (Client)
	*/
	public String getOutput();
}

class Plug {	
	private String specification = "5 AMP"; 
	public String getInput() {
	return specification;
	}
}



