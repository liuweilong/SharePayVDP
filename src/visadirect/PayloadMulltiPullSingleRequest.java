package visadirect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PayloadMulltiPullSingleRequest {
	private int systemsTraceAuditNumber;
	private String retrievalReferenceNumber;
	private String senderCardExpiryDate;
	private CardAcceptor cardAcceptor;
	private String senderCurrencyCode;
	private String localTransactionDateTime;
	private HashMap<String, String> pointOfServiceData = new HashMap<String, String>();
	private HashMap<String, String> pointOfServiceCapability = new HashMap<String, String>();
	private final String feeProgramIndicator = "123";
	private String senderPrimaryAccountNumber;
	private double amount;
	private HashMap<String, String> magneticStripeData = new HashMap<String, String>();
	private PinData pinData;
	
	public PayloadMulltiPullSingleRequest(Transaction transaction, Date now) {
		// Customized
		this.senderPrimaryAccountNumber = transaction.getActionAccount().getAccountNumber();
		this.senderCardExpiryDate = transaction.getActionAccount().getCardExpiryDate();
		this.amount = transaction.getAmount();
		this.senderCurrencyCode = transaction.getActionAccount().getCurrencyCode();
		
		// Generated
		this.systemsTraceAuditNumber = Utility.generateSystemsTraceAuditNumber();
		this.retrievalReferenceNumber = Utility.generateRetrievalReferenceNumber(Config.RETRIVAL_REFERENCE_FORMAT, now);
		this.localTransactionDateTime = Utility.generateLocalTransactionDateTime(Config.LOCAL_TRANSACTION_DATETIME_FORMAT, now);
		this.cardAcceptor = new CardAcceptor();
		
		// Fixed
		this.magneticStripeData.put("track1Data", "1010101010101010101010101010");
		this.pointOfServiceData.put("panEntryMode", "90");
		this.pointOfServiceData.put("posConditionCode", "0");
		this.pointOfServiceData.put("motoECIIndicator", "0");
		this.pointOfServiceCapability.put("posTerminalType", "4");
		this.pointOfServiceCapability.put("posTerminalEntryCapability", "2");
		this.pinData = new PinData("1cd948f2b961b682");
//		this.transactionId = "234234234234234";
	}
	
}
