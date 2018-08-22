package com.ddaqe.pages;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class AdminReportsPage extends MyAccountCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[@class='licenseLabel']")
	private WebElement licenseLabel;

	@FindBy(how = How.ID, using = "ctl00_cplBody_ddlReportType")
	private WebElement ddlReportType;

	@FindBy(how = How.ID, using = "ctl00_cplBody_btnRun")
	private WebElement btnRun;

	@FindBy(how = How.ID, using = "txtUser")
	private WebElement userDrop;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtFromDate")
	private WebElement txtFromDate;

	@FindBy(how = How.ID, using = "ctl00_cplBody_txtToDate")
	private WebElement txtToDate;

	@FindBy(how = How.ID, using = "ctl00_cplBody_chkSelectAll")
	private WebElement chkSelectAll;

	@FindBy(how = How.ID, using = "ctl00_cplBody_rptUserList_ctl00_chkUserList")
	private WebElement chkFirstUser;

	@FindBy(how = How.XPATH, using = "//div[@class='userChkBox']//input")
	private List<WebElement> chkUserList;

	@FindBy(how = How.XPATH, using = "//input[@id='ctl00_cplBody_rptUserList_ctl00_chkUserList']//following-sibling::span")
	private WebElement lblFirstUser;

	@FindBy(how = How.XPATH, using = "//div[@id='dvUserInfoGrid']//tbody[@id='userInfoDetail']//tr//td[1]")
	private WebElement gridUserName;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'userContainer')]//input[@type='checkbox']")
	private List<WebElement> userDrpChkList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'userContainer')]//input[@type='checkbox']//following-sibling::span")
	private List<WebElement> userDrpChkTextList;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix adminContainer']")
	private WebElement myDownloadBackgroundPage;

	public AdminReportsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Account - Reports Page");
	}

	public boolean checkWhiteSpaceInLicenseLabel() {
		String regex = " ";
		boolean IsSpaceFound = false;
		driver.switchTo().frame("iAdminControl");
		extentTest.log(Status.INFO, "Verify extraneous white space should not be present in the Report license label");
		IsSpaceFound = licenseLabel.getText().indexOf(regex) > 0;
		driver.switchTo().defaultContent();
		return IsSpaceFound;
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getSelectedReportTypeOptions() {
		extentTest.log(Status.INFO, "Get the selected option in report type drop down");
		ddlReportType.click();
		Select se = new Select(ddlReportType);
		WebElement selectedOptions = se.getFirstSelectedOption();
		return selectedOptions.getText().trim();
	}

	public boolean isLicenseLabelDisplayed() {
		try {
			return licenseLabel.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean verifyReportTypeOptions() {
		extentTest.log(Status.INFO, "Verify all the options in Report Type drop down");
		boolean isMatch = false;
		int i = 0;
		ddlReportType.click();
		Select se = new Select(ddlReportType);
		List<WebElement> options = se.getOptions();
		for (DGNEnum.MyAccountReportsOptions opt : DGNEnum.MyAccountReportsOptions.values()) {
			if (options.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		ddlReportType.click();
		return isMatch;

	}

	public boolean isRunButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify the Run button");
		return btnRun.isDisplayed();
	}

	public void clickRunButton() {
		extentTest.log(Status.INFO, "Verify the Run button");
		btnRun.click();

	}

	public void clickReportOptions(String option) {
		extentTest.log(Status.INFO, "Clicking the 'Company Download Detail' option in Report Type drop down");
		ddlReportType.click();
		Select se = new Select(ddlReportType);
		List<WebElement> options = se.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(option)) {
				options.get(i).click();
				ddlReportType.click();
			}
		}
	}

	public boolean isUserDropDwnDisplayed() {
		extentTest.log(Status.INFO, "Verify if User drop down is displayed");
		return userDrop.isDisplayed();
	}

	public AdminReportsPage clickOnUsersDrop() {
		extentTest.log(Status.INFO, "Clicking on the User drop down");
		userDrop.click();
		return new AdminReportsPage(driver);
	}

	public boolean isFromDateDisplayed() {
		extentTest.log(Status.INFO, "Verify if 'From Date' drop down is displayed");
		return txtFromDate.isDisplayed();
	}

	public String getFromDateText() {
		extentTest.log(Status.INFO, "Get the selected 'From Date' ");
		return txtFromDate.getAttribute("value").trim();
	}

	public boolean isToDateDropDwnDisplayed() {
		extentTest.log(Status.INFO, "Verify if 'To Date' drop down is displayed");
		return txtToDate.isDisplayed();
	}

	public String selectLastWeekBackCreatedBetween() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.add(Calendar.DATE, -1);

		String date = CommonUtils.getUSADateFormat(cal.getTime());
		return date;
	}

	public boolean isSelectAllChecked() {
		extentTest.log(Status.INFO, "Verify if Select All checked");
		try {
			return chkSelectAll.getAttribute("checked").equals("true");
		} catch (Exception ex) {
			return false;
		}
	}

	public String checkFirstUser() {
		extentTest.log(Status.INFO, "Check first user");
		chkFirstUser.click();
		return lblFirstUser.getText().trim();
	}

	public String getSelectedUserInTable() {
		extentTest.log(Status.INFO, "Get the user name from the user info grid");
		return gridUserName.getText().trim();
	}

	public boolean isAllUserChecked() {
		extentTest.log(Status.INFO, "Verify if all users selected");
		Boolean isChecked = false;
		for (int i = 0; i < chkUserList.size(); i++) {
			if (chkUserList.get(i).getAttribute("checked").equals("true")) {
				isChecked = true;
			} else {
				isChecked = false;
			}
		}
		return isChecked;
	}

	public void checkRandomUser(int index) {
		extentTest.log(Status.INFO, "Click user from the drop list");
		chkUserList.get(index).click();
	}

	public boolean isRandomUserChecked(int index) {
		extentTest.log(Status.INFO, "Verify if user from the drop list checked");
		try {
			return chkUserList.get(index).getAttribute("checked") != null;
		} catch (Exception ex) {
			return false;
		}
	}

	public void checkSelectAll() {
		extentTest.log(Status.INFO, "Verify if select all selected");
		SeleniumUtils.isVisible(chkSelectAll, driver);
		SeleniumUtils.isClickable(chkSelectAll, driver);
		chkSelectAll.click();
	}

	public boolean isFirstChkSelectAllInDrp() {
		extentTest.log(Status.INFO, "Verify if select all is the first check box in the user drop down");
		try {
			return userDrpChkTextList.get(0).getText().trim().equals("Select All");
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean checkPageBackgroud() {
		String expectedBackgroudValue = "rgba(0, 0, 0, 0)";
		return myDownloadBackgroundPage.getCssValue("background").trim().contains(expectedBackgroudValue);
	}
}
