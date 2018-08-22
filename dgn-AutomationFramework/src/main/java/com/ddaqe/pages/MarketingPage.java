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

public class MarketingPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	
	@FindBy(how = How.LINK_TEXT, using = "SWEETS ACTIVITY")
	private List<WebElement> SweetActivity;
	
	public MarketingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Verify the MARKETING tab is renamed to SWEETS ACTIVITY on DGN (TC24117)");
	}	
		public boolean isSpecAlertsInDashboardHeadersDisplayed(String Name) {
			extentTest.log(Status.INFO, "To verify if Sweet Activity tab is present	");
			for (WebElement webElement : SweetActivity) {
				if (webElement.getText().equalsIgnoreCase(Name))
					return true;
			}
			return false;

		}

	public String getTitle() {
		return driver.getTitle();
	}

}
