package com.jp.behavioral.state;

/*	This code demonstrates the State pattern which 
 * allows an Account to behave differently depending on its balance. 
 * The difference in behavior is delegated to State objects called RedState, 
 * SilverState and GoldState. These states represent overdrawn accounts, 
 * starter accounts, and accounts in good standing.*/ 

public class AccountBehaviorDemo {

	public static void main(String[] args) {
		// Open a new account

		Account account = new Account("Jim Johnson");
		// Apply financial transactions
		account.deposit(500.0);
		account.deposit(1300.0);
		account.deposit(1550.0);
		account.payInterest();
		account.withdraw(2500.00);
		account.payInterest();
		account.withdraw(1100.00);
		account.deposit(650.0);
		account.deposit(1750.0);
	}
}



// / The 'State' abstract class
abstract class State {
	protected Account account;
	protected double balance;
	protected double interest;
	protected double lowerLimit;
	protected double upperLimit;
	public abstract void deposit(double amount);
	public abstract void withdraw(double amount);
	public abstract void payInterest();
	public abstract void chargeFees();

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}

// 'ConcreteState' class
// Red indicates that account is overdrawn (balance less than 0)
class RedState extends State {
	private double _serviceFee;
	public RedState(State currentState) {
		this.balance = currentState.balance;
		this.account = currentState.account;
		initialize();
	}	
	private void initialize() {
		// Should come from a datasource
		interest = 0.0;
		lowerLimit = -100.0;
		upperLimit = 0.0;
		_serviceFee = 15.00;
	}	
	public void deposit(double amount)	{
		balance += amount;
		checkIfStateChangeRequired();
	}
	public void withdraw(double amount)	{
		// amount = amount - _serviceFee;
		System.out.println("No funds available for withdrawal!");
	}
	public void payInterest()	{
		// No interest is paid
	}
	private void checkIfStateChangeRequired() {
		if (balance > upperLimit) {
			account.setState(new SilverState(this));
		}
	}	
	public void chargeFees() {
		// charge daily fees 		
	}
}

//  'ConcreteState' class for balance greater than 0
// and less than 1000
// Silver indicates a non-interest bearing state
class SilverState extends State
{
	private double _serviceFee;
	// Constructor
	public SilverState(State currentState) {
		this.balance = currentState.balance;
		this.account = currentState.account;
		initialize();
	}
	// Constructor
	public SilverState(double balance, Account account) {
		this.balance = balance;
		this.account = account;
		initialize();
	}
	private void initialize()	{
		// Should come from a datasource
		interest = 0.0;
		lowerLimit = 0.0;
		upperLimit = 1000.0;
		_serviceFee = 0.02;
	}
	public void deposit(double amount)	{
		balance += amount;
		checkIfStateChangeRequired();
	}

	public void withdraw(double amount)	{
		balance -= amount;
		checkIfStateChangeRequired();
	}

	public void payInterest()	{
		balance += interest * balance;
		checkIfStateChangeRequired();
	}
	private void checkIfStateChangeRequired()
	{
		if (balance < lowerLimit)
		{
			account.setState(new RedState(this));
		}
		else if (balance > upperLimit)
		{
			account.setState(new GoldState(this));
		}
	}
	@Override
	public void chargeFees() {
		// charge monthly fees 		
	}
}

// A 'ConcreteState' class for balance greater than 1000
// Gold indicates an interest bearing state. 
class GoldState extends State {

	// Constructor
	public GoldState(State currentState) {
		this.balance = currentState.balance;
		this.account = currentState.account;
		initialize();
	}
	private void initialize() {
		// Should come from a database
		interest = 0.05;
		lowerLimit = 1000.0;
		upperLimit = 10000000.0;
	}

	public void deposit(double amount) {
		balance += amount;
		checkIfStateChangeRequired();
	}

	public void withdraw(double amount) {
		balance -= amount;
		checkIfStateChangeRequired();
	}

	public void payInterest() {
		balance += interest * balance;
		checkIfStateChangeRequired();
	}

	private void checkIfStateChangeRequired() {
		if (balance < 0.0) {
			account.setState(new RedState(this));
		} else if (balance < lowerLimit) {
			account.setState(new SilverState(this));
		}
	}
	@Override  // no fees charged
	public void chargeFees() {
		// no fees charged 		
	}

}

class Account {
	private State state;
	private String owner;

	// Constructor
	public Account(String owner) {
		// New accounts are 'Silver' by default
		this.owner = owner;
		this.state = new SilverState(0.0,this);
	}

	// Properties
	public double getBalance() {
		return state.getBalance();
	}

	public State getState() {
		return state;
	}
	public void deposit(double amount) {
		state.deposit(amount);
		System.out.println(" deposited  " + amount);
		System.out.println(" balance = " + state.getBalance());
		System.out.println(" Status = " + this.state.getClass().getName());
		System.out.println("********");
	}

	public void withdraw(double amount) {
		state.withdraw(amount);
		System.out.println(" Withdrew  " + amount);
		System.out.println(" balance = " + state.getBalance());
		System.out.println(" Status = " + this.state.getClass().getName());
		System.out.println("********");
	}

	public void payInterest() {
		state.payInterest();
		System.out.println("Interest Paid  ");
		System.out.println(" balance = " + state.getBalance());
		System.out.println(" Status = " + this.state.getClass().getName());
		System.out.println("********");
	}

	public void setState(State state) {
		this.state = state;
	}

}
