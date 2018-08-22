package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class MyAccount {

	private WebDriver driver;
	private ExtentTest extentTest;

	public MyAccount(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Account Page");
		PageFactory.initElements(driver, this);

	}

	public String getTitle() {
		return driver.getTitle();
	}

	@FindBy(how = How.LINK_TEXT, using = "Users")
	private WebElement UsersLink;
	@FindBy(how = How.LINK_TEXT, using = "Reports")
	private WebElement reportsLink;
	@FindBy(how = How.LINK_TEXT, using = "My SpecAlerts")
	private WebElement MySpecAlertsNavLink;
	@FindBy(how = How.LINK_TEXT, using = "My Tracking Lists")
	private WebElement MyTrackingListsNavLink;
	@FindBy(how = How.LINK_TEXT, using = "My Saved Searches")
	private WebElement MySavedSearchesNavLink;
	@FindBy(how = How.LINK_TEXT, using = "Edit")
	private WebElement EditFirstLink;
	@FindBy(how = How.LINK_TEXT, using = "Delete")
	private WebElement DeleteFirstLink;
	@FindBy(how = How.LINK_TEXT, using = "My Shipping Addresses")
	private WebElement myShippingAddress;

	@FindBy(how = How.LINK_TEXT, using = "#ctl00_cplBody_lnkLeadProfile")
	private WebElement linkSpecAlerts;
	@FindBy(how = How.LINK_TEXT, using = "My Downloads")
	private WebElement downloadMenu;
	@FindBy(how = How.ID, using = "myAccount")
	private WebElement myAccountLink;
	@FindBy(how = How.LINK_TEXT, using = "My Saved Searches")
	private WebElement mySavedSearchesMenu;
	@FindBy(how = How.LINK_TEXT, using = "My Purchases")
	private WebElement myPurchasesMenuLink;
	@FindBy(how = How.XPATH, using = "//*[@id='master']//div[20]//div[2]//div[1]//div[2]//span")
	private WebElement topNotchDropDown;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisibleCheck;

	@FindBy(how = How.LINK_TEXT, using = "My Preferences")
	private WebElement myPreferences;

	@FindBy(how = How.LINK_TEXT, using = "License Preferences")
	private WebElement licensePreferences;

	@FindBy(how = How.LINK_TEXT, using = "My Registration Info")
	private WebElement myRegistrationInfo;

	@FindBy(how = How.LINK_TEXT, using = "Profiles")
	private WebElement Profiles;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'myAccountMenu')]")
	private List<WebElement> myAccountMenuList;

	@FindBy(how = How.XPATH, using = "//div[@class='progreeBarForIframe']//img")
	private WebElement progressImg;

	@FindBy(how = How.XPATH, using = "//div[@class='progreeBarForIframe']//img")
	private List<WebElement> progressImgInvisible;

	public boolean isMyTrackingListNavLinkDisplayed() {
		return MyTrackingListsNavLink.isDisplayed();
	}

	public boolean isMySavedSearchedNavLinkDisplayed() {
		return MySavedSearchesNavLink.isDisplayed();
	}

	public void clickFirstEditLink() {
		extentTest.log(Status.INFO, "Click On First Edit Link");
		EditFirstLink.click();
	}

	public void clickOnFirstDeleteLink() {
		extentTest.log(Status.INFO, "Click On My Account Link");
		DeleteFirstLink.click();
	}

	public void clickOnMyAccountLink() {
		extentTest.log(Status.INFO, "Click On MyAccount Link");
		myAccountLink.click();
	}

	public AdminUsersPage clickOnUsersLink() {
		extentTest.log(Status.INFO, "Click On Users Link");
		try {
			UsersLink.click();
			waitforLoadingBar();
		} catch (Exception ex) {
			return null;
		}
		return new AdminUsersPage(driver);
	}

	public AdminUsersPage clickOnUsersLinkWithSwithFrame() {
		extentTest.log(Status.INFO, "Click On Users Link");
		try {
			UsersLink.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("iAdminControl");
		} catch (Exception ex) {
			return null;
		}
		return new AdminUsersPage(driver);
	}

	public AdminReportsPage clickOnReportsLink() {
		extentTest.log(Status.INFO, "Click On Reports Link");
		try {
			reportsLink.click();
			waitforLoadingBar();
		} catch (Exception ex) {
			return null;
		}
		return new AdminReportsPage(driver);
	}

	public boolean isReportsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Reports' link displayed");
		try {
			return reportsLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public TrackingLists clickOnMyTrackingListsNavLink() {
		extentTest.log(Status.INFO, "Click On My Account Link");
		MyTrackingListsNavLink.click();
		return new TrackingLists(driver);
	}

	public TrackingLists clickOnMyTrackingLists() {
		extentTest.log(Status.INFO, "Click On My Account Link");
		MyTrackingListsNavLink.click();
		return new TrackingLists(driver);
	}

	public SpecAlertsResultsPage clickOnMySpecAlertsNavLink() {
		extentTest.log(Status.INFO, "Click On My Account Link");
		MySpecAlertsNavLink.click();
		return new SpecAlertsResultsPage(driver);
	}

	public boolean isMySpecAlertsDisplayed() {
		extentTest.log(Status.INFO, "Verify if 'My SpecAlerts' displayed under 'My Accounts' menu");
		try {
			return MySpecAlertsNavLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public MyDownloadsPage clickOnMyDownloads() {
		extentTest.log(Status.INFO, "Click On My download Link");
		downloadMenu.click();
		return new MyDownloadsPage(driver);
	}

	public MyDownloadsPage clickOnDownloadNavLink() {
		extentTest.log(Status.INFO, "Click On My download Link");
		downloadMenu.click();
		return new MyDownloadsPage(driver);
	}

	public ManageProfilesPage clickOnProfiles() {
		extentTest.log(Status.INFO, "Click On Profiles Link");
		Profiles.click();
		SeleniumUtils.isVisible(linkSpecAlerts, driver);
		return new ManageProfilesPage(driver);
	}

	public ManageProfilesPage clickOnProfilesWithSwitchFrame() {
		extentTest.log(Status.INFO, "Click On Profiles Link");
		Profiles.click();
		return new ManageProfilesPage(driver);
	}

	public SavedSearchesPage clickOnSavedSearchMenuLink() {
		extentTest.log(Status.INFO, "Click On My SavedSearchMenu Link");
		mySavedSearchesMenu.click();
		return new SavedSearchesPage(driver);
	}

	public SavedSearchesPage clickOnSavedSearchMenuLinkMyAccount() {
		extentTest.log(Status.INFO, "Click On My SavedSearchMenu Link");
		mySavedSearchesMenu.click();
		return new SavedSearchesPage(driver);
	}

	public MyPreferencesPage clickOnMyPreferences() {
		extentTest.log(Status.INFO, "Verify that Clicks on My Preferences menu");
		SeleniumUtils.isVisible(myPreferences, driver);
		myPreferences.click();
		return new MyPreferencesPage(driver);
	}

	public boolean isMyAccountTopNotchDisplayed() {
		extentTest.log(Status.INFO, "Verify that my account has Drop down top notch");
		return topNotchDropDown.isDisplayed();

	}

	public MyPurchasesPage clickOnMyPurchasesLink() {
		extentTest.log(Status.INFO, "Clicking on My Purchases link");
		myPurchasesMenuLink.click();
		waitforLoadingRing();
		return new MyPurchasesPage(driver);
	}

	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void EscKeyPress() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);
	}

	public boolean checkMyTrackingListsPositionForPlusUser(String myTrackingLists) {
		extentTest.log(Status.INFO, "Verify My Tracking Lists is at first position in the list for Plus user");
		return myAccountMenuList.get(0).getText().toUpperCase().equals(myTrackingLists.toUpperCase());
	}

	public boolean checkMySavedSearchPositionForPlusUser(String mySavedSearchs) {
		extentTest.log(Status.INFO, "Verify My Saved Searched is at second position in the list for Plus user");
		return myAccountMenuList.get(1).getText().toUpperCase().equals(mySavedSearchs.toUpperCase());
	}

	public boolean isProfilesDisplayed() {
		extentTest.log(Status.INFO, "Verify if the profiles link displayed");
		try {
			return Profiles.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public LicensePreferencePage clickOnLicensePreference() {
		extentTest.log(Status.INFO, "Clicking the License Preference Link");
		licensePreferences.click();
		waitforLoadingBar();
		return new LicensePreferencePage(driver);
	}

	public boolean isLicensePreferenceDisplayed() {
		extentTest.log(Status.INFO, "Verify if License Preference is displayed");
		try {
			return licensePreferences.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isMyShippingAddressDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Shipping Address is displayed");
		try {
			return myShippingAddress.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isMyPurchasesDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Purchases is displayed under the 'My Accounts' dropdown");
		try {
			return myPurchasesMenuLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isMyDownlaodsDisplayed() {
		extentTest.log(Status.INFO, "Verify if My downloads is displayed under the 'My Accounts' dropdown");
		try {
			return downloadMenu.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isMyRegisterationDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Registeration is displayed under the 'My Accounts' dropdown");
		try {
			return myRegistrationInfo.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public MyRegistrationInfoPage clickOnMyRegistration() {
		extentTest.log(Status.INFO, "Clicking on 'My Registration'");
		myRegistrationInfo.click();
		CommonUtils.switchToNewTab(driver);
		return new MyRegistrationInfoPage(driver);
	}

	public void waitforLoadingBar() {
		SeleniumUtils.isVisible(progressImg, driver);
		SeleniumUtils.isLoadingIconInvisible(progressImgInvisible, driver);
	}

	public MyShippingAddressPage clickOnMyShippingAddress() {
		extentTest.log(Status.INFO, "Clicking the My Shiiping Address Link");
		myShippingAddress.click();
		waitforLoadingBar();
		return new MyShippingAddressPage(driver);
	}
	
	public boolean isMyPreferencesDisplayed() {
		extentTest.log(Status.INFO, "Verify if My Preferences is displayed");
		try {
			return myPreferences.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

}
