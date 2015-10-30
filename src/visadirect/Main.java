package visadirect;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Account receiver = new Account("4957030420210454", "2013-03", "USD");
		Account sender = new Account("4957030090295512", "2013-03", "USD");
		Transaction transaction = new Transaction(sender, 300, true);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		
		VisaDirectMethods methods = new VisaDirectMethodsImpl();
		System.out.println(methods.Transfer(transactions, receiver));
	}
}