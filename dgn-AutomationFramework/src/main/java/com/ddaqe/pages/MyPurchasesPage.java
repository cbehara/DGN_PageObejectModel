package com.ddaqe.pages;

import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class MyPurchasesPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//table[@class='batchDownloadDetailsContainer']//tr[2]//td[1]//span")
	private WebElement orderDate;
	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pagerTop$lblTopSearchResults")
	private WebElement resultDropDown;

	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pagerBottom$lblBottomSearchResults")
	private WebElement resultDropDownBottom;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptPurchaseHistory_ctl01_lnk")
	private WebElement orderNoLink;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix batchDownloadList']")
	private WebElement myDownloadBackgroundPage;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_divError span")
	private List<WebElement> errorMessage;

	public MyPurchasesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();

	}

	// Append Date/TimeStamp in order to have a unique name
	public boolean appendDateTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(orderDate.getText().trim());
		} catch (Exception pe) {
			return false;
		}
		return true;
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public boolean isResultDropDowndisplayed() {

		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed ");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(resultDropDown));
		return resultDropDown.isDisplayed();

	}

	public boolean isResultPerPageCentered() {
		if (resultDropDown.findElement(By.xpath("./ancestor::td")).getAttribute("align").equals("center"))
			return true;
		else
			return false;
	}

	public boolean isResultDropDownBottomdisplayed() {
		extentTest.log(Status.INFO, "Verify if Result Dropdown menu is displayed at the bottom");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(resultDropDownBottom));
		return resultDropDownBottom.isDisplayed();
	}

	public boolean isResultPerPageBottomCentered() {
		if (resultDropDownBottom.findElement(By.xpath("./ancestor::td")).getAttribute("align").equals("center"))
			return true;
		else
			return false;
	}

	public EcommercePage clickOnFirstOrderNoLink() {
		extentTest.log(Status.INFO, "Clicking on the first order receipt no link");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(orderNoLink));
		orderNoLink.click();
		return new EcommercePage(driver);
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}

	public String getErrorMessage() {
		extentTest.log(Status.INFO, "get error message");
		String errorMessageText = "";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(errorMessage.get(0)));
		if (!errorMessage.isEmpty()) {
			errorMessageText = errorMessage.get(0).getText();
		}
		return errorMessageText;
	}
}
