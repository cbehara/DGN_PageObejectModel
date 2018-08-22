package com.ddaqe.pages;

import com.ddaqe.base.BaseTest;
import com.ddaqe.utils.CommonUtils;

public class ssoUserFields extends BaseTest {

	private String firstName;
	private String lastName;
	private String companyName;
	private String address1;
	private String address2;
	private String countryName;
	private String cityName;
	private String stateName;
	private String zipCode;
	private String phNo;
	private String emailDomain;
	private String userName1;
	private String password1;
	private String secretQuestionAnswer;

	private String firmType;
	private String industryRole;

	public ssoUserFields() {
		PropertiesReader();
		this.firstName = getProperty("firstName");
		this.lastName = getProperty("lastName");
		this.companyName = getProperty("companyName");
		this.address1 = getProperty("address1");
		this.address2 = getProperty("address2");
		this.countryName = getProperty("countryName");
		this.cityName = getProperty("cityName");
		this.stateName = getProperty("stateName");
		this.zipCode = getProperty("zipCode");
		this.phNo = getProperty("phNo");
		this.emailDomain= getProperty("emailDomain");
		this.userName1 = getProperty("userName1");
		this.password1 = getProperty("password1");
		this.secretQuestionAnswer = getProperty("secretQuestionAnswer");
		this.firmType = getProperty("firmType");
		this.industryRole = getProperty("industryRole");
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPhNo() {
		return phNo;
	}
	
	public String getEmailDomain() {
		return emailDomain;
	}

	public String getUserName1() {
		return appendEmailDomain(CommonUtils.appendRandomNumberInString(userName1));
	}

	public String getPassword1() {
		return password1;
	}

	public String getSecretQuestionAnswer() {
		return secretQuestionAnswer;
	}

	public String getFirmType() {
		return firmType;
	}

	public String getIndustryRole() {
		return industryRole;
	}

	public String appendEmailDomain(String name) {
		return name + "@construction.com";
	}
}
