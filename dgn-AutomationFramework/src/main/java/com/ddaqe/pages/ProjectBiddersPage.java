package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class ProjectBiddersPage extends ProjectCommonContainerPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Low Bidders')]//div[contains(@class,'leftPlan')]")
	private List<WebElement> biddersTotalCount;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Planholders')]//div[contains(@class,'leftPlan')]")
	private List<WebElement> planholdersTotalCount;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Low Bidders')]//div[contains(@class,'generalPlan')]//div[contains(@class,'leftPlan')]")
	private List<WebElement> biddersLeftPlan;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Low Bidders')]//div[contains(@class,'generalPlan')]//div[contains(@class,'rightPlan')]")
	private List<WebElement> biddersRightPlan;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Planholders')]//div[contains(@class,'generalPlan')]//div[contains(@class,'leftPlan')]")
	private List<WebElement> planholdersLeftPlan;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Planholders')]//div[contains(@class,'generalPlan')]//div[contains(@class,'rightPlan')]")
	private List<WebElement> planholdersRightPlan;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Low Bidders')]//div[contains(@class,'generalPlan')and not(contains(@style,'display: none'))]/div[1]")
	private List<WebElement> biddersHeader;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//div[contains(@class,'Planholders')]//div[contains(@class,'generalPlan')and not(contains(@style,'display: none'))]/div[1]")
	private List<WebElement> planholdersHeader;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']/select[contains(@class,'placholder')]")
	private WebElement showDrpdown;

	@FindBy(how = How.XPATH, using = "//div[@class='generalPlan']//div[contains(@class,'leftPlan')]//div[1]")
	private WebElement lowBidderCompanyName;

	@FindBy(how = How.XPATH, using = "//div[@class='generalPlan']//div[contains(@class,'leftPlan')]//div[2]")
	private WebElement lowBidderContactName;

	@FindBy(how = How.XPATH, using = "//div[@class='generalPlan']//div[contains(@class,'leftPlan')]//div[3]")
	private WebElement lowBidderAddress;

	@FindBy(how = How.XPATH, using = "//div[@class='generalPlan']//div[contains(@class,'leftPlan')]//div[4]//a")
	private WebElement lowBidderWebsite;

	@FindBy(how = How.XPATH, using = "//div[@class='generalPlan']//div[contains(@class,'rightPlan')]//div[1]/span[2]")
	private WebElement lowBidderPhone;

	@FindBy(how = How.XPATH, using = "//div[@class='generalPlan']//div[contains(@class,'rightPlan')]//div[3]/a")
	private WebElement lowBidderEmail;

	@FindBy(how = How.ID, using = "rbCSV")
	private WebElement csvRadioBtn;
	@FindBy(how = How.ID, using = "drpCSVTemplateName")
	private WebElement selectATemplateDropDown;
	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadProjectPopUpCSVFields;

	@FindBy(how = How.XPATH, using = ".//*[@id='lsTemplateFields']/option")
	private List<WebElement> downloadProjectPopUpCSVAvailableFields;

	@FindBy(how = How.ID, using = "btnAdd")
	private WebElement clickOnAddButtonOnPopUPFrame;

	@FindBy(how = How.XPATH, using = ".//*[@id='listRemove']/option")
	private List<WebElement> downloadProjectPopUpCSVSelectedFields;

	@FindBy(how = How.ID, using = "tbTemplateName")
	private WebElement enterTeplateName;
	@FindBy(how = How.ID, using = "btnCreate")
	private WebElement clickOncraeteButton;

	@FindBy(how = How.ID, using = "rbSingleFile")
	private WebElement saveToSingleFileRadioBtn;
	@FindBy(how = How.CSS, using = "#drpCSVTemplateName option")
	private List<WebElement> downloadProjectPopUpCSVTemplateList;

	@FindBy(how = How.ID, using = "downloadProjectSubmit")
	private WebElement downloadButton;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/delayed-load-with-fade.gif']")
	private List<WebElement> delayImageDialog;

	@FindBy(how = How.XPATH, using = "//select[@id='drpCSVTemplateName']//option[text()='-- CREATE TEMPLATE --']")
	private WebElement selectCreatNewTemplateOption;

	public ProjectBiddersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Bidders Page");
	}

	public Integer getBiddersTotalCount() {
		extentTest.log(Status.INFO, "Get the 'Low Bidders' total count");
		return biddersTotalCount.size();
	}

	public Integer getPlanholdersTotalCount() {
		extentTest.log(Status.INFO, "Get the 'Planholders' total count");
		return planholdersTotalCount.size();
	}

	public Boolean isLowBiddersInTabular() {
		extentTest.log(Status.INFO, "Verify if the 'Low Bidders' is displayed in tabular format");
		return biddersLeftPlan.size() == biddersRightPlan.size();
	}

	public Boolean isPlanholdersInTabular() {
		extentTest.log(Status.INFO, "Verify if the 'Planholders' is displayed in tabular format");
		return planholdersLeftPlan.size() == planholdersRightPlan.size();
	}

	public boolean compareShowDrpOptionsWithHeaders() {
		extentTest.log(Status.INFO,
				"Verify if the show dropdown contains all the 'Low Bidders' headers as well as 'Planholders' headers");

		Select se = new Select(showDrpdown);
		List<String> biddersHeaderList = CommonUtils.getListFromWebElements(biddersHeader);
		CommonUtils.IterateList(biddersHeaderList);

		List<String> planholdersHeaderList = CommonUtils.getListFromWebElements(planholdersHeader);
		CommonUtils.IterateList(planholdersHeaderList);

		List<String> mergeList = new ArrayList<>();
		for (int i = 0; i < biddersHeaderList.size(); i++) {
			mergeList.add(biddersHeaderList.get(i).replaceAll("\\(A\\)", "").trim().replaceAll("\\(B\\)", "").trim());
		}

		for (int i = 0; i < planholdersHeaderList.size(); i++) {
			mergeList.add(
					planholdersHeaderList.get(i).replaceAll("\\(A\\)", "").trim().replaceAll("\\(B\\)", "").trim());
		}

		mergeList = CommonUtils.sortWebElements(mergeList, true);
		CommonUtils.IterateList(mergeList);
		List optionsList = CommonUtils.getListFromWebElements(se.getOptions());
		optionsList.remove(0);
		CommonUtils.IterateList(optionsList);

		return CommonUtils.CompareTwoList(optionsList, mergeList);

	}

	public void clickShowDrpDown() {
		extentTest.log(Status.INFO, "Click the show dropdown");
		showDrpdown.click();
	}

	public void selectShowDrpDownOptions() {
		extentTest.log(Status.INFO, "Select the options from the show dropdown");
		Select se = new Select(showDrpdown);
		for (int i = 2; i < se.getOptions().size(); i++) {
			se.getOptions().get(i).click();
			break;
		}

	}

	public boolean isSelectedHeaderListFiltered() {

		Boolean isSelectedMatch = false;
		Select se = new Select(showDrpdown);
		String selectedHeaderLabel = se.getFirstSelectedOption().getText();
		List<String> biddersHeaderList = CommonUtils.getListFromWebElements(biddersHeader);
		CommonUtils.IterateList(biddersHeaderList);

		List<String> planholdersHeaderList = CommonUtils.getListFromWebElements(planholdersHeader);
		CommonUtils.IterateList(planholdersHeaderList);

		if (biddersHeaderList.size() > 0) {
			for (int i = 0; i < biddersHeaderList.size(); i++) {
				if (biddersHeaderList.get(i).replaceAll("\\(A\\)", "").trim().replaceAll("\\(B\\)", "").trim()
						.equals(selectedHeaderLabel)) {
					isSelectedMatch = true;

				} else {
					isSelectedMatch = false;
				}
			}
		} else {
			if (planholdersHeaderList.size() > 0) {
				for (int i = 0; i < planholdersHeaderList.size(); i++) {
					if (planholdersHeaderList.get(i).replaceAll("\\(A\\)", "").trim().replaceAll("\\(B\\)", "").trim()
							.equals(selectedHeaderLabel)) {
						isSelectedMatch = true;

					} else {
						isSelectedMatch = false;
					}
				}
			}
		}

		return isSelectedMatch;

	}

	public List<String> getShowDrpDownOptions() {
		extentTest.log(Status.INFO, "Get all the options from the show dropdown");
		Select se = new Select(showDrpdown);
		return CommonUtils.getListFromWebElements(se.getOptions());
	}

	public boolean isLowBidderCompanyNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the low bidder company name is displayed");
		try {
			return lowBidderCompanyName.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLowBidderContactNameDisplayed() {
		extentTest.log(Status.INFO, "Verify if the low bidder contact name is displayed");
		try {
			return lowBidderContactName.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLowBidderAddressDisplayed() {
		extentTest.log(Status.INFO, "Verify if the low bidder Address is displayed");
		try {
			return lowBidderAddress.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLowBidderWebsiteClickable() {
		extentTest.log(Status.INFO, "Verify if the low bidder website is displayed as a link");
		try {
			return SeleniumUtils.isClickable(lowBidderWebsite, driver);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLowBidderPhoneDisplayed() {
		extentTest.log(Status.INFO, "Verify if the low bidder Phone is displayed");
		try {
			return lowBidderPhone.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLowBidderEmailClickable() {
		extentTest.log(Status.INFO, "Verify if the low bidder email is displayed as a link");
		try {
			return SeleniumUtils.isClickable(lowBidderEmail, driver);
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnCSVRadioBtn() {
		extentTest.log(Status.INFO, "Click on CSV Radio Button.");
		csvRadioBtn.click();
		SeleniumUtils.isVisible(selectATemplateDropDown, driver);
	}

	public List<String> getFieldList() {
		extentTest.log(Status.INFO, "Get Available Fileld List.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedField : downloadProjectPopUpCSVFields) {
			selectedFilelds.add(selectedField.getText());
		}
		return selectedFilelds;
	}

	public void selectNewFieldByCTRL() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(downloadProjectPopUpCSVAvailableFields.get(0))
				.click(downloadProjectPopUpCSVAvailableFields.get(3))
				.click(downloadProjectPopUpCSVAvailableFields.get(4))
				.click(downloadProjectPopUpCSVAvailableFields.get(6)).keyUp(Keys.CONTROL).build().perform();
	}

	public void clickOnAddBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOnAddButtonOnPopUPFrame.click();
	}

	public List<String> getSelectedFieldList() {
		extentTest.log(Status.INFO, "Get publish date range list.");
		List<String> selectedFilelds = new ArrayList<String>();
		for (WebElement selectedFieldPopUp : downloadProjectPopUpCSVSelectedFields) {
			selectedFilelds.add(selectedFieldPopUp.getText());
		}
		return selectedFilelds;
	}

	public void getTemplateName() {
		extentTest.log(Status.INFO, "enter valid template name.");
		String templateName = "MyNewCSVTemplate";
		enterTeplateName.sendKeys(templateName);
	}

	public void clickOnCreateBtn() {
		extentTest.log(Status.INFO, "Click On Select Template DropDown.");
		clickOncraeteButton.click();
	}

	public void clickOnSaveToSingleFilesRadioButton() {
		extentTest.log(Status.INFO, "Click on Save to single files radio button.");
		saveToSingleFileRadioBtn.click();
	}

	public List<String> getTemplateList() {
		extentTest.log(Status.INFO, "get default template list");
		List<String> templateList = new ArrayList<String>();
		for (WebElement template : downloadProjectPopUpCSVTemplateList) {
			templateList.add(template.getText());
		}
		return templateList;
	}

	public void clickOnDownloadBtn() {
		extentTest.log(Status.INFO, "Click on Download Button.");
		downloadButton.click();
		SeleniumUtils.isLoadingIconInvisible(delayImageDialog, driver);
	}

	public void selectCreateNewTemplate() {
		extentTest.log(Status.INFO, "click On Creat new template.");
		selectCreatNewTemplateOption.click();
	}
}
