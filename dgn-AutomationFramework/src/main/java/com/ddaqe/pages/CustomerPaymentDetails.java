package com.ddaqe.pages;

import com.ddaqe.base.BaseTest;

public class CustomerPaymentDetails extends BaseTest {

	private String billingAddress1;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String cardholdersName;
	private String creditcardtype;
	private String creditcardnumber;
	private String expiryMonth;
	private String expiryYear;

	public CustomerPaymentDetails() {
		PropertiesReaderCreditCarddetails();
		this.billingAddress1 = getProperty("billingAddress1");
		this.city = getProperty("city");
		this.state = getProperty("state");
		this.zipcode = getProperty("zipcode");
		this.country = getProperty("country");
		this.cardholdersName = getProperty("cardholdersName");
		this.creditcardtype = getProperty("creditcardtype");
		this.creditcardnumber = getProperty("creditcardnumber");
		this.expiryMonth = getProperty("expiryMonth");
		this.expiryYear = getProperty("expiryYear");
	}

	public String getBtillingAddress1() {
		return billingAddress1;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state.toUpperCase();
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCountry() {
		return country.toUpperCase();
	}

	public String getCardholdersName() {
		return cardholdersName;
	}

	public String getCreditcardtype() {
		return creditcardtype;
	}

	public String getCreditcardnumber() {
		return creditcardnumber;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}
}
