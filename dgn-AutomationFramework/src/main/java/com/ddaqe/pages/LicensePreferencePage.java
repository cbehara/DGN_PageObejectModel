package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class LicensePreferencePage extends MyAccountCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_cplBody_chkApprovalRequired")
	private WebElement approvalRequiredChk;

	@FindBy(how = How.XPATH, using = "//label[@for='ctl00_cplBody_chkApprovalRequired']")
	private WebElement approvalRequiredLabelFor;

	@FindBy(how = How.ID, using = "ctl00_cplBody_chkNotifyAdmin")
	private WebElement notifyAdminChk;

	@FindBy(how = How.XPATH, using = "//label[@for='ctl00_cplBody_chkNotifyAdmin']")
	private WebElement notifyAdminLabelFor;

	@FindBy(how = How.ID, using = "ctl00_cplBody_chkEnableNewPDFViewer")
	private WebElement enablePDFViewerChk;
	

	@FindBy(how = How.XPATH, using = "//label[@for='ctl00_cplBody_chkEnableNewPDFViewer']")
	private WebElement enablePDFViewerLabelFor;

	@FindBy(how = How.ID, using = "ctl00_cplBody_btnSaveLicensePreference")
	private WebElement saveBtn;

	@FindBy(how = How.ID, using = "My-Account---License-Preferences")
	private WebElement breadCrumb;

	@FindBy(how = How.ID, using = "ctl00_cplBody_lblLicense")
	private WebElement lblLicense;

	@FindBy(how = How.ID, using = "ctl00_cplBody_ddlAdmin")
	private WebElement ddlAdmin;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix adminContainer']")
	private WebElement myDownloadBackgroundPage;

	@FindBy(how = How.ID, using = "ctl00_bodyPlaceHolder_ucAdminMenu_LinkLicensePreferences")
	private WebElement clickOnLicensePreference;

	@FindBy(how = How.ID, using = "lblPreference")
	private WebElement checkLicensePreferencePageDispaly;

	public LicensePreferencePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Account - License Page");
	}

	@Override
	public void switchToFrame() {
		driver.switchTo().frame("iAdminControl");
	}

	@Override
	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public boolean isApprovalRequiredDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Approval Required check box displayed");
		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(approvalRequiredChk));
			return approvalRequiredChk.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isEnableKeywordDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Enable keyword highlighting check box displayed");
		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(enablePDFViewerChk));
			return enablePDFViewerChk.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean verifyIsApprovalRequiredLabelInBold() {
		extentTest.log(Status.INFO, "Verify whether the Approval Required label is displayed in bold?");
		return CommonUtils.isFontBold(driver, approvalRequiredLabelFor);
	}

	public boolean verifyIsNotifyAdminLabelInBold() {
		extentTest.log(Status.INFO, "Verify whether the Notify Admin label is displayed in bold?");
		return CommonUtils.isFontBold(driver, notifyAdminLabelFor);
	}

	public boolean verifyIsEnablePDFViewerLabelInBold() {
		extentTest.log(Status.INFO, "Verify whether the Enable PDF Viewer label is displayed in bold?");
		return CommonUtils.isFontBold(driver, enablePDFViewerLabelFor);
	}

	public boolean isNotifyAdminDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Notify Admin check box displayed");
		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(notifyAdminChk));
			return notifyAdminChk.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isLicenseDisplayed() {
		extentTest.log(Status.INFO, "Verify if the License is displayed");
		try {

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(lblLicense));
			return lblLicense.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickOnApprovalRequiredChk() {
		extentTest.log(Status.INFO, "Clicking the Approval Required check box");
		approvalRequiredChk.click();
	}

	public void clickOnEnableKeywordHighlightChk() {
		extentTest.log(Status.INFO, "Clicking the Enable keyword highlighting check box");
		if (!isEnableKeywordHighlightChecked()) {
			enablePDFViewerChk.click();
		}
	}

	public void unCheckEnableKeywordHighlightCheckbox() {
		extentTest.log(Status.INFO, "Uncheck Enable keyword highlighting check box");
		if (isEnableKeywordHighlightChecked()) {
			enablePDFViewerChk.click();
		}
	}
	
	public void clickOnNotifyAdminChk() {
		extentTest.log(Status.INFO, "Clicking the Notify Admin check box");
		notifyAdminChk.click();
	}

	public void clickOnSaveButton() {
		extentTest.log(Status.INFO, "Clicking the save button");
		saveBtn.click();
	}

	@Override
	public boolean isBreadCrumbCorrect(String name) {
		extentTest.log(Status.INFO, "Get the breadcrumb of the license preference page");
		try {
			return breadCrumb.getText().contains(name);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isApprovalRequiredChecked() {
		try {
			return approvalRequiredChk.getAttribute("checked").equals("true");
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isNotifyAdminChecked() {
		try {
			return notifyAdminChk.getAttribute("checked").equals("true");
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isEnableKeywordHighlightChecked() {
		extentTest.log(Status.INFO, "Verify Enable keword highlight checkbox is selected.");
		return enablePDFViewerChk.isSelected();
	}

	public boolean verifyAdminListSorted() {
		extentTest.log(Status.INFO, "Verify if the Admin drop down is sorted");
		ddlAdmin.click();
		Select se = new Select(ddlAdmin);
		List<WebElement> options = se.getOptions();
		List<String> adminOptions = CommonUtils.getListFromWebElements(options);
		List<String> adminListSorted = CommonUtils.sortWebElements(adminOptions, false);
		return CommonUtils.CompareTwoList(adminOptions, adminListSorted);
	}

	public void getAdminSelected(String name) {
		extentTest.log(Status.INFO, "Verify if the Admin drop down is sorted");
		SeleniumUtils.isVisible(ddlAdmin, driver);
		ddlAdmin.click();
		Select se = new Select(ddlAdmin);
		se.selectByVisibleText(name);
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);

	}

	public void clickOnLicensePreferenceLink() {
		extentTest.log(Status.INFO, "click On License OPreference Link");
		clickOnLicensePreference.click();
	}

	public boolean checkLicensePageDisplay() {
		extentTest.log(Status.INFO, "check LicensePage dispaly");
		return checkLicensePreferencePageDispaly.isDisplayed();
	}
}
