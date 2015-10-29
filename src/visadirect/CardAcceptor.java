package visadirect;

public class CardAcceptor {
	private String name;
	private String terminalId;
	private String idCode;
	private Address address;
	
	public CardAcceptor() {
		this.name = "Acceptor 1";
		this.terminalId = "365539";
		this.idCode = "VMT200911026070";
		this.address = new Address("USA", "CA", "081", "94404");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
