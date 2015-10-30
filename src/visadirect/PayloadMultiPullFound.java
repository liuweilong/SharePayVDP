package visadirect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayloadMultiPullFound extends PayloadCommon{
	
	private List<PayloadMulltiPullSingleRequest> request = new ArrayList<PayloadMulltiPullSingleRequest>();
	
	public PayloadMultiPullFound(Date now,
			List<Transaction> transactions) {
		super(now);
		this.setup(now, transactions);
	}
	
	private void setup(Date now,
			List<Transaction> transactions) {
		for (Transaction transaction : transactions) {
			PayloadMulltiPullSingleRequest payload = new PayloadMulltiPullSingleRequest(transaction, now);
			request.add(payload);
		}
	}
}