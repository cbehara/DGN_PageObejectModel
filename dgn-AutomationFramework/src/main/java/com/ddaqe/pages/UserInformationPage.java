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
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class UserInformationPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_TextBoxUserName")
	private WebElement ssoUsernameTxtField;
	
	@FindBy(how = How.XPATH, using = "//*[@class='mainboldHeader']")
	private WebElement pageHeader;
	
	@FindBy(how = How.CSS, using = ".BorderBottom.saved_search_name")
	private List<WebElement> savedSearchNameList;

	public UserInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public boolean isHeaderPresent(){
		extentTest.log(Status.INFO, "Check 'User Information' is present.");
		return SeleniumUtils.isVisible(pageHeader, driver);
	}
	
	public String getSSOUserNameTxt(){
		extentTest.log(Status.INFO, "Get the entered SSO Username text.");
		return ssoUsernameTxtField.getAttribute("value");
	}
	
	public List<String> getSavedSearchNameList(){
		extentTest.log(Status.INFO, "Get Saved Search name list.");
		return CommonUtils.getListFromWebElements(savedSearchNameList);
	}
}
