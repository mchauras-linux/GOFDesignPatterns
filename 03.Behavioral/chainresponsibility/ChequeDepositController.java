package com.jp.behavioral.chainresponsibility;

public class ChequeDepositController {
	private static LedgerRecord ledgerRecordHandler = new LedgerRecord();
	private static AccountingRecord accountingRecordHandler = new AccountingRecord();
	private static ForexConversion forexConversionHandler = new ForexConversion();
	private static TaxationFees taxFeesHandler = new TaxationFees();
	private static CustomerRecord customerRecordHandler = new CustomerRecord();
	
	public static void main(String[] args) {
		
		ChequeDetails chequeDetails = new ChequeDetails();
		
		// if domestic cheque -- define the relevant workflow
		ledgerRecordHandler.setSuccessor(accountingRecordHandler);
		accountingRecordHandler.setSuccessor(taxFeesHandler);
		taxFeesHandler.setSuccessor(customerRecordHandler);
		// launch the request at the start of the chain
		ledgerRecordHandler.handleChequeDeposit(chequeDetails);
		
		// if international cheque -- define the relevant workflow
		ledgerRecordHandler.setSuccessor(forexConversionHandler);
		forexConversionHandler.setSuccessor(accountingRecordHandler);
		accountingRecordHandler.setSuccessor(taxFeesHandler);
		taxFeesHandler.setSuccessor(customerRecordHandler);
		// launch the request at the start of the chain
		ledgerRecordHandler.handleChequeDeposit(chequeDetails);
	}
}

class ChequeDetails {
	private int chequeValue;
	private String chequeId, micrCode;
	private String toAccountNo, fromAccountNo, fromBankCode, toBranchCode;
	private String currencyCode, countryCode, fromBranchCode;

}

abstract class TransactionHandler {
	protected TransactionHandler successor;

	public void setSuccessor(TransactionHandler successor) {
		successor = successor;
	}

	public abstract void handleChequeDeposit(ChequeDetails chequeDepositRequest);

	public abstract void handleWithdrawal(ChequeDetails chequeDepositRequest);
}

class LedgerRecord extends TransactionHandler {
	public void handleChequeDeposit(ChequeDetails chequeDepositRequest) {
		// update Ledger records with Cheque details
		if (super.successor != null) {
			super.successor.handleChequeDeposit(chequeDepositRequest);
		} else {
			// end the chain
		}
	}

	@Override
	public void handleWithdrawal(ChequeDetails chequeDepositRequest) {

	}
}

class AccountingRecord extends TransactionHandler {
	public void handleChequeDeposit(ChequeDetails chequeDepositRequest) {
		// update Accounting records with Cheque details
		if (super.successor != null) {
			super.successor.handleChequeDeposit(chequeDepositRequest);
		} else {
			// end the chain
		}
	}
	@Override
	public void handleWithdrawal(ChequeDetails chequeDepositRequest) {

	}
}

class CustomerRecord extends TransactionHandler {
	public void handleChequeDeposit(ChequeDetails chequeDepositRequest) {
		// update Ledger records with Cheque details
		if (super.successor != null) {
			super.successor.handleChequeDeposit(chequeDepositRequest);
		} else {
			// end the chain
		}
	}
	@Override
	public void handleWithdrawal(ChequeDetails chequeDepositRequest) {

	}
}

class TaxationFees extends TransactionHandler {
	public void handleChequeDeposit(ChequeDetails chequeDepositRequest) {
		/* if cheque type is domestic and greater than 10K, deduct taxes
		 * if cheque type is international, deduct taxes and 
		 * international transaction fees
		 */
		if (super.successor != null) {
			super.successor.handleChequeDeposit(chequeDepositRequest);
		} else {
			// end the chain
		}
	}

	@Override
	public void handleWithdrawal(ChequeDetails chequeDepositRequest) {

	}
}

class ForexConversion extends TransactionHandler {
	public void handleChequeDeposit(ChequeDetails chequeDepositRequest) {
		// convert forex to local currency and update ChequeDetails	
		
		if (super.successor != null) {
			super.successor.handleChequeDeposit(chequeDepositRequest);
		} 
	}
	@Override
	public void handleWithdrawal(ChequeDetails chequeDepositRequest) {

	}
}




