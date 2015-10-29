package visadirect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.sun.jna.platform.win32.SetupApi;

public abstract class PayloadCommon {
	
	private String localTransactionDateTime;
	private final int acquiringBin = 409999;
	private final String acquirerCountryCode = "101";
	private final String businessApplicationId = "AA";
	private final String merchantCategoryCode = "6012";
	
	public PayloadCommon(SimpleDateFormat retrievalReferenceFormat, SimpleDateFormat localTransactionDateTimeFormat, Date now){
		this.localTransactionDateTime = Utility.generateLocalTransactionDateTime(localTransactionDateTimeFormat, now);
	}
}
