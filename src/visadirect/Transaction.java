package visadirect;

public class Transaction {
	private boolean sender;
	private double amount;
	private Account actionAccount;
	
	public Transaction(Account account, double amount, boolean sender) {
		this.sender = sender;
		this.amount = amount;
		this.actionAccount = account;
	}

	public boolean isSender() {
		return sender;
	}

	public double getAmount() {
		return amount;
	}

	public Account getActionAccount() {
		return actionAccount;
	}
	
	
}
