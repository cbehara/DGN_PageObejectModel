package com.ddaqe.dgn_free_trial;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.ActivationPage;
import com.ddaqe.pages.BidProHomePage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.LoginPage;

@Listeners(TestListener.class)

public class DGN_FreeTrial extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FreeTrial_DataProvider.class, dataProvider = "Valid_BidProUser", description = "Verify if a bidpro user, whose license has not expired, can login and use bidpro.")
	public void DGN_T3073(String emailAddress, String password, String URL) throws InterruptedException {
		BidProHomePage bidproHomepg = new BidProHomePage(getDriver());
		bidproHomepg.loginAs(emailAddress, password, URL);
		Assert.assertTrue(bidproHomepg.Is_SignOutLink_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FreeTrial_DataProvider.class, dataProvider = "Expired_BidProUser", description = "Verify if a bidpro user, whose license has not expired, can login and use bidpro.")
	public void DGN_T3072(String emailAddress, String password, String URL) throws InterruptedException {
		BidProHomePage bidproHomepg = new BidProHomePage(getDriver());
		bidproHomepg.loginAs(emailAddress, password, URL);
		bidproHomepg.ClickOnLicenseField();
		bidproHomepg.enterValueInLicenseField("106964743462695");
		bidproHomepg.clickOnSubmitButton();
		Assert.assertTrue(bidproHomepg.isExpiredLicenseErrorMsgDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FreeTrial_DataProvider.class, dataProvider = "Expired_DGNUser", description = "Verify if a DGN user whose license has expired, gets an error message,instead of license activation page.")
	public void DGN_T3070(String emailAddress, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(emailAddress, password);
		activationPage.clickOnLicenseField();
		activationPage.enterValueInLicenseField("106964743462695");
		activationPage.clickOnSubmitButton();
		Assert.assertTrue(activationPage.isExpiredLicenseErrorMsgDisplayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FreeTrial_DataProvider.class, dataProvider = "Expired_DGNUser", description = "Verify if a DGN user whose license has expired, redirected to license activation page.")
	public void DGN_T3074(String emailAddress, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		ActivationPage activationPage = loginPage.loginAsNonLicenseUser(emailAddress, password);
		Assert.assertTrue(activationPage.isLicenseInputPageDisplayed());
		Assert.assertTrue(activationPage.isWelcomeTextdisplayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = FreeTrial_DataProvider.class, dataProvider = "Valid_DGNUser", description = "Verify if a DGN user, whose license has not expired, can login and use DGN")
	public void DGN_T3071(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
	}
}
