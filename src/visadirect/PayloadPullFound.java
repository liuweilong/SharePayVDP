package visadirect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PayloadPullFound extends PayloadCommon{
	
	private final String feeProgramIndicator = "123";
	private String senderCurrencyCode;
	private int systemsTraceAuditNumber;
	private String retrievalReferenceNumber;
	private double amount;
	private CardAcceptor cardAcceptor;
	private String senderPrimaryAccountNumber;
	private String senderCardExpiryDate;
	private PinData pinData;
	private HashMap<String, String> magneticStripeData = new HashMap<String, String>();
	private HashMap<String, String> pointOfServiceData = new HashMap<String, String>();;
	private HashMap<String, String> pointOfServiceCapability = new HashMap<String, String>();

	public PayloadPullFound(Date now,
			Transaction transaction) {
		super(now);
		this.setup(now, transaction);
	}

	private void setup(Date now,
			Transaction transaction) {
		this.amount = transaction.getAmount();
		this.senderPrimaryAccountNumber = transaction.getActionAccount().getAccountNumber();
		this.senderCardExpiryDate = transaction.getActionAccount().getCardExpiryDate();
		this.senderCurrencyCode = transaction.getActionAccount().getCurrencyCode();
		this.magneticStripeData.put("track1Data", "1010101010101010101010101010");
		this.pointOfServiceData.put("panEntryMode", "90");
		this.pointOfServiceData.put("posConditionCode", "0");
		this.pointOfServiceData.put("motoECIIndicator", "0");
		this.pointOfServiceCapability.put("posTerminalType", "4");
		this.pointOfServiceCapability.put("posTerminalEntryCapability", "2");
		this.pinData = new PinData("1cd948f2b961b682");
		this.cardAcceptor = new CardAcceptor();
		this.retrievalReferenceNumber = Utility.generateRetrievalReferenceNumber(Config.RETRIVAL_REFERENCE_FORMAT, now);
		this.systemsTraceAuditNumber = Utility.generateSystemsTraceAuditNumber();
	}
}
