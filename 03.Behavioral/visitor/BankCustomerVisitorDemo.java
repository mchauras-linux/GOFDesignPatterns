package com.jp.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class BankCustomerVisitorDemo {
	public static void main(String[] args) {
		List<HomeLoanCustomer> homeLoanCustomers = new ArrayList<HomeLoanCustomer>();
		List<DematCustomer> dematCustomers = new ArrayList<DematCustomer>();
		List<SavingsBankAccountCustomer> savingsBankActCustomers = new ArrayList<SavingsBankAccountCustomer>();
		List<ForexTradingCustomer> forexTradingCustomers = new ArrayList<ForexTradingCustomer>();
		
		// master list of HomeLoanCustomers, DematCustomers, 
		// SavingsBankAccountCustomers and ForexTradingCustomers
		List<VisitableCustomer> allCustomers = new ArrayList<VisitableCustomer>();
		
		List<CustomerVisitor> visitors = new ArrayList<CustomerVisitor>();
		visitors.add(new PrintPhotoId()); 
		visitors.add(new PrintNewYearGreetings());
		visitors.add(new PrintDiscountCoupons());
		//optionB 
		for (CustomerVisitor visitor : visitors) {
			for (VisitableCustomer customer : allCustomers) {
				customer.accept(visitor);
			}
		}
		
		
		// option A 
		for (CustomerVisitor visitor : visitors) {
			// for each visitor, do the action for all customers
			for (DematCustomer dematCustomer : dematCustomers) {
				visitor.visitDematCustomer(dematCustomer);
			}			
			for (HomeLoanCustomer homeLoanCustomer : homeLoanCustomers) {
				visitor.visitHomeLoanCustomer(homeLoanCustomer);
			}
			for (SavingsBankAccountCustomer sbCustomer : savingsBankActCustomers) {
				visitor.visitSavingsBankAccountCustomer(sbCustomer);
			}
			for (ForexTradingCustomer fxCustomer : forexTradingCustomers) {
				visitor.visitForexTradingCustomer(fxCustomer);
			}
		}
		
		
	}
}

interface CustomerVisitor {
	public void visitHomeLoanCustomer(HomeLoanCustomer hlCustomer);

	public void visitSavingsBankAccountCustomer(
			SavingsBankAccountCustomer sbaCustomer);

	public void visitDematCustomer(DematCustomer dematCustomer);

	public void visitForexTradingCustomer(ForexTradingCustomer fxCustomer);
}

class PrintPhotoId implements CustomerVisitor {
	public void visitHomeLoanCustomer(HomeLoanCustomer hlCustomer) {
		// prints photo id for HomeLoanCustomer
	}

	public void visitSavingsBankAccountCustomer(
			SavingsBankAccountCustomer sbaCustomer) {
		// prints photo id for SavingsBankAccountCustomer
	}

	public void visitDematCustomer(DematCustomer dematCustomer) {
		// prints photo id for DematCustomer
	}

	public void visitForexTradingCustomer(ForexTradingCustomer fxCustomer) {
		// prints photo id for ForexTradingCustomer
	}
}

class PrintNewYearGreetings implements CustomerVisitor {
	public void visitHomeLoanCustomer(HomeLoanCustomer hlCustomer) {
		// prints new year greetings for HomeLoanCustomer
	}

	public void visitSavingsBankAccountCustomer(
			SavingsBankAccountCustomer sbaCustomer) {
		// prints new year greetings for SavingsBankAccountCustomer
	}

	public void visitDematCustomer(DematCustomer dematCustomer) {
		// prints new year greetings for DematCustomer
	}

	public void visitForexTradingCustomer(ForexTradingCustomer fxCustomer) {
		// prints new year greetings for ForexTradingCustomer
	}
}

class PrintDiscountCoupons implements CustomerVisitor {
	public void visitHomeLoanCustomer(HomeLoanCustomer hlCustomer) {
		// prints discount coupons for HomeLoanCustomer
	}

	public void visitSavingsBankAccountCustomer(
			SavingsBankAccountCustomer sbaCustomer) {
		// prints discount coupons for SavingsBankAccountCustomer
	}

	public void visitDematCustomer(DematCustomer dematCustomer) {
		// prints discount coupons for DematCustomer
	}

	public void visitForexTradingCustomer(ForexTradingCustomer fxCustomer) {
		// prints discount coupons for ForexTradingCustomer
	}
}
interface VisitableCustomer {
	public void accept(CustomerVisitor visitor);
}

class HomeLoanCustomer implements VisitableCustomer{
	public void accept(CustomerVisitor visitor) {
		visitor.visitHomeLoanCustomer(this);
	}
	// other related attributes & methods of Home Loan Customer
	private String homeLoanCustomerName;
	private String homeLoanId;
	private int homeLoanCustomerId;
	private String branchCode;
}

class SavingsBankAccountCustomer implements VisitableCustomer{
	public void accept(CustomerVisitor visitor) {
		visitor.visitSavingsBankAccountCustomer(this);
	}
	// other related attributes & methods of Savings Bank Account Customer
	private String customerName;
	private String bankAccountId;
	private int customerId;
	private String branchCode;

}

class DematCustomer implements VisitableCustomer  {
	public void accept(CustomerVisitor visitor) {
		visitor.visitDematCustomer(this);
	}
	// other related attributes & methods related to Demat Customer
	private String dematCustomerName;
	private String dematAccountId;
	private int dematCustomerId;
	private String branchCode;
}

class ForexTradingCustomer implements VisitableCustomer {
	public void accept(CustomerVisitor visitor) {
		visitor.visitForexTradingCustomer(this);
	}
	// other related attributes & methods of Forex Trading
	private String customerName;
	private String forexTradingActId;
	private int forexTradingCustomerId;

}
