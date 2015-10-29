package visadirect;

public class Account {
	private String accountNumber;
	private String currencyCode; // "US default"
	private String cardExpiryDate; // Format "yyyy-mm"
	
	public Account(String accountNumber, String cardExpiryDate, String currencyCode) {
		this.accountNumber = accountNumber;
		this.cardExpiryDate = cardExpiryDate;
		this.currencyCode = currencyCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}
}
