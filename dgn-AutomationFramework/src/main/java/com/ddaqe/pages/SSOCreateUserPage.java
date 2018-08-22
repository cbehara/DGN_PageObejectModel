package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CoreFrameworkException;
import com.ddaqe.utils.SeleniumUtils;

public class SSOCreateUserPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtFirstName")
	private WebElement firstNameTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtLastName")
	private WebElement lastNameTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtCompanyName")
	private WebElement companyNameTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtAddressLine1")
	private WebElement addressLine1Txt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_ddlCountry")
	private WebElement ddlCountry;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtCity")
	private WebElement cityTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_ddlState")
	private WebElement stateTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtZip")
	private WebElement zipTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_UserInformation1_txtPhoneNumber")
	private WebElement phNoTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserInformation1_ibtStep1_Next")
	private WebElement step1NextBtn;

	@FindBy(how = How.ID, using = "MainContent_CUUserAccount1_UserAccount1_txtUserName1")
	private WebElement userName1Txt;

	@FindBy(how = How.ID, using = "MainContent_CUUserAccount1_UserAccount1_txtUserName2")
	private WebElement userName2Txt;

	@FindBy(how = How.ID, using = "MainContent_CUUserAccount1_UserAccount1_txtPassword1")
	private WebElement password1Txt;

	@FindBy(how = How.ID, using = "MainContent_CUUserAccount1_UserAccount1_txtPassword2")
	private WebElement password2Txt;

	@FindBy(how = How.ID, using = "MainContent_CUUserAccount1_UserAccount1_txtSecretQuestionAnswer")
	private WebElement secretQuestionAnswerTxt;

	@FindBy(how = How.ID, using = "MainContent_CUUserAccount1_ibtStep2_Next")
	private WebElement step2NextBtn;

	@FindBy(how = How.ID, using = "MainContent_CUUserProfessionalProfile1_UserProfessionalProfile1_ddlFirmType")
	private WebElement ddlFirmType;

	@FindBy(how = How.ID, using = "MainContent_CUUserProfessionalProfile1_UserProfessionalProfile1_ddlIndustryRole")
	private WebElement ddlIndustryRole;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'cbTypeOfProjects')]")
	private List<WebElement> projectTypesChk;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'cbLicensedRegistered')]")
	private List<WebElement> licenseRegisteredChk;

	@FindBy(how = How.ID, using = "MainContent_CUUserProfessionalProfile1_ibtStep3_Next")
	private WebElement step3NextBtn;

	@FindBy(how = How.ID, using = "MainContent_CUPageUserConfirmation1_ibtFinish")
	private WebElement finishBtn;
	
	@FindBy(how = How.ID, using = "MainContent_CUWelcomeUserControl1_ibtDoneWithRegistration")
	private WebElement getStartedBtn;
	
	public SSOCreateUserPage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the SSO Create User Page");
		PageFactory.initElements(driver, this);

	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String[] populateSSOUser() {
		extentTest.log(Status.INFO, "Populating the SSO Create User Page");
		
		// Step 1
		String[] credentials = new String[20];
		ssoUserFields ssoUserFieldsPage = new ssoUserFields();
		firstNameTxt.sendKeys(ssoUserFieldsPage.getFirstName());
		String userName = ssoUserFieldsPage.getUserName1() + "DoNotAcceptTermsConditions";
		
		lastNameTxt.sendKeys(userName);
		companyNameTxt.sendKeys(ssoUserFieldsPage.getCompanyName());
		addressLine1Txt.sendKeys(ssoUserFieldsPage.getAddress1());
		SeleniumUtils.selectByVisibleText(ddlCountry, ssoUserFieldsPage.getCountryName());
		cityTxt.sendKeys(ssoUserFieldsPage.getCityName());
		zipTxt.sendKeys(ssoUserFieldsPage.getZipCode());
		phNoTxt.sendKeys(ssoUserFieldsPage.getPhNo());
		step1NextBtn.click();

		// Step 2
		
		userName1Txt.sendKeys(userName);
		userName2Txt.sendKeys(userName);
		password1Txt.sendKeys(ssoUserFieldsPage.getPassword1());
		password2Txt.sendKeys(ssoUserFieldsPage.getPassword1());
		secretQuestionAnswerTxt.sendKeys(ssoUserFieldsPage.getSecretQuestionAnswer());
		step2NextBtn.click();

		// Step 3
		SeleniumUtils.selectByVisibleText(ddlFirmType, ssoUserFieldsPage.getFirmType());
		SeleniumUtils.selectByVisibleText(ddlIndustryRole, ssoUserFieldsPage.getIndustryRole());
		checkProjectTypes(0);
		checkLicenseRegister(0);
		step3NextBtn.click();

		// Step 4
		finishBtn.click();
		getStartedBtn.click();
		
		credentials[0] = userName;
		credentials[1] = ssoUserFieldsPage.getPassword1();
		return credentials;

	}

	public void checkProjectTypes(Integer index) {
		if (projectTypesChk.size() > 0) {
			projectTypesChk.get(index).click();
		} else {
			throw new CoreFrameworkException("Project Types list is empty");
		}
	}

	public void checkLicenseRegister(Integer index) {
		if (licenseRegisteredChk.size() > 0) {
			licenseRegisteredChk.get(index).click();
		} else {
			throw new CoreFrameworkException("License Registered list is empty");
		}
	}

}
