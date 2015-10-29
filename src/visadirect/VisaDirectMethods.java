package visadirect;

import java.util.List;

public interface VisaDirectMethods {
	public boolean Transfer(List<Transaction> sendTransactions, Account receiverAccount);
}
