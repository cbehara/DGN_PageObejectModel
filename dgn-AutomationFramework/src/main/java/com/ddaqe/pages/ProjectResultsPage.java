package com.ddaqe.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
import com.ddaqe.constants.DGNProjectGridConstant;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.CoreFrameworkException;
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.DatePickerUtils;
import com.ddaqe.utils.SeleniumUtils;

public class ProjectResultsPage {

	private WebDriver driver;
	private ExtentTest extentTest;
	private String selectedTrackingList;
	private String selectedSpecAlertProgram;

	@FindBy(how = How.XPATH, using = ".//errorHoldar>div")
	private WebElement errorOnBlankChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_dvMatchedDocButton']/div[2]")
	private WebElement labelOnSpecPage;

	@FindBy(how = How.XPATH, using = "//*[@class='MfrCheckboxLabel' and text()='Basis of Design']//..//..//input[@id='dbrdChkBod' and @type='checkbox']")
	private WebElement mfrVizBasicOfDesignChkbox;

	@FindBy(how = How.XPATH, using = "//label[@for='dbrdChkBod']")
	private WebElement mfrVizBasicOfDesignChkboxLabel;

	@FindBy(how = How.XPATH, using = "//span[text()='Manufacturers'][@class='chartHead']/following::a[@original-title='Customize Manufacturers']")
	private List<WebElement> customizeManufacturersLink;

	@FindBy(how = How.XPATH, using = "//ul[@id='mfrmList']//li[contains(@data-key,'MFG')]")
	private List<WebElement> allMFRInCustomizeMFRPopup;

	@FindBy(how = How.XPATH, using = "//ul[@id='mSelected']//li[contains(@data-key,'MFG')]")
	private List<WebElement> selectedMFRInCustomizeMFRPopup;

	@FindBy(how = How.XPATH, using = "//ul[@id='mSelected']//li[contains(@data-key,'MFG')]/span[@class='fa fa-close mfrm-delete']")
	private List<WebElement> removeSelectedMFRInCustomizeMFRPopup;

	@FindBy(how = How.XPATH, using = "//div[@class='mfrm-save']")
	private WebElement customizeMFRPopupSaveButton;

	@FindBy(how = How.XPATH, using = "//span[@class= 'mfrm-header-title mfrm-title']")
	private WebElement customizeMFRPopupTitle;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mfrmInput']")
	private WebElement customizeMFRPopupFindAManufacturerTextBox;
	
	@FindBy(how = How.XPATH, using = "//span[@class= 'mfrm-header-subtitle']")
	private WebElement customizeMFRPopupSubTitle;
	
	@FindBy(how = How.XPATH, using = "//div[@class= 'mfrm-count-text']")
	private WebElement customizeMFRPopupSelectedMFRCount;
	
	@FindBy(how = How.XPATH, using = "//div[@class= 'mfrm-selected-header mfrm-title']")
	private WebElement customizeMFRPopup_AllAvailableMFRSectionTitle;
	
	@FindBy(how = How.XPATH, using = "(//div[@class= 'mfrm-selected-header mfrm-title'])[2]")
	private WebElement customizeMFRPopup_SelectedMFRSectionTitle;
	
	@FindBy(how = How.XPATH, using = "//div[@id= 'mCSB_27']")
	private WebElement customizeMFRPopup_SelectedMFRSectionBox;
	
	@FindBy(how = How.XPATH, using = "//i[@class='fa fa-close mfrm-close']")
	private WebElement customizeMFRPopup_CloseButton;

	@FindBy(how = How.XPATH, using = "//div[text()='Select Customize Manufacturers or toggle to ALL']")
	private WebElement noUserFavouredMFRMessage;

	@FindBy(how = How.XPATH, using = "//span[@class='slider round']")
	private WebElement all_UserfavouredMFR_Toggle;

	@FindBy(how = How.XPATH, using = "//span[@class='subheader'][text()='Top 100 Manufacturers']")
	private WebElement allMFRTop100Subtitle;

	@FindBy(how = How.XPATH, using = "//div[@class='mfrm-cancel']")
	private WebElement customizeMFRPopupCancelButton;

	@FindBy(how = How.XPATH, using = "//label[@for='dbrdChkSpec']")
	private WebElement mfrVizSpecifiedChkboxLabel;

	@FindBy(how = How.XPATH, using = "//*[@class='MfrCheckboxLabel' and text()='Specified']//..//..//input[@id='dbrdChkSpec' and @type='checkbox']")
	private WebElement mfrVizSpecifiedChkbox;

	@FindBy(how = How.CSS, using = "div[class*='dashboardleft'] #contentPlaceHolderHeader_leftNavFilterUI_Text1")
	private WebElement mfrVizLeftFilterTextbox;

	@FindBy(how = How.CSS, using = "div[class*='dashboardright'] #contentPlaceHolderHeader_leftNavFilterUI_Text1")
	private WebElement mfrVizRightFilterTextbox;

	@FindBy(how = How.CSS, using = "[class*='dashboardleft']  #LUSTACKED_BAR .bar tspan[fill='#00b98f']")
	private List<WebElement> mfrVizLeftBasicOfDesignFilterBarValueList;

	@FindBy(how = How.CSS, using = "[class*='dashboardleft']  #LUSTACKED_BAR .bar tspan[fill='#2b75a8']")
	private List<WebElement> mfrVizLeftSpecifiedFilterBarValueList;

	@FindBy(how = How.CSS, using = "[class*='dashboardright']  #RUSTACKED_BAR .bar tspan[fill='#00b98f']")
	private List<WebElement> mfrVizRightBasicOfDesignFilterBarValueList;

	@FindBy(how = How.CSS, using = "[class*='dashboardright']  #RUSTACKED_BAR .bar tspan[fill='#2b75a8']")
	private List<WebElement> mfrVizRightSpecifiedFilterBarValueList;

	@FindBy(how = How.CSS, using = ".projectGrid_link")
	private List<WebElement> projectGridLinkList;

	@FindBy(how = How.XPATH, using = "//*[@class='expandClose']//span")
	private WebElement expandcloseBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='bodSpecChkContainer']/label")
	private WebElement bodCheckbox;

	@FindBy(how = How.XPATH, using = ".//*[@id='bodSpecChkContainer']/label")
	private WebElement specifiedCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@class='barClass']")
	private List<WebElement> expandedChartGraph;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a[contains(@class,'iconheader iconExpand tipsy-ne')]//span[@class='fa fa-expand']")
	private WebElement expandOnManufacturer;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a[contains(@class,'iconheader iconExpand tipsy-ne')]//span[@class='fa fa-expand']")
	private WebElement expandOnRightChart;

	@FindBy(how = How.CSS, using = "table[class*='pq-grid-header-table'] th>div")
	private List<WebElement> gridColumnHeadings;

	@FindBy(how = How.CSS, using = ".pq-grid-table .pq-grid-row")
	private List<WebElement> gridRowList;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_pcTop_perPageSectionWrapper a[original-title*='Page:']")
	private List<WebElement> paginationPageLinkList;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_divAll']//div[contains(@class,'MyFilter ui-sortable')]")
	public WebElement LeftNavigationFilterBackGround;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ctrl_SavedSearchDisplay_lblSavedSearchName")
	private WebElement savedSearchName;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'Projectinoutlicense')]")
	public WebElement reports;

	@FindBy(how = How.XPATH, using = "//a[@class='DrawerAnchor']")
	private WebElement Filter;

	@FindBy(how = How.XPATH, using = ".//div[@id='additionalFilter']/span")
	public WebElement AdditionalFilters;

	@FindBy(how = How.XPATH, using = "//*[@id='additionalFilter']/span")
	private WebElement InAdditionalFilter;

	// *
	@FindBy(how = How.ID, using = "Project-Results")
	private WebElement ProjectResultBreadCrumb;

	// *
	@FindBy(how = How.ID, using = "myAccount")
	private WebElement myAccountLink;

	// *
	@FindBy(how = How.XPATH, using = "//*[@id='projectGrid']//tr[2]/td[9]")
	private WebElement PublishDate;

	// *
	@FindBy(how = How.XPATH, using = ".//*[@id='trackingListSearch']/tr[1]/td[1]/span")
	private WebElement firstTrackingList;

	// *
	@FindBy(how = How.LINK_TEXT, using = "My SpecAlerts")
	private WebElement mySpecAlertsLink;

	@FindBy(how = How.ID, using = "lblErr")
	private WebElement errLabel;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//a[contains(@class,'grpItems')]")
	private WebElement groupedSectionFilter;

	@FindBy(how = How.XPATH, using = ".//*[@id='LUBAR']//div[contains(@class,'barWrapper selectedbar')]")
	private WebElement selectedBarInchart1;

	@FindBy(how = How.XPATH, using = ".//*[@id='RUBAR']//div[contains(@class,'barWrapper selectedbar')]")
	private WebElement selectedBarInchart2;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'BreadCrumb')]")
	private List<WebElement> BreadCrumbSection;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_FILTERS']//following-sibling::span[contains(@class,'arrow-up')]")
	private WebElement specialFilterCollapsed;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_ViewListactive']")
	private WebElement projectListBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_InactiveView']/span/span")
	private WebElement projectListToggleBtn;

	@FindBy(how = How.CSS, using = ".toggleButtonNameStyles")
	private List<WebElement> toggleButtonNameList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_ViewGridActive")
	private WebElement gridViewToggleUnSelectedBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_A1")
	private WebElement gridViewToggleSelectedBtn;

	@FindBy(how = How.CSS, using = ".pq-grid-header-table .gear-icon")
	private WebElement gridGearIcon;

	@FindBy(how = How.CSS, using = ".projectGridSelectAll")
	private WebElement projectGridSelectAllCheckBox;

	@FindBy(how = How.ID, using = "projectGridWrapper")
	private WebElement projectGridTableWrapper;

	@FindBy(how = How.XPATH, using = "//input[@class='projectGridSelectAll' and @type='checkbox']//..//label")
	private WebElement projectGridSelectAllCheckBoxVisibility;

	@FindBy(how = How.CSS, using = ".projectGridCheckbox")
	private List<WebElement> projectGridCheckBox;

	@FindBy(how = How.XPATH, using = "//tr[@pq-row-indx][last()]")
	private WebElement GridlastDataRow;

	@FindBy(how = How.ID, using = "popup-dialog-DCS")
	private WebElement dCSDialog;

	@FindBy(how = How.XPATH, using = "//tr[@class='pq-grid-title-row']//th")
	private List<WebElement> GridTitleRow;

	@FindBy(how = How.XPATH, using = "//td[@class='pq-grid-cell gridProjectTitleColumn']")
	private List<WebElement> gridProjectTitleColumn;

	@FindBy(how = How.XPATH, using = "//td[@class='pq-grid-cell gridProjectTitleColumn']//span")
	private List<WebElement> gridProjectTitleColumn_ProjectLink;

	@FindBy(how = How.CSS, using = "#popup-dialog-DCS .DCS-header-wrapper .grid-DCS-header")
	private WebElement dCSDialogHeader;

	@FindBy(how = How.CSS, using = "div[class='ui-icon ui-icon-triangle-1-e']")
	private WebElement GridRightScroll;

	@FindBy(how = How.CSS, using = "div[class='ui-icon ui-icon-triangle-1-w']")
	private WebElement GridLeftScroll;

	@FindBy(how = How.CSS, using = "div[class='ui-icon ui-icon-triangle-1-n']")
	private WebElement GridUpScroll;

	@FindBy(how = How.CSS, using = "div[class='ui-icon ui-icon-triangle-1-s']")
	private WebElement GridDownScroll;

	@FindBy(how = How.ID, using = "resetAllGcs")
	private WebElement resetAllOkButton;

	@FindBy(how = How.CSS, using = "#chkContainer .checkbox_wrapper_GCS")
	private List<WebElement> dCSDialogCheckboxContainers;

	@FindBy(how = How.XPATH, using = "//*[@class='pq-sb-slider pq-sb-slider-h ui-state-default ui-corner-all ui-draggable ui-draggable-handle']")
	private WebElement horizontalBarForProjectGrid;

	@FindBy(how = How.ID, using = "Prev_Previous_Page")
	private WebElement prevPreviousPage;

	@FindBy(how = How.ID, using = "Prev_Next_Page")
	private WebElement prevNextPage;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_BarSelected']")
	private WebElement Dashboard1Btn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_InactiveSelected")
	private WebElement selectedDashboard1Icon;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_sectionViewActive']")
	private WebElement Dashboard2Btn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_sectionViewInActive")
	private WebElement selectedDashboard2Icon;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_searchwrapper")
	private WebElement keywordSearchTextfieldContainer;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'MySearchesDropDown')]")
	private List<WebElement> mySearchesDropDownListValues;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchSpan")
	public WebElement mySearchesDropDown;

	@FindBy(how = How.CSS, using = ".mysearchesdropdown")
	public WebElement mySearchesDropDownContainer;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_structuralPropLink']")
	private WebElement addProperties;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'_mySearchesTrackingListDD')]")
	private WebElement mySearchesTrackingListOption;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ProjectHeader_ProjectVisualToggle_toggleButtonContainer")
	private List<WebElement> visualizeProjectIcon;

	@FindBy(how = How.ID, using = "ctl00_ucTopTabMenu_lnkCompany")
	private WebElement companiesTab;

	@FindBy(how = How.ID, using = "ctl00_ucTopTabMenu_lnkProjects")
	private WebElement projectsTab;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_saveSearchProj")
	private WebElement saveSearchBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_btnSearch")
	private WebElement searchBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_searchText")
	private WebElement searchTextbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_dropdownList")
	private WebElement dropDownListBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesTrackingListDD")
	private WebElement mySearchesTrackingList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesSavedSearchDD")
	private WebElement mySavedSearches;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesSpecAlertDD")
	private WebElement mySearchesSpecAlerts;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_DesignAlerts']")
	private WebElement mySearchesSavedSearch;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ucCreateBetweenSpec_btnUpdate")
	private WebElement updateBtnCreateBetween;

	@FindBy(how = How.CSS, using = ".project-title")
	private List<WebElement> projectTitles;

	@FindBy(how = How.XPATH, using = ".//*[contains(@id,'lblValuation') and text()[contains(.,'A') or contains(.,'B') or contains(.,'C') or contains(.,'D') or contains(.,'E') or contains(.,'F') or contains(.,'G') or contains(.,'H')  or contains(.,'I') or contains(.,'J') or contains(.,'K') or contains(.,'L') or contains(.,'M') ]]//ancestor::div[contains(@class,'project-result')]//div[contains(@class,'project-title')]//a")
	private List<WebElement> projectTitlesWithValuationContainChar;

	@FindBy(how = How.LINK_TEXT, using = "Sea Cliff ES & North Shore HS Capital Improvements")
	private WebElement projectSeaCliff_SpecificProject;

	@FindBy(how = How.ID, using = "lnkBtnHide")
	private List<WebElement> hideButtonsOnPage;

	@FindBy(how = How.ID, using = "ctl00_lblTrack")
	private WebElement trackLink;

	@FindBy(how = How.ID, using = "ctl01_lblHide")
	private WebElement hideLink;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'lblHide')]")
	private List<WebElement> hideLinkList;

	@FindBy(how = How.ID, using = "lnkhideProjects")
	private WebElement hideProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_lblDrNumber")
	private WebElement firstDRNumber;

	@FindBy(how = How.ID, using = "lnkUnhideProjects")
	private WebElement unhideProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_chkProjectSelect")
	private WebElement firstProjectchkBox;

	@FindBy(how = How.XPATH, using = "//div[@class='pr-checkbox']//input[@class='checkbox']")
	private List<WebElement> projectTitleCheckboxes;

	@FindBy(how = How.ID, using = "ctl00_lblProjectTitle")
	private WebElement firstProjectTitle;

	@FindBy(how = How.ID, using = "ctl00_imgInCart")
	private WebElement firstImgInCart;

	@FindBy(how = How.ID, using = "ctl01_chkProjectSelect")
	private WebElement secondProjectchkBox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_rbDesignAlertsOnly")
	private WebElement LeftpnlSpecialFiltersDesignAlertsOnly;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_DesignAlerts")
	private WebElement DesignAlertCheckBox;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'SweetsIcon')]")
	private List<WebElement> iconDesignAlerts;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'ctl00_lnkDesignAlert')]")
	private List<WebElement> linkDesignAlerts;

	@FindBy(how = How.LINK_TEXT, using = "Plans")
	private WebElement plansLink;

	@FindBy(how = How.LINK_TEXT, using = "Planholders/Bidders")
	private WebElement planholdersBiddersLink;

	@FindBy(how = How.LINK_TEXT, using = "Specs")
	private WebElement specsLink;

	@FindBy(how = How.LINK_TEXT, using = "Addenda")
	private WebElement addendaLink;

	@FindBy(how = How.LINK_TEXT, using = "View Projects")
	private WebElement viewProjectsActionsOptions;

	@FindBy(how = How.LINK_TEXT, using = "Add Online Project")
	private WebElement addOnlineProjectActionOptions;

	@FindBy(how = How.LINK_TEXT, using = "Firms")
	private WebElement firmsLink;

	@FindBy(how = How.LINK_TEXT, using = "Notes")
	private WebElement notesLink;

	@FindBy(how = How.ID, using = "ctl02_lnkNotes")
	private WebElement notesLinkthird;

	@FindBy(how = How.ID, using = "lnkTrackProjects")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "lnkViewProjects")
	private WebElement viewProjectsMenu;

	@FindBy(how = How.ID, using = "lnkSaveProjects")
	protected WebElement downloadProjectsMenu;

	@FindBy(how = How.ID, using = "lnkDownloadFirms")
	private WebElement downloadFirmsMenu;

	@FindBy(how = How.ID, using = "lnkEmailProjects")
	private WebElement emailProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_lnkPrintProjectDetails")
	private WebElement printProjectDetailsMenu;

	@FindBy(how = How.ID, using = "lnkCancelBtn")
	private WebElement cancelbuttonShoppingCart;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_lnkPrintProjectListing")
	private WebElement printProjectListMenu;

	@FindBy(how = How.ID, using = "project-select-all")
	private WebElement projectSelectAll;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer")
	private WebElement appliedFilterContainer;

	@FindBy(how = How.CLASS_NAME, using = "appliedFilter")
	private WebElement filterSavedSearch;

	@FindBy(how = How.CLASS_NAME, using = "listProjectCountText")
	private WebElement listProjectCount;

	@FindBy(how = How.ID, using = "Prev4")
	private WebElement pageNumber4;
	@FindBy(how = How.ID, using = "Prev2")
	private WebElement pageNumber2;

	@FindBy(how = How.ID, using = "Prev_First_Page")
	private WebElement firstPageIcon;

	@FindBy(how = How.ID, using = "Prev1")
	private WebElement pageNumber1;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']/div/div[2]/div[2]/span/span")
	private WebElement secondFilterCrumbTooltip;

	@FindBy(how = How.ID, using = "Prev4")
	private WebElement highlightedpageNumber;

	@FindBy(how = How.LINK_TEXT, using = "Unhide")
	private WebElement unhideLink;

	@FindBy(how = How.LINK_TEXT, using = "Remove")
	private WebElement removeLink;

	@FindBy(how = How.LINK_TEXT, using = "Alert On")
	private WebElement alertOnLink;

	@FindBy(how = How.LINK_TEXT, using = "Print Project List")
	private WebElement printProjectListLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_lnkPrintProjectDetails")
	private WebElement printProjectDetailsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'mySearchesSpecAlert')]")
	private WebElement specAlertsDropDownValue;

	@FindBy(how = How.CSS, using = "div[id$='resultcontent']")
	private WebElement resultContent;

	@FindBy(how = How.CSS, using = "#ctl00_specText>span>strong")
	private WebElement specAlertsLabel;

	@FindBy(how = How.CSS, using = "#ctl00_trackListsLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.ID, using = "LUDONUT")
	private WebElement donutChart;

	@FindBy(how = How.XPATH, using = "//div[@id='LUDONUT']//following-sibling::div[contains(@class,'legendwrapper')]//div[@class='legendItem']//span")
	private List<WebElement> constructionTypeDonut;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_sectionViewActive")
	private WebElement sectionVisualizationsToggleBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[@class='tilesText']//ancestor::a")
	private List<WebElement> selectVisualCharButtonList;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a[contains(@class,'iconheader iconTiles tipsy-ne')]")
	private List<WebElement> customizeCharttile;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//div[contains(@class,'chartsection')]//div[@class='wrapperBar']")
	private List<WebElement> allBarWrapperChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//div[contains(@class,'chartsection')]//div[@class='wrapperBar']")
	private List<WebElement> allBarWrapperChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Project Type')]//ancestor::a")
	private List<WebElement> projectTypeCategoryInDashboard2;

	// Project Group VZ Chart
	@FindBy(how = How.XPATH, using = "//div[@class='LUBARleft leftText']/div")
	private List<WebElement> leftChartBarTextList;

	@FindBy(how = How.XPATH, using = "//div[@class='RUBARleft leftText']/div")
	private List<WebElement> rightChartBarTextList;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Project Type')]//ancestor::a")
	private List<WebElement> projectTypeTileOnDashboard;

	@FindBy(how = How.XPATH, using = "//div[@class='chartcontainerBg fleft dashboardleft LU']//span[contains(@class,'subheader')]")
	private WebElement projectGroupsSubtitleOnLeftChart;

	@FindBy(how = How.XPATH, using = "//div[@class='chartcontainerBg fright dashboardright RU']//span[contains(@class,'subheader')]")
	private WebElement projectGroupsSubtitleOnRightChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Manufacturers')]//ancestor::a")
	private List<WebElement> manufacturersCategoryInDashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Ownership Type')]//ancestor::a")
	private List<WebElement> ownershipTypeCategoryInDashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Division')]//ancestor::a")
	private List<WebElement> DivisionTypeCategoryInDashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Construction Type')]//ancestor::a")
	private List<WebElement> ContructionTypeCategoryInDashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Sections')]//ancestor::a")
	private List<WebElement> sectionsTypeCategoryInDashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'Action Stage')]//ancestor::a")
	private List<WebElement> actionsStageCategoryInDashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a//span[contains(.,'SpecAlerts')]//ancestor::a")
	private List<WebElement> specAlertsCategoryInDashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//*[@class='tilesText']")
	private List<WebElement> allTileTextChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Project Type')]")
	private WebElement projectTypeCategoryChart1Dashboard2;

	// Added for Project Groups
	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Project Type')]")
	private WebElement projectTypeTileOnLeftChart;

	@FindBy(how = How.XPATH, using = "//span[text()='Project Type'][@class='tilesText']")
	private WebElement projectTypeTileDisabledOnChart;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard [class*='dashboardleft LU'] [class*='chartsection']")
	private List<WebElement> allProjectGroupsBarRectChart1Dashboard;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard [class*='dashboardright RU'] [class*='chartsection']")
	private List<WebElement> allProjectGroupsBarRectChart2Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Manufacturers')]")
	private WebElement manufacturersCategoryChart1Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Construction Type')]")
	private WebElement constructionTypeCategoryChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Division')]")
	private WebElement divisionCategoryChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Sections')]")
	private WebElement sectionsCategoryChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Action Stage')]")
	private WebElement actionStageCategoryChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'Ownership Type')]")
	private WebElement ownershipTypeCategoryChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//a//span[contains(.,'SpecAlerts')]")
	private WebElement specAlertsCategoryChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//*[contains(@class,'fa-arrow-left')]")
	private WebElement backBtnChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//*[contains(@class,'chartHead')]")
	private WebElement chartHeaderChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//*[@class='tilesText']")
	private List<WebElement> allTileTextChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Project Type')]")
	private WebElement projectTypeCategoryChart2Dashboard2;

	// Added for Project Groups
	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Project Type')]")
	private WebElement projectTypeTileOnRightChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Manufacturers')]")
	private WebElement manufacturersCategoryChart2Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Construction Type')]")
	private WebElement constructionTypeCategoryChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Division')]")
	private WebElement divisionCategoryChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Sections')]")
	private WebElement sectionsCategoryChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Action Stage')]")
	private WebElement actionStageCategoryChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'Ownership Type')]")
	private WebElement ownershipTypeCategoryChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//a//span[contains(.,'SpecAlerts')]")
	private WebElement specAlertsCategoryChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//*[contains(@class,'fa-arrow-left')]")
	private WebElement backBtnChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//*[contains(@class,'chartHead')]")
	private WebElement chartHeaderChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[contains(@class,'chartcontainerBg fleft dashboardleft LU')]//input[contains(@id,'contentPlaceHolderHeader_leftNavFilterUI_Text1')]")
	private WebElement sectionFindInfilterChart1;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[contains(@class,'chartcontainerBg fright dashboardright RU')]//input[contains(@id,'contentPlaceHolderHeader_leftNavFilterUI_Text1')]")
	private WebElement sectionFindInfilter;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//a[contains(@class,'removeIconCross')]")
	private WebElement removeIconFindInfilter;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//span[contains(@class,'fa fa-search')]")
	private WebElement sectionsSearchBtn;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ProjectHeader_ProjectVisualToggle_InactiveSelected']/span/span")
	private WebElement barchartViewToggleBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_BarSelected")
	private WebElement dashboard1ToggleBtn;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'anchorFilter fleft')]//span")
	private List<WebElement> leftMenuAppliedFilterList;
	
	@FindBy(how = How.XPATH, using = "//i[@onclick='closeEditBox();']")
	private WebElement closePopupIcon;

	@FindBy(how = How.XPATH, using = "//div[@class='barWrapper deselectedbar' and @sectiongroupcode='SPEC_DIVISION_GROUP']")
	private List<WebElement> ChartView_DivGBarList;

	@FindBy(how = How.XPATH, using = "//div[@class='barWrapper selectedbar']")
	private WebElement ChartView_FirstDivGBar;

	@FindBy(how = How.XPATH, using = "//div[@class='LUBARleft leftText']")
	private List<WebElement> ChartView_DivGBar_LeftlblList;

	@FindBy(how = How.XPATH, using = "//div[@id='sectionWrapper']//input[contains(@class,'searchTest')]")
	private WebElement sectionSeactTextField;

	@FindBy(how = How.XPATH, using = "//div[@class='sectionText']//..//div[@class='sectionrectWrapper deselectedbar']")
	private List<WebElement> compareSectionSearchResultString;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[contains(@class,'chartcontainerBg fright dashboardright RU')]//span[contains(@class,'fa fa-search')]")
	private WebElement searchIconOfSectionVisualizations;

	@FindBy(how = How.XPATH, using = ".//*[@id='RUBAR']/div//div//div[contains(@class,'RUBARleft leftText')]/div")
	private List<WebElement> sectionsTitleInSectionVisualtionPage;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_CONDITION']//span[contains(@class,'arrow-up')]")
	private WebElement specialConditionsArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_CONDITION']")
	private WebElement specialConditionsFilter;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'specialCoditionFacet')]")
	private List<WebElement> specialConditonFilterList;
	@FindBy(how = How.ID, using = "mCSB_14_dragger_vertical")
	private WebElement SpecialCondi_scrollbar_verticalDragger;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'mesCategoryFacet')]")
	private List<WebElement> mesCategoryFacetList;

	@FindBy(how = How.XPATH, using = "//div[@class='SPEC_ALERT']//label")
	private List<WebElement> specAlertsLeftProgramsFilter;

	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecAlert']//span")
	private List<WebElement> specAlertsSeeMorePopUpProgramsList;

	@FindBy(how = How.ID, using = "form1")
	private List<WebElement> TradeOptionstList;

	@FindBy(how = How.XPATH, using = "//label[text()='Green Building Elements']/..//input[@type='checkbox']")
	private WebElement greenBuildingElementsSpecialConditionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Green Building Elements']/span")
	private WebElement greenBuildingElementsSpecialConditionCount;

	@FindBy(how = How.XPATH, using = "//label[text()='Electrical']/..//input[@type='checkbox']")
	private WebElement electricalSpecDivisionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Texas']/..//input[@type='checkbox']")
	private WebElement texasStateRegionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='California']/..//input[@type='checkbox']")
	private WebElement californiaStateRegionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Ohio']/..//input[@type='checkbox']")
	private WebElement ohioStateRegionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Florida']/..//input[@type='checkbox']")
	private WebElement floridaStateRegionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='New York']/..//input[@type='checkbox']")
	private WebElement newYorkStateRegionChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Electrical']/span")
	private WebElement electricalSpecDivisionCount;

	@FindBy(how = How.XPATH, using = "//span[@class='listProjectCountText']")
	private WebElement projectResultCount;

	@FindBy(how = How.XPATH, using = "//span[@class='chartProjectCountText']")
	private WebElement chartprojectResultCount;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[@class='chartcontainerBg fright dashboardright RU']//div[@style='float:left']/div/span")
	private WebElement Dashboard1_SectionsCount;

	@FindBy(how = How.XPATH, using = ".//*[@id='LUBAR']/div//div//div[contains(@class,'LUBARright')]")
	private List<WebElement> countDivision;

	@FindBy(how = How.XPATH, using = ".//*[@id='LUBAR']/div//div//div[contains(@class,'barWrapper')]")
	private List<WebElement> countDivisionBars;

	@FindBy(how = How.XPATH, using = ".//*[@id='LUBAR']/div//div//div[contains(@class,'leftText')]")
	private List<WebElement> countspecDivInChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='LUBAR']/div//div[contains(@class,'LUBARleft')]/div")
	private List<WebElement> projectTypeBarTextChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='RUBAR']/div/div//div[2]//div")
	private List<WebElement> widthOfSectionBars;

	@FindBy(how = How.XPATH, using = ".//*[@id='LUBAR']//*[@class='rectSection']")
	private List<WebElement> rectBarChart1Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='RUBAR']//*[@class='rectSection']")
	private List<WebElement> rectBarChart2Dashboard2;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//span[contains(@class,'txtBold')]")
	private List<WebElement> filterAppliedCount;

	@FindBy(how = How.XPATH, using = "ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer")
	private List<WebElement> appliedFilterSection;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//span[contains(@class,'anchorFilter fleft')]")
	private List<WebElement> appliedfiltersList;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//a[contains(@class,'keywrdFilter')]")
	private List<WebElement> appliedKeywordfiltersList;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_btnprjresltAction")
	public WebElement actionsDropdown;

	@FindBy(how = How.CSS, using = "li[id*='ctl00_contentPlaceHolderHeader_pcTop_HeaderActions']")
	private List<WebElement> actionDropdownAllOptions;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_btnProjectActions")
	private WebElement actionsDropdownSpecAlert;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_HeaderActions_btnProjectActions")
	private WebElement specAlertactionsDropdown;

	@FindBy(how = How.XPATH, using = "//div[@class='PROJECT_TYPE_CATEGORY']")
	private List<WebElement> projectGroupsFilter;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'projTypeCatFacet')]")
	private List<WebElement> leftnavprojectTypeCategoriesFilter;

	@FindBy(how = How.CSS, using = "[id*='ddlPageSize']")
	private List<WebElement> resultsDropdown;

	@FindBy(how = How.CSS, using = ".pagination")
	private List<WebElement> pagination;

	@FindBy(how = How.ID, using = "sortdropdownlist")
	private WebElement sectionssortDropdown;

	@FindBy(how = How.CSS, using = "div[class*='chartcontainerBg fright dashboardright RU'] #sortdropdown #countText")
	private WebElement sectionssortDropdownSecondView;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_multipleDRSearch")
	private WebElement drGrid;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private WebElement LoadingRingOnPopop;
	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private List<WebElement> LoadingRingOnPopopInvisibleCheck;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisibleCheck;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblAddress')]")
	private List<WebElement> countryAddressLabelLocator;

	// Bidding Within
	@FindBy(how = How.XPATH, using = "//div[@expandpref='BIDDING_WITHIN']")
	private WebElement biddingWithinFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='BIDDING_WITHIN']//span[contains(@class,'arrow-down')]")
	private WebElement biddingWithinFilterArrowDownIcon;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'contentPlaceHolderHeader_leftNavFilterUI_BidWithinController')]/following::label[@class='fleft InOutval']")
	private List<WebElement> biddingWithinElement;
	@FindBy(how = How.XPATH, using = "//label[text()='Next 7 days']/..//input[@type='radio']")
	private WebElement nextSevenDaysBiddingRadioBtn;
	@FindBy(how = How.XPATH, using = "//label[text()='Next 15 days ']/..//input[@type='radio']")
	private WebElement nextFifteenDaysBiddingRadioBtn;
	@FindBy(how = How.XPATH, using = "//label[text()='Next 1 Month ']/..//input[@type='radio']")
	private WebElement nextOneMonthBiddingRadioBtn;
	@FindBy(how = How.XPATH, using = "//label[text()='ASAP ']/..//input[@type='radio']")
	private WebElement asapBiddingRadioBtn;
	@FindBy(how = How.XPATH, using = "//label[text()='No date set ']/..//input[@type='radio']")
	private WebElement noDateSetBiddingRadioBtn;
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblBidDate') and not(contains(@id,'lblBidDateText'))]")
	private List<WebElement> bidDateSearchResultList;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'BidWithinController_txtFromBidDatenew')]//..//span[contains(@class,'calendarIcon')]")
	private WebElement customRange_BidWithIn_FromDate;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'BidWithinController_txtToBidDatenew')]//..//span[contains(@class,'calendarIcon')]")
	private WebElement customRange_BidWithIn_ToDate;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'ctl00_contentPlaceHolderHeader_leftNavFilterUI_BidWithinController')]")
	private List<WebElement> biddingWithin_LHSFilterList;
	@FindBy(how = How.XPATH, using = ".//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_BidWithinCon']//label[@for='Custom']")
	private WebElement CustomRangeBiddingRadio_txt;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_BidWithinCon']//span[@class='pubvalidationStatus']")
	private WebElement CustomRangeBiddingRadio_errorMsg;
	@FindBy(how = How.XPATH, using = "//label[starts-with(@id, 'ctl00_contentPlaceHolderHeader_leftNavFilterUI_BidWithinController')]")
	private List<WebElement> biddingWithinLHS_LabelList;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'ucCreateBetweenSpec_txtFromDate')]//following-sibling::a[contains(@class,'dp-choose-date')]")
	private WebElement createdBetween_FromDateIcon;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'ucCreateBetweenSpec_txtToDate')]//following-sibling::a[contains(@class,'dp-choose-date')]")
	private WebElement createdBetween_ToDateIcon;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ucCreateBetweenSpec_txtFromDate")
	private WebElement createdBetween_FromDateIconTxt;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ucCreateBetweenSpec_txtToDate")
	private WebElement createdBetween_ToDateIconTxt;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'project-result')]")
	private List<WebElement> projectSummary;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithPlans;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ProjectResult_dvProjresults']//a[contains(.,'Plans')][contains(@class,'lnkProjDocHighilight')]")
	private List<WebElement> highlightedPlans;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ProjectResult_dvProjresults']//a[contains(.,'Specs')][contains(@class,'lnkProjDocHighilight')]")
	private List<WebElement> highlightedSpecs;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ProjectResult_dvProjresults']//a[contains(.,'Addenda')][contains(@class,'lnkProjDocHighilight')]")
	private List<WebElement> highlightedAddenda;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithSpecs;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkFirm')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithFirms;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkBidders')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithBidders;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkBidders')]//ancestor::Div[contains(@class,'project-result')]//input[@class='checkbox']")
	private List<WebElement> projectTitlesWithBiddersCheckboxList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkNotes')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithNotes;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkAddenda')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitleWithAddenda;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkDesignAlert')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitleWithDesignAlerts;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]//following-sibling::a[contains(@id,'lnkSpecs')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithPlansAndSpecs;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]//following-sibling::a[contains(@id,'lnkSpecs' and @class='lnkProjDocHighilight')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithPlansAndSpecsMatched;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]//following-sibling::a[contains(@id,'lnkSpecs')]//following-sibling::a[contains(@id,'lnkAddenda')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithPlansAndSpecsAndAddenda;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_btnSearch")
	private WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkFirm')]//following-sibling::a[contains(@id,'lnkBidders')]//following-sibling::a[contains(@id,'lnkSpecs')]//following-sibling::a[contains(@id,'lnkAddenda')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithFirmAndBidderAndPlansAndSpecsAndAddenda;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkFirm')]//following-sibling::a[contains(@id,'lnkBidders')]//following-sibling::a[contains(@id,'lnkAddenda')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithFirmAndBidderAndPlansAndAddenda;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkFirm')]//following-sibling::a[contains(@id,'lnkBidders')]//following-sibling::a[contains(@id,'lnkPlans')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithFirmAndBidderAndPlans;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkBidders')]//following-sibling::a[contains(@id,'lnkPlans')]//following-sibling::a[contains(@id,'lnkSpecs')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithSpecAndBidderAndPlans;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'specAlertOnSummary')]//ancestor::Div[contains(@class,'project-result')]//input[contains(@id,'chkProjectSelect')]")
	private List<WebElement> projectTitlesWithSpecAlertsChkBox;

	@FindBy(how = How.XPATH, using = "(//a[contains(@class,'lnkProjDocHighilight') and contains(.,'Specs')])[1]")
	private WebElement highlightLinkSpecsInProject;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'specAlertOnSummary')]//ancestor::Div[contains(@class,'project-result')]//div[@class='project-title']")
	private List<WebElement> projectTitlesWithSpecAlertsName;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]//ancestor::div[contains(@class,'project-result')]//div[@class='addToLicense']")
	private List<WebElement> addThisProjectToYourLicenseLinkWithPlans;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]//ancestor::div[contains(@class,'project-result')]//div[@class='addToLicense']//ancestor::ul//div[@class='project-title']")
	private List<WebElement> addThisProjectToYourLicenseLinkWithPlansProjectTitle;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'dvProjresults')]//span[contains(@id,'lblTrack') and contains(.,'Track')]")
	private List<WebElement> trackLinksInSummary;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ddlSortBy")
	private WebElement sortDropdown;

	@FindBy(how = How.XPATH, using = "//div[@class='mfrm-cancel']")
	private WebElement mfrUserFavPopupCancelButton;

	@FindBy(how = How.CSS, using = "[class*='dashboardright'] #sortdropdown")
	private WebElement rightSortDropdownManufacturers;

	@FindBy(how = How.CSS, using = "[class*='dashboardleft'] #sortdropdown")
	private WebElement leftSortDropdownManufacturers;

	@FindBy(how = How.CSS, using = "#sortdropdownlist>div[onclick*='sortdropdown'][place='RU']")
	private List<WebElement> rightSortDropdownOptionsManufacturers;

	@FindBy(how = How.CSS, using = "#sortdropdownlist>div[onclick*='sortdropdown'][place='LU']")
	private List<WebElement> leftSortDropdownOptionsManufacturers;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_SearchResultMessage_lblSummary")
	private WebElement projectResultSummary;

	@FindBy(how = How.XPATH, using = ".//*[@id='mCSB_23']//div[contains(@class,'barWrapper')]//div")
	private List<WebElement> barsInRightChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='mCSB_23']//div[contains(@class,'RUBARleft leftText')]/div")
	private List<WebElement> textInRightChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='mCSB_21']//div[contains(@class,'barWrapper')]")
	private List<WebElement> barsInLeftChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='mCSB_21']//div[contains(@class,'LUBARleft leftText')]/div")
	private List<WebElement> textInLeftChart;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard [class*='dashboardleft LU'] [class*='chartsection']>g>g:nth-of-type(2) [class='barClass']")
	private List<WebElement> allManufacturerBarRectChart1Dashboard;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard [class*='dashboardright RU'] [class*='chartsection']>g>g:nth-of-type(2) [class='barClass']")
	private List<WebElement> allManufacturerBarRectChart2Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//*[contains(@class,'chartsection')]//*[contains(@class,'LUBARleft leftText') or contains(@class,'tick')]")
	private List<WebElement> allTextBarChart1Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//*[contains(@class,'chartsection')]//*[contains(@class,'barWrapper')]")
	private List<WebElement> allBarRectChart1Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardleft LU')]//*[@class='mCSB_dragger_bar']")
	private WebElement scrollBarVizChart1Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//*[@class='mCSB_dragger_bar']")
	private WebElement scrollBarVizChart2Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//*[contains(@class,'chartsection')]//*[contains(@class,'RUBARleft leftText') or contains(@class,'tick')]")
	private List<WebElement> allTextBarChart2Dashboard;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//*[contains(@class,'dashboardright RU')]//*[contains(@class,'chartsection')]//*[contains(@class,'barWrapper')]")
	private List<WebElement> allBarRectChart2Dashboard;

	@FindBy(how = How.XPATH, using = "//li[@class='ui-menu-item']")
	private List<WebElement> findInMFRDropDownList;

	@FindBy(how = How.LINK_TEXT, using = "Alert Off")
	private WebElement alertoff;

	@FindBy(how = How.LINK_TEXT, using = "Alert On")
	private WebElement alerton;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_indicatorProjects']/img")
	private WebElement alertIndicatorIcon;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkPlans')]")
	private List<WebElement> lnkPlans;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs')]")
	private List<WebElement> lnkSpecs;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkAddenda')]")
	private List<WebElement> lnkAddenda;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkDesignAlert')]")
	private List<WebElement> lnkDesignAlerts;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lnkBidders')]")
	private List<WebElement> lnkBidders;

	@FindBy(how = How.ID, using = "lnkAlertOff")
	private WebElement alertoffUnderActions;

	@FindBy(how = How.ID, using = "lnkAlertOn")
	private WebElement alertonUnderActions;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_listProjectCountText")
	private WebElement projectDisplayedCount;

	// Filter crumb
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//span/a[@class='grpItems']")
	private List<WebElement> crumbFilterLinks;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//span/a[@class='grpItems']//..//span[@class='txtBold']")
	private List<WebElement> crumbGroupFilterLinksType;

	@FindBy(how = How.XPATH, using = "//span[text()='Spec Alert - ']")
	private WebElement specAlertFilterCrumb;
	@FindBy(how = How.XPATH, using = "//span[text()='Addenda']")
	private WebElement specDivisionFilterCrumb;
	@FindBy(how = How.XPATH, using = "//div[@id='filterpop']//input[@type='text']")
	private WebElement crumbFilterPopupSearchBox;
	@FindBy(how = How.XPATH, using = "//div[@class='popupResults']//span[@class='fa fa-remove']")
	private List<WebElement> crumbFilterPopupCloseBtnList;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//span[@class='fa fa-remove']")
	private List<WebElement> FilterCrumb_AllClose_btn;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//a[contains(@class,'removeIcon')]")
	private List<WebElement> filterCrumbAllCloseButton;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//div[@class='appliedFilter filterPanelCon']")
	private List<WebElement> FilterCrumb_AppliedFilter_List;

	@FindBy(how = How.ID, using = "filter")
	private WebElement filterScrumb;

	@FindBy(how = How.ID, using = "aClearAll")
	private WebElement filterclearAllLink;
	@FindBy(how = How.XPATH, using = "//div[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//span[@class='anchorFilter fleft popupFilterText']/span")
	private List<WebElement> crumbFilterCrumbPopupFilterList;
	@FindBy(how = How.XPATH, using = "//a[@class='common']")
	private WebElement FilterCrumbPopup_Done_Link;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//div[@class='appliedFilter filterPanelCon']/span")
	private List<WebElement> FilterCrumb_AppliedFilter_TitleList;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CATEGORY']")
	private WebElement tradeFilter;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_tradeTypeCategoryCon']/div[1]/span[2]")
	private WebElement tradeFilterClosedOnLoad;

	@FindBy(how = How.XPATH, using = "//div[@id='dvTradeTypeCon']")
	private WebElement tradeFilterFacetPopUp;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='BIDDING_WITHIN']")
	private WebElement BiddingWithinFilter;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_BidWithinController_P7D")
	private WebElement BiddingWithinNext7DaysOption;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblBidDate')][2]")
	private List<WebElement> bidDate;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblProjectType')][2]")
	private List<WebElement> projectType;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblActionStage')][2]")
	private List<WebElement> actionStage;

	@FindBy(how = How.ID, using = "ctl02_lblAddress")
	private List<WebElement> location;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblPublishDate')]")
	private List<WebElement> publishDate;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblValuation')]")
	private List<WebElement> valuation;

	@FindBy(how = How.ID, using = "fancybox-content")
	private WebElement FilterFacetPopUp;

	@FindBy(how = How.XPATH, using = "//a[@id='fancybox-close']")
	private WebElement ClosePopup;

	@FindBy(how = How.XPATH, using = "//span[text()='MES Category - ']")
	private WebElement materialEquipFilterCrumb;

	// Publish Range
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PUBLISH_RANGE']")
	private WebElement PublishRangeFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PUBLISH_RANGE']//span[contains(@class,'arrow-up')]")
	private WebElement PUBLISH_RANGE_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = ".//*[@id='pubRangList']/span[2]")
	private WebElement PublishRangeDropdown;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_Publishdate_PublishRangeDiv']//span[@class='pubvalidationStatus']")
	private WebElement PublishRange_errorMessage;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_Publishdate_pubSelector")
	private WebElement PublishRange_DropdownOptionTxt;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_Publishdate_txtFromDatenew")
	private WebElement PublichRangeFromtxt;
	@FindBy(how = How.XPATH, using = "//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
	private WebElement PublichRangeFromday;
	@FindBy(how = How.XPATH, using = "//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[1]/a")
	private WebElement PublichRangeFromdayMax;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_Publishdate_txtToDatenew")
	private WebElement PublichRangeTotxt;
	@FindBy(how = How.XPATH, using = "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[1]/a")
	private WebElement PublichRangeToday;
	@FindBy(how = How.XPATH, using = "//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a")
	private WebElement PublichRangeTodayMin;

	// Common
	@FindBy(how = How.ID, using = "fancybox-frame")
	private WebElement PopupFrame;
	@FindBy(how = How.ID, using = "filterpop")
	private WebElement Filterpop;
	@FindBy(how = How.XPATH, using = "//div[@id='filterpop']//a[@class='closeIcon fright']//span[@class='fa fa-remove']")
	private WebElement FilterpopClose;
	@FindBy(how = How.ID, using = "btnUpdateFancyBox")
	private WebElement PopupUpdateFancyBoxbtn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='']")
	private List<WebElement> CommonPopupParentFilterList;
	@FindBy(how = How.ID, using = "selectAll")
	private WebElement PopupSelectAll_cbk;
	@FindBy(how = How.XPATH, using = "//div[starts-with(@id,'dv')]//div[@class='1']//div[@class='subSectionLabel']/span")
	private WebElement PopupFirstParentfilter_lbl;
	@FindBy(how = How.ID, using = "showNovalues")
	private WebElement CommonPopupHideZeroProjects_cbk;
	@FindBy(how = How.XPATH, using = "//label[@for='showNovalues']")
	private WebElement CommonPopupHideZeroProjects_label;
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Show More')]")
	private WebElement CommonShowMoreFiltes;
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Show Less')]")
	private WebElement CommonShowLessFilters;
	@FindBy(how = How.XPATH, using = "//div[starts-with(@id,'dv')]//span")
	private List<WebElement> CommonPopupParentFilter_lblList;
	@FindBy(how = How.ID, using = "fancybox-overlay")
	private WebElement Filterpop_Overlay;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'_chkProjectSelect')]")
	private List<WebElement> ProjectResult_Chkbox_List;
	@FindBy(how = How.XPATH, using = "//span[@class='ui-accordion-header-icon ui-icon ui-icon-triangle-1-s']")
	private WebElement CommonPopop_Accodion_DownArrow;
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'_lblPublishDate')]")
	private List<WebElement> PublishDateRange_List;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_divAll']//div//span[@class='fleft filterHeader']")
	private List<WebElement> LHS_Filters_lblList;
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Light Gauge Steel')']")
	private WebElement AddPropertiesCheckBoxFrame;
	@FindBy(how = How.XPATH, using = ".//a[@class='lnkProjDocHighilight']")
	private List<WebElement> lnkProjDocHighilight;
	@FindBy(how = How.XPATH, using = "//div[@class='lastLevelLabel']//span")
	private List<WebElement> Common_Popup_allSubOptionslbl;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @id !='showNovalues']")
	private List<WebElement> Common_Popup_allCbksList;
	@FindBy(how = How.XPATH, using = "//input[contains(@id, '1.')]")
	private List<WebElement> Common_Popup_FirstLevel2CbksList;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_divAll']")
	private WebElement leftNavFilter;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']/div")
	private WebElement filterCrumbDrawer;

	@FindBy(how = How.XPATH, using = "//div[@class='commonChartHide']")
	private WebElement searchResultArea;

	@FindBy(how = How.ID, using = "lnkHostProject")
	private WebElement hostProjectActionsLink;
	@FindBy(how = How.ID, using = "lnkHostCompanies")
	private WebElement lnkHostCompaniesLink;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_rptNavigator_actionsNav_btnProjectActions")
	private WebElement ActionDropdown_ProjectDetailsPage;

	@FindBy(how = How.XPATH, using = "//a[@class='docupro-popup-close']")
	private WebElement closeDocupropopup;
	@FindBy(how = How.XPATH, using = "//a[@class='docupro-popup-submit']")
	private WebElement transferDocupropopup;
	@FindBy(how = How.CSS, using = "#hostToDocuProPopup .header")
	private WebElement hostToDocuProPopupHeader;

	// Reports
	@FindBy(how = How.XPATH, using = "//div[@expandpref='REPORTS']")
	private WebElement REPORTSFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='REPORTS']//span[contains(@class,'arrow-up')]")
	private WebElement REPORTS_Filter_ArrowUp;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='STRUCTURAL_PROPERTIES']//span[contains(@class,'arrow-up')]")
	private WebElement structuralPropFilterArrowUp;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_ProjectInOutUI_InOutDiv']//div[@class='projectreportLebel']/label[@class='InOutval fleft']")
	private List<WebElement> ReportFilter_LHS_lblList;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ProjectInOutUI_rbOutVal")
	private WebElement Reports_ProjOutSideOfSub_radio;

	// Find In
	@FindBy(how = How.XPATH, using = "//div[@expandpref='FIND_IN']")
	private WebElement FIND_INFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='FIND_IN']//span[contains(@class,'arrow-up')]")
	private WebElement FIND_INFilter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_Div3']//input[contains(@id, 'ctl00_contentPlaceHolderHeader_leftNavFilterUI')]")
	private List<WebElement> FindIn_LHS_CbkList;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_geoStateCon']//input[contains(@id, 'stateFacet')]")
	private List<WebElement> stateRegionCheckboxList;

	@FindBy(how = How.XPATH, using = "//label[text()='Specs']/..//input[@type='checkbox']")
	private WebElement specsFindInCheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_DesignAlerts")
	private WebElement designalertFindInCheckbox;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ProjectResult_dvProjresults']//a[contains(.,'DesignAlerts')][contains(@class,'lnkProjDocHighilight')]")
	private List<WebElement> highlightedDesignAlerts;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ProjectResult_dvProjresults']//a[contains(.,'DesignAlerts')]")
	private List<WebElement> linkdesignAlerts;

	@FindBy(how = How.XPATH, using = ".//span[@id='ctl00_lnkDesignAlert']")
	private List<WebElement> labeldesignAlerts;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_news")
	private WebElement newsFindInCheckbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_Plans")
	private WebElement plansFindInCheckbox;

	@FindBy(how = How.ID, using = "contentTypeList")
	private WebElement NewsSeachLst;

	@FindBy(how = How.XPATH, using = "//div[text()='Project Detail Text']")
	private WebElement projectDetailTextNewsSeachLstOption;

	// Geography
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']")
	private WebElement geographyFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']/a/img")
	private WebElement geographyFilterSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@class='GEOGRAPHY_COUNTRY']//input[@value='USA']")
	private WebElement geographyUSAchkbox;
	@FindBy(how = How.XPATH, using = ".//div[@class='subSectionChkBox']/input[@value='AFR']")
	private WebElement geographyAfricachkboxPopup;
	@FindBy(how = How.XPATH, using = ".//div[@class='subSectionChkBox']/input[@value='USA']")
	private WebElement geographyUSAChkboxPopup;
	@FindBy(how = How.ID, using = "countryFacet_2")
	private WebElement geographySecondchkbox;
	@FindBy(how = How.XPATH, using = "//input[@value='Update']")
	private WebElement geographyPopUpUapdateButton;
	@FindBy(how = How.ID, using = "projectStateNav")
	private WebElement geographyPopupStateLink;
	@FindBy(how = How.XPATH, using = "//input[@parent='1.1' and starts-with(@value,'USA')]")
	private List<WebElement> geographyPopupUASStatesList;
	@FindBy(how = How.XPATH, using = "//input[@id='1.1.5' and @value='USACA']")
	private WebElement geographyPopupUASStates_California_cbk;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']//span[contains(@class,'arrow-up')]")
	private WebElement GEOGRAPHY_COUNTRY_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']//span[contains(@class,'arrow-down')]")
	private WebElement GEOGRAPHY_COUNTRY_Filter_ArrowDown;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'countryFacet')]")
	private List<WebElement> GeographyFilterList;
	@FindBy(how = How.XPATH, using = "//div[@class='GEOGRAPHY_COUNTRY']//label/span")
	private List<WebElement> GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List;
	@FindBy(how = How.XPATH, using = "//div[@class='GEOGRAPHY_COUNTRY']//label")
	private List<WebElement> GEOGRAPHY_COUNTRY_LHS_lbl_list;
	@FindBy(how = How.ID, using = "mCSB_1_dragger_vertical")
	private WebElement Geography_scrollbar_verticalDragger;

	// ProjectDelivery Filter (PDS)

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'PROJECT_DELIVERY_SYSTEM')]//div[@class='index']")
	private List<WebElement> pdsFilterFacetList;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_DELIVERY_SYSTEM']")
	private WebElement ProjectDeliveryFilter;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_DELIVERY_SYSTEM']/span[contains(@class,'arrow-up')]")
	private WebElement ProjectDeliveryFilterCollapsed;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_DELIVERY_SYSTEM']/span[contains(@class,'arrow-down')]")
	private WebElement ProjectDeliveryFilterExpanded;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'projectDeliverySystemTypeFacet')]")
	private List<WebElement> ProjectDeliveryFilterFacets;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_pdsCon']/div[@class='expendCollaps commonLeftNav']/span[@class='fleft filterHeader']")
	private WebElement PDSFilterName;

	@FindBy(how = How.XPATH, using = "//div[@class='MyFilter ui-sortable']//div[@expandpref='PROJECT_DELIVERY_SYSTEM']")
	private WebElement MyFilterProjectDeliveryCheckElement;

	@FindBy(how = How.XPATH, using = "//div[@class='MyFilter ui-sortable']//div[contains(@class, 'ui-sortable-handle')]//span[contains(@class,'filterHeader')]")
	private List<WebElement> AllMyfilterElemets;

	@FindBy(how = How.XPATH, using = "//a[@class='grpItems']")
	private List<WebElement> FilterCrumb_AppliedFilter_Having_Hyperlink_List;

	@FindBy(how = How.XPATH, using = "//label[contains(@for,'projectDeliverySystemTypeFacet')]")
	private List<WebElement> ProjectDeliveryFilterFacetsName;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_DELIVERY_SYSTEM']/a/img")
	private WebElement PDS_SeeMore_Btn;

	@FindBy(how = How.ID, using = "dvProjectDeliveryCon")
	private WebElement PDSSeeMorePopUp;

	@FindBy(how = How.XPATH, using = "//div[@id='dvProjectDeliveryType']/div[@class='facetCheckBoxes']//input")
	private List<WebElement> pdsSeeMorePopUpListChkBox;

	@FindBy(how = How.ID, using = "projectDeliverySystemTypeFacet_1")
	private List<WebElement> pdsfacetChkBox;

	@FindBy(how = How.ID, using = "projectDeliveryNav")
	private WebElement projectDeliveryHeader;

	// GEOGRAPHY_STATE
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_STATE']")
	private WebElement StateRegionFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_STATE']//span[contains(@class,'arrow-up')]")
	private WebElement GEOGRAPHY_STATE_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_STATE']//span[contains(@class,'arrow-down')]")
	private WebElement GEOGRAPHY_STATE_ArrowDown;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'stateFacet')]")
	private List<WebElement> StateRegionFilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_STATE']/a/img")
	private WebElement GEOGRAPHY_STATESeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@id='dvProjectState']//input[@id='selectAll']")
	private WebElement StateRegionPopupSelectAllStates_checkbox;
	@FindBy(how = How.XPATH, using = "//label[@for='stateFacet_1']")
	private WebElement StateRegion_LHSFirstFilter_lbl;
	@FindBy(how = How.XPATH, using = ".//*[@id='1.1.51']")
	private WebElement StateRegion_LHSLastFilter;
	@FindBy(how = How.XPATH, using = "//span[@original-title= 'Alabama']")
	private WebElement StateRegion_PopupFirstFilter_Alabama_lbl;
	@FindBy(how = How.CSS, using = "span[original-title='California']")
	private WebElement StateRegion_PopupFirstFilter_California_lbl;
	@FindBy(how = How.ID, using = "stateFacet_1")
	private WebElement StateRegin_FirstOption;
	@FindBy(how = How.XPATH, using = "//div[@class='GEOGRAPHY_STATE']//label/span")
	private List<WebElement> StateRegion_ProjectCountNextToElement_List;
	@FindBy(how = How.XPATH, using = "//div[@class='lastLevelLabel']//span")
	private List<WebElement> StateRegion_Popup_allStateslbl;
	@FindBy(how = How.ID, using = "mCSB_2_dragger_vertical")
	private WebElement StateRegion_scrollbar_verticalDragger;

	// GEOGRAPHY_COUNTY
	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_geoCountryCon']/div[1]")
	private WebElement GEOGRAPHY_CountyFilter;
	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_geoCountryCon']/div[1]/span[contains(@class,'arrow-up')]")
	private WebElement GEOGRAPHY_COUNTY_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'countyFacet')]")
	private List<WebElement> GEOGRAPHY_COUNTYFilterList;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_geoCountyCon']/div[1]/a/img")
	private WebElement GEOGRAPHY_COUNTYSeeMore_Btn;
	@FindBy(how = How.ID, using = "projectCountyNav")
	private WebElement GEOGRAPHYPopupprojectCountyNavLink;
	@FindBy(how = How.XPATH, using = "//input[@parent='1.1.1' and starts-with(@value,'USA')]")
	private List<WebElement> COUNTYPopupUASCountyList;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_countyWrapper']//a[text()='Select All']")
	private WebElement Counties_LHS_SelectAll_Btn;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_countyWrapper']//a[text()='Deselect All']")
	private WebElement Counties_LHS_DeselectAll_Btn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='1.1' and @headertext='Alabama']")
	private WebElement GEOGRAPHY_AllCountiesOfAlabamaState_checkbox;
	@FindBy(how = How.XPATH, using = "//div[@id='dvProjectCounty']//input[@id='selectAll']")
	private WebElement GEOGRAPHY_PopupSelectAllCounties_checkbox;
	@FindBy(how = How.ID, using = "mCSB_3_dragger_vertical")
	private WebElement GEOGRAPHY_County_scrollbar_verticalDragger;

	// Valuation
	@FindBy(how = How.XPATH, using = "//div[@expandpref='VALUATION']")
	private WebElement ValuationFilter;
	@FindBy(how = How.ID, using = "valMinimum")
	private WebElement valMinimumDropdown;
	@FindBy(how = How.ID, using = "valMaximum")
	private WebElement valMaximumDropdown;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_valuationListfirst")
	private WebElement valuationListFirstRadio;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_valuationListsecond")
	private WebElement valuationListsecondRadio;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_valuationListthird")
	private WebElement valuationListThirdRadio;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_rptValuationMinRange_ctl01_dvValuationMin")
	private WebElement valMinimumDropdownFirstOption;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_rptValuationMinRange_ctl07_dvValuationMin")
	private WebElement valMinimumDropdownSeventhOption;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_rptValuationMaxRange_ctl02_dvValuationMax")
	private WebElement valMaximumDropdownSecondOption;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_rptValuationMaxRange_ctl08_dvValuationMax")
	private WebElement valMaximumDropdownEightOption;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='VALUATION']//span[contains(@class,'arrow-up')]")
	private WebElement VALUATION_Filter_ArrowUp;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_ValuationController_lblValuationStatus")
	private WebElement ValuationStatus;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_valuation']//label")
	private List<WebElement> VALUATION_Filter_radiobtn_label;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='OWNERSHIP_TYPE']")
	private WebElement OWNERSHIP_TYPE_Filter;

	// ACTION_STAGE_CATEGORY
	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CATEGORY']")
	private WebElement ACTION_STAGE_CATEGORY_Filter;
	@FindBy(how = How.ID, using = "actionStageCatFacet_1")
	private WebElement actionStageCatFacet_1_Ckbox;
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'actionStageCatFacet_')]")
	private List<WebElement> allActionStageCkboxes;
	@FindBy(how = How.ID, using = "actionStageCatFacet_5")
	private WebElement actionStageCatFacet_5_Ckbox;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CATEGORY']/a/img")
	private WebElement ACTION_STAGE_CATEGORYSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='3']")
	private List<WebElement> BiddingOptionsFilterListFromPopup;
	@FindBy(how = How.ID, using = "btnUpdateFancyBox")
	private WebElement ACTION_STAGE_CATEGORYUpdateFancyBoxbtn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'actionStageCatFacet')]")
	private List<WebElement> ACTION_STAGE_CATEGORY_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CATEGORY']//span[contains(@class,'arrow-up')]")
	private WebElement ACTION_STAGE_CATEGORY_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//label[@for='actionStageCatFacet_1']")
	private WebElement ActionStage_LHSFirstParentElement_lbl;
	@FindBy(how = How.XPATH, using = "//div[@class='ACTION_STAGE_CATEGORY']//label")
	private List<WebElement> ACTION_STAGE_CATEGORYLHS_LabelList;

	// Find In Filter
	@FindBy(how = How.CLASS_NAME, using = "contenttype")
	private List<WebElement> FindIn_ContentType;

	@FindBy(how = How.XPATH, using = "//*[@class='contenttype']//..//label")
	private List<WebElement> FindInFilterLabel;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='FIND_IN']//span[contains(@class,'arrow-up')]")
	private WebElement FindIn_ContentType_collapsedPnl;

	// Action Stage2 code
	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CODE']")
	private WebElement ACTION_STAGE_CODE_Filter;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_actStgCodeCon']/div[1]/a/img")
	private WebElement ACTION_STAGE2_SeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CODE']//span[contains(@class,'arrow-up')]")
	private WebElement ACTION_STAGE_CODE_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_StageChildWrapper']//a[text()='Select All']")
	private WebElement actionStage_Code_SelectAllLink;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_StageChildWrapper']//a[text()='Deselect All']")
	private WebElement actionStage_Code_DeselectAll_Link;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'actionStageCodeFacet')]")
	private List<WebElement> actionStageCodeFacetList;
	@FindBy(how = How.XPATH, using = "//div[@class='ACTION_STAGE_CODE']//label")
	private List<WebElement> ACTION_STAGE_CODELHS_LabelList;

	// Project Groups
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']")
	private WebElement PROJECT_TYPE_CATEGORY_Filter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='Project Types']")
	private WebElement PROJECT_TYPE_Filter;
	@FindBy(how = How.ID, using = "projTypeCatFacet_1")
	private WebElement projTypeCatFacet_1_Ckbox;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']/a/img")
	private WebElement ProjectGroupsSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='2']")
	private List<WebElement> ProjectGroupsParent2FilterList;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'projTypeCatFacet')]")
	private List<WebElement> ProjectGroups_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']//span[contains(@class,'arrow-up')]")
	private WebElement ProjectGrups_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']//span[contains(@class,'arrow-down')]")
	private WebElement ProjectGrups_Filter_ArrowDown;
	@FindBy(how = How.XPATH, using = "//input[contains(@headertext,'Primary')]")
	private WebElement ProjGrpPopupEducationPrimaryChk;
	@FindBy(how = How.ID, using = "mCSB_8_dragger_vertical")
	private WebElement ProjGrp_scrollbar_verticalDragger;
	@FindBy(how = How.ID, using = "mCSB_12_scrollbar_vertical")
	private WebElement constructionTypeScrollbarVerticalDragger;

	@FindBy(how = How.XPATH, using = "//div[@class='PROJECT_TYPE_CATEGORY']//label")
	private List<WebElement> ProjectGroupsLHS_LabelList;
	@FindBy(how = How.XPATH, using = "//input[contains(@headertext,'Custom')]")
	private WebElement ProjGrpPopupCustomHomesyChk;
	@FindBy(how = How.XPATH, using = "//span[@original-title='Custom Homes']")
	private WebElement ProjGrpPopupCustomHomesLbl;

	// Project Types

	// div[@expandpref='PLAN_SHEET_CLASS']/a/img
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CODE']")
	private WebElement PROJECT_TYPE_CODE_Filter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CODE']/a/img")
	private WebElement ProjectTypesSeeMore_Btn;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PLAN_SHEET_CLASS']/a/img")
	private WebElement planClassSheetSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CODE']//span[contains(@class,'arrow-up')]")
	private WebElement ProjectTypes_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'projTypeCodeFacet')]")
	private List<WebElement> ProjectTypes_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_ProjTypeChildWrapper']//a[text()='Select All']")
	private WebElement ProjectTypesSelectAll_Btn;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_ProjTypeChildWrapper']//a[text()='Deselect All']")
	private WebElement ProjectTypesDeselectAll_Btn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='2.3']")
	private List<WebElement> CommercialOptionsFilterListFromPopup;
	@FindBy(how = How.XPATH, using = "//*[@id='dvProjectType']//span[@original-title='Bank']")
	private WebElement ProjTypePopupCommercialParents_FirstOption_lbl;
	@FindBy(how = How.XPATH, using = "//label[@for='projTypeCodeFacet_1']")
	private WebElement ProjTypeLHS_FirstOption_lbl;
	@FindBy(how = How.ID, using = "projTypeCodeFacet_1")
	private WebElement ProjTypeLHS_FirstOption_cbk;
	@FindBy(how = How.XPATH, using = "//div[@class='PROJECT_TYPE_CODE']//label")
	private List<WebElement> ProjectTypesLHS_LabelList;
	@FindBy(how = How.XPATH, using = "//*[@id='dvProjectType']//div[@class='subSectionLabel']/span")
	private List<WebElement> ProjTypePopup_subSection_LabelList;

	// Profile Filter
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_appliedProfile")
	private WebElement PROFILE_Filter;
	@FindBy(how = How.LINK_TEXT, using = "Turn Off")
	private WebElement turnOffProfileLink;
	@FindBy(how = How.LINK_TEXT, using = "Turn On")
	private WebElement turnOnProfileLink;

	// TrackingList Filter
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRACKING_LIST']")
	private WebElement trackingListFilter;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_lblTrackingListName")
	private WebElement trackingList;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_trackingListChildWrapper']/div[1]/label")
	private WebElement excludeTL_lbl;
	@FindBy(how = How.ID, using = "excludeTL")
	private WebElement excludeTL_ckbox;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'trackingListFacet')]")
	private List<WebElement> trackingListFacetList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRACKING_LIST']//span[contains(@class,'arrow-up')]")
	private WebElement TRACKING_LIST_ArrowUpFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRACKING_LIST']//span[contains(@class,'arrow-down')]")
	private WebElement TRACKING_LIST_ArrowDownFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRACKING_LIST']/a/img")
	private WebElement TRACKING_LIST_SeeMore_btn;
	@FindBy(how = How.XPATH, using = "//div[@id='dvTrackingList']//input[@id='']")
	private List<WebElement> trackingList_PopupOptionList;
	@FindBy(how = How.XPATH, using = "//div[@id='dvTrackingList']//span")
	private List<WebElement> trackingList_PopupOption_lblList;
	@FindBy(how = How.XPATH, using = ".//*[@id='dvTrackingList']/div[2]//div//div//div/img")
	private List<WebElement> trackingList_PopupOption_lblimgdiv;
	@FindBy(how = How.XPATH, using = "//label[@for='trackingListFacet_1']")
	private WebElement trackingList_LHSFirstOption_lbl;
	@FindBy(how = How.ID, using = "trackingListFacet_1")
	private WebElement trackingList_LHSFirstOption_cbk;

	@FindBy(how = How.ID, using = "chkBoxTrack")
	private WebElement exclude_SeeMore_chkbox;

	// Specalert Filter
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPEC_ALERT']")
	private WebElement specAlertFilter;
	@FindBy(how = How.XPATH, using = "//label[text()='All Carpet']/..//input[@type='checkbox']")
	private WebElement carpetSpecAlertchkbox;
	@FindBy(how = How.XPATH, using = "//label[text()='Carpet - Mohawk']/..//input[@type='checkbox']")
	private WebElement carpetMohawkSpecAlertchkbox;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'targetLeadsFacet')]")
	private List<WebElement> specAlertFilterList;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_specAlertChildWrapper']/div[1]/label")
	private WebElement excludeSpecAlert_lbl;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPEC_ALERT']//span[contains(@class,'arrow-up')]")
	private WebElement SPEC_ALERT_ArrowUpFilter;
	@FindBy(how = How.XPATH, using = "//div[@class='SPEC_ALERT']//span")
	private List<WebElement> specAlertFilterCount;
	@FindBy(how = How.XPATH, using = "//div[@class='SPEC_ALERT']//span//parent::label")
	private WebElement specAlertFilterCountLabel;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_lblProjectTitle']//ancestor::div[contains(@class,'project-result')]//div[2]//div[contains(@class,'specProgramNamesContainer')]//div[@class='specAlertProgramNames']//a")
	private List<WebElement> specAlertsInProjectSummaryList;
	@FindBy(how = How.ID, using = "btnUpdateFancyBox")
	private WebElement seeMoreSpecAlertsUpdateBtn;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_specAlertFilter']//a/img")
	private WebElement specAlerts_SeeMore_Popup_btn;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_pdsCon']//a/img")
	private WebElement pds_SeeMore_Popup_btn;
	@FindBy(how = How.XPATH, using = "//*[@class='facetCheckBoxes']//*[@class='lastLevelValues clearfix']")
	private List<WebElement> specAlertsCheckBoxTextList;
	@FindBy(how = How.XPATH, using = "//a[@class='tcviewer-popup-close']")
	private WebElement btnCancelMerx;
	@FindBy(how = How.CSS, using = "div[id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_specAlertFiltercontent'] div[class='index'] input")
	private List<WebElement> specAlertCheckBoxes;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_ownershipTypeCon']//a/img")
	private WebElement ownerShipType_SeeMore_Popup_btn;
	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecAlert']/div[@class='facetCheckBoxes']//input")
	private List<WebElement> specAlertSeeMorePopUpListChkBox;
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'appliedFilter')]//span[@title='Spec Alert']")
	private List<WebElement> specAlertFewAppliedFilter;
	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecAlert']/div[2]/div/div[1]/div[1]/div[1]/input")
	private WebElement specAlertSeeMorePopUpFirstOption_lbl;
	@FindBy(how = How.XPATH, using = "//div[@id='mCSB_10_container']/div/div/label")
	private WebElement specAlertLHSFirstOption_lbl;
	@FindBy(how = How.ID, using = "targetLeadsFacet_1")
	private WebElement specAlertLHSFirstOption_cbk;
	@FindBy(how = How.ID, using = "chkBoxTargetLead")
	private WebElement exclude_SeeMore_Specalert_chkbox;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_specAlertChildWrapper']//span[@title='Exclude projects that are matched to the selected programs']")
	private WebElement specAlertFilter_Exclude_SpecAlerts_tooltip;

	@FindBy(how = How.ID, using = "exclude")
	private WebElement excludeChkbox;

	// Special Filter
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_FILTERS']")
	private List<WebElement> specialFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_FILTERS']//span[contains(@class,'arrow-up')]")
	private WebElement specialFilterArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_SpclFilterDiv']//div[contains(@class,'spclFilterLebel')]//label[contains(@class,'InOutval')]")
	private List<WebElement> specialFilterOptionsList;
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and starts-with(@id,'ctl00_contentPlaceHolderHeader_leftNavFilterUI_rb')]")
	private List<WebElement> specialFilter_LHS_ParentFilter_cbkList;

	// Special conditions
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_CONDITION']")
	private WebElement SPECIAL_CONDITIONFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_CONDITION']//span[contains(@class,'arrow-up')]")
	private WebElement SPECIAL_CONDITIONArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPECIAL_CONDITION']/a/img")
	private WebElement SPECIAL_CONDITIONSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//label[@for='specialCoditionFacet_1']")
	private WebElement SPECIAL_Conditions_LHSFirstFilter_lbl;
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'specialCoditionFacet')]")
	private List<WebElement> SPECIAL_Conditions_LHSFilterList;
	@FindBy(how = How.ID, using = "specialCoditionFacet_1")
	private WebElement SPECIAL_Conditions_LHSFirstOption_cbk;

	// Spec Division
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPEC_DIVISION']")
	private WebElement specDivisionFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPEC_DIVISION']//span[contains(@class,'arrow-up')]")
	private WebElement SPEC_DIVISION_ArrowUpFilter;
	@FindBy(how = How.ID, using = "specDivisionNav")
	private WebElement specDivisionNav_RefineYourResults_txt;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SPEC_DIVISION']/a/img")
	private WebElement specDivision_SeeMore_Popup_btn;
	@FindBy(how = How.ID, using = "Button1")
	private WebElement specDivisionNav_UpdateResults_btn;
	@FindBy(how = How.XPATH, using = "//div[@id='specdivMessage']/span")
	private List<WebElement> specDivisionSeeMorePopupFooterTxt;
	@FindBy(how = How.XPATH, using = "//a[@class='toggleTree']/span[@class='fa fa-plus']")
	private List<WebElement> specDivision_SeeMore_Popup_SpecGroupsAreCollapsed;
	@FindBy(how = How.XPATH, using = "//a[@class='toggleTree toggle']/span[@class='fa fa-minus']")
	private List<WebElement> specDivision_SeeMore_Popup_SpecGroupsAreExpandeded;
	@FindBy(how = How.XPATH, using = "//label[text()='Addenda']/..//input[@type='checkbox']")
	private WebElement addendaSpecDivisionchkbox;
	@FindBy(how = How.XPATH, using = "//label[text()='Procurement And Contracting Requirements']/..//input[@type='checkbox']")
	private WebElement procurementSpecDivisionchkbox;
	@FindBy(how = How.XPATH, using = "//span[@class='fa fa-minus']")
	private List<WebElement> specDivisionFilterPopup_IS_ExpandAll;
	@FindBy(how = How.XPATH, using = "//span[@class='fa fa-plus']")
	private List<WebElement> specDivisionFilterPopup_IS_CollapseAll;
	@FindBy(how = How.LINK_TEXT, using = "Expand All")
	private WebElement specDivisionPopup_ExpandAll_Link;
	@FindBy(how = How.LINK_TEXT, using = "Collapse All")
	private WebElement specDivisionPopup_CollapseAll_Link;
	@FindBy(how = How.CSS, using = "a[class*='SpecDivFancy']")
	private WebElement specDivisionSpecDivFancyBtn;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'specDivisionFacet')]")
	private List<WebElement> specDivisionFilterList;
	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecDiv']//span[contains(@class,'iconCheck')]")
	private List<WebElement> specDivisionFilterListPopup;
	@FindBy(how = How.XPATH, using = ".//*[@id='dvSpecDiv']//div[contains(@class,'subSectionChkBox')]//input")
	private List<WebElement> specDivisionFilterCheckboxListPopup;
	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecDiv']//div[@class='37']//div[@class='subSectionChkBox']/span")
	private WebElement specDivisionFilterPopup_LastCheckbox_lbl;
	@FindBy(how = How.XPATH, using = "//label[@for='specDivisionFacet_1']")
	private WebElement specDivisionFilterLHS_FirstCheckbox_lbl;
	@FindBy(how = How.XPATH, using = "//label[@for='hideShow']/span")
	private WebElement specDivisionPopup_HideShowCheckbox;
	@FindBy(how = How.ID, using = "G0050_2")
	private WebElement specDivisionPopup_ThirdCheckbox;
	@FindBy(how = How.XPATH, using = "//span[@class='hasCountBlue']")
	private List<WebElement> specDivisionFilterPopup_lbl_list;
	@FindBy(how = How.ID, using = "mCSB_4_dragger_vertical")
	private WebElement SpecDivision_scrollbar_verticalDragger;

	// Trades Filter
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CATEGORY']")
	private WebElement TRADE_TYPE_CATEGORY_Filter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CATEGORY']/a/img")
	private WebElement TradesSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'itemTradeCategoryFacet')]")
	private List<WebElement> Trades_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CATEGORY']//span[contains(@class,'arrow-up')]")
	private WebElement Trades_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//label[@for='itemTradeCategoryFacet_1']")
	private WebElement TradeCategoryFacet_LHSFirstFilter_lbl;
	@FindBy(how = How.ID, using = "itemTradeCategoryFacet_1")
	private WebElement TradeCategoryFacet_LHSFirstOption_cbk;
	@FindBy(how = How.XPATH, using = "//div[@class='TRADE_TYPE_CATEGORY']//span")
	private List<WebElement> Trades_LHS_ProjectwiseCountList;
	@FindBy(how = How.XPATH, using = "//div[@class='TRADE_TYPE_CATEGORY']//label")
	private List<WebElement> Trades_LHS_ProjectwiseTitleList;

	// Specific Trade
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CODE']")
	private WebElement SpecificTRADE_Filter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CODE']/a/img")
	private WebElement SpecificTradesSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='TRADE_TYPE_CODE']//span[contains(@class,'arrow-up')]")
	private WebElement TRADE_TYPE_CODE_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'itemTradeCodeFacet')]")
	private List<WebElement> SpecificTrades_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='3']")
	private List<WebElement> SpecificTrades_BuildingUtilities_PopopFilterList;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_TradeChildWrapper']//a[text()='Select All']")
	private WebElement SpecificTradesSelectAll_Btn;
	@FindBy(how = How.XPATH, using = "//div[@class='TRADE_TYPE_CODE']//span")
	private List<WebElement> SpecificTrades_LHS_ProjectwiseCountList;
	@FindBy(how = How.XPATH, using = "//div[@class='TRADE_TYPE_CODE']//label")
	private List<WebElement> SpecificTrades_LHS_ProjectwiseTitleList;

	// Materials_Equip
	@FindBy(how = How.XPATH, using = "//div[@expandpref='MES_EQUIP_CATEGORY']")
	private WebElement materialsEquipFilter;
	@FindBy(how = How.ID, using = "mesCategoryFacet_1")
	private WebElement materialsEquipFilter_FirstOption;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_mesTypeCategoryCon']/div[1]/span[2]")
	private WebElement materialsEquipFilterClosedOnLoad;
	@FindBy(how = How.XPATH, using = "//label[text()='Contractors Equip']/..//input[@type='checkbox']")
	private WebElement contractorsEquipElementMaterialsEquipChkbox;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_mesTypeCategory']")
	private WebElement materialsEquipFilterSubOptions;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_mesTypeCategoryCon']/div[@expandpref='MES_EQUIP_CATEGORY']/a/img")
	private WebElement materialsEquipFilterFacet;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'mesCategoryFacet')]")
	private List<WebElement> materialsEquipFilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='MES_EQUIP_CATEGORY']/a/img")
	private WebElement Materials_EquipSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='MES_EQUIP_CATEGORY']//span[contains(@class,'arrow-up')]")
	private WebElement MES_EQUIP_CATEGORY_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'mesCategoryFacet')]")
	private List<WebElement> materialsEquip_LHS_FilterList;
	@FindBy(how = How.XPATH, using = "//label[@for='mesCategoryFacet_1']")
	private WebElement materialsEquip_LHSFirstFilter_lbl;
	@FindBy(how = How.XPATH, using = "//div[@class='MES_EQUIP_CATEGORY']//label")
	private List<WebElement> materialsEquipLHS_LabelList;

	// Materials_Equip2
	@FindBy(how = How.XPATH, using = "//div[@expandpref='MES_EQUIP_CODE']")
	private WebElement materialsEquip2Filter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='MES_EQUIP_CODE']//span[contains(@class,'arrow-up')]")
	private WebElement MES_EQUIP2_Code_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'mesCodeFacet')]")
	private List<WebElement> materialsEquip2_LHS_FilterList;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @parent='2']")
	private List<WebElement> BuildingCLG_WaterProofing_FilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='MES_EQUIP_CODE']/a/img")
	private WebElement Materials_Equip2SeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_mesChildWrapper']//a[text()='Select All']")
	private WebElement Materials_Equip2SelectAll_Link;
	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_leftNavFilterUI_mesChildWrapper']//a[text()='Deselect All']")
	private WebElement Materials_Equip2DeselectAll_Link;
	@FindBy(how = How.XPATH, using = "//div[@class='MES_EQUIP_CODE']//label")
	private List<WebElement> materialsEquip2LHS_LabelList;

	// STRUCTURAL_PROPERTIES
	@FindBy(how = How.XPATH, using = "//div[@expandpref='STRUCTURAL_PROPERTIES']")
	private WebElement STRUCTURAL_PROPERTIESFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='STRUCTURAL_PROPERTIES']//span[contains(@class,'arrow-up')]")
	private WebElement STRUCTURAL_PROPERTIES_ArrowUp;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_structuralPropLink")
	private WebElement StructuralPropLink;
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'ucStructuralPropertiesNav')]")
	private List<WebElement> StructuralPropertiesSectionList;
	@FindBy(how = How.ID, using = "ucStructuralPropertiesNav_lblSquare")
	private WebElement SquareArealbl;
	@FindBy(how = How.ID, using = "ucStructuralPropertiesNav_lblNoOfBuildings")
	private WebElement NoOfBuildingslbl;
	@FindBy(how = How.ID, using = "ucStructuralPropertiesNav_lblStoriesAboveGrade")
	private WebElement StoriesAboveGradelbl;
	@FindBy(how = How.ID, using = "ucStructuralPropertiesNav_lblStoriesBelowGrade")
	private WebElement StoriesBelowGradelbl;
	@FindBy(how = How.ID, using = "ucStructuralPropertiesNav_lblBuildingFrame")
	private WebElement BuildingFramelbl;
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'ucStructuralPropertiesNav')]")
	private List<WebElement> StructuralPropertiesCheckboxList;
	@FindBy(how = How.XPATH, using = "//*[@class='MinLimit buildingFrameText']//input[@type='checkbox']")
	private List<WebElement> buildingFrameCheckboxList;
	@FindBy(how = How.XPATH, using = "//*[@class='MinLimit buildingFrameText']//*[@class='buildingFrameLabel']")
	private List<WebElement> buildingFrameCheckboxTextList;
	@FindBy(how = How.XPATH, using = "//*[@class='facetCheckBoxes']//*[@class='lastLevelValues clearfix']")
	private List<WebElement> planSheetCheckboxTextList;

	@FindBy(how = How.XPATH, using = "//select[starts-with(@id,'ucStructuralPropertiesNav_ddl')]")
	private List<WebElement> StrucPropDDList;
	@FindBy(how = How.XPATH, using = "//span[@id='lblStructuralErrorStatus']")
	private WebElement StructuralErrorStatuslbl;

	// Construction Type filter
	@FindBy(how = How.XPATH, using = "//div[@expandpref='CONSTRUCTION_TYPE']")
	private WebElement CONSTRUCTION_TYPEFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='CONSTRUCTION_TYPE']//span[contains(@class,'arrow-up')]")
	private WebElement CONSTRUCTION_TYPE_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='CONSTRUCTION_TYPE']/a/img")
	private WebElement CONSTRUCTION_TYPESeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//label[@for='constructionTypeFacet_1']")
	private WebElement ConstructionType_LHSFirstFilter_lbl;
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'constructionTypeFacet')]")
	private List<WebElement> ConstructionType_LHSFilterList;
	@FindBy(how = How.XPATH, using = "//div[@class='CONSTRUCTION_TYPE']//label")
	private List<WebElement> CONSTRUCTION_TYPEFilter_LHS_lblList;
	@FindBy(how = How.XPATH, using = "//div[@class='CONSTRUCTION_TYPE']//label/span")
	private List<WebElement> CONSTRUCTION_TYPEFilter_LHS_lblCountList;
	@FindBy(how = How.XPATH, using = "//label[contains(@for, 'constructionTypeFacet')]")
	private List<WebElement> ConstructionType_LHSFilterListName;
	@FindBy(how = How.ID, using = "constructionTypeFacet_1")
	private WebElement ConstructionType_LHSFirstOption_cbk;

	// Ownership Type filter
	@FindBy(how = How.XPATH, using = "//div[@expandpref='OWNERSHIP_TYPE']")
	private WebElement OWNERSHIP_TYPEFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='OWNERSHIP_TYPE']//span[contains(@class,'arrow-up')]")
	private WebElement OWNERSHIP_TYPE_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='OWNERSHIP_TYPE']/a/img")
	private WebElement OWNERSHIP_TYPESeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//label[@for='ownershipTypeFacet_1']")
	private WebElement OWNERSHIP_TYPE_LHSFirstFilter_lbl;
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'ownershipTypeFacet')]")
	private List<WebElement> OWNERSHIP_TYPE_LHSFilterList;
	@FindBy(how = How.ID, using = "ownershipTypeFacet_1")
	private WebElement OWNERSHIP_TYPE_LHSFirstOption_cbk;
	@FindBy(how = How.ID, using = "ownershipTypeFacet_4")
	private WebElement OWNERSHIP_TYPE_LHSFederalOption_cbk;
	@FindBy(how = How.XPATH, using = "//div[@class='OWNERSHIP_TYPE']//label/span")
	private List<WebElement> OWNERSHIP_TYPEFilter_LHS_ProjectCountNextToElement_List;
	@FindBy(how = How.XPATH, using = "//div[@class='OWNERSHIP_TYPE']//label")
	private List<WebElement> OWNERSHIP_TYPEFilter_LHS_lblList;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_indicatorProjects']/img")
	private WebElement projectIconImage;

	@FindBy(how = How.CLASS_NAME, using = "listProjectCount")
	private WebElement projectCountTxt;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='FIND_IN']//span[contains(@class,'arrow-up')]")
	private WebElement findInArrowUpFilter;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='FIND_IN']")
	private WebElement findInFilter;

	@FindBy(how = How.XPATH, using = "//label[text()='Plans']/..//input[@type='checkbox']")
	private WebElement plansOptionFindInFilterChkbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Specs']/..//input[@type='checkbox']")
	private WebElement specsOptionFindInFilterChkbox;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PLAN_SHEET_CLASS']//span[contains(@class,'arrow-up')]")
	private WebElement planClassSheetArrowUpFilter;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PLAN_SHEET_CLASS']")
	private WebElement planClassSheetFilter;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'planSheetFacet')]")
	private List<WebElement> planClassSheetFilterList;

	@FindBy(how = How.XPATH, using = "//div[@class='expendCollaps commonLeftNav']")
	private List<WebElement> projectFilterOptionList;

	@FindBy(how = How.XPATH, using = "//span[@class='ui-datepicker-month']")
	private WebElement calenderMonthString;

	@FindBy(how = How.XPATH, using = "//span[@class='ui-datepicker-year']")
	private WebElement calenderYearString;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'ui-icon-circle-triangle-e')]")
	private WebElement navigateCalenderNextMonth;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'ui-icon-circle-triangle-w')]")
	private WebElement navigateCalenderPrevMonth;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_divAll']//div[contains(@class,'expendCollaps')]//span[contains(@class,'filterHeader')]")
	private List<WebElement> leftExpandCollapseList;

	// My Tracking Lists
	@FindBy(how = How.XPATH, using = "//a[text()='Hidden Projects']/../..//strong[text()='Tracking Lists: ']")
	private WebElement hiddenProjectTrackingNameResult;

	// My Tracking Lists
	@FindBy(how = How.XPATH, using = "//a[text()='My Projects']/../..//strong[text()='Tracking Lists: ']")
	private WebElement myProjectTrackingNameResult;

	@FindBy(how = How.XPATH, using = "//a[text()='My Projects']/../..//strong[text()='Tracking Lists: ']")
	private List<WebElement> myProjectTrackingNameResultList;

	@FindBy(how = How.XPATH, using = "//a[text()='My Companies']/../..//strong[text()='Tracking Lists: ']")
	private WebElement myCompaniesTrackingNameResult;

	@FindBy(how = How.LINK_TEXT, using = "HOME")
	private WebElement homeMenuLink;

	// Section visualization
	@FindBy(how = How.XPATH, using = ".//*[@id='sectionmapContainer']/svg/g[1]/path[contains(@class,'datamaps-subunit CA')]")
	private WebElement firstStateInMap;

	// ProjectTypeFilterList
	@FindBy(how = How.XPATH, using = "//*[@id='LUBAR']/div/div/div[contains(@class,'barWrapper')]")
	private List<WebElement> projectTypeFilterList;

	@FindBy(how = How.XPATH, using = "//*[@id='LUBAR']/div/div/div[contains(@class,'CountWrapper ')]")
	private List<WebElement> projectTypeCountList;

	@FindBy(how = How.XPATH, using = "//*[@id='RUBAR']/div/div/div[contains(@class,'CountWrapper ')]")
	private List<WebElement> projectTypeRightCountList;

	@FindBy(how = How.XPATH, using = "//*[@id='RUBAR']/div/div/div[contains(@class,'barWrapper')]")
	private List<WebElement> actionStageTypeFilterList;

	@FindBy(how = How.XPATH, using = "//*[@id='LUBAR']/div/div/div[contains(@class,'barWrapper')]")
	private List<WebElement> divisionTypeFilterList;

	@FindBy(how = How.XPATH, using = "//*[@id='LUDONUT']/*[name()='svg']/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][4]/*[name()='g'][3]/*  [name()='g']/*[name()='path']")
	private WebElement chartConstructionTypeInteriors;

	@FindBy(how = How.XPATH, using = "//*[name()='svg']/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][4]/*[name()='g'][4]/*  [name()='g']/*[name()='path']")
	private WebElement chartConstructionTypeAdditions;

	@FindBy(how = How.XPATH, using = "//*[@id='LUDONUT']/*[name()='svg']/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][4]/*[name()='g'][1]/*  [name()='g']/*[name()='path']")
	private WebElement chartConstructionTypeAlterations;

	@FindBy(how = How.XPATH, using = "//*[@id='LUDONUT']/*[name()='svg']/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][4]/*[name()='g'][2]/*  [name()='g']/*[name()='path']")
	private WebElement chartConstructionTypeNewProject;

	@FindBy(how = How.XPATH, using = "//*[@id='LUDONUT']/*[name()='svg']/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][4]/*[name()='g']")
	private List<WebElement> chartConstructionTypes;

	@FindBy(how = How.CSS, using = "[class*='fleft'] [id='LUDONUT']>svg>g:nth-of-type(1)>g[class='c3-chart']>g[class='c3-chart-arcs'] path[class*='arc-Private']")
	private WebElement chartOwnershipType;

	@FindBy(how = How.XPATH, using = "//span[@class='chartHead' and text()='Construction Type']")
	private WebElement chartHeadConstructionType;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'Engineering')]")
	private WebElement barChartProjectTypeEngineering;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'All Carpet')]")
	private WebElement barChart1SpecAlerts;
	@FindBy(how = How.XPATH, using = "//div[@id='RUBAR']//div[contains(@desc,'Engineering')]")
	private WebElement barChartProjectTypeEngineeringRightView;

	@FindBy(how = How.XPATH, using = "//div[contains(@desc,'Design')]")
	private WebElement barChartActionStageDesign;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']/div[1]/div/div/div[2]/div/div[2]/div/div[2]/a[2]/span")
	private WebElement barChart1ActionStageDesign;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']/div[2]/div/div/div[2]/div/div[2]/div/div[2]/a[2]/span")
	private WebElement barChart2ActionStageDesign;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'Utilities')]")
	private WebElement barChartProjectTypeUtilities;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'General Building')]")
	private WebElement barChartProjectTypeGeneralBuilding;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'Procurement And Contracting Requirements')]")
	private WebElement firstDivisionInDivisionChart;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'Submittal Procedures')]")
	private WebElement firstSectionInSectionsChart;

	@FindBy(how = How.XPATH, using = "//div[@id='RUBAR']//div[contains(@desc,'Procurement And Contracting Requirements')]")
	private WebElement firstDivisionInRightDivisionChart;

	@FindBy(how = How.XPATH, using = ".//*[@id='NEW']/div[1]")
	private WebElement newTypeInConstructionType;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//span[contains(@class,'subheader')]")
	private List<WebElement> chartsubheader;

	@FindBy(how = How.XPATH, using = "//div[@id='LUBAR']//div[contains(@desc,'Housing')]")
	private WebElement barChartProjectTypeHousing;

	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'WA')]")
	private WebElement mapChartProjectDensityWashington;

	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'MT')]")
	private WebElement mapChartProjectDensityMontana;

	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][2]/*[name()='text']")
	private List<WebElement> smallStates;

	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'MB')]")
	private WebElement mapChartManitobaState;

	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']//div[contains(@class,'datamaps-hoverover')]")
	private WebElement mapTooltip;

	@FindBy(how = How.XPATH, using = "//div[@id='LUDONUT']//div[contains(@class,'tooltip')]")
	private WebElement constructionTypetooltip;

	@FindBy(how = How.XPATH, using = "//div[@id='BLMAP']/*[name()='svg']/*[name()='g'][1]/*[name()='path'][contains(@class,'TX')]")
	private WebElement mapChartProjectDensityTexas;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[contains(@class,'greyBorder')]//span[contains(@class,'chartHead')]")
	private List<WebElement> mapTitles;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_ChartControlView2_ChartContainer_chartWrapper']/div[@class='errorHoldar']/div")
	private WebElement ProjectDensitybyLocation_ErrorMsg;
	// @FindBy(how = How.XPATH, using =
	// ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_ChartControlView1_ChartContainer_chartWrapper']/div[@class='errorHoldar']/div")
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_ChartControl_ChartContainer_chartWrapper']/div[@class='errorHoldar']")
	private WebElement SectionChart_ErrorMsg;

	// Dashboard1
	@FindBy(how = How.ID, using = "countText")
	private WebElement Dashboard1_FirstDD;
	@FindBy(how = How.XPATH, using = ".//*[@id='sortdropdownlist']/div[@data-range-value='CLH']")
	private WebElement Dashboard1_FirstDD_CLH;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblProjectTitle')]")
	private List<WebElement> projectTitleList;

	@FindBy(how = How.XPATH, using = "//*[@class='fleft filterPanel']//*[@class='txtBold']")
	private List<WebElement> appliedFilterTexts;

	@FindBy(how = How.XPATH, using = "//input[@value='HardwareAUTOOPSTANLEY']")
	private WebElement CheckBoxSpecAlertOption;

	@FindBy(how = How.XPATH, using = "(//div[@id='ctl00_contentPlaceHolderHeader_dvReportContent']//h2[not(img)])[2]")
	private WebElement projectDRNumber;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_dvReportContent']/div[1]/div[2]/table/tbody/tr[1]/td/h2")
	private WebElement projectDRNum;

	@FindBy(how = How.ID, using = "txtFileName")
	private WebElement savedSingleFileTextField;

	@FindBy(how = How.ID, using = "lnkDownloadFirms")
	private WebElement downloadFirmActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_searchText")
	private WebElement searchTxtField;

	@FindBy(how = How.CSS, using = "#actionStageCatFacet_2")
	private WebElement designCheckBox;

	@FindBy(how = How.LINK_TEXT, using = "Action Stage Type")
	private WebElement clickonActionStage;

	@FindBy(how = How.ID, using = "actionStageCatFacet_4")
	private WebElement clickonPre_Designchbox;

	@FindBy(how = How.CSS, using = "div[expandpref='FIND_IN']>span:nth-of-type(1)")
	private WebElement filterln;

	@FindBy(how = How.CSS, using = "div[expandpref='PUBLISH_RANGE']>span:nth-of-type(1)")
	private WebElement publishedRange;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']")
	private WebElement expandProjectGroup;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']//span[contains(@class,'arrow-up')]")
	private WebElement ProjectType_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'projTypeCatFacet')]")
	private List<WebElement> ProjectType_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = ".//div[@class='appliedFilter filterPanelCon']//span//span//a")
	private WebElement showMoreControl;

	@FindBy(how = How.ID, using = "ctl00_lnkSpecs")
	private WebElement specLink;

	@FindBy(how = How.ID, using = "lnkSaveProjects")
	private WebElement downloadProjectActionMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_leftNavFilterUI_news")
	private WebElement deselectNewCheckbox;

	@FindBy(how = How.XPATH, using = ".//*[@id='pubRangList']//span[@class='arrowpng']")
	private WebElement result;

	@FindBy(how = How.XPATH, using = ".//*[@id='publist']//div[@id='ctl00_contentPlaceHolderHeader_Publishdate_rptPublishDateRange_ctl09_dvPubDateRange']//a")
	private WebElement clickOn3MonthPrior;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs')]")
	private WebElement displaySpecLink;

	@FindBy(how = How.XPATH, using = ".//*[@class='pr-top-right']//ul//li//span[contains(@id,'lblPublishDate')]")
	private WebElement displayPublishedDate;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fleft dashboardleft LU']//span[contains(.,'Project Type') and @class='chartHead']")
	private WebElement chartLeftContainsProjectType;

	// Added for Project Groups
	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fleft dashboardleft LU']//span[contains(.,'Project Type Groups') and @class='chartHead']")
	private WebElement chartLeftContainsProjectTypeGroups;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fleft dashboardleft LU']//span[contains(.,'Project Type Categories') and @class='chartHead']")
	private WebElement chartLeftContainsProjectTypeCategories;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fleft dashboardleft LU']//span[contains(.,'Project Types') and @class='chartHead']")
	private WebElement chartLeftContainsProjectTypes;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fright dashboardright RU']//span[contains(.,'Project Type Groups') and @class='chartHead']")
	private WebElement chartRightContainsProjectTypeGroups;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fright dashboardright RU']//span[contains(.,'Project Type Categories') and @class='chartHead']")
	private WebElement chartRightContainsProjectTypeCategories;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fright dashboardright RU']//span[contains(.,'Project Types') and @class='chartHead']")
	private WebElement chartRightContainsProjectTypes;

	// @FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fleft
	// dashboardleft LU']//span[contains(.,'Project Type Groups') and
	// @class='chartHead']")
	// private WebElement projectGroupChartHeading;
	// div[@class='chartArea chartTiles']//span[contains(.,'Project Groups')][1]

	@FindBy(how = How.XPATH, using = ".//input[@id='ptType']")
	private List<WebElement> projectTypesRadioButton;

	@FindBy(how = How.XPATH, using = ".//input[@id='ptGroup']")
	private List<WebElement> projectGroupsRadioButton;

	@FindBy(how = How.XPATH, using = ".//input[@id='ptCategory']")
	private List<WebElement> projectCategoriesRadioButton;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fleft dashboardleft LU']")
	private WebElement leftChartSelectionVisualizationScreen;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fright dashboardright RU']")
	private WebElement rightChartSelectionVisualizationScreen;

	@FindBy(how = How.XPATH, using = ".//div[@class='chartcontainerBg fright dashboardright RU']//span[contains(.,'Division')]")
	private WebElement chartRightContainsDivision;

	@FindBy(how = How.XPATH, using = ".//div[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[@class='chartcontainerBg fleft dashboardleft LU']//a//span[contains(.,'Sections')]")
	private List<WebElement> sectionsTypeCategoryInDashboardLeft;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_ucVisualization_chartDashboard']//div[contains(@class,'chartcontainerBg fleft dashboardleft LU')]//input[contains(@id,'contentPlaceHolderHeader_leftNavFilterUI_Text1')]")
	private WebElement sectionFindInfilterLeft;

	@FindBy(how = How.XPATH, using = "//a[@class='DrawerAnchor']")
	private WebElement hideFilterBtn;

	@FindBy(how = How.CSS, using = "a[id*='ProjectVisualToggle'][original-title='Dashboard 1']")
	private WebElement dashboard1ToggleButton;

	@FindBy(how = How.CSS, using = "[class*='fleft '] [class='tileiconparent'] [class='tilesWrap']")
	private List<WebElement> leftSelectionView;

	@FindBy(how = How.CSS, using = ".leftNavwrapper a[class='DrawerAnchor']")
	private WebElement drawerAnchorButton;

	@FindBy(how = How.CSS, using = "[class*='fright '] [class='tileiconparent'] [class='tilesWrap']")
	private List<WebElement> rightSelectionView;

	@FindBy(how = How.CSS, using = "div[class='legendwrapper besideCon']>div>div:nth-of-type(2)")
	private List<WebElement> leftLegendList;

	@FindBy(how = How.CSS, using = "g[class='c3-chart-arc c3-target c3-target-Private']")
	private WebElement donutForPrivate;

	@FindBy(how = How.CSS, using = "a[class*='iconExpand']")
	private WebElement expandIcon;

	@FindBy(how = How.CSS, using = "div[class*='dashboardleft'] a[class*='iconExpand'][original-title='Expand']")
	private WebElement expandIconOnLeftChart;

	@FindBy(how = How.CSS, using = "div[class*='dashboardright'] a[class*='iconExpand'][original-title='Expand']")
	private WebElement expandIconOnRightChart;

	@FindBy(how = How.CSS, using = "#ExpandWrapper .chartHead")
	private WebElement chartHeaderPopup;

	@FindBy(how = How.CSS, using = "#ExpandWrapper .subheader")
	private WebElement chartSubHeaderPoupup;

	@FindBy(how = How.CSS, using = "#LUBAR .LUBARleft div")
	private List<WebElement> projectTypeDiscription;

	@FindBy(how = How.CSS, using = ".proj-Section-Action-List>li>a")
	private List<WebElement> projectActionsOptions;

	@FindBy(how = How.XPATH, using = "//div[@id='publist']//div//a")
	private List<WebElement> publishRangeDropDownOptions;

	@FindBy(how = How.ID, using = "ctl00_lblProjectTitle")
	private WebElement firstProjectName;

	@FindBy(how = How.CSS, using = "span[id*='lblProjectTitle']")
	private List<WebElement> projectNameList;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkBidders')]//ancestor::div[contains(@class,'project-result')]//div[contains(@class,'project-title')]//a")
	private List<WebElement> projectTitleswithBiddersLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs')]//ancestor::div[contains(@class,'project-result')]//div[contains(@class,'project-title')]//a")
	private List<WebElement> projectTitleswithSpecsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs') and @class='lnkProjDocHighilight']//ancestor::div[contains(@class,'project-result')]//div[contains(@class,'project-title')]//a")
	private List<WebElement> projectTitleswithSpecsLinkMatched;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkAddenda')]//ancestor::div[contains(@class,'project-result')]//div[contains(@class,'project-title')]//a")
	private List<WebElement> projectTitleswithAddendaLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkAddenda')]//ancestor::div[contains(@class,'project-result')]//div[contains(@class,'project-title')]//span")
	private List<WebElement> projectTitleswithAddendaLinkOutOfSubs;

	@FindBy(how = How.CSS, using = "a[id*='lnkTrackList']")
	private List<WebElement> trackNameList;

	@FindBy(how = How.CSS, using = "[class*='fleft'] [id='LUDONUT']>svg>g:nth-of-type(1)>g[class='c3-chart']>g[class='c3-chart-arcs']>g>g>path")
	private List<WebElement> allOwnershipTypes;

	@FindBy(how = How.CSS, using = ".errorHoldar>div")
	private WebElement errorMsg;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkSpecs')]")
	private List<WebElement> specLinksummary;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'dashboardleft')]//div[contains(@class,'tilesheader')]/div/div[2]/span")
	private List<WebElement> leftTilesheader;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'dashboardright')]//div[contains(@class,'tilesheader')]/div/div[2]/span")
	private List<WebElement> rightTilesheader;

	@FindBy(how = How.CSS, using = "div[class*='dashboardleft'] .backArrow")
	private List<WebElement> leftDashboardBackArrow;

	@FindBy(how = How.CSS, using = "div[class*='dashboardright'] .backArrow")
	private List<WebElement> rightDashboardBackArrow;

	@FindBy(how = How.CSS, using = "li[id*='lblStatus']")
	private List<WebElement> projectStatusList;

	@FindBy(how = How.XPATH, using = "//span[@class='fa fa-list']")
	private WebElement listViewDashboardIcon;

	@FindBy(how = How.XPATH, using = "//a[@id='ctl00_contentPlaceHolderHeader_pcTop_ProjectVisualToggle_InactiveView'  and ( contains(@style,'display: none'))]")
	private WebElement listViewDashboardIconDisable;

	// Project column title list of Project Grid page

	@FindBy(how = How.XPATH, using = "//*[@class='pq-grid-cell gridProjectTitleColumn']//span")
	private List<WebElement> projectTitleListOfProjGrid;

	@FindBy(how = How.CSS, using = ".resetGCSPopUp .resetGCSPopUpText")
	private WebElement resetGCSPopUpText;

	@FindBy(how = How.CSS, using = ".resetGCSPopUp #resetAllGcs")
	private WebElement resetAllGcsButton;

	@FindBy(how = How.CSS, using = ".resetGCSPopUp #cancelResetGcs")
	private WebElement cancelResetGcsButton;

	@FindBy(how = How.XPATH, using = "//*[@class='anchorFilter fleft keywrdFilter']")
	private WebElement keywordSearchFilterCrumb;

	@FindBy(how = How.ID, using = "mCSB_18_container")
	private WebElement ProjectDeliverySystemDesription;

	@FindBy(how = How.XPATH, using = "//*[@id='saveSearchNewwrapper']//*[@id='ctl00_contentPlaceHolderHeader_ctrl_SavedSearchDisplay_lblSavedSearchName']")
	private List<WebElement> saveSearchMFRList;

	@FindBy(how = How.CSS, using = ".dashboardleft .subheaderShow>span")
	private WebElement subHeaderOnLeftChart;

	@FindBy(how = How.CSS, using = ".dashboardright .subheaderShow>span")
	private WebElement subHeaderOnRightChart;

	@FindBy(how = How.CSS, using = ".dashboardleft .removeIconCross>span")
	private WebElement removeIconOnLeftChart;

	@FindBy(how = How.CSS, using = ".dashboardright .removeIconCross>span")
	private WebElement removeIconOnRightChart;

	@FindBy(how = How.CSS, using = ".dashboardleft .iconSearch>a")
	private WebElement searchIconOnLeftChart;

	@FindBy(how = How.CSS, using = ".dashboardright .iconSearch>a")
	private WebElement searchIconOnRightChart;

	@FindBy(how = How.ID, using = "planRoomSyncButtonWrapper")
	private WebElement planroomSyncbutton;

	@FindBy(how = How.XPATH, using = "//div[@class='RUBARright CountWrapper  padleft5']")
	private List<WebElement> distributionCountRightSection;
		
	public ProjectResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Results Page");
		clickOnListViewIcon();
	}

	public ProjectResultsPage(WebDriver driver, boolean persistantSettingExpected) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Project Results Page with persistant setting.");
	}

	public ProjectResultsPage(WebDriver driver, String selectedTrackingList) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		this.selectedTrackingList = selectedTrackingList;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/***
	 * Check left chart SubHeader is displayed.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isLeftChartSubHeaderDisplayed() {
		extentTest.log(Status.INFO, "Check left chart SubHeader is displayed.");
		return SeleniumUtils.isVisible(subHeaderOnLeftChart, driver);
	}

	/***
	 * Get left chart SubHeader Text
	 * 
	 * @return : Get the text of sub header.
	 */
	public String getLeftChartSubHeaderText() {
		extentTest.log(Status.INFO, "Get left chart SubHeader Text.");
		return subHeaderOnLeftChart.getText().trim();
	}

	public boolean compareNthCountForLeftChartSubHeaderText() {
		extentTest.log(Status.INFO, "Compare dynamic Nth count for left chart SubHeader Text.");
		int nthCount = Integer.parseInt(getLeftChartSubHeaderText().split(" ")[0].trim());
		System.out.println("list : " + allTextBarChart1Dashboard.size() + " count :" + nthCount);
		return allTextBarChart1Dashboard.size() == nthCount;
	}

	public boolean compareNthCountForRightChartSubHeaderText() {
		extentTest.log(Status.INFO, "Compare dynamic Nth count for right chart SubHeader Text.");
		int nthCount = Integer.parseInt(getRightChartSubHeaderText().split(" ")[0].trim());
		System.out.println("list : " + allTextBarChart2Dashboard.size() + " count :" + nthCount);
		return allTextBarChart2Dashboard.size() == nthCount;
	}

	/***
	 * Check Right chart SubHeader is displayed.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isRightChartSubHeaderDisplayed() {
		extentTest.log(Status.INFO, "Check Right chart SubHeader is displayed.");
		return SeleniumUtils.isVisible(subHeaderOnRightChart, driver);
	}

	/***
	 * Get Right chart SubHeader Text
	 * 
	 * @return : Get the text of sub header.
	 */
	public String getRightChartSubHeaderText() {
		extentTest.log(Status.INFO, "Get Right chart SubHeader Text.");
		return subHeaderOnRightChart.getText().trim();
	}

	/***
	 * Click on left chart remove icon.
	 */
	public void clickOnLeftChartRemoveIcon() {
		extentTest.log(Status.INFO, "Click on left chart remove icon.");
		SeleniumUtils.isVisible(removeIconOnLeftChart, driver);
		removeIconOnLeftChart.click();
		waitforLoadingRing();
	}

	/***
	 * Check left chart remove icon is displayed.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isLeftChartRemoveIconDisplayed() {
		extentTest.log(Status.INFO, "Check left chart remove icon is displayed.");
		return SeleniumUtils.isVisible(removeIconOnLeftChart, driver);
	}

	/***
	 * Click on right chart remove icon.
	 */
	public void clickOnRightChartRemoveIcon() {
		extentTest.log(Status.INFO, "Click on right chart remove icon.");
		SeleniumUtils.isVisible(removeIconOnRightChart, driver);
		removeIconOnRightChart.click();
		waitforLoadingRing();
	}

	/***
	 * Check Right chart remove icon is displayed.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isRightChartRemoveIconDisplayed() {
		extentTest.log(Status.INFO, "Check Right chart remove icon is displayed.");
		return SeleniumUtils.isVisible(removeIconOnRightChart, driver);
	}

	public void clickOnLeftChartSearchIcon() {
		extentTest.log(Status.INFO, "Click on left chart search icon.");
		SeleniumUtils.isVisible(searchIconOnLeftChart, driver);
		searchIconOnLeftChart.click();
	}

	/***
	 * Check left chart search icon is displayed.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isLeftChartSearchIconDisplayed() {
		extentTest.log(Status.INFO, "Check left chart search icon is displayed.");
		return SeleniumUtils.isVisible(searchIconOnLeftChart, driver);
	}

	public void clickOnRightChartSearchIcon() {
		extentTest.log(Status.INFO, "Click on right chart search icon.");
		SeleniumUtils.isVisible(searchIconOnRightChart, driver);
		searchIconOnRightChart.click();
	}

	/***
	 * Check Right chart search icon is displayed.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isRightChartSearchIconDisplayed() {
		extentTest.log(Status.INFO, "Check Right chart search icon is displayed.");
		return SeleniumUtils.isVisible(searchIconOnRightChart, driver);
	}

	/**
	 * This method is used to select Select All projects from grid.
	 */
	public void clickOnProjectGridSelectAllCheckBox() {
		extentTest.log(Status.INFO, "Click on Project Grid Select All Check Box.");
		SeleniumUtils.isVisible(projectGridSelectAllCheckBox, driver);
		CommonUtils.clickOnElementUsingJavascript(projectGridSelectAllCheckBox, driver);
	}

	/**
	 * This method is used to select expected number of projects from grid.
	 * 
	 * @param numberOfProjectsToBeSelected
	 *            Number Of Projects To Be Selected.
	 */
	public void clickOnProjectGridCheckBox(final int numberOfProjectsToBeSelected) {
		extentTest.log(Status.INFO, "Click on Project Grid Check Box.");
		SeleniumUtils.isVisible(projectGridCheckBox.get(0), driver);
		for (int count = 0; count < numberOfProjectsToBeSelected; count++) {
			CommonUtils.clickOnElementUsingJavascript(projectGridCheckBox.get(count), driver);
		}
	}

	/**
	 * This method verifies the all project selection using select all check
	 * box.
	 * 
	 * @return return true if all project selection using select all check box
	 *         successfully else return false.
	 */
	public boolean verifyProjectGridSelectAllCheckBoxFunctionality() {
		extentTest.log(Status.INFO, "Verify Project Grid Select All Check Box Functionality.");
		SeleniumUtils.isVisible(projectGridCheckBox.get(0), driver);
		boolean isSelected = false;
		for (final WebElement projectGridChkbx : projectGridCheckBox) {
			isSelected = projectGridChkbx.isSelected();
			if (!isSelected) {
				break;
			}
		}
		return isSelected;
	}

	public boolean isProjectGridSelectAllCheckBoxSelected() {
		extentTest.log(Status.INFO, "Verify status of grid check box to select all project.");
		SeleniumUtils.isVisible(projectGridSelectAllCheckBox, driver);
		return projectGridSelectAllCheckBox.isSelected();
	}

	public boolean isProjectGridSelectAllCheckBoxDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of grid check box to select all project.");
		return projectGridSelectAllCheckBoxVisibility.isDisplayed();
	}

	// Click on the section visualization toggle button.
	public void clickOnDashboard2ToggleButton() {
		extentTest.log(Status.INFO, "Click on Project section visualization toggle button.");
		SeleniumUtils.isVisible(sectionVisualizationsToggleBtn, driver);
		CommonUtils.clickOnElementUsingJavascript(sectionVisualizationsToggleBtn, driver);
	}

	public ProjectPlansPage clickOnFirstHighlightedPlan() {
		extentTest.log(Status.INFO, "Click on First highlighted Plan");
		highlightedPlans.get(0).click();
		return new ProjectPlansPage(driver);
	}

	public ProjectSpecsPage clickOnFirstHighlightedSpec() {
		extentTest.log(Status.INFO, "Click on First highlighted Spec");
		highlightedSpecs.get(0).click();
		return new ProjectSpecsPage(driver);
	}

	public ProjectAddendaPage clickOnFirstHighlightedAddenda() {
		extentTest.log(Status.INFO, "Click on First highlighted Addenda");
		highlightedAddenda.get(0).click();
		return new ProjectAddendaPage(driver);
	}

	public int getSizeOfHighlightedPlanDocuments() {
		extentTest.log(Status.INFO, "Get size of highlighted Plan Docs");
		return highlightedPlans.size();
	}

	public boolean isDashboard1TogglebuttonDisplayed() {
		extentTest.log(Status.INFO, "check if dashboard1 toggle button is displayed");
		return SeleniumUtils.isVisible(Dashboard1Btn, driver);
	}

	public boolean isDashboard1TogglebuttonSelected() {
		extentTest.log(Status.INFO, "check if dashboard1 toggle button is selected.");
		return selectedDashboard1Icon.isDisplayed();
	}

	public boolean isDashboard2TogglebuttonSelected() {
		extentTest.log(Status.INFO, "check if dashboard2 toggle button is selected.");
		return selectedDashboard2Icon.isDisplayed();
	}

	public boolean isPlansLinkHighlightedInYellow() {
		SeleniumUtils.isClickable(plansLink, driver);
		return highlightedPlans.get(0).getCssValue("background-color").equals("#FFFF57");
	}

	public boolean isSpecLinkHighlightedInYellow() {
		extentTest.log(Status.INFO, "check if spec link is highlighted");
		return SeleniumUtils.isVisible(displaySpecLink, driver);

	}

	public boolean isDashboard2TogglebuttonDisplayed() {
		extentTest.log(Status.INFO, "check if dashboard2 toggle button is displayed");
		return SeleniumUtils.isVisible(sectionVisualizationsToggleBtn, driver);
	}

	public boolean isProjectListTogglebuttonDisplayed() {
		extentTest.log(Status.INFO, "check if project list toggle button is displayed");
		return SeleniumUtils.isVisible(projectListToggleBtn, driver);
	}

	public String LeftNavigationFilterBackGroundcolor() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(LeftNavigationFilterBackGround));
		return LeftNavigationFilterBackGround.getCssValue("color");
	}

	public String getDashboard1ToggleBtnBackGroundColor() {

		return Dashboard1Btn.getCssValue("color");
	}

	public String getDashboard2ToggleBtnBackGroundColor() {

		return Dashboard2Btn.getCssValue("color");
	}

	public String getProjectListToggleBtnBackGroundColor() {

		return projectListBtn.getCssValue("color");
	}

	public void clickOnProjectListToggleButton() {
		extentTest.log(Status.INFO, "Click on Project List view toggle button.");
		if (SeleniumUtils.isVisible(projectListBtn, driver)) {
			projectListBtn.click();
			waitforLoadingRing();
		}
	}

	public void clickOnGridViewUnSelectedToggleButton() {
		extentTest.log(Status.INFO, "Click on Grid view toggle button.");
		if (gridViewToggleUnSelectedBtn.isDisplayed()) {
			gridViewToggleUnSelectedBtn.click();
			waitforLoadingRing();
		}
	}

	public void lazyLoadDataInGrid() {
		extentTest.log(Status.INFO, "lazy load data in grid");
		while (!isLoadingRingDisplayedForGridLazyLoad()) {

			GridDownScroll.click();
		}
		waitforLoadingRing();
	}

	public boolean checkLazyLoadDataInGrid() {
		extentTest.log(Status.INFO, "Check for lazy load data in grid");
		boolean lazyLoadFlag = false;
		while (!lazyLoadFlag) {
			GridDownScroll.click();
			lazyLoadFlag = isLoadingRingDisplayedForGridLazyLoad();
		}
		waitforLoadingRing();
		return lazyLoadFlag;
	}

	public boolean scrollTillLoadingRingVisibleInGrid() {
		extentTest.log(Status.INFO, "Scroll till lazy load data visible in grid");
		boolean lazyLoadFlag;
		CommonUtils.scrollDownTillElement(GridDownScroll, driver);
		lazyLoadFlag = isLoadingRingDisplayedForGridLazyLoad();
		waitforLoadingRing();
		return lazyLoadFlag;
	}

	public void clickOnGridViewToggleSelectedButton() {
		extentTest.log(Status.INFO, "Click on Grid view toggle inactive button.");
		CommonUtils.scrollDownTillElement(gridViewToggleSelectedBtn, driver);
		gridViewToggleSelectedBtn.click();
	}

	public void clickOnGridGearIcon() {
		extentTest.log(Status.INFO, "Click on grid gear icon.");
		clickOnGridViewToggleSelectedButton();
		CommonUtils.scrollDownTillElement(gridGearIcon, driver);
		gridGearIcon.click();
		SeleniumUtils.isVisible(dCSDialog, driver);
	}

	public boolean isGridGearIconDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of grid gear icon.");
		return gridGearIcon.isDisplayed();
	}

	public void selectProjectTypeUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Project Type")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnProjectTypeUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Project Type")) {
					clickOnProjectTypeUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	// Added for Project Group
	public void selectProjectTypeTileUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Project Type Groups")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnProjectTypeTileUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Project Type")) {
					clickOnProjectTypeTileUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectManufacturersUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Manufacturers")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnManufacturersUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Manufacturers")) {
					clickOnManufacturersUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void clickOnLeftDashboardBackArrow() {
		extentTest.log(Status.INFO, "Click on left Dashboard Back Arrow icon.");
		leftDashboardBackArrow.get(0).click();
	}

	public void selectManufacturersUnderSecondChartView() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Manufacturers")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnManufacturersUnderSecondchartView();
			} else {
				if (verifyRightVisualizationStatus("Manufacturers")) {
					clickOnManufacturersUnderSecondchartView();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectProjectTypeUnderSecondChartView() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Project Type")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnProjectTypeUnderSecondchartView();
			} else {
				if (verifyRightVisualizationStatus("Project Type")) {
					clickOnProjectTypeUnderSecondchartView();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	// Added for Project Groups
	public void selectProjectTypeTileUnderSecondChartView() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Project Type Groups")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnProjectTypeTileUnderSecondchartView();
			} else {
				if (verifyRightVisualizationStatus("Project Type")) {
					clickOnProjectTypeTileUnderSecondchartView();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectSectionsUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Sections")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnSectionTypeUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Sections")) {
					clickOnSectionTypeUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectSectionsUnderSecondChartView() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Sections")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnSectionTypeUnderSecondchartView();
			} else {
				if (verifyRightVisualizationStatus("Sections")) {
					clickOnSectionTypeUnderSecondchartView();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectSpecAlertsUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("SpecAlerts")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnSpecAlertsUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("SpecAlerts")) {
					clickOnSpecAlertsUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectOwnershipTypeUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Ownership Type")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnOwnershipTypeUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Ownership Type")) {
					clickOnOwnershipTypeUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectActionStageUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Action Stage")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnActionStageUnderFirstChartView();
			} else {
				if (verifyLeftVisualizationStatus("Action Stage")) {
					clickOnActionStageUnderFirstChartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectActionStageUnderSecondChartView() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Action Stage")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnActionStageUnderSecondChartView();
			} else {
				if (verifyRightVisualizationStatus("Action Stage")) {
					clickOnActionStageUnderSecondChartView();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectDivisionTypeUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Division")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnDivisionTypeUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Division")) {
					clickOnDivisionTypeUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectDivisionUnderSecondChartView() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Division")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnDivisionTypeUnderSecondchartView();
			} else {
				if (verifyRightVisualizationStatus("Division")) {
					clickOnDivisionTypeUnderSecondchartView();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void selectConstructionTypeUnderFirstChartView() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Construction Type")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnConstructionTypeUnderFirstchartView();
			} else {
				if (verifyLeftVisualizationStatus("Construction Type")) {
					clickOnConstructionTypeUnderFirstchartView();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void clickOnActionStageUnderFirstChartView() {
		extentTest.log(Status.INFO, "Click on ActionStage Type under first chart");
		if (actionsStageCategoryInDashboard2.size() > 1)
			actionsStageCategoryInDashboard2.get(0).click();
		else
			actionsStageCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(barChartActionStageDesign));
	}

	public boolean checkSpaceExist() {
		return barChart1SpecAlerts.getText().trim().contains("\\s");
	}

	public void clickOnActionStageUnderSecondChartView() {
		extentTest.log(Status.INFO, "Click on ActionStage Type under second chart");
		if (actionsStageCategoryInDashboard2.size() > 1)
			actionsStageCategoryInDashboard2.get(1).click();
		else
			actionsStageCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(barChartActionStageDesign));
	}

	public void clickOnConstructionTypeUnderFirstchartView() {
		extentTest.log(Status.INFO, "Click on Construction Type under first chart");

		if (ContructionTypeCategoryInDashboard2.size() > 1)
			ContructionTypeCategoryInDashboard2.get(0).click();
		else
			ContructionTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(newTypeInConstructionType));
	}

	public void clickOnFirstChartCustomizeTile() {
		extentTest.log(Status.INFO, "Click on customize button of first chart");
		if (customizeCharttile.get(0).isDisplayed()) {
			customizeCharttile.get(0).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(projectTypeTileOnDashboard));
		}
	}

	public void clickOnSecondChartCustomizeTile() {

		extentTest.log(Status.INFO, "Click on customize button of Second chart");
		if (customizeCharttile.get(1).isDisplayed()) {
			customizeCharttile.get(1).click();
			waitforLoadingRing();
			/*
			 * new WebDriverWait(driver, 10)
			 * .until(ExpectedConditions.visibilityOfAllElements(
			 * sectionsTypeCategoryInDashboard2));
			 */
		}
	}

	public Boolean isChart1CustomizeTilePresent() {
		return SeleniumUtils.isVisible(customizeCharttile.get(0), driver);
	}

	public Boolean isChart2CustomizeTilePresent() {
		return SeleniumUtils.isVisible(customizeCharttile.get(1), driver);
	}

	public Boolean isProjectTypeDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(projectTypeCategoryChart1Dashboard2, driver);
	}

	// Added for Project Group
	public Boolean isProjectTypeTileDisplayedOnLeftChart() {
		return SeleniumUtils.isVisible(projectTypeTileOnLeftChart, driver);
	}

	public String VerifyProjectTypeTileTextOnChart() {

		SeleniumUtils.isVisible(projectTypeTileDisabledOnChart, driver);
		return projectTypeTileDisabledOnChart.getText().trim();

	}

	public Boolean isProjectTypeTileDisabledOnChart() {
		return SeleniumUtils.isVisible(projectTypeTileDisabledOnChart, driver);
	}

	public Boolean isSelectionVisualizationScreenDisplayedOnRightChart() {
		return SeleniumUtils.isVisible(rightChartSelectionVisualizationScreen, driver);
	}

	public Boolean isSelectionVisualizationScreenDisplayedOnLeftChart() {
		return SeleniumUtils.isVisible(leftChartSelectionVisualizationScreen, driver);
	}

	public Boolean isManufacturersDisplayedOnChart1Dashboard() {
		return SeleniumUtils.isVisible(manufacturersCategoryChart1Dashboard, driver);
	}

	public Boolean isManufacturersDisplayedOnChart2Dashboard() {
		return SeleniumUtils.isVisible(manufacturersCategoryChart2Dashboard, driver);
	}

	public Boolean isConstructionTypeDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(constructionTypeCategoryChart1Dashboard2, driver);
	}

	public Boolean isDivisionDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(divisionCategoryChart1Dashboard2, driver);
	}

	public Boolean isSectionsDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(sectionsCategoryChart1Dashboard2, driver);
	}

	public Boolean isActionStageDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(actionStageCategoryChart1Dashboard2, driver);
	}

	public Boolean isOwnershipTypeDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(ownershipTypeCategoryChart1Dashboard2, driver);
	}

	public Boolean isSpecAlertsDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(specAlertsCategoryChart1Dashboard2, driver);
	}

	public Boolean isBackBtnDisplayedOnChart1Dashboard2() {
		return SeleniumUtils.isVisible(backBtnChart1Dashboard2, driver);
	}

	public void clickOnBackBtnOnChart1Dashboard2() {
		if (SeleniumUtils.isClickable(backBtnChart1Dashboard2, driver)) {
			backBtnChart1Dashboard2.click();
		}
	}

	public String getChart1HeaderDashboard2() {
		SeleniumUtils.isVisible(chartHeaderChart1Dashboard2, driver);
		return chartHeaderChart1Dashboard2.getText().trim();
	}

	public String checkPDSSeeMorePopupHeading() {
		SeleniumUtils.isVisible(projectDeliveryHeader, driver);
		return projectDeliveryHeader.getText().trim();
	}

	public String checkPDSSeeMoreHideZeroLabelText() {
		SeleniumUtils.isVisible(CommonPopupHideZeroProjects_label, driver);
		return CommonPopupHideZeroProjects_label.getText().trim();
	}

	public Boolean isSelectedTileDisabledOnChart1Dashboard2(String selectedTile) {
		boolean isTileDisabled = false;
		switch (selectedTile) {
		case "Project Type":
			isTileDisabled = SeleniumUtils.isClickable(projectTypeCategoryChart1Dashboard2, driver);
			break;
		case "Construction Type":
			isTileDisabled = SeleniumUtils.isClickable(constructionTypeCategoryChart1Dashboard2, driver);
			break;
		case "Division":
			isTileDisabled = SeleniumUtils.isClickable(divisionCategoryChart1Dashboard2, driver);
			break;
		case "Sections":
			isTileDisabled = SeleniumUtils.isClickable(sectionsCategoryChart1Dashboard2, driver);
			break;
		case "Action Stage":
			isTileDisabled = SeleniumUtils.isClickable(actionStageCategoryChart1Dashboard2, driver);
			break;
		case "Ownership Type":
			isTileDisabled = SeleniumUtils.isClickable(ownershipTypeCategoryChart1Dashboard2, driver);
			break;
		case "SpecAlerts":
			isTileDisabled = SeleniumUtils.isClickable(specAlertsCategoryChart1Dashboard2, driver);
			break;
		}
		return !isTileDisabled;
	}

	public Boolean isProjectTypeDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(projectTypeCategoryChart2Dashboard2, driver);
	}

	// Added for Project Groups
	public Boolean isProjectTypeTileDisplayedOnRightChart() {
		return SeleniumUtils.isVisible(projectTypeTileOnRightChart, driver);
	}

	public Boolean isConstructionTypeDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(constructionTypeCategoryChart2Dashboard2, driver);
	}

	public Boolean isDivisionDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(divisionCategoryChart2Dashboard2, driver);
	}

	public Boolean isSectionsDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(sectionsCategoryChart2Dashboard2, driver);
	}

	public Boolean isActionStageDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(actionStageCategoryChart2Dashboard2, driver);
	}

	public Boolean isOwnershipTypeDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(ownershipTypeCategoryChart2Dashboard2, driver);
	}

	public Boolean isSpecAlertsDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(specAlertsCategoryChart2Dashboard2, driver);
	}

	public Boolean isBackBtnDisplayedOnChart2Dashboard2() {
		return SeleniumUtils.isVisible(backBtnChart2Dashboard2, driver);
	}

	public void clickOnBackBtnOnChart2Dashboard2() {
		if (SeleniumUtils.isClickable(backBtnChart2Dashboard2, driver)) {
			backBtnChart2Dashboard2.click();
		}
	}

	public String getChart2HeaderDashboard2() {
		return chartHeaderChart2Dashboard2.getText().trim();
	}

	public Boolean isSelectedTileDisabledOnChart2Dashboard2(String selectedTile) {
		boolean isTileDisabled = false;
		switch (selectedTile) {
		case "Project Type":
			isTileDisabled = SeleniumUtils.isClickable(projectTypeCategoryChart2Dashboard2, driver);
			break;
		case "Construction Type":
			isTileDisabled = SeleniumUtils.isClickable(constructionTypeCategoryChart2Dashboard2, driver);
			break;
		case "Division":
			isTileDisabled = SeleniumUtils.isClickable(divisionCategoryChart2Dashboard2, driver);
			break;
		case "Sections":
			isTileDisabled = SeleniumUtils.isClickable(sectionsCategoryChart2Dashboard2, driver);
			break;
		case "Action Stage":
			isTileDisabled = SeleniumUtils.isClickable(actionStageCategoryChart2Dashboard2, driver);
			break;
		case "Ownership Type":
			isTileDisabled = SeleniumUtils.isClickable(ownershipTypeCategoryChart2Dashboard2, driver);
			break;
		case "SpecAlerts":
			isTileDisabled = SeleniumUtils.isClickable(specAlertsCategoryChart2Dashboard2, driver);
			break;
		}
		return !isTileDisabled;
	}

	public void clickOnTileChart1Dashboard2(String tileName) {
		switch (tileName) {
		case "Project Type":
			SeleniumUtils.isClickable(projectTypeCategoryChart1Dashboard2, driver);
			projectTypeCategoryChart1Dashboard2.click();
			break;
		case "Construction Type":
			SeleniumUtils.isClickable(constructionTypeCategoryChart1Dashboard2, driver);
			constructionTypeCategoryChart1Dashboard2.click();
			break;
		case "Division":
			SeleniumUtils.isClickable(divisionCategoryChart1Dashboard2, driver);
			divisionCategoryChart1Dashboard2.click();
			break;
		case "Sections":
			SeleniumUtils.isClickable(sectionsCategoryChart1Dashboard2, driver);
			sectionsCategoryChart1Dashboard2.click();
			break;
		case "Action Stage":
			SeleniumUtils.isClickable(actionStageCategoryChart1Dashboard2, driver);
			actionStageCategoryChart1Dashboard2.click();
			break;
		case "Ownership Type":
			SeleniumUtils.isClickable(ownershipTypeCategoryChart1Dashboard2, driver);
			ownershipTypeCategoryChart1Dashboard2.click();
			break;
		case "SpecAlerts":
			SeleniumUtils.isClickable(specAlertsCategoryChart1Dashboard2, driver);
			specAlertsCategoryChart1Dashboard2.click();
			break;
		}
	}

	public List<Integer> getIndexesOfDeselectedBarsInChart1Dashboard2() {
		List<Integer> deselectedBarIndexes = new ArrayList<>();
		for (int cntr = 0; cntr < allBarWrapperChart1Dashboard2.size(); cntr++) {
			String styleAttrVal = allBarWrapperChart1Dashboard2.get(cntr).getAttribute("style");
			if (null != styleAttrVal) {
				if (styleAttrVal.contains("background-color: rgb(243, 243, 243)")) {
					deselectedBarIndexes.add(cntr + 1);
				}
			}
		}
		return deselectedBarIndexes;
	}

	public void selectTileFromChart1Dashboard2(String tileName) {
		if (isChart1CustomizeTilePresent()) {
			if (!getChart1HeaderDashboard2().equalsIgnoreCase(tileName)) {
				clickOnFirstChartCustomizeTile();
				if (isSelectedTileDisabledOnChart1Dashboard2(tileName)) {
					clickOnBackBtnOnChart1Dashboard2();
				} else {
					clickOnTileChart1Dashboard2(tileName);
				}
			}
		} else {
			if (isSelectedTileDisabledOnChart1Dashboard2(tileName)) {
				clickOnBackBtnOnChart1Dashboard2();
			} else {
				clickOnTileChart1Dashboard2(tileName);
			}
		}
	}

	public void selectedDifferentChartsOnDashboard2() {
		String selectedTileChart1 = "";
		if (!isChart1CustomizeTilePresent() && !isChart2CustomizeTilePresent()) {
			selectedTileChart1 = selectAnyEnabledTileFromChart1Dashboard2();
			selectAnyEnabledTileFromChart2Dashboard2(selectedTileChart1);
		} else if (!isChart1CustomizeTilePresent() && isChart2CustomizeTilePresent()) {
			selectAnyEnabledTileFromChart1Dashboard2(getChart2HeaderDashboard2());
		} else if (isChart1CustomizeTilePresent() && !isChart2CustomizeTilePresent()) {
			selectAnyEnabledTileFromChart2Dashboard2(getChart1HeaderDashboard2());
		}
	}

	public String selectAnyEnabledTileFromChart1Dashboard2() {
		String selectedTile = "";
		for (WebElement tile : allTileTextChart1Dashboard2) {
			if (SeleniumUtils.isClickable(tile, driver)) {
				tile.click();
				selectedTile = tile.getText().trim();
			}
		}

		return selectedTile;
	}

	public String selectAnyEnabledTileFromChart2Dashboard2() {
		String selectedTile = "";
		for (WebElement tile : allTileTextChart2Dashboard2) {
			if (SeleniumUtils.isClickable(tile, driver)) {
				tile.click();
				selectedTile = tile.getText().trim();
			}
		}

		return selectedTile;
	}

	public String selectAnyEnabledTileFromChart1Dashboard2(String tileName) {
		String selectedTile = "";
		for (WebElement tile : allTileTextChart1Dashboard2) {
			selectedTile = tile.getText().trim();
			if (!selectedTile.equalsIgnoreCase(tileName) && SeleniumUtils.isClickable(tile, driver)) {
				tile.click();
			}
		}

		return selectedTile;
	}

	public String selectAnyEnabledTileFromChart2Dashboard2(String tileName) {
		String selectedTile = "";
		for (WebElement tile : allTileTextChart2Dashboard2) {
			selectedTile = tile.getText().trim();
			if (!selectedTile.equalsIgnoreCase(tileName) && SeleniumUtils.isClickable(tile, driver)) {
				tile.click();
				break;
			}
		}

		return selectedTile;
	}

	public Boolean isBreadCrumbSectionDisplayed() {
		if (BreadCrumbSection.size() > 0)
			return true;
		else
			return false;

	}

	public void clickOnProjectTypeUnderFirstchartView() {
		extentTest.log(Status.INFO, "Click on Project Type under first chart");
		if (projectTypeCategoryInDashboard2.size() > 1)
			projectTypeCategoryInDashboard2.get(0).click();
		else
			projectTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(barChartProjectTypeEngineering));
	}

	// Added for Project Groups
	public void clickOnProjectTypeTileUnderFirstchartView() {
		extentTest.log(Status.INFO, "Click on Project Groups under first chart");
		if (projectTypeTileOnDashboard.size() > 1)
			projectTypeTileOnDashboard.get(0).click();
		else
			projectTypeTileOnDashboard.get(0).click();
		waitforLoadingRing();

	}

	public void clickOnManufacturersUnderFirstchartView() {
		extentTest.log(Status.INFO, "Click on Manufacturers under first chart");
		manufacturersCategoryInDashboard.get(0).click();
		waitforLoadingRing();
		SeleniumUtils.isVisible(leftSortDropdownManufacturers, driver);
	}

	public void closeMfrUserFavouredPopupButton() {
		extentTest.log(Status.INFO, "Click on Cancel button to close the Manufacturer user-favored pop-up");
		if (SeleniumUtils.isVisible(mfrUserFavPopupCancelButton, driver)) {
			mfrUserFavPopupCancelButton.click();
			waitforLoadingRing();
		}
	}

	public void clickOnManufacturersUnderSecondchartView() {
		extentTest.log(Status.INFO, "Click on Manufacturers under second chart");
		System.out.println("hifds");
		manufacturersCategoryInDashboard.get(0).click();
		waitforLoadingRing();
		SeleniumUtils.isVisible(rightSortDropdownManufacturers, driver);
	}

	/***
	 * Check if Manufacturers under first chart is Displayed.
	 * 
	 * @return : Get boolean result
	 */
	public boolean isManufacturersUnderVisaulChartViewDisplayed() {
		extentTest.log(Status.INFO, "Check if Manufacturers under first chart is Displayed.");
		return CommonUtils.checkElementExist(manufacturersCategoryInDashboard.get(0), driver);
	}

	public void clickOnOwnershipTypeUnderFirstchartView() {

		extentTest.log(Status.INFO, "Click on Ownership Type under first chart");

		if (ownershipTypeCategoryInDashboard2.size() > 1)
			ownershipTypeCategoryInDashboard2.get(0).click();
		else
			ownershipTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
	}

	public void clickOnSpecAlertsUnderFirstchartView() {
		extentTest.log(Status.INFO, "Click on Spec Alers under first chart");
		if (specAlertsCategoryInDashboard2.size() > 1)
			specAlertsCategoryInDashboard2.get(0).click();
		else
			specAlertsCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(barChart1SpecAlerts));

	}

	public void clickOnDivisionTypeUnderFirstchartView() {

		extentTest.log(Status.INFO, "Click on Division Type under first chart");

		if (DivisionTypeCategoryInDashboard2.size() > 1)
			DivisionTypeCategoryInDashboard2.get(0).click();
		else
			DivisionTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(firstDivisionInDivisionChart));
	}

	public void clickOnDivisionTypeUnderSecondchartView() {

		extentTest.log(Status.INFO, "Click on Division Type under second chart");

		if (DivisionTypeCategoryInDashboard2.size() > 1)
			DivisionTypeCategoryInDashboard2.get(1).click();
		else
			DivisionTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(firstDivisionInRightDivisionChart));

	}

	public void clickOnSectionTypeUnderFirstChartView() {
		extentTest.log(Status.INFO, "Click on Sections under first chart");
		sectionsTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(sectionFindInfilterChart1));
	}

	public void clickOnSectionTypeUnderSecondchartView() {

		extentTest.log(Status.INFO, "Click on Sections under second chart");

		if (sectionsTypeCategoryInDashboard2.size() > 1)
			sectionsTypeCategoryInDashboard2.get(1).click();
		else
			sectionsTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(sectionFindInfilter));
	}

	public void clickOnSectionTypeUnderFirstchartView() {

		extentTest.log(Status.INFO, "Click on Sections under first chart");

		if (sectionsTypeCategoryInDashboard2.size() > 1)
			sectionsTypeCategoryInDashboard2.get(0).click();
		else
			sectionsTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(firstSectionInSectionsChart));
	}

	public void clickOnProjectTypeUnderSecondchartView() {

		extentTest.log(Status.INFO, "Click on Sections under first chart");

		if (projectTypeCategoryInDashboard2.size() > 1)
			projectTypeCategoryInDashboard2.get(1).click();
		else
			projectTypeCategoryInDashboard2.get(0).click();
		waitforLoadingRing();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(barChartProjectTypeEngineeringRightView));

	}

	// Added for Project Groups
	public void clickOnProjectTypeTileUnderSecondchartView() {

		extentTest.log(Status.INFO, "Click on Project Groups under second chart");

		if (projectTypeTileOnDashboard.size() > 1)
			projectTypeTileOnDashboard.get(1).click();
		else
			projectTypeTileOnDashboard.get(0).click();
		waitforLoadingRing();

	}

	public void clickOnCancelButtonInShoppingCart() {
		extentTest.log(Status.INFO, "Click on cancel Button in shopping cart");
		cancelbuttonShoppingCart.click();

		driver.switchTo().alert().accept();
	}

	public boolean isTexasStateOnMapDisplayed() {
		extentTest.log(Status.INFO, "Verify if Map is displayed ");
		return mapChartProjectDensityTexas.isDisplayed();
	}

	public boolean isChartLeftContainsProjectType() {
		extentTest.log(Status.INFO, "Verify if Project type is displayed under chartleft ");
		SeleniumUtils.isVisible(chartLeftContainsProjectType, driver);
		return chartLeftContainsProjectType.isDisplayed();
	}

	// Added for Project Groups

	public boolean isProjectGroupsRadioButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if Project groups radio butoon is displayed on chart");
		try {
			try {
				projectGroupsRadioButton.get(0).isDisplayed();
			} catch (Exception e) {
				projectGroupsRadioButton.get(1).isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}

		return true;

	}

	public boolean isProjectCategoriesRadioButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if Project categiries radio butoon is displayed on chart");
		try {
			try {
				projectCategoriesRadioButton.get(0).isDisplayed();
			} catch (Exception e) {
				projectCategoriesRadioButton.get(1).isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean isProjectTypesRadioButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if Project Types radio butoon is displayed on chart");
		try {
			try {
				projectTypesRadioButton.get(0).isDisplayed();
			} catch (Exception e) {
				projectTypesRadioButton.get(1).isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean verifyProjectGroupsRadioButtonLabel() {
		extentTest.log(Status.INFO, "Verify Project groups radio butoon Label on chart");
		boolean resultFlag = false;
		for (WebElement project : projectGroupsRadioButton) {
			if (project.isDisplayed()) {
				if (project.getText().equalsIgnoreCase("Groups")) {
					resultFlag = true;
					break;
				}
			}
		}

		return resultFlag;
	}

	public boolean verifyProjectCategoriesRadioButtonLabel() {
		extentTest.log(Status.INFO, "Verify Project Categories radio butoon Label on chart");
		boolean resultFlag = false;
		for (WebElement project : projectCategoriesRadioButton) {
			if (project.isDisplayed()) {
				if (project.getText().equalsIgnoreCase("Categories")) {
					resultFlag = true;
					break;
				}
			}
		}

		return resultFlag;
	}

	public boolean verifyProjectTypesRadioButtonLabel() {
		extentTest.log(Status.INFO, "Verify Project Types radio butoon Label on chart");
		boolean resultFlag = false;
		for (WebElement project : projectTypesRadioButton) {
			if (project.isDisplayed()) {
				if (project.getText().equalsIgnoreCase("Types")) {
					resultFlag = true;
					break;
				}
			}
		}

		return resultFlag;
	}

	public boolean clickProjectGroupsRadioButton() {
		extentTest.log(Status.INFO, "Clicking on Project groups radio butoon");
		try {
			try {
				projectGroupsRadioButton.get(0).click();
			} catch (Exception e) {
				projectGroupsRadioButton.get(1).click();
			}
		} catch (Exception e) {
			return false;
		}

		return true;

	}

	public boolean clickProjectCategoriesRadioButton() {
		extentTest.log(Status.INFO, "Clicking on Project Categories radio butoon");
		try {
			try {
				projectCategoriesRadioButton.get(0).click();
			} catch (Exception e) {
				projectCategoriesRadioButton.get(1).click();
			}
		} catch (Exception e) {
			return false;
		}

		return true;

	}

	public boolean clickProjectTypesRadioButton() {
		extentTest.log(Status.INFO, "Clicking on Project Types radio butoon");
		try {
			try {
				projectTypesRadioButton.get(0).click();
			} catch (Exception e) {
				projectTypesRadioButton.get(1).click();
			}
		} catch (Exception e) {
			return false;
		}

		return true;

	}

	public boolean isProjectGroupsRadioButtonSelected() {
		extentTest.log(Status.INFO, "Verify if Project groups radio butoon is selected");
		try {
			try {
				projectGroupsRadioButton.get(0).isSelected();
			} catch (Exception e) {
				projectGroupsRadioButton.get(1).isSelected();
			}
		} catch (Exception e) {
			return false;
		}

		return true;

	}

	public boolean isProjectCategoriesRadioButtonSelected() {
		extentTest.log(Status.INFO, "Verify if Project Categories radio butoon is selected");
		try {
			try {
				projectCategoriesRadioButton.get(0).isSelected();
			} catch (Exception e) {
				projectCategoriesRadioButton.get(1).isSelected();
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean isProjectTypesRadioButtonSelected() {
		extentTest.log(Status.INFO, "Verify if Project Types radio butoon is selected");
		try {
			try {
				projectTypesRadioButton.get(0).isSelected();
			} catch (Exception e) {
				projectTypesRadioButton.get(1).isSelected();
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public List<String> clickOnMultipleBarsOnLeftChart(final int count) {
		extentTest.log(Status.INFO, "click on multiple bars in left Chart");
		List<String> barChartTextList = new ArrayList<>();
		String barTextList;
		if (count <= allBarRectChart1Dashboard.size()) {
			for (int i = 0; i < count; i++) {
				waitforLoadingRing();
				CommonUtils.scrollDownTillElement(allTextBarChart1Dashboard.get(i), driver);
				barTextList = allTextBarChart1Dashboard.get(i).getText();
				if (barTextList.length() > 16) {
					barTextList = barTextList.substring(0, 15);
				}
				barChartTextList.add(barTextList);
				allBarRectChart1Dashboard.get(i).click();
				waitforLoadingRing();
			}
		}
		return barChartTextList;
	}

	public List<String> clickOnMultipleBarsOnRightChart(final int count) {
		extentTest.log(Status.INFO, "click on multiple bars in right Chart");
		List<String> barChartTextList = new ArrayList<>();
		if (count <= allBarRectChart2Dashboard.size()) {
			for (int i = 0; i < count; i++) {
				CommonUtils.scrollDownTillElement(allTextBarChart2Dashboard.get(i), driver);
				barChartTextList.add(allTextBarChart2Dashboard.get(i).getText());
				allBarRectChart2Dashboard.get(i).click();
				waitforLoadingRing();
			}
		}
		return barChartTextList;
	}

	public boolean VerifyProjectGroupSubtitleOnLeftChartDisplayed() {
		extentTest.log(Status.INFO, "Verify if Project groups subtitle on left chart displayed");
		return projectGroupsSubtitleOnLeftChart.getText().equals("Distribution by Count");

	}

	public boolean VerifyProjectGroupSubtitleOnRightChartDisplayed() {
		extentTest.log(Status.INFO, "Verify if Project groups subtitle on right chart displayed");
		return projectGroupsSubtitleOnRightChart.getText().equals("Distribution by Count");
	}

	public boolean isChartLeftContainsProjectTypeGroups() {
		extentTest.log(Status.INFO, "Verify if Project groups is displayed under chartleft ");
		SeleniumUtils.isVisible(chartLeftContainsProjectTypeGroups, driver);
		return chartLeftContainsProjectTypeGroups.isDisplayed();
	}

	public boolean isChartLeftContainsProjectTypeCategories() {
		extentTest.log(Status.INFO, "Verify if Project Categories is displayed under chartleft ");
		SeleniumUtils.isVisible(chartLeftContainsProjectTypeCategories, driver);
		return chartLeftContainsProjectTypeCategories.isDisplayed();
	}

	public boolean isChartLeftContainsProjectTypes() {
		extentTest.log(Status.INFO, "Verify if Project Types is displayed under chartleft ");
		SeleniumUtils.isVisible(chartLeftContainsProjectTypes, driver);
		return chartLeftContainsProjectTypes.isDisplayed();
	}

	public boolean isChartRightContainsProjectTypeGroups() {
		extentTest.log(Status.INFO, "Verify if Project Groups is displayed under right chart");
		SeleniumUtils.isVisible(chartRightContainsProjectTypeGroups, driver);
		return chartRightContainsProjectTypeGroups.isDisplayed();
	}

	public boolean isChartRightContainsProjectTypeCategories() {
		extentTest.log(Status.INFO, "Verify if Project Categories is displayed under right chart ");
		SeleniumUtils.isVisible(chartLeftContainsProjectTypeCategories, driver);
		return chartRightContainsProjectTypeCategories.isDisplayed();
	}

	public boolean isChartRightContainsProjectTypes() {
		extentTest.log(Status.INFO, "Verify if Project Types is displayed under right chart ");
		SeleniumUtils.isVisible(chartLeftContainsProjectTypes, driver);
		return chartRightContainsProjectTypes.isDisplayed();
	}

	public boolean isChartRightContainsDivision() {
		extentTest.log(Status.INFO, "Verify if Division is displayed under chartRight ");
		SeleniumUtils.isVisible(chartRightContainsDivision, driver);
		return chartRightContainsDivision.isDisplayed();
	}

	public boolean isTrackLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if Track Link is displayed in the Project Summary");
		return trackLink.isDisplayed();
	}

	public boolean isHideLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if Hide Link is displayed in the Project Summary");
		return hideLink.isDisplayed();
	}

	public int getTrackLinksCountInSummary() {
		extentTest.log(Status.INFO, "Verify if Track Links are displayed in each Project Summary and get the count");
		int count = 0;
		for (WebElement trackLink : trackLinksInSummary) {
			if (trackLink.isDisplayed())
				count++;
		}
		return count;
	}

	public String getSectionFindInfilterPreloadedText() {

		extentTest.log(Status.INFO, "getSectionFindInFilterPreloadedText");
		return sectionFindInfilter.getAttribute("placeholder").toString();
	}

	public int getProjectSummaryCount() {
		int count = 0;
		for (WebElement summary : projectSummary) {
			if (summary.isDisplayed())
				count++;
		}
		return count;
	}

	public boolean isPlansLinkEnabled() {
		extentTest.log(Status.INFO, "Verify the Plans Tab");
		return plansLink.isEnabled();
	}

	public boolean isSavedSearchFilterDisplayed() {
		extentTest.log(Status.INFO, "Check if Saved Search Filter is displayed");
		return filterSavedSearch.isDisplayed();
	}

	public boolean isPlansLinkDisplayed() {
		extentTest.log(Status.INFO, "Check if plans link is displa" + "yed");
		return plansLink.isDisplayed();
	}

	public boolean isSpecsLinkEnabled() {
		extentTest.log(Status.INFO, "Verify the Specs Tab");
		return specsLink.isEnabled();
	}

	public boolean isZeroSearchResultDisplayedforGivenSearch(String searchText) {
		if (projectResultSummary.getText().contains("Your search - " + searchText + " - did not match any documents"))
			return true;
		else
			return false;
	}

	public boolean isMessageDisplayedforGivenSearch(String searchText) {
		if (projectResultSummary.getText().contains(searchText))
			return true;
		else
			return false;
	}

	public String getProjectResultSummaryMessage() {
		extentTest.log(Status.INFO, "Get Project Result summary message.");
		return projectResultSummary.getText();
	}

	public boolean isSpecsLinkDisplayed() {
		extentTest.log(Status.INFO, "Check if specs link is displayed");
		return specsLink.isDisplayed();
	}

	public ProjectFirmsPage clickOnFirmsLink() {
		extentTest.log(Status.INFO, "Clicking on firm Link");
		firmsLink.click();
		return new ProjectFirmsPage(driver);
	}

	public void clickOnFistProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		SeleniumUtils.isVisible(firstProjectchkBox, driver);
		if (!firstProjectchkBox.isSelected()) {
			firstProjectchkBox.click();
		}
	}

	public void clickOnLastStateCheckbox() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		SeleniumUtils.isVisible(StateRegion_LHSLastFilter, driver);
		if (!StateRegion_LHSLastFilter.isSelected()) {
			StateRegion_LHSLastFilter.click();
		}
	}

	public void selectProjectsByIndex(List<Integer> projectIndexes) {
		for (Integer projectIndex : projectIndexes) {
			if (projectIndex < projectTitleCheckboxes.size()) {
				projectTitleCheckboxes.get(projectIndex).click();
			}
		}
	}

	public String getFirstProjectTitle() {
		extentTest.log(Status.INFO, "Get the first project title");
		return firstProjectTitle.getText();
	}

	public void clickOnSecondProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on second project checkbox");
		secondProjectchkBox.click();
	}

	public void clickOnActionsDropdown() {
		extentTest.log(Status.INFO, "Clicking on Actions Dropdown");
		actionsDropdown.click();
		waitforLoadingRing();
	}

	public void clickOnActionsDropdownOutOfSubscription() {
		extentTest.log(Status.INFO, "Clicking on Actions Dropdown for out of subscription projects");
		SeleniumUtils.isVisible(actionsDropdown, driver);
		CommonUtils.scrollDownTillElement(actionsDropdown, driver);
		actionsDropdown.click();
	}

	public PrintProjectListPage clickOnPrintProjectListUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Project List' under Actions Dropdown");
		printProjectListLink.click();
		waitforLoadingRing();
		return new PrintProjectListPage(driver);
	}

	public PrintProjectDetailsPage clickOnPrintProjectListActionsMenuProjectDetailPage() {
		extentTest.log(Status.INFO, "Clicking on 'Print Project List' under Actions Dropdown");
		printProjectListLink.click();
		return new PrintProjectDetailsPage(driver);
	}

	public PrintProjectDetailsPage clickOnPrintProjectDetailsUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Project Details' under Actions Dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectDetailsLink));
		printProjectDetailsLink.click();
		waitforLoadingRing();
		return new PrintProjectDetailsPage(driver);
	}

	public TrackPopUpPage clickOnTrackProjects() {
		extentTest.log(Status.INFO, "Clicking on Track Projects under 'Actions' dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public void clickOnHideProjects() {
		extentTest.log(Status.INFO, "Clicking on Hide Projects under 'Actions' dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hideProjectsMenu));
		hideProjectsMenu.click();
	}

	public TrackPopUpPage clickOnTrackLinkFromSummary() {
		extentTest.log(Status.INFO, "Clicking on Track Link from Project Summary");
		trackLink.click();
		return new TrackPopUpPage(driver);
	}

	public boolean isTrackProjectsMenuDisplayed() {
		extentTest.log(Status.INFO, "verify if Track Projects link is displayed under 'Actions' dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		return trackProjectsMenu.isDisplayed();
	}

	public boolean isResultContentDisplayed() {
		extentTest.log(Status.INFO, "verify if project result displayed");
		return resultContent.isDisplayed();
	}

	// Redirection to different pages
	public ProjectPlansPage clickOnPlansLink() {
		extentTest.log(Status.INFO, "Click on the Plans Tab");
		CommonUtils.scrollDownTillElement(plansLink, driver);
		plansLink.click();
		return new ProjectPlansPage(driver);
	}

	// Redirection to different pages
	public ProjectBiddersPage clickOnPlanholderBidderLink() {
		extentTest.log(Status.INFO, "Click on the Planholders and Bidder Tab");
		CommonUtils.scrollDownTillElement(planholdersBiddersLink, driver);
		planholdersBiddersLink.click();
		return new ProjectBiddersPage(driver);
	}

	public ProjectSpecsPage clickOnSpecsLink() {
		extentTest.log(Status.INFO, "Click on the Specs Tab");
		specsLink.click();
		waitforLoadingRing();
		return new ProjectSpecsPage(driver);
	}

	public ProjectNotesPage clickOnNotesLink() {
		extentTest.log(Status.INFO, "Click on the Notes Link");
		notesLink.click();
		return new ProjectNotesPage(driver);
	}

	public ProjectAddendaPage clickOnAddendaLink() {
		extentTest.log(Status.INFO, "Click on the Addenda Tab");
		CommonUtils.scrollDownTillElement(addendaLink, driver);
		addendaLink.click();
		return new ProjectAddendaPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitle() {
		extentTest.log(Status.INFO, "Clicking on First Project Title");
		SeleniumUtils.isVisible(projectTitles.get(0), driver);
		projectTitles.get(0).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithValuationContainChar() {
		extentTest.log(Status.INFO, "Clicking on First Project Title with valuation contain character in the value.");
		CommonUtils.scrollDownTillElement(projectTitlesWithValuationContainChar.get(0), driver);
		waitforLoadingRing();
		SeleniumUtils.isVisible(projectTitlesWithValuationContainChar.get(0), driver);
		projectTitlesWithValuationContainChar.get(0).click();
		return new ProjectPage(driver);
	}

	public boolean isCurrentView_ListView() {
		extentTest.log(Status.INFO, "Verifying the curent view on Project result page is List View");
		try {
			projectTitles.get(0).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ProjectPage clickOnSeaCliffES_And_ImprovementsSpecifcProject() {
		extentTest.log(Status.INFO, "Clicking on ES & North Shore HS Capital Improvements Project Title");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(projectTitles.get(0)));
		projectSeaCliff_SpecificProject.click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithAddenda() {
		extentTest.log(Status.INFO, "Clicking on a Project Title which has addenda");
		projectTitleswithAddendaLink.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithAddendaOutOfSubscription() {
		extentTest.log(Status.INFO, "Clicking on a Project Title which has addenda in project out of subscribtion.");
		projectTitleswithAddendaLinkOutOfSubs.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnProjectTitleWithDesignAlerts() {
		extentTest.log(Status.INFO, "Clicking on a Project Title which has Design Alerts");
		projectTitleWithDesignAlerts.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnThirdProjectTitle() {
		extentTest.log(Status.INFO, "Clicking on Third Project Title");
		projectTitles.get(2).click();
		return new ProjectPage(driver);
	}

	public ProjectCommonContainerPage clickOnFirstProjectTitleCommon() {
		extentTest.log(Status.INFO, "Clicking on First Project Title");
		projectTitles.get(0).click();
		return new ProjectCommonContainerPage(driver);
	}

	public ProjectPage clickOnSecondProjectTitle() {
		extentTest.log(Status.INFO, "Clicking on Second Project Title");
		projectTitles.get(1).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnLastProjectTitle() {
		extentTest.log(Status.INFO, "Clicking on Last  Project Title");
		int count = projectTitles.size() - 1;
		projectTitles.get(count).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnSpecProjectTitle(int count) {
		extentTest.log(Status.INFO, "Clicking on Last  Project Title");
		projectTitles.get(count).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnProjectTitleWithoutNotes() {
		extentTest.log(Status.INFO, "Clicking on Project Title which does not have notes");
		projectTitles.get(1).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithPlans() {
		extentTest.log(Status.INFO, "Click on Project Title With Plans");
		projectTitlesWithPlans.get(0).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public EcommercePage clickOnProjectWithPlansToAddThisProjectToYourLicense() {
		extentTest.log(Status.INFO, "Click on Project With Plans in order to Add this project to your license");
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfAllElements(addThisProjectToYourLicenseLinkWithPlansProjectTitle));
		String name = addThisProjectToYourLicenseLinkWithPlansProjectTitle.get(0).getText();
		addThisProjectToYourLicenseLinkWithPlans.get(0).click();
		waitforLoadingRing();
		return new EcommercePage(driver, name);
	}

	public ProjectPage clickOnFirstProjectTitleWithSpecs() {
		extentTest.log(Status.INFO, "Click on Project Title With Specs");
		SeleniumUtils.isVisible(projectTitleswithSpecsLink.get(0), driver);
		projectTitleswithSpecsLink.get(0).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithSpecsMatched() {
		extentTest.log(Status.INFO, "Click on Project Title With Specs matched");
		projectTitleswithSpecsLinkMatched.get(0).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithFirms() {
		extentTest.log(Status.INFO, "Click on Project Title With Firms");
		CommonUtils.scrollDownTillElement(projectTitlesWithFirms.get(0), driver);
		SeleniumUtils.isVisible(projectTitlesWithFirms.get(0), driver);
		projectTitlesWithFirms.get(0).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithBidders() {
		extentTest.log(Status.INFO, "Click on Project Title With Bidders");
		CommonUtils.scrollDownTillElement(projectTitleswithBiddersLink.get(0), driver);
		SeleniumUtils.isVisible(projectTitleswithBiddersLink.get(0), driver);
		projectTitleswithBiddersLink.get(0).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	// firstProjectNameWithAddenda
	// Click on first checkbox of Project Title With Bidders
	public void clickOnFirstCheckboxProjectTitleWithBidders() {
		extentTest.log(Status.INFO, "Click on first checkbox of Project Title With Bidders");
		projectTitlesWithBiddersCheckboxList.get(0).click();
	}

	public ProjectPage clickOnFirstProjectTitleWithPlansAndSpecs() {
		extentTest.log(Status.INFO, "Click on Project Title With Plans And Specs");
		projectTitlesWithPlansAndSpecs.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithPlansAndSpecsMatched() {
		extentTest.log(Status.INFO, "Click on Project Title With Plans And Specs matched");
		projectTitlesWithPlansAndSpecsMatched.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnMentionedProjectTitleWithPlansAndSpecs(int projectIndex) {
		extentTest.log(Status.INFO, "Click on Project Title With Plans And Specs");
		projectTitlesWithPlansAndSpecs.get(projectIndex).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithPlansAndSpecsAndAddenda() {
		extentTest.log(Status.INFO, "Click on Project Title With Plans And Specs");
		CommonUtils.scrollDownTillElement(projectTitlesWithPlansAndSpecsAndAddenda.get(0), driver);
		projectTitlesWithPlansAndSpecsAndAddenda.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithBidderPlansAddenda() {
		extentTest.log(Status.INFO, "Click on Project Title With Plans And Bidder and Addenda.");
		projectTitlesWithFirmAndBidderAndPlansAndAddenda.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithFirmAndBidderAndPlansAndSpecsAndAddenda() {
		extentTest.log(Status.INFO, "Click on Project Title With Firm and Bidder and Plans And Specs and Addenda.");
		int i = 0;
		while (projectTitlesWithFirmAndBidderAndPlansAndSpecsAndAddenda.size() == 0) {
			scrollResultPerPageAtBottom();
			waitforLoadingRing();
			if (i == 5) {
				break;
			}
			i++;
		}
		projectTitlesWithFirmAndBidderAndPlansAndSpecsAndAddenda.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithFirmBidderPlans() {
		extentTest.log(Status.INFO, "Click on Project Title With Firm and Bidder and Plans.");
		int i = 0;
		while (projectTitlesWithFirmAndBidderAndPlans.size() == 0) {
			scrollResultPerPageAtBottom();
			waitforLoadingRing();
			if (i == 5) {
				break;
			}
			i++;
		}
		projectTitlesWithFirmAndBidderAndPlans.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstProjectTitleWithBidderPlansSpec() {
		extentTest.log(Status.INFO, "Click on Project Title With Spec and Bidder and Plans.");
		int i = 0;
		while (projectTitlesWithSpecAndBidderAndPlans.size() == 0) {
			scrollResultPerPageAtBottom();
			waitforLoadingRing();
			if (i == 5) {
				break;
			}
			i++;
		}
		projectTitlesWithSpecAndBidderAndPlans.get(0).click();
		return new ProjectPage(driver);
	}

	public boolean IsSelectedTrackingDisplayed() {
		extentTest.log(Status.INFO, "Verify if The 'selections' area reflects the tracking list selected.");
		return selectedTrackingList.equals(trackingList.getText());
	}

	// Enter the search string in Section Visualization Search text field and
	// hit
	// the Enter Key on keyboard.
	public void enterTextInSectionVisualizationSearchField(String strToSearch) {
		sectionSeactTextField.sendKeys(strToSearch);
		new Actions(driver).sendKeys(Keys.ENTER).build().perform();
	}

	public void enterTextInSectionVisualizationFindInSearchBox(String strToSearch) {

		SeleniumUtils.isClickable(sectionFindInfilter, driver);
		sectionFindInfilter.sendKeys(strToSearch);
	}

	public Boolean IsFindInSearchBoxInSectionVisualtizationDisplayed() {
		SeleniumUtils.isVisible(sectionFindInfilter, driver);
		return sectionFindInfilter.isDisplayed();

	}

	public Boolean isRemoveIconFindInSearchBoxInSectionVisualtizationDisplayed() {
		SeleniumUtils.isVisible(removeIconFindInfilter, driver);
		return removeIconFindInfilter.isDisplayed();

	}

	public void clickRemoveIconFindInSearchBoxInSectionVisualtizationDisplayed() {
		SeleniumUtils.isVisible(removeIconFindInfilter, driver);
		removeIconFindInfilter.click();

	}

	public void pressEnterKey() {
		new Actions(driver).sendKeys(Keys.ENTER).build().perform();
	}

	public void clickSectionVisualizationSectionsSearchBtn() {
		searchIconOfSectionVisualizations.click();
	}

	public boolean IsMagnifyingGlassSearchButtonInSectionVisualizationSectionsDisplayed() {
		SeleniumUtils.isVisible(searchIconOfSectionVisualizations, driver);
		return searchIconOfSectionVisualizations.isDisplayed();
	}

	public boolean verifyAllSectionTitlesContainsTheSearchedKeyword(String strToCompare) {

		boolean resultFlag = false;
		for (WebElement compString : sectionsTitleInSectionVisualtionPage) {
			if (compString.isDisplayed()) {
				if (compString.getText().toUpperCase().contains(strToCompare.toUpperCase())) {
					resultFlag = true;
					break;
				}
			}
		}

		return resultFlag;
	}

	public Boolean isSectionsListDisplayedInSectionChart() {
		return SeleniumUtils.verifyWebElementListIsPresent(sectionsTitleInSectionVisualtionPage);

	}

	// *
	public void clickOnMyAccountLink() {
		extentTest.log(Status.INFO, "Click On MyAccount Link");
		SeleniumUtils.isVisible(myAccountLink, driver);
		myAccountLink.click();
	}

	// *
	public void clickOnFirstTrackingList() {
		extentTest.log(Status.INFO, "Click on First Tracking List");
		SeleniumUtils.isClickable(firstTrackingList, driver);
		firstTrackingList.click();

	}

	// *
	public void clickOnMySpecAlertsLink() {
		extentTest.log(Status.INFO, "Click On My SpecAlerts Link");
		mySpecAlertsLink.click();
	}

	// compare the search result.
	public boolean compareSearchResulOnSectionVisualization(String strToCompare) {

		boolean resultFlag = true;

		SeleniumUtils.isClickable(sectionSeactTextField, driver);
		for (WebElement compString : compareSectionSearchResultString) {
			if (!compString.getAttribute("desc").trim().toUpperCase().contains(strToCompare.trim().toUpperCase())) {
				resultFlag = false;
				break;
			}
		}

		return resultFlag;
	}

	// Click on the search icon of section visualizations.
	public void clickOnSearchIconOfSectionVisualizations() {
		searchIconOfSectionVisualizations.click();
	}

	// Click on the search icon of special condition filter.
	public void clickOnSpecialConditions() {
		extentTest.log(Status.INFO, "Click on the special condition filter");
		if (CommonUtils.checkElementExist(specialConditionsArrowUp, driver)) {
			specialConditionsFilter.click();
		}
	}

	// Click on the search icon of geography filter.
	public void clickOnGeographyFilter() {
		extentTest.log(Status.INFO, "Click on the geography filter.");
		if (CommonUtils.checkElementExist(GEOGRAPHY_COUNTRY_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(GEOGRAPHY_COUNTRY_Filter_ArrowUp, driver);
			geographyFilter.click();
			waitforLoadingRing();
		}
	}

	// Click on the StateRegionFilter filter.
	public void clickOnStateRegionFilter() {
		extentTest.log(Status.INFO, "Click on the StateRegion filter.");
		if (CommonUtils.checkElementExist(GEOGRAPHY_STATE_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(GEOGRAPHY_STATE_ArrowUp, driver);
			StateRegionFilter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnGEOGRAPHY_CountyFilter() {
		extentTest.log(Status.INFO, "Click on the county filter.");
		if (CommonUtils.checkElementExist(GEOGRAPHY_COUNTY_ArrowUp, driver)) {
			SeleniumUtils.isVisible(GEOGRAPHY_CountyFilter, driver);
			GEOGRAPHY_CountyFilter.click();
		}
		waitforLoadingRing();
	}

	// Click on the Texas state_Regin.
	public void clickOnTexaxState() {
		extentTest.log(Status.INFO, "Click on the texas State filter.");
		CommonUtils.scrollDownTillElement(californiaStateRegionChkbox, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		texasStateRegionChkbox.click();
		waitforLoadingRing();
	}

	// Click on the california state_Regin.
	public void clickOnCaliforniaState() {
		clickOnStateRegionFilter();
		californiaStateRegionChkbox.click();
		waitforLoadingRing();
	}

	// Click on the ohio state_Regin.
	public void clickOnOhioState() {
		extentTest.log(Status.INFO, "Click on the ohio State filter.");
		CommonUtils.scrollDownTillElement(ohioStateRegionChkbox, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		ohioStateRegionChkbox.click();
		waitforLoadingRing();
	}

	// Click on the Florida state_Regin.
	public void clickOnFloridaState() {
		extentTest.log(Status.INFO, "Click on the Florida State filter.");
		CommonUtils.scrollDownTillElement(floridaStateRegionChkbox, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		floridaStateRegionChkbox.click();
		waitforLoadingRing();
	}

	// Click on the New York state_Regin.
	public void clickOnNewYorkState() {
		extentTest.log(Status.INFO, "Click on the New York State filter.");
		CommonUtils.scrollDownTillElement(newYorkStateRegionChkbox, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		newYorkStateRegionChkbox.click();
		waitforLoadingRing();
	}

	// Click on the search icon of geography filter.
	public boolean checkGeographyFilter() {
		return geographyFilter.isDisplayed();
	}

	public boolean checkSpecialConditionFilterListSize() {
		return CommonUtils.verifyListSize(specialConditonFilterList);
	}

	// click on the special condition.

	// click on the special condition.
	public void clickOnSpecDivisionelement() {
		SeleniumUtils.isClickable(carpetSpecAlertchkbox, driver);
		carpetSpecAlertchkbox.click();
	}

	public void clickOnProfileFilter() {
		SeleniumUtils.isClickable(PROFILE_Filter, driver);
		PROFILE_Filter.click();
	}

	public Boolean isProfileFilterVisible() {
		extentTest.log(Status.INFO, "check if profile filter is displayed in left nav");
		return SeleniumUtils.isVisible(PROFILE_Filter, driver);

	}

	public void clickTurnOnProfile() {
		extentTest.log(Status.INFO, "click on Turn On link");
		if (turnOnProfileLink.isDisplayed())
			turnOnProfileLink.click();
		waitforLoadingRing();
	}

	public void clickTurnOffProfile() {
		extentTest.log(Status.INFO, "click on Turn Off link");
		if (turnOffProfileLink.isDisplayed())
			turnOffProfileLink.click();
		waitforLoadingRing();
	}

	public Boolean isTurnOnProfileLinkDisplayed() {
		extentTest.log(Status.INFO, "check if turn on profile link is displayed");
		if (turnOnProfileLink.isDisplayed())
			return true;
		else
			return false;
	}

	public Boolean isTurnOffProfileLinkDisplayed() {
		extentTest.log(Status.INFO, "check if turn off profile link is displayed");
		if (turnOffProfileLink.isDisplayed())
			return true;
		else
			return false;
	}

	// click on the special condition.
	public void clickOnSpecialConditionGreenBuildelement() {
		extentTest.log(Status.INFO, "Click on Special Condition Green Building check box");
		SeleniumUtils.isClickable(greenBuildingElementsSpecialConditionChkbox, driver);
		greenBuildingElementsSpecialConditionChkbox.click();
		waitforLoadingRing();
	}

	// click on the special condition.
	public void clickOnSpecDivisionElectricalType() {
		SeleniumUtils.isClickable(electricalSpecDivisionChkbox, driver);
		electricalSpecDivisionChkbox.click();
		waitforLoadingRing();
	}

	// click on the multiple special condition.
	public void clickOnMultipleSpecialCondition() {
		int count = 1;

		for (WebElement specialCond : specialConditonFilterList) {
			if (count <= 2) {
				SeleniumUtils.isClickable(specialCond, driver);
				specialCond.click();
			}

			if (count > 2) {
				break;
			}
			count++;
		}
	}

	// Return the count for the Green Building spec condition.
	public int getCountOfGreenBuildingSpecCondition() {
		return Integer.parseInt(greenBuildingElementsSpecialConditionCount.getText().replace("(", "").replace(",", "")
				.replace(")", ""));
	}

	// Return the count for the Green Building spec condition.
	public int getCountOfelectricalSpecDivision() {
		SeleniumUtils.isClickable(electricalSpecDivisionChkbox, driver);
		return Integer
				.parseInt(electricalSpecDivisionCount.getText().replace("(", "").replace(",", "").replace(")", ""));
	}

	public String getFilterCrumbCount() {
		SeleniumUtils.isClickable(sectionVisualizationsToggleBtn, driver);
		return projectResultCount.getText();
	}

	public int getAppliedFilterCount() {
		SeleniumUtils.isClickable(sectionVisualizationsToggleBtn, driver);
		return filterAppliedCount.size();
	}

	public int getAppliedFilterText() {

		SeleniumUtils.isClickable(sectionVisualizationsToggleBtn, driver);
		return filterAppliedCount.size();
	}

	public boolean IsContratorsEquipCheckBoxSelected() {
		extentTest.log(Status.INFO, "Verify if the Contactors option selected.");
		return (contractorsEquipElementMaterialsEquipChkbox.getAttribute("checked") != null);
	}

	public boolean IsVisualizeProjectIconPresent() {
		extentTest.log(Status.INFO, "Verify if Visualize Project Icon is Present");
		if (visualizeProjectIcon.size() == 0)
			return true;
		else
			return false;
	}

	public boolean checkSelectionOfMultipleSpecialContion(int count) {
		int i = 0;
		for (WebElement specialCond : specialConditonFilterList) {
			extentTest.log(Status.INFO, "Verify the Specs Tab");
			if (i < count) {
				if (!CommonUtils.isCheckboxSelected(specialCond)) {
					extentTest.log(Status.INFO, "Checkbox at count " + (i + 1) + " is not selected.");
					return false;
				}
			}
			if (i > count) {
				break;
			}
			i++;
		}
		return true;
	}

	// check the list whether element is present in the list or not
	public boolean checkSpecDivisionFilterListSize() {
		return CommonUtils.verifyListSize(specDivisionFilterList);
	}

	// check the texas string in the country address
	public boolean checkStateNameInCountryAddress(String containString) {
		SeleniumUtils.isVisible(countryAddressLabelLocator.get(0), driver);
		return CommonUtils.checkStringContainInCompleteList(containString, countryAddressLabelLocator);
	}

	public boolean checkBiddingWithinList(List<String> elementList) {
		List<String> biddingList = CommonUtils.getListFromWebElements(biddingWithinElement);
		List<String> compareBiddingList = new ArrayList<>();

		for (String bidding : biddingList) {
			if (bidding.contains("(")) {
				compareBiddingList.add(bidding.split(Pattern.quote("("))[0].trim().toUpperCase());

			} else {
				compareBiddingList.add(bidding.trim().toUpperCase());
			}
		}

		return CommonUtils.CompareTwoList(elementList, compareBiddingList);
	}

	// check the next Days month result for bidding days
	public boolean checkBiddingFilterForNextDaysMonth(String daysCount) {
		List<String> bidDateSearchResultStringList = CommonUtils.getListFromWebElements(bidDateSearchResultList);
		return CommonUtils.compareTwoListForStringContains(bidDateSearchResultStringList,
				CommonUtils.getListOfDate(Integer.parseInt(daysCount.replaceAll("[^0-9]+", ""))));
	}

	public void clickOnBiddingWithinFilter() {
		if (!CommonUtils.checkElementExist(biddingWithinFilterArrowDownIcon, driver)) {
			CommonUtils.scrollDownTillElement(biddingWithinFilter, driver);
			biddingWithinFilter.click();
			waitforLoadingRing();
		}
	}

	// Click on the Next Seven Days of bidding within.
	public void clickOnNextSevenDays() {
		extentTest.log(Status.INFO, "Clicking on Next 7 Days");
		clickOnBiddingWithinFilter();
		SeleniumUtils.isVisible(nextSevenDaysBiddingRadioBtn, driver);
		nextSevenDaysBiddingRadioBtn.click();
		waitforLoadingRing();
	}

	// Click on the Next fifteen Days of bidding within.
	public void clickOnNextFifteenDays() {
		extentTest.log(Status.INFO, "Clicking on Next 15 Days");
		clickOnBiddingWithinFilter();
		SeleniumUtils.isVisible(nextFifteenDaysBiddingRadioBtn, driver);
		nextFifteenDaysBiddingRadioBtn.click();
		waitforLoadingRing();
	}

	// Click on the Next One month of bidding within.
	public void clickOnNextOneMonthDays() {
		extentTest.log(Status.INFO, "Clicking on Next 1 Month");
		clickOnBiddingWithinFilter();
		SeleniumUtils.isVisible(nextOneMonthBiddingRadioBtn, driver);
		nextOneMonthBiddingRadioBtn.click();
		waitforLoadingRing();
	}

	// Click on the ASAP of bidding within.
	public void clickOnASAPBiddingDays() {
		extentTest.log(Status.INFO, "Clicking on ASAP");
		clickOnBiddingWithinFilter();
		SeleniumUtils.isVisible(asapBiddingRadioBtn, driver);
		asapBiddingRadioBtn.click();
		waitforLoadingRing();
	}

	// Click on the No Date Set bidding within.
	public void clickOnNDSBiddingDays() {
		extentTest.log(Status.INFO, "Clicking on No date set");
		clickOnBiddingWithinFilter();
		SeleniumUtils.isVisible(noDateSetBiddingRadioBtn, driver);
		noDateSetBiddingRadioBtn.click();
		waitforLoadingRing();
	}

	// set the custome range for bid within filter.
	public void setBidCustomRange(String bidFromDate, String bidToDate) {
		DatePickerUtils datePicker = new DatePickerUtils(driver);
		extentTest.log(Status.INFO, "Set the custome range for bid within filter.");
		clickOnBiddingWithinFilter();
		datePicker.setDate(customRange_BidWithIn_FromDate, bidFromDate);
		datePicker.setDate(customRange_BidWithIn_ToDate, bidToDate);
		waitforLoadingRing();
	}

	// check the existence of DesignAlert checkbox in Find In filter
	public boolean isDesignAlertCheckBoxDisplayed() {
		extentTest.log(Status.INFO, "Check if DesignAlert check box is present in Find In filter in Left Nav");
		try {
			return designalertFindInCheckbox.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	// Check if DesignAlerts link is highlighted
	public boolean isDesignAlertsLinkHighlighted() {
		extentTest.log(Status.INFO, "Check if DesignAlerts link is highlighted");
		return lnkDesignAlerts.get(0).getCssValue("background-color").equals("rgba(255, 255, 87, 1)");
	}

	// Click on first highlighted design alerts link of project
	public ProjectDesignAlertsPage clickOnFirstHighlightedDesignAlertsLink() {
		extentTest.log(Status.INFO, "Click on First highlighted Design alerts link");
		highlightedDesignAlerts.get(0).click();
		return new ProjectDesignAlertsPage(driver);
	}

	// Click on first design alerts link of project
	public ProjectDesignAlertsPage clickOnFirstDesignAlertsLink() {
		extentTest.log(Status.INFO, "Click on First Design alerts link");
		linkdesignAlerts.get(0).click();
		return new ProjectDesignAlertsPage(driver);
	}

	// Check on DesignAlert check box in Find In filter
	public void clickOnDesignAlertInFindInFilter() {
		extentTest.log(Status.INFO, "Clicking on DesignAlert option in FindIn filter.");
		designalertFindInCheckbox.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	// Check on Plan check box in Find In filter
	public void clickOncheckPlansFindInFilter() {
		extentTest.log(Status.INFO, "Clicking on DesignAlert option in FindIn filter.");
		plansFindInCheckbox.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	// Check on DesignAlert check box in Find In filter

	public boolean IsdesignalertFindInCheckBoxUnchecked() {
		extentTest.log(Status.INFO, "Verify if the design alert checkbox in Find In filter is unselected.");
		boolean unChecked;
		unChecked = designalertFindInCheckbox.isSelected();
		return unChecked;
	}

	// check the search result contain ASAP and NDS bidding option
	public boolean checkStringASAP_And_NDS_Bidding(String containString) {
		return CommonUtils.checkStringContainInCompleteList(containString, bidDateSearchResultList);
	}

	public boolean IsContratorsEquipCheckBoxUnchecked() {
		extentTest.log(Status.INFO, "Verify if the Contractors checkbox is unselected.");
		boolean unChecked;
		unChecked = contractorsEquipElementMaterialsEquipChkbox.isSelected();
		return unChecked;
	}

	public boolean IsaddendaSpecDivisionCheckBoxSelected() {
		extentTest.log(Status.INFO, "Verify if the addenda checkbox selected.");
		return (addendaSpecDivisionchkbox.getAttribute("checked") != null);
	}

	public boolean IsaddendaSpecDivisionCheckBoxUnchecked() {
		extentTest.log(Status.INFO, "Verify if the addenda checkbox is unselected.");
		boolean unChecked;
		unChecked = addendaSpecDivisionchkbox.isSelected();
		return unChecked;
	}

	public boolean IsProcurementSpecDivisionCheckBoxChecked() {
		extentTest.log(Status.INFO, "Verify if the addenda checkbox is checked.");

		return procurementSpecDivisionchkbox.isSelected();

	}

	public boolean IsCarpetCheckBoxSelected() {
		extentTest.log(Status.INFO, "Verify if the Carpet checkbox is selected.");
		return (carpetSpecAlertchkbox.getAttribute("checked") != null);
	}

	public boolean IsCarpetCheckBoxUnchecked() {
		extentTest.log(Status.INFO, "Verify if the Contractors checkbox is unselected.");
		boolean unChecked;
		unChecked = carpetSpecAlertchkbox.isSelected();
		return unChecked;
	}

	public boolean iserrorMessageDisplayed() {
		extentTest.log(Status.INFO, "Verify if the error message displayed or not.");
		String errorText = PublishRange_errorMessage.getText();
		if (errorText.contains("Your search - - did not match any documents"))
			;
		return true;
	}

	public boolean isSaveSearchButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if save search buton is displayed or not.");
		if (searchBtn.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean IsValuationArrowUpFilterDisplayed() {
		return CommonUtils.checkElementExist(VALUATION_Filter_ArrowUp, driver);
	}

	public boolean IsTRACKING_LIST_ArrowUpFilter_displayed() {
		return CommonUtils.checkElementExist(TRACKING_LIST_ArrowUpFilter, driver);
	}

	public void clickOnTrackingListFilter() {
		extentTest.log(Status.INFO, "Click on TrackingList filter");
		if (CommonUtils.checkElementExist(TRACKING_LIST_ArrowUpFilter, driver)) {
			CommonUtils.scrollDownTillElement(TRACKING_LIST_ArrowUpFilter, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			trackingListFilter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnTrackingListFilterToCloseAccordion() {
		extentTest.log(Status.INFO, "Click on TrackingList filter");
		if (!CommonUtils.checkElementExist(TRACKING_LIST_ArrowUpFilter, driver)) {
			trackingListFilter.click();
		}
	}

	public boolean IsSPEC_DIVISION_ArrowUpFilter_displayed() {
		return CommonUtils.checkElementExist(SPEC_DIVISION_ArrowUpFilter, driver);
	}

	public void clickOnSpecAlertFilter() {
		extentTest.log(Status.INFO, "Click on SpecAlert filter");
		if (CommonUtils.checkElementExist(SPEC_ALERT_ArrowUpFilter, driver)) {
			CommonUtils.scrollDownTillElement(SPEC_ALERT_ArrowUpFilter, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			specAlertFilter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnSpecialFilter() {
		extentTest.log(Status.INFO, "Clicking on special filter");
		if (CommonUtils.checkElementExist(specialFilterArrowUp, driver)) {
			specialFilter.get(0).click();
		}
	}

	public boolean isSpecialFilterDisplayed() {
		extentTest.log(Status.INFO, "Verify if the special filter displayed");
		return specialFilter.isEmpty();
	}

	public void clickOnSpecAlertFilterToCloseAccordion() {
		if (!CommonUtils.checkElementExist(SPEC_ALERT_ArrowUpFilter, driver)) {
			specAlertFilter.click();
		}
	}

	public void clickOnSpecDivisionFilter() {
		if (CommonUtils.checkElementExist(SPEC_DIVISION_ArrowUpFilter, driver)) {
			CommonUtils.scrollDownTillElement(SPEC_DIVISION_ArrowUpFilter, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			specDivisionFilter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnSpecDivisionFilterToCloseAccordion() {
		if (!CommonUtils.checkElementExist(SPEC_DIVISION_ArrowUpFilter, driver)) {
			specDivisionFilter.click();
		}
	}

	public boolean IsSPEC_ALERT_ArrowUpFilter_displayed() {
		return CommonUtils.checkElementExist(SPEC_ALERT_ArrowUpFilter, driver);
	}

	// Click to expand Materials/Equip filter.
	public void clickOnMaterialsEquipFilter() {
		extentTest.log(Status.INFO, "Clicking on materials Equip Filter");
		if (CommonUtils.checkElementExist(MES_EQUIP_CATEGORY_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(MES_EQUIP_CATEGORY_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			materialsEquipFilter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnMaterialsEquip2Filter() {
		extentTest.log(Status.INFO, "Clicking on materials Equip 2 Filter");
		if (CommonUtils.checkElementExist(MES_EQUIP2_Code_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(MES_EQUIP2_Code_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			materialsEquip2Filter.click();
		}
		waitforLoadingRing();
	}

	public void PopUpClose() {
		CommonUtils.clickOnElementUsingJavascript(ClosePopup, driver);
	}

	// Click to expand open Materials/Equip filter pop up.
	public void clickOnMaterialsEquipFilterFacet() {
		materialsEquipFilterFacet.click();
	}

	// Click to open Geography filter pop up.
	public void clickOngeographyFilterSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on geography Filter SeeMore Btn");

		geographyFilterSeeMore_Btn.click();

	}

	public void clickOnTrackingListFilterSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on TrackingList Filter SeeMore Btn");
		TRACKING_LIST_SeeMore_btn.click();
	}

	public void clickOnGEOGRAPHY_STATESeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on GEOGRAPHY_STATE SeeMore Btn");
		CommonUtils.scrollDownTillElement(GEOGRAPHY_STATESeeMore_Btn, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		GEOGRAPHY_STATESeeMore_Btn.click();
		waitforLoadingRing();
	}

	public void clickOnGEOGRAPHY_COUNTYSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on GEOGRAPHY_COUNTY See More Btn");
		GEOGRAPHY_COUNTYSeeMore_Btn.click();
	}

	// Click to open ACTION_STAGE_CATEGORY filter pop up.

	public void clickOnACTION_STAGE_CATEGORYSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on ACTION STAGE CATEGORY See More Btn");
		ACTION_STAGE_CATEGORYSeeMore_Btn.click();
	}

	public void clickOnACTION_STAGE2_SeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on ACTION STAGE 2 See More Btn");
		ACTION_STAGE2_SeeMore_Btn.click();
	}

	// Click to uncheck USA checkbox on Geography popup
	public void clickOnUSACheckBoxPopupWindow() {
		if (!geographyUSAChkboxPopup.isSelected()) {
			geographyUSAChkboxPopup.click();
		}
	}

	// Click to uncheck USA checkbox on Geography popup
	public void deselectUSACheckBoxPopupWindow() {
		if (geographyUSAChkboxPopup.isSelected()) {
			geographyUSAChkboxPopup.click();
		}
	}

	// Click to check Africa checkbox on Geography popup
	public void clickOnAFRCheckBoxPopupWindow() {
		if (!geographyAfricachkboxPopup.isSelected()) {
			geographyAfricachkboxPopup.click();
		}
	}

	// Click to check Africa checkbox on Geography popup
	public void clickOnUpdateButton() {
		try {
			geographyPopUpUapdateButton.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchBtn));
		} catch (Exception ex) {
		}
	}

	// Click on given count of project titles with spec alerts and return the
	// list of selected projects
	public List<String> clickOnMultipleProjectsWithSpecAlertsChkBox(int numberOfSelect) {

		List<String> projectTitleWithSpecAlertList = new ArrayList<>();
		extentTest.log(Status.INFO, "Clicking on multiple check box for projects titles having spec alerts");
		for (int count = 0; count < numberOfSelect; count++) {
			projectTitleWithSpecAlertList.add(projectTitlesWithSpecAlertsName.get(count).getText());
			projectTitlesWithSpecAlertsChkBox.get(count).click();
			waitforLoadingRing();
		}

		return projectTitleWithSpecAlertList;
	}

	// Click on project title with spec alerts and return the
	// list of selected projects
	public ProjectPage clickOnSingleProjectTitleWithSpecAlerts() throws InterruptedException {
		extentTest.log(Status.INFO, "Clicking on the projects titles having spec alerts");
		String selectedProject = "";
		for (int i = 0; i < projectTitlesWithSpecAlertsName.size(); i++) {
			selectedProject = projectTitlesWithSpecAlertsName.get(i).getText().replaceAll("[^A-Za-z\\s]", "").trim();
			projectTitlesWithSpecAlertsName.get(i).click();
			break;
		}
		System.out.println("selectedProject:" + selectedProject);
		return new ProjectPage(driver, selectedProject);
	}

	public void clickOnClearAllLinkInFilter() {
		extentTest.log(Status.INFO, "Click on Clear All link to remove all filter");
		SeleniumUtils.isVisible(filterclearAllLink, driver);
		SeleniumUtils.isClickable(filterclearAllLink, driver);
		filterclearAllLink.click();
		waitforLoadingRing();
	}

	public boolean isSpecDivisionFilterCrumbDisplayed() {
		extentTest.log(Status.INFO, "Check if Spec Division filter selection is displayed in Filter crumb");
		return specDivisionFilterCrumb.isDisplayed();
	}

	public boolean isTradeLeftNavFilterDisplayed() {
		extentTest.log(Status.INFO, "Check if Trade filter present in Left Nav");
		return tradeFilter.isDisplayed();
	}

	public boolean isGeographyFilterDisplayed() {
		extentTest.log(Status.INFO, "Check if Geography filter is present in Left Nav");
		return geographyFilter.isDisplayed();
	}

	public boolean isGeographyCollapsedOnLoad() {
		extentTest.log(Status.INFO, "Check if Geography filter in Left Nav is closed on load");
		return GEOGRAPHY_COUNTRY_Filter_ArrowUp.isDisplayed();
	}

	public boolean isMaterialsEquipFillterDisplayed() {
		extentTest.log(Status.INFO, "Check if Material/Equip filter present in Left Nav");
		return materialsEquipFilter.isDisplayed();
	}

	public boolean isMaterialsEquipFillterSubOptions() {
		extentTest.log(Status.INFO, "Check if Material/Equip filter has sub options");
		return materialsEquipFilterSubOptions.isDisplayed();
	}

	public boolean checkMaterialsEquipFilterOption_Diaplayed() {
		extentTest.log(Status.INFO, "Check if Material/Equip filter has sub options");
		return CommonUtils.verifyListSize(mesCategoryFacetList);
	}

	public boolean isPopUpDisplayed() {
		extentTest.log(Status.INFO, "Check if pop up is opened after clicking on facet");
		driver.switchTo().activeElement();
		return FilterFacetPopUp.isDisplayed();
	}

	public boolean checkTradePopupContainingAllOptionsSize() {
		extentTest.log(Status.INFO, "Check Trade popup containing all options");
		return CommonUtils.verifyListSize(TradeOptionstList);
	}

	public boolean isMaterialEquipFilterCrumbDisplayed() {
		extentTest.log(Status.INFO, "Check if Material/Equip filter selection is displayed in Filter crumb");
		return materialEquipFilterCrumb.isDisplayed();
	}

	public String getNoteslinkTooltipText(String noteText) throws InterruptedException {
		Actions ToolTip = new Actions(driver);
		SeleniumUtils.scrollToView(driver, notesLinkthird);
		ToolTip.clickAndHold(notesLinkthird).build().perform();
		// waiting for dynamic div which fetches tooltip data to load
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.attributeContains(notesLinkthird, "original-title", noteText));
		String ToolTipText = notesLinkthird.getAttribute("original-title");
		return ToolTipText;
	}

	public boolean isErrorMessageDisplayed() {
		return errLabel.isDisplayed();
	}

	public String getErrorMessage() {
		extentTest.log(Status.INFO, "Get the error message");
		SeleniumUtils.isVisible(errLabel, driver);
		return errLabel.getText().trim();
	}

	public String getFirstDRNumer() {
		extentTest.log(Status.INFO, "Get the first DR Number on Project Results Page");
		String drText = firstDRNumber.getText();
		return drText.substring(4, 17);
	}

	public void clickOnSelectAllProjects() {
		extentTest.log(Status.INFO, "Click on Select All in the Projects List");
		projectSelectAll.click();
	}

	public void clickOnmaterialsEquipFilter_FirstOption() {

		if (!SeleniumUtils.isVisible(materialsEquipFilter_FirstOption, driver)) {
			clickOnMaterialsEquipFilter();

		}
		extentTest.log(Status.INFO, "Click on Materials EquipFilter FirstOption");
		SeleniumUtils.isClickable(materialsEquipFilter_FirstOption, driver);
		materialsEquipFilter_FirstOption.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);

	}

	public void clickOnBiddingWithinNext7DaysOption() {
		extentTest.log(Status.INFO, "Click on Bidding Within Next 7 Days Option");
		if (!SeleniumUtils.isVisible(BiddingWithinNext7DaysOption, driver)) {
			CommonUtils.scrollDownTillElement(biddingWithinFilter, driver);
			biddingWithinFilter.click();
		}
		SeleniumUtils.isClickable(BiddingWithinNext7DaysOption, driver);
		BiddingWithinNext7DaysOption.click();
		waitforLoadingRing();
	}

	public void clickOnStateRegion_FirstCheckBox() {

		if (!SeleniumUtils.isVisible(StateRegin_FirstOption, driver)) {
			SeleniumUtils.isClickable(StateRegionFilter, driver);
			StateRegionFilter.click();
		}

		extentTest.log(Status.INFO, "Click on Bidding Within Next 7 Days Option");
		SeleniumUtils.isClickable(StateRegin_FirstOption, driver);
		StateRegin_FirstOption.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public Boolean mouseOverActionandChecktrackProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Check if Track Projects is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		return trackProjectsMenu.isDisplayed();
	}

	public void mouseOverTexasInMap() {
		Actions action = new Actions(driver);
		action.clickAndHold(mapChartProjectDensityTexas).build().perform();
		extentTest.log(Status.INFO, "Mouse Over Texas in Map");

	}

	public TrackPopUpPage mouseOverActionandClicktrackProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click track projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public void mouseOverActionandClickAlertOn() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click alert on under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(alertonUnderActions));
		alertonUnderActions.click();
		SeleniumUtils.isClickable(searchBtn, driver);
	}

	public void mouseOverActionandClickAlertOff() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click alert off under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(alertoffUnderActions));
		alertoffUnderActions.click();
	}

	public Boolean mouseOverActionandCheckHideProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hideProjectsMenu));
		extentTest.log(Status.INFO, "Check if Hide Projects displayed");
		return hideProjectsMenu.isDisplayed();
	}

	public Boolean mouseOverActionandCheckUnhideProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(unhideProjectsMenu));
		extentTest.log(Status.INFO, "Check if unHide Projects displayed");
		return unhideProjectsMenu.isDisplayed();
	}

	public void mouseOverActionandClickUnhideProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click unHide projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(unhideProjectsMenu));
		unhideProjectsMenu.click();
	}

	public void mouseOverActionandClickHideProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click Hide projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hideProjectsMenu));
		hideProjectsMenu.click();
	}

	public Boolean mouseOverActionandCheckViewProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(viewProjectsMenu));
		extentTest.log(Status.INFO, "Check if View Projects displayed");
		return viewProjectsMenu.isDisplayed();
	}

	public void mouseOverActionandClickViewProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click view projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(viewProjectsMenu));
		viewProjectsMenu.click();
	}

	public Boolean mouseOverActionandCheckDownloadProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		extentTest.log(Status.INFO, "Check if Download Projects displayed");
		return downloadProjectsMenu.isDisplayed();
	}

	public DownloadProjects mouseOverActionandClickDownloadProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click download projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		downloadProjectsMenu.click();
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		return new DownloadProjects(driver);
	}

	public DownloadProjects mouseOverActionandClickDownloadProjects_SpecAlertPage() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdownSpecAlert).build().perform();
		extentTest.log(Status.INFO, "Click download projects under Actions menu");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		downloadProjectsMenu.click();

		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);

		return new DownloadProjects(driver);
	}

	public DownloadProjects SpecAlertmouseOverActionandClickDownloadProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(specAlertactionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click download projects under Actions menu");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadProjectsMenu));
		downloadProjectsMenu.click();

		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("rbPdf")));
		return new DownloadProjects(driver);
	}

	public Boolean mouseOverActionandCheckEmailProjectsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailProjectsMenu));
		extentTest.log(Status.INFO, "Check if Email Projects displayed");
		return emailProjectsMenu.isDisplayed();
	}

	public EmailAlertsPage mouseOverActionandClickEmailProjects() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click email projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailProjectsMenu));
		emailProjectsMenu.click();
		return new EmailAlertsPage(driver);
	}

	public EmailAlertsPage clickEmailProjectsLinkUnderActionsDrpDwn() {
		extentTest.log(Status.INFO, "Click email projects under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailProjectsMenu));
		emailProjectsMenu.click();
		return new EmailAlertsPage(driver);
	}

	public Boolean mouseOverActionandCheckPrintProjectDetailsDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectDetailsMenu));
		extentTest.log(Status.INFO, "Check if Print Project Details displayed");
		return printProjectDetailsMenu.isDisplayed();
	}

	public void mouseOverActionandClickPrintProjectDetails() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click print project Details under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectDetailsMenu));
		printProjectDetailsMenu.click();
	}

	public Boolean mouseOverActionandCheckPrintProjectListDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectListMenu));
		extentTest.log(Status.INFO, "Check if Print Project List displayed");
		return printProjectListMenu.isDisplayed();
	}

	public void mouseOverActionandClickPrintProjectList() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Click print project list under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printProjectListMenu));
		printProjectListMenu.click();
	}

	public SavedSearchPopUp clickOnSavedSearch() {
		SeleniumUtils.isClickable(saveSearchBtn, driver);
		extentTest.log(Status.INFO, "Click on Saved Search button in the Projects Results");
		saveSearchBtn.click();
		return new SavedSearchPopUp(driver);
	}

	public void enterSearchText(final String searchText) {
		extentTest.log(Status.INFO, "Entering search text on the Projects Results");
		searchTextbox.click();
		searchTextbox.sendKeys(searchText);
	}

	public void performSearchString(String searchText) {
		SeleniumUtils.isClickable(searchTxtField, driver);
		extentTest.log(Status.INFO, "Performing search on the Projects Results page.");
		searchTxtField.clear();
		searchTxtField.sendKeys(searchText);
		clickOnSearchBtn();
		waitforLoadingRing();
	}

	public Integer getListProjectCountText() {
		extentTest.log(Status.INFO, "Get the list Project Count in the Project Results Page");
		String countText = listProjectCount.getText();
		countText = countText.substring(countText.indexOf("of") + 3, countText.length()).trim().replace(",", "");
		return Integer.parseInt(countText);
	}

	public boolean verifyProjectTitleExistsInList(String projectTitle) {
		extentTest.log(Status.INFO, "Verify if the Project is found in the project list");
		CommonUtils.IterateWebElementsList(projectTitles);
		return CommonUtils.getListFromWebElements(projectTitles).contains(projectTitle);
	}

	public boolean isUnhideDisplayed() {
		extentTest.log(Status.INFO, "Verify if Unhide Link is displayed for the hidden projects");
		return unhideLink.isDisplayed();
	}

	public boolean isProjectSelectAllDisplayed() {
		extentTest.log(Status.INFO, "Verify if Select All is displayed");
		return projectSelectAll.isDisplayed();
	}

	public boolean isRemoveLinkPresent() {
		extentTest.log(Status.INFO, "Verify if remove Link is displayed for the hidden projects");
		return SeleniumUtils.isVisible(removeLink, driver);
	}

	public boolean isAlertOnLinkPresent() {
		extentTest.log(Status.INFO, "Verify if Alert ON Link is displayed for the hidden projects");
		return SeleniumUtils.isVisible(alertOnLink, driver);
	}

	public void clickOnUnhideLink() {
		extentTest.log(Status.INFO, "Click on Unhide Link for the hidden projects");
		unhideLink.click();
	}

	public boolean isAlertIconDisplayed() throws InterruptedException {
		extentTest.log(Status.INFO, "verify if alert icon is displayed for the project");
		Thread.sleep(3000);
		if (projectIconImage.getAttribute("src").toString().toLowerCase().contains("images/star-alert-icon.png"))
			return true;
		else
			return false;
	}

	public void clickOnFindInFilter() {
		extentTest.log(Status.INFO, "Clicking on FindIn filter.");
		if (CommonUtils.checkElementExist(findInArrowUpFilter, driver)) {
			findInFilter.click();
			SeleniumUtils.isVisible(newsFindInCheckbox, driver);
		}
	}

	public void clickOnPageNumber4() {
		extentTest.log(Status.INFO, "Click on Fourth Page number.");
		pageNumber4.click();

	}

	public void clickOnPageNumber2() {
		extentTest.log(Status.INFO, "Click on Second Page number.");
		pageNumber2.click();

	}

	public void clickOnNextPageNumber() {
		extentTest.log(Status.INFO, "Click on Next Page number.");
		prevNextPage.click();
	}

	public void clickOnPreviousPageNumber() {
		extentTest.log(Status.INFO, "Click on Next Page number.");
		prevPreviousPage.click();
	}

	public void clickOnFirstPageIcon() {
		extentTest.log(Status.INFO, "Click on first Page icon.");
		firstPageIcon.click();

	}

	public Boolean IsPageNumber4HighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Page number 4 is highlighted in bold");
		CommonUtils.scrollDownTillElement(pageNumber4, driver);
		return CommonUtils.isFontBold(driver, pageNumber4);

	}

	public Boolean IsPageNumber2HighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Page number 2 is highlighted in bold");
		return CommonUtils.isFontBold(driver, pageNumber2);

	}

	public Boolean IsPageNumber1HighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Page number 1 is highlighted in bold");
		return CommonUtils.isFontBold(driver, pageNumber1);
	}

	public String getCountOfProjectsDisplayed() {
		extentTest.log(Status.INFO, "Return the count of the total Projects Displayed");
		String str = projectCountTxt.getText();
		int lastIndex = str.lastIndexOf(" ");
		return (str.substring(lastIndex + 1, str.length()).replaceAll(",", ""));
	}

	public void clickOnDropDownList() {
		extentTest.log(Status.INFO, "Clicking on Drop Down List");
		dropDownListBtn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mySearchesTrackingList));
	}

	public TrackingList clickOnMySearchesTrackingList() {
		extentTest.log(Status.INFO, "Clicking on My Searches Dropdown Tracking List");
		mySearchesTrackingList.click();
		return new TrackingList(driver);
	}

	public SpecAlertsResultsPage clickOnMySearchesSpecAlerts() {
		extentTest.log(Status.INFO, "Clicking on My Searches Dropdown SpecAlerts");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mySearchesSpecAlerts));
		mySearchesSpecAlerts.click();
		return new SpecAlertsResultsPage(driver);
	}

	public SavedSearchesPage clickOnMySavedSearches() {
		extentTest.log(Status.INFO, "Clicking on My Saved Searches Dropdown option.");
		SeleniumUtils.isVisible(mySavedSearches, driver);
		mySavedSearches.click();
		return new SavedSearchesPage(driver);
	}

	public boolean isMySavedSearchesDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Saved Searches displayed in the my searches dropdown");
		return mySavedSearches.isDisplayed();
	}

	public SavedSearchesPage clickOnMySearchesSavedSearches() {
		extentTest.log(Status.INFO, "Clicking on My Searches Dropdown Saved Searches.");
		mySearchesSavedSearch.click();
		return new SavedSearchesPage(driver);
	}

	public String getPublishRange_DropDown_OptionText() {
		extentTest.log(Status.INFO, "Get drop down option text");
		return PublishRange_DropdownOptionTxt.getText();
	}

	public String getDrGridText() {
		extentTest.log(Status.INFO, "Get DR Grid text");
		return drGrid.getText();
	}

	public void clickOnPublishRangeFilter() {
		extentTest.log(Status.INFO, "Click on Publish Range Filter");
		if (CommonUtils.checkElementExist(PUBLISH_RANGE_Filter_ArrowUp, driver)) {
			PublishRangeFilter.click();
			waitforLoadingRing();
		}
	}

	public boolean IsPublishRange_DropdownOptionDisplayed() {
		extentTest.log(Status.INFO, "Check is Publish Range Filter with default Last Quarter option displayed");
		return PublishRange_DropdownOptionTxt.isDisplayed();
	}

	public void enterPublichRangeFrom() {
		extentTest.log(Status.INFO, "Enter Publich Range From date");
		SeleniumUtils.isClickable(PublichRangeFromtxt, driver);
		PublichRangeFromtxt.click();
		SeleniumUtils.isClickable(PublichRangeFromday, driver);
		PublichRangeFromday.click();
		waitforLoadingRing();
	}

	public void enterPublichRangeTo() {
		extentTest.log(Status.INFO, "Enter Publich Range To date");
		SeleniumUtils.isClickable(PublichRangeTotxt, driver);
		PublichRangeTotxt.click();
		SeleniumUtils.isClickable(PublichRangeToday, driver);
		PublichRangeToday.click();
		waitforLoadingRing();
	}

	public void clickOnValuationFilter() {
		extentTest.log(Status.INFO, "Click on the Valuation filter.");
		if (CommonUtils.checkElementExist(VALUATION_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(VALUATION_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			ValuationFilter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnHyperLink(String linkText) {
		extentTest.log(Status.INFO, "Click on Hyperlink" + linkText);
		driver.findElement(By.partialLinkText(linkText)).click();
	}

	public boolean isLoadingRingDisplayed() {
		extentTest.log(Status.INFO, "Check to see loading ring is displayed or not ");
		return SeleniumUtils.isVisible(rotateLoadingIcon, driver);

	}

	public boolean isLoadingRingDisplayedForGridLazyLoad() {
		extentTest.log(Status.INFO,
				"Check to see loading ring is displayed or not , small wait time to scroll grid faster");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			wait.until(ExpectedConditions.visibilityOf(rotateLoadingIcon));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLoadingIconInvisible() {
		if (SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver)) {
			return true;
		}
		return false;
	}

	public void CheckFilterOptionVisible(WebElement Filter, WebElement Option) {
		if (!SeleniumUtils.isVisible(Option, driver)) {
			Filter.click();
			SeleniumUtils.isClickable(Option, driver);
		}
	}

	public void clickOnValMinimumDdAndSelectFirstOpt() {
		CheckFilterOptionVisible(ValuationFilter, valMinimumDropdown);
		extentTest.log(Status.INFO, "Click on valMinimumDropdown and select first option ");
		valMinimumDropdown.click();
		valMinimumDropdownFirstOption.click();
		waitforLoadingRing();
	}

	public String getvalMinimumDropdowntxt() {
		return valMinimumDropdown.getText();
	}

	public String getvalMaximumDropdowntxt() {
		return valMaximumDropdown.getText();
	}

	public List<String> getAllCrumbFilterTextList() {
		extentTest.log(Status.INFO, "Get all applied filter text");
		return CommonUtils.getListFromWebElements(FilterCrumb_AppliedFilter_List);
	}

	public String getcrumbFirstFiltertxt() {
		extentTest.log(Status.INFO, "Get the first applied filter text");
		return FilterCrumb_AppliedFilter_List.get(0).getText();
	}

	public String getcrumbSecondFiltertxt() {
		extentTest.log(Status.INFO, "Get the second applied filter text");
		SeleniumUtils.isVisible(FilterCrumb_AppliedFilter_List.get(1), driver);
		try {
			return FilterCrumb_AppliedFilter_List.get(1).getText();
		} catch (Exception ex) {
			return "";
		}
	}

	public String getFilterScrumbTxt() {
		extentTest.log(Status.INFO, "Get the filter scrumb text");
		try {
			return filterScrumb.getText();
		} catch (Exception ex) {
			return "";
		}
	}

	public String getcrumbThirdFiltertxt() {
		extentTest.log(Status.INFO, "Get the third applied filter text");
		return FilterCrumb_AppliedFilter_List.get(2).getText();
	}

	public String getcrumbForthFiltertxt() {
		extentTest.log(Status.INFO, "Get the forth applied filter text");
		return FilterCrumb_AppliedFilter_List.get(3).getText();
	}

	public void ClickOncrumbFirstFilterClose() {
		extentTest.log(Status.INFO, "Remove first crumb filter");
		FilterCrumb_AllClose_btn.get(0).click();
		waitforLoadingRing();
	}

	public void ClickOncrumbSecondFilterClose() {
		extentTest.log(Status.INFO, "Remove second crumb filter");
		FilterCrumb_AllClose_btn.get(1).click();
		waitforLoadingRing();
	}

	public void ClickOncrumbThirdFilterClose() {
		extentTest.log(Status.INFO, "Remove third crumb filter");
		FilterCrumb_AllClose_btn.get(2).click();
		waitforLoadingRing();
	}

	public void ClickOncrumbForthFilterClose() {
		extentTest.log(Status.INFO, "Remove forth crumb filter");
		FilterCrumb_AllClose_btn.get(3).click();
		waitforLoadingRing();
	}

	public void ClickOncrumbSecondFilterLink() {
		extentTest.log(Status.INFO, "Click on second crumb filter");
		crumbFilterLinks.get(1).click();
	}

	public void ClickOncrumbFirstGroupFilterLink() {
		extentTest.log(Status.INFO, "Click on first group crumb filter");
		crumbFilterLinks.get(0).click();
	}

	public String mouseOverOncrumbSecondFilterLinkandGetTooltip() {
		extentTest.log(Status.INFO, "MouseOver on second crumb filter");
		Actions actions = new Actions(driver);
		actions.clickAndHold(appliedfiltersList.get(1)).build().perform();
		return appliedfiltersList.get(1).getAttribute("title");
	}

	public String mouseOverOncrumbFirstFilterLinkandGetTooltip() {
		extentTest.log(Status.INFO, "MouseOver on first crumb filter");
		String filterTitle = "";
		if (!appliedKeywordfiltersList.isEmpty()) {
			Actions actions = new Actions(driver);
			actions.clickAndHold(appliedKeywordfiltersList.get(0)).build().perform();
			filterTitle = appliedKeywordfiltersList.get(0).getAttribute("original-title");
		}
		return filterTitle;
	}

	public String mouseOverProjectListBtnAndGetTooltip() throws InterruptedException {
		extentTest.log(Status.INFO, "MouseOver on Project List view button and Get tooltip");
		Actions actions = new Actions(driver);
		actions.clickAndHold(projectListBtn);
		Thread.sleep(2000);
		return projectListBtn.getAttribute("original-title");
	}

	public String mouseOverDashboard1BtnAndGetTooltip() throws InterruptedException {
		extentTest.log(Status.INFO, "MouseOver on Dashboard1 view button and Get tooltip");
		Actions actions = new Actions(driver);
		actions.clickAndHold(Dashboard1Btn);
		Thread.sleep(2000);
		return Dashboard1Btn.getAttribute("original-title");
	}

	public String mouseOverDashboard2BtnAndGetTooltip() throws InterruptedException {
		extentTest.log(Status.INFO, "MouseOver on Dashboard2 view button and Get tooltip");
		Actions actions = new Actions(driver);
		actions.clickAndHold(Dashboard2Btn);
		Thread.sleep(2000);
		return Dashboard2Btn.getAttribute("original-title");
	}

	public boolean iscrumbfilterUASLinkDisplayed() {
		extentTest.log(Status.INFO, "Check is crumb filter UAS Link displayed");
		return FilterCrumb_AppliedFilter_List.get(0).isDisplayed();
	}

	public boolean iscrumbSecondFilterDisplayed() {
		extentTest.log(Status.INFO, "Check is crumb Second Filter displayed");
		return FilterCrumb_AppliedFilter_List.get(1).isDisplayed();
	}

	public boolean iscrumbThirdFilterDisplayed() {
		extentTest.log(Status.INFO, "Check is crumb Third Filter option displayed");
		clickOnCommonShowMoreFiltes_Arrow();
		return FilterCrumb_AppliedFilter_List.get(2).isDisplayed();
	}

	public boolean isShowMoreFilterDisplayed() {
		extentTest.log(Status.INFO, "Check is Show More Filter option is displayed");
		return CommonShowMoreFiltes.getText().equals("Show More");
	}

	public boolean isShowLessFilterDisplayed() {
		extentTest.log(Status.INFO, "Check is Show Less Filter option is displayed");
		return CommonShowLessFilters.getText().equals("Show Less");
	}

	public boolean iscrumbForthFilterDisplayed() {
		extentTest.log(Status.INFO, "Check is crumb Forth Filter option displayed");
		clickOnCommonShowMoreFiltes_Arrow();
		return FilterCrumb_AppliedFilter_List.get(3).isDisplayed();
	}

	public void clickOnValMaximumDdAndSelectSecondOpt() {
		CheckFilterOptionVisible(ValuationFilter, valMaximumDropdown);
		extentTest.log(Status.INFO, "Click on valMinimumDropdown and select Second option ");
		valMaximumDropdown.click();
		valMaximumDropdownSecondOption.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void clickOnvaluationListsecondRadio() {
		CheckFilterOptionVisible(ValuationFilter, valuationListsecondRadio);
		extentTest.log(Status.INFO, "Click on valuation List second Radio button");
		CommonUtils.scrollDownTillElement(valuationListsecondRadio, driver);
		waitforLoadingRing();
		valuationListsecondRadio.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void clickOnvaluationListFirstRadio() {
		extentTest.log(Status.INFO, "Click on valuation List first Radio button");
		valuationListFirstRadio.click();
		waitforLoadingRing();
	}

	public boolean isvalMinimumDropdownDisplayed() {
		CheckFilterOptionVisible(ValuationFilter, valMinimumDropdown);
		return valMinimumDropdown.isDisplayed();
	}

	public boolean isvalvalMaximumDropdownDisplayed() {
		CheckFilterOptionVisible(ValuationFilter, valMaximumDropdown);
		return valMaximumDropdown.isDisplayed();
	}

	public boolean isvaluationListsecondRadioSelected() {
		CheckFilterOptionVisible(ValuationFilter, valuationListsecondRadio);
		return valuationListsecondRadio.isEnabled();
	}

	public void clickOnactionStageCatFacet_1_Ckbox() {
		CheckFilterOptionVisible(ACTION_STAGE_CATEGORY_Filter, actionStageCatFacet_1_Ckbox);
		extentTest.log(Status.INFO, "Click on action Stage CatFacet first Check box");
		actionStageCatFacet_1_Ckbox.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void clickOnactionStageCatFacet_5_Ckbox() {
		CheckFilterOptionVisible(ACTION_STAGE_CATEGORY_Filter, actionStageCatFacet_5_Ckbox);
		extentTest.log(Status.INFO, "Click on action Stage CatFacet Abandon Check box");
		actionStageCatFacet_5_Ckbox.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void clickOnOwnershipFederalCkbox() {
		CheckFilterOptionVisible(OWNERSHIP_TYPE_Filter, OWNERSHIP_TYPE_LHSFederalOption_cbk);
		extentTest.log(Status.INFO, "Click on Ownership type federal Check box");
		OWNERSHIP_TYPE_LHSFederalOption_cbk.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public boolean isACTION_STAGE_CODE_FilterDisplayed() {
		extentTest.log(Status.INFO, "Check ACTION_STAGE_CODE_Filter is displayed");
		CommonUtils.scrollDownTillElement(ACTION_STAGE_CODE_Filter, driver);
		return ACTION_STAGE_CODE_Filter.isDisplayed();
	}

	public boolean isactionStage_Code_SelectAllLinkDisplayed() {
		extentTest.log(Status.INFO, "Check actionStage_Code_SelectAllLink is displayed");
		CommonUtils.scrollDownTillElement(actionStage_Code_SelectAllLink, driver);
		return actionStage_Code_SelectAllLink.isDisplayed();
	}

	public boolean isactionStage_Code_DeselectAll_Link() {
		extentTest.log(Status.INFO, "Check actionStage_Code_DeselectAll_Link is displayed");
		CommonUtils.scrollDownTillElement(actionStage_Code_DeselectAll_Link, driver);
		return actionStage_Code_DeselectAll_Link.isDisplayed();
	}

	public boolean checkactionStageCodeFacetListSize() {
		extentTest.log(Status.INFO, "Check if action Stage Code Facet List filter has sub options");
		return CommonUtils.verifyListSize(actionStageCodeFacetList);
	}

	public String getACTION_STAGE_CATEGORY_Filter_txt() {
		extentTest.log(Status.INFO, "Check action stahe categories filter name");
		return ACTION_STAGE_CATEGORY_Filter.getText();
	}

	public String getACTION_STAGE_CODE_Filter_txt() {
		extentTest.log(Status.INFO, "Check the action stage code filter name");
		return ACTION_STAGE_CODE_Filter.getText();
	}

	public void clickOnprojTypeCatFacet_1_Ckbox() {
		CheckFilterOptionVisible(PROJECT_TYPE_CATEGORY_Filter, projTypeCatFacet_1_Ckbox);
		extentTest.log(Status.INFO, "Click on project Type Cat Facet_1_Ckbox Check box");
		projTypeCatFacet_1_Ckbox.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public String getPROJECT_TYPE_CATEGORY_Filter_txt() {
		extentTest.log(Status.INFO, "Check the project type categories filter name");
		return PROJECT_TYPE_CATEGORY_Filter.getText();
	}

	public String getSavedSearchNameinLeftNav() {
		extentTest.log(Status.INFO, "Get the saved search name in left nav");
		return savedSearchName.getText();
	}

	public String getPROJECT_TYPE_CODE_Filter() {
		extentTest.log(Status.INFO, "Check the PROJECT TYPE CODE Filter name");
		return PROJECT_TYPE_CODE_Filter.getText();
	}

	public String getExcludeTL_lbl() {
		CheckFilterOptionVisible(trackingListFilter, excludeTL_ckbox);
		extentTest.log(Status.INFO, "Get exclude Tracking List label");
		return excludeTL_lbl.getText();

	}

	public void clikcExcludeTrackingListChkbox() {
		extentTest.log(Status.INFO, "Get exclude Tracking List label");
		excludeTL_lbl.click();

	}

	public String getexcludeSpecAlert_lbl() {
		extentTest.log(Status.INFO, "Get exclude Spec Alert label");
		return excludeSpecAlert_lbl.getText();

	}

	public boolean checkTrackingListFacetListSize() {
		extentTest.log(Status.INFO, "Check to see whether tracking list have options or not");
		return CommonUtils.verifyListSize(trackingListFacetList);
	}

	public boolean checkspecAlertFilterListListSize() {
		return CommonUtils.verifyListSize(specAlertFilterList);
	}

	public void clickOnPlanOpenInFindInFilter() {
		extentTest.log(Status.INFO, "Clicking on Plans option in FindIn filter.");
		clickOnFindInFilter();
		plansOptionFindInFilterChkbox.click();
		waitforLoadingRing();
	}

	public boolean isPlanInFindInFilterChecked() {
		extentTest.log(Status.INFO, "Check if Plans option in FindIn filter is checked");
		clickOnFindInFilter();
		if (plansOptionFindInFilterChkbox.getAttribute("checked") == null)
			return false;
		else
			return true;

	}

	public boolean isSpecsInFindInFilterChecked() {
		extentTest.log(Status.INFO, "Check if Specs option in FindIn filter is checked");
		clickOnFindInFilter();
		if (specsOptionFindInFilterChkbox.getAttribute("checked") == null)
			return false;
		else
			return true;

	}

	public boolean isDisplayedPlanclassSheetFilter() {
		return planClassSheetFilter.isDisplayed();
	}

	public boolean isDisplayedSpecDivisionFilter() {
		extentTest.log(Status.INFO, "check if Spec division filter is displayed");
		return specDivisionFilter.isDisplayed();
	}

	public void clickOnPlanClassSheetFilter() {
		extentTest.log(Status.INFO, "Clicking on Plan Class Sheet filter.");

		if (CommonUtils.checkElementExist(planClassSheetArrowUpFilter, driver)) {
			planClassSheetFilter.click();

		}
	}

	public boolean checkOptionPresentInPlanClassSheetFilter() {
		return CommonUtils.verifyListSize(planClassSheetFilterList);
	}

	public boolean checkProjectFilterApperance(String filterName_1, String filterName_2, String attributeName) {
		filterName_1 = filterName_1.replace(" ", "_").toUpperCase().trim();
		filterName_2 = filterName_2.replace(" ", "_").toUpperCase().trim();
		List<String> projectFilterNameList = CommonUtils.getListFromWebElementsAttribute(projectFilterOptionList,
				attributeName);
		boolean resultFlag = false;
		for (int i = 0; i < projectFilterNameList.size(); i++) {
			if (projectFilterNameList.get(i).equals(filterName_1)) {
				if (projectFilterNameList.get(i + 1).equals(filterName_2)) {
					resultFlag = true;
					break;
				}
			}
		}

		return resultFlag;
	}

	public void ClickOnspecDivision_SeeMore_Popup_btn() {
		extentTest.log(Status.INFO, "Clicking on spec Division See More button to open the popup");
		CommonUtils.scrollDownTillElement(specDivision_SeeMore_Popup_btn, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		specDivision_SeeMore_Popup_btn.click();
		waitforLoadingRing();

	}

	public void ClickOnSpecalerts_SeeMore_Popup_btn() {
		extentTest.log(Status.INFO, "Clicking on SpecAlerts See More button to open the popup");
		specAlerts_SeeMore_Popup_btn.click();
	}

	public boolean isMEnuExistsInLeftPane(String menuName) {
		extentTest.log(Status.INFO, "Verify if menu '" + menuName + "' exists in the left pane");

		for (WebElement webElement : leftExpandCollapseList) {
			if (webElement.getText().equalsIgnoreCase(menuName)) {
				return true;
			}
		}
		return false;
	}

	public void clickingOnSpecAlertsFilter() throws InterruptedException {
		extentTest.log(Status.INFO, "Clicking on Programs from the Spec Alerts Filter on the left");
		for (int index = 1; index < specAlertsLeftProgramsFilter.size(); index++) {
			this.selectedSpecAlertProgram = specAlertsLeftProgramsFilter.get(index).getText()
					.replaceAll("[^A-Za-z\\s]", "").trim();
			SeleniumUtils.scrollToView(driver, specAlertsLeftProgramsFilter.get(index));
			specAlertsLeftProgramsFilter.get(index).click();
			waitforLoadingRing();
			break;
		}
	}

	public boolean isleftMenuAppliedFilterListContainsSpecAlertProgram() {
		extentTest.log(Status.INFO, "Verify if program from left menu Spec Alerts Filter applied successfully");

		SeleniumUtils.scrollToView(driver, appliedFilterContainer);
		SeleniumUtils.waitForListOfWebElementsToBePresent(driver, leftMenuAppliedFilterList);
		CommonUtils.IterateList(CommonUtils.getListFromWebElements(leftMenuAppliedFilterList));
		selectedSpecAlertProgram = selectedSpecAlertProgram.replaceAll("[^A-Za-z\\s]", "").trim();
		List<String> listWebElements = CommonUtils.getListFromWebElements(leftMenuAppliedFilterList);
		boolean isMatch = false;

		for (String string : listWebElements) {
			string = string.replaceAll("[^A-Za-z\\s]", "").trim();
			if (string.contains(selectedSpecAlertProgram)) {
				isMatch = true;
			}
		}
		return isMatch;
	}

	public boolean isleftMenuAppliedFilterListContainsSpecAlertProgram(String SelectedLeftSpecFilter) {
		extentTest.log(Status.INFO, "Verify if program from left menu Spec Alerts Filter applied successfully");

		SeleniumUtils.scrollToView(driver, appliedFilterContainer);
		SeleniumUtils.waitForListOfWebElementsToBePresent(driver, leftMenuAppliedFilterList);
		CommonUtils.IterateList(CommonUtils.getListFromWebElements(leftMenuAppliedFilterList));
		return CommonUtils.getListFromWebElements(leftMenuAppliedFilterList).contains(SelectedLeftSpecFilter.trim());
	}

	public boolean checkHiddenProjectTrackingListResultDisplayed() {
		extentTest.log(Status.INFO,
				"Verify selected tracking list result page displayed the selected tracking list name.");
		return CommonUtils.checkElementExist(hiddenProjectTrackingNameResult, driver);

	}

	public boolean checkMyProjectTrackingListResultDisplayed() {
		extentTest.log(Status.INFO,
				"Verify selected tracking list result page displayed the selected tracking list name.");
		return CommonUtils.checkElementExist(myProjectTrackingNameResult, driver);
	}

	public boolean checkMyCompaniesTrackingListResultDisplayed() {
		extentTest.log(Status.INFO,
				"Verify selected tracking list result page displayed the selected tracking list name.");
		return CommonUtils.checkElementExist(myCompaniesTrackingNameResult, driver);
	}

	public void SwitchToFrame() throws InterruptedException {
		SeleniumUtils.switchToFrame(driver, PopupFrame);
	}

	public boolean verifyIsSpecAlertsBold() {
		extentTest.log(Status.INFO, "Verify whether the specalerts label is displayed in bold?");
		String specalertFont = specAlertsLabel.getCssValue("font-weight");
		System.out.println("specalertFont:" + specalertFont);

		return CommonUtils.isFontBold(driver, specAlertsLabel);
	}

	public void SwitchToActiveElement() {
		driver.switchTo().activeElement();
	}

	public void ClickOnFilterpopClose() {
		extentTest.log(Status.INFO, "Close filter popup");
		SeleniumUtils.isClickable(FilterpopClose, driver);
		FilterpopClose.click();
	}

	public boolean isFilterPopupDisplayed() {
		extentTest.log(Status.INFO, "Check is filter popup displayed");
		return Filterpop.isDisplayed();
	}

	public void SwitchToParent() {
		SeleniumUtils.switchToParent(driver);
	}

	public String getspecDivisionNav_RefineYourResults_txt() {
		extentTest.log(Status.INFO, "Capcturing text from header 'refine your results' ");
		return specDivisionNav_RefineYourResults_txt.getText();
	}

	public void ClickOnspecDivisionNav_UpdateResults_btn() throws InterruptedException {
		extentTest.log(Status.INFO, "Click on specDivisionNav_UpdateResults_btn ");
		specDivisionNav_UpdateResults_btn.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchBtn));
	}

	public ProjectResultsPage test() {
		return new ProjectResultsPage(driver);
	}

	public boolean verifySpecDivisionSeeMorePopupFooter() {
		extentTest.log(Status.INFO, "Verify spec division see more popup footer");
		return specDivisionSeeMorePopupFooterTxt.isEmpty();
	}

	public String getspecDivisionNav_UpdateResults_btn_Txt() {
		extentTest.log(Status.INFO, "Capcturing text from update results button");
		return specDivisionNav_UpdateResults_btn.getAttribute("value");
	}

	public boolean CheckAllSpecGroupAreCollapsed() {
		int size = specDivision_SeeMore_Popup_SpecGroupsAreExpandeded.size();
		if (size == 0) {
			return true;
		}
		return false;
	}

	public void ClickOngeographyPopupStateLink() {
		extentTest.log(Status.INFO, "Click On state navigation link from geography Popup");
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		SeleniumUtils.isVisible(geographyPopupStateLink, driver);
		SeleniumUtils.isClickable(geographyPopupStateLink, driver);
		geographyPopupStateLink.click();
		waitforLoadingRing();
	}

	public void ClickOnGEOGRAPHYPopupprojectCountyNavLink() {
		extentTest.log(Status.INFO, "Click On county navigation link from geography Popup");
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		SeleniumUtils.isVisible(GEOGRAPHYPopupprojectCountyNavLink, driver);
		SeleniumUtils.isClickable(GEOGRAPHYPopupprojectCountyNavLink, driver);
		GEOGRAPHYPopupprojectCountyNavLink.click();
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
	}

	public void ClickOnACTION_STAGE_CATEGORYUpdateFancyBoxbtn() {
		extentTest.log(Status.INFO, "Click On Update button on ACTION_STAGE_CATEGORY popup");
		ACTION_STAGE_CATEGORYUpdateFancyBoxbtn.click();
	}

	public void clickOnActionStageFilter() {
		extentTest.log(Status.INFO, "Clicking on Action Stage Filter");
		if (CommonUtils.checkElementExist(ACTION_STAGE_CATEGORY_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(ACTION_STAGE_CATEGORY_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			ACTION_STAGE_CATEGORY_Filter.click();
		}
		waitforLoadingRing();
	}

	public void clickOn_ACTION_STAGE_CODE_Filter() {
		extentTest.log(Status.INFO, "Clicking on Action Stage Filter");
		if (CommonUtils.checkElementExist(ACTION_STAGE_CODE_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(ACTION_STAGE_CODE_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			ACTION_STAGE_CODE_Filter.click();
		}
		waitforLoadingRing();
	}

	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
	}

	public void clickOnProjectGroupsFilter() {
		extentTest.log(Status.INFO, "Clicking on PROJECT Group_Filter");
		if (CommonUtils.checkElementExist(ProjectGrups_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(ProjectGrups_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			ProjectGrups_Filter_ArrowUp.click();
		}
		waitforLoadingRing();
	}

	public void CloseProjectGroupsFilter() {
		extentTest.log(Status.INFO, "Close PROJECT Group_Filter accordion");
		ProjectGrups_Filter_ArrowDown.click();
	}

	public void clickOnPROJECT_TYPE_CODE_Filter() {
		extentTest.log(Status.INFO, "Clicking on PROJECT_TYPE_CATEGORY_Filter");
		if (CommonUtils.checkElementExist(ProjectTypes_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(ProjectTypes_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			PROJECT_TYPE_CODE_Filter.click();
		}
		waitforLoadingRing();
	}

	// Click to open Project groups filter pop up.

	public void clickOnProjectGroupsSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Project Groups See More Btn");
		ProjectGroupsSeeMore_Btn.click();
	}

	public void clickOnProjectTypesSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Project Types See More Btn");
		ProjectTypesSeeMore_Btn.click();
	}

	public void clickOnPlanClassSheetSheetSeeMore_btn() {
		extentTest.log(Status.INFO, " Click on Project Types See More Btn");
		planClassSheetSeeMore_Btn.click();
	}

	public void ClickOnPopupUpdateFancyBoxbtn() {
		extentTest.log(Status.INFO, "Click On Update button");
		PopupUpdateFancyBoxbtn.click();
	}

	// Click to open Trades filter pop up.
	public void clickOnTradesSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Trades See More Btn");
		TradesSeeMore_Btn.click();
	}

	public void clickOnSpecificTradesSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Specific Trades See More Btn");
		SpecificTradesSeeMore_Btn.click();
	}

	public void clickOnTradesFilter() {
		extentTest.log(Status.INFO, "Clicking on Trades Filter");
		if (CommonUtils.checkElementExist(Trades_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(Trades_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			TRADE_TYPE_CATEGORY_Filter.click();
		}
		waitforLoadingRing();
	}

	public void clickOnSpecificTRADE_Filter() {
		extentTest.log(Status.INFO, "Clicking on specific Trades Filter");
		if (CommonUtils.checkElementExist(TRADE_TYPE_CODE_Filter_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(TRADE_TYPE_CODE_Filter_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			SpecificTRADE_Filter.click();
		}
		waitforLoadingRing();
	}

	public String getSelectedBarInChart1Text() {
		return selectedBarInchart1.getAttribute("desc").toString();
	}

	public String getSelectedBarInChart2Text() {
		return selectedBarInchart2.getAttribute("desc").toString();
	}

	public boolean isAnyBarSelectedinChart1() {
		return SeleniumUtils.isVisible(selectedBarInchart1, driver);
	}

	public boolean isAnyBarSelectedinChart2() {
		return SeleniumUtils.isVisible(selectedBarInchart2, driver);
	}

	// Click to open Materials_EquipSeeMore_Btn
	public void clickOnMaterials_EquipSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Materials_Equip See More Btn");
		SeleniumUtils.scrollToView(driver, Materials_EquipSeeMore_Btn);
		waitforLoadingRing();
		SeleniumUtils.isClickable(Materials_EquipSeeMore_Btn, driver);
		Materials_EquipSeeMore_Btn.click();
	}

	public void clickOnMaterials_Equip2SeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Materials_Equip 2 See More Btn");
		SeleniumUtils.scrollToView(driver, Materials_Equip2SeeMore_Btn);
		waitforLoadingRing();
		SeleniumUtils.isClickable(Materials_Equip2SeeMore_Btn, driver);
		Materials_Equip2SeeMore_Btn.click();
	}

	public void SelectOptionsFromTheList(int cout, String ParentList) throws InterruptedException {
		switch (ParentList) {
		case "GeographyFilterList":
			ClickOnMultipleOptionFromTheList(cout, GeographyFilterList);
			break;
		case "materialsEquipFilterList":
			ClickOnMultipleOptionFromTheList(cout, materialsEquipFilterList);
			break;
		case "CommonPopupParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, CommonPopupParentFilterList);
			break;
		case "Trades_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, Trades_LHS_ParentFilterList);
			break;
		case "ProjectDeliveryFilterFacets":
			ClickOnMultipleOptionFromTheList(cout, ProjectDeliveryFilterFacets);
			break;
		case "StateRegionFilterList":
			ClickOnMultipleOptionFromTheList(cout, StateRegionFilterList);
			break;
		case "GEOGRAPHY_COUNTYFilterList":
			ClickOnMultipleOptionFromTheList(cout, GEOGRAPHY_COUNTYFilterList);
			break;
		case "geographyPopupUASStatesList":
			ClickOnMultipleOptionFromTheList(cout, geographyPopupUASStatesList);
			break;
		case "COUNTYPopupUASCountyList":
			ClickOnMultipleOptionFromTheList(cout, COUNTYPopupUASCountyList);
			break;
		case "actionStageCodeFacetList":
			ClickOnMultipleOptionFromTheList(cout, actionStageCodeFacetList);
			break;
		case "trackingListFacetList":
			ClickOnMultipleOptionFromTheList(cout, trackingListFacetList);
			break;
		case "specAlertFilterList":
			ClickOnMultipleOptionFromTheList(cout, specAlertFilterList);
			break;
		case "specDivisionFilterList":
			ClickOnMultipleOptionFromTheList(cout, specDivisionFilterList);
			break;
		case "specDivisionFilterListPopup":
			ClickOnFewOptionsFromSpecDivisionPopUpList(cout, specDivisionFilterListPopup);
			ClickOnLastOptionFromThePopUpList(specDivisionFilterListPopup);
			break;
		case "ProjectGroups_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, ProjectGroups_LHS_ParentFilterList);
			break;
		case "SpecificTrades_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, SpecificTrades_LHS_ParentFilterList);
			break;
		case "ACTION_STAGE_CATEGORY_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, ACTION_STAGE_CATEGORY_LHS_ParentFilterList);
			break;
		case "BuildingCLG_WaterProofing_FilterList":
			ClickOnMultipleOptionFromTheList(cout, BuildingCLG_WaterProofing_FilterList);
			break;
		case "ProjectTypes_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, ProjectTypes_LHS_ParentFilterList);
			break;
		case "BiddingOptionsFilterListFromPopup":
			ClickOnMultipleOptionFromTheList(cout, BiddingOptionsFilterListFromPopup);
			break;
		case "ProjectGroupsParent2FilterList":
			ClickOnMultipleOptionFromTheList(cout, ProjectGroupsParent2FilterList);
			break;
		case "CommercialOptionsFilterListFromPopup":
			ClickOnMultipleOptionFromTheList(cout, CommercialOptionsFilterListFromPopup);
			break;
		case "SpecificTrades_BuildingUtilities_PopopFilterList":
			ClickOnMultipleOptionFromTheList(cout, SpecificTrades_BuildingUtilities_PopopFilterList);
			break;
		case "materialsEquip_LHS_FilterList":
			ClickOnMultipleOptionFromTheList(cout, materialsEquip_LHS_FilterList);
			break;
		case "materialsEquip2_LHS_FilterList":
			ClickOnMultipleOptionFromTheList(cout, materialsEquip2_LHS_FilterList);
			break;
		case "specAlertSeeMorePopUpListChkBox":
			ClickOnMultipleOptionFromTheList(cout, specAlertSeeMorePopUpListChkBox);
			break;
		case "trackingList_PopupOptionList":
			ClickOnMultipleOptionFromTheList(cout, trackingList_PopupOptionList);
			break;
		case "ConstructionType_LHSFilterList":
			ClickOnMultipleOptionFromTheList(cout, ConstructionType_LHSFilterList);
			break;
		case "OWNERSHIP_TYPE_LHSFilterList":
			ClickOnMultipleOptionFromTheList(cout, OWNERSHIP_TYPE_LHSFilterList);
			break;
		case "SPECIAL_Conditions_LHSFilterList":
			ClickOnMultipleOptionFromTheList(cout, SPECIAL_Conditions_LHSFilterList);
			break;
		case "biddingWithin_LHSFilterList":
			ClickOnMultipleOptionFromTheList(cout, biddingWithin_LHSFilterList);
			break;
		case "StructuralPropertiesCheckboxList":
			ClickOnMultipleOptionFromTheList(cout, StructuralPropertiesCheckboxList);
			break;
		case "ProjectResult_Chkbox_List":
			ClickOnMultipleOptionFromTheList(cout, ProjectResult_Chkbox_List);
			break;
		case "ChartView_DivGBarList":
			ClickOnMultipleOptionFromTheList(cout, ChartView_DivGBarList);
			break;
		case "FindIn_LHS_CbkList":
			ClickOnMultipleOptionFromTheList(cout, FindIn_LHS_CbkList);
			break;
		case "Common_Popup_FirstLevel2CbksList":
			ClickOnMultipleOptionFromTheList(cout, Common_Popup_FirstLevel2CbksList);
			break;
		case "ProjectType_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, ProjectType_LHS_ParentFilterList);
			break;

		case "pdsSeeMorePopUpListChkBox":
			ClickOnMultipleOptionFromTheList(cout, pdsSeeMorePopUpListChkBox);
			break;
		}
	}

	// Click On index From The Filter List
	public String ClickOnIndexFromTheList(int index) {
		extentTest.log(Status.INFO, "Click on index" + index + " from the Construction type filter list");
		CommonUtils.IterateList(CommonUtils.getListFromWebElements(ConstructionType_LHSFilterListName));
		String strSelected = ConstructionType_LHSFilterListName.get(index).getText();
		ConstructionType_LHSFilterList.get(index).click();
		waitforLoadingRing();
		return strSelected;

	}

	// Click On Multiple Option From The List
	private void ClickOnMultipleOptionFromTheList(int cout, List<WebElement> eList) throws InterruptedException {
		extentTest.log(Status.INFO, "Click on first " + cout + " options from the list");
		try {
			int count = 1;
			for (WebElement ParentFilter : eList) {
				if (count <= cout) {
					CommonUtils.scrollDownTillElement(ParentFilter, driver);
					if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
						waitforLoadingRing();
					}
					SeleniumUtils.isVisible(ParentFilter, driver);
					SeleniumUtils.isClickable(ParentFilter, driver);
					ParentFilter.click();
					if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
						waitforLoadingRing();
					}
					count++;
				}
				if (count > cout) {
					break;
				}
			}
		} catch (Exception ex) {
		}
	}

	// Click On Multiple Option From The List
	public void ClickOnFewOptionsFromSpecDivisionPopUpList(int count, List<WebElement> list)
			throws InterruptedException {
		for (int i = 1; i < count; i++) {
			list.get(i).click();
		}
		Thread.sleep(2000);
	}

	public void ClickOnLastOptionFromThePopUpList(List<WebElement> list) throws InterruptedException {
		int size = list.size();
		list.get(size - 1).click();
		Thread.sleep(2000);
	}

	public void clickOnMySearchesDropDown() {
		extentTest.log(Status.INFO, "Click on My Searches dropDown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mySearchesDropDown));
		mySearchesDropDown.click();
	}

	public void clickOnSTRUCTURAL_PROPERTIESFilter() {
		extentTest.log(Status.INFO, "Clicking on STRUCTURAL_PROPERTIES Filter");
		if (CommonUtils.checkElementExist(STRUCTURAL_PROPERTIES_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(STRUCTURAL_PROPERTIES_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			STRUCTURAL_PROPERTIESFilter.click();
			waitforLoadingRing();
		}
	}

	public TrackingLists clickOnTrackingListFromMySearchesDropDown() {
		extentTest.log(Status.INFO, "Click on Tracking List option present in My Searches dropDown");
		mySearchesTrackingListOption.click();
		return new TrackingLists(driver);
	}

	public void clickOnStructuralPropLink() {
		extentTest.log(Status.INFO, "Click on Structural Prop Link");
		CommonUtils.scrollDownTillElement(StructuralPropLink, driver);
		waitforLoadingRing();
		StructuralPropLink.click();
	}

	public void clickOnStructuralAddProperties() {
		extentTest.log(Status.INFO, "Click on AddProperties Link");
		SeleniumUtils.isClickable(addProperties, driver);
		addProperties.click();
	}

	public boolean checkStructuralPropSectionList(List<String> elementList) {
		List<String> actualList = CommonUtils.getListFromWebElements(StructuralPropertiesSectionList);
		return CommonUtils.CompareTwoList(elementList, actualList);
	}

	public String getSquareArealbl() {
		extentTest.log(Status.INFO, "Get Square Area label");
		return SquareArealbl.getText();

	}

	public String getNoOfBuildingslbl() {
		extentTest.log(Status.INFO, "Get NoOfBuildingslbl label");
		return NoOfBuildingslbl.getText();

	}

	public String getStoriesAboveGradelbl() {
		extentTest.log(Status.INFO, "Get StoriesAboveGradelbl label");
		return StoriesAboveGradelbl.getText();

	}

	public String getStoriesBelowGradelbl() {
		extentTest.log(Status.INFO, "Get StoriesBelowGradelbl label");
		return StoriesBelowGradelbl.getText();

	}

	public String getBuildingFramelbl() {
		extentTest.log(Status.INFO, "Get BuildingFramelbl label");
		return BuildingFramelbl.getText();

	}

	public boolean CheckisSelected_StructuralProperties() {
		return CommonUtils.isCheckboxListSelected(StructuralPropertiesCheckboxList);

	}

	// check the default selection of type dropdown.
	public boolean checkDefaultValueOfStructPropDDL() {
		extentTest.log(Status.INFO, "Verify the default selection for structural properties dropdown.");
		String defaultSelectionMinValue = "No Minimum";
		String defaultSelectionMaxValue = "No Maximum";
		for (int i = 0; i < 7; i++) {
			Select DDSelect = new Select(StrucPropDDList.get(i));

			if (DDSelect.getFirstSelectedOption().getText().toUpperCase().equals(defaultSelectionMinValue)
					|| DDSelect.getFirstSelectedOption().getText().toUpperCase().equals(defaultSelectionMaxValue)) {

				return false;
			}
		}
		return true;
	}

	public void SelectValuesFromStrucPropDDList() {
		extentTest.log(Status.INFO, "Select values from dropdown boxes");

		Select DDSelect0 = new Select(StrucPropDDList.get(0));
		DDSelect0.selectByIndex(DDSelect0.getOptions().size() - 1);
		Select DDSelect1 = new Select(StrucPropDDList.get(1));
		DDSelect1.selectByIndex(2);
		Select DDSelect2 = new Select(StrucPropDDList.get(2));
		DDSelect2.selectByIndex(DDSelect2.getOptions().size() - 1);
		Select DDSelect3 = new Select(StrucPropDDList.get(3));
		DDSelect3.selectByIndex(2);
		Select DDSelect4 = new Select(StrucPropDDList.get(4));
		DDSelect4.selectByIndex(DDSelect4.getOptions().size() - 1);
		Select DDSelect5 = new Select(StrucPropDDList.get(5));
		DDSelect5.selectByIndex(2);
		Select DDSelect6 = new Select(StrucPropDDList.get(6));
		DDSelect6.selectByIndex(DDSelect6.getOptions().size() - 1);
		Select DDSelect7 = new Select(StrucPropDDList.get(7));
		DDSelect7.selectByIndex(2);
	}

	public String getTextFirstCheckboxSpecAlert() {
		extentTest.log(Status.INFO, "get first value from Spec Alert Frame checkboxes");
		return specAlertsCheckBoxTextList.get(0).findElement(By.tagName("span")).getText();
	}

	public void clickOnBtnCancelMerx() {
		try {
			btnCancelMerx.click();
		} catch (Exception ex) {

		}
	}

	public void clickOnTextFirstCheckboxSpecAlert() {
		extentTest.log(Status.INFO, "click first value from Spec Alert Frame checkboxes");
		CommonUtils.clickOnElementUsingJavascript(specAlertsCheckBoxTextList.get(0).findElement(By.tagName("input")),
				driver);
	}

	public String getTextFirstCheckboxBuildingFrame() {
		extentTest.log(Status.INFO, "get first value from Building Frame checkboxes");
		return buildingFrameCheckboxTextList.get(0).getText();
	}

	public String getTextFirstCheckboxPlanClassSheet() {
		extentTest.log(Status.INFO, "get first value from Plan Class Sheet checkboxes");
		return planSheetCheckboxTextList.get(0).getText();
	}

	public void clickOnFirstCheckboxPlanSheetFrame() {
		extentTest.log(Status.INFO, "get first value from Building Frame checkboxes");
		planSheetCheckboxTextList.get(0).findElement(By.tagName("input")).click();
	}

	public void clickOnFirstCheckboxBuildingFrame() {
		extentTest.log(Status.INFO, "select first value from Building Frame checkboxes");
		buildingFrameCheckboxList.get(0).click();
	}

	public String getStructuralErrorStatus() {
		extentTest.log(Status.INFO, "Get Structural Error Status");
		return StructuralErrorStatuslbl.getText();

	}

	public boolean checkFirstCheckboxBuildingFrameIsDisplayed() {
		extentTest.log(Status.INFO, "verify first value from Building Frame checkboxes");
		return StructuralPropertiesCheckboxList.isEmpty();
	}

	public boolean checkspecDivision_SeeMore_Popup_SpecGroupsAreCollapsedIsDisplayed() {
		extentTest.log(Status.INFO, "verify specDivision_SeeMore_Popup_SpecGroups Are Collapsed");
		return specDivision_SeeMore_Popup_SpecGroupsAreCollapsed.isEmpty();
	}

	public void SelectValuesFromStructPropDropdown(String option, int firstDD, int secondDD, int firstindex,
			int secondindex) {
		extentTest.log(Status.INFO, "Select values from" + option + "dropdown");
		Select DDSelect0 = new Select(StrucPropDDList.get(firstDD));
		DDSelect0.selectByIndex(firstindex);
		Select DDSelect1 = new Select(StrucPropDDList.get(secondDD));
		DDSelect1.selectByIndex(DDSelect1.getOptions().size() - secondindex);
	}

	public void SelectCheckboxFromStructProp(int option) {
		extentTest.log(Status.INFO, "Select include all Projects checkbox ");
		StructuralPropertiesCheckboxList.get(option).click();
	}

	public void SelectValuesFromSquareAreaDD() {
		Select DDSelect0 = new Select(StrucPropDDList.get(0));
		DDSelect0.selectByIndex(2);
		Select DDSelect1 = new Select(StrucPropDDList.get(1));
		DDSelect1.selectByIndex(DDSelect1.getOptions().size() - 2);
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the Tracking List label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public HomePage clickOnHomeTab() {
		extentTest.log(Status.INFO, "Clicking on Home Tab");
		homeMenuLink.click();
		return new HomePage(driver);

	}

	public List<String> VerifyResultDropdownValues() {
		Select select = new Select(resultsDropdown.get(0));
		List<WebElement> options = select.getOptions();
		List<String> dropdownValues = new ArrayList<String>();
		for (WebElement we : options) {
			dropdownValues.add(we.getText());
		}
		return dropdownValues;
	}

	public String VerifySectionsSortDropdownValues() {
		return sectionssortDropdownSecondView.getText();

	}

	public List<String> VerifySortDropdownValues() {
		Select select = new Select(sortDropdown);
		List<WebElement> options = select.getOptions();
		List<String> dropdownValues = new ArrayList<String>();
		for (WebElement we : options) {
			dropdownValues.add(we.getText());
		}
		return dropdownValues;
	}

	public List<String> VerifyActionsDropdownValues() {
		actionsDropdown.click();
		SeleniumUtils.isVisible(actionDropdownAllOptions.get(0), driver);
		List<String> ActionsdownValues = new ArrayList<String>();
		for (WebElement we : actionDropdownAllOptions) {
			if (!(we.getText().equals("") || we.getText().equals(" "))) {
				ActionsdownValues.add(we.getText());
			}
		}
		return ActionsdownValues;
	}

	public String getResultDropdownSelectedValue() {
		Select select = new Select(resultsDropdown.get(0));
		return select.getFirstSelectedOption().getText().trim();
	}

	public Boolean areBothResultDropdownDisplayed() {
		try {
			return resultsDropdown.get(0).isDisplayed() && resultsDropdown.get(1).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean isSortDropdownDisplayed() {
		try {
			return sortDropdown.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean isActionsDropdownDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of Actions Dropdown");
		try {
			return actionsDropdown.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean areBothPaginationDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of both pagination");
		try {
			return pagination.get(0).isDisplayed() && resultsDropdown.get(1).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void SelectResultDropdownValue(String value) {
		Select select = new Select(resultsDropdown.get(0));
		SeleniumUtils.isVisible(resultsDropdown.get(0), driver);
		select.selectByVisibleText(value);
		waitforLoadingRing();
	}

	public ProjectResultsPage selectResultDropdownValueUpdatedResultPage(String value) {
		Select select = new Select(resultsDropdown.get(0));
		select.selectByVisibleText(value);
		waitforLoadingRing();
		return new ProjectResultsPage(driver);
	}

	public void IsFirstFourCurmbFiltersDisplayed() {
		Assert.assertTrue(iscrumbfilterUASLinkDisplayed());
		Assert.assertTrue(iscrumbSecondFilterDisplayed());
		Assert.assertTrue(iscrumbThirdFilterDisplayed());
		Assert.assertTrue(iscrumbForthFilterDisplayed());
	}

	public void IsFirstThreeCurmbFiltersDisplayed() {
		Assert.assertTrue(iscrumbfilterUASLinkDisplayed());
		Assert.assertTrue(iscrumbSecondFilterDisplayed());
		Assert.assertTrue(iscrumbThirdFilterDisplayed());

	}

	public void IsFirstTwoCurmbFiltersDisplayed() {
		Assert.assertTrue(iscrumbfilterUASLinkDisplayed());
		Assert.assertTrue(iscrumbSecondFilterDisplayed());
	}

	public void CheckFilterCrumbPopupFunctionality() {
		ClickOncrumbFirstGroupFilterLink();
		SwitchToActiveElement();
		Assert.assertTrue(isFilterPopupDisplayed());
		ClickOnFilterpopClose();
	}

	public Boolean checkforValidationMessage(String message) {
		return driver.getPageSource().contains(message);
	}

	public int getStateRegionFilterListSize() {
		return StateRegionFilterList.size();
	}

	public int getGEOGRAPHY_COUNTYFilterListSize() {
		return GEOGRAPHY_COUNTYFilterList.size();
	}

	public void RefreshPage() {
		CommonUtils.refreshPage(driver);
	}

	public void ClickOnCounties_LHS_SelectAll_Btn() {
		extentTest.log(Status.INFO, "Click on select all link");
		SeleniumUtils.isVisible(Counties_LHS_SelectAll_Btn, driver);
		Counties_LHS_SelectAll_Btn.click();
	}

	public void ClickOnStateRegionPopupSelectAllStates_checkbox() {
		SeleniumUtils.isVisible(StateRegionPopupSelectAllStates_checkbox, driver);
		SeleniumUtils.isClickable(StateRegionPopupSelectAllStates_checkbox, driver);
		StateRegionPopupSelectAllStates_checkbox.click();
	}

	// set the date range for created between filter.
	public boolean setCreatedBetweenRange(String fromDate, String toDate) {
		DatePickerUtils datePicker = new DatePickerUtils(driver);
		extentTest.log(Status.INFO, "set the date range for created between filter");

		Boolean isSelected = false;
		isSelected = datePicker.setDateSpecAlerts(createdBetween_FromDateIcon, fromDate);
		isSelected = datePicker.setDateSpecAlerts(createdBetween_ToDateIcon, toDate);
		return isSelected;
	}

	public void ClickOnGEOGRAPHY_AllCountiesOfAlabamaState_checkbox() {
		SeleniumUtils.isVisible(GEOGRAPHY_AllCountiesOfAlabamaState_checkbox, driver);
		SeleniumUtils.isClickable(GEOGRAPHY_AllCountiesOfAlabamaState_checkbox, driver);
		GEOGRAPHY_AllCountiesOfAlabamaState_checkbox.click();
	}

	public String getGEOGRAPHY_AllCountiesOfAlabamaState_checkbox_txt() {
		SeleniumUtils.isVisible(GEOGRAPHY_AllCountiesOfAlabamaState_checkbox, driver);
		return GEOGRAPHY_AllCountiesOfAlabamaState_checkbox.getText();
	}

	public void ClickOnactionStage_Code_SelectAllLink() {
		extentTest.log(Status.INFO, "Click on SelectAll link");
		SeleniumUtils.isVisible(actionStage_Code_SelectAllLink, driver);
		actionStage_Code_SelectAllLink.click();
	}

	public void ClickOnactionStage_Code_DeselectAll_Link() {
		extentTest.log(Status.INFO, "Click on DeSelectAll link");
		SeleniumUtils.isVisible(actionStage_Code_DeselectAll_Link, driver);
		actionStage_Code_DeselectAll_Link.click();
	}

	public void ClickOnPopupSelectAll_cbk() {
		extentTest.log(Status.INFO, "Click on SelectAll checkbox from popup");
		SeleniumUtils.isVisible(PopupSelectAll_cbk, driver);
		PopupSelectAll_cbk.click();
	}

	public void ClickProjectTypesSelectAll_Btn() {
		extentTest.log(Status.INFO, "Click on SelectAll checkbox");
		SeleniumUtils.isVisible(ProjectTypesSelectAll_Btn, driver);
		ProjectTypesSelectAll_Btn.click();
	}

	public void ClickSpecificTradesSelectAll_Btn() {
		SeleniumUtils.isVisible(SpecificTradesSelectAll_Btn, driver);
		SpecificTradesSelectAll_Btn.click();
	}

	public void clickOnUpdateInCreatedBetweenDateRange() {
		extentTest.log(Status.INFO, "Click on the update button for created between date range");
		updateBtnCreateBetween.click();
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);

	}

	public String getFromDate() {
		extentTest.log(Status.INFO, "Get the From Date from the created between date range");
		return createdBetween_FromDateIconTxt.getText().trim();
	}

	public String getToDate() {
		extentTest.log(Status.INFO, "Get the To Date from the created between date range");
		return createdBetween_ToDateIconTxt.getText();
	}

	public Boolean isHyperLinkPresent(String link) {
		extentTest.log(Status.INFO, "Is hyperlink" + link + "Present");
		return SeleniumUtils.verifyWebElementListIsPresent(driver.findElements(By.linkText(link)));
	}

	public String selectOneYearBackCreatedBetween() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, -1);
		return CommonUtils.getUSADateFormat(cal.getTime());
	}

	public String selectLastQuarterBackCreatedBetween() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		String date = CommonUtils.getUSADateFormat(cal.getTime());
		return date;
	}

	public String selectDayOFThisMonthCreatedBetween() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 5);

		return CommonUtils.getUSADateFormat(cal.getTime());
	}

	public String getCurrentDateFromTo() {
		String toDate = CommonUtils.getUSADateFormat(Calendar.getInstance().getTime());
		String fromDate = selectLastQuarterBackCreatedBetween();
		String dateRange = fromDate + " - " + toDate;
		return dateRange;
	}

	public String getTodayDateFromTo() {
		String date = CommonUtils.getUSADateFormat(Calendar.getInstance().getTime());
		String dateRange = date + " - " + date;
		return dateRange;
	}

	public boolean isMySearchesSpecAlertsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the specalerts displayed in the my searches dropdown");
		mySearchesDropDown.click();
		SeleniumUtils.isVisible(mySearchesSpecAlerts, driver);
		try {
			return mySearchesSpecAlerts.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isMySearchesTrackingListsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Tracking Lists displayed in the my searches dropdown");
		SeleniumUtils.isVisible(mySearchesSpecAlerts, driver);
		try {
			return mySearchesTrackingList.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isMySearchesSavedSearchesDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Saved Searches displayed in the my searches dropdown");
		SeleniumUtils.isVisible(mySearchesSpecAlerts, driver);
		try {
			return mySavedSearches.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void ClickOnMaterials_Equip2SelectAll_Link() {
		SeleniumUtils.isVisible(Materials_Equip2SelectAll_Link, driver);
		Materials_Equip2SelectAll_Link.click();
	}

	public String getCommonPopupFirstParentfilter_lbl() {
		extentTest.log(Status.INFO, "Get first parent filter label from popup");
		try {
			return PopupFirstParentfilter_lbl.getText();
		} catch (Exception ex) {
			return "";
		}
	}

	public void clickOnSeeMoreSpecAlertsIcon() {
		extentTest.log(Status.INFO, "Clicking on the 'See More' icon for SpecAlerts in the left pane");
		specAlerts_SeeMore_Popup_btn.click();
	}

	public void clickOnSeeMorePDSIcon() {
		extentTest.log(Status.INFO, "Clicking on the 'See More' icon for PDS in the left pane");
		CommonUtils.scrollDownTillElement(pds_SeeMore_Popup_btn, driver);
		pds_SeeMore_Popup_btn.click();
	}

	public void clickOnSeeMoreSpecAlertsOwnershipType() {
		ownerShipType_SeeMore_Popup_btn.click();
	}

	public void clickOnUpdateButtonSeeMoreSpecAlertPopup() {
		extentTest.log(Status.INFO, "Click on specalerts update button in the see more specalerts popup");
		seeMoreSpecAlertsUpdateBtn.click();
	}

	// Click On Multiple Option From The List
	public void ClickOnFewOptionsFromSpecAlertsPopUpList(int count, List<WebElement> list) throws InterruptedException {
		for (int i = 1; i < count; i++) {
			list.get(i).click();
		}
		Thread.sleep(2000);
	}

	public void clickOnFewOptions(int count) throws InterruptedException {
		extentTest.log(Status.INFO, "Clicking on few specalerts in the see more specalerts popup");
		ClickOnFewOptionsFromSpecAlertsPopUpList(count, specAlertSeeMorePopUpListChkBox);
	}

	public int getSpecAlertFewAppliedFilterCount() {
		extentTest.log(Status.INFO, "verify specalerts applied filter");
		return specAlertFewAppliedFilter.size();
	}

	public void ClickOnGEOGRAPHY_PopupSelectAllCounties_checkbox() {
		extentTest.log(Status.INFO, "Click on select all counties checkbox");
		SeleniumUtils.isVisible(GEOGRAPHY_PopupSelectAllCounties_checkbox, driver);
		SeleniumUtils.isClickable(GEOGRAPHY_PopupSelectAllCounties_checkbox, driver);
		GEOGRAPHY_PopupSelectAllCounties_checkbox.click();
	}

	public void clickSpecDivisionSpecDivFancyBtn() {
		extentTest.log(Status.INFO, "Click on the spec Division fancy Link");
		specDivisionSpecDivFancyBtn.click();
	}

	public boolean isSpecDivisionFilterPopupExpandAllDisplayed() {
		extentTest.log(Status.INFO, "Check all options are expanded");
		return CommonUtils.verifyListSize(specDivisionFilterPopup_IS_ExpandAll);
	}

	public boolean isSpecDivisionFilterPopupCollapseAllDisplayed() {
		extentTest.log(Status.INFO, "Check all options are collapsed ");
		return CommonUtils.verifyListSize(specDivisionFilterPopup_IS_CollapseAll);
	}

	public boolean IsgeographyFilterSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check is geographyFilterSeeMore_Btn displayed");
		return geographyFilterSeeMore_Btn.isDisplayed();
	}

	public int getCountOfProjectsOnPage() {
		extentTest.log(Status.INFO, "get the count of all the project displayed on Project Results page");
		return projectTitles.size();
	}

	public int getCountOfHideButtonsOnPage() {
		extentTest.log(Status.INFO, "get the count of all Hide buttons displayed on Project Results page");
		return hideButtonsOnPage.size();
	}

	public void clickHideLinkForFirstProject() {
		extentTest.log(Status.INFO, "click on the hide link for the first project on Project Results Page");
		hideButtonsOnPage.get(0).click();
	}

	public boolean isSpecDivisionPopupCollapseAllLinkDisplayed() {
		extentTest.log(Status.INFO, "Check is CollapseAll link displayed");
		return SeleniumUtils.isVisible(specDivisionPopup_CollapseAll_Link, driver);
	}

	public boolean isSpecDivisionPopupExpandAllLinkDisplayed() {
		extentTest.log(Status.INFO, "Check is ExpandAll link displayed");
		return SeleniumUtils.isVisible(specDivisionPopup_ExpandAll_Link, driver);
	}

	public String clickFirstSectionBarInRightChart() {
		extentTest.log(Status.INFO, "click on the first section in right chart on visualtions page");
		String selectedSection = textInRightChart.get(0).getText();
		CommonUtils.clickOnElementUsingJavascript(barsInRightChart.get(0), driver);
		waitforLoadingRing();
		return selectedSection;
	}

	public String clickFirstSectionBarInLeftChart() {
		extentTest.log(Status.INFO, "click on the first section in left chart on visualtions page");
		String selectedSection = textInLeftChart.get(0).getText();
		barsInLeftChart.get(0).click();
		waitforLoadingRing();
		return selectedSection;
	}

	public String clickFirstBarChart1Dashboard() {
		extentTest.log(Status.INFO, "click on the first bar in Chart1 - Dashboard.");
		SeleniumUtils.isVisible(allBarRectChart1Dashboard.get(0), driver);
		String selectedSection = allTextBarChart1Dashboard.get(0).getText();
		allBarRectChart1Dashboard.get(0).click();
		waitforLoadingRing();
		return selectedSection;
	}

	public String clickFirstManufacturerBarChart1Dashboard() {
		extentTest.log(Status.INFO, "click on first manufacturer bar in Chart1 - Dashboard.");
		SeleniumUtils.isVisible(allManufacturerBarRectChart1Dashboard.get(0), driver);
		String selectedSection = allTextBarChart1Dashboard.get(0).getText();
		CommonUtils.scrollDownTillElement(allManufacturerBarRectChart1Dashboard.get(0), driver);
		allManufacturerBarRectChart1Dashboard.get(0).click();
		waitforLoadingRing();
		return selectedSection;
	}

	public String clickFirstManufacturerBarChart2Dashboard() {
		extentTest.log(Status.INFO, "click on first manufacturer bar in Chart2 - Dashboard.");
		SeleniumUtils.isVisible(allManufacturerBarRectChart2Dashboard.get(0), driver);
		String selectedSection = allTextBarChart2Dashboard.get(0).getText();
		CommonUtils.scrollDownTillElement(allManufacturerBarRectChart2Dashboard.get(0), driver);
		allManufacturerBarRectChart2Dashboard.get(0).click();
		waitforLoadingRing();
		return selectedSection;
	}

	public List<String> clickOnMultipleManufacturerBarChart1Dashboard(final int count) {
		extentTest.log(Status.INFO, "click on multiple manufacturer bar in Chart1 - Dashboard.");
		List<String> barChartTextList = new ArrayList<>();
		String barTextList;
		if (count <= allManufacturerBarRectChart1Dashboard.size()) {
			for (int i = 0; i < count; i++) {
				waitforLoadingRing();
				CommonUtils.scrollDownTillElement(allTextBarChart1Dashboard.get(i), driver);
				barTextList = allTextBarChart1Dashboard.get(i).getText();
				if (barTextList.length() > 16) {
					barTextList = barTextList.substring(0, 15);
				}
				barChartTextList.add(barTextList);
				allManufacturerBarRectChart1Dashboard.get(i).click();
				waitforLoadingRing();
			}
		}
		return barChartTextList;
	}

	public List<String> clickOnMultipleManufacturerBarChart2Dashboard(final int count) {
		extentTest.log(Status.INFO, "click on multiple manufacturer bar in Chart2 - Dashboard.");
		List<String> barChartTextList = new ArrayList<>();
		if (count <= allManufacturerBarRectChart2Dashboard.size()) {
			for (int i = 0; i < count; i++) {
				CommonUtils.scrollDownTillElement(allTextBarChart2Dashboard.get(i), driver);
				barChartTextList.add(allTextBarChart2Dashboard.get(i).getText());
				allManufacturerBarRectChart2Dashboard.get(i).click();
				waitforLoadingRing();
			}
		}
		return barChartTextList;
	}

	public List<String> getAllFilterTextChart1Dashboard() {
		extentTest.log(Status.INFO, "Get all the filter texts in Chart1 - Dashboard.");
		return CommonUtils.getListFromWebElements(allTextBarChart1Dashboard);
	}

	public String clickFirstBarChart2Dashboard() {
		extentTest.log(Status.INFO, "click on the first bar in Chart2 - Dashboard.");
		String selectedSection = allTextBarChart2Dashboard.get(0).getText();
		allBarRectChart2Dashboard.get(0).click();
		waitforLoadingRing();
		return selectedSection;
	}

	public List<String> getAllFilterTextChart2Dashboard() {
		extentTest.log(Status.INFO, "Get all the filter texts in Chart2 - Dashboard.");
		return CommonUtils.getListFromWebElements(allTextBarChart2Dashboard);
	}

	public Boolean isFilterBarHorizontalOnChart1Dashboard(int idx) {
		extentTest.log(Status.INFO, "Check if the bars on the Chart1 is horizontal - Dashboard.");
		WebElement rectBar = allBarRectChart1Dashboard.get(idx);
		int width = rectBar.getRect().getWidth();
		int height = rectBar.getRect().getHeight();

		if (width > height)
			return true;
		else
			return false;
	}

	public Boolean isFilterBarHorizontalOnChart2Dashboard(int idx) {
		extentTest.log(Status.INFO, "Check if the bars on the Chart2 is horizontal - Dashboard.");
		WebElement rectBar = allBarRectChart2Dashboard.get(idx);
		int width = rectBar.getRect().getWidth();
		int height = rectBar.getRect().getHeight();

		if (width > height)
			return true;
		else
			return false;
	}

	public Boolean areAllFilterBarsHorizontalOnChart1Dashboard() {
		extentTest.log(Status.INFO, "Check if all the bars on the Chart1 are horizontal - Dashboard.");
		boolean areAllBarsHorizontal = true;
		for (WebElement rectBar : allBarRectChart1Dashboard) {
			int width = rectBar.getSize().getWidth();
			int height = rectBar.getSize().getHeight();
			if (width <= height) {
				areAllBarsHorizontal = false;
				break;
			}
		}
		return areAllBarsHorizontal;
	}

	public Boolean areAllFilterBarsHorizontalOnChart2Dashboard() {
		extentTest.log(Status.INFO, "Check if all the bars on the Chart2 are horizontal - Dashboard.");
		boolean areAllBarsHorizontal = true;
		for (WebElement rectBar : allBarRectChart2Dashboard) {
			int width = rectBar.getRect().getWidth();
			int height = rectBar.getRect().getHeight();
			if (width <= height) {
				areAllBarsHorizontal = false;
				break;
			}
		}
		return areAllBarsHorizontal;
	}

	public boolean verifySizeOfEachBarDependingOnFacetCountChart1Dashboard2() {
		List<Double> rectBarWidthList = new ArrayList<Double>();
		List<Integer> rectBarFacetCountList = new ArrayList<Integer>();
		for (WebElement rectBar : rectBarChart1Dashboard2) {
			String[] styleValueArr = rectBar.getAttribute("style").split(";");
			for (String styleVal : styleValueArr) {
				if (styleVal.contains("width")) {
					String[] styleWidthSplit = styleVal.split(":");
					Double width = Double.parseDouble(styleWidthSplit[1].trim().replace("%", ""));
					rectBarWidthList.add(width);
					break;
				}
			}
		}

		for (String facetCount : getAllFilterFacetCountChart1Dashboard2()) {
			rectBarFacetCountList.add(Integer.parseInt(facetCount.trim().replace(",", "")));
		}

		List<Integer> reverseSortedRectBarFacetCountList = CommonUtils
				.sortIntegerListAndGetSortedList(rectBarFacetCountList, false);
		Boolean barWidthSorted = CommonUtils.compareTwoIntegerList(rectBarFacetCountList,
				reverseSortedRectBarFacetCountList);

		List<Double> reverseSortedRectBarWidthList = CommonUtils.sortDoubleListAndGetSortedList(rectBarWidthList,
				false);
		Boolean facetCountSorted = CommonUtils.compareTwoDoubleList(rectBarWidthList, reverseSortedRectBarWidthList);

		if (barWidthSorted && facetCountSorted) {
			return true;
		}

		return false;
	}

	public boolean verifyFacetCountDisplayedOnRightOfBarChart1Dashboard() {
		boolean isFacetCountOnRightOfBar = true;
		if (allBarRectChart1Dashboard.size() == projectTypeCountList.size()) {
			for (int elementCntr = 0; elementCntr < allBarRectChart1Dashboard.size(); elementCntr++) {
				WebElement rectBarElement = allBarRectChart1Dashboard.get(elementCntr);
				WebElement facetCountElement = projectTypeCountList.get(elementCntr);
				int xAxisBarTopRight = rectBarElement.getLocation().getX() + rectBarElement.getSize().getWidth();
				int xAxisFacetTopLeft = facetCountElement.getLocation().getX();
				if (xAxisBarTopRight > xAxisFacetTopLeft) {
					isFacetCountOnRightOfBar = false;
					break;
				}
			}
		}

		return isFacetCountOnRightOfBar;
	}

	public int getCountOfSectionBarInRightChart() {
		extentTest.log(Status.INFO, "get count of  section bar in right chart on visualtions page");
		return barsInRightChart.size();

	}

	public String clickFirstDivisionBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "click on the first Division bar in Section visualtions page");
		String selectedDivision = divisionTypeFilterList.get(0).getText();
		divisionTypeFilterList.get(0).click();
		waitforLoadingRing();
		return selectedDivision;
	}

	public String clickSecondDivisionBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "click on the second division bar in Section visualtions page");
		String selectedDivision = divisionTypeFilterList.get(1).getText();
		divisionTypeFilterList.get(1).click();
		waitforLoadingRing();
		return selectedDivision;
	}

	public String getClassOfFirstSectionBarInRightChart() {
		extentTest.log(Status.INFO, "Get the class of first section bar on right chart on visualization page");
		return barsInRightChart.get(0).getAttribute("class");

	}

	public String getClassOfSecondProjectTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of second ProjectType bar on section visualization page");
		return projectTypeFilterList.get(1).getAttribute("class");

	}

	public String getFirstProjectTypeCount() {
		extentTest.log(Status.INFO, "Get the first ProjectType count on section visualization page");
		return projectTypeCountList.get(0).getText();

	}

	public List<String> getAllFilterFacetCountChart1Dashboard2() {
		extentTest.log(Status.INFO, "Get all the facet count of filters on Chart1 Dashboard2");
		return CommonUtils.getListFromWebElements(projectTypeCountList);
	}

	public String getClassOfThirdProjectTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of third ProjectType bar on section visualization page");
		return projectTypeFilterList.get(2).getAttribute("class");

	}

	public String getClassOfFirstProjectTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of first ProjectType bar on section visualization page");
		return projectTypeFilterList.get(0).getAttribute("class");

	}

	public String getClassOfFirstActionStageTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of first actionstage bar on section visualization page");
		return actionStageTypeFilterList.get(0).getAttribute("class");

	}

	public String getClassOfsecondActionStageTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of second action stage Type bar on section visualization page");
		return actionStageTypeFilterList.get(1).getAttribute("class");

	}

	public String getClassOfFirstDivisionTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of first Division Type bar on section visualization page");
		return divisionTypeFilterList.get(0).getAttribute("class");

	}

	public String getClassOfSecondDivisionTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of second Division Type bar on section visualization page");
		return divisionTypeFilterList.get(1).getAttribute("class");

	}

	public String getClassOfThirsDivisionTypeBarInSectionVisualizations() {
		extentTest.log(Status.INFO, "Get the class of third Division Type bar on section visualization page");
		return divisionTypeFilterList.get(2).getAttribute("class");

	}

	public String getClassOfThirdSectionBarInRightChart() {
		extentTest.log(Status.INFO, "Get the class of third section bar on right chart on visualization page");
		return barsInRightChart.get(2).getAttribute("class");

	}

	public String clickSecondSectionBarInRightChart() {
		extentTest.log(Status.INFO, "click on the second section in right chart on visualtions page");
		String selectedSection = textInRightChart.get(1).getText();
		CommonUtils.clickOnElementUsingJavascript(barsInRightChart.get(1), driver);
		waitforLoadingRing();
		return selectedSection;
	}

	public String clickThirdSectionBarInRightChart() {
		extentTest.log(Status.INFO, "click on the third section in right chart on visualtions page");
		String selectedSection = textInRightChart.get(2).getText();
		CommonUtils.clickOnElementUsingJavascript(barsInRightChart.get(2), driver);
		waitforLoadingRing();
		return selectedSection;
	}

	public String clickFourthSectionBarInRightChart() {
		extentTest.log(Status.INFO, "click on the fourth section in right chart on visualtions page");
		String selectedSection = textInRightChart.get(3).getText();
		CommonUtils.clickOnElementUsingJavascript(barsInRightChart.get(3), driver);
		waitforLoadingRing();
		return selectedSection;
	}

	public String getNamesOfAllAppliedFilters() {
		extentTest.log(Status.INFO, "get the list of all applied filters on the page");
		return appliedFilterContainer.getText();
	}

	public String getGroupedSectionFilterName() {
		extentTest.log(Status.INFO, "Get grouped Section Filter name");
		return groupedSectionFilter.getText();
	}

	public String clickFirststateInMapInSectionVisualizations() {
		extentTest.log(Status.INFO, "click on the first state in Map in Section visualtions page");
		firstStateInMap.click();
		return firstStateInMap.getText();
	}

	public void clickAlertOffLink() {
		extentTest.log(Status.INFO, "click on alertOff link for project");
		alertoff.click();
		SeleniumUtils.isClickable(searchBtn, driver);

	}

	public void clickAlertOnLink() {
		extentTest.log(Status.INFO, "click on alertOn link for project");
		alerton.click();

	}

	public Boolean isalertOnIconDisplayed() {
		extentTest.log(Status.INFO, "check if alert on icon is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(alertIndicatorIcon));
		if (alertIndicatorIcon.getAttribute("src").contains("Images/star-alert-icon.PNG"))
			return true;
		else
			return false;

	}

	public Boolean isTrackIconDisplayed() {
		extentTest.log(Status.INFO, "check if track icon is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(alertIndicatorIcon));
		if (alertIndicatorIcon.getAttribute("src").contains("Images/star-icon.PNG"))
			return true;
		else
			return false;

	}

	public boolean IsGEOGRAPHY_STATESeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check is GEOGRAPHY_STATESeeMore_Btn displayed");
		return GEOGRAPHY_STATESeeMore_Btn.isDisplayed();
	}

	public String getStateRegion_LHSFirstFilter_lbl() {
		CommonUtils.scrollDownTillElement(StateRegion_LHSFirstFilter_lbl, driver);
		return StateRegion_LHSFirstFilter_lbl.getText();
	}

	public String getStateRegion_PopupFirstFilter_Alabama_lbl() {
		return StateRegion_PopupFirstFilter_Alabama_lbl.getText();
	}

	public String getStateRegion_PopupFirstFilter_California_lbl() {
		return StateRegion_PopupFirstFilter_California_lbl.getText();
	}

	public void ClickOnCommonPopupHideZeroProjects_cbk() {
		extentTest.log(Status.INFO, "Click on Hide selections with zero Projects checkbox");
		CommonPopupHideZeroProjects_cbk.click();
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
	}

	public void ClickOnBarChartToggleButton() {
		extentTest.log(Status.INFO, "Clicking on bar chart toggle button");
		barchartViewToggleBtn.click();

	}

	public boolean IsFilterCrumbPopup_Searchbox_Displayed() {
		extentTest.log(Status.INFO, "Check is FilterCrumbPopup_Searchbox displayed");
		return crumbFilterPopupSearchBox.isDisplayed();
	}

	public int getCrumbFilterPopupCloseBtnListSize() {
		extentTest.log(Status.INFO, "Get FilterCrumbPopup_AllClose_btn_Size ");
		return crumbFilterPopupCloseBtnList.size();
	}

	public boolean IsStateRegin_FirstOption_Selected() {
		extentTest.log(Status.INFO, "Check StateRegin_FirstOption is selected");
		return StateRegin_FirstOption.isSelected();
	}

	public boolean IsspecDivision_SeeMore_Popup_btn_Displayed() {
		extentTest.log(Status.INFO, "Check is specDivision_SeeMore_Popup_btn displayed");
		return specDivision_SeeMore_Popup_btn.isDisplayed();
	}

	public String getspecDivisionFilterPopup_LastCheckbox_lbl() {
		extentTest.log(Status.INFO, "Get specDivisionFilterPopup_LastCheckbox_lbl label");
		return specDivisionFilterPopup_LastCheckbox_lbl.getText();
	}

	public String getspecDivisionFilterLHS_FirstCheckbox_lbl() {
		extentTest.log(Status.INFO, "Get specDivisionFilterLHS_FirstCheckbox_lbl label");
		return specDivisionFilterLHS_FirstCheckbox_lbl.getText();
	}

	public void ClickOnspecDivisionPopup_HideShowCheckbox() {
		extentTest.log(Status.INFO, "Click on specDivisionPopup_HideShowCheckbox checkbox");
		specDivisionPopup_HideShowCheckbox.click();
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
	}

	public ProjectPage clickOnViewProjectsFromActions() {
		extentTest.log(Status.INFO, "Clicking on View Projects under Actions Dropdown in Project Results Page");
		viewProjectsActionsOptions.click();
		return new ProjectPage(driver);
	}

	public boolean isExcludeCheckboxUnChecked() {
		extentTest.log(Status.INFO, "Verify if Exclude checkbox is checked on tracking list see more view");
		return exclude_SeeMore_chkbox.isSelected();
	}

	public boolean isExcludeCheckboxPresent() {
		extentTest.log(Status.INFO, "Verify if Exclude checkbox is present");
		return CommonUtils.checkElementExist(exclude_SeeMore_chkbox, driver);
	}

	public void clickExcludeCheckbox_SeeMore() {
		extentTest.log(Status.INFO, "click Exclude checkbox on see more dialog");
		exclude_SeeMore_chkbox.click();
	}

	public void clickExcludeCheckbox_SeeMore_Specalert() {
		extentTest.log(Status.INFO, "click Exclude checkbox on see more dialog");
		exclude_SeeMore_Specalert_chkbox.click();
	}

	public void isExcludeCheckbox_SeeMore_Specalert_Checked() {
		extentTest.log(Status.INFO, "check if Exclude checkbox on see more LHS is checked");
		exclude_SeeMore_Specalert_chkbox.click();
	}

	public void selectSortingOption(String sortType) {
		extentTest.log(Status.INFO, "Select sort option" + sortType);
		try {
			Select select = new Select(sortDropdown);
			select.selectByVisibleText(sortType);
		} catch (Exception ex) {

		}
	}
	
	public void selectOptionFromLeftSortDropdownManufacturers(String sortOption) {
		extentTest.log(Status.INFO, "Select option " + sortOption + " from left sort dropdown manufacturer.");
		SeleniumUtils.isVisible(leftSortDropdownManufacturers, driver);
		CommonUtils.scrollDownTillElement(leftSortDropdownManufacturers, driver);
		leftSortDropdownManufacturers.click();
		for (WebElement option : leftSortDropdownOptionsManufacturers) {
			if (sortOption.equalsIgnoreCase(option.getText())) {
				option.click();
				waitforLoadingRing();
				break;
			}
		}
	}
	
	/**
	 * This method will select the provided drop down option from sort drop down lits.
	 * 
	 * @param sortOption - Option to be selected from drop down list.
	 */
	public void selectOptionFromRightSortDropdownManufacturers(String sortOption) {
		extentTest.log(Status.INFO, "Select option " + sortOption + " from Right sort dropdown manufacturer.");
		SeleniumUtils.isVisible(rightSortDropdownManufacturers, driver);
		rightSortDropdownManufacturers.click();
		for(int i=0;i<rightSortDropdownOptionsManufacturers.size();i++){
			if(rightSortDropdownOptionsManufacturers.get(i).getText().equalsIgnoreCase(sortOption)){
				rightSortDropdownOptionsManufacturers.get(i).click();
				break;
			}
		}
	}

	/***
	 * Check the filter text name is displayed at left in visual left chart
	 * 
	 * @return : Return boolean result
	 */
	public boolean checkDisplayeNameAtLeftChart1Dashboard() {
		extentTest.log(Status.INFO, "Check the filter text name is displayed at left in visual left chart.");
		return allTextBarChart1Dashboard.get(0).getText().length() > 0;
	}

	/***
	 * Check the filter text name is displayed at left in visual right chart
	 * 
	 * @return : Return boolean result
	 */
	public boolean checkDisplayeNameAtLeftChart2Dashboard() {
		extentTest.log(Status.INFO, "Check the filter text name is displayed at left in visual right chart.");
		return allTextBarChart2Dashboard.get(0).getText().length() > 0;
	}

	/***
	 * Check Displays up-to 100 filter on the left VIZ chart
	 * 
	 * @return : return boolean result
	 */
	public boolean verifyTopHundredFilterPresentChart1Dashboard() {
		extentTest.log(Status.INFO, "Check Displays up-to 100 filter on the left VIZ chart .");
		List<String> valueList = new ArrayList<>();
		for (WebElement webElement : allTextBarChart1Dashboard) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(webElement.getText());
		}
		return valueList.size() == 100;
	}

	/***
	 * Check Displays up-to 100 filter on the right VIZ chart.
	 * 
	 * @return : return boolean result
	 */
	public boolean verifyTopHundredFilterPresentChart2Dashboard() {
		extentTest.log(Status.INFO, "Check Displays up-to 100 filter on the right VIZ chart.");
		List<String> valueList = new ArrayList<>();
		for (WebElement webElement : allTextBarChart2Dashboard) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(webElement.getText());
		}
		return valueList.size() == 100;
	}

	/***
	 * Get all the bar text from left visual chart
	 * 
	 * @return : REturn the list of string
	 */
	public List<String> getAllBarTextChart1Dashboard() {
		extentTest.log(Status.INFO, "Get list of left bar text from visual left chart.");
		List<String> valueList = new ArrayList<>();
		for (WebElement webElement : allTextBarChart1Dashboard) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(webElement.getText());
		}
		return valueList;
	}

	public String getFirstBarTextChart1Dashboard() {
		extentTest.log(Status.INFO, "Get first left bar text from visual left chart.");
		return allTextBarChart1Dashboard.get(0).getText().trim();
	}

	public String getFirstBarTextChart2Dashboard() {
		extentTest.log(Status.INFO, "Get first right bar text from visual right chart.");
		return allTextBarChart2Dashboard.get(0).getText().trim();
	}

	/***
	 * Check if first left bar text is displayed from visual left chart.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isFirstBarTextChart1Dashboard() {
		extentTest.log(Status.INFO, "Check if first left bar text is displayed from visual left chart.");
		return CommonUtils.checkElementExist(allTextBarChart1Dashboard.get(0), driver);
	}

	/***
	 * Check if first right bar text is displayed from visual left chart.
	 * 
	 * @return : Get the boolean result
	 */
	public boolean isFirstBarTextChart2Dashboard() {
		extentTest.log(Status.INFO, "Check if first left bar text is displayed from visual right chart.");
		return CommonUtils.checkElementExist(allTextBarChart2Dashboard.get(0), driver);
	}

	/***
	 * Get all the bar text from left visual chart
	 * 
	 * @return : REturn the list of string
	 */
	public List<String> getAllBarTextChart2Dashboard() {
		extentTest.log(Status.INFO, "Get list of right bar text from visual Right chart.");
		List<String> valueList = new ArrayList<>();
		for (WebElement webElement : allTextBarChart2Dashboard) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(webElement.getText());
		}
		return valueList;
	}

	/***
	 * Check Display ellipses if more than two lines or exceed character count
	 * limit
	 * 
	 * @return : Return boolean result.
	 */
	public boolean checkDisplayEllipsesOnExceedCharCountChart1Dashboard() {
		extentTest.log(Status.INFO, "Check Display ellipses if more than two lines or exceed character count limit.");
		boolean charCountFlag = true;
		for (String charText : getAllBarTextChart1Dashboard()) {
			if (charText.length() > 32) {
				charCountFlag = charText.contains("...");
			}
			if (!charCountFlag)
				break;
		}
		return charCountFlag;
	}

	/***
	 * Check Display ellipses if more than two lines or exceed character count
	 * limit in right chart
	 * 
	 * @return : Return boolean result.
	 */
	public boolean checkDisplayEllipsesOnExceedCharCountChart2Dashboard() {
		extentTest.log(Status.INFO,
				"Check Display ellipses if more than two lines or exceed character count limit in right chart.");
		boolean charCountFlag = true;
		for (String charText : getAllBarTextChart1Dashboard()) {
			if (charText.length() > 16) {
				charCountFlag = charText.contains("...");
			}
			if (!charCountFlag)
				break;
		}
		return charCountFlag;
	}

	public List<String> getLeftChartBarTextValueList() {
		extentTest.log(Status.INFO, "Get Left chart Bars text Value List.");
		List<String> valueList = new ArrayList<>();
		for (WebElement webElement : leftChartBarTextList) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add((webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}

	public List<String> getRightChartBarTextValueList() {
		extentTest.log(Status.INFO, "Get Right chart Bars text Value List.");
		List<String> valueList = new ArrayList<>();
		for (WebElement webElement : rightChartBarTextList) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add((webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}

	public List<Integer> getMFRVizLeftBasicOfDesignFilterBarIntegerValueList() {
		extentTest.log(Status.INFO, "Get MFR viz left Basic Of Design Filter Bar integer Value List.");
		List<Integer> valueList = new ArrayList<>();
		for (WebElement webElement : mfrVizLeftBasicOfDesignFilterBarValueList) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(Integer.parseInt(webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}

	public List<Integer> getMFRVizRightBasicOfDesignFilterBarIntegerValueList() {
		extentTest.log(Status.INFO, "Get MFR viz right Basic Of Design Filter Bar integer Value List.");
		List<Integer> valueList = new ArrayList<>();
		for (WebElement webElement : mfrVizRightBasicOfDesignFilterBarValueList) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(Integer.parseInt(webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}

	public List<Integer> getMFRVizLeftSpecifiedFilterBarIntegerValueList() {
		extentTest.log(Status.INFO, "Get MFR viz Specified Filter Bar integer Value List.");
		List<Integer> valueList = new ArrayList<>();
		for (WebElement webElement : mfrVizLeftSpecifiedFilterBarValueList) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(Integer.parseInt(webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}

	public List<Integer> getMFRVizRightSpecifiedFilterBarIntegerValueList() {
		extentTest.log(Status.INFO, "Get MFR viz right Specified Filter Bar integer Value List.");
		List<Integer> valueList = new ArrayList<>();
		for (WebElement webElement : mfrVizRightSpecifiedFilterBarValueList) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(Integer.parseInt(webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}

	public String getSelectedSortOption() {
		extentTest.log(Status.INFO, "return the selected sort option on project results page");
		Select select = new Select(sortDropdown);
		return select.getFirstSelectedOption().getText();
	}

	public String getRightSelectedSortOptionForManufacturers() {
		extentTest.log(Status.INFO, "Get the selected sort option from Manufacturers right chart view.");
		SeleniumUtils.isVisible(rightSortDropdownManufacturers, driver);
		CommonUtils.scrollDownTillElement(rightSortDropdownManufacturers, driver);
		return rightSortDropdownManufacturers.findElement(By.id("countText")).getText().trim();
	}

	public String getLeftSelectedSortOptionForManufacturers() {
		extentTest.log(Status.INFO, "Get the selected sort option from Manufacturers left chart view.");
		SeleniumUtils.isVisible(leftSortDropdownManufacturers, driver);
		CommonUtils.scrollDownTillElement(leftSortDropdownManufacturers, driver);
		return leftSortDropdownManufacturers.findElement(By.id("countText")).getText().trim();
	}

	public List<String> getRightSortDropdownValueListManufacturers() {
		SeleniumUtils.isVisible(rightSortDropdownManufacturers, driver);
		CommonUtils.scrollDownTillElement(rightSortDropdownManufacturers, driver);
		rightSortDropdownManufacturers.click();
		SeleniumUtils.isVisible(rightSortDropdownOptionsManufacturers.get(0), driver);
		return CommonUtils.getListFromWebElements(rightSortDropdownOptionsManufacturers);
	}

	public List<String> getLeftManufacturersSortDropdownValueList() {
		SeleniumUtils.isVisible(leftSortDropdownManufacturers, driver);
		CommonUtils.scrollDownTillElement(leftSortDropdownManufacturers, driver);
		leftSortDropdownManufacturers.click();
		SeleniumUtils.isVisible(leftSortDropdownOptionsManufacturers.get(0), driver);
		return CommonUtils.getListFromWebElements(leftSortDropdownOptionsManufacturers);
	}
	
	public List<String> getRightManufacturersSortDropdownValueList() {
		SeleniumUtils.isVisible(rightSortDropdownManufacturers, driver);
		CommonUtils.scrollDownTillElement(rightSortDropdownManufacturers, driver);
		rightSortDropdownManufacturers.click();
		SeleniumUtils.isVisible(rightSortDropdownOptionsManufacturers.get(0), driver);
		return CommonUtils.getListFromWebElements(rightSortDropdownOptionsManufacturers);
	}

	public boolean IsACTION_STAGE_CATEGORYSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see ACTION_STAGE_CATEGORYSeeMore_Btn displayed");
		return ACTION_STAGE_CATEGORYSeeMore_Btn.isDisplayed();
	}

	public List<String> getSpecAlertsLeftProgramsFomilterList() {
		extentTest.log(Status.INFO, "Get the list of SpecAlerts Program Filter On Left");
		return CommonUtils.getListFromWebElements(specAlertsLeftProgramsFilter);
	}

	public boolean IsactionStageCatFacet_1_Ckbox_Selected() {
		extentTest.log(Status.INFO, "Check actionStageCatFacet_1_Ckbox is selected");
		return actionStageCatFacet_1_Ckbox.isSelected();
	}

	public List<String> getSelectedActionStageTypes() {
		extentTest.log(Status.INFO, "Get all selected checkboxes in Action Stage Type.");
		List<String> selectedCheckboxes = new ArrayList<>();
		for (int chkBoxCntr = 0; chkBoxCntr < allActionStageCkboxes.size(); chkBoxCntr++) {
			if (allActionStageCkboxes.get(chkBoxCntr).isSelected()) {
				selectedCheckboxes.add(ACTION_STAGE_CATEGORYLHS_LabelList.get(chkBoxCntr).getText());
			}
		}

		return selectedCheckboxes;
	}

	public List<String> getSpecAlertsSeeMorePopUpProgramsList() {
		extentTest.log(Status.INFO, "Get the list of SPecAlerts Program from the 'see more' specalerts popup");
		return CommonUtils.getListFromWebElements(specAlertsSeeMorePopUpProgramsList);
	}

	public String getActionStage_LHSFirstParentElement_lbl() {
		extentTest.log(Status.INFO, "Get ActionStage_LHSFirstParentElement_lbl label");
		return ActionStage_LHSFirstParentElement_lbl.getText();
	}

	public void clickOnProjectGrpEducationPrimary() {
		extentTest.log(Status.INFO, "Click on the project group popup for Education - Primary School");
		ProjGrpPopupEducationPrimaryChk.click();
	}

	public boolean IsProjectTypesSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see ProjectTypesSeeMore_Btn displayed");
		return ProjectTypesSeeMore_Btn.isDisplayed();
	}

	public String getProjTypePopupCommercialParents_FirstOption_lbl() {
		extentTest.log(Status.INFO, "Get ProjTypePopupCommercialParents_FirstOption_lbl label");
		return ProjTypePopupCommercialParents_FirstOption_lbl.getText();
	}

	public String getProjTypeLHS_FirstOption_lbl() {
		extentTest.log(Status.INFO, "Get ProjTypeLHS_FirstOption_lbl label");
		return ProjTypeLHS_FirstOption_lbl.getText();
	}

	public boolean IsProjTypeLHS_FirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check ProjTypeLHS_FirstOption_cbk is selected");
		return ProjTypeLHS_FirstOption_cbk.isSelected();
	}

	public boolean verifyPublishDateSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(publishDate);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public void clickOnSpecAlertFilterCarpetMohawk() {
		extentTest.log(Status.INFO, "Click on the SpecAlerts Filter for Carpet - Mohawk");
		carpetMohawkSpecAlertchkbox.click();
	}

	public String ClickSpecAlertFilterCount() {
		extentTest.log(Status.INFO, "Click on the SpecAlerts Filter for Carpet - Mohawk");
		String specAlertFilterCountText = "";
		for (int i = 0; i < specAlertFilterCount.size(); i++) {
			if (Integer.parseInt(CommonUtils.formatIntoAlphanumeric(specAlertFilterCount.get(i).getText())) > 50) {
				specAlertFilterCountText = specAlertFilterCountLabel.getText();
				specAlertFilterCount.get(i).click();
				waitforLoadingRing();
				break;
			}
		}
		return specAlertFilterCountText;

	}

	public String mouseOverSpecALertsListAndGetTooltip() {
		Actions action = new Actions(driver);
		action.clickAndHold(specAlertsInProjectSummaryList.get(0)).build().perform();
		String toolTip = "";
		extentTest.log(Status.INFO, "Mouse Over the specalerts and get the tooltip");
		if (specAlertsInProjectSummaryList.size() > 0) {
			toolTip = specAlertsInProjectSummaryList.get(0).getAttribute("original-title");
		} else
			throw new CoreFrameworkException("Specalerts programs list is empty");
		return toolTip;
	}

	public ProjectSpecsPage clickOnFirstFromSpecAlertsListInSummary() {
		extentTest.log(Status.INFO, "Clicking on first SpecAlerts from the SpecAlerts List");
		if (specAlertsInProjectSummaryList.size() > 0) {
			specAlertsInProjectSummaryList.get(0).click();
		} else
			throw new CoreFrameworkException("Specalerts programs list is empty");
		return new ProjectSpecsPage(driver);
	}

	public int getSpecAlertSize() {
		extentTest.log(Status.INFO, "Get the count of the specalerts in the project summary list");
		return specAlertsInProjectSummaryList.size();
	}

	public ProjectCommonContainerPage clickFirstSpecAlertsInFirstProject() {
		extentTest.log(Status.INFO, "Click on the first program in the first project");
		if (specAlertsInProjectSummaryList.size() > 0) {
			specAlertsInProjectSummaryList.get(0).click();
		} else
			throw new CoreFrameworkException("Specalerts programs list is empty");
		return new ProjectCommonContainerPage(driver);
	}

	public boolean verifySpecialFilterOptions() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down");
		boolean isMatch = false;
		int i = 0;
		for (DGNEnum.SpecialFiltersOptions opt : DGNEnum.SpecialFiltersOptions.values()) {
			if (specialFilterOptionsList.get(i).getText().equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	// Swiching to Search frame
	public String IframeswitchSpecAlert = "fancybox-frame";

	public void switchFrameFancyBox() {
		extentTest.log(Status.INFO, "SwitchFrameFancyBox-Spec Alerts");

		driver.switchTo().frame(IframeswitchSpecAlert);

	}

	public void clickOnSpecialFilterOptions() {
		extentTest.log(Status.INFO, "Clicking on Special Filter Options");
		specialFilterOptionsList.get(0).click();
		waitforLoadingRing();
	}

	public boolean verifyValuationSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(valuation);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean isDonutChartDisplayed() {
		extentTest.log(Status.INFO, "Verify if Donut Chart displayed");
		return donutChart.isDisplayed();

	}

	public boolean verifyBidDateSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(bidDate);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyLocationSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(location);
		int beginIndex = listElements.lastIndexOf(" ") + 1;
		int endIndex = listElements.size() - 1;
		listElements.subList(beginIndex, endIndex);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyLocationSortingDescending() {
		List<String> listElements = CommonUtils.getListFromWebElements(location);
		int beginIndex = listElements.lastIndexOf(" ") + 1;
		int endIndex = listElements.size() - 1;
		listElements.subList(beginIndex, endIndex);
		List<String> listArc = CommonUtils.sortWebElements(listElements, false);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyProjectResultsSortingDesccending() {
		List<String> listElements = CommonUtils.getListFromWebElements(projectTitles);
		List<String> listArc = CommonUtils.sortWebElements(listElements, false);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyProjectResultsSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(projectTitles);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean IsspecAlerts_SeeMore_Popup_btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see specAlerts_SeeMore_Popup_btn displayed");
		return specAlerts_SeeMore_Popup_btn.isDisplayed();
	}

	public String getspecAlertSeeMorePopUpFirstOption_lbl() {
		extentTest.log(Status.INFO, "Get specAlertSeeMorePopUpFirstOption_lbl label");
		return specAlertSeeMorePopUpFirstOption_lbl.getText();
	}

	public String getspecAlertLHSFirstOption_lbl() {
		extentTest.log(Status.INFO, "Get specAlertLHSFirstOption_lbl label");
		return specAlertLHSFirstOption_lbl.getText();
	}

	public void ClickOnFilterCrumb_AllClose_btn() {
		extentTest.log(Status.INFO,
				"Check to see The selection should go away when X associated with the filter is clicked");
		for (int i = 0; i <= FilterCrumb_AllClose_btn.size(); i++) {
			FilterCrumb_AllClose_btn.get(1).click();
			SeleniumUtils.isVisible(rotateLoadingIcon, driver);
			SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisibleCheck, driver);
		}
	}

	public void clickOnFilterCrumbAllCloseButton() {
		extentTest.log(Status.INFO,
				"Check to see The selection should go away when X associated with the filter is clicked");
		for (int i = 0; i < filterCrumbAllCloseButton.size(); i++) {
			filterCrumbAllCloseButton.get(i).click();
			waitforLoadingRing();
		}
	}

	public int getSizeOfFilterCrumb_AllClose_btn() {
		return FilterCrumb_AllClose_btn.size();
	}

	public int getSizeOfFilterCrumbAllCloseButton() {
		return filterCrumbAllCloseButton.size();
	}

	public boolean getAppliedFilterText(String Expected) {
		extentTest.log(Status.INFO,
				"Check to to see the filter name and the number of selections displayed on the top followed by a hyperlink named as Filters");
		return CommonUtils.checkExpectedStringContainInCompleteList(Expected, FilterCrumb_AppliedFilter_List);
	}

	public boolean isCrumbSecondFilterLinkDisplayed() {
		extentTest.log(Status.INFO, "Check to see second crumb filter link displayed");
		return crumbFilterLinks.get(0).isDisplayed();
	}

	public List<String> getCrumbGroupFilterLinkList() {
		extentTest.log(Status.INFO, "Get crumb filter link list.");
		return CommonUtils.getListFromWebElements(crumbGroupFilterLinksType);
	}

	public void clickOnCrumbGroupFilterLink(final String linkText) {
		extentTest.log(Status.INFO, "Click on crumb group filter link.");
		for (WebElement filterLink : crumbGroupFilterLinksType) {
			if (filterLink.getText().contains(linkText)) {
				filterLink.findElement(By.xpath("following-sibling::a")).click();
				break;
			}
		}
		waitforLoadingRing();
	}

	public void removeCrumbGroupFilter(final String linkText) {
		extentTest.log(Status.INFO, "Remove crumb group filter.");
		WebElement removeLink = null;
		SeleniumUtils.isVisible(crumbGroupFilterLinksType.get(0), driver);
		for (WebElement filterLink : crumbGroupFilterLinksType) {
			if (filterLink.getText().contains(linkText)) {
				removeLink = filterLink.findElement(By.xpath(
						".//ancestor::span[contains(@class,'anchorFilter')]//following-sibling::a[contains(@class,'removeIcon')]"));
				CommonUtils.scrollDownTillElement(removeLink, driver);
				removeLink.click();
				break;
			}
		}
	}

	public String getProjectResultsCount() {
		extentTest.log(Status.INFO, "Get project results count");
		return projectResultCount.getText();
	}

	public String getChartProjectResultsCount() {
		extentTest.log(Status.INFO, "Get chart project results count");
		return chartprojectResultCount.getText();
	}

	public String getDashboard1_SectionsCount() {
		extentTest.log(Status.INFO, "Get section count");
		return Dashboard1_SectionsCount.getText();
	}

	public void clickDashboard1ToggleBtn() {
		extentTest.log(Status.INFO, "Click the Dashboard 1 visualization button");
		SeleniumUtils.isClickable(dashboard1ToggleBtn, driver);
		CommonUtils.clickOnElementUsingJavascript(dashboard1ToggleBtn, driver);
	}

	public boolean verifyConstructionDonutLegends() {
		extentTest.log(Status.INFO, "Verify all the options in actions drop down");
		boolean isMatch = false;
		int i = 0;
		List<String> constructionTypeDonutList = CommonUtils.getListFromWebElements(constructionTypeDonut);
		CommonUtils.IterateList(constructionTypeDonutList);
		for (DGNEnum.ConstructionTypeDonutLegends opt : DGNEnum.ConstructionTypeDonutLegends.values()) {
			if (constructionTypeDonutList.get(i).equals(opt.description())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;

	}

	public int getFilterCrumb_AppliedFilterList_Size() {
		extentTest.log(Status.INFO, "Get FilterCrumb_AppliedFilter_List size");
		return FilterCrumb_AppliedFilter_List.size();
	}

	public void clickOnChartConnstructionTypeInteriors() {
		chartConstructionTypeInteriors.click();
	}

	public void clickOnChartConnstructionTypeAdditions() {
		chartConstructionTypeAdditions.click();
	}

	public void mouseOverConstructionTypeAdditions() {
		chartConstructionTypeAdditions.click();
	}

	public void clickOnCommonShowMoreFiltes_Arrow() {
		extentTest.log(Status.INFO, "Clicking show more arrow button");
		if (SeleniumUtils.isVisible(CommonShowMoreFiltes, driver)) {
			CommonShowMoreFiltes.click();
		}
	}

	public void clickOnCommonShowLessFiltes_Arrow() {
		extentTest.log(Status.INFO, "Clicking show less arrow button");
		if (SeleniumUtils.isVisible(CommonShowLessFilters, driver)) {
			CommonShowLessFilters.click();
		}
	}

	public boolean IsspecAlertLHSFirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check specAlertLHSFirstOption_cbk is selected");
		return specAlertLHSFirstOption_cbk.isSelected();
	}

	public boolean IsspecAlertLHSExcludeChkbox_Selected() {
		extentTest.log(Status.INFO, "Check specAlert LHs exclude_cbk is selected");
		return excludeChkbox.isSelected();
	}

	public boolean IsTRACKING_LIST_SeeMore_btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see TRACKING_LIST_SeeMore_btn displayed");
		return TRACKING_LIST_SeeMore_btn.isDisplayed();
	}

	public String gettrackingList_PopupOption_Second_lbl() {
		extentTest.log(Status.INFO, "Get trackingList_PopupOption_Second label");
		return trackingList_PopupOption_lblList.get(2).getText();
	}

	public String gettrackingList_PopupOption_lbl_ByIndex(int index) {
		extentTest.log(Status.INFO, "Get trackingList_PopupOption_Second label");
		if (index <= trackingList_PopupOption_lblList.size()) {
			return trackingList_PopupOption_lblList.get(index).getText();
		}
		return null;
	}

	public List<String> gettrackingListPopup_all_lbl() {
		extentTest.log(Status.INFO, "Get trackingList_PopupOption all label");
		List<String> list = new ArrayList<String>();
		for (WebElement element : trackingList_PopupOption_lblList) {

			list.add(element.getText());
		}

		return list;
	}

	public boolean verifyTrackingListContainsCountInBrackets() {
		extentTest.log(Status.INFO, "verify tracking list contains count in brackets");
		for (WebElement element : trackingList_PopupOption_lblList) {
			if (!element.getText().contains("(") && element.getText().contains(")"))
				return false;
		}

		return true;
	}

	public void clickOnSearchBtn() {

		extentTest.log(Status.INFO, "Click on Search button on project search button.");
		searchButton.click();
	}

	public ProjectPage clickOnSearchButtonForDR() {

		extentTest.log(Status.INFO, "Click on Search button on project search button when searching DR.");
		searchButton.click();
		return new ProjectPage(driver);
	}

	public boolean verifyTrackingListContainsIconForPublicShared(String trackList) {
		extentTest.log(Status.INFO, "verify tracking list contains Icon for Public/Shared");

		if (trackingList_PopupOption_lblimgdiv.size() > 0) {
			return true;
		}

		return false;
	}

	public String gettrackingList_PopupOption_first_lbl() {
		extentTest.log(Status.INFO, "Get trackingList_PopupOption_First label");
		return trackingList_PopupOption_lblList.get(1).getText();
	}

	public String gettrackingList_LHSFirstOption_lbl() {
		extentTest.log(Status.INFO, "Get trackingList_LHSFirstOption_lbl label");
		return trackingList_LHSFirstOption_lbl.getText();
	}

	public boolean IstrackingList_LHSFirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check trackingList_LHSFirstOption_cbk is selected");
		return trackingList_LHSFirstOption_cbk.isSelected();
	}

	public void clickOnOWNERSHIP_TYPEFilter() {
		extentTest.log(Status.INFO, "Click on OWNERSHIP_TYPEFilter filter");
		if (CommonUtils.checkElementExist(OWNERSHIP_TYPE_ArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(OWNERSHIP_TYPE_ArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			OWNERSHIP_TYPEFilter.click();
			waitforLoadingRing();
		}
	}

	public void clickOnOWNERSHIP_TYPESeeMore_Btn() {
		extentTest.log(Status.INFO, "Click on OWNERSHIP_TYPESeeMore_Btn");
		CommonUtils.scrollDownTillElement(OWNERSHIP_TYPESeeMore_Btn, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		OWNERSHIP_TYPESeeMore_Btn.click();
		waitforLoadingRing();
	}

	public boolean IsOWNERSHIP_TYPESeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see OWNERSHIP_TYPESeeMore_Btn displayed");
		return OWNERSHIP_TYPESeeMore_Btn.isDisplayed();
	}

	public String getOWNERSHIP_TYPE_LHSFirstFilter_lbl() {
		extentTest.log(Status.INFO, "Get OWNERSHIP_TYPE_LHSFirstFilter_lbl label");
		return OWNERSHIP_TYPE_LHSFirstFilter_lbl.getText();
	}

	public boolean IsOWNERSHIP_TYPE_LHSFirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check OWNERSHIP_TYPE_LHSFirstOption_cbk is selected");
		return OWNERSHIP_TYPE_LHSFirstOption_cbk.isSelected();
	}

	public void clickOnCONSTRUCTION_TYPEFilter() {
		extentTest.log(Status.INFO, "Click on CONSTRUCTION_TYPE filter");
		if (CommonUtils.checkElementExist(CONSTRUCTION_TYPE_ArrowUp, driver)) {
			CONSTRUCTION_TYPEFilter.click();
			waitforLoadingRing();
		}
	}

	public void clickOnCONSTRUCTION_TYPESeeMore_Btn() {
		extentTest.log(Status.INFO, "Click on CONSTRUCTION_TYPESeeMore_Btn");
		if (CommonUtils.checkElementExist(CONSTRUCTION_TYPESeeMore_Btn, driver)) {
			CommonUtils.scrollDownTillElement(CONSTRUCTION_TYPESeeMore_Btn, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			CONSTRUCTION_TYPESeeMore_Btn.click();
			waitforLoadingRing();
		}
	}

	public boolean IsCONSTRUCTION_TYPESeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see CONSTRUCTION_TYPESeeMore_Btn displayed");
		return CONSTRUCTION_TYPESeeMore_Btn.isDisplayed();
	}

	public String getCommonPopupParentFilter_lbl(int indx) {
		extentTest.log(Status.INFO, "Get " + indx + " parent filter label");
		return CommonPopupParentFilter_lblList.get(indx).getText();
	}

	public String getConstructionType_LHSFirstFilter_lbl() {
		extentTest.log(Status.INFO, "Get ConstructionType_LHSFirstFilter label");
		return ConstructionType_LHSFirstFilter_lbl.getText();
	}

	public boolean IsConstructionType_LHSFirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check ConstructionType_LHSFirstOption_cbk is selected");
		return ConstructionType_LHSFirstOption_cbk.isSelected();
	}

	public boolean IsSPECIAL_CONDITIONSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see SPECIAL_CONDITIONSeeMore_Btn displayed");
		return SPECIAL_CONDITIONSeeMore_Btn.isDisplayed();
	}

	public String getSPECIAL_Conditions_LHSFirstFilter_lbl() {
		extentTest.log(Status.INFO, "Get SPECIAL_Conditions_LHSFirstFilter_lbl label");
		return SPECIAL_Conditions_LHSFirstFilter_lbl.getText();
	}

	public boolean IsSPECIAL_Conditions_LHSFirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check SPECIAL_Conditions_LHSFirstOption_cbk is selected");
		return SPECIAL_Conditions_LHSFirstOption_cbk.isSelected();
	}

	public void ClickOnSPECIAL_CONDITIONSeeMore_Btn() {
		extentTest.log(Status.INFO, "Click on SPECIAL_CONDITIONSeeMore_Btn");
		CommonUtils.scrollDownTillElement(SPECIAL_CONDITIONSeeMore_Btn, driver);
		if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
			waitforLoadingRing();
		}
		SPECIAL_CONDITIONSeeMore_Btn.click();
		waitforLoadingRing();
	}

	public void clickOnSPECIAL_CONDITIONFilter() {
		extentTest.log(Status.INFO, "Click on SPECIAL_CONDITIONFilter");
		if (CommonUtils.checkElementExist(SPECIAL_CONDITIONArrowUp, driver)) {
			CommonUtils.scrollDownTillElement(SPECIAL_CONDITIONArrowUp, driver);
			if (CommonUtils.checkElementExist(rotateLoadingIcon, driver)) {
				waitforLoadingRing();
			}
			SPECIAL_CONDITIONFilter.click();
			waitforLoadingRing();
		}
	}

	public boolean IsTradesSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see TradesSeeMore_Btn displayed");
		return TradesSeeMore_Btn.isDisplayed();
	}

	public String getTradeCategoryFacet_LHSFirstFilter_lbl() {
		extentTest.log(Status.INFO, "Get TradeCategoryFacet_LHSFirstFilter_lbl label");
		return TradeCategoryFacet_LHSFirstFilter_lbl.getText();
	}

	public boolean IsTradeCategoryFacet_LHSFirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check TradeCategoryFacet_LHSFirstOption_cbk is selected");
		return TradeCategoryFacet_LHSFirstOption_cbk.isSelected();
	}

	public boolean IsMaterials_EquipSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see Materials_EquipSeeMore_Btn displayed");
		return Materials_EquipSeeMore_Btn.isDisplayed();
	}

	public String getmaterialsEquip_LHSFirstFilter_lbl() {
		extentTest.log(Status.INFO, "Get materialsEquip_LHSFirstFilter label");
		return materialsEquip_LHSFirstFilter_lbl.getText();
	}

	public boolean IsmaterialsEquipFilter_FirstOption_cbk_Selected() {
		extentTest.log(Status.INFO, "Check materialsEquipFilter_FirstOption cbk is selected");
		return materialsEquipFilter_FirstOption.isSelected();
	}

	public boolean IsCounties_LHS_SelectAll_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see Counties_LHS_SelectAll_Btn displayed");
		return Counties_LHS_SelectAll_Btn.isDisplayed();
	}

	public boolean IsCounties_LHS_DeselectAll_Btn_Displayed() {
		extentTest.log(Status.INFO, "Check to see Counties_LHS_DeselectAll_Btn displayed");
		return Counties_LHS_DeselectAll_Btn.isDisplayed();
	}

	public void clickOnREPORTSFilter() {
		extentTest.log(Status.INFO, "Click on the REPORTS filter.");
		if (CommonUtils.checkElementExist(REPORTS_Filter_ArrowUp, driver)) {
			REPORTSFilter.click();
		}
		waitforLoadingRing();
	}

	public String getReportFilter_lbl(int indx) {
		extentTest.log(Status.INFO, "Get " + indx + " report filter label");
		return ReportFilter_LHS_lblList.get(indx).getText();
	}

	public String getFIND_INFilter_lbl() {
		extentTest.log(Status.INFO, "Get FIND_IN filter label");
		return FIND_INFilter.getText();
	}

	public String getPaginationSectionText() {
		extentTest.log(Status.INFO, "Get ProjectDisplayedCount");
		return projectCountTxt.getText();
	}

	public Boolean verifyChartConstructionTypes() {
		extentTest.log(Status.INFO, "Verify each construction type donut is same as legend");
		String matchedString = "";
		int i = 0;
		Boolean isMatch = false;

		for (DGNEnum.ConstructionTypeDonutLegends opt : DGNEnum.ConstructionTypeDonutLegends.values()) {
			matchedString = CommonUtils
					.retrieveRegexMatchString("c3-target-(.*)", chartConstructionTypes.get(i).getAttribute("class"))
					.replaceAll("\\s(.*)", "").trim();
			if (matchedString.equals(opt.description().trim())) {
				isMatch = true;
			} else {
				isMatch = false;
			}
			i++;
		}
		return isMatch;
	}

	public boolean verifyConstructionTypeInteriorsFiltered() {
		return chartConstructionTypeInteriors.getAttribute("transform").equals("scale(1.05)");

	}

	public boolean verifyConstructionTypeAlterationsFiltered() {
		return chartConstructionTypeAlterations.getAttribute("transform").equals("scale(1.05)");

	}

	public boolean verifyConstructionTypeNewProjectFiltered() {
		return chartConstructionTypeNewProject.getAttribute("transform").equals("scale(1.05)");

	}

	public boolean verifyConstructionTypeAdditionsFiltered() {
		return chartConstructionTypeAdditions.getAttribute("transform").equals("scale(1.05)");

	}

	public String getConstructionTypeAdditionsColor() {
		return chartConstructionTypeAdditions.getCssValue("fill").toString();

	}

	public boolean isChartHeaderConstructionTypeDisplayed() {
		extentTest.log(Status.INFO, "Verify if 'Constuction Type' displayed as chart header");
		return chartHeadConstructionType.isDisplayed();
	}

	public boolean checkConstructionTypeFiltered(DGNEnum.ConstructionTypeDonutLegends opt) {
		boolean isFiltered = false;
		switch (opt) {
		case ALTERATIONS:
			isFiltered = verifyConstructionTypeAlterationsFiltered();
			break;

		case NEW_PROJECT:
			isFiltered = verifyConstructionTypeNewProjectFiltered();
			break;

		case INTERIORS:
			isFiltered = verifyConstructionTypeInteriorsFiltered();
			break;
		case ADDITIONS:
			isFiltered = verifyConstructionTypeAdditionsFiltered();
			break;

		default:
			break;
		}
		return isFiltered;
	}

	public boolean IsbiddingWithin_FirstOption_Selected() {
		extentTest.log(Status.INFO, "Check to see biddingWithin LHS first filter option selected");
		return biddingWithin_LHSFilterList.get(0).isSelected();
	}

	public boolean checkSpecialFiltersOptions(List<String> elementList) {
		List<String> specialFilterOptionsActual = CommonUtils.getListFromWebElements(specialFilterOptionsList);
		return CommonUtils.CompareTwoList(elementList, specialFilterOptionsActual);
	}

	public String getStructuralPropLink_txt() {
		extentTest.log(Status.INFO, "Get StructuralPropLink text");
		return StructuralPropLink.getText();
	}

	public void ClickOnFilterpop_Overlay() {
		extentTest.log(Status.INFO, "Click out side the popup");
		Filterpop_Overlay.click();
		waitforLoadingRing();
	}

	public boolean Check_DefaultStatusOf_CheckboxList(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check by default filter checkboxs should not be selected");
		boolean checkboxStatus = false;
		switch (ParentList) {
		case "trackingList_PopupOptionList":
			checkboxStatus = Default_CheckboxStatus(trackingList_PopupOptionList);
			break;
		case "ProjectGroups_LHS_ParentFilterList":
			checkboxStatus = Default_CheckboxStatus(ProjectGroups_LHS_ParentFilterList);
			break;
		case "CommonPopupParentFilterList":
			checkboxStatus = Default_CheckboxStatus(CommonPopupParentFilterList);
			break;
		case "specDivisionFilterListPopup":
			checkboxStatus = Default_CheckboxStatus(specDivisionFilterListPopup);
			break;
		case "Common_Popup_allCbksList":
			checkboxStatus = Default_CheckboxStatus(Common_Popup_allCbksList);
			break;
		case "Common_Popup_FirstLevel2CbksList":
			checkboxStatus = Default_CheckboxStatus(Common_Popup_FirstLevel2CbksList);
			break;
		}
		return checkboxStatus;
	}

	public boolean Default_CheckboxStatus(List<WebElement> Checkboxlist) {
		extentTest.log(Status.INFO, "Verify default checkbox list status");
		return CommonUtils.isCheckboxListSelected(Checkboxlist);
	}

	public void clickBarChartProjectTypeEngineering() {
		extentTest.log(Status.INFO, "Clicking the value in the Project Type Bar Chart");
		waitforLoadingRing();
		barChartProjectTypeEngineering.click();
		waitforLoadingRing();
	}

	public void clickBarChartProjectTypeUtilities() {
		extentTest.log(Status.INFO, "Clicking the value in the Project Type Bar Chart");
		waitforLoadingRing();
		barChartProjectTypeUtilities.click();
		waitforLoadingRing();
	}

	public void clickBarChartProjectTypeGeneralBuilding() {
		extentTest.log(Status.INFO, "Clicking the value in the Project Type Bar Chart");
		waitforLoadingRing();
		barChartProjectTypeGeneralBuilding.click();
	}

	public boolean verifyBarChartProjectTypeEngineeringSelected() {
		extentTest.log(Status.INFO, "Verify if the value in the Project Type Bar Chart selected");
		waitforLoadingRing();
		return barChartProjectTypeEngineering.getAttribute("class").contains("barWrapper selectedbar");
	}

	public void clickBarChartProjectTypeHousing() {
		extentTest.log(Status.INFO, "Clicking the value in the Project Type Bar Chart");
		waitforLoadingRing();
		barChartProjectTypeHousing.click();
	}

	public boolean verifyBarChartProjectTypeHousingSelected() {
		extentTest.log(Status.INFO, "Verify if the value in the Project Type Bar Chart selected");
		waitforLoadingRing();
		return barChartProjectTypeHousing.getAttribute("class").contains("barWrapper selectedbar");
	}

	public void clickMapChartProjectDensityWashington() {
		extentTest.log(Status.INFO, "Clicking the location in the Project Density map");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mapChartProjectDensityWashington));
		mapChartProjectDensityWashington.click();
		waitforLoadingRing();
	}

	public void clickMapChartProjectDensityMontana() {
		extentTest.log(Status.INFO, "Clicking the location in the Project Density map");
		mapChartProjectDensityMontana.click();
		waitforLoadingRing();
	}

	public String hoverOverWashingTonandgetMapToolTip() {
		Actions action = new Actions(driver);
		action.clickAndHold(mapChartProjectDensityWashington).build().perform();
		extentTest.log(Status.INFO, "Hover over map and get the first tooltip");
		return mapTooltip.getText();

	}

	public String hoverOverTexasandgetMapToolTip() {
		Actions action = new Actions(driver);
		action.clickAndHold(mapChartProjectDensityTexas).build().perform();
		extentTest.log(Status.INFO, "Hover over map and get the first tooltip");
		return mapTooltip.getText();

	}

	public String hoverOverAdditionsandgetMapToolTip() {
		CommonUtils.scrollDownTillElement(chartConstructionTypeAdditions, driver);
		Actions action = new Actions(driver);
		action.clickAndHold(chartConstructionTypeAdditions).build().perform();
		extentTest.log(Status.INFO, "Hover over additions and get the first tooltip");
		return constructionTypetooltip.getText();
	}

	public List<String> getSmallStatesLabelNames() {
		extentTest.log(Status.INFO, "Get text of small USA states");

		List<String> listSmallStateLabelNames = new ArrayList<String>();
		for (WebElement element : smallStates) {

			listSmallStateLabelNames.add(element.getText());
		}

		return listSmallStateLabelNames;

	}

	public List<String> getCountDivisions() {
		extentTest.log(Status.INFO, "Get count of Divisions on chart view");

		List<String> listCountDivisions = new ArrayList<String>();
		for (WebElement element : countDivision) {

			listCountDivisions.add(element.getText());
		}

		return listCountDivisions;

	}

	public List<String> getProjectBarChartLabel() {
		extentTest.log(Status.INFO, "Get Project Bar Chart Label.");

		List<String> listCountDivisions = new ArrayList<String>();
		for (WebElement element : projectTypeBarTextChart) {
			listCountDivisions.add(element.getText());
		}
		return listCountDivisions;
	}

	public List<String> getSortedProjectTypeLabelList() {
		extentTest.log(Status.INFO, "Get Sorted Project Type Label list.");
		return CommonUtils.sortWebElements(CommonUtils.getListFromWebElements(projectTypeBarTextChart), true);
	}

	public List<String> getCountDivisionBars() {
		extentTest.log(Status.INFO, "Get count of Divisions bars on chart view");

		List<String> listCountDivisions = new ArrayList<String>();
		for (WebElement element : countDivisionBars) {

			listCountDivisions.add(element.getText());
		}

		return listCountDivisions;

	}

	public String mouseOverFirstDivisionBarandGetText() {
		extentTest.log(Status.INFO, "MouseOver on first division bar and get text");
		Actions actions = new Actions(driver);
		actions.clickAndHold(countDivisionBars.get(0)).build().perform();
		return countDivisionBars.get(0).getAttribute("desc");
	}

	public String mouseOverProjectTypeEngineeringBarandGetText() {
		extentTest.log(Status.INFO, "MouseOver on first project type bar and get text");
		Actions actions = new Actions(driver);
		actions.clickAndHold(barChartProjectTypeEngineering).build().perform();
		return barChartProjectTypeEngineering.getAttribute("tooltiptext");
	}

	public List<String> getCountSpecDiv() {
		extentTest.log(Status.INFO, "Get count of Spec Divisions on chart view");

		List<String> listCountDivisions = new ArrayList<String>();
		for (WebElement element : countspecDivInChart) {

			listCountDivisions.add(element.getText());
		}

		return listCountDivisions;

	}

	public List<String> getWidthOfSectionBars() {
		extentTest.log(Status.INFO, "Get width of SectionBars on chart view");
		List<String> listWidthSectionBars = new ArrayList<String>();
		for (WebElement element : widthOfSectionBars) {
			listWidthSectionBars.add(element.getText());
		}
		return listWidthSectionBars;

	}

	public String hoverOverManitbaStateAndGetMapToolTip() {
		Actions action = new Actions(driver);
		action.clickAndHold(mapChartManitobaState).build().perform();
		extentTest.log(Status.INFO, "Hover over map and get the first tooltip");
		return mapTooltip.getText();
	}

	public String getGeographicalMapTitle() {
		extentTest.log(Status.INFO, "Get the geographical map title");
		return mapTitles.get(2).getText();
	}

	public void clickMapChartProjectDensityTexas() {
		extentTest.log(Status.INFO, "Clicking the location in the Project Density map");
		mapChartProjectDensityTexas.click();
		waitforLoadingRing();
	}

	public String getFirstChartSubheaderText() {
		extentTest.log(Status.INFO, "get the first chart subheader text");
		return chartsubheader.get(0).getText();

	}

	public String getSecondChartSubheaderText() {
		extentTest.log(Status.INFO, "get the second chart subheader text");
		return chartsubheader.get(1).getText();

	}

	public boolean Check_First9_OptionsAreVisible(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check first 9 options are visible");
		boolean isVisible = false;
		switch (ParentList) {
		case "materialsEquip_LHS_FilterList":
			isVisible = Are_First9_OptionsVisible(materialsEquip_LHS_FilterList);
			break;
		case "Trades_LHS_ParentFilterList":
			isVisible = Are_First9_OptionsVisible(Trades_LHS_ParentFilterList);
			break;
		case "ProjectGroups_LHS_ParentFilterList":
			isVisible = Are_First9_OptionsVisible(ProjectGroups_LHS_ParentFilterList);
			break;
		case "StateRegionFilterList":
			isVisible = Are_First9_OptionsVisible(StateRegionFilterList);
			break;
		case "trackingListFacetList":
			isVisible = Are_First9_OptionsVisible(trackingListFacetList);
			break;
		case "GEOGRAPHY_COUNTYFilterList":
			isVisible = Are_First9_OptionsVisible(GEOGRAPHY_COUNTYFilterList);
			break;
		case "specDivisionFilterList":
			isVisible = Are_First9_OptionsVisible(specDivisionFilterList);
			break;
		case "specialConditonFilterList":
			isVisible = Are_First9_OptionsVisible(specialConditonFilterList);
			break;
		case "GeographyFilterList":
			isVisible = Are_First9_OptionsVisible(GeographyFilterList);
			break;
		}
		return isVisible;
	}

	public boolean Are_First9_OptionsVisible(List<WebElement> Checkboxlist) {
		for (int i = 0; i < 9; i++) {
			if (SeleniumUtils.isVisible(Checkboxlist.get(i), driver)) {
				return true;
			}
		}
		return false;
	}

	public boolean Check_10th_OptionsVisible(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check first 10th option is not visible");
		boolean isVisible = false;
		switch (ParentList) {
		case "materialsEquip_LHS_FilterList":
			isVisible = Is10th_OptionVisible(materialsEquip_LHS_FilterList);
			break;
		case "Trades_LHS_ParentFilterList":
			isVisible = Is10th_OptionVisible(Trades_LHS_ParentFilterList);
			break;
		case "ProjectGroups_LHS_ParentFilterList":
			isVisible = Is10th_OptionVisible(ProjectGroups_LHS_ParentFilterList);
			break;
		case "StateRegionFilterList":
			isVisible = Is10th_OptionVisible(StateRegionFilterList);
			break;
		case "trackingListFacetList":
			isVisible = Is10th_OptionVisible(trackingListFacetList);
			break;
		case "GEOGRAPHY_COUNTYFilterList":
			isVisible = Is10th_OptionVisible(GEOGRAPHY_COUNTYFilterList);
			break;
		case "specDivisionFilterList":
			isVisible = Is10th_OptionVisible(specDivisionFilterList);
			break;
		case "specialConditonFilterList":
			isVisible = Is10th_OptionVisible(specialConditonFilterList);
			break;
		case "GeographyFilterList":
			isVisible = Is10th_OptionVisible(GeographyFilterList);
			break;
		}
		return isVisible;
	}

	public boolean verifyMapChartProjectDensityWashingtonSelected() throws ParseException {
		extentTest.log(Status.INFO, "Verify if the location in the Project Density Map Chart selected");
		String jsonStr = mapChartProjectDensityWashington.getAttribute("data-info");
		String getJsonStr = CommonUtils.getStringFromJSON(jsonStr, "selected");
		waitforLoadingRing();
		return getJsonStr.equals("true");
	}

	public boolean verifyMapChartProjectDensityTexasSelected() throws ParseException {
		extentTest.log(Status.INFO, "Verify if the location in the Project Density Map Chart selected");
		String jsonStr = mapChartProjectDensityTexas.getAttribute("data-info");
		String getJsonStr = CommonUtils.getStringFromJSON(jsonStr, "selected");
		waitforLoadingRing();
		return getJsonStr.equals("true");
	}

	public int getMapChartProjectDensityTexasValue() {
		extentTest.log(Status.INFO, "get the location in the Project Density Map Chart");
		String jsonStr = mapChartProjectDensityTexas.getAttribute("data-info");
		int getJsonStr = 0;
		try {
			getJsonStr = Integer.parseInt(CommonUtils.getStringFromJSON(jsonStr, "value"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getJsonStr;
	}

	public int getMapChartProjectDensityWashingtonValue() {
		extentTest.log(Status.INFO, "get the location in the Project Density Map Chart");
		String jsonStr = mapChartProjectDensityWashington.getAttribute("data-info");
		int getJsonStr = 0;
		try {
			getJsonStr = Integer.parseInt(CommonUtils.getStringFromJSON(jsonStr, "value"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getJsonStr;
	}

	public void uncheckAllFindInContentType() {
		for (int i = 0; i < FindIn_ContentType.size(); i++) {
			if (FindIn_ContentType.get(i).getAttribute("checked") != null) {
				FindIn_ContentType.get(i).click();
				waitforLoadingRing();
			}
		}
	}

	/***
	 * Funtion uncheck all the find in filter expect one send as parameter
	 * 
	 * @param filterLabel
	 *            : Filter label which need to be selected as Find In filter.
	 */
	public void uncheckAllFindInFilterExceptOne(String filterLabel) {
		for (int i = 0; i < FindInFilterLabel.size(); i++) {
			if (!FindIn_ContentType.get(i).getText().trim().equalsIgnoreCase(filterLabel)) {
				if (FindIn_ContentType.get(i).getAttribute("checked") != null) {
					FindIn_ContentType.get(i).click();
					waitforLoadingRing();
				}
			}
		}
	}

	public void checkPlanInFindInContentType() {

		for (int i = 0; i < FindIn_ContentType.size(); i++) {
			if (FindIn_ContentType.get(i).getAttribute("value").equals("Plans")) {
				FindIn_ContentType.get(i).click();
				waitforLoadingRing();
			}
		}
	}

	public void checkSpecsInFindInContentType() {

		for (int i = 0; i < FindIn_ContentType.size(); i++) {
			if (FindIn_ContentType.get(i).getAttribute("value").equals("Specs")) {
				FindIn_ContentType.get(i).click();
				waitforLoadingRing();
			}
		}
	}

	public boolean Is10th_OptionVisible(List<WebElement> Checkboxlist) {
		return SeleniumUtils.isVisible(Checkboxlist.get(9), driver);
	}

	public boolean Check_ListOptionsAre_Displayed(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check list options are visible");
		boolean isVisible = false;
		switch (ParentList) {
		case "materialsEquip_LHS_FilterList":
			isVisible = ListOptionsDisplayed(materialsEquip_LHS_FilterList);
			break;
		case "CommonPopupParentFilterList":
			isVisible = ListOptionsDisplayed(CommonPopupParentFilterList);
			break;
		case "GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List":
			isVisible = ListOptionsDisplayed(GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List);
			break;
		case "StateRegion_ProjectCountNextToElement_List":
			isVisible = ListOptionsDisplayed(StateRegion_ProjectCountNextToElement_List);
			break;
		case "biddingWithin_LHSFilterList":
			isVisible = ListOptionsDisplayed(biddingWithin_LHSFilterList);
			break;
		}
		return isVisible;
	}

	public boolean ListOptionsDisplayed(List<WebElement> Checkboxlist) {

		for (int i = 0; i < Checkboxlist.size(); i++) {
			if (Checkboxlist.get(i).isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	public boolean ProjGrp_scrollbar_verticalDragger_displayed() {
		extentTest.log(Status.INFO, "Check ProjGrp_scrollbar_verticalDragger is displayed");
		return ProjGrp_scrollbar_verticalDragger.isDisplayed();
	}

	public boolean constructionTypeScrollbarVerticalDraggerDisplayed() {
		extentTest.log(Status.INFO, "Check constructionTypeScrollbarVerticalDragger is displayed");
		return constructionTypeScrollbarVerticalDragger.isDisplayed();
	}

	public int GetFilterFacetPopUp_Height() {
		extentTest.log(Status.INFO, "Get filter see more popup height");
		return FilterFacetPopUp.getSize().getHeight();
	}

	public int GetFilterFacetPopUp_Width() {
		extentTest.log(Status.INFO, "Get filter see more popup width");
		return FilterFacetPopUp.getSize().getWidth();
	}

	public boolean IsPROJECT_TYPE_CODE_Filter_Diaplayed() {
		extentTest.log(Status.INFO, "Check to see PROJECT_TYPE_CODE_Filter is displayed");
		return PROJECT_TYPE_CODE_Filter.isDisplayed();
	}

	public boolean IsProjectTypesSelectAll_Btn_Diaplayed() {
		extentTest.log(Status.INFO, "Check to see ProjectTypesSelectAll_Btn is displayed");
		return ProjectTypesSelectAll_Btn.isDisplayed();
	}

	public boolean IsProjectTypesDeselectAll_Btn_Diaplayed() {
		extentTest.log(Status.INFO, "Check to see ProjectTypesDeselectAll_Btn is displayed");
		return ProjectTypesDeselectAll_Btn.isDisplayed();
	}

	public boolean IsmaterialsEquip2Filter_Displayed() {
		extentTest.log(Status.INFO, "Check to see materialsEquip2Filter is displayed");
		return materialsEquip2Filter.isDisplayed();
	}

	public boolean IsMaterials_Equip2SelectAll_Link_Diaplayed() {
		extentTest.log(Status.INFO, "Check to see Materials_Equip2SelectAll_Link is displayed");
		return Materials_Equip2SelectAll_Link.isDisplayed();
	}

	public boolean IsMaterials_Equip2DeselectAll_Link_Diaplayed() {
		extentTest.log(Status.INFO, "Check to see Materials_Equip2DeselectAll_Link is displayed");
		return Materials_Equip2DeselectAll_Link.isDisplayed();
	}

	public boolean IsStructuralPropLink_Displayed() {
		extentTest.log(Status.INFO, "Check Structural Prop Link displayed");
		return StructuralPropLink.isDisplayed();
	}

	public boolean IsPublishRangeFilter_Displayed() {
		extentTest.log(Status.INFO, "Check PublishRangeFilter displayed");
		return PublishRangeFilter.isDisplayed();
	}

	public boolean IsfilterclearAllLink_Displayed() {
		extentTest.log(Status.INFO, "Check filterclearAllLink displayed");
		return filterclearAllLink.isDisplayed();
	}

	public boolean IsgeographyUSAchkbox_Selected() {
		extentTest.log(Status.INFO, "Check GeographyUSAchkbox selected");
		return geographyUSAchkbox.isSelected();
	}

	public boolean IsZeroProjCountElement_Displayed_StateRegionFilterList() {
		for (int i = 0; i < StateRegion_ProjectCountNextToElement_List.size(); i++) {
			int j = Integer.parseInt(StateRegion_ProjectCountNextToElement_List.get(i).getText().replace("(", "")
					.replace(",", "").replace(")", ""));
			if (j > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean IsZeroProjCountElement_Displayed_GeographyFilterList() {
		for (int i = 0; i < GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List.size(); i++) {
			int j = Integer.parseInt(GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List.get(i).getText().replace("(", "")
					.replace(",", "").replace(")", ""));
			if (j > 0) {
				return true;
			}
		}
		return false;
	}

	public String getFirstGeographyInFilterProjectCount() {
		return GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List.get(0).getText();
	}

	public boolean IsGEOGRAPHY_STATE_Filter_Expanded() {
		return CommonUtils.checkElementExist(GEOGRAPHY_STATE_ArrowDown, driver);
	}

	public boolean IsGEOGRAPHY_STATE_Filter_Collapsed() {
		GEOGRAPHY_STATE_ArrowDown.click();
		return CommonUtils.checkElementExist(GEOGRAPHY_STATE_ArrowUp, driver);
	}

	public boolean IsProject_Delivery_Filter_Expanded() {
		extentTest.log(Status.INFO, "Chcking Project Delivery filter is expanded or collapsed");
		try {
			if (ProjectDeliveryFilterCollapsed.isDisplayed()) {
				return false;
			}
		} catch (Exception e) {

		}
		return true;
	}

	public void Expand_Project_Delivery_Filter() {
		extentTest.log(Status.INFO, "Expanding Project Delivery filter");
		if (!IsProject_Delivery_Filter_Expanded()) {
			ProjectDeliveryFilter.click();
			waitforLoadingRing();
		}

	}

	public void ExpandAndCollapsePDSUsingArrowClick() {
		if (IsProject_Delivery_Filter_Expanded()) {
			ProjectDeliveryFilterCollapsed.click();
		} else {
			ProjectDeliveryFilterExpanded.click();
		}

	}

	public String checkProjectDelivaryFilterName() {
		extentTest.log(Status.INFO, "Get PDS Filter name");
		System.out.println(PDSFilterName.getText());
		return PDSFilterName.getText();
	}

	public void clickOnPDSSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on Project Groups See More Btn");
		PDS_SeeMore_Btn.click();
	}

	public boolean isPDSFilterDisplayed() {
		extentTest.log(Status.INFO, "Verify if the PDS filter displayed in left nav");
		return ProjectDeliveryFilter.isDisplayed();
	}

	public boolean isSeeMorePopUpDisplayed() {
		extentTest.log(Status.INFO, "Check if pop up is opened after clicking on facet");
		driver.switchTo().activeElement();
		return PDSSeeMorePopUp.isDisplayed();
	}

	public boolean IsGEOGRAPHY_COUNTRY_Filter_Expanded() {
		return CommonUtils.checkElementExist(GEOGRAPHY_COUNTRY_Filter_ArrowDown, driver);
	}

	public boolean IsGEOGRAPHY_COUNTRY_Filter_Collapsed() {
		GEOGRAPHY_COUNTRY_Filter_ArrowDown.click();
		return CommonUtils.checkElementExist(GEOGRAPHY_COUNTRY_Filter_ArrowUp, driver);
	}

	public boolean IsProjectDelivarySystem_Filter_Collapsed() {

		return CommonUtils.checkElementExist(ProjectDeliveryFilterCollapsed, driver);
	}

	public void ClickOnvalMinimumDropdown() {
		extentTest.log(Status.INFO, "Click on valuation minimum From dropdown");
		valMinimumDropdown.click();
	}

	public void ClickOnvalMaximumDropdown() {
		extentTest.log(Status.INFO, "Click on valuation maximum To dropdown");
		valMaximumDropdown.click();
	}

	public void ClickOnvalMinimumDropdownSeventhOption() {
		extentTest.log(Status.INFO, "Select Seventh option from min dropdown");
		valMinimumDropdownSeventhOption.click();
		waitforLoadingRing();
	}

	public void ClickOnvalMaximumDropdownSecondOption() {
		extentTest.log(Status.INFO, "Select Second option from max dropdown");
		valMaximumDropdownSecondOption.click();
		waitforLoadingRing();

	}

	public String GetValuationStatus_text() {
		extentTest.log(Status.INFO, "Get valuation status message");
		return ValuationStatus.getText();
	}

	public boolean IsGEOGRAPHY_CountyFilter_Displayed() {
		extentTest.log(Status.INFO, "Check  GEOGRAPHY_CountyFilter is displayed");
		return GEOGRAPHY_CountyFilter.isDisplayed();
	}

	public boolean IsGEOGRAPHY_County_scrollbar_verticalDragger_displayed() {
		extentTest.log(Status.INFO, "Check GEOGRAPHY_County_scrollbar_verticalDragger is displayed");
		return GEOGRAPHY_County_scrollbar_verticalDragger.isDisplayed();
	}

	public void EnterPublichRangeFromMax() {
		extentTest.log(Status.INFO, "Enter Publich Range From date");
		SeleniumUtils.isClickable(PublichRangeFromtxt, driver);
		PublichRangeFromtxt.click();
		SeleniumUtils.isClickable(PublichRangeFromdayMax, driver);
		PublichRangeFromdayMax.click();
		waitforLoadingRing();
	}

	public void EnterPublichRangeToMin() {
		extentTest.log(Status.INFO, "Enter Publich Range To date");
		SeleniumUtils.isClickable(PublichRangeTotxt, driver);
		PublichRangeTotxt.click();
		SeleniumUtils.isClickable(PublichRangeTodayMin, driver);
		PublichRangeTodayMin.click();
		waitforLoadingRing();
	}

	public String getPublishRange_errorMessage() {
		extentTest.log(Status.INFO, "Get publich range error message");
		return PublishRange_errorMessage.getText();
	}

	public String getSpecAlertFilter_Exclude_SpecAlerts_tooltip() {
		extentTest.log(Status.INFO, "Get specAlertFilter_Exclude_SpecAlerts_tooltip");
		new Actions(driver).moveToElement(specAlertFilter_Exclude_SpecAlerts_tooltip).build().perform();
		return specAlertFilter_Exclude_SpecAlerts_tooltip.getAttribute("title");
	}

	public void addSearchTextIn_FilterCrumbPopup_Searchbox(String searchtxt) {
		extentTest.log(Status.INFO, "Add search text in crumb popup searchbox");
		crumbFilterPopupSearchBox.sendKeys(searchtxt);
	}

	public List<String> getCrumbFilterTextListFromFilterPopup() {
		extentTest.log(Status.INFO, "Get filter text list from filet crumb popup.");
		return CommonUtils.getListFromWebElements(crumbFilterCrumbPopupFilterList);
	}

	public boolean verifyCrumbFilterTextListFromFilterPopup(List<String> filterLIstToBeVerified) {
		extentTest.log(Status.INFO, "Verify filter text list from filet crumb popup.");
		boolean isVerified = false;
		List<String> filterList = getCrumbFilterTextListFromFilterPopup();
		if (filterLIstToBeVerified.size() == filterList.size()) {
			for (int index = 0; index < filterLIstToBeVerified.size(); index++) {
				isVerified = filterList.get(index).contains(filterLIstToBeVerified.get(index));
				if (!isVerified) {
					break;
				}
			}
		}
		return isVerified;
	}

	public boolean verifyCrumbFilterTextListFromMainFilterCrumb(List<String> filterLIstToBeVerified) {
		extentTest.log(Status.INFO, "Verify filter text list from filet crumb.");
		boolean isVerified = false;
		List<String> filterList = getAllCrumbFilterTextList();
		List<String> modifiedFilterList = new ArrayList<>();
		if (filterLIstToBeVerified.size() == filterList.size()) {
			for (int index = 0; index < filterList.size(); index++) {
				modifiedFilterList.add(filterList.get(index).replace("BOD - ", "").trim());
			}
			for (String filterText : modifiedFilterList) {
				for (String filterTextToBeVerified : filterLIstToBeVerified) {
					isVerified = filterText.contains(filterTextToBeVerified);
					if (isVerified) {
						break;
					}
				}
				if (!isVerified) {
					break;
				}
			}
		}
		return isVerified;
	}

	public String getFirstFilter_txt_From_FilterCrumbPopup_FilterList() {
		extentTest.log(Status.INFO, "Get the first applied filter text");
		return crumbFilterCrumbPopupFilterList.get(0).getText();
	}

	public String getSecondFilter_txt_From_FilterCrumbPopup_FilterList() {
		extentTest.log(Status.INFO, "Get the second applied filter text");
		return crumbFilterCrumbPopupFilterList.get(1).getText();
	}

	public String getThirdFilter_txt_From_FilterCrumbPopup_FilterList() {
		extentTest.log(Status.INFO, "Get the third applied filter text");
		return crumbFilterCrumbPopupFilterList.get(2).getText();
	}

	public String getFourthFilter_txt_From_FilterCrumbPopup_FilterList() {
		extentTest.log(Status.INFO, "Get the fourth applied filter text");
		return crumbFilterCrumbPopupFilterList.get(3).getText();
	}

	public void clickOnCrumbFilterPopupFirstCloseBtn() {
		extentTest.log(Status.INFO, "Click on first close button");
		crumbFilterPopupCloseBtnList.get(0).click();
	}

	public void clickOnCrumbFilterPopupDonebtn() {
		extentTest.log(Status.INFO, "Click on Done link");
		FilterCrumbPopup_Done_Link.click();
		waitforLoadingRing();
	}

	public int getProjectCountOf_FirstOption_OWNERSHIP_TYPE_LHSFilterList() {
		extentTest.log(Status.INFO, "Get project count of first option from the list");
		return Integer.parseInt(OWNERSHIP_TYPEFilter_LHS_ProjectCountNextToElement_List.get(0).getText()
				.replace("(", "").replace(",", "").replace(")", ""));
	}

	public int getProjectCountOf_FirstThreeOption_OWNERSHIP_TYPE_LHSFilterList() {
		extentTest.log(Status.INFO, "Get project count of first option from the list");
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			int x = Integer.parseInt(OWNERSHIP_TYPEFilter_LHS_ProjectCountNextToElement_List.get(i).getText()
					.replace("(", "").replace(",", "").replace(")", ""));
			sum = sum + x;
		}
		return sum;
	}

	public String getOWNERSHIP_TYPEFilter_LHSFirstOption_lbl() {
		extentTest.log(Status.INFO, "Get first option text from LHS list");
		return OWNERSHIP_TYPEFilter_LHS_lblList.get(0).getText().replaceAll("\\P{L}", "");
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public boolean IsCustomRangeBiddingRadio_txt_displayed() {
		extentTest.log(Status.INFO, "Check  CustomRangeBiddingRadio option displayed");
		return CustomRangeBiddingRadio_txt.isDisplayed();
	}

	public void setBidCustomRange_FromDate(String bidFromDate) {
		DatePickerUtils datePicker = new DatePickerUtils(driver);
		extentTest.log(Status.INFO, "Set the bid fromdate for bid within filter.");
		datePicker.setDate(customRange_BidWithIn_FromDate, bidFromDate);
	}

	public String getCustomRangeBiddingRadio_errorMsg_txt() {
		extentTest.log(Status.INFO, "get CustomRangeBiddingRadio error message");
		return CustomRangeBiddingRadio_errorMsg.getText();
	}

	public String getVALUATION_Filter_radiobtn_label(int idx) {
		extentTest.log(Status.INFO, "get label of" + idx + "option");
		return VALUATION_Filter_radiobtn_label.get(idx).getText();
	}

	public boolean ISCheckboxFromStructProp_Selected(int option) {
		extentTest.log(Status.INFO, "Select checkbox option number" + option + "from the list");
		return StructuralPropertiesCheckboxList.get(option).isSelected();
	}

	public void ClickOnReports_ProjOutSideOfSub_radio() {
		extentTest.log(Status.INFO, "Select projects outside of subscription radio button");
		SeleniumUtils.isVisible(Reports_ProjOutSideOfSub_radio, driver);
		Reports_ProjOutSideOfSub_radio.click();
		waitforLoadingRing();
	}

	public boolean Is_specDivisionPopup_ThirdCheckbox_Selected() {
		extentTest.log(Status.INFO, "Check specDivisionPopup_ThirdCheckbox is selected");
		return specDivisionPopup_ThirdCheckbox.isSelected();
	}

	public boolean Check_ProjectResultList_Size() {
		extentTest.log(Status.INFO, "Check the size of project result list");
		return CommonUtils.verifyListSize(ProjectResult_Chkbox_List);
	}

	public boolean ISPopupSelectAll_cbk_displayed() {
		extentTest.log(Status.INFO, "Check SelectAll checkbox displayed");
		return PopupSelectAll_cbk.isDisplayed();
	}

	public boolean ISSeeMorePopup_UpdateBtn_displayed() {
		extentTest.log(Status.INFO, "Check Update button on see more popup is displayed");
		return PopupUpdateFancyBoxbtn.isDisplayed();
	}

	public boolean ISXCloseButtonOn_displayed() {
		extentTest.log(Status.INFO, "Check X close popup button is displayed");
		return ClosePopup.isDisplayed();
	}

	public boolean ISCommonPopop_Accodion_DownArrow_displayed() {
		extentTest.log(Status.INFO, "Ensure that this popop have have only one panel visible");
		return SeleniumUtils.isVisible(CommonPopop_Accodion_DownArrow, driver);
	}

	public void ClickOn_Accodion_DownArrow() {
		extentTest.log(Status.INFO, "Click on accordion doen arrow");
		CommonPopop_Accodion_DownArrow.click();
	}

	public boolean IsmySearchesDropDown_Displayed() {
		extentTest.log(Status.INFO, "Check mysearches dropdown displayed");
		return mySearchesDropDown.isDisplayed();
	}

	public Point getPositionOfKeywordSearchTextfield() {
		extentTest.log(Status.INFO, "Get position of the Keyword Search textfield.");
		return keywordSearchTextfieldContainer.getLocation();
	}

	public void clickSpecialFilterDesignAlertsOnly() {
		extentTest.log(Status.INFO, "Clicking on the special filter design alerts only");
		LeftpnlSpecialFiltersDesignAlertsOnly.click();
		waitforLoadingRing();
	}

	public void clickFindInFilterDesignAlertsOnly() {
		extentTest.log(Status.INFO, "Clicking on the special filter design alerts only");
		mySearchesSavedSearch.click();
	}

	public Point getPositionOfMySearchesTextfield() {
		extentTest.log(Status.INFO, "Get position of the My Searches drop-down.");
		return mySearchesDropDownContainer.getLocation();
	}

	public void clickOnprojectGroupsFilterWithParticularIndex(int i) {
		extentTest.log(Status.INFO, "Clicking on particular index for projectGroupsFilter");
		if (projectGroupsFilter.size() > 0)
			projectGroupsFilter.get(i).click();

	}

	public boolean isProjectCatgoriesFilterCheckboxesChecked() {
		extentTest.log(Status.INFO, "Checking filter checkboxes checked in Project Type Categories left nav filter");
		try {
			if (leftnavprojectTypeCategoriesFilter.size() > 0)
				for (int i = 0; i < leftnavprojectTypeCategoriesFilter.size(); i++) {
					if (!leftnavprojectTypeCategoriesFilter.get(i).isSelected()) {
						return false;
					}
				}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public String getLeftNavProjectTypeCategoriesFilterLabel() {
		extentTest.log(Status.INFO, "Get text of Left Nav Project Categories Filter label");
		return PROJECT_TYPE_CATEGORY_Filter.getText();
	}

	public String getLeftNavProjectTypeFilterLabel() {
		extentTest.log(Status.INFO, "Get text of Left Nav Project Type Filter label");
		return PROJECT_TYPE_Filter.getText();
	}

	public List<String> getMySearchesDropDownValues() {
		extentTest.log(Status.INFO, "Get My Searches drop-down values.");
		List<String> mySearchesDropDownListValuesStr = new ArrayList<>();
		for (WebElement mySearchesDropDownListValue : mySearchesDropDownListValues) {
			mySearchesDropDownListValuesStr.add(mySearchesDropDownListValue.getText());
		}
		return mySearchesDropDownListValuesStr;
	}

	public List<String> getGridHeaderTileList() {
		extentTest.log(Status.INFO, "Get ordered list of grid header title .");
		List<String> GridTitleRowStr = new ArrayList<>();

		for (int i = 2; i < GridTitleRow.size(); i++) {

			while (GridTitleRow.get(i).getText().equals("")) {
				GridRightScroll.click();
				SeleniumUtils.scrollToView(driver, GridTitleRow.get(i));
			}
			GridTitleRowStr.add(GridTitleRow.get(i).getText());
		}

		return GridTitleRowStr;
	}

	public void clickOnAllGridHeaderTitles() {
		for (int i = 2; i < GridTitleRow.size(); i++) {
			while (GridTitleRow.get(i).getText().equals("")) {
				GridRightScroll.click();
				SeleniumUtils.scrollToView(driver, GridTitleRow.get(i));
			}
			GridTitleRow.get(i).click();
		}
	}

	public String getLastColumnFromProjGrid() {
		extentTest.log(Status.INFO, "Get last column Text from project grid table.");
		return GridTitleRow.get(GridTitleRow.size() - 1).getText().toUpperCase().trim();
	}

	public String GetRequireddataColumnValue(int requiredRowvalue) {
		String requiredDatavalue = gridProjectTitleColumn.get(requiredRowvalue).getText();
		return requiredDatavalue;
	}

	public ProjectPage clickOnGridFirstProjectTitle() {
		extentTest.log(Status.INFO, "Clicking on Grid First Project Title");
		SeleniumUtils.isVisible(gridProjectTitleColumn.get(0), driver);
		gridProjectTitleColumn.get(0).findElement(By.cssSelector("span>a")).click();
		waitforLoadingRing();
		return new ProjectPage(driver);
	}

	public boolean verifyGridColumnTitlevalue(String value) {
		for (int i = 0; i < gridProjectTitleColumn.size(); i++) {
			if (gridProjectTitleColumn.get(i).getText().contains(value)) {
				return true;
			}

		}
		return false;

	}

	public ProjectPage clickRequiredProjectTitleColumnValue(int Indexvalue) {
		gridProjectTitleColumn_ProjectLink.get(Indexvalue).click();
		waitforLoadingRing();
		return new ProjectPage(driver);

	}

	public int GetCurrentlyDisplayedFirstdataColumnIndex() {
		extentTest.log(Status.INFO,
				"Get the index value from first data column in Grid which is displayed in current scroll position");
		int indexvalue = 0;
		for (int i = 0; i < gridProjectTitleColumn.size(); i++) {
			if (!(gridProjectTitleColumn.get(i).getText().equals(""))) {
				indexvalue = i;
				break;

			}
		}
		return indexvalue + 5;
	}

	public CompanyResultsPage rightClickOnCompaniesTab() {
		Actions buildOpenInNewTab = new Actions(driver);
		buildOpenInNewTab.contextClick(companiesTab).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
		buildOpenInNewTab.build().perform();
		return new CompanyResultsPage(driver);
	}

	public CompanyResultsPage ClickOnCompaniesTab() {
		extentTest.log(Status.INFO, "Click on Companies Link");
		companiesTab.click();
		return new CompanyResultsPage(driver);
	}

	public void ClickOnProjectsTab() {
		extentTest.log(Status.INFO, "Click on Projects Link");
		projectsTab.click();
		// return new ProjectResultsPage(driver);
	}

	public boolean isDesignAlertsIconDisplayed() {
		extentTest.log(Status.INFO, "Verify if the design alerts icon is displayed");
		try {
			return iconDesignAlerts.get(0).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isDesignAlertsLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the design alerts link is displayed");
		try {
			return linkDesignAlerts.get(0).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public ProjectDesignAlertsPage clickOnDesignAlertLink() {
		extentTest.log(Status.INFO, "Clicking on first design Alert link");
		linkDesignAlerts.get(0).click();
		return new ProjectDesignAlertsPage(driver);
	}

	public boolean specialFilterDesignAlertsOnlyDisplayed() {
		extentTest.log(Status.INFO, "Verify the special filter design alerts only is displayed");
		try {
			return LeftpnlSpecialFiltersDesignAlertsOnly.isDisplayed();
		} catch (Exception ex) {
			return false;
		}

	}

	public boolean CheckFilterpop_Overlay_BGColor() {
		extentTest.log(Status.INFO, "Ensure that the background color is grey");
		return Filterpop_Overlay.getAttribute("style").contains("rgb(119, 119, 119)");
	}

	public EcommercePage clickAddOnlineProjects() {
		extentTest.log(Status.INFO, "Clicking on the Add Online Projects link under the actions dropdown");
		addOnlineProjectActionOptions.click();
		return new EcommercePage(driver);
	}

	public boolean verifyMessageToAddOnlineProjects() {
		extentTest.log(Status.INFO,
				"'To add project to your license, please change to List View' error message is not displayed.");
		return getErrorMessage().equalsIgnoreCase("To add project to your license, please change to List View.");
	}

	// Return the boolean result of Addenda link present on the project result
	// page.
	public boolean isAddendaLinkIsDisplayed() {
		extentTest.log(Status.INFO, "Check if Addenda link is displayed");
		return CommonUtils.checkElementExist(addendaLink, driver);
	}

	public void ClickOnspecialFilter_LHS_ParentFilter_FirstCheckbox(int idx) {
		extentTest.log(Status.INFO, "Click on the" + idx + "radio button");
		specialFilter_LHS_ParentFilter_cbkList.get(idx).click();
		waitforLoadingRing();
	}

	public boolean isFirstImgInCartDisplayed() {
		extentTest.log(Status.INFO, "Verify if the image in cart displayed for the first project");
		try {
			return firstImgInCart.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isAddOnlineProjectActionOptionsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Add Online Project displayed under actions dropdown");
		try {
			return addOnlineProjectActionOptions.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public ProjectPage clickOnFirstPlansLink() {
		extentTest.log(Status.INFO, "Click on the first plans link");
		lnkPlans.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstSpecsLink() {
		extentTest.log(Status.INFO, "Click on the first specs link");
		lnkSpecs.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectPage clickOnFirstAddendaLink() {
		extentTest.log(Status.INFO, "Click on the first Addenda link");
		lnkAddenda.get(0).click();
		return new ProjectPage(driver);
	}

	public ProjectAddendaPage clickOnFirstAddendaLinkProjectAddendaPage() {
		extentTest.log(Status.INFO, "Click on the first Addenda link");
		lnkAddenda.get(0).click();
		return new ProjectAddendaPage(driver);
	}

	public ProjectAddendaPage clickOnFirstAddendaWithProjectAddendaPage() {
		extentTest.log(Status.INFO, "Click on the first Addenda link");
		lnkAddenda.get(0).click();
		return new ProjectAddendaPage(driver);
	}

	public ProjectPage clickOnSecondPlansLink() {
		extentTest.log(Status.INFO, "Click on the second plans link");
		lnkPlans.get(1).click();
		return new ProjectPage(driver);
	}

	public void clickOnSpecialFilterCollapsedPanel() {
		extentTest.log(Status.INFO, "Click on the collapsed panel of special filter");
		specialFilterCollapsed.click();
	}

	// Return the list of Project titles present on project page.
	public List<String> getListProjectTitle() {
		extentTest.log(Status.INFO, "Return the list of Project titles present on project page.");
		SeleniumUtils.isVisible(projectTitleList.get(0), driver);
		return CommonUtils.getListFromWebElements(projectTitleList);
	}

	// Scroll down till result per page at bottom.
	public void scrollResultPerPageAtBottom() {
		extentTest.log(Status.INFO, "Scroll down till result per page at bottom.");
		CommonUtils.scrollDownTillElement(resultsDropdown.get(1), driver);
	}

	public boolean isSpecialFilterCollapsed() {
		extentTest.log(Status.INFO, "Verify if the Special Filters Collapsed");
		try {
			return specialFilterCollapsed.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isFindInContentTypeCollapsed() {
		extentTest.log(Status.INFO, "Verify if the Find In panel collapsed");
		try {
			return FindIn_ContentType_collapsedPnl.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickFindInCollapsed() {
		extentTest.log(Status.INFO, "Click Find In collapsed panel");
		FindIn_ContentType_collapsedPnl.click();
	}

	public boolean IsCommonPopupHideZeroProjects_cbk_Displayed() {
		extentTest.log(Status.INFO, "Check Hide Zero Projects checkbox displayed");
		return CommonPopupHideZeroProjects_cbk.isDisplayed();
	}

	public boolean IsZeroProjCountElement_Displayed_SpecDivPopopFilterList() {
		int j = 0;
		for (int i = 0; i < specDivisionFilterPopup_lbl_list.size(); i++) {
			String string = specDivisionFilterPopup_lbl_list.get(i).getText();
			String[] parts = string.split("\\(");
			String part2 = parts[parts.length - 1];
			j = Integer.parseInt(part2.replaceAll("\\D+", ""));
			if (j < 0) {
				return false;
			}
		}
		return true;
	}

	public boolean IscountDivision_FirstCount_Clicable() {
		extentTest.log(Status.INFO, "Click on Project count beside the divG bar");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(countDivision.get(0)));
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public String GetProjectDensitybyLocation_ErrorMsg() {
		extentTest.log(Status.INFO, "Get ProjectDensitybyLocation Error Message");
		return ProjectDensitybyLocation_ErrorMsg.getText();
	}

	public String GetspecDivisionFilterPopup_Optionlbl(int idx) {
		extentTest.log(Status.INFO, "Get text of " + idx + " option");
		return specDivisionFilterPopup_lbl_list.get(idx).getText();
	}

	public boolean Are_First7_ChartView_DivGBarList_OptionsVisible() {
		for (int i = 0; i < 7; i++) {
			if (SeleniumUtils.isVisible(ChartView_DivGBarList.get(i), driver)) {
				return true;
			}
		}
		return false;
	}

	public boolean Is8th_ChartView_DivGBarList_OptionVisible() {
		return SeleniumUtils.isVisible(ChartView_DivGBarList.get(7), driver);
	}

	public String GetChartView_DivGBar_Leftlbl(int idx) {
		extentTest.log(Status.INFO, "Get text of " + idx + " option");
		CommonUtils.scrollDownTillElement(ChartView_DivGBar_LeftlblList.get(idx), driver);
		return ChartView_DivGBar_LeftlblList.get(idx).getText();
	}

	public void ClickOnDashboard1_FirstDD_CLH() {
		extentTest.log(Status.INFO, "Click on the dropdown");
		Dashboard1_FirstDD.click();
		Dashboard1_FirstDD_CLH.click();
	}

	public String GetSectionChart_ErrorMsg() {
		extentTest.log(Status.INFO, "Get SectionChart_ErrorMsg Error Message");
		return SectionChart_ErrorMsg.getText();
	}

	public void ClickOnChartView_FirstDivGBar() {
		extentTest.log(Status.INFO, "Click on ChartView_FirstDivGBar");
		ChartView_FirstDivGBar.click();
	}

	public boolean IsFindIn_LHS_CbkList(int idx) {
		extentTest.log(Status.INFO, "Check FindIn checkbox " + idx + " is selected");
		return FindIn_LHS_CbkList.get(idx).isSelected();
	}

	public boolean getCommonPopupParentFilterList_option_status(int idx) {
		extentTest.log(Status.INFO, "Get CommonPopupParentFilterList option status");
		return CommonPopupParentFilterList.get(idx).isSelected();
	}

	public boolean getgeographyPopupUASStates_California_cbk_status() {
		extentTest.log(Status.INFO, "Get geographyPopupUASStates_California_cbk status");
		return geographyPopupUASStates_California_cbk.isSelected();
	}

	public boolean IsFindIn_LHS_CbkList_Enabled(int idx) {
		extentTest.log(Status.INFO, "Check FindIn checkbox " + idx + " is Enabled");
		return FindIn_LHS_CbkList.get(idx).isEnabled();
	}

	public boolean Are_trackingListFacetListOptionsVisible_AsPerTheLicenses(int size) {
		extentTest.log(Status.INFO, "Check trackingListFacetListOptionsVisible_AsPerTheLicenses");
		for (int i = 0; i < size; i++) {
			if (SeleniumUtils.isVisible(trackingListFacetList.get(i), driver)) {
				return true;
			}
		}
		return false;
	}

	public boolean Check_CommonPopupParentFilter_lblList(String Expected) {
		extentTest.log(Status.INFO, "Check the " + Expected + " in the list");
		int size = CommonPopupParentFilter_lblList.size();
		for (int i = 0; i < size; i++) {
			if (CommonPopupParentFilter_lblList.get(i).getText().equals(Expected)) {
				return true;
			}
		}
		return false;
	}

	public boolean IsProjGrp_scrollbar_verticalDragger_Displayed() {
		extentTest.log(Status.INFO, "IsProjGrp_scrollbar_verticalDragger_Displayed");
		return SeleniumUtils.isVisible(ProjGrp_scrollbar_verticalDragger, driver);
	}

	public void ClickOngeographyPopupUASStates_California_cbk() {
		extentTest.log(Status.INFO, "Click on geographyPopupUASStates_California_cbk");
		geographyPopupUASStates_California_cbk.click();
	}

	public boolean getStateRegionFilterList_FirstOptionStatus(int idx) {
		extentTest.log(Status.INFO, "Check the status of " + idx + " checkbox");
		return StateRegionFilterList.get(idx).isSelected();
	}

	public String GetLHS_Filters_lbl(int idx) {
		extentTest.log(Status.INFO, "Verify the order of top 3 filters");
		extentTest.log(Status.INFO, "Get LHF " + idx + " filter label");
		return LHS_Filters_lblList.get(idx).getText();
	}

	public void ClickOnspecDivisionFilterListPopup_LastOption() {
		extentTest.log(Status.INFO, "Click on the Addenda checkbox");
		int size = specDivisionFilterListPopup.size();
		specDivisionFilterListPopup.get(size - 1).click();
	}

	public void unselectAllOptionFromSpecDivision() {
		unselectMultipleOptionFromTheList(specDivisionFilterCheckboxListPopup.size(),
				specDivisionFilterCheckboxListPopup);
	}

	private void unselectMultipleOptionFromTheList(int cout, List<WebElement> eList) {
		extentTest.log(Status.INFO, "Unselect multiple options from the list");
		int count = 1;
		WebElement optionLabel = null;
		for (WebElement ParentFilter : eList) {
			if (count <= cout) {
				CommonUtils.scrollDownTillElement(ParentFilter, driver);
				if (ParentFilter.isSelected()) {
					optionLabel = ParentFilter.findElement(By.xpath("//following-sibling::label//span"));
					CommonUtils.scrollDownTillElement(optionLabel, driver);
					optionLabel.click();
				}
				count++;
			}
			if (count > cout) {
				break;
			}
		}
	}

	public boolean isShowMoreFilterVisible() {
		extentTest.log(Status.INFO, "Check is Show More Filter option is visible");
		return SeleniumUtils.isVisible(CommonShowMoreFiltes, driver);
	}

	public boolean IsProjectGrups_Filter_ArrowUp_Visible() {
		extentTest.log(Status.INFO, "Check ProjectGrups_Filter_ArrowUp is visible");
		return SeleniumUtils.isVisible(ProjectGrups_Filter_ArrowUp, driver);
	}

	public boolean Check_FilterSelectionsShouldAppearHorizontally() {

		extentTest.log(Status.INFO,
				"Ensure that the selections should appear horizontally next to each other in filter crumb area based on user selection");
		Point firstCrumb = FilterCrumb_AppliedFilter_List.get(0).getLocation();
		int FristCrumbXcoordi = firstCrumb.getX();
		int FristCrumbYcoordi = firstCrumb.getY();
		Point secondCrumb = FilterCrumb_AppliedFilter_List.get(1).getLocation();
		int SecondCrumbXcoordi = secondCrumb.getX();
		int SecondCrumbYcoordi = secondCrumb.getY();
		if (FristCrumbYcoordi == SecondCrumbYcoordi && FristCrumbXcoordi != SecondCrumbXcoordi) {
			return true;
		}
		return false;
	}

	public boolean verifyProjectTypeSorting(boolean order) {
		extentTest.log(Status.INFO, "Ensure that the projects are sorted based on Project Type");
		List<String> listElements = CommonUtils.getListFromWebElements(projectType);
		List<String> listArc = CommonUtils.sortWebElements(listElements, order);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyactionStageSorting(boolean order) {
		extentTest.log(Status.INFO, "Ensure that the projects are sorted based on Action Stage");
		List<String> listElements = CommonUtils.getListFromWebElements(actionStage);
		List<String> listArc = CommonUtils.sortWebElements(listElements, order);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean isProjectTitleWithNotesPresent() {
		extentTest.log(Status.INFO, "Check if a project title is present having Notes.");
		if (projectTitlesWithNotes.size() > 0)
			return true;
		return false;
	}

	public boolean isBiddersLinkDisabled() {
		extentTest.log(Status.INFO, "Verify if the bidders link is disabled");
		try {
			return lnkBidders.get(0).getAttribute("class").trim().equalsIgnoreCase("disabledLink");
		} catch (Exception ex) {
			return false;
		}
	}

	public int getExactProjectCount() {
		String count = getProjectResultsCount();
		String[] parts = count.split("f");
		String part2 = parts[1];
		return Integer.parseInt(part2.replace(" ", "").replace(",", ""));
	}

	public boolean compareTwoNumbers(int before, int after) {
		if (before > after) {
			return true;
		}
		return false;
	}

	public int get_LHS_FilterWiseProjectCount(int idx, String ParentList) throws InterruptedException {
		int i = 0;
		switch (ParentList) {
		case "Trades_LHS_ProjectwiseCountList":
			return getProjecCount(idx, Trades_LHS_ProjectwiseCountList);
		case "SpecificTrades_LHS_ProjectwiseCountList":
			return getProjecCount(idx, SpecificTrades_LHS_ProjectwiseCountList);
		case "ProjectGroupsLHS_LabelList":
			return getProjecCount(idx, ProjectGroupsLHS_LabelList);
		case "ProjectTypesLHS_LabelList":
			return getProjecCount(idx, ProjectTypesLHS_LabelList);
		case "ACTION_STAGE_CATEGORYLHS_LabelList":
			return getProjecCount(idx, ACTION_STAGE_CATEGORYLHS_LabelList);
		case "materialsEquipLHS_LabelList":
			return getProjecCount(idx, materialsEquipLHS_LabelList);
		case "materialsEquip2LHS_LabelList":
			return getProjecCount(idx, materialsEquip2LHS_LabelList);
		case "biddingWithinLHS_LabelList":
			return getProjecCount(idx, biddingWithinLHS_LabelList);
		case "GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List":
			return getProjecCount(idx, GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List);
		}
		return i;
	}

	public List<String> get_LHS_FilterWiseLabelList(String ParentList) throws InterruptedException {
		List<String> filterLabelList = new ArrayList<>();
		switch (ParentList) {
		case "Trades_LHS_ProjectwiseCountList":
			filterLabelList = CommonUtils.getListFromWebElements(Trades_LHS_ProjectwiseCountList);
			break;
		case "SpecificTrades_LHS_ProjectwiseCountList":
			filterLabelList = CommonUtils.getListFromWebElements(SpecificTrades_LHS_ProjectwiseCountList);
			break;
		case "ProjectGroupsLHS_LabelList":
			filterLabelList = CommonUtils.getListFromWebElements(ProjectGroupsLHS_LabelList);
			break;
		case "ProjectTypesLHS_LabelList":
			filterLabelList = CommonUtils.getListFromWebElements(ProjectTypesLHS_LabelList);
			break;
		case "ACTION_STAGE_CATEGORYLHS_LabelList":
			filterLabelList = CommonUtils.getListFromWebElements(ACTION_STAGE_CATEGORYLHS_LabelList);
			break;
		case "materialsEquipLHS_LabelList":
			filterLabelList = CommonUtils.getListFromWebElements(materialsEquipLHS_LabelList);
			break;
		case "materialsEquip2LHS_LabelList":
			filterLabelList = CommonUtils.getListFromWebElements(materialsEquip2LHS_LabelList);
			break;
		case "biddingWithinLHS_LabelList":
			filterLabelList = CommonUtils.getListFromWebElements(biddingWithinLHS_LabelList);
			break;
		case "GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List":
			filterLabelList = CommonUtils.getListFromWebElements(GEOGRAPHY_COUNTRY_ProjectCountNextToElement_List);
			break;
		}
		return filterLabelList;
	}

	public int getProjecCount(int idx, List<WebElement> eList) {
		return Integer.parseInt(eList.get(idx).getText().replaceAll("\\D+", ""));
	}

	public String getFilterCrumb_AppliedFilter_TooltipText(int CrumbIdx, String title) throws InterruptedException {
		WebElement CrumbfilterNumber = FilterCrumb_AppliedFilter_TitleList.get(CrumbIdx);
		Actions ToolTip = new Actions(driver);
		SeleniumUtils.scrollToView(driver, CrumbfilterNumber);
		ToolTip.clickAndHold(CrumbfilterNumber).build().perform();
		// waiting for dynamic div which fetches tooltip data to load
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.attributeContains(CrumbfilterNumber, "title", title));
		String ToolTipText = CrumbfilterNumber.getAttribute("title");
		return ToolTipText;
	}

	public String get_LHS_OptionFilterTitle(int idx, String ParentList) throws InterruptedException {
		String str = "empty";
		switch (ParentList) {
		case "Trades_LHS_ProjectwiseTitleList":
			String temp = get_LHS_FilterTitl(idx, Trades_LHS_ProjectwiseTitleList);
			String[] parts = temp.split("\\(");
			String part2 = parts[0];
			return part2.replace(" ", "");

		}
		return str;
	}

	public String get_LHS_FilterTitl(int idx, List<WebElement> eList) {
		return eList.get(idx).getText();
	}

	public boolean checkExpectedOptionIsDisplayedIn_CommonPopupParentFilter_lblList(String Expected) {
		extentTest.log(Status.INFO, "Check" + Expected + " this option is displayed");
		return CommonUtils.checkExpectedStringContainInCompleteList(Expected, CommonPopupParentFilter_lblList);
	}

	public String getGEOGRAPHY_COUNTRY_LHS_lbl(int idx) {
		extentTest.log(Status.INFO, "Get option label from GEOGRAPHY_COUNTRY_LHS_lbl_list");
		String temp = GEOGRAPHY_COUNTRY_LHS_lbl_list.get(idx).getText();
		String[] parts = temp.split("\\(");
		String part2 = parts[0];
		return part2.replace(" ", "");
	}

	public boolean StateRegion_scrollbar_verticalDragger_displayed() {
		extentTest.log(Status.INFO, "Check StateRegion_scrollbar_verticalDragger is displayed");
		return StateRegion_scrollbar_verticalDragger.isDisplayed();
	}

	public boolean SpecDivision_scrollbar_verticalDragger_displayed() {
		extentTest.log(Status.INFO, "Check SpecDivision_scrollbar_verticalDragger is displayed");
		return SpecDivision_scrollbar_verticalDragger.isDisplayed();
	}

	public boolean SpecialCondi_scrollbar_verticalDragger_displayed() {
		extentTest.log(Status.INFO, "Check SpecialCondi_scrollbar_verticalDragger is displayed");
		return SpecialCondi_scrollbar_verticalDragger.isDisplayed();
	}

	public String getACTION_STAGE_CODELHS_Label(int idx) {
		extentTest.log(Status.INFO, "Get option label from ACTION_STAGE_CODELHS_LabelList");
		return ACTION_STAGE_CODELHS_LabelList.get(idx).getText();
	}

	public String getSpecificTrades_LHS_ProjectwiseTitleList(int idx) {
		extentTest.log(Status.INFO, "Get option label from SpecificTrades_LHS_ProjectwiseTitleList");
		return SpecificTrades_LHS_ProjectwiseTitleList.get(idx).getText();
	}

	public String getmaterialsEquip2LHS_LabelList(int idx) {
		extentTest.log(Status.INFO, "Get option label from materialsEquip2LHS_LabelList");
		return materialsEquip2LHS_LabelList.get(idx).getText();
	}

	public String getAppliedcrumb_txt(int idx) {
		extentTest.log(Status.INFO, "Get the" + idx + " applied filter text");
		clickOnCommonShowMoreFiltes_Arrow();
		return FilterCrumb_AppliedFilter_List.get(idx).getText();
	}

	public void ClickOncrumbFilterClose(int idx) {
		extentTest.log(Status.INFO, "Remove " + idx + " crumb filter");
		FilterCrumb_AllClose_btn.get(idx).click();
		waitforLoadingRing();
	}

	public boolean checkExpectedOptionIsDisplayedIn_ProjTypePopup_subSection_LabelList(String Expected) {
		extentTest.log(Status.INFO, "Check" + Expected + " this option is displayed");
		return CommonUtils.checkExpectedStringContainInCompleteList(Expected, ProjTypePopup_subSection_LabelList);
	}

	public void ClickOnProjGrpPopupCustomHomesyChk() {
		extentTest.log(Status.INFO, "Click on ProjGrpPopupCustomHomesyChk");
		ProjGrpPopupCustomHomesyChk.click();
	}

	public int getProjGrpPopupCustomHomes_Count() {
		extentTest.log(Status.INFO, "Get level 2 ProjGrpPopupCustomHomesyChk count");
		return Integer.parseInt(ProjGrpPopupCustomHomesLbl.getText().replaceAll("\\D+", ""));
	}

	public ProjectPage openProjectPageInNewTab(int projectIndex) {
		extentTest.log(Status.INFO, "Open Project page in new tab by clicking on the project index.");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.CONTROL).click(projectTitleList.get(projectIndex)).keyUp(Keys.CONTROL).build().perform();
		return new ProjectPage(driver);
	}

	public Set<String> getWindowHandles() {
		extentTest.log(Status.INFO, "Returns all window handles.");
		return driver.getWindowHandles();
	}

	public String getCurrentWindowHandle() {
		extentTest.log(Status.INFO, "Returns current window handle.");
		return driver.getWindowHandle();
	}

	public void switchTab(String windowHandle) {
		extentTest.log(Status.INFO, "Switch tab.");
		driver.switchTo().defaultContent();
		driver.switchTo().window(windowHandle);
	}

	public List<String> getAppliedFilterTexts() {
		List<String> appliedFilterTextsStr = new ArrayList<>();
		for (WebElement appliedFilterText : appliedFilterTexts) {
			appliedFilterTextsStr.add(appliedFilterText.getText());
		}
		return appliedFilterTextsStr;
	}

	public void selectProjectTitleWithNotes() {

	}

	public void ClickOnvalMaximumDropdownEight_thOption() {
		extentTest.log(Status.INFO, "Select 8th option from max dropdown");
		valMaximumDropdown.click();
		valMaximumDropdownEightOption.click();
		waitforLoadingRing();

	}

	public boolean checklnkProjDocHighilight() {
		extentTest.log(Status.INFO, "Ensure that the project result documents are highlighted");
		return CommonUtils.verifyListSize(lnkProjDocHighilight);
	}

	public Integer getTrackedProjectCount() {
		return myProjectTrackingNameResultList.size();
	}

	// Switch the control to new tab where project is download and return the
	// page object.
	public Download switchToDownloadNewTab() {
		extentTest.log(Status.INFO, "Switch the control to new tab where project is downloaded.");
		CommonUtils.switchToNewTab(driver);
		return new Download(driver);
	}

	// Navigate to specific url
	public ProjectPage navigateToProjectPageURL(String url) {
		extentTest.log(Status.INFO, "Navigate to specific url : " + url);
		driver.navigate().to(url);
		return new ProjectPage(driver);
	}

	public String get_AppliedFilter_lbl(int idx) {
		extentTest.log(Status.INFO, "Get applied filter " + idx + " text");
		return FilterCrumb_AppliedFilter_TitleList.get(idx).getText();
	}

	public String get_LHS_Filter_lbl(int idx, String ParentList) throws InterruptedException {
		String str = "null";
		switch (ParentList) {
		case "GEOGRAPHY_COUNTRY_LHS_lbl_list":
			return get_LHS_lbl(idx, GEOGRAPHY_COUNTRY_LHS_lbl_list);
		case "ACTION_STAGE_CATEGORYLHS_LabelList":
			return get_LHS_lbl(idx, ACTION_STAGE_CATEGORYLHS_LabelList);
		case "OWNERSHIP_TYPEFilter_LHS_lblList":
			return get_LHS_lbl(idx, OWNERSHIP_TYPEFilter_LHS_lblList);
		case "CONSTRUCTION_TYPEFilter_LHS_lblList":
			return get_LHS_lbl(idx, CONSTRUCTION_TYPEFilter_LHS_lblList);
		}
		return str;
	}

	public String get_LHS_lbl(int idx, List<WebElement> eList) {
		extentTest.log(Status.INFO, "Get label of LHS filter");
		String temp = eList.get(idx).getText();
		String[] parts = temp.split("\\(");
		String part2 = parts[0];
		return part2.replace(" ", "");
	}

	// Verifying the date is in required format
	public boolean isValidDateFormate(String inDate, String dateFormate) {
		extentTest.log(Status.INFO, "Verifying the date is in required format: " + dateFormate);
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// Check DesignAlert search checkbox
	public void checkDesignAlertFindInFilter() {
		extentTest.log(Status.INFO, "Check the DesignAlert find in filter ");
		if (!designalertFindInCheckbox.isSelected()) {
			designalertFindInCheckbox.click();
			SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
			SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		}
	}

	public void uncheckSpecsFindInFilter() {
		extentTest.log(Status.INFO, "Uncheck the Specs find in filter ");
		if (specsFindInCheckbox.isSelected()) {
			specsFindInCheckbox.click();
			SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
			SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		}
	}

	// Uncheck News search checkbox
	public void uncheckNewsFindInFilter() {
		extentTest.log(Status.INFO, "Uncheck the News find in filter ");
		if (newsFindInCheckbox.isSelected()) {
			newsFindInCheckbox.click();
			SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
			SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		}
	}

	// Uncheck Plans search checkbox
	public void uncheckPlansFindInFilter() {
		extentTest.log(Status.INFO, "Uncheck the Plans find in filter ");
		if (plansFindInCheckbox.isSelected()) {
			plansFindInCheckbox.click();
			SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
			SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		}
	}

	// click on News search dropdown.
	public void clickOnNewsSearchDropdownOption() {
		extentTest.log(Status.INFO, "click on News search dropdown.");
		NewsSeachLst.click();
	}

	// click on News search dropdown.
	public void clickOnProjectDetailTextNewsSearchDropdownOption() {
		extentTest.log(Status.INFO, "click on project Detail Text News Seach List Option.");
		projectDetailTextNewsSeachLstOption.click();
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
	}

	public void ClickOn_CommonPopupParent_cbk(int idx) {
		driver.findElement(By.xpath("//input[@class='" + idx + "' and @type='checkbox' and @parent='']")).click();
	}

	public boolean IsZeroProjCountElement_Displayed_Common_Popup_allSubOptionslbl() {
		int j = 0;
		for (int i = 0; i < Common_Popup_allSubOptionslbl.size(); i++) {
			String string = Common_Popup_allSubOptionslbl.get(i).getText();
			String[] parts = string.split("\\(");
			String part2 = parts[parts.length - 1];
			j = Integer.parseInt(part2.replaceAll("\\D+", ""));
			if (j == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean Check_ListOption_Selecteded(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check list options are visible");
		boolean isSelected = false;
		switch (ParentList) {
		case "materialsEquip_LHS_FilterList":
			isSelected = ListOptionSelected(materialsEquip_LHS_FilterList);
			break;
		}
		return isSelected;
	}

	public boolean Check_PDS_Filters_See_More_Selecteded(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check list options are visible");
		boolean isSelected = false;
		switch (ParentList) {
		case "pdsSeeMorePopUpListChkBox":
			isSelected = ListOptionSelected(pdsSeeMorePopUpListChkBox);
			break;
		}
		return isSelected;
	}

	public boolean ListOptionSelected(List<WebElement> Checkboxlist) {

		for (int i = 0; i < Checkboxlist.size(); i++) {
			if (Checkboxlist.get(i).isSelected()) {
				return true;
			}
		}
		return false;
	}

	public boolean IsACTION_STAGE_CATEGORY_Filter_Displayed() {
		extentTest.log(Status.INFO, "Check to see ACTION_STAGE_CATEGORY_Filter displayed");
		return ACTION_STAGE_CATEGORY_Filter.isDisplayed();
	}

	public boolean IsPROJECT_TYPE_CATEGORY_Filter_Displayed() {
		extentTest.log(Status.INFO, "Check to see PROJECT_TYPE_CATEGORY_Filter displayed");
		return PROJECT_TYPE_CATEGORY_Filter.isDisplayed();
	}

	public boolean IsspecAlertFilter_Displayed() {
		extentTest.log(Status.INFO, "Check to see specAlertFilter displayed");
		return specAlertFilter.isDisplayed();
	}

	public boolean IsCONSTRUCTION_TYPEFilter_Displayed() {
		extentTest.log(Status.INFO, "Check to see CONSTRUCTION_TYPEFilter displayed");
		return CONSTRUCTION_TYPEFilter.isDisplayed();
	}

	public boolean IsOWNERSHIP_TYPEFilter_Displayed() {
		extentTest.log(Status.INFO, "Check to see OWNERSHIP_TYPEFilter displayed");
		return OWNERSHIP_TYPEFilter.isDisplayed();
	}

	public boolean IsSTRUCTURAL_PROPERTIESFilter_Displayed() {
		extentTest.log(Status.INFO, "Check to see STRUCTURAL_PROPERTIESFilter displayed");
		return STRUCTURAL_PROPERTIESFilter.isDisplayed();
	}

	// Get the DR Number numeric value on Project Page.
	public String getDRNumerOnly() {
		extentTest.log(Status.INFO, "Get the DR Number numeric value on Project Page.");
		SeleniumUtils.isVisible(projectDRNum, driver);
		return projectDRNum.getText().trim().split(" ")[0];
	}

	public boolean Geography_scrollbar_verticalDragger_displayed() {
		extentTest.log(Status.INFO, "Check Geography_scrollbar_verticalDragger is displayed");
		return Geography_scrollbar_verticalDragger.isDisplayed();
	}

	public boolean IsCommonPopupHideZeroProjects_cbk_Selected() {
		extentTest.log(Status.INFO, "Check Hide Zero Projects checkbox selected");
		return CommonPopupHideZeroProjects_cbk.isSelected();
	}

	public void Check_AllLevel_1_cbks() {
		extentTest.log(Status.INFO, "Check all level 1 checkboxes");
		for (int i = 0; i < CommonPopupParentFilterList.size(); i++) {
			CommonPopupParentFilterList.get(i).click();
		}
	}

	public boolean ISPopupSelectAll_cbk_Selected() {
		extentTest.log(Status.INFO, "Check SelectAll checkbox displayed");
		return PopupSelectAll_cbk.isSelected();
	}

	public void DragAndDrop_MySearches_Additionalfilters() {
		extentTest.log(Status.INFO, "Check draganddrop of webelements in My searches to additionaFilters");
		Actions action = new Actions(driver);
		action.dragAndDrop(REPORTSFilter, InAdditionalFilter).build().perform();
		action.dragAndDrop(PublishRangeFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(FIND_INFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(materialsEquipFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(STRUCTURAL_PROPERTIESFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(specialFilter.get(0), REPORTSFilter).build().perform();
		action.dragAndDrop(geographyFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(StateRegionFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(biddingWithinFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(specDivisionFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(ACTION_STAGE_CATEGORY_Filter, REPORTSFilter).build().perform();
		action.dragAndDrop(PROJECT_TYPE_CATEGORY_Filter, REPORTSFilter).build().perform();
		action.dragAndDrop(trackingListFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(ValuationFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(CONSTRUCTION_TYPEFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(OWNERSHIP_TYPEFilter, REPORTSFilter).build().perform();
		action.dragAndDrop(specialConditionsFilter, REPORTSFilter).build().perform();

	}

	public void DragAndDrop_Additionalfilters_MySearches() {
		extentTest.log(Status.INFO, "Check draganddrop of webelements from additionaFilters to My searches ");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mySearchesDropDown));
		Actions action = new Actions(driver);
		action.dragAndDrop(REPORTSFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(PublishRangeFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(FIND_INFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(materialsEquipFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(STRUCTURAL_PROPERTIESFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(specialFilter.get(0), TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(geographyFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(StateRegionFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(biddingWithinFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(specDivisionFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(ACTION_STAGE_CATEGORY_Filter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(PROJECT_TYPE_CATEGORY_Filter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(trackingListFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(ValuationFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(CONSTRUCTION_TYPEFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(OWNERSHIP_TYPEFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
		action.dragAndDrop(specialConditionsFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();

	}

	public void DragAndDrop_geographyFilter_Additionalfilters_MySearches() {
		extentTest.log(Status.INFO, "Check draganddrop of geographyFilter in My searches to additionaFilters");
		Actions action = new Actions(driver);
		action.dragAndDrop(geographyFilter, TRADE_TYPE_CATEGORY_Filter).build().perform();
	}

	public void DragAndDrop_StateRegionFilter_Additionalfilters_MySearches() {
		extentTest.log(Status.INFO, "Check draganddrop of StateRegionFilter in My searches to additionaFilters");
		Actions action = new Actions(driver);
		action.dragAndDrop(StateRegionFilter, AdditionalFilters).build().perform();
	}

	public void DragAndDrop_ACTION_STAGE_CATEGORY_Filter_Additionalfilters_MySearches() {
		extentTest.log(Status.INFO,
				"Check draganddrop of ACTION_STAGE_CATEGORY_Filter in My searches to additionaFilters");
		Actions action = new Actions(driver);
		action.dragAndDrop(ACTION_STAGE_CATEGORY_Filter, AdditionalFilters).build().perform();
	}

	public void DragAndDrop_ProjectDelivarySystem_Filter_Additionalfilters_MySearches() {
		extentTest.log(Status.INFO,
				"Check draganddrop of Project Delivary System Filter in My searches to additionaFilters");
		Actions action = new Actions(driver);
		action.dragAndDrop(ProjectDeliveryFilter, AdditionalFilters).build().perform();
	}

	public void DragAndDrop_PROJECT_TYPE_CATEGORY_Filter_Additionalfilters_MySearches() {
		extentTest.log(Status.INFO,
				"Check draganddrop of PROJECT_TYPE_CATEGORY_Filter in My searches to additionaFilters");
		Actions action = new Actions(driver);
		action.dragAndDrop(PROJECT_TYPE_CATEGORY_Filter, AdditionalFilters).build().perform();
	}

	public void DragAndDrop_geographyFilter_MySearches_Additionalfilters() {
		extentTest.log(Status.INFO, "Check draganddrop of geographyFilter from Additionalfilters to MySearches");
		Actions action = new Actions(driver);
		action.dragAndDrop(geographyFilter, mySearchesDropDown).build().perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mySearchesDropDown));
		action.dragAndDrop(REPORTSFilter, StateRegionFilter).build().perform();
		action.dragAndDrop(PublishRangeFilter, biddingWithinFilter).build().perform();
		action.dragAndDrop(FIND_INFilter, biddingWithinFilter).build().perform();
		action.dragAndDrop(materialsEquipFilter, PublishRangeFilter).build().perform();
		action.dragAndDrop(STRUCTURAL_PROPERTIESFilter, PublishRangeFilter).build().perform();
		action.dragAndDrop(specialFilter.get(0), biddingWithinFilter).build().perform();
		action.dragAndDrop(geographyFilter, StateRegionFilter).build().perform();
		action.dragAndDrop(StateRegionFilter, PublishRangeFilter).build().perform();
		action.dragAndDrop(biddingWithinFilter, biddingWithinFilter).build().perform();
		action.dragAndDrop(specDivisionFilter, geographyFilter).build().perform();
		action.dragAndDrop(ACTION_STAGE_CATEGORY_Filter, specialFilter.get(0)).build().perform();
		action.dragAndDrop(PROJECT_TYPE_CATEGORY_Filter, STRUCTURAL_PROPERTIESFilter).build().perform();
		action.dragAndDrop(trackingListFilter, FIND_INFilter).build().perform();
		action.dragAndDrop(ValuationFilter, StateRegionFilter).build().perform();
		action.dragAndDrop(CONSTRUCTION_TYPEFilter, biddingWithinFilter).build().perform();
		action.dragAndDrop(OWNERSHIP_TYPEFilter, specDivisionFilter).build().perform();
		action.dragAndDrop(specialConditionsFilter, StateRegionFilter).build().perform();
		action.dragAndDrop(TRADE_TYPE_CATEGORY_Filter, biddingWithinFilter).build().perform();
	}

	public void ClickOnFilter() {
		if (CommonUtils.checkElementExist(biddingWithinFilterArrowDownIcon, driver)) {
			biddingWithinFilter.click();
			waitforLoadingRing();
			extentTest.log(Status.INFO, " Click on filter");
			Filter.sendKeys(Keys.ENTER);
		}
	}

	public boolean IsREPORTSFilterr_Displayed() {
		if (CommonUtils.checkElementExist(REPORTSFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see REPORTSFilter displayed");
			return REPORTSFilter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean IsFIND_INFilter_Displayed() {
		if (CommonUtils.checkElementExist(FIND_INFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see FIND_INFilter displayed");
			return FIND_INFilter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean IsStateRegionFilter_Displayed() {
		if (CommonUtils.checkElementExist(StateRegionFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see StateRegionFilter displayed");
			return StateRegionFilter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean IsbiddingWithinFilter_Displayed() {
		if (CommonUtils.checkElementExist(biddingWithinFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see biddingWithinFilter displayed");
			return biddingWithinFilter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean IstrackingListFilter_Displayed() {
		if (CommonUtils.checkElementExist(trackingListFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see trackingListFilter displayed");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackingListFilter));
			return trackingListFilter.isDisplayed();
		}
		return false;
	}

	public void clickOnProjPublishDateFilter() {
		extentTest.log(Status.INFO, "Click on the ProjectLocationFilter");
		if (CommonUtils.checkElementExist(result, driver)) {
			result.click();
			clickOn3MonthPrior.click();
			waitforLoadingRing();
		}
	}

	public boolean IsValuationFilter_Displayed() {
		if (CommonUtils.checkElementExist(ValuationFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see ValuationFilter displayed");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ValuationFilter));
			return ValuationFilter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean IsspecialConditionsFilter_Displayed() {
		if (CommonUtils.checkElementExist(specialConditionsFilter, driver)) {
			extentTest.log(Status.INFO, "Check to see specialConditionsFilter displayed");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(specialConditionsFilter));
			return specialConditionsFilter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean IsTRADE_TYPE_CATEGORY_Filter_Displayed() {
		if (CommonUtils.checkElementExist(TRADE_TYPE_CATEGORY_Filter, driver)) {
			extentTest.log(Status.INFO, "Check to see TRADE_TYPE_CATEGORY_Filter displayed");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(TRADE_TYPE_CATEGORY_Filter));
			return TRADE_TYPE_CATEGORY_Filter.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean checkScrollExistifWidthLess1024() {
		extentTest.log(Status.INFO,
				"Verify if browser display width is less than 1024px, then a horizontal scroll-bar appears.");
		Dimension initial_size = driver.manage().window().getSize();
		int width = initial_size.getWidth();
		if (width < 1024) {
			return (boolean) ((JavascriptExecutor) driver).executeScript(
					"return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
		} else {
			return true;
		}
	}

	public void mySearchesSavedSearchSearchesDropDown() {
		extentTest.log(Status.INFO, "Click on Tracking List option present in My Searches dropDown");
		mySearchesSavedSearch.click();

	}

	public String getDownloadFileName() {
		return savedSingleFileTextField.getAttribute("value");
	}

	public VisualizationPage clickDashboard1ToggleButton() {
		dashboard1ToggleButton.click();
		return new VisualizationPage(driver);
	}

	public boolean isFinfLnDisplayed() {
		extentTest.log(Status.INFO, "find ln is  not displayed On CompanyResultPage");
		return filterln.isDisplayed();

	}

	public boolean isPublishedRangeDisplayed() {
		extentTest.log(Status.INFO, "find ln is  not displayed On CompanyResultPage");
		return publishedRange.isDisplayed();

	}

	public void clickOnProjectGroupTab() {
		extentTest.log(Status.INFO, "Click on the ProjectLocationFilter");
		if (CommonUtils.checkElementExist(ProjectType_Filter_ArrowUp, driver)) {
			expandProjectGroup.click();
		}
	}

	public void clickOnShowMore() {
		extentTest.log(Status.INFO, "Click On Sort DropDown button");
		showMoreControl.click();
	}

	public ProjectFirmsPage clickOnSpecLink() {
		extentTest.log(Status.INFO, "Clicking on firm Link");
		CommonUtils.scrollDownTillElement(specLink, driver);
		specLink.click();
		return new ProjectFirmsPage(driver);
	}

	public void clickOnPublishDateFilter() {
		result.click();
		clickOn3MonthPrior.click();
	}

	public void clickDashboard2ToggleBtn() {
		extentTest.log(Status.INFO, "Click the Dashboard 2 visualization button");
		if (!isDashboard2TogglebuttonSelected()) {
			SeleniumUtils.isClickable(Dashboard2Btn, driver);
			Dashboard2Btn.click();
		}
	}

	public boolean checkHideFilterShowWithoutFilters() {
		if (SeleniumUtils.isVisible(geographyFilter, driver)) {
			hideFilterBtn.click();
		}
		return SeleniumUtils.isVisible(geographyFilter, driver);
	}

	// Check if My Searches drop down is vertically-aligned with the right
	// border of the left-nav filter
	public boolean isMySearchesVerticallyAlignedWithLeftNav() {
		extentTest.log(Status.INFO,
				"Check if right border of the 'My Searches' button is vertically-aligned with the right border of the left-nav filter");

		if (dropDownListBtn.getLocation().getX() == leftNavFilter.getLocation().getX()) {
			if (dropDownListBtn.getSize().getWidth() == leftNavFilter.getSize().getWidth()) {
				return true;
			}
		}
		return false;
	}

	// Check if keyword search text box is left-aligned (with the left-border
	// pagination header)
	public boolean isKeywordSearchTextBoxLeftAligned() {
		extentTest.log(Status.INFO, "Check if keyword search text box is left-aligned ");

		return (keywordSearchTextfieldContainer.getLocation().getX() == filterCrumbDrawer.getLocation().getX());
	}

	public String getSelectedVisualizationFromLeftSelectionView() {
		extentTest.log(Status.INFO, "Getting selected visualization from left selection view.");
		String selectedVisualization = "";
		for (WebElement visualization : leftSelectionView) {
			if (visualization.getAttribute("style").contains("opacity: 0.5")) {
				selectedVisualization = visualization.getText();
			}
		}
		return selectedVisualization;
	}

	public String getSelectedVisualizationFromRightSelectionView() {
		extentTest.log(Status.INFO, "Getting selected visualization from right selection view.");
		String selectedVisualization = "";
		for (WebElement visualization : rightSelectionView) {
			if (visualization.getAttribute("style").contains("opacity: 0.5")) {
				selectedVisualization = visualization.getText();
			}
		}
		return selectedVisualization;
	}

	public void DragAndDrop_StateRegionFilter_MySearches_Additionalfilters() {
		extentTest.log(Status.INFO, "Check draganddrop of StateRegionFilter from Additionalfilters to MySearches");
		Actions action = new Actions(driver);
		action.dragAndDrop(StateRegionFilter, mySearchesDropDown).build().perform();
	}

	public void DragAndDrop_ProjectDelivarySystem_Filter_MySearches_Additionalfilters() {
		extentTest.log(Status.INFO,
				"Check draganddrop of Project Delivary System from Additionalfilters to MySearches");
		Actions action = new Actions(driver);
		action.dragAndDrop(ProjectDeliveryFilter, mySearchesDropDown).build().perform();
	}

	public void DragAndDrop_ACTION_STAGE_CATEGORY_Filter_MySearches_Additionalfilters() {
		extentTest.log(Status.INFO,
				"Check draganddrop of ACTION_STAGE_CATEGORY_Filter from Additionalfilters to MySearches");
		Actions action = new Actions(driver);
		action.dragAndDrop(ACTION_STAGE_CATEGORY_Filter, mySearchesDropDown).build().perform();
	}

	public void DragAndDrop_PROJECT_TYPE_CATEGORY_Filter_MySearches_Additionalfilters() {
		extentTest.log(Status.INFO,
				"Check draganddrop of PROJECT_TYPE_CATEGORY_Filter from Additionalfilters to MySearches");
		Actions action = new Actions(driver);
		action.dragAndDrop(PROJECT_TYPE_CATEGORY_Filter, mySearchesDropDown).build().perform();
	}

	public void DragAndDrop_ChangeOrder_MySearches() {
		extentTest.log(Status.INFO, "Check draganddrop of webelements in My searches");
		Actions action = new Actions(driver);
		action.dragAndDrop(REPORTSFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(PublishRangeFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(FIND_INFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(materialsEquipFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(STRUCTURAL_PROPERTIESFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(specialFilter.get(0), mySearchesDropDown).build().perform();
		action.dragAndDrop(geographyFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(StateRegionFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(biddingWithinFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(specDivisionFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(ACTION_STAGE_CATEGORY_Filter, mySearchesDropDown).build().perform();
		action.dragAndDrop(PROJECT_TYPE_CATEGORY_Filter, mySearchesDropDown).build().perform();
		action.dragAndDrop(trackingListFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(ValuationFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(CONSTRUCTION_TYPEFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(OWNERSHIP_TYPEFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(specialConditionsFilter, mySearchesDropDown).build().perform();
		action.dragAndDrop(TRADE_TYPE_CATEGORY_Filter, mySearchesDropDown).build().perform();

	}

	public List<String> getLeftLegendList() {
		extentTest.log(Status.INFO, "Getting legend list from left selection view.");
		return CommonUtils.getListFromWebElements(leftLegendList);
	}

	public ProjectResultsPage selectVisualizationFromLeftSelectionView(String visualizationToBeSelected) {
		extentTest.log(Status.INFO, "Selecting visualization from left selection view.");
		for (WebElement visualization : leftSelectionView) {
			if (visualizationToBeSelected.equalsIgnoreCase(visualization.getText())) {
				visualization.click();
			}
		}
		return new ProjectResultsPage(driver);
	}

	public void clickOnDonutForPrivate() {
		extentTest.log(Status.INFO, "Clicking on donut for Private.");
		donutForPrivate.click();
	}

	public void clickOnExpandIcon() {
		extentTest.log(Status.INFO, "Clicking on expand icon");
		expandIcon.click();
	}

	public void clickOnExpandIconLeftChart() {
		extentTest.log(Status.INFO, "Clicking on expand icon Left Chart");
		expandIconOnLeftChart.click();
	}

	public void clickOnExpandIconRightChart() {
		extentTest.log(Status.INFO, "Clicking on expand icon on Right Chart");
		expandIconOnRightChart.click();
	}

	public boolean isExpandIconOnLeftChartDisplayed() {
		extentTest.log(Status.INFO, "Verify expand icon on left chart view quadrant.");
		return SeleniumUtils.isVisible(expandIconOnLeftChart, driver);
	}

	public boolean isExpandIconOnRightChartDisplayed() {
		extentTest.log(Status.INFO, "Verify expand icon on right chart view quadrant.");
		return SeleniumUtils.isVisible(expandIconOnRightChart, driver);
	}

	public String getChartHeaderPopup() {
		extentTest.log(Status.INFO, "getting chart heading text.");
		SeleniumUtils.isVisible(chartHeaderPopup, driver);
		return chartHeaderPopup.getText();
	}

	public String getChartSubheaderPopup() {
		extentTest.log(Status.INFO, "getting chart sub heading text.");
		SeleniumUtils.isVisible(chartSubHeaderPoupup, driver);
		return chartSubHeaderPoupup.getText();
	}

	public void performSearch(String searchText) {
		extentTest.log(Status.INFO, "Performing search.");
		searchTextbox.clear();
		searchTextbox.sendKeys(searchText);
		searchBtn.click();
		waitforLoadingRing();
	}

	public List<String> getProjectTypeDiscriptionList() {
		extentTest.log(Status.INFO, "Get project type discription list.");
		return CommonUtils.getListFromWebElements(projectTypeDiscription);
	}

	public List<String> getProjectTypeLeftCountList() {
		extentTest.log(Status.INFO, "Get project type left count list.");
		return CommonUtils.getListFromWebElements(projectTypeCountList);
	}

	public List<String> getProjectTypeRightCountList() {
		extentTest.log(Status.INFO, "Get project type right count list.");
		return CommonUtils.getListFromWebElements(projectTypeRightCountList);
	}

	public List<String> getProjectTypeFilterList() {
		extentTest.log(Status.INFO, "Get project type filter list.");
		return CommonUtils.getListFromWebElements(projectTypeFilterList);
	}

	public void selectPublishRangeFromDropdown(String publishRangeText) {
		for (WebElement publishRange : publishRangeDropDownOptions) {
			if (publishRange.getText().contains(publishRangeText)) {
				publishRange.click();
				break;
			} else
				sectionsTypeCategoryInDashboardLeft.get(0).click();
			waitforLoadingRing();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(sectionFindInfilterLeft));
		}
	}

	public void selectPublishRangeFromDD(String publishRangeText) {
		extentTest.log(Status.INFO, "Select publish range from drop down.");
		PublishRangeDropdown.click();
		for (WebElement publishRange : publishRangeDropDownOptions) {
			if (publishRange.getText().contains(publishRangeText)) {
				publishRange.click();
				break;
			}
		}
		waitforLoadingRing();
	}

	public boolean isDivisionChartDisplayed() {
		extentTest.log(Status.INFO, "Verify if the division chart is displayed");
		return firstDivisionInDivisionChart.isDisplayed();
	}

	public void selectDeselectOptionFromTheList1(WebElement optionToBeHandle, boolean toBeSelected) {
		extentTest.log(Status.INFO, "Selecting/Deselecting options from the left list");
		if (toBeSelected) {
			if (!optionToBeHandle.isSelected()) {
				optionToBeHandle.click();
			}
		} else {
			if (optionToBeHandle.isSelected()) {
				optionToBeHandle.click();
			}
		}
	}

	public boolean getFindInLHSCheckboxStatus(String checkBoxValue) {
		boolean isSelected = false;
		for (WebElement checkBx : FindIn_LHS_CbkList) {
			if (checkBoxValue.equalsIgnoreCase(checkBx.getAttribute("value"))) {
				isSelected = checkBx.isSelected();
				break;
			}
		}
		return isSelected;
	}

	public void selectDeselectFindInLHSCheckboxList(String checkBoxValue, boolean toBeSelected) {
		clickOnFindInFilter();
		for (WebElement checkBx : FindIn_LHS_CbkList) {
			if (checkBoxValue.equalsIgnoreCase(checkBx.getAttribute("value"))) {
				selectDeselectOptionFromTheList(checkBx, toBeSelected);
				waitforLoadingRing();
				break;
			}
		}
	}

	public int getProjectCountFromCONSTRUCTION_TYPELeftSelectionView1(String requiredFact) {
		extentTest.log(Status.INFO, "Get project count from Construction Type from Left selection view");
		List<WebElement> labelList = CONSTRUCTION_TYPEFilter_LHS_lblList;
		int projectCount = 0;
		for (WebElement labelItem : labelList) {
			if (labelItem.getAttribute("innerHTML").contains(requiredFact)) {
				projectCount = Integer.parseInt(labelItem.findElement(By.tagName("span")).getAttribute("innerHTML")
						.trim().replace("(", "").replace(",", "").replace(")", ""));
				break;
			}
		}
		return projectCount;
	}

	public void clickCONSTRUCTION_TYPEFromLeftSelectionView(String requiredFact) {
		extentTest.log(Status.INFO, "Click Construction Type from Left selection view");
		List<WebElement> labelList = CONSTRUCTION_TYPEFilter_LHS_lblList;
		for (WebElement labelItem : labelList) {
			if (labelItem.getAttribute("innerHTML").contains(requiredFact)) {
				CommonUtils.scrollDownTillElement(labelItem, driver);
				CommonUtils.clickOnElementUsingJavascript(labelItem, driver);
				break;
			}
		}
	}

	public ProjectSpecsPage clickHighlightLinkInProject1() {
		extentTest.log(Status.INFO, "Click on the highlighted link under the project");
		highlightLinkSpecsInProject.click();
		return new ProjectSpecsPage(driver);
	}

	public int getProjectCountFromActionStageLeftSelectionView(String requiredFact) {
		extentTest.log(Status.INFO, "Get project count from Action Stage from Left selection view");
		List<WebElement> labelList = actionStageTypeFilterList;
		int projectCount = 0;
		for (WebElement labelItem : labelList) {
			if (labelItem.getAttribute("desc").contains(requiredFact)) {
				projectCount = Integer.parseInt(labelItem.getAttribute("count"));
				break;
			}
		}
		return projectCount;
	}

	public void clickActionStageFromLeftSelectionView(String requiredFact) {
		extentTest.log(Status.INFO, "Click Action Stage from Left selection view");
		List<WebElement> labelList = actionStageTypeFilterList;
		for (WebElement labelItem : labelList) {
			if (labelItem.getAttribute("desc").contains(requiredFact)) {
				labelItem.click();
				break;
			}
		}
	}

	public ProjectPage clickOnFistProjectName1() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		firstProjectName.click();
		return new ProjectPage(driver);
	}

	public List<String> getProjectTrackList1() {
		extentTest.log(Status.INFO, "Get project track list");
		List<String> trackList = new ArrayList<String>();
		for (WebElement trackName : trackNameList) {
			trackList.add(trackName.getText());
		}
		return trackList;
	}

	public List<String> getProjectTitleWithspec() {
		extentTest.log(Status.INFO, "Get project title with spec Present in summary");
		List<String> specProfile = new ArrayList<String>();
		for (WebElement speckName : projectTitlesWithSpecs) {
			specProfile.add(speckName.getText());
		}
		return specProfile;
	}

	public boolean verifyUniqueStyleForAllOwnerTypes() {
		extentTest.log(Status.INFO, "Get all owner types style details.");
		boolean isVerified = true;
		for (WebElement ownershipType : allOwnershipTypes) {
			if (StringUtils.isBlank(ownershipType.getAttribute("style"))) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	public String getChartErrorMessage() {
		extentTest.log(Status.INFO, "Get error message on dashboard chart.");
		CommonUtils.scrollDownTillElement(errorMsg, driver);
		SeleniumUtils.isVisible(errorMsg, driver);
		return errorMsg.getText();
	}

	public List<String> getProjectNameList() {
		extentTest.log(Status.INFO, "Getting project name list.");
		return CommonUtils.getListFromWebElements(projectNameList);
	}

	public String getChartSubheaderPopup1() {
		extentTest.log(Status.INFO, "getting chart sub heading text.");
		return chartSubHeaderPoupup.getText();
	}

	public void performSearch1(String searchText) {
		extentTest.log(Status.INFO, "Performing search.");
		searchTextbox.clear();
		searchTextbox.sendKeys(searchText);
		searchBtn.click();
		waitforLoadingRing();
	}

	public List<String> getProjectTypeDiscriptionList1() {
		extentTest.log(Status.INFO, "Get project type discription list.");
		return CommonUtils.getListFromWebElements(projectTypeDiscription);
	}

	public List<String> getProjectTypeLeftCountList1() {
		extentTest.log(Status.INFO, "Get project type left count list.");
		return CommonUtils.getListFromWebElements(projectTypeCountList);
	}

	public List<String> getProjectTypeRightCountList1() {
		extentTest.log(Status.INFO, "Get project type right count list.");
		return CommonUtils.getListFromWebElements(projectTypeRightCountList);
	}

	public List<String> getProjectTypeFilterList1() {
		extentTest.log(Status.INFO, "Get project type filter list.");
		return CommonUtils.getListFromWebElements(projectTypeFilterList);
	}

	public void selectPublishRangeFromDropdown1(String publishRangeText) {
		extentTest.log(Status.INFO, "Select publish range from drop down.");
		PublishRangeDropdown.click();
		for (WebElement publishRange : publishRangeDropDownOptions) {
			if (publishRange.getText().contains(publishRangeText)) {
				publishRange.click();
				break;
			}
		}
	}

	public void selectDeselectOptionFromTheList(WebElement optionToBeHandle, boolean toBeSelected) {
		extentTest.log(Status.INFO, "Selecting/Deselecting options from the left list");
		if (toBeSelected) {
			if (!optionToBeHandle.isSelected()) {
				optionToBeHandle.click();
			}
		} else {
			if (optionToBeHandle.isSelected()) {
				optionToBeHandle.click();
			}
		}
	}

	public int getProjectCountFromCONSTRUCTION_TYPELeftSelectionView(String requiredFact) {
		extentTest.log(Status.INFO, "Get project count from Construction Type from Left selection view");
		List<WebElement> labelList = CONSTRUCTION_TYPEFilter_LHS_lblList;
		int projectCount = 0;
		for (WebElement labelItem : labelList) {
			if (labelItem.getAttribute("innerHTML").contains(requiredFact)) {
				projectCount = Integer.parseInt(labelItem.findElement(By.tagName("span")).getAttribute("innerHTML")
						.trim().replace("(", "").replace(",", "").replace(")", ""));
				break;
			}
		}
		return projectCount;
	}

	public ProjectSpecsPage clickHighlightLinkInProject() {
		extentTest.log(Status.INFO, "Click on the highlighted link under the project");
		highlightLinkSpecsInProject.click();
		return new ProjectSpecsPage(driver);
	}

	public ProjectPage clickOnFistProjectName() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		firstProjectName.click();
		return new ProjectPage(driver);
	}

	public List<String> getProjectTrackList() {
		extentTest.log(Status.INFO, "Get project track list");
		List<String> trackList = new ArrayList<String>();
		for (WebElement trackName : trackNameList) {
			trackList.add(trackName.getText());
		}
		return trackList;
	}

	public boolean IsmyAdditionalFilters_Displayed() {
		if (CommonUtils.checkElementExist(AdditionalFilters, driver)) {
			extentTest.log(Status.INFO, "Check to see AdditionalFilters displayed");
			return AdditionalFilters.isDisplayed();
		} else {
			return false;
		}
	}

	public void clickOnAdditionalFilters() {
		if (CommonUtils.checkElementExist(AdditionalFilters, driver)) {
			extentTest.log(Status.INFO, "Click on additional filter");
			SeleniumUtils.isClickable(AdditionalFilters, driver);
			AdditionalFilters.click();
		}
	}

	public void removeParticularFilter(String filterName) {
		extentTest.log(Status.INFO, "Remove given filter");
		WebElement webElement = driver
				.findElement(By.xpath(".//span[@title='" + filterName + "']//following-sibling::a"));
		webElement.click();
	}

	public void removeParticularKeywordFilter(String filterName) {
		extentTest.log(Status.INFO, "Remove given keyword filter");
		WebElement webElement = driver
				.findElement(By.xpath(".//a[@original-title='" + filterName + "']//following-sibling::a"));
		webElement.click();
		waitforLoadingRing();
	}

	public boolean isBiddingWithinFilterPaneCollapsed() {
		extentTest.log(Status.INFO, "Verify Bidding Within filter pane in collapsed state");
		return !driver.findElements(By.cssSelector("div[expandpref='BIDDING_WITHIN'] span[class*='arrow-up']"))
				.isEmpty();
	}

	public void clickOnactionStg() {
		clickonActionStage.click();
	}

	public void applyFilter() {
		extentTest.log(Status.INFO, "Applying filter i.e. 3 years prior");
		deselectNewCheckbox.click();
	}

	public boolean checkSpecIsDisplayed() {
		extentTest.log(Status.INFO, "check spec link is displayed");
		return displaySpecLink.isDisplayed();
	}

	public boolean checkPublishedDateIsDisplayed() {
		extentTest.log(Status.INFO, "check published date is displayed");
		return displayPublishedDate.isDisplayed();
	}

	public void clickOnDesignOption() {
		extentTest.log(Status.INFO, "Click On Commercial Check box");
		designCheckBox.click();
		waitforLoadingRing();
	}

	public void clickOnPre_DesignOption() {
		extentTest.log(Status.INFO, "Click On Commercial Check box");
		clickonPre_Designchbox.click();
		waitforLoadingRing();
	}

	public boolean checkHideFilterShowWithFilters() {
		if (!SeleniumUtils.isVisible(geographyFilter, driver)) {
			hideFilterBtn.click();
		}
		return SeleniumUtils.isVisible(geographyFilter, driver);
	}

	public void clickOnDownloadFirmsActionMenu() {
		extentTest.log(Status.INFO, "click on Download Firms action menu. ");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadFirmActionMenu));
		downloadFirmActionMenu.click();
	}

	public void enterSearchTxt(String dR_Num) {
		SeleniumUtils.isClickable(searchTxtField, driver);
		extentTest.log(Status.INFO, "Enter Search Text");
		searchTxtField.clear();
		searchTxtField.sendKeys(dR_Num);

	}

	public ProjectPage clickOnSearchButton() {
		extentTest.log(Status.INFO, "Click on Search Button");
		searchBtn.click();
		return new ProjectPage(driver);
	}

	public Boolean mouseOverActionandCheckDownloadFirmDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Download Firm is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadFirmActionMenu));
		return downloadFirmActionMenu.isDisplayed();
	}

	public void mouseOverActionandClickDownloadFirms() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdown).build().perform();
		extentTest.log(Status.INFO, "Check if Download Firms is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadFirmActionMenu));
		downloadFirmActionMenu.click();
	}

	public void ClickOn_hostProjectActionsLink() {
		extentTest.log(Status.INFO, "Click on hostProjectActionsLink");
		hostProjectActionsLink.click();
	}

	public void ClickOn_closeDocupropopup() {
		extentTest.log(Status.INFO, "Click on closeDocupropopup");
		closeDocupropopup.click();
	}

	public boolean Is_searchButton_displayed() {
		extentTest.log(Status.INFO, "Verify search button box is displayed");
		return searchButton.isDisplayed();
	}

	public void ClickOn_lnkHostCompaniesLink() {
		extentTest.log(Status.INFO, "Click on lnkHostCompaniesLink");
		lnkHostCompaniesLink.click();
	}

	public void ClickOn_ActionDropdown_ProjectDetailsPage() {
		extentTest.log(Status.INFO, "Click on ActionDropdown_ProjectDetailsPage");
		ActionDropdown_ProjectDetailsPage.click();
	}

	public boolean Is_hostToDocuProPopupHeader_Displayed() {
		extentTest.log(Status.INFO, "Ensure hostToDocuProPopupHeader is displayed");
		return hostToDocuProPopupHeader.isDisplayed();
	}

	public boolean Is_hostProjectActionsLink_Visible() {
		extentTest.log(Status.INFO, "Is hostProjectActionsLink visible");
		return SeleniumUtils.isVisible(hostProjectActionsLink, driver);
	}

	public boolean Is_lnkHostCompaniesLink_Visible() {
		extentTest.log(Status.INFO, "Is lnkHostCompaniesLink visible");
		return SeleniumUtils.isVisible(lnkHostCompaniesLink, driver);
	}

	public void ClickOnBackbutton() {
		driver.navigate().back();
	}

	public boolean verifyLeftVisualizationStatus(String visualizationName) {
		extentTest.log(Status.INFO, "Verify Visualization status");
		List<WebElement> webElements = driver
				.findElements(By.xpath(".//div[contains(@class,'dashboardleft')]//div//a//span[contains(.,'"
						+ visualizationName + "')]//ancestor::a"));
		boolean isEnabled = false;
		if (!webElements.isEmpty()) {
			if (webElements.get(0).getAttribute("style").contains("opacity: 1")) {
				isEnabled = true;
			} else {
				isEnabled = false;
			}
		}
		return isEnabled;
	}

	public boolean verifyRightVisualizationStatus(String visualizationName) {
		extentTest.log(Status.INFO, "Verify Visualization status");
		List<WebElement> webElements = driver
				.findElements(By.xpath(".//div[contains(@class,'dashboardright')]//div//a//span[contains(.,'"
						+ visualizationName + "')]//ancestor::a"));
		boolean isEnabled = false;
		if (!webElements.isEmpty()) {
			if (webElements.get(0).getAttribute("style").contains("opacity: 1")) {
				isEnabled = true;
			} else {
				isEnabled = false;
			}
		}
		return isEnabled;
	}

	public boolean isLeftTilesHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of left tiles header");
		boolean isDisplayed = false;
		if (!leftTilesheader.isEmpty()) {
			isDisplayed = leftTilesheader.get(0).isDisplayed();
		}
		return isDisplayed;
	}

	public void clickOnHideAllFilterButton() {
		extentTest.log(Status.INFO, "Click on Project section hide filter button.");
		if (SeleniumUtils.isVisible(LHS_Filters_lblList.get(0), driver)) {
			drawerAnchorButton.click();
			waitforLoadingRing();
		}
	}

	public void clickOnShowAllFiltersButton() {
		extentTest.log(Status.INFO, "Click on Project section show filter button.");
		if (!SeleniumUtils.isVisible(LHS_Filters_lblList.get(0), driver)) {
			drawerAnchorButton.click();
			waitforLoadingRing();
		}
	}

	public boolean isRightTilesHeaderDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of right tiles header");
		boolean isDisplayed = false;
		if (!rightTilesheader.isEmpty()) {
			isDisplayed = rightTilesheader.get(0).isDisplayed();
		}
		return isDisplayed;
	}

	public void deselect_FindInLHSCheckboxList() {
		for (int i = 0; i < FindIn_LHS_CbkList.size(); i++) {
			if (FindIn_LHS_CbkList.get(i).isSelected()) {
				FindIn_LHS_CbkList.get(i).click();
				waitforLoadingRing();
			}
		}
	}

	public void ClickOn_FindIn_LHS_CbkList(int idx) {
		extentTest.log(Status.INFO, "Click on FindIn checkbox " + idx + " ");
		FindIn_LHS_CbkList.get(idx).click();
		waitforLoadingRing();
	}

	public boolean Is_lnkSpecs_links_Displayed() {
		for (int i = 0; i < lnkSpecs.size(); i++) {
			if (!lnkSpecs.get(i).isDisplayed()) {
				return false;
			}
		}
		return true;
	}

	public boolean Is_lnkPlans_links_Displayed() {
		for (int i = 0; i < lnkPlans.size(); i++) {
			if (!lnkPlans.get(i).isDisplayed()) {
				return false;
			}
		}
		return true;
	}

	public boolean isSpecAlertCheckBoxesDisplayed() {
		extentTest.log(Status.INFO, "Verify Spec Alert check boxes.");
		return !specAlertCheckBoxes.isEmpty();
	}

	public void scrollDownToFilter(String filterName) {
		Actions actions = new Actions(driver);
		switch (filterName) {
		case "GeographyFilterList":
			actions.moveToElement(GeographyFilterList.get(0));
			break;
		case "materialsEquipFilterList":
			actions.moveToElement(materialsEquipFilterList.get(0));
			break;
		case "CommonPopupParentFilterList":
			actions.moveToElement(CommonPopupParentFilterList.get(0));
			break;
		case "Trades_LHS_ParentFilterList":
			actions.moveToElement(Trades_LHS_ParentFilterList.get(0));
			break;
		case "StateRegionFilterList":
			actions.moveToElement(StateRegionFilterList.get(0));
			break;
		case "GEOGRAPHY_COUNTYFilterList":
			actions.moveToElement(GEOGRAPHY_COUNTYFilterList.get(0));
			break;
		case "geographyPopupUASStatesList":
			actions.moveToElement(geographyPopupUASStatesList.get(0));
			break;
		case "COUNTYPopupUASCountyList":
			actions.moveToElement(COUNTYPopupUASCountyList.get(0));
			break;
		case "actionStageCodeFacetList":
			actions.moveToElement(actionStageCodeFacetList.get(0));
			break;
		case "trackingListFacetList":
			actions.moveToElement(trackingListFacetList.get(0));
			break;
		case "specAlertFilterList":
			actions.moveToElement(specAlertFilterList.get(0));
			break;
		case "specDivisionFilterList":
			actions.moveToElement(specDivisionFilterList.get(0));
			break;
		case "specDivisionFilterListPopup":
			actions.moveToElement(specDivisionFilterListPopup.get(0));
			break;
		case "ProjectGroups_LHS_ParentFilterList":
			actions.moveToElement(ProjectGroups_LHS_ParentFilterList.get(0));
			break;
		case "SpecificTrades_LHS_ParentFilterList":
			actions.moveToElement(SpecificTrades_LHS_ParentFilterList.get(0));
			break;
		case "ACTION_STAGE_CATEGORY_LHS_ParentFilterList":
			actions.moveToElement(ACTION_STAGE_CATEGORY_LHS_ParentFilterList.get(0));
			break;
		case "BuildingCLG_WaterProofing_FilterList":
			actions.moveToElement(BuildingCLG_WaterProofing_FilterList.get(0));
			break;
		case "ProjectTypes_LHS_ParentFilterList":
			actions.moveToElement(ProjectTypes_LHS_ParentFilterList.get(0));
			break;
		case "BiddingOptionsFilterListFromPopup":
			actions.moveToElement(BiddingOptionsFilterListFromPopup.get(0));
			break;
		case "ProjectGroupsParent2FilterList":
			actions.moveToElement(ProjectGroupsParent2FilterList.get(0));
			break;
		case "CommercialOptionsFilterListFromPopup":
			actions.moveToElement(CommercialOptionsFilterListFromPopup.get(0));
			break;
		case "SpecificTrades_BuildingUtilities_PopopFilterList":
			actions.moveToElement(SpecificTrades_BuildingUtilities_PopopFilterList.get(0));
			break;
		case "materialsEquip_LHS_FilterList":
			actions.moveToElement(materialsEquip_LHS_FilterList.get(0));
			break;
		case "materialsEquip2_LHS_FilterList":
			actions.moveToElement(materialsEquip2_LHS_FilterList.get(0));
			break;
		case "specAlertSeeMorePopUpListChkBox":
			actions.moveToElement(specAlertSeeMorePopUpListChkBox.get(0));
			break;
		case "trackingList_PopupOptionList":
			actions.moveToElement(trackingList_PopupOptionList.get(0));
			break;
		case "ConstructionType_LHSFilterList":
			actions.moveToElement(ConstructionType_LHSFilterList.get(0));
			break;
		case "OWNERSHIP_TYPE_LHSFilterList":
			actions.moveToElement(OWNERSHIP_TYPE_LHSFilterList.get(0));
			break;
		case "SPECIAL_Conditions_LHSFilterList":
			actions.moveToElement(SPECIAL_Conditions_LHSFilterList.get(0));
			break;
		case "biddingWithin_LHSFilterList":
			actions.moveToElement(biddingWithin_LHSFilterList.get(0));
			break;
		case "StructuralPropertiesCheckboxList":
			actions.moveToElement(StructuralPropertiesCheckboxList.get(0));
			break;
		case "ProjectResult_Chkbox_List":
			actions.moveToElement(ProjectResult_Chkbox_List.get(0));
			break;
		case "ChartView_DivGBarList":
			actions.moveToElement(ChartView_DivGBarList.get(0));
			break;
		case "FindIn_LHS_CbkList":
			actions.moveToElement(FindIn_LHS_CbkList.get(0));
			break;
		case "Common_Popup_FirstLevel2CbksList":
			actions.moveToElement(Common_Popup_FirstLevel2CbksList.get(0));
			break;
		case "ProjectType_LHS_ParentFilterList":
			actions.moveToElement(ProjectType_LHS_ParentFilterList.get(0));
			break;
		}

		actions.perform();

	}

	public List<String> getprojectActionDropdownOptions() {
		extentTest.log(Status.INFO, "Get project action dropdown options");
		SeleniumUtils.isVisible(projectActionsOptions.get(0), driver);
		return CommonUtils.getListFromWebElements(projectActionsOptions);
	}

	public List<String> getGridColumnHeadings() {
		extentTest.log(Status.INFO, "Get project grid column headings");
		List<String> headingList = new ArrayList<String>();
		for (final WebElement heading : gridColumnHeadings) {
			SeleniumUtils.scrollToView(driver, heading);
			headingList.add(heading.getText().trim());
		}
		return headingList;
	}

	public List<String> getColumnValueOfAllRows(final String columnName) {
		final List<String> colValue = new ArrayList<String>();
		final int columnIndex = getColumnIndex(columnName);
		colValue.addAll(getColumnValueList(columnIndex));
		return colValue;
	}

	public int getColumnIndex(final String columnName) {
		int columnIndex = -1;
		for (final WebElement heading : gridColumnHeadings) {
			columnIndex++;
			CommonUtils.scrollDownTillElement(heading, driver);
			if (heading.getText().equalsIgnoreCase(columnName)) {
				break;
			}
		}
		return columnIndex;
	}

	/**
	 * This method is used to get column values from all rows.
	 *
	 * @param columnIndex
	 *            columnIndex
	 */
	private List<String> getColumnValueList(final int columnIndex) {
		final List<String> colValue = new ArrayList<String>();
		String columnValue;
		WebElement dataCell = null;
		if (columnIndex > -1) {
			final int recordCount = gridRowList.size();
			final List<WebElement> rowList = gridRowList;
			for (int i = 0; i < recordCount; i++) {
				CommonUtils.scrollDownTillElement(rowList.get(i), driver);
				dataCell = rowList.get(i).findElements(By.tagName("td")).get(columnIndex);
				CommonUtils.scrollDownTillElement(dataCell, driver);
				columnValue = dataCell.getText();
				if (StringUtils.isNotBlank(columnValue)) {
					colValue.add(columnValue.trim());
				}
			}
		}
		return colValue;
	}

	public boolean verifyProjectColumnDRLinkList() {
		extentTest.log(Status.INFO, "Verify Project DR link list.");
		List<WebElement> linkList = new ArrayList<>();
		boolean isVerified = false;
		int columnIndex = getColumnIndex(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_PROJECT);
		WebElement projectCell = null;
		if (columnIndex > -1) {
			final int recordCount = gridRowList.size();
			final List<WebElement> rowList = gridRowList;
			for (int i = 0; i < recordCount; i++) {
				CommonUtils.scrollDownTillElement(rowList.get(i), driver);
				projectCell = rowList.get(i).findElements(By.tagName("td")).get(columnIndex);
				CommonUtils.scrollDownTillElement(projectCell, driver);
				if (StringUtils.isNotBlank(projectCell.getText())) {
					linkList.addAll(projectCell.findElements(By.cssSelector(".projectGrid_link")));
				}
				if (DGNProjectGridConstant.getProjectDRLinkList()
						.containsAll(CommonUtils.getListFromWebElements(linkList))) {
					isVerified = true;
				} else {
					isVerified = false;
					break;
				}
				linkList.clear();
			}
		}
		return isVerified;
	}

	public boolean verifyProjectColumnDRLinkListOnAllPages() {
		extentTest.log(Status.INFO, "Verify Project DR link list on all pages.");
		boolean isVerified = false;
		int paginationSize = paginationPageLinkList.size();
		for (int count = 0; count < paginationSize; count++) {
			paginationPageLinkList.get(count).click();
			waitforLoadingRing();
			isVerified = verifyProjectColumnDRLinkList();
			if (!isVerified) {
				break;
			}
		}
		return isVerified;
	}

	public boolean verifyBIDDateColumnValues() {
		extentTest.log(Status.INFO, "Verify BID date column value.");
		final List<String> dateList = getColumnValueOfAllRows(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_BID_DATE);
		boolean isVerified = false;
		String regex = "^(0[0-9]||1[0-2])/([0-2][0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$";
		for (final String dateValue : dateList) {
			if (dateValue.equalsIgnoreCase("-") || dateValue.matches(regex) || dateValue.equalsIgnoreCase("ASAP")
					|| dateValue.equalsIgnoreCase("NDS")) {
				isVerified = true;
			} else {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	public boolean verifyPublishDateColumnValues() {
		extentTest.log(Status.INFO, "Verify Publish date column value.");
		CommonUtils.scrollDownTillElement(gridGearIcon, driver);
		final List<String> dateList = getColumnValueOfAllRows(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_PUBLISH_DATE);
		boolean isVerified = false;
		String regex = "^(0[0-9]||1[0-2])/([0-2][0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$";
		for (final String dateValue : dateList) {
			if (dateValue.equalsIgnoreCase("-") || dateValue.matches(regex)) {
				isVerified = true;
			} else {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	public boolean verifyDRNumberColumnValues() {
		extentTest.log(Status.INFO, "Verify DR Number column value.");
		CommonUtils.scrollDownTillElement(gridGearIcon, driver);
		final List<String> drNumberList = getColumnValueOfAllRows(
				DGNProjectGridConstant.GRID_PROJECT_OOS_COLUMN_DR_NUMBER);
		boolean isVerified = false;
		String regex = "^[0-9]{0,12}$";
		for (final String drNumber : drNumberList) {
			if (drNumber.matches(regex)) {
				isVerified = true;
			} else {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	public boolean verifyVersionColumnValues() {
		extentTest.log(Status.INFO, "Verify version column value.");
		CommonUtils.scrollDownTillElement(gridGearIcon, driver);
		final List<String> versionList = getColumnValueOfAllRows(
				DGNProjectGridConstant.GRID_PROJECT_OOS_COLUMN_VERSION);
		boolean isVerified = false;
		String regex = "\\d+";
		for (final String version : versionList) {
			if (version.matches(regex)) {
				isVerified = true;
			} else {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}

	public List<String> getProjectStatusList() {
		extentTest.log(Status.INFO, "Get project status list.");
		return CommonUtils.getListFromWebElements(projectStatusList);
	}

	public boolean isDCSDialogDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of DCS dialog.");
		return dCSDialog.isDisplayed();
	}

	public boolean isDCSDialogHeaderDisplayed(final String expectedHeader) {
		extentTest.log(Status.INFO, "Verify presence of DCS dialog header.");
		try {
			return dCSDialogHeader.isDisplayed() && expectedHeader.equalsIgnoreCase(dCSDialogHeader.getText());
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnResetToDefault() {
		extentTest.log(Status.INFO, "Click on DCS dialog header.");
		SeleniumUtils.isVisible(dCSDialogHeader, driver);
		dCSDialogHeader.click();
		waitforLoadingRing();
	}

	public String verifyAlertMessageForResetToDefaultAndDismiss() {
		extentTest.log(Status.INFO, "Verify alert message for Reset To Default.");
		String message = resetGCSPopUpText.getText();
		cancelResetGcsButton.click();
		return message;
	}

	public String verifyAlertMessageForResetToDefaultAndAccept() {
		extentTest.log(Status.INFO, "Verify alert message for Reset To Default.");
		String message = resetGCSPopUpText.getText();
		resetAllGcsButton.click();
		return message;
	};

	public void clickOnResetAllOkButton() {
		extentTest.log(Status.INFO, "Click on Reset To Default ok button");
		SeleniumUtils.isVisible(resetAllOkButton, driver);
		resetAllOkButton.click();
	}

	// ***
	public ProjectResultsPage clickOnProjectFilterBreadcrumb() {
		extentTest.log(Status.INFO, "Click on Project detail bread crumb");
		ProjectResultBreadCrumb.click();
		waitforLoadingRing();
		return new ProjectResultsPage(driver);
	}

	public void selectDeselectDCSCheckbox(final String checkboxToBeUpdated, final boolean selectionStatus) {
		extentTest.log(Status.INFO, "Change DCS check box status as " + selectionStatus);
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		for (final WebElement container : containerList) {
			if (container.getText().contains(checkboxToBeUpdated)) {
				WebElement chkbx = container.findElement(By.tagName("input"));
				if ((chkbx.isSelected() && !selectionStatus) || (!chkbx.isSelected() && selectionStatus)) {
					chkbx.click();
					break;
				}
			}
		}
	}

	public boolean IsCheckZeroProjectFilter() {
		extentTest.log(Status.INFO, "Check Zero count project check boxes");
		List<WebElement> containerList = pdsSeeMorePopUpListChkBox;
		for (final WebElement container : containerList) {
			if (container.getText().contains("(0)")) {
				break;
			}
			return false;
		}
		return true;
	}

	public int CheckPDSSeeMoreFilterCount() {
		int filterCount = pdsSeeMorePopUpListChkBox.size();
		return filterCount;
	}

	public boolean getDCSCheckboxStatus(final String checkboxToBeVerified) {
		extentTest.log(Status.INFO, "Verify DCS check box status for " + checkboxToBeVerified);
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		boolean isSelected = false;
		for (final WebElement container : containerList) {
			if (container.getText().contains(checkboxToBeVerified)) {
				isSelected = container.findElement(By.tagName("input")).isSelected();
				break;
			}
		}
		return isSelected;
	}

	public boolean isGridViewToggleButtonUnSelected() {
		extentTest.log(Status.INFO, "Verify presence of clickable Grid view toggle button.");
		return gridViewToggleUnSelectedBtn.isDisplayed() && gridViewToggleUnSelectedBtn.getTagName().equals("a")
				&& gridViewToggleUnSelectedBtn.getAttribute("style").contains("inline-block");
	}

	public boolean isGridViewToggleButtonSelected() {
		extentTest.log(Status.INFO, "Verify presence of clickable Grid view toggle button.");
		return gridViewToggleSelectedBtn.isDisplayed() && gridViewToggleSelectedBtn.getTagName().equals("a")
				&& gridViewToggleSelectedBtn.getAttribute("style").contains("inline-block");
	}

	public boolean verifyToggleButtonName(final String expectedName) {
		extentTest.log(Status.INFO, "Verify toggle button name.");
		List<String> buttonNameList = CommonUtils.getListFromWebElements(toggleButtonNameList);
		boolean isVerified = false;
		for (final String name : buttonNameList) {
			if (expectedName.equalsIgnoreCase(name)) {
				isVerified = true;
				break;
			}
		}
		return isVerified;
	}

	public boolean verifyBlankTitleForGridViewUnSelectedToggleButton() {
		extentTest.log(Status.INFO, "Verify blank title to Grid view toggle button.");
		return StringUtils.isBlank(gridViewToggleUnSelectedBtn.getAttribute("original-title"));
	}

	public boolean verifyHorizontalScrollPresent() {
		extentTest.log(Status.INFO, "Verify presence of horizontal scroll bar.");
		return SeleniumUtils.isHorizontalScrollPresent(driver);
	}

	public List<String> getDCSCheckBoxLabelList() {
		extentTest.log(Status.INFO, "Get DCS check box label list.");
		return CommonUtils.getListFromWebElements(dCSDialogCheckboxContainers);
	}

	public List<String> getDCSSelectedCheckboxList() {
		extentTest.log(Status.INFO, "Get DCS selected check box list.");
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		List<String> selectedCheckBoxes = new ArrayList<String>();
		for (final WebElement container : containerList) {
			if (container.findElement(By.tagName("input")).isSelected()) {
				selectedCheckBoxes.add(container.getText().trim().toUpperCase(Locale.ENGLISH));
			}
		}
		return selectedCheckBoxes;
	}

	public List<String> getDCSUnselectedCheckboxList() {
		extentTest.log(Status.INFO, "Get DCS unselected check box list.");
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		List<String> unSelectedCheckBoxes = new ArrayList<String>();
		for (final WebElement container : containerList) {
			if (!container.findElement(By.tagName("input")).isSelected()) {
				unSelectedCheckBoxes.add(container.getText().trim().toUpperCase(Locale.ENGLISH));
			}
		}
		return unSelectedCheckBoxes;
	}

	public void selectAllDCSCheckbox() {
		extentTest.log(Status.INFO, "Change status of all DCS check box as selected.");
		CommonUtils.scrollDownTillElement(gridGearIcon, driver);
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		for (final WebElement container : containerList) {
			if (!container.findElement(By.tagName("input")).isSelected()) {
				container.findElement(By.tagName("input")).click();
			}
		}
		waitforLoadingRing();
	}

	public void deSelectAllDCSCheckbox() {
		extentTest.log(Status.INFO, "Change status of all DCS check box as not selected.");
		CommonUtils.scrollDownTillElement(gridGearIcon, driver);
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		for (final WebElement container : containerList) {
			if (container.findElement(By.tagName("input")).isSelected()) {
				container.findElement(By.tagName("input")).click();
			}
		}
		waitforLoadingRing();
	}

	// * Append Date/TimeStamp in order to have a unique name
	public boolean appendDateTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(PublishDate.getText().trim());
		} catch (Exception pe) {
			return false;
		}
		return true;

	}

	// *
	public boolean IsProject_Delivery_Filter_Displayed() {
		try {
			if (ProjectDeliveryFilter.isDisplayed()) {
				return false;
			}
		} catch (Exception e) {

		}
		return true;
	}

	public String getProjectCountString() {
		extentTest.log(Status.INFO, "Get project count string.");
		return projectDisplayedCount.getText();
	}

	public boolean isDesignAlertsLabelDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of design alert label.");
		return !labeldesignAlerts.isEmpty();
	}

	public boolean IsProjectDeliveryFilterpresentinMyfilter() {
		extentTest.log(Status.INFO, "Chcking Project Delivery filter is present in My filter section");
		try {
			if (MyFilterProjectDeliveryCheckElement.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public String getLastFilterNamepresentinMyfilter() {
		extentTest.log(Status.INFO, "Getting the name of the last filter in My filter ");
		int i = AllMyfilterElemets.size();
		String filName = AllMyfilterElemets.get(i - 1).getText();
		System.out.println("Last filter name in my filter is " + filName);
		return filName;

	}

	public String getFirstHyperlinkNumberInFilterCrumb() {
		extentTest.log(Status.INFO, "Returns first hyperlink number in filter crumb as a String");
		String Crumbnumber = FilterCrumb_AppliedFilter_Having_Hyperlink_List.get(0).getText();
		return Crumbnumber;
	}

	public void clickOnListViewIcon() {
		extentTest.log(Status.INFO, "Click on the List view dashboard button.");
		if (CommonUtils.checkElementExist(listViewDashboardIconDisable, driver)) {
			listViewDashboardIcon.click();
			SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
			SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
		}
	}

	/***
	 * 
	 * @param projectTitle
	 *            : check project present as hidden
	 * @return : return boolean value.
	 */
	public boolean checkHiddenProject(String projectTitle) {
		boolean hiddenProjTitleFlag = false;

		for (String title : getListProjectTitle()) {
			if (title.toUpperCase().trim().equals(projectTitle.toUpperCase().trim())) {
				hiddenProjTitleFlag = true;
			}

			if (hiddenProjTitleFlag) {
				break;
			}
		}
		return hiddenProjTitleFlag;
	}

	/***
	 * Unhide the specific project from hidden project list.
	 * 
	 * @param projectTitle
	 *            : check the project to unhide.
	 */
	public void unhidSpecificProject(String projectTitle) {
		int count = 0;
		boolean hiddenProjTitleFlag = false;
		for (String title : getListProjectTitle()) {
			if (title.toUpperCase().trim().equals(projectTitle.toUpperCase().trim())) {
				hiddenProjTitleFlag = true;
			}
			if (hiddenProjTitleFlag) {
				break;
			}
			count++;
		}
		hideLinkList.get(count).click();
	}

	public void clickOnFirstDCSUnselectedCheckbox() {
		extentTest.log(Status.INFO, "Click on first DCS unselected check box.");
		for (final WebElement container : dCSDialogCheckboxContainers) {
			if (!container.findElement(By.tagName("input")).isSelected()) {
				container.findElement(By.tagName("input")).click();
				waitforLoadingRing();
				break;
			}
		}
	}

	public String getFirstDCSUnselectedCheckboxLable() {
		extentTest.log(Status.INFO, "Get first DCS unselected check box lable.");
		List<WebElement> containerList = dCSDialogCheckboxContainers;
		String firstUnSelectedCheckBoxesLabel = "";
		for (final WebElement container : containerList) {
			if (!container.findElement(By.tagName("input")).isSelected()) {
				firstUnSelectedCheckBoxesLabel = container.getText().trim();
				break;
			}
		}
		return firstUnSelectedCheckBoxesLabel;
	}

	public void clickOnFirstDCSSelectedCheckbox() {
		extentTest.log(Status.INFO, "Click on first DCS selected check box to uncheck it.");
		for (final WebElement container : dCSDialogCheckboxContainers) {
			if (!container.findElement(By.tagName("input")).isSelected()) {
				container.findElement(By.tagName("input")).click();
				waitforLoadingRing();
				break;
			}
		}
	}

	public String getFirstDCSSelectedCheckboxLable() {
		extentTest.log(Status.INFO, "Get first DCS selected check box lable.");
		String firstSelectedCheckBoxesLabel = "";
		for (final WebElement container : dCSDialogCheckboxContainers) {
			if (!container.findElement(By.tagName("input")).isSelected()) {
				firstSelectedCheckBoxesLabel = container.getText().trim();
				break;
			}
		}
		return firstSelectedCheckBoxesLabel;
	}

	public boolean isHorizontalBarForProjGridDisplayed() {
		extentTest.log(Status.INFO, "Check horizontal Bar For ProjGrid is Displayed.");
		return CommonUtils.checkElementExist(horizontalBarForProjectGrid, driver);
	}

	public boolean checkToolTipForNoteLinkInProjGrid() {
		extentTest.log(Status.INFO, "Check ToolTip For Note Link InProject Grid.");
		String linkText = "Notes";
		String tooltipAttribute = "original-title";
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.linkText(linkText))).build().perform();
		String value = driver.findElement(By.linkText(linkText)).getAttribute(tooltipAttribute);

		if (value != null) {
			return true;
		}
		return false;
	}

	/***
	 * Click on the first project title of project grid table
	 * 
	 * @return : ProjectPage object.
	 */
	public ProjectPage clickOnFirstProjectTitleInProjectGrid() {
		extentTest.log(Status.INFO, "Click On First Project Title in Project Grid table.");
		projectTitleListOfProjGrid.get(0).click();
		return new ProjectPage(driver);
	}

	public boolean checkToolTipForProjectTitleInProjGrid() {
		extentTest.log(Status.INFO, "check ToolTip For Project Title In Project Grid.");
		String attribute = "title";
		Actions actions = new Actions(driver);
		actions.moveToElement(projectTitleListOfProjGrid.get(0)).build().perform();
		String value = projectTitleListOfProjGrid.get(0).getAttribute(attribute);

		if (value != null) {
			return true;
		}
		return false;
	}

	public int getTotalProjectTitleVisibleInGrid() {
		extentTest.log(Status.INFO, "Get total Project Title Visible size In Grid.");
		return projectTitleListOfProjGrid.size();
	}

	// Check the Project Result Displayed in Table format.
	public boolean checkProjectGridTableWrapper() {
		extentTest.log(Status.INFO, "Check the Project Result Displayed in Table format.");
		return CommonUtils.checkElementExist(projectGridTableWrapper, driver);
	}

	// Check if Keyword search filter crumb is displayed.
	public boolean isKeywordSearchFilterCrumbDisplayed() {
		extentTest.log(Status.INFO, "Check Keyword Search Filter Crumb is Displayed.");
		return CommonUtils.checkElementExist(keywordSearchFilterCrumb, driver);
	}

	/***
	 * 
	 * @param list
	 *            : Hold the value list of Project Grid Valuation column
	 * @return : Boolean result to check string contain character.
	 */
	public boolean checkGridValuationValueWithChar(List<String> list) {
		boolean charCheckFlag = true;
		for (String str : list) {
			String pattern = "[a-zA-Z]";
			if (str.matches(pattern)) {
				charCheckFlag = false;
				break;
			}
		}
		return charCheckFlag;
	}

	/***
	 * Select the Sort option from dropdown one by one and check the selected
	 * row from the grid is sorted as per selection
	 * 
	 * @param sortOptionList
	 *            : Hold the Sort dropdown options list
	 */
	public void verifyGridDataSortedBySelection(List<String> sortOptionList) {
		for (String sortOption : sortOptionList) {
			selectSortingOption(sortOption);
			waitforLoadingRing();
			String tableHeader = sortOption.split("-")[0].toUpperCase().trim();
			String sortType = sortOption.split("-")[1].toUpperCase().trim();
			if (sortType.equalsIgnoreCase("Ascending")) {
				Assert.assertTrue(
						getColumnValueOfAllRows(tableHeader)
								.containsAll(CommonUtils.sortWebElements(getColumnValueOfAllRows(tableHeader), true)),
						"Project Grid result is not sorted as per selection for column : " + tableHeader
								+ " sort typle : " + sortType);
			} else {
				Assert.assertTrue(
						getColumnValueOfAllRows(tableHeader)
								.containsAll(CommonUtils.sortWebElements(getColumnValueOfAllRows(tableHeader), false)),
						"Project Grid result is not sorted as per selection for column : " + tableHeader
								+ " sort typle : " + sortType);
			}
		}
	}

	public List<String> getProjectGridLinkList() {
		extentTest.log(Status.INFO, "Get project grid DR link list.");
		return CommonUtils.getListFromWebElements(projectGridLinkList);
	}

	public boolean verifyProjectColumnDRLinkListForOOS() {
		extentTest.log(Status.INFO, "Verify the Sorting option funtionality.");
		List<WebElement> linkList = new ArrayList<>();
		boolean isVerified = false;
		int columnIndex = getColumnIndex(DGNProjectGridConstant.GRID_DEFAULT_COLUMN_PROJECT);
		WebElement projectCell = null;
		if (columnIndex > -1) {
			final int recordCount = gridRowList.size();
			final List<WebElement> rowList = gridRowList;
			for (int i = 0; i < recordCount; i++) {
				CommonUtils.scrollDownTillElement(rowList.get(i), driver);
				projectCell = rowList.get(i).findElements(By.tagName("td")).get(columnIndex);
				CommonUtils.scrollDownTillElement(projectCell, driver);
				if (StringUtils.isNotBlank(projectCell.getText())) {
					isVerified = projectCell.findElements(By.tagName("a")).isEmpty();
				}
				if (isVerified) {
					linkList.clear();
				} else {
					break;
				}
			}
		}
		return isVerified;
	}

	/***
	 * Verify the Column value is sorted as per the the selected option from
	 * Sort dropdown.
	 * 
	 * @param sortOption
	 *            : Hold the sort dropdown option.
	 * @return
	 */
	public boolean verifyColumnSortedBySelectionValue(String sortOption) {
		boolean result;
		extentTest.log(Status.INFO, "Verify column sorted by selection.");
		String tableHeader = sortOption.split("-")[0].toUpperCase().trim();
		String sortType = sortOption.split("-")[1].toUpperCase().trim();
		if (sortType.equalsIgnoreCase("Ascending")) {
			result = getColumnValueOfAllRows(tableHeader)
					.containsAll(CommonUtils.sortWebElements(getColumnValueOfAllRows(tableHeader), true));
		} else {
			result = getColumnValueOfAllRows(tableHeader)
					.containsAll(CommonUtils.sortWebElements(getColumnValueOfAllRows(tableHeader), false));
		}
		return result;
	}

	/***
	 * Check Manufacture Basic of Design checkbox display.
	 * 
	 * @return : Result the boolean result
	 */
	public boolean isMFRVizBasicOfDesignCheckboxDisplayed() {
		extentTest.log(Status.INFO, "Check Manufacture Basic of Design checkbox is display.");
		return CommonUtils.checkElementExist(mfrVizBasicOfDesignChkbox, driver);
	}

	/***
	 * Check Manufacture Basic of Design checkbox is selected
	 * 
	 * @return : Result the boolean result
	 */
	public boolean isMFRVizBasicOfDesignCheckboxSelected() {
		extentTest.log(Status.INFO, "Check Manufacture Basic of Design checkbox is selected.");
		return mfrVizBasicOfDesignChkbox.isSelected();
	}

	/***
	 * Select the Basic Of Design Checkbox
	 */
	public void selectMFRVizBasicOfDesignCheckbox() {
		extentTest.log(Status.INFO, "Select Manufacture Basic of Design checkbox.");
		if (!mfrVizBasicOfDesignChkbox.isSelected()) {
			mfrVizBasicOfDesignChkboxLabel.click();
			waitforLoadingRing();
		}
	}

	/***
	 * Unselect the Basic Of Design Checkbox
	 */
	public void unselectMFRVizBasicOfDesignCheckbox() {
		extentTest.log(Status.INFO, "Unselect Manufacture Basic of Design checkbox.");
		if (mfrVizBasicOfDesignChkbox.isSelected()) {
			mfrVizBasicOfDesignChkboxLabel.click();
			waitforLoadingRing();
		}
	}

	/***
	 * Check Left Basic of Design Bar value present in MFR Viz
	 * 
	 * @return : Result the boolean result
	 */
	public boolean checkLeftBasicOfDesignBarValuePresent() {
		extentTest.log(Status.INFO, "Check Manufacture Basic of Design checkbox display.");
		return mfrVizLeftBasicOfDesignFilterBarValueList.size() > 0;
	}

	/***
	 * Check Right Basic of Design Bar value present in MFR Viz
	 * 
	 * @return : Result the boolean result
	 */
	public boolean checkRightBasicOfDesignBarValuePresent() {
		extentTest.log(Status.INFO, "Check Manufacture Basic of Design checkbox display.");
		return mfrVizRightBasicOfDesignFilterBarValueList.size() > 0;
	}

	/***
	 * Check Basic Of Design Bar Value Present For All Bar in left Viz chart.
	 * 
	 * @return : Get boolean result
	 */
	public boolean checkProjectCountBODPresentForAllBarChart1() {
		extentTest.log(Status.INFO, "Check Basic Of Design Bar Value Present For All Bar in left Viz chart.");
		return mfrVizLeftBasicOfDesignFilterBarValueList.size() == 100;
	}

	@SuppressWarnings("null")
	private int getCustomizeMFRIndex() {
		extentTest.log(Status.INFO, "get index location for customize MFR popup");
		for (int i = 0; i <= customizeManufacturersLink.size(); i++) {
			if(SeleniumUtils.isVisible(customizeManufacturersLink.get(i), driver)){
				return i;
			}
		}
		return (Integer) null;
	}

	/***
	 * Check Check customize MFR link is displayed.
	 * 
	 * @return : Get boolean result
	 */
	public boolean checkCustomizeManufacturersLinkIsDisplayed() {
		extentTest.log(Status.INFO, "Check customize MFR link is displayed.");
		int i = getCustomizeMFRIndex();
		return customizeManufacturersLink.get(i).isDisplayed();
	}

	public int getSelectedMFRCountAsUserFavoured() {
		extentTest.log(Status.INFO, "Get the count of MFR selected as User favoured");
		clickOnCustomizeMFRlinkPopup();
		int count = 0;
		try {
			count = selectedMFRInCustomizeMFRPopup.size() + 1;
		} catch (Exception e) {
			count = 0;
		}
		clickOnCustomizeMFRPopupCancelButton();
		return count;
	}

	public List<String> getSelectedMFRInCustomizeMFRPopupList() {
		extentTest.log(Status.INFO, "Return list of MFR name in selected MFR section in Customize MFR Popup ");
		List<String> selctedMFR = CommonUtils.getListFromWebElements(selectedMFRInCustomizeMFRPopup);
		return selctedMFR;
	}

	public List<String> removeWhiteSpaceInStringInList(List<String> toRemove) {
		List<String> removed = new ArrayList<String>();
		for (String s : toRemove) {
			removed.add(s.replace(" ", "").trim());
		}
		return removed;

	}

	public void selectMFRInUserFavoredPopup(int toSelect) {
		extentTest.log(Status.INFO, "Add required number of MFR in user favoured popup");
		for (int i = 0; i < toSelect; i++) {
			CommonUtils.scrollDownTillElement(allMFRInCustomizeMFRPopup.get(i), driver);
			allMFRInCustomizeMFRPopup.get(i).click();
		}
	}
	
	public List<String> getAllMFRInUserFavoredPopup() {
		extentTest.log(Status.INFO, "Get All MFR in user favoured popup available for selection");
		List<String> allMFRList = new ArrayList<>();
		for(WebElement ele:allMFRInCustomizeMFRPopup ){
			CommonUtils.scrollDownTillElement(ele, driver);
			allMFRList.add(ele.getText());
		}
		return allMFRList;
	}
	
	public int getAllMFRCountInUserFavoredPopup() {
		extentTest.log(Status.INFO, "Get All MFR count in user favoured popup available for selection");
		return allMFRInCustomizeMFRPopup.size();
	}
	
	


	public void removeMFRInUserFavoredPopup(int toRemove) {
		extentTest.log(Status.INFO, "Remove selected MFR in user favoured popup");
		try {
			for (int i = 0; i < toRemove; i++) {
				removeSelectedMFRInCustomizeMFRPopup.get(0).click();
			}
		} catch (Exception e) {
			// All selected MFR already removed.
			e.printStackTrace();
		}
	}

	public void removeAllMFRInUserFavoredPopup() {
		extentTest.log(Status.INFO, "Remove All selected MFR in user favoured popup");
		clickOnCustomizeMFRlinkPopup();
		int count = selectedMFRInCustomizeMFRPopup.size();
		if (count != 0) {
			try {

				for (int i = 0; i <= count; i++) {
					removeSelectedMFRInCustomizeMFRPopup.get(0).click();

				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		clickOnCustomizeMFRPopupSaveButton();
	}

	public boolean isCustomizeMFRPopupDisplayed() {
		extentTest.log(Status.INFO, "Check whether Customize MFR Popup is displayed ");
		return SeleniumUtils.isVisible(customizeMFRPopupTitle, driver);
	}
	
	public String getCustomizeMFRPopupheaderTitle() {
		extentTest.log(Status.INFO, "Return Customize MFR Popup header title ");
		return customizeMFRPopupTitle.getText();
	}
	
	public String getCustomizeMFRPopupheaderSubTitle() {
		extentTest.log(Status.INFO, "Return Customize MFR Popup header Sub-title ");
		return customizeMFRPopupSubTitle.getText();
	}
	
	public boolean  isCustomizeMFRPopupheaderSubTitleDisplayedInRed() {
		extentTest.log(Status.INFO, "Check if Customize MFR Popup header Sub-title is displayed in red");
		CommonUtils.scrollDownTillElement(customizeMFRPopupSubTitle, driver);
		boolean logic = false;
		if( customizeMFRPopupSubTitle.getAttribute("style").equals("color: rgb(255, 0, 0);")){
			logic = true;
		}
		return logic;
	}
	

	public void addTextInCustomizeMFRPopupFindAManufacturerTextBox(String toAdd){
		extentTest.log(Status.INFO, "Add text in find a MFR textbox in user favoured customize popup");
		CommonUtils.scrollDownTillElement(customizeMFRPopupFindAManufacturerTextBox, driver);
		customizeMFRPopupFindAManufacturerTextBox.clear();
		customizeMFRPopupFindAManufacturerTextBox.sendKeys(toAdd);
	}
	
	public String getHelpTextInCustomizeMFRPopupFindAManufacturerTextBox(){
		extentTest.log(Status.INFO, "Get default help text in find a MFR textbox in user favoured customize popup");
		CommonUtils.scrollDownTillElement(customizeMFRPopupFindAManufacturerTextBox, driver);
		return customizeMFRPopupFindAManufacturerTextBox.getAttribute("placeholder");
	}
	
	public boolean  isCustomizeMFRPopupSelectedMFRCountDisplayedInRed() {
		extentTest.log(Status.INFO, "Check if Customize MFR Popup MFR count in Selected section is displayed in red");
		CommonUtils.scrollDownTillElement(customizeMFRPopupSelectedMFRCount, driver);
		boolean logic = false;
		if( customizeMFRPopupSelectedMFRCount.getAttribute("style").equals("color: rgb(255, 0, 0);")){
			logic = true;
		}
		return logic;
	}
	
	public boolean isListValueContainsStringInAllElements(List<String> valueList, String textToBeVerified) {
		extentTest.log(Status.INFO, "Checking that a string is contained in the allements of the list");
		boolean isValueVerified = true;
		for (String value : valueList) {
			if (!value.toUpperCase().trim().contains(textToBeVerified.toUpperCase())) {
				isValueVerified = false;
				break;
			}
		}
		return isValueVerified;
	}
	
	public String getTextFromCustomizeMFRPopupSelectedMFRSectionBox() {
		extentTest.log(Status.INFO, "Return any text in Customize MFR Popup Selected MFR Section ");
		return customizeMFRPopup_SelectedMFRSectionBox.getText();
	}	
	
	public String getCustomizeMFRPopupSelectedMFRCount() {
		extentTest.log(Status.INFO, "Return Customize MFR Popup MFR count in Selected section");
		return customizeMFRPopupSelectedMFRCount.getText();
	}
	
	public String getCustomizeMFRPopupAllAvailableMFRSectionTitle() {
		extentTest.log(Status.INFO, "Return Customize MFR Popup All Available MFR Section Title ");
		return customizeMFRPopup_AllAvailableMFRSectionTitle.getText();
	}
	
	public String getCustomizeMFRPopupSelectedMFRSectionTitle() {
		extentTest.log(Status.INFO, "Return Customize MFR Popup Selected MFR Section Title ");
		return customizeMFRPopup_SelectedMFRSectionTitle.getText();
	}

	public boolean isNoUserFavouredMFRMessagedisplayed() {
		extentTest.log(Status.INFO, "Check whether no User Favoured MFR Message is displayed ");
		return SeleniumUtils.isVisible(noUserFavouredMFRMessage, driver);
	}

	public boolean isAllMFRTop100Subtitledisplayed() {
		extentTest.log(Status.INFO, "Check whether all MFR Top 100 Subtitle is displayed ");
		return SeleniumUtils.isVisible(allMFRTop100Subtitle, driver);
	}

	public void clickOnUserfavouredMFRToggle() {
		extentTest.log(Status.INFO, "Click all/UserfavouredMFR Toggle button.");
		all_UserfavouredMFR_Toggle.click();
		waitforLoadingRing();
	}

	public void clickOnCustomizeMFRlinkPopup() {
		extentTest.log(Status.INFO, "Click customize MFR link to open customize MFR popup.");
		int i = getCustomizeMFRIndex();
		customizeManufacturersLink.get(i).click();
	}

	public void clickOnCustomizeMFRPopupCancelButton() {
		extentTest.log(Status.INFO, "Click on Customize MFR Popup Cancel Button.");
		customizeMFRPopupCancelButton.click();

	}
	
	public void clickOnCustomizeMFRPopupCloseButton() {
		extentTest.log(Status.INFO, "Click on Customize MFR Popup close Button.");
		customizeMFRPopup_CloseButton.click();

	}

	public void clickOnCustomizeMFRPopupSaveButton() {
		extentTest.log(Status.INFO, "Click on Customize MFR Popup Cancel Button.");
		customizeMFRPopupSaveButton.click();
		waitforLoadingRing();
	}

	/***
	 * Get customize MFR tooltip text.
	 * 
	 * @return : Get String result
	 */
	public String getCustomizeManufacturersLinkTooltiptext() {
		extentTest.log(Status.INFO, "Get customize MFR tooltip text.");
		String tooltip = null;
		int i = getCustomizeMFRIndex();
		tooltip = customizeManufacturersLink.get(i).getAttribute("original-title");
		return tooltip;
	}

	/***
	 * Check Basic Of Design Bar Value Present For All Bar in right Viz chart.
	 * 
	 * @return : Get boolean result
	 */
	public boolean checkProjectCountBODPresentForAllBarChart2() {
		extentTest.log(Status.INFO, "Check Basic Of Design Bar Value Present For All Bar in right Viz chart.");
		return mfrVizRightBasicOfDesignFilterBarValueList.size() == 100;
	}

	/***
	 * Check Specified Bar Value Present For All Bar in left Viz chart.
	 * 
	 * @return : Get boolean result
	 */
	public boolean checkProjectCountSpecifiedPresentForAllBarChart1() {
		extentTest.log(Status.INFO, "Check Specified Bar Value Present For All Bar in left Viz chart.");
		return mfrVizLeftSpecifiedFilterBarValueList.size() == 100;
	}

	/***
	 * Check Specified Bar Value Present For All Bar in right Viz chart.
	 * 
	 * @return : Get boolean result
	 */
	public boolean checkProjectCountSpecifiedPresentForAllBarChart2() {
		extentTest.log(Status.INFO, "Check Specified Bar Value Present For All Bar in right Viz chart.");
		return mfrVizRightSpecifiedFilterBarValueList.size() == 100;
	}

	/***
	 * Check The project count shown for each BOD MFR is not a clickable link.
	 * 
	 * @return : Get boolean result
	 */
	public boolean isLeftAllProjectCountBODClickbale() {
		extentTest.log(Status.INFO, "Check The project count shown for each MFR is not a clickable link.");
		int count = 0;
		boolean resultFlag = false;
		for (WebElement element : mfrVizLeftBasicOfDesignFilterBarValueList) {
			CommonUtils.scrollDownTillElement(element, driver);
			if (element.getTagName().contains("tspan")) {
				count = count + 1;
			}
		}
		if (count == 100)
			resultFlag = true;
		return resultFlag;
	}

	/***
	 * Check The project count shown for each MFR BOD is not a clickable link
	 * right chart.
	 * 
	 * @return : Get boolean result
	 */
	public boolean isRightAllProjectCountBODClickbale() {
		extentTest.log(Status.INFO, "Check The project count shown for each MFR is not a clickable link right chart.");
		int count = 0;
		boolean resultFlag = false;
		for (WebElement element : mfrVizRightBasicOfDesignFilterBarValueList) {
			CommonUtils.scrollDownTillElement(element, driver);
			if (element.getTagName().contains("tspan")) {
				count = count + 1;
			}
		}
		if (count == 100)
			resultFlag = true;
		return resultFlag;
	}

	/***
	 * Check The project count shown for each Specified MFR is not a clickable
	 * link.
	 * 
	 * @return : Get boolean result
	 */
	public boolean isLeftAllProjectCountSpecifiedClickbale() {
		extentTest.log(Status.INFO, "Check The project count shown for each MFR Specified is not a clickable link.");
		int count = 0;
		boolean resultFlag = false;
		for (WebElement element : mfrVizLeftSpecifiedFilterBarValueList) {
			CommonUtils.scrollDownTillElement(element, driver);
			if (element.getTagName().contains("tspan")) {
				count = count + 1;
			}
		}
		if (count == 100)
			resultFlag = true;
		return resultFlag;
	}

	/***
	 * Check The project count shown for each MFR Specified is not a clickable
	 * link right chart.
	 * 
	 * @return : Get boolean result
	 */
	public boolean isRightAllProjectCountSpecifiedClickbale() {
		extentTest.log(Status.INFO,
				"Check The project count shown for each MFR Specified is not a clickable link right chart.");
		int count = 0;
		boolean resultFlag = false;
		for (WebElement element : mfrVizRightSpecifiedFilterBarValueList) {
			CommonUtils.scrollDownTillElement(element, driver);
			if (element.getTagName().contains("tspan")) {
				count = count + 1;
			}
		}
		if (count == 100)
			resultFlag = true;
		return resultFlag;
	}

	/***
	 * Check Manufacture Specified checkbox display.
	 * 
	 * @return : Result the boolean result
	 */
	public boolean isMFRVizSpecifiedCheckboxDisplayed() {
		extentTest.log(Status.INFO, "Check Manufacture Specified checkbox display.");
		return CommonUtils.checkElementExist(mfrVizSpecifiedChkbox, driver);
	}

	/***
	 * Check Manufacture Specified checkbox is selected
	 * 
	 * @return : Result the boolean result
	 */
	public boolean isMFRVizSpecifiedCheckboxSelected() {
		extentTest.log(Status.INFO, "Check Manufacture Specified checkbox is selected.");
		return mfrVizSpecifiedChkbox.isSelected();
	}

	/***
	 * Select the Specified Checkbox
	 */
	public void selectMFRVizSpecifiedCheckbox() {
		extentTest.log(Status.INFO, "Select Manufacture Specified checkbox.");
		if (!mfrVizSpecifiedChkbox.isSelected()) {
			mfrVizSpecifiedChkboxLabel.click();
			waitforLoadingRing();
		}
	}

	public void enterMFRVizLeftFilterSearchText(String searchText) {
		extentTest.log(Status.INFO, "Enter MFR viz left filter search text.");
		SeleniumUtils.isVisible(mfrVizLeftFilterTextbox, driver);
		mfrVizLeftFilterTextbox.clear();
		mfrVizLeftFilterTextbox.sendKeys(searchText);
	}

	/***
	 * Get MFR viz left filter search text from Chart 1
	 * 
	 * @return : return search text
	 */
	public String getMFRVizLeftFilterSearchText() {
		extentTest.log(Status.INFO, "Get MFR viz left filter search text.");
		SeleniumUtils.isVisible(mfrVizLeftFilterTextbox, driver);
		return CommonUtils.getTextOFElementUsingJavascript(mfrVizLeftFilterTextbox, driver).trim();
	}

	/***
	 * Get viz left filter search text placeholder
	 * 
	 * @return : Get the placeholder string
	 */
	public String getVizLeftFilterSearchTextPlaceholder() {
		extentTest.log(Status.INFO, "Get viz left filter search text placeholder.");
		SeleniumUtils.isVisible(mfrVizLeftFilterTextbox, driver);
		return mfrVizLeftFilterTextbox.getAttribute("placeholder").trim();
	}

	public ProjectResultsPage enterMFRVizRightFilterSearchText(String searchText) {
		extentTest.log(Status.INFO, "Enter MFR viz right filter search text.");
		SeleniumUtils.isVisible(mfrVizRightFilterTextbox, driver);
		mfrVizRightFilterTextbox.clear();
		mfrVizRightFilterTextbox.sendKeys(searchText);
		return new ProjectResultsPage(driver, true);
	}

	/***
	 * Get MFR viz Right filter search text from Chart 1
	 * 
	 * @return : return search text
	 */
	public String getMFRVizRightFilterSearchText() {
		extentTest.log(Status.INFO, "Get MFR viz Right filter search text.");
		SeleniumUtils.isVisible(mfrVizRightFilterTextbox, driver);
		return CommonUtils.getTextOFElementUsingJavascript(mfrVizRightFilterTextbox, driver).trim();
	}

	/***
	 * Get viz Right filter search text placeholder
	 * 
	 * @return : Get the placeholder string
	 */
	public String getVizRightFilterSearchTextPlaceholder() {
		extentTest.log(Status.INFO, "Get viz Right filter search text placeholder.");
		SeleniumUtils.isVisible(mfrVizRightFilterTextbox, driver);
		return mfrVizRightFilterTextbox.getAttribute("placeholder").trim();
	}

	/***
	 * Unselect the Specified Checkbox
	 */
	public void unselectMFRVizSpecifiedCheckbox() {
		extentTest.log(Status.INFO, "Unselect Manufacture Specified checkbox.");
		if (mfrVizSpecifiedChkbox.isSelected()) {
			mfrVizSpecifiedChkboxLabel.click();
			waitforLoadingRing();
		}
	}

	/***
	 * Check left Specified Bar value present in MFR Viz
	 * 
	 * @return : Result the boolean result
	 */
	public boolean checkLeftSpecifiedBarValuePresent() {
		extentTest.log(Status.INFO, "Check Manufacture left Specified checkbox display.");
		return mfrVizLeftSpecifiedFilterBarValueList.size() > 0;
	}

	/***
	 * altte Check right Specified Bar value present in MFR Viz
	 * 
	 * @return : Result the boolean result
	 */
	public boolean checkRightSpecifiedBarValuePresent() {
		extentTest.log(Status.INFO, "Check Manufacture right Specified checkbox display.");
		return mfrVizRightSpecifiedFilterBarValueList.size() > 0;
	}

	/***
	 * Verify Scroll bar present Viz left chart
	 * 
	 * @return : Get boolean result
	 */
	public boolean verifyScrollBarPresentVizChart1Dashboard() {
		extentTest.log(Status.INFO, "Verify Scroll bar present Viz left chart.");
		return CommonUtils.checkElementExist(scrollBarVizChart1Dashboard, driver);
	}

	/***
	 * Verify Scroll bar present Viz right chart
	 * 
	 * @return : Get boolean result
	 */
	public boolean verifyScrollBarPresentVizChart2Dashboard() {
		extentTest.log(Status.INFO, "Verify Scroll bar present Viz right chart.");
		return CommonUtils.checkElementExist(scrollBarVizChart2Dashboard, driver);
	}

	/***
	 * Verify 'Select a Visualization' screen are ordered in ascending
	 * alphabetical order, from left-to-right, top-to-bottom.
	 * 
	 * @return : Return boolean result
	 */
	public boolean verifyVisualizationBtnInAscendingOrder() {
		extentTest.log(Status.INFO,
				"Verify 'Select a Visualization' screen are ordered in ascending alphabetical order, from left-to-right,  top-to-bottom.");
		List<String> visualBtnList = CommonUtils.getListFromWebElements(selectVisualCharButtonList);
		return CommonUtils.CompareTwoList(visualBtnList, CommonUtils.sortWebElements(visualBtnList, true));
	}

	public boolean isPDSFilterFacetDisplayed() {
		extentTest.log(Status.INFO, "Verify if the PDS filter Facet is available in left nav");
		return IsProject_Delivery_filterfacets_Displayed();
	}

	public boolean verifyProjectDeliveryFacetSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(ProjectDeliveryFilterFacets);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean verifyAscendingSortOrderForFacetsOnPDSPopup() {
		List<String> listElements = CommonUtils.getListFromWebElements(pdsSeeMorePopUpListChkBox);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public boolean IsProject_Delivery_Checkbox_Displayed() {
		try {
			for (int i = 0; i < pdsfacetChkBox.size(); i++) {

				if (pdsfacetChkBox.get(i).isDisplayed()) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;

	}

	public boolean IsProject_Delivery_filterfacets_Displayed() {
		try {
			if (ProjectDeliverySystemDesription.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verifyPDSFilterList() {
		extentTest.log(Status.INFO, "Verify PDS filter Facet with valid components.");
		boolean isVerified = true;
		String facetLabel;
		String facetProjectCount;
		for (WebElement facet : pdsFilterFacetList) {
			isVerified = isVerified && facet
					.findElement(By.xpath("//input[contains(@id,'projectDeliverySystemTypeFacet')]")).isDisplayed();
			facetLabel = facet.findElement(By.xpath("//label[contains(@for,'projectDeliverySystemTypeFacet')]"))
					.getText();
			isVerified = isVerified && StringUtils.isNotBlank(facetLabel);
			facetProjectCount = facetLabel.substring(facetLabel.lastIndexOf("(") + 1, facetLabel.lastIndexOf(")"));
			isVerified = isVerified && StringUtils.isNotBlank(facetProjectCount);
			if (!isVerified) {
				break;
			}
		}
		return isVerified;
	}

	/***
	 * compare Project Results Count
	 * 
	 * @param expProjectResultCount
	 *            : Take the exp proj result count to compare
	 * @return : get the boolean result
	 */
	public boolean compareProjectResultsCount(String expProjectResultCount) {
		extentTest.log(Status.INFO, "compare Project Results Count.");
		String actualProjectResultCount = getChartProjectResultsCount();
		if (actualProjectResultCount.contains(","))
			actualProjectResultCount = actualProjectResultCount.replaceAll(",", "");
		return actualProjectResultCount.equals(expProjectResultCount);
	}

	public boolean isexpandOnManufacturerIconDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of expand icon on manufacturer.");
		return SeleniumUtils.isVisible(expandOnManufacturer, driver);
	}

	public boolean isexpandOnManufacturerIconRightChartDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of expand icon on manufacturer.");
		return SeleniumUtils.isVisible(expandOnRightChart, driver);
	}

	public void clickOnexpandOnManufacturerIcon() {
		extentTest.log(Status.INFO, "Click on expandOnManufacturerIcon on manufacturer chart.");
		SeleniumUtils.isVisible(expandOnManufacturer, driver);
		CommonUtils.clickOnElementUsingJavascript(expandOnManufacturer, driver);
	}

	public void clickOnexpandOnCloseBtn() {
		extentTest.log(Status.INFO, "Click on expandOnManufacturerIcon on manufacturer chart.");
		SeleniumUtils.isVisible(expandcloseBtn, driver);
		CommonUtils.clickOnElementUsingJavascript(expandcloseBtn, driver);
	}

	public boolean isexpandOnCloseBtnDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of expand icon on manufacturer.");
		System.out.println("Text:" + expandOnManufacturer.getText());
		return CommonUtils.checkElementExist(expandcloseBtn, driver);
	}

	public boolean isexpandedChartGraphDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of graph on expanded chart of manufacturer.");
		for (int i = 0; i <= 5; i++) {
			if (expandedChartGraph.get(i).isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	/***
	 * Click on specific MFR fin in MFR dropdown
	 * 
	 * @param mfrToSelect
	 *            : Get the MFR text to select from the dropdown
	 */
	public void clickOnLeftSelectedMFRFindInMFRDropdown(String mfrToSelect) {
		extentTest.log(Status.INFO, "Click on specific MFR fin in MFR dropdown.");
		enterMFRVizLeftFilterSearchText(mfrToSelect);
		String xpathLocator = "//li[@class='ui-menu-item' and text()='" + mfrToSelect + "']";
		driver.findElement(By.xpath(xpathLocator)).click();
		waitforLoadingRing();
	}

	/***
	 * Click on specific MFR fin in MFR dropdown
	 * 
	 * @param mfrToSelect
	 *            : Get the MFR text to select from the dropdown
	 */
	public void clickOnRightSelectedMFRFindInMFRDropdown(String mfrToSelect) {
		extentTest.log(Status.INFO, "Click on specific MFR fin in MFR dropdown.");
		enterMFRVizRightFilterSearchText(mfrToSelect);
		String xpathLocator = "//li[@class='ui-menu-item' and text()='" + mfrToSelect + "']";
		driver.findElement(By.xpath(xpathLocator)).click();
		waitforLoadingRing();
	}

	/***
	 * Check Search Text is contain in Left Find In MFR dropdown List.
	 * 
	 * @param searchText
	 *            : text which need to check for contains
	 * @return : Get the boolean result.
	 */
	public boolean checkSearchTextContainInFindInMFRList(String searchText) {
		extentTest.log(Status.INFO, "Check Search Text is contain in Left Find In MFR dropdown List.");
		return CommonUtils.checkStringContainInCompleteList(searchText, findInMFRDropDownList);
	}

	public boolean isSearchTextContainInFindInMFRListDisplayed() {
		extentTest.log(Status.INFO, "Check Search Text is contain in Left Find In MFR dropdown List.");
		return CommonUtils.checkElementExist(findInMFRDropDownList.get(0), driver);
	}

	public String getErrorMessageOnBlankChart() {
		SeleniumUtils.isVisible(errorOnBlankChart, driver);
		return errorOnBlankChart.getText().trim();
	}

	public ProjectPlansPage clickOnFirstBarOnLowestSort() {
		extentTest.log(Status.INFO, "Click on First highlighted bar");
		rightSortDropdownOptionsManufacturers.get(0).click();
		return new ProjectPlansPage(driver);
	}

	public boolean checkMFRSaveSearchUnderMySearches(String saveSearch) {
		extentTest.log(Status.INFO, "Check Search Text is contain in Left Find In MFR dropdown List.");
		return CommonUtils.checkStringContainInCompleteList(saveSearch, saveSearchMFRList);
	}

	public boolean checkFilterCrumbContainSeachCriteria(String containString) {
		extentTest.log(Status.INFO, "Check check Filter Crumb Contain Seach Criteria List.");
		boolean result = false;
		for (String crumb : getAllCrumbFilterTextList()) {
			if (crumb.toUpperCase().trim().contains(containString.toUpperCase().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	public void clickOnSpecLinkOnProjects() {
		extentTest.log(Status.INFO, "Clicking on spec Link");
		CommonUtils.scrollDownTillElement(specLink, driver);
		specLink.click();

	}

	public boolean isSyncPlanRoomButtonVisible() {
		extentTest.log(Status.INFO, "Verify syncplanroom button displays");
		return SeleniumUtils.isVisible(planroomSyncbutton, driver);
	}
	
	public List<Integer> getDistributionCountListRightQuadron(){
		extentTest.log(Status.INFO, "get distribution count list Right pane.");
		List<Integer> valueList = new ArrayList<>();
		for (WebElement webElement : distributionCountRightSection) {
			CommonUtils.scrollDownTillElement(webElement, driver);
			valueList.add(Integer.parseInt(webElement.getText().replace(",", "").trim()));
		}
		return valueList;
	}
	
	/**
	 * This method will verify if Left sort drop down option is displayed.
	 * 
	 * @return true if left sort drop down option is displayed.
	 */
	public boolean isLeftSortDropDownOptionDisplayed(){
		extentTest.log(Status.INFO, "Verify if Left sort drop down is displayed.");
		SeleniumUtils.isVisible(leftSortDropdownManufacturers, driver);
		return leftSortDropdownManufacturers.isDisplayed();
	}
	
	/**
	 * This method will verify if Right sort drop down option is displayed.
	 * 
	 * @return true if right sort drop down option is displayed.
	 */
	public boolean isRightSortDropDownOptionDisplayed(){
		extentTest.log(Status.INFO, "Verify if Left sort drop down is displayed.");
		SeleniumUtils.isVisible(rightSortDropdownManufacturers, driver);
		return rightSortDropdownManufacturers.isDisplayed();
	}
	
	public void selectManufacturersUnderSecondChartViewEnhanced() {
		if (!getChart2HeaderDashboard2().equalsIgnoreCase("Manufacturers")) {
			if (!isRightTilesHeaderDisplayed()) {
				clickOnSecondChartCustomizeTile();
				clickOnManufacturersUnderSecondchartViewEnhanced();
				waitforLoadingRing();
			} else {
				if (verifyRightVisualizationStatus("Manufacturers")) {
					clickOnManufacturersUnderSecondchartViewEnhanced();
				} else {
					rightDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}
	
	public void clickOnManufacturersUnderSecondchartViewEnhanced() {
		extentTest.log(Status.INFO, "Click on Manufacturers under second chart");
		System.out.println("hifds");
		manufacturersCategoryInDashboard.get(0).click();
		waitforLoadingRing();
		if(customizeMFRPopupCancelButton.isDisplayed()){
			closePopUpIconEnhanced();
		} else{
			extentTest.log(Status.INFO, "Intermediate pop up is not displayed.");
		}
	}
	
	public void closePopUpIconEnhanced(){
		extentTest.log(Status.INFO, "Close intermediate pop up.");
		SeleniumUtils.isVisible(closePopupIcon, driver);
		closePopupIcon.click();
	}
	
	public void selectManufacturersUnderFirstChartViewEnhanced() {
		if (!getChart1HeaderDashboard2().equalsIgnoreCase("Manufacturers")) {
			if (!isLeftTilesHeaderDisplayed()) {
				clickOnFirstChartCustomizeTile();
				clickOnManufacturersUnderFirstchartViewEnhanced();
				waitforLoadingRing();
				
			} else {
				if (verifyLeftVisualizationStatus("Manufacturers")) {
					clickOnManufacturersUnderFirstchartViewEnhanced();
				} else {
					leftDashboardBackArrow.get(0).click();
					waitforLoadingRing();
				}
			}
		}
	}
	
	public void clickOnManufacturersUnderFirstchartViewEnhanced() {
		extentTest.log(Status.INFO, "Click on Manufacturers under first chart");
		manufacturersCategoryInDashboard.get(0).click();
		waitforLoadingRing();
		if(customizeMFRPopupCancelButton.isDisplayed()){
			closePopUpIconEnhanced();
		} else{
			extentTest.log(Status.INFO, "Intermediate pop up is not displayed.");
		}
	}
	
}
