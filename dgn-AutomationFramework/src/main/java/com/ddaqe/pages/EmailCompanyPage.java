package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;

public class EmailCompanyPage {
	private WebDriver driver;
	private ExtentTest extentTest;
	
	@FindBy(how = How.ID, using = "emailTemplateDialog")
	private WebElement emailCompanyPopupDialog;
	
	@FindBy(how = How.ID, using = "emailAddressesSection")
	private WebElement emailAddressTxtBx;
	
	@FindBy(how = How.ID, using = "emailPopupSubmit")
	private WebElement sendBtn;
	
	@FindBy(how = How.ID, using = "emailPopupClose")
	private WebElement cancelBtn;
	
	public EmailCompanyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}
	
	public void enterEmailAddress(String emailAddress){
		extentTest.log(Status.INFO, "Enter the email address to email company.");
		emailAddressTxtBx.clear();
		emailAddressTxtBx.sendKeys(emailAddress);
	}
	
	public void clickOnSendEmailButton(){
		extentTest.log(Status.INFO, "Click on the send email button");
		if(sendBtn.isEnabled()){
			sendBtn.click();
		}else{
			extentTest.log(Status.INFO, "Send button is disable.");
		}
	}
	
	// Check Email Companies Popup Dialog Displayed.
	public boolean isEmailCompaniesPopupDialogDisplayed() {
		extentTest.log(Status.INFO, "Check Email Companies Popup Dialog Displayed.");
		return CommonUtils.checkElementExist(emailCompanyPopupDialog, driver);
	}
	
	public void clickOnCancelButton(){
		extentTest.log(Status.INFO, "Click on the Cancel button");
		if(cancelBtn.isEnabled()){
			cancelBtn.click();
		}else{
			extentTest.log(Status.INFO, "Cancel button is disable.");
		}
	}
}
