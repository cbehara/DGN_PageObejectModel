package com.ddaqe.dgn_tech_support;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.pages.ManageUsers;
import com.ddaqe.pages.TechSupportPage;
import com.ddaqe.pages.UserInformationPage;
import com.ddaqe.pages.UserSelectionPage;

@Listeners(TestListener.class)
public class DGNTech_Support1 extends BaseTest {
	static Logger log = Logger.getLogger(DGNTech_Support1.class);

	@BeforeTest(alwaysRun = true)
	public void setupBeforeTest() {
		log.info("Before Test Started");
		log.info("Before Test Ended");

	}

	@AfterTest(alwaysRun = true)
	public void tearDownAfterTest() {
		log.info("After Test Started");
		log.info("After Test Ended");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Add and remove users from BidPro licenses - Scenario 1 (TC781)")
	public void tc2935(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		Assert.assertTrue(techSupportPage.ssoUserIDButtondisplay(), "SSOUserIDButton is not display");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Customer Admin - License Preferences - Tech Support - License Preferences Page verification for 2.0 license (TC11135)")
	public void tc2937(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		Assert.assertTrue(userSelectionPage.isManageUsersLinkPresent(), "manage Users link not present.");
		Assert.assertTrue(userSelectionPage.isManageProfilesLinkPresent(), "Manage Profile link is not present.");
		Assert.assertTrue(userSelectionPage.isGlobalPreferencesLinkPresent(),
				"Global Preferences link is not present.");
		Assert.assertTrue(userSelectionPage.isReportsLinkPresent(), "Report link is not present.");
		ManageUsers manageUsers = userSelectionPage.clickOnManageUserLink();
		manageUsers.clickOnLicensePreferencesLink();
		manageUsers.clickOnCustomerServiceHomeBtn();
		Assert.assertTrue(manageUsers.checkReportBtnDisplay(), "User should not navigate to License search Page");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TC2953", description = "[GY:I5577377] [Tech Support] Trying to look up a BidPro user results in a 'no matches found' message (TC16826)")
	public void tc2953(String username, String password, String URL, String bidproUserName)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterSSOUserIDTxt(bidproUserName);

		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		Assert.assertEquals(userInformationPage.getSSOUserNameTxt(), bidproUserName);
	}

	@Test(enabled = false, groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "[Customer Admin - Tech Support] Replicate profile views within tech support application - To Verify that what happens when a User is assigned to a Profile by the tech support tool (TC12093)")
	public void tc2938(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnManageProfileLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickShowUsersLink();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "[Customer Admin - Tech Support] Replicate profile views within tech support application - To Verify that what happens when tech support rep edits the Profile (TC12095)")
	public void tc2939(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnManageProfileLink();
		manageUsers.clickOnManageProfileLink();
		manageUsers.SwitchToFrame();
		Assert.assertTrue(manageUsers.isManageProfileSectionDisplayed(), "Manage Profile label is not displayed.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "[Customer Admin - Tech Support] Replicate profile views within tech support application - To Verify what happen when tech support rep adds a Profile (TC12098)")
	public void tc2942(String username, String password, String URL, String licenseID) throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnManageProfileLink();
		manageUsers.SwitchToFrame();
		manageUsers.clickOnAddProfileButton();
		String prrfileValue = String.valueOf(new Date().getTime());
		manageUsers.enterProjectProfileName(prrfileValue);
		manageUsers.enterProjectProfileDescription(prrfileValue);
		manageUsers.clickProfileEditLink(2);
		manageUsers.selectActionStageCheckBoxes(1);
		manageUsers.clickProfileDoneLink(2);
		manageUsers.clickOnManageProfileSaveButton();
		Assert.assertTrue(manageUsers.getProjectProfileNames().contains(prrfileValue), "Project profile is not added");
		manageUsers.deleteProjectProfile(prrfileValue);

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "[Customer Admin - Tech Support] Replicate profile views within tech support application - To Verify that what happens when tech support rep edits the Profile (TC12095)")
	public void tc2940_2941(String username, String password, String URL, String licenseID)
			throws InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterLicenseIDTxt(licenseID);
		UserSelectionPage userSelectionPage = techSupportPage.clickOnSubmitQueryBtn();
		ManageUsers manageUsers = userSelectionPage.clickOnManageProfileLink();
		manageUsers.clickOnManageProfileLink();
		manageUsers.SwitchToFrame();
		manageUsers.isManageProfileSectionDisplayed();
		manageUsers.clickManageProfileEditLink();
		manageUsers.clickProfileEditLink(2);
		List<String> selectedValue = manageUsers.selectActionStageLastValues(1);
		manageUsers.clickProfileDoneLink(2);
		Assert.assertTrue(manageUsers.getSelectedActionStageHeader().contains(selectedValue.get(0)),
				"Profile is not edited.");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TC2953", description = "Verify  bulk SSO creation process (TC21969)")
	public void tc3022(String username, String password, String URL, String bidproUserName)
			throws FileNotFoundException, InterruptedException {
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.enterSSOUserIDTxt(bidproUserName);

		UserInformationPage userInformationPage = techSupportPage.clickOnSubmitQueryBtnNavToUserInfoPage();
		Assert.assertTrue(userInformationPage.isHeaderPresent());
		Assert.assertEquals(userInformationPage.getSSOUserNameTxt(), bidproUserName);
	}
	
	@Test(groups = {
	"Medium" }, dataProviderClass = DGNTech_SupportDataProvider.class, dataProvider = "TCCommon", description = "Verify MFR product code")
	public void dgnt3634(String username, String password, String URL, String licenseID){
		TechSupportPage techSupportPage = new TechSupportPage(getDriver());
		techSupportPage.loginAs(username, password, URL);
		String licenseIDMFRAccess = "391341532727411";
		String licenseIDNotMFRAccess = "236189980795398";
		techSupportPage.enterLicenseIDTxt(licenseIDMFRAccess);
		techSupportPage.clickOnSubmitQueryBtn();
		techSupportPage.waitforLoadingRing();
		Assert.assertTrue(techSupportPage.getValueAttributeFromHorizonAbbrvTextField().contains("NETBOD"), "NETBOD is present in Horizon Product Abbrv.");
		techSupportPage.clickOnNetTechSupport();
		techSupportPage.waitforLoadingRing();
		techSupportPage.enterLicenseIDTxt(licenseIDNotMFRAccess);
		techSupportPage.clickOnSubmitQueryBtn();
		techSupportPage.waitforLoadingRing();
		Assert.assertTrue(!techSupportPage.getValueAttributeFromHorizonAbbrvTextField().contains("NETBOD"), "NETBOD is not present in Horizon Product Abbrv.");		
	}
	
}

