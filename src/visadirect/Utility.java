package visadirect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utility {
	public static int generateSystemsTraceAuditNumber() {
		int systemsTraceAuditNumberMin = 100000;
		int systemsTraceAuditNumberMax = 999999;
		return new Random().nextInt(systemsTraceAuditNumberMax-systemsTraceAuditNumberMin) + systemsTraceAuditNumberMin;
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
