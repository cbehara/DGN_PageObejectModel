package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;

public class HelpPage {
	private WebDriver driver;
	private ExtentTest extentTest;
	
	public HelpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Help Page");
	}
	
	public boolean checkHelpPageDisplayed(String helpULRContain){
		CommonUtils.switchToNewTab(driver);
		return driver.getCurrentUrl().toUpperCase().contains(helpULRContain.toUpperCase());
	}
	
	public void returnToDefaultContentPage(){
		CommonUtils.switchToHomeTab(driver);
	}

}
