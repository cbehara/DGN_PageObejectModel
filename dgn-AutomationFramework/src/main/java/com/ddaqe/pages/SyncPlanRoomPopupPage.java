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

public class SyncPlanRoomPopupPage {
	private WebDriver driver;
	private ExtentTest extentTest;
	
	@FindBy(how = How.XPATH, using = ".//div[@id='exceedadminProject']//div[1]")
	private WebElement planroomMessageLabel;
	
	@FindBy(how = How.XPATH, using = ".//div[@id='exceedadminProject']//div[2]")
	private WebElement planroomUserMessageLabel;
	
	@FindBy(how = How.XPATH, using = "//a[@class='planroomOk']")
	private WebElement planroomOKBtn;
	
	@FindBy(how = How.XPATH, using = "//span[@class='planRoomOkText']")
	private WebElement planroomOKBtnWithSpan;
	
	@FindBy(how = How.ID, using = "//div[@id='PrpSyncProjectExist' and not( contains(@style,'display: none'))]")
	private WebElement PrpSyncProjectExistDialog;
	
	@FindBy(how = How.XPATH, using = "//div[@id='PrpSyncProjectExist']//a[@class='planroomProOk']")
	private WebElement prpSyncExistDialogOKBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@id='PrpSyncProjectExist']//div[1]")
	private WebElement prpSyncProjectExistMsg;
	
	@FindBy(how = How.XPATH, using = "//*[@id='exceeduserPrpProject']//p")
	private WebElement prpSyncDialog;
	
	
	@FindBy(how = How.XPATH, using = "//div[@id='PrpSyncOk']//a[@class='planroomProOk']")
	private WebElement prpSyncDialogOKBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='exceeduserPrpProject']//*[@class='planroomProOk']")
	private WebElement prpSyncDialogOKBtnExceedUser;
	
	
	public SyncPlanRoomPopupPage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the SSO Create User Page");
		PageFactory.initElements(driver, this);
	}
	
	//Get plan room message label.
	public String getPlanroomDialogMessage(){
		extentTest.log(Status.INFO, "Get plan room message label.");
		return planroomMessageLabel.getText().trim();
	}
	
	//Get plan room user name message label
	public String getPlanroomUserNameDialogMessage(){
		extentTest.log(Status.INFO, "Get plan room user name message label.");
		return planroomUserMessageLabel.getText().trim();
	}
	
	//Click on OK button
	public void clickOnOKBtn(){
		extentTest.log(Status.INFO, "Click on OK button.");
//		planroomOKBtn.click();
		planroomOKBtnWithSpan.click();
	}
	
	public void clickOnOKBtnOfSyncPRPProject(){
		prpSyncDialogOKBtn.click();
	}
	
	public void clickOnOKBtnOfSyncPRPProjectExceedUser(){
		prpSyncDialogOKBtnExceedUser.click();
	}
	
	public String getPRPSyncProjectExistMsg(){
		extentTest.log(Status.INFO, "Get the message for PRP sync project exist dialog.");
		return prpSyncProjectExistMsg.getText().trim();
	}
	
	public boolean checkPRPSyncDialog(){
		extentTest.log(Status.INFO, "Check PRP Sync project dialog.");
		return CommonUtils.checkElementExist(prpSyncDialog, driver);
	}
	
	public String getPRPSynctMsg(){
		extentTest.log(Status.INFO, "Get the message for PRP sync dialog.");
		return prpSyncDialog.getText().trim();
	}
	
	public boolean checkPRPSyncProjectExistDialog(){
		extentTest.log(Status.INFO, "Check PRP Sync project exist dialog.");
		return CommonUtils.checkElementExist(PrpSyncProjectExistDialog, driver);
	}
	
	public void clickOnOKBtnOfSyncPRPProjectExist(){
		prpSyncExistDialogOKBtn.click();
	}
}
