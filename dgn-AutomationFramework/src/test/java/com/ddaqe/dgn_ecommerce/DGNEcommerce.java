package com.ddaqe.dgn_ecommerce;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_tracking_list.DGNTrackingListDataProvider;
import com.ddaqe.pages.EcommercePage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.MyAccount;
import com.ddaqe.pages.MyDownloadsPage;
import com.ddaqe.pages.MyPurchasesPage;
import com.ddaqe.pages.MyShippingAddressPage;
import com.ddaqe.pages.PaymentGatewayCustomerDetailsDGN;
import com.ddaqe.pages.ProjectPage;
import com.ddaqe.pages.ProjectResultsPage;
import com.ddaqe.pages.SavedSearchPopUp;
import com.ddaqe.pages.SavedSearchesPage;
import com.ddaqe.pages.TrackPopUpPage;
import com.ddaqe.pages.TrackingList;

@Listeners(TestListener.class)

public class DGNEcommerce extends BaseTest {
	static Logger log = Logger.getLogger(DGNEcommerce.class);

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
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1996(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();

		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1997(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		projectResultPage.clickAddOnlineProjects();
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultPage.isFirstImgInCartDisplayed(),
				"Failed to display the image in cart for the first project");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1998(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc1999(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// First
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		// Second
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnSecondPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();

		ecommercePage.SwitchToFrame();
		if (ecommercePage.getHardCopyCount() >= 1) {
			Assert.assertTrue(ecommercePage.isAdditionalChargeDisplayed(), "Failed to display the additional charge");
		}
		ecommercePage.clickRemoveHardCopy();

		ecommercePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2000(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// First
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		// Second
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		projectResultPage.clickOnSecondPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();

		ecommercePage.SwitchToFrame();
		if (ecommercePage.getHardCopyCount() >= 1) {
			Assert.assertTrue(ecommercePage.isAdditionalChargeDisplayed(), "Failed to display the additional charge");
		}
		ecommercePage.clickRemoveHardCopy();
		Assert.assertTrue(!ecommercePage.isAdditionalChargeDisplayed(),
				"The additional charge should not be displayed if there are no hard copies");

		ecommercePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2002(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// First
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isShipHardCopyDisplayed(), "Failed to display the ship hard copy options");
		ecommercePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2003(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		ecommercePage.clickCheckOutLinkTopPurchase();
		ecommercePage.switchToDefault();
		goToBackPage();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2004(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickShoppingCartLink();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"), "Failed to get the ecommerce page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2008(String EmailAddress, String Password) {
		// precondition : add online project
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();
		// precondition : add hard copy
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isShipHardCopyDisplayed(), "Failed to display the ship hard copy dropdown");
		ecommercePage.clickAllHardCopyCancelButton();
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2010(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		// precondition : add hard copy
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isShipHardCopyDisplayed(), "Failed to display the ship hard copy dropdown");
		Assert.assertTrue(ecommercePage.verifyShippingAddressOptions(),
				"Failed to get the shipping address options as expected");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2014(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		// precondition : add hard copy
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		ecommercePage.SwitchToFrame();
		ecommercePage.selectAddNewShippingAddress();
		Assert.assertTrue(ecommercePage.isAddShippingAddressPopUpDisplayed(),
				"Failed to display the Add Shipping Pop Up");
		ecommercePage.populateNewShippingAddressPopUp();
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2017(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		Integer count = myShippingAddressPage.getAddressCount();
		myShippingAddressPage.clickFirstDeleteButton();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count - 1, "Failed to delete the address");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2018(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		ecommercePage.SwitchToFrame();
		ecommercePage.clickOnSeleclAllChk();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		Assert.assertTrue(ecommercePage.getCartEmptyText().equalsIgnoreCase("Your cart is empty"),
				"Failed to get the expected message");
		Assert.assertTrue(ecommercePage.isContinueShoppingEmptyLinkDisplayed(), "Failed to display the continue link");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2019(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		ecommercePage.clickOnCancelBtnWithYes();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"),
				"Failed to redirect to the expected page");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2020(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		ecommercePage.clickOnCancelBtnWithNo();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2021(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.enterSearchText("doors");
		projectResultPage.clickOnSearchButton();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		ecommercePage.SwitchToFrame();
		ecommercePage.clickOnRecalculateButton();
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2023(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();
		ecommercePage.SwitchToFrame();
		ecommercePage.clickOnRecalculateButton();
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtTop(),
				"Failed to get the total amount as the sum of the additional charges");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2024(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		double totalValue = ecommercePage.getTotalAmount();

		ecommercePage.chkFirstProject();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		ecommercePage.switchToDefault();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.getTotalAmount() != totalValue, "Failed to recompute the total");
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtTop(),
				"Failed to get the total amount as the sum of the additional charges");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2027(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2028(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		ecommercePage.clickContinueShoppingBtn();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"),
				"Failed to redirect to the expected page");

		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2029(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		ecommercePage.SwitchToFrame();
		ecommercePage.clickContinueShoppingBtn();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectPlans.aspx"),
				"Failed to redirect to the expected page");

		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2011(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		Assert.assertTrue(projectPage.isPrintProductListUnderActionsDisplayed(),
				"Failed to display the Print Product List option under actions drop down");
		Assert.assertTrue(projectPage.isOrderHardCopyDocumentsLinkDisplayed(),
				"Failed to display the Order Hard Copy Documents option under actions drop down");
		Assert.assertTrue(projectPage.isAddOnlineProjectUnderActionsDisplayed(),
				"Failed to display Add Online Projects under Actions drop down");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2033(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		Assert.assertTrue(myAccountPage.isMyShippingAddressDisplayed(),
				"Failed to display the 'My Shipping Address' under the My Accounts dropdown menu");
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		Assert.assertTrue(myShippingAddressPage.getCurrentURL().contains("ShippingAddress.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2034(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		Assert.assertTrue(myAccountPage.isMyShippingAddressDisplayed(),
				"Failed to display the 'My Shipping Address' under the My Accounts dropdown menu");
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		Assert.assertTrue(myShippingAddressPage.getCurrentURL().contains("ShippingAddress.aspx"),
				"Failed to redirect to the expected page");
		Assert.assertTrue(myShippingAddressPage.isAddAddressButtonDisplayed(),
				"Failed to display the add address button");
		Assert.assertTrue(myShippingAddressPage.isEditAddressLinkDisplayed(),
				"Failed to display the edit address link");
		Assert.assertTrue(myShippingAddressPage.isDeleteAddressLinkDisplayed(),
				"Failed to display the delete address link");

		Assert.assertTrue(myShippingAddressPage.isBreadCrumbDisplayed(), "Failed to display the expected breadcrumb");
		Assert.assertTrue(
				myShippingAddressPage.getTitle().contains("Dodge Global Network - My Account - My Shipping Addresses"),
				"Failed to display the expected page title");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2036(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();

		Assert.assertTrue(myShippingAddressPage.isBreadCrumbDisplayed(), "Failed to display the expected breadcrumb");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2037(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(projectPage.getErrorMessage().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected message");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2040(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2041(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2042(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(projectPage.getErrorMessage().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected message");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2043(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(ecommercePage.isSetOfItemDescriptionValid(),
				"Failed to get the Items description as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2044(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2046(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultPage.isProjectSelectAllDisplayed(), "Failed to display the Select All Checkbox");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2047(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstAddendaLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(projectPage.getErrorMessage().equalsIgnoreCase("Please make a selection"),
				"Failed to get the expected message");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2048(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstAddendaLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2051(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2052(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		// precondition
		myShippingAddressPage.populateShippingAddress("");
		myShippingAddressPage.clickSaveShippingAddress();

		Integer count = myShippingAddressPage.getAddressCount();
		myShippingAddressPage.clickFirstDeleteButton();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count - 1, "Failed to delete the address");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2053(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();

		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		// precondition
		myShippingAddressPage.populateShippingAddress("");
		myShippingAddressPage.clickSaveShippingAddress();

		Integer count = myShippingAddressPage.getAddressCount();
		myShippingAddressPage.clickFirstDeleteButton();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count - 1, "Failed to delete the address");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2054(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		goToBackPage();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2056(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		goToBackPage();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2058(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstAddendaLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		goToBackPage();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2060(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();
		Assert.assertTrue(myPurchasesPage.isResultDropDowndisplayed(),
				"Failed to display the result per page at the top");
		Assert.assertTrue(myPurchasesPage.isResultPerPageCentered(),
				"Failed tp display the result per page in the center of the top");

		Assert.assertTrue(myPurchasesPage.isResultDropDownBottomdisplayed(),
				"Failed to display the result per page at the bottom");
		Assert.assertTrue(myPurchasesPage.isResultPerPageBottomCentered(),
				"Failed tp display the result per page in the center of the bottom");

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNTrackingListDataProvider.class, dataProvider = "TCPlatiAdmin_WithPurchases", description = "Track link from Summary (TC4143)")
	public void tc2892(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage customerAccountToolsPage = myAccount.clickOnMyPurchasesLink();
		Assert.assertTrue(customerAccountToolsPage.isResultDropDowndisplayed(),
				"Failed to display the result per page at the top");
		Assert.assertTrue(customerAccountToolsPage.isResultPerPageCentered(),
				"Failed tp display the result per page in the center of the top");
		Assert.assertTrue(customerAccountToolsPage.isResultDropDownBottomdisplayed(),
				"Failed to display the result per page at the bottom");
		Assert.assertTrue(customerAccountToolsPage.isResultPerPageBottomCentered(),
				"Failed tp display the result per page in the center of the bottom");
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2064(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(ecommercePage.isSetOfItemDescriptionValid(),
				"Failed to get the Items description as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2065(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2066(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(ecommercePage.isSetOfItemDescriptionValid(),
				"Failed to get the Items description as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2067(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2068(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstAddendaLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(ecommercePage.isSetOfItemDescriptionValid(),
				"Failed to get the Items description as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2069(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstAddendaLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2073(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.populateShippingAddress("");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2074(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.populateShippingAddress("99");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2075(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		Assert.assertTrue(projectPage.getCurrentURl().contains("ProjectPlans.aspx"),
				"Failed to get the current page as expected");
		projectPage.clickOnBreadCrumbProjectLink();
		Assert.assertTrue(projectPage.getCurrentURl().contains("ProjectResults.aspx"),
				"Failed to get the current page as expected");

		projectResultPage.clickOnFirstSpecsLink();
		Assert.assertTrue(projectPage.getCurrentURl().contains("ProjectSpecs.aspx"),
				"Failed to get the current page as expected");
		projectPage.clickOnBreadCrumbProjectLink();
		Assert.assertTrue(projectPage.getCurrentURl().contains("ProjectResults.aspx"),
				"Failed to get the current page as expected");

		projectResultPage.clickOnFirstAddendaLink();
		Assert.assertTrue(projectPage.getCurrentURl().contains("ProjectAddenda.aspx"),
				"Failed to get the current page as expected");
		projectPage.clickOnBreadCrumbProjectLink();
		Assert.assertTrue(projectPage.getCurrentURl().contains("ProjectResults.aspx"),
				"Failed to get the current page as expected");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2076(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		Assert.assertTrue(myPurchasesPage.getCurrentURL().contains("PurchaseHistory.aspx"),
				"Failed to get the expected Purchase history page");

		Assert.assertTrue(myPurchasesPage.isResultDropDowndisplayed(),
				"Failed to display the result per page at the top");
		Assert.assertTrue(myPurchasesPage.isResultPerPageCentered(),
				"Failed tp display the result per page in the center of the top");

		Assert.assertTrue(myPurchasesPage.isResultDropDownBottomdisplayed(),
				"Failed to display the result per page at the bottom");
		Assert.assertTrue(myPurchasesPage.isResultPerPageBottomCentered(),
				"Failed tp display the result per page in the center of the bottom");

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.getCurrentURL().contains("MyDownloads.aspx"),
				"Failed to get the expected downloads page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2078(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		Assert.assertTrue(myShippingAddressPage.isNameEmpty(), "Failed to get the name as empty");
		Assert.assertTrue(myShippingAddressPage.isFullNameEmpty(), "Failed to get the full name as empty");
		Assert.assertTrue(myShippingAddressPage.isAddressEmpty(), "Failed to get the address as empty");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2080(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(
				myShippingAddressPage.getErrorMessage().equalsIgnoreCase("Please provide a name for this address"),
				"Failed to get the expected message");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[21];
		Arrays.fill(charArray, '@');
		String new21 = new String(charArray);
		myShippingAddressPage.enterName(new21);
		Assert.assertTrue(myShippingAddressPage.getNameLength() < 20, "Failed to get the expected length for Name");
		myShippingAddressPage.clickSaveShippingAddress();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2081(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[51];
		Arrays.fill(charArray, '@');
		String new51 = new String(charArray);
		myShippingAddressPage.enterFullName(new51);
		Assert.assertTrue(myShippingAddressPage.getFullNameLength() < 50,
				"Failed to get the expected length for Full Name");
		myShippingAddressPage.clickSaveShippingAddress();

		myShippingAddressPage.enterFullName("");
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(
				myShippingAddressPage.getErrorMessage().equalsIgnoreCase("Please provide recipient's full name"),
				"Failed to get the expected message");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2082(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");

		myShippingAddressPage.enterPhoneNumber("");
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(
				myShippingAddressPage.getErrorMessage().equalsIgnoreCase("Please provide recipient's phone number"),
				"Failed to get the expected message");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2083(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[51];
		Arrays.fill(charArray, '@');
		String new51 = new String(charArray);
		myShippingAddressPage.enterExtension(new51);
		Assert.assertTrue(myShippingAddressPage.getExtensionLength() < 6,
				"Failed to get the expected length for Extension");
		myShippingAddressPage.clickSaveShippingAddress();

		myShippingAddressPage.enterExtension("");
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(!myShippingAddressPage.isErrorMessageDisplayed(), "Error message should not be displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2084(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[150];
		Arrays.fill(charArray, '@');
		String new51 = new String(charArray);
		myShippingAddressPage.enterAddress(new51);
		Assert.assertTrue(myShippingAddressPage.getAddressLength() < 70,
				"Failed to get the expected length for Address");
		myShippingAddressPage.clickSaveShippingAddress();

		myShippingAddressPage.enterAddress("");
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(!myShippingAddressPage.isErrorMessageDisplayed(), "Error message should not be displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2085(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[51];
		Arrays.fill(charArray, '@');
		String new51 = new String(charArray);
		myShippingAddressPage.enterCity(new51);
		Assert.assertTrue(myShippingAddressPage.getCityLength() < 50, "Failed to get the expected length for City");
		myShippingAddressPage.clickSaveShippingAddress();

		myShippingAddressPage.enterCity("");
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(!myShippingAddressPage.isErrorMessageDisplayed(), "Error message should not be displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2086(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");

		myShippingAddressPage.enterState("New York");
		myShippingAddressPage.clickSaveShippingAddress();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2087(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[11];
		Arrays.fill(charArray, '1');
		String new51 = new String(charArray);
		myShippingAddressPage.enterZipCode(new51);
		Assert.assertTrue(myShippingAddressPage.getZipCodeLength() < 10,
				"Failed to get the expected length for Zipcode");
		myShippingAddressPage.clickSaveShippingAddress();

		myShippingAddressPage.enterZipCode("");
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(!myShippingAddressPage.isErrorMessageDisplayed(), "Error message should not be displayed");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2088(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		Integer count = myShippingAddressPage.getAddressCount();

		myShippingAddressPage.populateShippingAddress("");

		myShippingAddressPage.clickCancelButtonInAddProfilePopup();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count + 1, "Failed to add the shipping address");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2089(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		Integer count = myShippingAddressPage.getAddressCount();
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(
				myShippingAddressPage.getErrorMessage().equalsIgnoreCase("Please provide a name for this address"),
				"Failed to get the expected message");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[21];
		Arrays.fill(charArray, '@');
		String new21 = new String(charArray);
		myShippingAddressPage.enterName(new21);
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count + 1, "Failed to add the shipping address");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2090(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		Integer count = myShippingAddressPage.getAddressCount();
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(
				myShippingAddressPage.getErrorMessage().equalsIgnoreCase("Please provide a name for this address"),
				"Failed to get the expected message");

		myShippingAddressPage.populateShippingAddress("");
		char[] charArray = new char[21];
		Arrays.fill(charArray, '@');
		String new21 = new String(charArray);
		myShippingAddressPage.enterName(new21);
		myShippingAddressPage.clickSaveShippingAddress();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count + 1, "Failed to add the shipping address");

		homePage.clickOnSignOutButton();
	}

	// Clicking the Print Link at the top breaks the application
	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2091(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccountPage.clickOnMyPurchasesLink();
		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();
		ecommercePage.clickPrintLink();
		Assert.assertTrue(ecommercePage.isPrintTopLinkDisplayed(), "Failed to get the print link at the top");
		// ecommercePage.clickPrintTopLink();//Bug
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2093(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFistProjectCheckbox();
		projectResultsPage.clickOnActionsDropdown();
		TrackPopUpPage trackPopUpPage = projectResultsPage.clickOnTrackProjects();
		trackPopUpPage.clickOnOneExistingTrackingListCheckBox(1);
		trackPopUpPage.clickOnSaveBtn();

		homePage.clickOnMyAccountLink();

		homePage.clickOnMyAccountLink();
		TrackingList trackingList = homePage.clickOnMyTrackingList();
		projectResultsPage = trackingList.clickOnMyProjects();
		projectResultsPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultsPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();

		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2094(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtBottom(),
				"Failed to get the total amount as the sum of the additional charges including tax");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2095(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// First
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		// Second
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnSecondPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtBottom(),
				"Failed to get the total amount as the sum of the additional charges including tax");
		double totalValue = ecommercePage.getTotalAmount();

		ecommercePage.chkFirstProject();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		ecommercePage.switchToDefault();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.getTotalAmount() != totalValue, "Failed to recompute the total");
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtBottom(),
				"Failed to get the total amount as the sum of the additional charges");
		ecommercePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2096(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();
		ecommercePage.SwitchToFrame();
		ecommercePage.clickOnRecalculateButton();
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtBottom(),
				"Failed to get the total amount as the sum of the additional charges including tax");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2098(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		String drNumber = projectPage.getProjectDRNumber();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.populatePaymentDetails();
		paymentGatewayCustomerDetailsDGN.clickPlaceOrder();
		ecommercePage.switchToDefault();

		homePage.clickOnProjectsLink();
		homePage.enterSearchText(drNumber);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2099(String EmailAddress, String Password) throws InterruptedException {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		homePage.enterSearchText("wood");
		homePage.clickOnSearchButton();
		SavedSearchPopUp saveSearchPopUp = homePage.clickOnSaveSearchButtonProject();

		String searchName1 = "Automation" + String.valueOf(new Date().getTime());
		saveSearchPopUp.enterName(searchName1);
		saveSearchPopUp.clickSave();
		Assert.assertEquals(projectResultPage.getTitle(), "Dodge Global Network - Project Results");
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		String drNumber = projectPage.getProjectDRNumber();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.populatePaymentDetails();
		paymentGatewayCustomerDetailsDGN.clickPlaceOrder();
		ecommercePage.switchToDefault();

		homePage.clickOnProjectsLink();
		homePage.clickOnMySearchesDropDown();
		SavedSearchesPage savedSearchesPage = homePage.clickOnSavedSearchMenu();
		Assert.assertTrue(savedSearchesPage.isSavedSearchPresent(searchName1));

		homePage.clickOnProjectsLink();
		homePage.enterSearchText(drNumber);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2102(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		Assert.assertTrue(myAccountPage.isMyShippingAddressDisplayed(),
				"Failed to display the 'My Shipping' option under the My Accounts Dropdown");

		Assert.assertTrue(myAccountPage.isMyPurchasesDisplayed(),
				"Failed to display the 'My Purchases' option under the My Accounts Dropdown");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2103(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		Assert.assertTrue(projectResultPage.isAddOnlineProjectActionOptionsDisplayed(),
				"Failed to display the Add Online Projects under the actions drop down");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2104(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2105(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		Assert.assertTrue(ecommercePage.isAlertDisplayed(), "Failed to prompt the alert");
		ecommercePage.alertAccept();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2106(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		double totalValue = ecommercePage.getTotalAmount();

		ecommercePage.chkFirstProject();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		ecommercePage.switchToDefault();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.getTotalAmount() != totalValue, "Failed to recompute the total");
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtTop(),
				"Failed to get the total amount as the sum of the additional charges");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2111(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2112(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.clickCancel();
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2113(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.populatePaymentDetails();
		paymentGatewayCustomerDetailsDGN.clickPlaceOrder();
		ecommercePage.switchToDefault();

		Assert.assertTrue(projectPage.getCurrentURl().contains("Home.aspx"), "Failed to get the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2114(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		double totalValue = ecommercePage.getTotalAmount();

		ecommercePage.chkFirstProject();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		ecommercePage.switchToDefault();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.getTotalAmount() != totalValue, "Failed to recompute the total");
		ecommercePage.clickOnRecalculateButton();
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtTop(),
				"Failed to get the total amount as the sum of the additional charges");

		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2116(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		ecommercePage.SwitchToFrame();
		ecommercePage.clickContinueShoppingBtn();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectPlans.aspx"),
				"Failed to redirect to the expected page");
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2117(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		ecommercePage.SwitchToFrame();
		ecommercePage.clickContinueShoppingBtn();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectSpecs.aspx"),
				"Failed to redirect to the expected page");
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2120(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccountPage.clickOnMyPurchasesLink();
		myPurchasesPage.clickOnFirstOrderNoLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2121(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccountPage.clickOnMyPurchasesLink();
		myPurchasesPage.clickOnFirstOrderNoLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2123(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccountPage.clickOnMyPurchasesLink();
		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();
		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2124(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);

		Assert.assertTrue(!homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2025(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
		loginAs(EmailAddress, Password);
		Assert.assertTrue(!homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2127(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccountPage.clickOnMyPurchasesLink();
		myPurchasesPage.clickOnFirstOrderNoLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2128(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccountPage.clickOnMyPurchasesLink();
		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();
		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2129(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		Assert.assertTrue(ecommercePage.isbillShipAddressDisplayed(), "Failed to display the billing/shipping address");
		Assert.assertTrue(ecommercePage.isChargedOnCreditCardDisplayed(),
				"Failed to display the Charged On Credit Card");
		Assert.assertTrue(ecommercePage.isPrintLinkDisplayed(), "Failed to display the print link");
		Assert.assertTrue(ecommercePage.isPurchaseTotalsSectionDisplayed(),
				"Failed to display the Purchase totals section");
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2130(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.isProjectTitleLinkDisplayed(), "Failed to display the Project Title Link");
		ecommercePage.clickOnProjectTitleLink();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2131(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		String drNumber = projectPage.getProjectDRNumber();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.populatePaymentDetails();
		paymentGatewayCustomerDetailsDGN.clickPlaceOrder();
		ecommercePage.switchToDefault();

		homePage.clickOnProjectsLink();
		homePage.enterSearchText(drNumber);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2132(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();

		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2133(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();

		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2134(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickShoppingCartLink();
		Assert.assertTrue(ecommercePage.getTitle().contains("Dodge Global Network - Shopping Cart"),
				"Failed to get the expected title");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2135(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickShoppingCartLink();
		Assert.assertTrue(ecommercePage.getTitle().contains("Dodge Global Network - Shopping Cart"),
				"Failed to get the expected title");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2136(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickShoppingCartLink();
		Assert.assertTrue(ecommercePage.getTitle().contains("Dodge Global Network - Shopping Cart"),
				"Failed to get the expected title");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2138(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickShoppingCartLink();
		Assert.assertTrue(ecommercePage.getTitle().contains("Dodge Global Network - Shopping Cart"),
				"Failed to get the expected title");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2139(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// First
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		// Second
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnSecondPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();

		ecommercePage.SwitchToFrame();
		if (ecommercePage.getHardCopyCount() >= 1) {
			Assert.assertTrue(ecommercePage.isAdditionalChargeDisplayed(), "Failed to display the additional charge");
		}
		ecommercePage.clickRemoveHardCopy();
		Assert.assertTrue(ecommercePage.isRemoveHardCopyDisplayed(), "Failed to display the remove icon");
		ecommercePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2140(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.clickCancel();
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2144(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.isProjectTitleLinkDisplayed(), "Failed to display the Project Title Link");
		ecommercePage.clickOnProjectTitleLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2145(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.isProjectTitleLinkDisplayed(), "Failed to display the Project Title Link");
		ecommercePage.clickOnProjectTitleLink();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2146(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2147(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2149(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2150(String EmailAddress, String Password) {
		// precondition : add online project
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();
		// precondition : add hard copy
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();
		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isShipHardCopyDisplayed(), "Failed to display the ship hard copy dropdown");
		ecommercePage.clickAllHardCopyCancelButton();
		Assert.assertTrue(!ecommercePage.isShipHardCopyDisplayed(),
				"The ship hard copy dropdown should not be displayed when there are no hard copies");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2151(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(homePage.isShoppingCartLinkDisplayed(), "Failed to display the shopping cart link");
		homePage.clickShoppingCartLink();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"), "Failed to get the ecommerce page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2152(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultPage.isProjectSelectAllDisplayed(), "Failed to display the Select All Checkbox");
		projectResultPage.SelectResultDropdownValue("25");
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2153(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultPage.isProjectSelectAllDisplayed(), "Failed to display the Select All Checkbox");
		projectResultPage.SelectResultDropdownValue("25");
		projectResultPage.clickOnSelectAllProjects();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();
		Assert.assertTrue(ecommercePage.getCurrentURL().contains("Ecommerce.aspx"),
				"Failed to redirect to the expected page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2154(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		// First
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		// Second
		homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnSecondPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		projectPage.clickOrderHardCopyDocuments();

		ecommercePage.SwitchToFrame();
		if (ecommercePage.getHardCopyCount() >= 1) {
			Assert.assertTrue(ecommercePage.isAdditionalChargeDisplayed(), "Failed to display the additional charge");
		}
		ecommercePage.clickRemoveHardCopy();
		Assert.assertTrue(!ecommercePage.isAdditionalChargeDisplayed(),
				"The additional charge should not be displayed if there are no hard copies");

		ecommercePage.switchToDefault();

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2158(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnSecondProjectCheckbox();

		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		double totalValue = ecommercePage.getTotalAmount();

		ecommercePage.chkFirstProject();
		ecommercePage.clickOnActionsDropDown();
		ecommercePage.clickOnRemoveLinkUnderActionsDropDown();
		ecommercePage.switchToDefault();

		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.getTotalAmount() > 0, "Failed to get the total");
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtTop(),
				"Failed to get the total amount as the sum of the additional charges");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2159(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		String drNumber = projectPage.getProjectDRNumber();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.populatePaymentDetails();
		paymentGatewayCustomerDetailsDGN.clickPlaceOrder();
		ecommercePage.switchToDefault();

		homePage.clickOnProjectsLink();
		homePage.enterSearchText(drNumber);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2162(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		String drNumber = projectPage.getProjectDRNumber();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");

		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		paymentGatewayCustomerDetailsDGN.populatePaymentDetails();
		paymentGatewayCustomerDetailsDGN.clickPlaceOrder();
		ecommercePage.switchToDefault();

		homePage.clickOnProjectsLink();
		homePage.enterSearchText(drNumber);
		homePage.clickOnSearchButton();
		Assert.assertTrue(projectPage.getCurrentURl().contains("Project.aspx"), "Failed to get the expected page");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2163(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		projectResultPage.clickOnFistProjectCheckbox();
		projectResultPage.clickOnActionsDropdownOutOfSubscription();
		EcommercePage ecommercePage = projectResultPage.clickAddOnlineProjects();

		ecommercePage.SwitchToFrame();
		ecommercePage.clickContinueShoppingBtn();
		Assert.assertTrue(projectResultPage.getCurrentURL().contains("ProjectResults.aspx"),
				"Failed to redirect to the expected page");

		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2164(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		MyAccount myAccountPage = homePage.clickOnMyAccountLink();
		MyShippingAddressPage myShippingAddressPage = myAccountPage.clickOnMyShippingAddress();
		myShippingAddressPage.clickAddProfileButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		myShippingAddressPage.clickFirstEditButton();
		Assert.assertTrue(myShippingAddressPage.isPopUpAddShippingAddressDisplayed(),
				"Failed to display the Add Shipping Address Popup");
		myShippingAddressPage.clickCancelButtonInAddProfilePopup();

		// precondition
		myShippingAddressPage.populateShippingAddress("");
		myShippingAddressPage.clickSaveShippingAddress();

		Integer count = myShippingAddressPage.getAddressCount();
		myShippingAddressPage.clickFirstDeleteButton();
		Assert.assertTrue(myShippingAddressPage.getAddressCount() == count - 1, "Failed to delete the address");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2165(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		Assert.assertTrue(myPurchasesPage.getCurrentURL().contains("PurchaseHistory.aspx"),
				"Failed to get the expected Purchase history page");

		Assert.assertTrue(myPurchasesPage.isResultDropDowndisplayed(),
				"Failed to display the result per page at the top");
		Assert.assertTrue(myPurchasesPage.isResultPerPageCentered(),
				"Failed tp display the result per page in the center of the top");

		Assert.assertTrue(myPurchasesPage.isResultDropDownBottomdisplayed(),
				"Failed to display the result per page at the bottom");
		Assert.assertTrue(myPurchasesPage.isResultPerPageBottomCentered(),
				"Failed tp display the result per page in the center of the bottom");

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		MyDownloadsPage myDownloadsPage = myAccount.clickOnMyDownloads();
		Assert.assertTrue(myDownloadsPage.getCurrentURL().contains("MyDownloads.aspx"),
				"Failed to get the expected downloads page");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2166(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.getTextBreadCrumb1().equalsIgnoreCase("My Account - My Purchases"),
				"Failed to get the expected breadcrumb part 1");
		Assert.assertTrue(ecommercePage.getTextBreadCrumb2().equalsIgnoreCase("Order Receipt"),
				"Failed to get the expected breadcrumb part 2");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2006(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		Assert.assertTrue(projectResultPage.isBiddersLinkDisabled(), "Failed to get the bidders link disabled");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2009(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		ecommercePage.SwitchToFrame();
		ecommercePage.clickOptionsLinkTop();
		ecommercePage.clickOptionPickUp();
		ecommercePage.clickOptionPopUpSaveButton();
		Assert.assertTrue(!ecommercePage.isShippingAddressDropdwonDisplayed(),
				"Shipping address dropdown should not be displayed");

		ecommercePage.clickOptionsLinkTop();
		ecommercePage.clickOptionShip();
		ecommercePage.clickOptionPopUpSaveButton();
		Assert.assertTrue(ecommercePage.isShippingAddressDropdwonDisplayed(),
				"Failed to display the shipping address dropdown");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2012(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		Assert.assertTrue(projectPage.verifyActionDropDownOptionsOutOfSubscription(),
				"Failed to get the options under actions dropdown as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2013(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		Assert.assertTrue(projectPage.verifyActionDropDownOptions(),
				"Failed to get the options under actions dropdown as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2015(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		Assert.assertTrue(projectPage.verifyActionDropDownOptions(),
				"Failed to get the options under actions dropdown as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2016(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		Assert.assertTrue(projectPage.verifyActionDropDownOptions(),
				"Failed to get the options under actions dropdown as expected");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2062(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstSpecsLink();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2168(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();

		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2169(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");
		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2170(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("doors");
		homePage.clickOnSearchButton();
		EcommercePage ecommercePage = projectResultPage.clickOnProjectWithPlansToAddThisProjectToYourLicense();

		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");
		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		ecommercePage.switchToDefault();
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2173(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");
		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		ecommercePage.switchToDefault();
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2174(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");
		ecommercePage.SwitchToFrame();
		PaymentGatewayCustomerDetailsDGN paymentGatewayCustomerDetailsDGN = ecommercePage
				.clickCheckOutLinkTopPurchase();
		Assert.assertTrue(paymentGatewayCustomerDetailsDGN.getCurrentURL().contains("CustomerDetailsDGN.aspx"),
				"Failed to get the credit card page");
		ecommercePage.switchToDefault();
		Assert.assertTrue(homePage.isheaderDodgeLogoDisplayed(), "Failed to get the expected logo");

		homePage.clickOnSignOutButton();
	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2175(String emailAddress, String password) {
		HomePage homePage = loginAs(emailAddress, password);
		MyAccount myAccount = homePage.clickOnMyAccountLink();
		MyPurchasesPage myPurchasesPage = myAccount.clickOnMyPurchasesLink();

		EcommercePage ecommercePage = myPurchasesPage.clickOnFirstOrderNoLink();

		Assert.assertTrue(ecommercePage.isPurchaseTotalsSectionDisplayed(),
				"Failed to display the Purchase totals section");
		ecommercePage.SwitchToFrame();
		Assert.assertTrue(ecommercePage.isSumTotalAsExpectedAtTop(),
				"Failed to get the total amount as the sum of the additional charges");
		ecommercePage.switchToDefault();
		homePage.clickOnSignOutButton();

	}

	@Test(groups = {
			"Medium" }, dataProviderClass = DGNEcommerceDashboardDataProvider.class, dataProvider = "TCOutOfSubscription", description = "[DGN] Display 'in progress' indicator on top of pagination controls while system loads the report ids (TC20998)")
	public void tc2176(String EmailAddress, String Password) {
		HomePage homePage = loginAs(EmailAddress, Password);
		ProjectResultsPage projectResultPage = homePage.clickOnProjectsLink();
		projectResultPage.ClickOnReports_ProjOutSideOfSub_radio();
		homePage.enterSearchText("woods");
		homePage.clickOnSearchButton();
		ProjectPage projectPage = projectResultPage.clickOnFirstPlansLink();
		projectPage.clickSelectAllTitle();
		projectPage.clickOnActionsDropDownForOutOfSubscription();
		EcommercePage ecommercePage = projectPage.clickOrderHardCopyDocuments();
		Assert.assertTrue(ecommercePage.isProjectFoundInItemsInShoppingCart(),
				"Failed to get the project in the items in the shopping cart");
		homePage.clickOnSignOutButton();
	}

}
