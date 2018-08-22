package com.ddaqe.pages;

import java.util.List;

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
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class HighlightKeywordsPage {

	private WebDriver driver;
	private ExtentTest extentTest;
	public String strNumber;

	@FindBy(how = How.XPATH, using = "//a[not(@class)]")
	private WebElement numberMatched;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'notMatchedDoc')]")
	private WebElement numberNotMatched;

	@FindBy(how = How.ID, using = "instanceSearchKeyword")
	private WebElement searchTextBox;

	@FindBy(how = How.ID, using = "btnSearchNow")
	private WebElement searchBtn;

	@FindBy(how = How.ID, using = "lblMatchText")
	private WebElement matchText;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblSnippet')]")
	private WebElement snippet;

	@FindBy(how = How.XPATH, using = ".//a[@id='0_lnkPageNumber']")
	private WebElement matchedPageNumber;

	@FindBy(how = How.XPATH, using = ".//*[@id='header']//div[contains(@class,'highlightheaderText')]")
	private List<WebElement> logoLabel;

	@FindBy(how = How.XPATH, using = ".//*[@id='header']//img")
	private WebElement infoIcon;

	@FindBy(how = How.ID, using = "header-left")
	private WebElement topHeader;

	@FindBy(how = How.ID, using = "lblMatchCount")
	private WebElement lblMatchCount;

	@FindBy(how = How.ID, using = "numPages")
	private WebElement numPages;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'snipetdiv')]//span//b")
	private List<WebElement> matchSnippet;

	@FindBy(how = How.XPATH, using = ".//span[@id='specnumber']")
	private WebElement textMatched;

	public HighlightKeywordsPage(WebDriver driver, String strNumber) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		this.strNumber = strNumber;
	}

	public HighlightKeywordsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public boolean IsLeftHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify if the top header is displayed");
		return topHeader.isDisplayed();
	}

	public String getLogoLabelText() {
		extentTest.log(Status.INFO, "Verify if the logo is displayed");
		return logoLabel.get(1).getText();
	}

	public String getDRText() {
		extentTest.log(Status.INFO, "Get the dr text");
		return logoLabel.get(0).getText();
	}

	public String getlblMatchCountText() {
		extentTest.log(Status.INFO, "Get the match count");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(lblMatchCount));
		return lblMatchCount.getText().trim();
	}

	public String getNumPagesText() {
		extentTest.log(Status.INFO, "Get the number of pages");
		return numPages.getText();
	}

	public boolean IsDocumentLoadedForMatched() {
		extentTest.log(Status.INFO,
				"Verify if the document loaded in the document viewer for Number which has a keyword match");
		Boolean isMatch = false;
		try {
			CommonUtils.switchToNewTab(driver);

			if (strNumber.isEmpty()) {
				return false;
			}
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(textMatched));
			isMatch = textMatched.getText().equals(strNumber);
			CommonUtils.switchToHomeTab(driver);
		} catch (Exception ex) {
		CommonUtils.switchToHomeTab(driver);
			return false;
		}
		return isMatch;
	}

	public boolean IsCountMatched() {
		extentTest.log(Status.INFO, "Verify if the count matches the number of matches");
		try {
			return numberMatched.getText().equals(strNumber);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean IsDocumentLoadedForNonMatched() {
		extentTest.log(Status.INFO,
				"Verify if the document is directly downloaded for Number which does not have a keyword match");
		return numberNotMatched.getText().equals(strNumber);
	}

	public boolean IsSearchTextBoxPresent() {
		extentTest.log(Status.INFO, "Verify if searchbox is present on page");
		return searchTextBox.isDisplayed();
	}

	public boolean IsMatchTextPresent() {
		extentTest.log(Status.INFO, "Verify if match text is present on page");
		return matchText.isDisplayed();
	}

	public String getsearchTextBoxText() {
		extentTest.log(Status.INFO, "Get search textbox tex");
		SeleniumUtils.isVisible(searchTextBox, driver);
		return searchTextBox.getAttribute("value");
	}

	public boolean IsSnippetDisplayed() {
		extentTest.log(Status.INFO, "Verify if snippet is present on page");
		return snippet.isDisplayed();
	}

	public boolean IsMatchedPageNumberDsplayed() {
		extentTest.log(Status.INFO, "Verify if matched page number is present on page");
		return matchedPageNumber.isDisplayed();
	}

	public void entersearchText(String text) {
		extentTest.log(Status.INFO, "Verify if matched page number is present on page");
		searchTextBox.clear();
		searchTextBox.sendKeys(text);
	}

	public void clickSearchBtn() {
		extentTest.log(Status.INFO, "Click search button");
		searchBtn.click();
	}

	public String clickinfoIconandGetURL() {

		extentTest.log(Status.INFO, "click on info Icon and get url");
		infoIcon.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		return driver.getCurrentUrl();

	}

	public int getMatchSnippetCount() {
		extentTest.log(Status.INFO, "Get the match count in the snippet");
		return matchSnippet.size();
	}

	public String getPageTitle(String title) {
		driver.switchTo().window(title);
		return driver.getTitle();
	}

}
