package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class MyRegistrationInfoPage extends BaseTest {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "MainContent_CntrUUPageUserConfirmation1_ibtFinish")
	private WebElement btnFinish;

	@FindBy(how = How.ID, using = "MainContent_CntrUUPageUserConfirmation1_UserConfirmation1_lbPersonalInformation")
	private WebElement btnEditPersonalInfo;

	@FindBy(how = How.ID, using = "MainContent_CntrUUUserInformation1_UserInformation1_txtCompanyName")
	private WebElement txtCompanyName;
	
	@FindBy(how = How.ID, using = "MainContent_CntrUUUserInformation1_ibtUserInformation_Update")
	private WebElement btnUpdate;
	
	@FindBy(how = How.ID, using = "MainContent_CntrUUPageUserConfirmation1_UserConfirmation1_lbPersonalInformation")
	private WebElement myDownloadBackgroundPage;
	
	public MyRegistrationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Account - My Registration Info Page");
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public boolean isFinishButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if the finish button is displayed in the My Registration Page");
		SeleniumUtils.scrollToView(driver, btnFinish);
		return btnFinish.isDisplayed();

	}

	public boolean isEditPersonalInfoDisplayed() {
		extentTest.log(Status.INFO,
				"Verify if the Edit button for Personal Information is displayed in the My Registration Page");
		SeleniumUtils.scrollToView(driver, btnEditPersonalInfo);
		return btnEditPersonalInfo.isDisplayed();

	}

	public void clickEditPersonalInfo() {
		extentTest.log(Status.INFO, "Clicking on the edit personal info");
		btnEditPersonalInfo.click();
		
	}
	
	public void clickUpdatePersonalInfo() {
		extentTest.log(Status.INFO, "Clicking on the update button for edit personal info");
		btnUpdate.click();
	}

	public void switchToPreviousTab() {
		CommonUtils.switchToHomeTab(driver);
	}

	public void enterCompanyName(String name) {
		extentTest.log(Status.INFO, "Edit the company name");
		txtCompanyName.clear();
		txtCompanyName.sendKeys(name);
	}

	public boolean checkPageBackgroud(){
		String expectedBackgroudValue = "rgba(0, 102, 204, 1)";
		 return  myDownloadBackgroundPage.getCssValue("color").trim().contains(expectedBackgroudValue);
	}
}
