package visadirect;

public class Address {
	private String country;
	private String state;
	private String county;
	private String zipCode;
	
	public Address(String country, String state, String county, String zipCode) {
		this.country = country;
		this.state = state;
		this.county = county;
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
