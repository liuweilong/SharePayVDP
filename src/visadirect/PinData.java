package visadirect;

import java.util.HashMap;

public class PinData {

	private HashMap<String, Integer> securityRelatedControlInfo;
	private String pinDataBlock;
	
	public PinData(String pinDataBlock) {
		securityRelatedControlInfo = new HashMap<String, Integer>();
		securityRelatedControlInfo.put("pinBlockFormatCode", 1);
		securityRelatedControlInfo.put("zoneKeyIndex", 1);
		this.pinDataBlock = pinDataBlock;
	}

	public String getPinDataBlock() {
		return pinDataBlock;
	}

	public void setPinDataBlock(String pinDataBlock) {
		this.pinDataBlock = pinDataBlock;
	}
}
