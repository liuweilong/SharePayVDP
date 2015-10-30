package visadirect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PayloadPushFound extends PayloadCommon{
	
	private int systemsTraceAuditNumber;
	private String retrievalReferenceNumber;
	private String senderReference = "";
	private String senderAccountNumber;
	private String transactionCurrencyCode = "USD";
	private String recipientName = "Bob";
	private String recipientPrimaryAccountNumber;
	private double amount; 
	private String transactionId;
	private String sourceOfFundsCode;
	private CardAcceptor cardAcceptor;
	private HashMap<String, String> pointOfServiceData = new HashMap<String, String>();
	private HashMap<String, String> pointOfServiceCapability = new HashMap<String, String>();
	private HashMap<String, String> magneticStripeData = new HashMap<String, String>();
	private PinData pinData;

	public PayloadPushFound(Date now, 
			String sourceofFundsCode, 
			String transactionId, 
			Transaction transaction,
			Account sender) {
		super(now);
		
		this.recipientPrimaryAccountNumber = transaction.getActionAccount().getAccountNumber();
		this.amount = transaction.getAmount();
		this.transactionId = transactionId;
		this.sourceOfFundsCode = sourceofFundsCode;
		this.senderAccountNumber = sender.getAccountNumber();
		
		this.systemsTraceAuditNumber = Utility.generateSystemsTraceAuditNumber();
		this.retrievalReferenceNumber = Utility.generateRetrievalReferenceNumber(Config.RETRIVAL_REFERENCE_FORMAT, now);
		this.cardAcceptor = new CardAcceptor();
		
		// Fixed
		this.magneticStripeData.put("track1Data", "1010101010101010101010101010");
		this.pointOfServiceData.put("panEntryMode", "90");
		this.pointOfServiceData.put("posConditionCode", "0");
		this.pointOfServiceData.put("motoECIIndicator", "0");
		this.pointOfServiceCapability.put("posTerminalType", "4");
		this.pointOfServiceCapability.put("posTerminalEntryCapability", "2");
		this.pinData = new PinData("1cd948f2b961b682");
	}

}
