package com.ddaqe.pages;

import java.util.List;
import java.util.NoSuchElementException;

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

public class TechSupportPage {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "MainContent_txtEmail")
	private WebElement emailAddressTxtField;

	@FindBy(how = How.ID, using = "MainContent_txtPassword")
	private WebElement passwordTxtField;

	@FindBy(how = How.ID, using = "MainContent_btnLogin")
	private WebElement signInBtn;
	
	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisibleCheck;

	@FindBy(how = How.XPATH, using = ".//*[@id='_ctl0_NavigationBarPlaceHolder_lnkTechsupport']")
	private WebElement clickOnNetworkTechSupport;

	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_DropDownListSearchBy")
	private WebElement ssoUserID;

	@FindBy(how = How.ID, using = "ctl00_NavigationBarPlaceHolder_HyperLink1")
	private WebElement clickOnSetUpCustomer;

	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_TextBoxLicense")
	private WebElement licenseIDTxtField;

	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_TextBoxUserId")
	private WebElement ssoUserIDTxtField;

	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_ButtonSubmit")
	private WebElement clickOnSubmitQuery;

	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_Label1")
	private WebElement findSubscriberLabel;

	@FindBy(how = How.ID, using = "savedSearchCriteriaPopup")
	private WebElement savedSearchCriteriaPopupWindow;

	@FindBy(how = How.XPATH, using = "//*[@class='BorderBottom saved_search_name' and text()='New PDS search']")
	private WebElement clickOnNewPDSSearch;

	@FindBy(how = How.XPATH, using = "//*[@class='BorderBottom saved_search_name' and text()='Default Saved Search']")
	private WebElement DefaultSavedSearch;

	@FindBy(how = How.XPATH, using = "//*[@class='BorderBottom saved_search_name' and text()='Search Without MFR']")
	private WebElement SearchWithoutMFR;

	@FindBy(how = How.XPATH, using = "//*[@class='BorderBottom saved_search_name' and text()='Multiple PDS Search Criteria']")
	private WebElement MultiplePDSSearch;

	@FindBy(how = How.XPATH, using = "//*[@class='BorderBottom saved_search_name' and text()='Multiple_Manufacturers']")
	private WebElement Multiple_Manufacturers_Search;

	@FindBy(how = How.XPATH, using = "//*[@class='saved-search-name-text' and text()='Default Saved Search']")
	private WebElement SavedSearchDefaultSavedSearch;

	@FindBy(how = How.ID, using = "ctl02_header")
	private WebElement PDS_header_in_SavedSearch;
	
	@FindBy(how = How.ID, using = "_ctl0_bodyPlaceHolder_TextBoxHorizonProductAbbrv")
	private WebElement horizonProdAbbrv;

	@FindBy(how = How.ID, using = "ctl00_header")
	private WebElement Published_Since;

	@FindBy(how = How.ID, using = "ctl01_header")
	private WebElement FindIn;

	@FindBy(how = How.ID, using = "ctl03_header")
	private WebElement Geography;

	@FindBy(how = How.ID, using = "ctl02_headerText")
	private WebElement PDSname;

	@FindBy(how = How.ID, using = "ctl03_headerText")
	private WebElement MultipleManufacturers;

	@FindBy(how = How.ID, using = "savedSearchPopupClose")
	private WebElement closeButton;
	
	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	// @FindBy(how = How.ID, using = "ctl02_headerText")
	// private WebElement Multiple_PDS_Names;

	// @FindBy(how = How.XPATH, using =
	// "//*[@class='filterText']/span(@id,'ctl02_headerText_extraOption'")
	// private WebElement Multiple_PDS_Names;

	@FindBy(how = How.ID, using = "ctl03_header")
	private WebElement ManufacturersLabel;

	@FindBy(how = How.ID, using = "ctl03_headerText")
	private WebElement Manufacturers_text;

	@FindBy(how = How.XPATH, using = "//*[@class='BorderBottom saved_search_name']")
	private List<WebElement> savedSearchList;

	@FindBy(how = How.ID, using = "")
	private WebElement BOD_Manufacturers_Search;

	public TechSupportPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
	}

	public HomePage loginAs(String emailAddress, String password, String URL) {
		extentTest.log(Status.INFO, "Enter URL: " + URL);
		driver.get(URL);
		extentTest.log(Status.INFO, "Enter Email: " + emailAddress);
		emailAddressTxtField.clear();
		emailAddressTxtField.sendKeys(emailAddress);
		extentTest.log(Status.INFO, "Enter Password: " + password);
		passwordTxtField.clear();
		passwordTxtField.sendKeys(password);
		extentTest.log(Status.INFO, "Click on Login button.");

		signInBtn.click();

		return new HomePage(this.driver);

	}

	public void clickOnNetTechSupport() {
		extentTest.log(Status.INFO, "click on Network tech support link");
		clickOnNetworkTechSupport.click();
	}

	public boolean ssoUserIDButtondisplay() {
		extentTest.log(Status.INFO, "check sso user Id dropdown is displayed");
		return ssoUserID.isDisplayed();
	}

	public void clickOnSetUPCustomer() {
		extentTest.log(Status.INFO, "click on set UP customer Link");
		clickOnSetUpCustomer.click();
	}

	public void enterSSOUserIDTxt(String ssoUserId) {
		SeleniumUtils.isClickable(ssoUserIDTxtField, driver);
		extentTest.log(Status.INFO, "Entered SSO ID: " + ssoUserId);
		ssoUserIDTxtField.clear();
		ssoUserIDTxtField.sendKeys(ssoUserId);
	}

	public void enterLicenseIDTxt(String licenseID) {
		SeleniumUtils.isClickable(licenseIDTxtField, driver);
		extentTest.log(Status.INFO, "Entered License ID: " + licenseID);
		licenseIDTxtField.clear();
		licenseIDTxtField.sendKeys(licenseID);
	}

	public UserSelectionPage clickOnSubmitQueryBtn() {
		extentTest.log(Status.INFO, "Enter Search Text");
		clickOnSubmitQuery.click();
		return new UserSelectionPage(driver);
	}

	public UserInformationPage clickOnSubmitQueryBtnNavToUserInfoPage() {
		extentTest.log(Status.INFO, "Click on Submit Query button and navigate to User Information page.");
		clickOnSubmitQuery.click();
		return new UserInformationPage(driver);
	}

	public boolean findSubscriberdisplay() {
		extentTest.log(Status.INFO, "check findSubscriber Id dropdown is displayed");
		return findSubscriberLabel.isDisplayed();
	}

	public void clickOnProjectSaveSearch() {
		extentTest.log(Status.INFO, "Click on project saved search which has PDS search criteria");
		clickOnNewPDSSearch.click();
	}

	public void clickOnDefaultSavedSearch() {
		extentTest.log(Status.INFO, "Click on Default Saved Search which does not have PDS search criteria");
		DefaultSavedSearch.click();
	}

	public Boolean IsSavedSearchCriteriaDispalyed() {
		extentTest.log(Status.INFO, "Check if Saved Search Criteria window is dispalyed");
		return savedSearchCriteriaPopupWindow.isDisplayed();
	}

	public void clickOnMultiplePDSSearch() {
		extentTest.log(Status.INFO, "Click on Default Saved Search which does not have PDS search criteria");
		MultiplePDSSearch.click();
	}

	public boolean IsPDS_HeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify search criteria label should be 'Project Delivery System'");
		return PDS_header_in_SavedSearch.isDisplayed();
	}

	public Boolean IsPublishedSinceLabelsHighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Save Search Label is highlighted in bold");
		return CommonUtils.isFontBold(driver, Published_Since);
	}

	public Boolean IsFindInLabelHighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Find In Label is highlighted in bold");
		return CommonUtils.isFontBold(driver, FindIn);
	}

	public Boolean IsGeographyLabelHighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Geography Label is highlighted in bold");
		return CommonUtils.isFontBold(driver, Geography);
	}

	public boolean IsDefaultSavedSearchHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify SavedSearch displays Default Saved Search");
		return SavedSearchDefaultSavedSearch.isDisplayed();
	}

	public boolean IsPDSSearchHeaderNotDisplayed() {
		extentTest.log(Status.INFO, "Check PDS search header is not present.");
		return SeleniumUtils.isVisible(PDS_header_in_SavedSearch, driver);
	}

	public boolean IsPDSDescriptionDisplayed() {
		extentTest.log(Status.INFO, "Verify PDS description");
		return PDSname.isDisplayed();
	}

	public void ClickOnSearchWithoutMFRSearch() {
		extentTest.log(Status.INFO,
				"Click on 'Search Without MFR' search criteria which does not have manufacturer search criteria");
		SearchWithoutMFR.click();
	}

	public boolean isManufactuereHeaderPresent() {
		extentTest.log(Status.INFO, "Check 'Manufacturer header' is not present.");
		return "Manufacturers:".equalsIgnoreCase(ManufacturersLabel.getText());
	}

	public boolean isManufactuereTextPresent() {
		extentTest.log(Status.INFO, "Check 'Manufacturer text' is present.");
		return SeleniumUtils.isVisible(Manufacturers_text, driver);
	}

	public String IsMultiplePDSSearchDisplayed() {
		extentTest.log(Status.INFO, "Verify more than one PDS search criteria displayed'");
		return PDSname.getText();
	}

	public void clickOnMultiple_Manufacturers_Search() {
		extentTest.log(Status.INFO, "Click on Default Saved Search which does not have PDS search criteria");
		Multiple_Manufacturers_Search.click();
	}

	public String IsMultipleManufacturerSearchDisplayed() {
		extentTest.log(Status.INFO, "Verify more than one Manufacturer search criteria displayed'");
		return MultipleManufacturers.getText();
	}

	public void clickOnPManufacturersSearch(String savedSearchToBeClicked) {
		extentTest.log(Status.INFO,
				"Click on  project saved search which has Manufacturer search criteria as 'p-manufacturer'");
		for (WebElement savedSearch : savedSearchList) {
			if (savedSearchToBeClicked.equalsIgnoreCase(savedSearch.getText())) {
				savedSearch.click();
			}
		}
	}

	public void clickOnSpecifiedManufacturersSearch(String savedSearchToBeClicked) {
		extentTest.log(Status.INFO,
				"Click on  project saved search which has Manufacturer search criteria as 'p-manufacturer'");
		for (WebElement savedSearch : savedSearchList) {
			if (savedSearchToBeClicked.equalsIgnoreCase(savedSearch.getText())) {
				savedSearch.click();
			}
		}
	}

	public void clickOnBODandspecifiedManufacturersSearch(String savedSearchToBeClicked) {
		extentTest.log(Status.INFO,
				"Click on  project saved search which has Manufacturer search criteria as 'BOD & Specified manufacturer'");
		for (WebElement savedSearch : savedSearchList) {
			if (savedSearchToBeClicked.equalsIgnoreCase(savedSearch.getText())) {
				savedSearch.click();
			}
		}
	}

	public String isPManufacturerHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify Header should be 'Manufacturers' displayed");
		return ManufacturersLabel.getText();
	}

	public String isBODManufacturerHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify Header should be 'Basis of Design Manufacturers:' displayed");
		return ManufacturersLabel.getText();
	}

	public void clickOnClose() {
		extentTest.log(Status.INFO, "Click on close button to close saved search criteria");
		closeButton.click();
	}

	public void clickOnBODManufacturersSearch(String savedSearchToBeClicked) {
		extentTest.log(Status.INFO,
				"Click on  project saved search which has Manufacturer search criteria as 'p-manufacturer'");
		for (WebElement savedSearch : savedSearchList) {
			if (savedSearchToBeClicked.equalsIgnoreCase(savedSearch.getText())) {
				savedSearch.click();
			}
		}
	}
	
	/**
	 * This method will check for Value attribute in Horizon Product Abbrv. text field.
	 * 
	 * @return value attribute.
	 */
	public String getValueAttributeFromHorizonAbbrvTextField(){
		extentTest.log(Status.INFO, "Get value attribute from Horizon Product Abbrv.");
		return horizonProdAbbrv.getAttribute("value");
	}
	
	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

}
