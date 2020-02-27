package com.jp.behavioral.chainresponsibility;



abstract class ApprovalPower {
	
	protected ApprovalPower successor;

	public void setSuccessor(ApprovalPower successor){
		this.successor = successor;
	}

	abstract public void processRequest(PurchaseRequest request);
}

class Manager extends ApprovalPower {
	private final double ALLOWABLE_LIMIT = 5000;

	public void processRequest(PurchaseRequest request ) {
		if( request.getAmount() < ALLOWABLE_LIMIT )
			System.out.println("Manager will approve $"+ request.getAmount());
		else
		   if( successor != null)
			   successor.processRequest(request);
  }
}

class Director extends ApprovalPower {
	private final double ALLOWABLE_LIMIT = 75000;
	public void processRequest(PurchaseRequest request ) {
		if( request.getAmount() < ALLOWABLE_LIMIT )
			System.out.println("Director will approve $"+ request.getAmount());
		else
			System.out.println( "Your request for $" + request.getAmount() + " needs a board meeting!");		  
  }
}

class VicePresident extends ApprovalPower {
	private final double ALLOWABLE_LIMIT = 25000;

	public void processRequest(PurchaseRequest request) {
		if( request.getAmount() < ALLOWABLE_LIMIT )
			System.out.println("Vice President will approve $" + request.getAmount());
		else
		if( successor != null )
			successor.processRequest(request);
  }
}

class President extends ApprovalPower {
	private final double ALLOWABLE_LIMIT = 40000;
   
	public void processRequest(PurchaseRequest request){
		if( request.getAmount() < ALLOWABLE_LIMIT )
			System.out.println("President will approve $" + request.getAmount());
		else
			 if( successor != null)
				   successor.processRequest(request);
	}
}

class PurchaseRequest {  
	private int requestNumber;
	private double amount;
	private String purpose;
	public PurchaseRequest(int requestNumber, double amount, String purpose){
		this.requestNumber = requestNumber;
		this.amount = amount;
		this.purpose = purpose;
	}
	public double getAmount() {
		return amount;
	}		
	public String getPurpose() {
		return purpose;
	}		
}

public class ApprovalWorkflowExample {
	public static void main(String[] args) throws Exception{
		Manager manager = new Manager();
		Director director = new Director();
		VicePresident vp = new VicePresident();
		President president = new President();
		// Link the chain at runtime 
		// Manager -> Vice President -> President --> Director
		manager.setSuccessor(vp);
		vp.setSuccessor(president);
		president.setSuccessor(director);
		
		// always leave the request at the start of the chain 
		manager.processRequest(new PurchaseRequest(0, 2000, "Stationery"));
		manager.processRequest(new PurchaseRequest(0, 12000, "Travel"));
		manager.processRequest(new PurchaseRequest(0, 28000, "International Travel"));		

  }
}
