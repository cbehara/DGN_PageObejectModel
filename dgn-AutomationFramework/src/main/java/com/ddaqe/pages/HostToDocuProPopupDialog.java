package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class HostToDocuProPopupDialog {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.CSS, using = "#hostToDocuProPopup .header")
	private WebElement hostToDocuProPopupHeader;

	public HostToDocuProPopupDialog(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		PageFactory.initElements(driver, this);
	}

	public boolean verifyHostToDocuProPopupHeader(final String headerToBeVerified) {
		extentTest.log(Status.INFO, "Verify header for 'Host to Docu Pro' dialog.");
		return hostToDocuProPopupHeader.getText().trim().equals(headerToBeVerified);
	}
}
