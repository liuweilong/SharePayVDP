package visadirect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utility {
	
	private static String BASE_X_TRANSACTION_ID = "8888888";
	
	private static int rangeRandom(int min, int max) {
		return new Random().nextInt(max + 1 - min) + min;
	}
	
	public static String generateXTransactionId() {
		int min = 100000000;
		int max = 1000000000;
		return BASE_X_TRANSACTION_ID + Integer.toString(rangeRandom(min, max));
	}
	
	public static int generateSystemsTraceAuditNumber() {
		int systemsTraceAuditNumberMin = 100000;
		int systemsTraceAuditNumberMax = 1000000;
		return rangeRandom(systemsTraceAuditNumberMin, systemsTraceAuditNumberMax);
	}
	
	public static String generateRetrievalReferenceNumber(SimpleDateFormat format, Date now) {
		SimpleDateFormat yearFormat = new SimpleDateFormat("y");
		String year = yearFormat.format(now);
		return year.substring(3) + format.format(now);
	}
	
	public static String generateLocalTransactionDateTime(SimpleDateFormat format, Date now) {
		return format.format(now);
	}
}
