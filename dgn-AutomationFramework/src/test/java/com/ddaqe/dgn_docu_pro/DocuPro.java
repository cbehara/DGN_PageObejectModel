package com.ddaqe.dgn_docu_pro;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ddaqe.Listener.TestListener;
import com.ddaqe.base.BaseTest;
import com.ddaqe.dgn_notes.DGNNotes;
import com.ddaqe.pages.CompanyPage;
import com.ddaqe.pages.CompanyProjectsPage;
import com.ddaqe.pages.CompanyResultsPage;
import com.ddaqe.pages.HomePage;
import com.ddaqe.pages.HostToDocuProPopupDialog;
import com.ddaqe.pages.ProjectResultsPage;

@Listeners(TestListener.class)

public class DocuPro extends BaseTest {

	static Logger log = Logger.getLogger(DGNNotes.class);

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate company transfer from company profile views (TC12285)")
	public void DGN_T1763(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		CompanyPage companyPage = companyResultsPage.clickOnCompanyProfileLink();
		companyPage.clickOnActionsDropDown();
		Assert.assertTrue(companyPage.getCompanyActionsMenuItemList().contains("Host Company"),
				"Company Actions menu list doesn't contain 'Host Company' option.");
		HostToDocuProPopupDialog hostToDocuProPopupDialog = companyPage.clickOnHostCompanyActionsLink();
		Assert.assertTrue(hostToDocuProPopupDialog.verifyHostToDocuProPopupHeader("Transfer Companies to DocuPro"),
				"'Transfer Companies to DocuPro' dialog is not displayed when 'Host Company' Actions item is clicked.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate company transfer from company list views - Verify the action menu from the company list view (TC12286)")
	public void DGN_T1764_1787(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnActionsDropDown();
		Assert.assertTrue(companyResultsPage.mouseOverActionandCheckHostCompaniesDisplayed(),
				"Company Actions menu list doesn't contain 'Host Company' option.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "NonDocuProUser", description = "Create modal transfer companies dialog for docupro(PLAT use not Docupro Licenced)")
	public void DGN_T1780(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.waitforLoadingRing();
		companyResultsPage.clickOnActionsDropDown();
		Assert.assertFalse(companyResultsPage.Is_hostCompaniesActionsLink_Visible(),
				"Company Actions menu list contain 'Host Company' option.");
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "MultipleUsers", description = "Create modal transfer companies dialog for Docupro(Plat with docupro enabled) (TC12310)")
	public void DGN_T1779_1781_1797(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.SelectOptionsFromTheList(1, "companySelectChkList");
		companyResultsPage.clickOnActionsDropDown();
		companyResultsPage.ClickOn_hostCompaniesActionsLink();
		companyResultsPage.ClickOn_closeDocupropopup();
		Assert.assertTrue(companyResultsPage.Is_searchButton_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "MultipleUsers", description = "Initiate company transfer from company list views - Verify message when no company is selected from the list view (TC12299)")
	public void DGN_1782_1783_1784(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.ClickOn_hostProjectActionsLink();
		projectResultsPage.ClickOn_closeDocupropopup();
		Assert.assertTrue(projectResultsPage.Is_searchButton_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate company transfer from company list views - Verify message when no company is selected from the list view (TC12299)")
	public void DGN_T1772(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnActionsDropDown();
		companyResultsPage.ClickOn_hostCompaniesActionsLink();
		Assert.assertTrue(companyResultsPage.getErrorMessage().contains("Please make a selection"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate company transfer from company list views - Verify message when no company is selected from the list view (TC12299)")
	public void DGN_1798_1792(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFirmsLink();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		Assert.assertTrue(projectResultsPage.Is_hostProjectActionsLink_Visible());
		Assert.assertTrue(projectResultsPage.Is_lnkHostCompaniesLink_Visible());
		projectResultsPage.ClickOn_lnkHostCompaniesLink();
		Assert.assertTrue(projectResultsPage.getErrorMessage().contains("Please make a selection"));

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from Dodge Report views(PLUS ) (TC12408)")
	public void DGN_1786(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		projectResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(projectResultsPage.Is_hostToDocuProPopupHeader_Displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from Dodge Report views(PLUS ) (TC12408)")
	public void DGN_1768_1788(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		companyResultsPage.clickOnCompanyProjectsLink();
		companyResultsPage.clickOnProjectchkBox();
		companyResultsPage.clickOnactionsDropdowm_companyNavigator();
		Assert.assertTrue(companyResultsPage.Is_hostProjectActionsLink_Visible());
		companyResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(companyResultsPage.Is_hostToDocuProPopupHeader_Displayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from company projects view with more than one project for docupro(PLAT admin/user/PLUS) (TC12289)")
	public void DGN_1767(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.enteredInTheSearchBox("doors");
		companyResultsPage.clickOnSearchButton();
		CompanyProjectsPage companyProjectsPage = companyResultsPage.clickOnCompanyProjectsLink();
		companyProjectsPage.clickOnFistProjectCheckbox();
		companyProjectsPage.clickOnSecondProjectCheckbox();
		companyProjectsPage.clickOnactionsDropdowm_companyNavigator();
		companyProjectsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(companyProjectsPage.getErrorMessage().contains("You can only transfer one project at a time"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from company projects view without selecting a project for Docupro(PLAT Admin/User/PLUS) (TC12288)")
	public void DGN_1766(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		CompanyResultsPage companyResultsPage = homePage.clickOnCompaniesLink();
		companyResultsPage.clickOnCompanyProjectsLink();
		companyResultsPage.clickOnactionsDropdowm_companyNavigator();
		companyResultsPage.clickOnactionsDropdowm_companyNavigator();
		companyResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(companyResultsPage.getErrorMessage().contains("Please make a selection"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from Dodge Report views (PLAT Docupro enabled) (TC12287)")
	public void DGN_1765(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		projectResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(projectResultsPage.Is_hostToDocuProPopupHeader_Displayed());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "NonDocuProUser", description = "Initiate project transfer from Dodge Report views with non docupro enabled licence (TC12407)")
	public void DGN_1785_1790(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionsDropdown();
		Assert.assertFalse(projectResultsPage.Is_hostProjectActionsLink_Visible());
		projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		Assert.assertFalse(projectResultsPage.Is_hostProjectActionsLink_Visible());

	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from project list view with a project for docupro(PLAT admin/user/PLUS) (TC12296)")
	public void DGN_1771_1789_1793(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectOptionsFromTheList(1, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		Assert.assertTrue(projectResultsPage.Is_hostProjectActionsLink_Visible());
		projectResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(projectResultsPage.Is_hostToDocuProPopupHeader_Displayed());
		projectResultsPage.ClickOn_closeDocupropopup();
		Assert.assertTrue(projectResultsPage.Is_searchButton_displayed());
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from project list view with more than one project For Docupro(PLATadmin/user/PLUS) (TC12295)")
	public void DGN_1770(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.SelectOptionsFromTheList(2, "ProjectResult_Chkbox_List");
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(projectResultsPage.getErrorMessage().contains("You can only transfer one project at a time"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Initiate project transfer from project list view without selecting Project(Plat Admin/User/PLUS) (TC12294)")
	public void DGN_1769(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnActionsDropdown();
		projectResultsPage.ClickOn_hostProjectActionsLink();
		Assert.assertTrue(projectResultsPage.getErrorMessage().contains("Please make a selection"));
	}

	@Test(groups = { "Medium",
			"Regression" }, dataProviderClass = DocuPro_DataProvider.class, dataProvider = "Common_DocuProUser", description = "Add host option to the action menu on the Dodge Report views(except firm tab) (TC12451)")
	public void DGN_1791(String emailAddress, String password) throws InterruptedException {
		HomePage homePage = loginAs(emailAddress, password);
		ProjectResultsPage projectResultsPage = homePage.clickOnProjectsLink();
		projectResultsPage.clickOnFirstProjectTitleWithAddenda();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		Assert.assertTrue(projectResultsPage.Is_hostProjectActionsLink_Visible());
		projectResultsPage.ClickOnBackbutton();
		projectResultsPage.selectDeselectFindInLHSCheckboxList("Plans", true);
		projectResultsPage.selectDeselectFindInLHSCheckboxList("News", false);
		projectResultsPage.clickOnPlansLink();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		Assert.assertTrue(projectResultsPage.Is_hostProjectActionsLink_Visible());
		projectResultsPage.ClickOnBackbutton();
		projectResultsPage.clickOnSpecLink();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		Assert.assertTrue(projectResultsPage.Is_hostProjectActionsLink_Visible());
		projectResultsPage.ClickOnBackbutton();
		projectResultsPage.clickOnAddendaLink();
		projectResultsPage.ClickOn_ActionDropdown_ProjectDetailsPage();
		Assert.assertTrue(projectResultsPage.Is_hostProjectActionsLink_Visible());

	}
}
