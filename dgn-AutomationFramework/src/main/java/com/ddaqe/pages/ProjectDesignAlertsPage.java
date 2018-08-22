package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class ProjectDesignAlertsPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_projectAlert_divDesignAlertDisclaimer']/div[@class='importantProjectUpdates']")
	private WebElement designAlertsLegalDisclaimerHeader;

	@FindBy(how = How.XPATH, using = ".//*[@id='tblDesignAlert']/tbody/tr[3]/td[1]")
	private WebElement productAlternateGridColor;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_projectAlert_divDesignAlertDisclaimer']//div[@class='DesignAlertDisclaimer']")
	private WebElement designAlertsLegalDisclaimerContents;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvProjectProductDetails']//h2")
	private WebElement designAlertsEmptyGrid;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement actionsDropdown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkPrintDesignAlertDetails")
	private WebElement printProductListActionMenu;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_ucTopTabMenu_lnkProjects']")
	private WebElement ProjectTab;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_contentType']/div[1]/span[2]")
	private WebElement FineInArrorClick;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_DesignAlerts")
	private WebElement DesignAlertCheckBox;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_keywordSearch_saveSearchProj']")
	private WebElement SaveSearchButton;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesSavedSearchDD")
	private WebElement mySearchesSavedSearch;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_ucTopTabMenu_lnkMarketing']")
	private WebElement SweetsActivity;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_lnkPrintProjectDetails")
	private WebElement printProjectDetailsActionMenu;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkDesignAlertsSpan")
	private WebElement lnkDesignAlerts;

	@FindBy(how = How.XPATH, using = "//div/span[text()='Door' or text()='door']")
	private List<WebElement> searchedWord;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_lnkDesignAlerts")
	private WebElement designAlertTabOnProjectDetailPage;

	@FindBy(how = How.XPATH, using = ".//*[text()[contains(.,'Category')]]")
	private WebElement productGridHeaderBGColor;

	@FindBy(how = How.XPATH, using = ".//*[text()[contains(.,'Category')]]")
	private WebElement productGridBorderColor;

	@FindBy(how = How.XPATH, using = "//*[@id='tblDesignAlert']//a[@href]")
	private List<WebElement> manufacturerLinkProductGrid;

	@FindBy(how = How.XPATH, using = "//a[text()[contains(.,'Delray Lighting')]]")
	private WebElement designAlertProductLink;

	public ProjectDesignAlertsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Design Alerts Page");
	}

	public boolean isDesignAlertsLegalDisclaimerHeaderDisplayed() {
		extentTest.log(Status.INFO, "Failed to display the design alerts legal disclaimer header");
		return designAlertsLegalDisclaimerHeader.isDisplayed();
	}

	public String getDesignAlertsLegalDisclaimerText() {
		extentTest.log(Status.INFO, "Failed to get design alerts legal disclaimer contents");
		return designAlertsLegalDisclaimerContents.getText();
	}

	public boolean isDesignAlertsLegalDisclaimerContentsDisplayed() {
		extentTest.log(Status.INFO, "Failed to display the design alerts legal disclaimer contents");
		return designAlertsLegalDisclaimerContents.isDisplayed();
	}

	public boolean isDesignAlertsEmptyGrid() {
		extentTest.log(Status.INFO, "Failed to display the message when the grid is empty");
		return designAlertsEmptyGrid.isDisplayed();
	}

	// Check design alert product alternate grid color as grey
	public boolean checkForDesignAlertProductAleternateGridColor() {
		extentTest.log(Status.INFO, "Checking design laert product gird border color as #CED2D6");
		String Color = productAlternateGridColor.getCssValue("background-color");
		if (Color.equals("rgba(0, 0, 0, 0)")) {
			return true;
		} else {
			return false;
		}
	}

	// Click on Print Product List action menu and return object of Print
	// Project detail page
	public PrintProjectDetailsPage clickOnPrintProductListActionMenu() {

		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProductListActionMenu));
		extentTest.log(Status.INFO, "Click on Print Product List action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(printProductListActionMenu, driver);
		printProductListActionMenu.click();
		return new PrintProjectDetailsPage(driver);
	}

	public void SweetsActivityBoard() {

	}

	// Check if DesignAlerts link is highlighted
	public boolean isDesignAlertsLinkHighlighted() {
		extentTest.log(Status.INFO, "Check if DesignAlerts link is highlighted");
		return lnkDesignAlerts.getCssValue("background-color").equals("rgba(255, 255, 87, 1)");
	}

	// Check if searched keyword present on DesignAlerts page
	public boolean isKeywordPresentOnDesignAlertsPage(String searchText) {
		extentTest.log(Status.INFO, "Check if searched keyword present on DesignAlerts page");
		return driver.getPageSource().contains(searchText);

	}

	// Check if searched keyword highlighted on DesignAlerts page
	public boolean isKeywordHighlightedOnDesignAlertsPage() {
		extentTest.log(Status.INFO, "Check if searched keyword is highlighted on DesignAlerts page");
		return searchedWord.get(0).getCssValue("background-color").equals("rgba(255, 255, 0, 1)");
	}

	// Click on Print Project Details action menu and return object of Print
	// Project detail page
	public PrintProjectDetailsPage clickOnPrintProjectDetailsActionMenu() {

		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectDetailsActionMenu));
		extentTest.log(Status.INFO,
				"Click on Print Project Details option in action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(printProjectDetailsActionMenu, driver);
		printProjectDetailsActionMenu.click();
		return new PrintProjectDetailsPage(driver);
	}

	// Verify presence of Print Product List option in action menu
	public boolean VerifyPrintProductListOptionInActionMenu() {

		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProductListActionMenu));
		extentTest.log(Status.INFO, "Click on Print Product List action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(printProductListActionMenu, driver);
		return printProductListActionMenu.isDisplayed();
	}

	// Verify presence of Print Project Details option in action menu
	public boolean VerifyPrintProjectDetailsOptionInActionMenu() {

		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectDetailsActionMenu));
		extentTest.log(Status.INFO, "Click on Print Product List action menu and go to Print Project detail page.");
		SeleniumUtils.isVisible(printProjectDetailsActionMenu, driver);
		return printProjectDetailsActionMenu.isDisplayed();
	}

	// Check design alert product grid header color
	public boolean checkForDesignAlertProductGridColor() {
		extentTest.log(Status.INFO, "Checking design laert product gird header color as #CED2D6");
		String Color = productGridHeaderBGColor.getCssValue("background-color");
		if (Color.equals("rgba(206, 210, 214, 1)")) {
			return true;
		} else {
			return false;
		}
	}

	// Check design alert product grid Border color as grey
	public boolean checkForDesignAlertProductGridBorderColor() {
		extentTest.log(Status.INFO, "Checking design laert product gird border color as #a7aaaf");
		String Color = productGridBorderColor.getCssValue("border");
		if (Color.equals("1px solid rgb(167, 170, 175)")) {
			return true;
		} else {
			return false;
		}
	}

	// Click on design alert product link
	public void clickOnDesignAlertProductLink() {
		extentTest.log(Status.INFO, "Click on manufacturer product link");
		designAlertProductLink.click();
		CommonUtils.switchToNewTab(driver);

	}

	// Click on design alerts tab
	public void clickOnDesignAlertsTabPRojectReportPage() {
		extentTest.log(Status.INFO, "Click on Design alerts tab");
		designAlertTabOnProjectDetailPage.click();

	}

	public String getNewTabUrl() {
		extentTest.log(Status.INFO, "Get new window URL");
		String tabUrl = driver.getCurrentUrl();
		return tabUrl;
	}
}
