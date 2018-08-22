package com.ddaqe.pages;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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

public class MyAccountCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[@class='batchRightSubMenu'][1]//a")
	private List<WebElement> AdminToolsMenu;

	@FindBy(how = How.XPATH, using = "//div[@class='batchRightSubMenu'][2]//a")
	private List<WebElement> AccountToolsMenu;

	@FindBy(how = How.XPATH, using = "//li[contains(@id,'ctl00_ucTopTabMenu_li')]")
	private List<WebElement> commonHeaderTabs;

	@FindBy(how = How.ID, using = "licensedUser")
	private WebElement licenseNumber;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'breadcrumb')]//span")
	private WebElement breadCrumb;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_ucFooter_logoDiv']/img")
	private WebElement footerLogo;

	public MyAccountCommonContainerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the My Accounts - Users Page");
	}

	/**
	 * 
	 * @param resultPerPageElement
	 *            : hold the identifier for the Result per page dropdown
	 * @return : return the result of the option list present in the dropdown.
	 */
	public static boolean checkResultPerPageDropdownOption(WebElement resultPerPageElement) {
		List<String> pageOption = new ArrayList<>();
		for (DGNEnum.resultPerPageOptionList optionEnumList : DGNEnum.resultPerPageOptionList.values()) {
			pageOption.add(String.valueOf(optionEnumList.getValue()));
		}
		Select resultPerPage = new Select(resultPerPageElement);
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(resultPerPage.getOptions()), pageOption);
	}

	/**
	 * 
	 * @param element
	 *            : hold the list of element which contain the Type filter
	 * @return : return the result of comparision
	 */
	public static boolean checkTableTypeHeaderContent(WebElement element) {
		List<String> pageOption = new ArrayList<>();
		for (DGNEnum.typeFilterAccessSpecifier spec : DGNEnum.typeFilterAccessSpecifier.values()) {
			pageOption.add(spec.getTypeAccessSpec());
		}

		Select typeOption = new Select(element);
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(typeOption.getOptions()), pageOption);
	}

	/**
	 * 
	 * @param element
	 *            : hold the list of element which contain the Type filter
	 * @return : return the result of comparison
	 */
	public static boolean checkUsersDropdownOptionOnShareUser(WebElement element) {
		List<String> userOptions = new ArrayList<>();
		for (DGNEnum.usersOptionsLists user : DGNEnum.usersOptionsLists.values()) {
			userOptions.add(user.getUserOptions());
		}

		Select usersOption = new Select(element);
		return CommonUtils.CompareTwoList(CommonUtils.getListFromWebElements(usersOption.getOptions()), userOptions);
	}

	/**
	 * 
	 * @param element
	 *            : hold the list of webelement.
	 * @return : the status result
	 */
	public static boolean checkStatusOfEachRecord(List<WebElement> element) {
		boolean result = true;
		List<String> actualList = CommonUtils.getListFromWebElements(element);
		for (String actual : actualList) {
			switch (actual) {
			case "Private":
			case "Public":
			case "Shared":
				break;
			default:
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param element
	 *            : hold the webelement for type dropdown present in edit
	 *            dialog.
	 * @return : the status result
	 */
	public static boolean checkOptionOfShareEditTypeDropdown(WebElement element) {
		boolean result = true;
		Select typeOption = new Select(element);
		List<String> actualList = CommonUtils.getListFromWebElements(typeOption.getOptions());
		for (String actual : actualList) {
			switch (actual) {
			case "Private":
			case "Public":
			case "Shared":
				break;
			default:
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param element
	 *            : hold the webelement for type dropdown present in edit
	 *            dialog.
	 * @return : the status result
	 */
	public static boolean checkOptionOfEditTypeDropdown(WebElement element) {
		boolean result = true;
		Select typeOption = new Select(element);
		List<String> actualList = CommonUtils.getListFromWebElements(typeOption.getOptions());
		for (String actual : actualList) {
			switch (actual) {
			case "Private":
			case "Public":
				break;
			default:
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param element
	 *            : hold the dropdown element for tracking name
	 * @param element
	 *            : hold the dropdown element for tracking name
	 * @param element
	 *            : hold the dropdown element for tracking name
	 * @return : return the list compare result of sorting in ascending order
	 */
	public static boolean verifySortingTrackingName(List<WebElement> element) {
		return CommonUtils.CompareTwoList(sortWebElementsMyAccount(getTrackingListFromWebElements(element), true),
				getTrackingListFromWebElements(element));
	}

	/**
	 * 
	 * @param element
	 *            : hold the webelement for the actual breadcrum to check.
	 * @param expectedBreadCrum
	 *            : hold the string of exp breadcrum
	 * @return : return boolean result.
	 */
	public static boolean verifyBreadCrumb(WebElement element, String expectedBreadCrum) {
		return element.getText().toUpperCase().equals(expectedBreadCrum.toUpperCase());
	}

	/**
	 * 
	 * @param compareString
	 *            : Check for the string present or not.
	 * @param list
	 *            : hold the list of name to check
	 * @return : return the result.
	 */

	public static boolean checkEditStringPresentInTableNameList(String compareString, List<WebElement> list) {
		List<String> listString = CommonUtils.getListFromWebElements(list);
		boolean resultFlag = false;
		for (String eachString : listString) {
			if (eachString.toUpperCase().trim().equals(compareString.toUpperCase().trim())) {
				resultFlag = true;
				break;
			}
		}
		return resultFlag;
	}

	public static List<String> getTrackingListFromWebElements(List<WebElement> webElements) {
		List<String> skipList = new ArrayList<>();
		for (DGNEnum.trackingListSkipString trackName : DGNEnum.trackingListSkipString.values()) {
			skipList.add(trackName.getTrackingName());
		}
		List<String> listElements = new ArrayList<String>();
		for (WebElement element : webElements) {
			if (!(element.getText().equals(skipList.get(0))) && !(element.getText().equals(skipList.get(1)))
					&& !(element.getText().equals(skipList.get(2))) && !(element.getText().equals(skipList.get(3)))) {
				listElements.add(element.getText());
			}
		}
		return listElements;
	}

	// To Sort the List in the sort order (Ascending/Descending) as specified
	public static List<String> sortWebElementsMyAccount(List<String> webElements, boolean sortOrder) {
		List<String> listElements = webElements;
		if (sortOrder) {
			Collections.sort(listElements, Collator.getInstance(Locale.US));
			Collections.sort(listElements, String.CASE_INSENSITIVE_ORDER);
		} else {
			Collections.sort(listElements, Collections.reverseOrder());
		}
		return listElements;
	}

	public static String returnFirstTrackingNameWithShareLink(List<WebElement> element) {
		List<String> list = CommonUtils.getListFromWebElements(element);
		String returnString = "";

		List<String> skipList = new ArrayList<>();
		for (DGNEnum.trackingListSkipString trackName : DGNEnum.trackingListSkipString.values()) {
			skipList.add(trackName.getTrackingName());
		}

		for (String name : list) {
			if (!(name.toUpperCase().equals(skipList.get(0).toUpperCase()))
					&& !(name.toUpperCase().equals(skipList.get(1).toUpperCase()))
					&& !(name.toUpperCase().equals(skipList.get(2).toUpperCase()))
					&& !(name.toUpperCase().equals(skipList.get(3).toUpperCase()))) {
				returnString = name;
				break;
			}
		}

		return returnString;
	}

	public static void shareSpecificUser(List<WebElement> element, List<WebElement> shareLinkList, String userName) {
		int count = 0;
		for (String actualUserName : CommonUtils.getListFromWebElements(element)) {
			if (actualUserName.toUpperCase().contains(userName.toUpperCase())) {
				break;
			} else {
				count++;
			}
		}
		shareLinkList.get(count).click();
	}

	public static void UnshareSpecificUser(List<WebElement> element, List<WebElement> shareLinkList, String userName) {
		int count = 0;
		for (String actualUserName : CommonUtils.getListFromWebElements(element)) {
			if (actualUserName.toUpperCase().contains(userName.toUpperCase())) {
				break;
			} else {
				count++;
			}
		}
		shareLinkList.get(count).isDisplayed();
	}

	public void clickElementWithTextFromAdminTools(String elementWithText) {
		for (int i = 0; i < AdminToolsMenu.size(); i++) {
			if (AdminToolsMenu.get(i).getText().contains(elementWithText)) {
				AdminToolsMenu.get(i).click();
				break;
			}
		}
	}

	public boolean verifyMyAccountsAdminToolsOptions() {
		extentTest.log(Status.INFO, "Verify all the options in the My Accounts - Admin Tools");
		boolean isMatch = false;
		int i = 0;
		for (DGNEnum.MyAccountsAdminToolsOptions opt : DGNEnum.MyAccountsAdminToolsOptions.values()) {
			if (AdminToolsMenu.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	public boolean verifyMyAccountsAccountToolsOptions() {
		extentTest.log(Status.INFO, "Verify all the options in the My Accounts - Account Tools");
		boolean isMatch = false;
		int i = 0;
		for (DGNEnum.MyAccountsAccountToolsOptions opt : DGNEnum.MyAccountsAccountToolsOptions.values()) {
			if (AccountToolsMenu.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	public boolean verifyCommonTabHighlighted() {
		extentTest.log(Status.INFO, "Verify if the common header tabs highlighted");
		for (int i = 0; i < commonHeaderTabs.size(); i++) {
			if (commonHeaderTabs.get(i).getAttribute("class").equals("inactive")) {
				return false;
			}
		}
		return true;

	}

	public boolean isLicenseNumberDisplayed() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down");
		return licenseNumber.isDisplayed();
	}

	public void switchToFrame() {
		driver.switchTo().frame("iAdminControl");
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public boolean isBreadCrumbCorrect(String name) {
		extentTest.log(Status.INFO, "Get the breadcrumb of the users page");
		try {
			return breadCrumb.getText().contains(name);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isFooterLogoDodge() {
		extentTest.log(Status.INFO, "Verify the footer logo");
		return footerLogo.getAttribute("alt").trim().equalsIgnoreCase("Dodge Data & Analytics");
	}

}
