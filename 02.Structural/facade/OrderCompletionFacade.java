package com.jpm.structural.facade;

public class OrderCompletionFacade {
	/**
	 * facade method takes in Order details and does the following 1. Process
	 * the order completion by calling the order module 2. Updates shipping
	 * information 3. Bills the order and Updates billing information 
	 */
	public void completeOrder(String orderId, String paymentId) {
		new OrderProcessing().completeOrder(); // might fetch and pass Order object to this method

		new BillingImpl().processBilling(); // might pass billing object to this method

		new Shipping().initiateShippingForOrder(); // might create a new shipping object and pass 
	}
}

class BillingImpl {
	public void processBilling() {
		// contains logic for processing the billing given the credit card
		// details
	}
}
class OrderProcessing {
	public void completeOrder() {
		// Updates the order records and completes the order
	}
}
class Shipping {
	public void initiateShippingForOrder() {
		// initiates the shipping request
	}
	public void captureShippingDetails() {
		// captures the address and does basic verification for zip code etc. 
	}
}