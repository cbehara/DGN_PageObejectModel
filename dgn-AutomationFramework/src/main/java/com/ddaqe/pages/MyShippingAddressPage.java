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
import com.ddaqe.utils.ConfigurationReader;
import com.ddaqe.utils.SeleniumUtils;

public class MyShippingAddressPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "btnAddProfile")
	private WebElement btnAddProfile;

	@FindBy(how = How.ID, using = "My-Account---My-Shipping-Addresses")
	private WebElement breadcrumbShipping;

	@FindBy(how = How.ID, using = "popupAddShippingAddress")
	private WebElement popupAddShippingAddress;

	@FindBy(how = How.ID, using = "addShippingAddressClose")
	private WebElement addShippingAddressClose;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'edit-shipping-address shippingLink')]")
	private List<WebElement> editBtnShippingAddress;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblCount")
	private WebElement lblAddressCount;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkDelete')]")
	private List<WebElement> deleteBtnShippingAddress;

	@FindBy(how = How.ID, using = "addShippingAddressSave")
	private WebElement addShippingAddressSave;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtName")
	private WebElement txtName;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtRecipientFullName")
	private WebElement txtFullName;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtRecipientPhoneNumber")
	private WebElement txtPhoneNumber;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtCompanyName'")
	private WebElement txtCompanyName;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtAddress")
	private WebElement txtAddress;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtCity")
	private WebElement txtCity;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_stateDropDownList")
	private WebElement ddlState;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtZip")
	private WebElement txtZip;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_addShippingAddress_txtExtnNumber")
	private WebElement txtExtension;

	@FindBy(how = How.XPATH, using = "//div[@id='popupAddShippingAddress']//div[contains(@class,'error-message')]")
	private WebElement errorMsg;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix batchDownloadList']")
	private WebElement myDownloadBackgroundPage;

	public MyShippingAddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public String getTitle() {
		extentTest.log(Status.INFO, "getTitle of My Shipping Address Page");
		return driver.getTitle();
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public void clickAddProfileButton() {
		extentTest.log(Status.INFO, "Clicking the Add Profile Button");
		btnAddProfile.click();
	}

	public void clickCancelButtonInAddProfilePopup() {
		extentTest.log(Status.INFO, "Clicking the cancel button in the Add Profile popup");
		addShippingAddressClose.click();
	}

	public void clickFirstEditButton() {
		extentTest.log(Status.INFO, "Clicking the first edit button");
		for (int i = 0; i < editBtnShippingAddress.size(); i++) {
			editBtnShippingAddress.get(i).click();
			break;
		}
	}

	public void clickFirstDeleteButton() {
		extentTest.log(Status.INFO, "Clicking the first delete button");
		for (int i = 0; i < deleteBtnShippingAddress.size(); i++) {
			deleteBtnShippingAddress.get(i).click();
			driver.switchTo().alert().accept();
			break;
		}
	}

	public Integer getAddressCount() {
		String lblCount = lblAddressCount.getText();
		lblCount = lblCount.replaceAll("[^0-9]", "");
		return Integer.parseInt(lblCount);
	}

	public boolean isPopUpAddShippingAddressDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Add Shipping Address' pop up displayed");
		try {
			return popupAddShippingAddress.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isAddAddressButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Add Address' button displayed");
		try {
			return btnAddProfile.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isEditAddressLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Edit Address' link is displayed");
		try {
			return editBtnShippingAddress.get(0).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isDeleteAddressLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Delete Address' link is displayed");
		try {
			return deleteBtnShippingAddress.get(0).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isBreadCrumbDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Breadcrumb is as expected");
		try {
			return breadcrumbShipping.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isNameEmpty() {
		extentTest.log(Status.INFO, "Verify if the name is empty");
		return txtName.getText().isEmpty();
	}

	public void enterName(String name) {
		extentTest.log(Status.INFO, "Enter Name");
		txtName.clear();
		txtName.sendKeys(name);
	}

	public void enterFullName(String fullName) {
		extentTest.log(Status.INFO, "Enter Full Name");
		txtFullName.clear();
		txtFullName.sendKeys(fullName);
	}

	public void enterPhoneNumber(String strPhoneNumber) {
		extentTest.log(Status.INFO, "Enter Phone Number");
		txtPhoneNumber.clear();
		txtPhoneNumber.sendKeys(strPhoneNumber);
	}

	public void enterExtension(String ext) {
		extentTest.log(Status.INFO, "Enter Extension");
		txtExtension.clear();
		txtExtension.sendKeys(ext);
	}

	public void enterAddress(String address) {
		extentTest.log(Status.INFO, "Enter Address");
		txtAddress.clear();
		txtAddress.sendKeys(address);
	}

	public void enterCity(String city) {
		extentTest.log(Status.INFO, "Enter City");
		txtCity.clear();
		txtCity.sendKeys(city);
	}

	public void enterState(String state) {
		extentTest.log(Status.INFO, "Enter State");
		SeleniumUtils.selectByVisibleText(ddlState, state);
	}

	public void enterZipCode(String zip) {
		extentTest.log(Status.INFO, "Enter ZipCode");
		txtZip.clear();
		txtZip.sendKeys(zip);
	}

	public Integer getNameLength() {
		extentTest.log(Status.INFO, "Verify the name length");
		return txtName.getText().length();
	}

	public Integer getExtensionLength() {
		extentTest.log(Status.INFO, "Verify the Extension length");
		return txtExtension.getText().length();
	}

	public Integer getAddressLength() {
		extentTest.log(Status.INFO, "Verify the Address length");
		return txtAddress.getText().length();
	}

	public Integer getCityLength() {
		extentTest.log(Status.INFO, "Verify the City length");
		return txtCity.getText().length();
	}

	public Integer getZipCodeLength() {
		extentTest.log(Status.INFO, "Verify the ZipCode length");
		return txtZip.getText().length();
	}

	public boolean isFullNameEmpty() {
		extentTest.log(Status.INFO, "Verify if the Full Name is empty");
		return txtFullName.getText().isEmpty();
	}

	public boolean isAddressEmpty() {
		extentTest.log(Status.INFO, "Verify if the Address is empty");
		return txtAddress.getText().isEmpty();
	}

	public Integer getFullNameLength() {
		extentTest.log(Status.INFO, "Verify the Full Name length");
		return txtFullName.getText().length();
	}

	public void populateShippingAddress(String zipcode) {
		extentTest.log(Status.INFO, "Populating the SSO Create User Page");
		ConfigurationReader configurationReader = ConfigurationReader.getInstance();
		txtName.clear();
		txtName.sendKeys(configurationReader.getProperty("name"));
		txtFullName.clear();
		txtFullName.sendKeys(configurationReader.getProperty("recipientFullName"));
		txtPhoneNumber.clear();
		txtPhoneNumber.sendKeys(configurationReader.getProperty("recipientPhoneNumber"));
		txtCompanyName.clear();
		txtCompanyName.sendKeys(configurationReader.getProperty("companyName"));
		txtAddress.clear();
		txtAddress.sendKeys(configurationReader.getProperty("address"));
		txtCity.clear();
		txtCity.sendKeys(configurationReader.getProperty("city"));
		SeleniumUtils.selectByVisibleText(ddlState, configurationReader.getProperty("state"));
		if (zipcode.isEmpty()) {
			txtZip.sendKeys(configurationReader.getProperty("zipcode"));
		} else {
			txtZip.sendKeys(zipcode);
		}

	}

	public void clickSaveShippingAddress() {
		extentTest.log(Status.INFO, "Clicking the save button in Add Shiping Address");
		addShippingAddressSave.click();
	}

	public String getErrorMessage() {
		extentTest.log(Status.INFO, "Verify the error message");
		return errorMsg.getText().trim();
	}

	public boolean isErrorMessageDisplayed() {
		extentTest.log(Status.INFO, "Verify if the error message is displayed");
		try {
			return errorMsg.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}
}
