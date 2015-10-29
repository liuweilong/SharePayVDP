package visadirect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayloadMultiPullFound extends PayloadCommon{
	
	private List<PayloadMulltiPullSingleRequest> request = new ArrayList<PayloadMulltiPullSingleRequest>();
	
	public PayloadMultiPullFound(SimpleDateFormat retrievalReferenceFormat,
			SimpleDateFormat localTransactionDateTimeFormat,
			Date now,
			List<Transaction> transactions) {
		super(retrievalReferenceFormat, localTransactionDateTimeFormat, now);
		this.setup(retrievalReferenceFormat, localTransactionDateTimeFormat, now, transactions);
	}
	
	private void setup(SimpleDateFormat retrievalReferenceFormat,
			SimpleDateFormat localTransactionDateTimeFormat,
			Date now,
			List<Transaction> transactions) {
		for (Transaction transaction : transactions) {
			PayloadMulltiPullSingleRequest payload = new PayloadMulltiPullSingleRequest(transaction, retrievalReferenceFormat, localTransactionDateTimeFormat, now);
			request.add(payload);
		}
	}
}