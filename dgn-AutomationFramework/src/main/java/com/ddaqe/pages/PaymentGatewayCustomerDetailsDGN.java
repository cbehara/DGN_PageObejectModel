package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.SeleniumUtils;

public class PaymentGatewayCustomerDetailsDGN {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "txtBillingAddress1")
	private WebElement txtBillingAddress1;

	@FindBy(how = How.ID, using = "txtCity")
	private WebElement txtCity;

	@FindBy(how = How.ID, using = "ddlState")
	private WebElement ddlState;

	@FindBy(how = How.ID, using = "txtZip")
	private WebElement txtZip;

	@FindBy(how = How.ID, using = "ddlCountry")
	private WebElement ddlCountry;

	@FindBy(how = How.ID, using = "txtCCHolderName")
	private WebElement txtCCHolderName;

	@FindBy(how = How.ID, using = "ddlCreditCardType")
	private WebElement ddlCreditCardType;

	@FindBy(how = How.ID, using = "txtCreditCardNUmber")
	private WebElement txtCreditCardNUmber;

	@FindBy(how = How.ID, using = "ddlExpireMonth")
	private WebElement ddlExpireMonth;

	@FindBy(how = How.ID, using = "ddlExpireYear")
	private WebElement ddlExpireYear;

	@FindBy(how = How.ID, using = "btnPlaceOrder")
	private WebElement btnPlaceOrder;

	@FindBy(how = How.ID, using = "btnCancel")
	private WebElement btnCancel;

	@FindBy(how = How.ID, using = "chkTermsAgree")
	private WebElement chkTermsAgree;

	public PaymentGatewayCustomerDetailsDGN(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Ecommerce Page");
		PageFactory.initElements(driver, this);
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public void populatePaymentDetails() {
		extentTest.log(Status.INFO, "Populating the Payment details");

		CustomerPaymentDetails customerPaymentDetails = new CustomerPaymentDetails();
		txtBillingAddress1.sendKeys(customerPaymentDetails.getBtillingAddress1());
		txtCity.sendKeys(customerPaymentDetails.getCity());
		SeleniumUtils.selectByVisibleText(ddlState, customerPaymentDetails.getState());
		txtZip.sendKeys(customerPaymentDetails.getZipcode());
		SeleniumUtils.selectByVisibleText(ddlCountry, customerPaymentDetails.getCountry());
		txtCCHolderName.sendKeys(customerPaymentDetails.getCardholdersName());
		SeleniumUtils.selectByVisibleText(ddlCreditCardType, customerPaymentDetails.getCreditcardtype());
		txtCreditCardNUmber.sendKeys(customerPaymentDetails.getCreditcardnumber());
		SeleniumUtils.selectByVisibleText(ddlExpireMonth, customerPaymentDetails.getExpiryMonth());
		SeleniumUtils.selectByVisibleText(ddlExpireYear, customerPaymentDetails.getExpiryYear());
		chkTermsAgree.click();

	}

	public void clickPlaceOrder() {
		extentTest.log(Status.INFO, "Click on the Place Order");
		btnPlaceOrder.click();
	}

	public void clickCancel() {
		extentTest.log(Status.INFO, "Click on the Cancel");
		btnCancel.click();
	}
}
