package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;

public class CompanyNotesPage extends CompanyCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl01_lblNote")
	private WebElement notesText;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl01_lblType")
	private WebElement notesType;

	@FindBy(how = How.CLASS_NAME, using = "notesContentContainer")
	private WebElement notesGrid;

	@FindBy(how = How.ID, using = "lnkAddNotes")
	private WebElement addNotesMenu;

	@FindBy(how = How.ID, using = "btnNotesActions")
	private WebElement notesActionDropdown;

	@FindBy(how = How.ID, using = "lnkEditNotes")
	private WebElement editNotesMenu;

	@FindBy(how = How.ID, using = "lnkDeleteNotes")
	private WebElement deleteNotesMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_NotesAction1_lnkViewNotes")
	private WebElement viewNotesMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_NotesAction1_lnkPrintNotes")
	private WebElement printNotesMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl01_ckbSelect")
	private WebElement firstNoteCheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl02_ckbSelect")
	private WebElement secondNoteCheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl01_lblDate")
	private WebElement notesDate;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl01_lblUserName")
	private WebElement notesUser;

	@FindBy(how = How.XPATH, using = "//div[@class='breadcrumb-container clearfix']/span[last()]")
	private WebElement lastBreadCrumb;

	@FindBy(how = How.CLASS_NAME, using = "importantComapanyUpdates")
	private WebElement importantCompanyUpdatesSection;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl00_ckbHeaderSelectAll")
	private WebElement selectAllCheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyOverview_divCompanyTrackedSection")
	private WebElement companyOverviewSection;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_ddlType")
	private WebElement selectNoteTypeFilter;

	@FindBy(how = How.XPATH, using = ".//*[@class='notesContentContainer']//span[contains(@id,'Date')]")
	private List<WebElement> dateColumnValues;

	@FindBy(how = How.XPATH, using = ".//*[@class='notesContentContainer']//span[contains(@id,'UserName')]")
	private List<WebElement> userColumnValues;

	@FindBy(how = How.XPATH, using = ".//*[@class='notesContentContainer']//span[contains(@id,'Type')]")
	private List<WebElement> typeColumnValues;

	@FindBy(how = How.XPATH, using = ".//*[@class='notesContentContainer']//span[contains(@id,'Note')]")
	private List<WebElement> noteColumnValues;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl00_lnkHeaderDate")
	private WebElement dateColumnHeader;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl00_lnkHeaderUser")
	private WebElement userColumnHeader;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl00_lnkHeaderType")
	private WebElement typeColumnHeader;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_notesSection_rptNotes_ctl00_lnkHeaderNote")
	private WebElement noteColumnHeader;

	public CompanyNotesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Company Notes Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getRecentNoteFromNotesTab() {
		extentTest.log(Status.INFO, "Get Recent notes test from notes tab");
		return notesText.getText();

	}

	public void clickDateColumnHeader() {
		extentTest.log(Status.INFO, "Clicking the date column header");
		dateColumnHeader.click();
	}

	public void clickUserColumnHeader() {
		extentTest.log(Status.INFO, "Clicking the user column Header");
		userColumnHeader.click();
	}

	public void clickTypeColumnHeader() {
		extentTest.log(Status.INFO, "Clicking the type column Header");
		typeColumnHeader.click();
	}

	public void clickNoteColumnHeader() {
		extentTest.log(Status.INFO, "Clicking the note Column Header");
		noteColumnHeader.click();
	}

	public String getRecentNoteDateFromNotesTab() {
		extentTest.log(Status.INFO, "Get recent Note Date from notes tab");
		return notesDate.getText();
	}

	public String getRecentNoteTypeNotesTab() {
		extentTest.log(Status.INFO, "Get Recent notes type from notes tab");
		return notesType.getText();

	}

	public String getRecentNoteUserNotesTab() {
		extentTest.log(Status.INFO, "Get Recent notes User/email from notes tab");
		return notesUser.getText();

	}

	public Boolean isNotesGridDisplayed() {
		extentTest.log(Status.INFO, "Check if notes Grid is displayed");
		return notesGrid.isDisplayed();
	}

	public void clickSelectAllCheckbox() {
		extentTest.log(Status.INFO, "Clicking the selectAll checkbox");
		selectAllCheckbox.click();
	}

	public Boolean isNotesActionsDropdownDisplayed() {
		extentTest.log(Status.INFO, "Check if Notes Action dropdown is displayed");
		return notesActionDropdown.isDisplayed();
	}

	public NotesPage mouseoverNotesActionandClickEditNotes() {
		Actions action = new Actions(driver);
		action.moveToElement(notesActionDropdown).perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(editNotesMenu));
		action.moveToElement(editNotesMenu).click().perform();
		return new NotesPage(driver);
	}

	public void mouseoverNotesActionandClickDeleteNotes() {
		Actions action = new Actions(driver);
		action.moveToElement(notesActionDropdown).perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(deleteNotesMenu));
		action.moveToElement(deleteNotesMenu).click().perform();
	}

	public NotesPage mouseoverNotesActionandClickViewNotes() {
		Actions action = new Actions(driver);
		action.moveToElement(notesActionDropdown).perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(viewNotesMenu));
		action.moveToElement(viewNotesMenu).click().perform();
		return new NotesPage(driver);
	}

	public void mouseoverNotesActionandClickPrintNotes() {
		Actions action = new Actions(driver);
		action.moveToElement(notesActionDropdown).perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printNotesMenu));
		action.moveToElement(printNotesMenu).click().perform();
	}

	public Boolean checkforValidationMessage(String message) {
		return driver.getPageSource().contains(message);
	}

	public void clickFirstNoteCheckbox() {
		extentTest.log(Status.INFO, "Clicking the checkbox to select first note on Projects Note page");
		firstNoteCheckbox.click();
	}

	public void clickSecondNoteCheckbox() {
		extentTest.log(Status.INFO, "Clicking the checkbox to select first note on Projects Note page");
		secondNoteCheckbox.click();

	}

	public String getURL() {
		extentTest.log(Status.INFO, "Get URL");
		return driver.getCurrentUrl();
	}

	public void mouseoverNotesActionandVerifyDropdownMenu() throws InterruptedException {
		Actions action = new Actions(driver);
		action.clickAndHold(notesActionDropdown).build().perform();
		Thread.sleep(2000);
		Assert.assertTrue(viewNotesMenu.isDisplayed());
		Assert.assertTrue(deleteNotesMenu.isDisplayed());
		Assert.assertTrue(printNotesMenu.isDisplayed());
		Assert.assertTrue(editNotesMenu.isDisplayed());
		Assert.assertTrue(addNotesMenu.isDisplayed());

	}

	public boolean verifyProjectNotesBreadCrumb() {

		String lastBreadcrumb = lastBreadCrumb.getText();
		if (lastBreadcrumb.equals("Notes")) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isProjectOverViewSectionDisplayed() {
		extentTest.log(Status.INFO, "Check if Project Overview Section is displayed");

		return companyOverviewSection.isDisplayed();
	}

	public Boolean isimpProjectUpdatesSectionDisplayed() {
		extentTest.log(Status.INFO, "Check if Important Project updates Section is displayed");

		return importantCompanyUpdatesSection.isDisplayed();
	}

	public Boolean isSelectAllSChkBoxDisplayed() {
		extentTest.log(Status.INFO, "Check if Select all checkbox to select all notes is displayed");

		return selectAllCheckbox.isDisplayed();
	}

	public boolean verifyNotesDataGrid() {

		WebElement table = driver.findElement(By.className("notesContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			Assert.assertTrue(cols.get(0).isDisplayed(), "Check if individual Row checkbox is displayed");

			for (int j = 1; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
					extentTest.log(Status.INFO, cols.get(j).getText());
				} else {
					extentTest.log(Status.INFO, "The notes grid is not displaying data in Row" + i + "Column" + j);
					return false;
				}
			}
		}

		return true;

	}

	public boolean selectNoteAddedyOtherUser(String rowDetails) {

		WebElement table = driver.findElement(By.className("notesContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if (!(actualStr.toLowerCase().contains(rowDetails.toLowerCase()))) {
				cols.get(0).click();

				return true;
			}
		}

		return false;
	}

	public boolean isNoteAddedyOtherUserPresent(String rowDetails) {

		WebElement table = driver.findElement(By.className("notesContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if (!(actualStr.toLowerCase().contains(rowDetails.toLowerCase()))) {
				return true;
			}
		}

		return false;
	}

	public int getNoteUserCount(String noteType, String User) {

		WebElement table = driver.findElement(By.className("notesContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int Count = 0;
		for (int i = 1; i < rows.size(); i++) {

			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			if (noteType.toLowerCase().equals("private")) {
				if (cols.get(2).getText().toLowerCase().equals(User.toLowerCase())) {
					if (cols.get(3).getText().toLowerCase().equals(noteType.toLowerCase())) {
						Count++;
					}

				}
			} else {
				if (cols.get(3).getText().toLowerCase().equals(noteType.toLowerCase())) {
					Count++;
				}

			}

		}
		return Count;

	}

	public boolean selectNoteAddedyByLoggedInUser(String rowDetails) {

		WebElement table = driver.findElement(By.className("notesContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		String actualStr = "";
		for (int i = 1; i < rows.size(); i++) {
			actualStr = "";
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

			for (int j = 0; j < cols.size(); j++) {
				if (!cols.get(j).getText().equals("")) {
					actualStr = actualStr + cols.get(j).getText() + "&&";
				} else {
					continue;
				}
			}
			if ((actualStr.toLowerCase().contains(rowDetails.toLowerCase()))) {
				cols.get(0).click();

				return true;
			}
		}

		return false;
	}

	public int getDisplayedNotesCount() {

		WebElement table = driver.findElement(By.className("notesContainer"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows.size() - 1;

	}

	public void selectNoteTypeFiler(String noteType) {
		Select dropdown = new Select(selectNoteTypeFilter);
		dropdown.selectByVisibleText(noteType);
	}

	public boolean verifyDateColumnSorting(Boolean sortOrder) {
		List<String> listElements = CommonUtils.getListFromWebElements(dateColumnValues);
		List<String> listArc = CommonUtils.sortWebElements(listElements, sortOrder);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyUserColumnSorting(Boolean sortOrder) {
		List<String> listElements = CommonUtils.getListFromWebElements(userColumnValues);
		List<String> listArc = CommonUtils.sortWebElements(listElements, sortOrder);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyTypeColumnSorting(Boolean sortOrder) {
		List<String> listElements = CommonUtils.getListFromWebElements(typeColumnValues);
		List<String> listArc = CommonUtils.sortWebElements(listElements, sortOrder);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyNoteColumnSorting(Boolean sortOrder) {
		List<String> listElements = CommonUtils.getListFromWebElements(noteColumnValues);
		List<String> listArc = CommonUtils.sortWebElements(listElements, sortOrder);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyDateColumnSortingArrowSymbol(Boolean sortOrder) {
		if (sortOrder == true) {
			return dateColumnHeader.getCssValue("background-image").contains("arrow-sort-down.png");
		}
		if (sortOrder == false)
			return dateColumnHeader.getCssValue("background-image").contains("arrow-sort-up.png");
		return false;

	}

	public boolean verifyNoteColumnSortingArrowSymbol(Boolean sortOrder) {
		if (sortOrder == true) {
			return noteColumnHeader.getCssValue("background-image").contains("arrow-sort-down.png");
		}
		if (sortOrder == false)
			return noteColumnHeader.getCssValue("background-image").contains("arrow-sort-up.png");
		return false;

	}

	public boolean verifyUserColumnSortingArrowSymbol(Boolean sortOrder) {
		if (sortOrder == true) {
			return userColumnHeader.getCssValue("background-image").contains("arrow-sort-down.png");
		}
		if (sortOrder == false)
			return userColumnHeader.getCssValue("background-image").contains("arrow-sort-up.png");
		return false;

	}

	public boolean verifyTypeColumnSortingArrowSymbol(Boolean sortOrder) {
		if (sortOrder == true) {
			return typeColumnHeader.getCssValue("background-image").contains("arrow-sort-down.png");
		}
		if (sortOrder == false)
			return typeColumnHeader.getCssValue("background-image").contains("arrow-sort-up.png");
		return false;

	}

}
