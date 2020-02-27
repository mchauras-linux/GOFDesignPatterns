package com.jp.behavioral.observer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TestAccountObserver {
	
	public static void main(String[] args) {
		SavingsAccount sa1 = new SavingsAccount("SA01234");
		SavingsAccount sa2 = new SavingsAccount("SA02345");
		SavingsAccount sa3 = new SavingsAccount("SA04567");
		SavingsAccount sa4 = new SavingsAccount("SA06789");
		sa1.subscribeForDepositAlerts(new SMSAlert());
		sa1.subscribeForDepositAlerts(new EmailAlert());
		sa1.subscribeForWithdrawalAlerts(new SMSAlert());
		sa1.subscribeForWithdrawalAlerts(new EmailAlert());
		sa2.subscribeForDepositAlerts(new SMSAlert());
		sa2.subscribeForDepositAlerts(new EmailAlert());
		sa3.subscribeForWithdrawalAlerts(new SMSAlert());
		sa3.subscribeForWithdrawalAlerts(new EmailAlert());
		sa4.subscribeForDepositAlerts(new SMSAlert());		
		sa4.subscribeForWithdrawalAlerts(new SMSAlert());
				
		sa1.deposit(5000);
		sa1.withdrawal(7000);
		sa1.deposit(8000);
		
		sa2.deposit(10000);
		sa2.withdrawal(12000);
		
		sa3.deposit(15000);
		sa3.withdrawal(18000);
		
		sa4.deposit(20000);
		sa4.withdrawal(25000);
		
	}	
}

// subject being observed
class SavingsAccount
{
	private Withdrawal withdrawal;
	private Deposit deposit; 
	private int currentBalance; 
	private String bankAccountId; 
	public SavingsAccount(String bankAccountId)	{
		this.bankAccountId = bankAccountId;
		withdrawal = new Withdrawal();
		deposit = new Deposit();
	}
	public void subscribeForDepositAlerts(AccountObserver observer)	{
		deposit.subscribe(observer);
	}
	public void subscribeForWithdrawalAlerts(AccountObserver observer)	{
		withdrawal.subscribe(observer);
	}
	
	public void deposit(int depositAmt)	{
		deposit.depositIntoAct(depositAmt);
	}
	public void withdrawal(int withdrawAmt)	{
		withdrawal.withdrawalFromAct(withdrawAmt);
	}		
	
	//Whenever a debit occurs on an account more than a specific amount
	private class Withdrawal extends AccountObservable {		
		public void withdrawalFromAct(int withdrawAmt)
		{
			String message = bankAccountId + " : Withdrawal amount : "+withdrawAmt;
			System.out.println("\n ######## "+message);
			notifySubscribers(message);		
		}	
	}
	// Whenever a credit occurs on an account more than a specific amount
	private class Deposit extends AccountObservable {
		public void depositIntoAct(int depositAmt)
		{
			String message = bankAccountId +" : Depositing amount : "+depositAmt;
			System.out.println("\n ######## "+message);
			notifySubscribers(message);		
		}
	}	
}


// Observable - List of event classes which are interested in particular event
class AccountObservable {
	ArrayList<AccountObserver> observers = new ArrayList<AccountObserver>();
	
	public void subscribe(AccountObserver observer) {
		observers.add(observer);		
	}

	public void unSubscribe(AccountObserver observer) {
		observers.remove(observer);	
	}

	public void notifySubscribers(String message) {
		System.out.println("****** Notify Subscribers called *******");
		// notify all the subscribers
		for (AccountObserver observer : observers) {
			observer.sendAlert(message);			
		}
	}
}


abstract class AccountObserver {
	public abstract void sendAlert(String message);
}

// Concrete Observers
class SMSAlert extends AccountObserver {
	@Override
	public void sendAlert(String message) {
		System.out.println("Sending SMS: " + message);
	}
}

//Concrete Observers 
class EmailAlert extends AccountObserver {
	@Override
	public void sendAlert(String message) {
		System.out.println("Sending Email: " + message);
	}

}