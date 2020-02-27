package com.creational.builder;

import java.util.ArrayList;
import java.util.List;

//Abstract Builder
abstract class CustomerHandler {
	abstract void initiateHandling();
	abstract void endHandling();
	abstract void handleCustomer(Customer cust);
}

// Product
class Customer {
	int id;
	String custName, city, address;
	String emailId;	
}

	
// Concrete Builder for writing into CSV file
class CSVHandler extends CustomerHandler {	
	@Override	
	public void initiateHandling() {
		// create a new csv file and create a IO writer 
	}
	@Override
	public void handleCustomer(Customer cust) {
		// add each customer record to the csv file 
	}
	@Override
	void endHandling() {
		// close the writer and the file
	}	
}

//Concrete Builder for writing into Text file
class TextFileHandler extends CustomerHandler {	
	@Override	
	public void initiateHandling() {
		// create a new text file and create a IO writer 
	}
	@Override
	public void handleCustomer(Customer cust) {
		// add each customer record to the text file 
	}
	@Override
	void endHandling() {
		// close the writer and the file
	}	
}

//Concrete Builder for writing into Oracle database
class DbHandler extends CustomerHandler {	
	@Override	
	public void initiateHandling() {
		// connect to the database
	}
	@Override
	public void handleCustomer(Customer cust) {
		// add each customer record to the db table 
	}
	@Override
	void endHandling() {
		// Commit and close the connection
	}	
}

//Concrete Builder for sending emails
class CustomerEmailHandler extends CustomerHandler {	
	@Override	
	public void initiateHandling() {
		// do nothing
	}
	@Override
	public void handleCustomer(Customer cust) {
		// if email id exists send email  
	}
	@Override
	void endHandling() {
		// do nothing
	}	
}

//Concrete Builder for writing into CSV file
class SummaryReportHandler extends CustomerHandler {	
	@Override	
	public void initiateHandling() {
		// initialize parameters 
	}
	@Override
	public void handleCustomer(Customer cust) {
		// maintain internal state for the report 
		// and change it based on the customer record  
	}
	@Override
	void endHandling() {
		// process the report. Publish and save the same. 
	}	
}

// Director
public class CustomerRecordDirector {	

	public CustomerRecordDirector(String filepath) {
		// store the XML file path 
	}

	private void processCustomerFile(List<CustomerHandler> handlers) {
		for (CustomerHandler customerHandler : handlers) {
			customerHandler.initiateHandling();
		}
		
		// parse and read the XML file
		// create Customer record for each record
		Customer customer = new Customer();
		// for each customer call the handlers 
		for (CustomerHandler customerHandler : handlers) {
			customerHandler.handleCustomer(customer);
		}
		
		// After the entire parsing is done
		for (CustomerHandler customerHandler : handlers) {
			customerHandler.endHandling();
		}
	}
	
	
	// Client code calling the director
	public static void main(String args[]) {
		CustomerRecordDirector director = 
			new CustomerRecordDirector("C:/customer.xml");
		// client can select the handlers dynamically which are needed
		List<CustomerHandler> handlers = new ArrayList<CustomerHandler>();
		handlers.add(new SummaryReportHandler());
		handlers.add(new CSVHandler());
		handlers.add(new TextFileHandler());
		handlers.add(new DbHandler());
		handlers.add(new CustomerEmailHandler());
		
		director.processCustomerFile(handlers);
	}
}
