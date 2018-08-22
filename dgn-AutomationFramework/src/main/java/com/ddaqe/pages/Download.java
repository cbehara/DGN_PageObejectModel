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

public class Download {
	private WebDriver driver;
	private ExtentTest extentTest;
	
	@FindBy(how = How.XPATH, using = "//img[@src='Images/logo.png']")
	private WebElement dodgeLogoImage;
	
	public Download(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Help Page");
	}
	
	public boolean checkDodgeLogo(){
		extentTest.log(Status.INFO, "Check the download tab is not displayed as white window. Checking the Dodge Logo.");
		return CommonUtils.checkElementExist(dodgeLogoImage, driver);
	}
	
	public void returnToDefaultContentPage(){
		CommonUtils.switchToHomeTab(driver);
	}

}
