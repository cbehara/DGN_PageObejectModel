package com.ddaqe.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;
import com.ddaqe.utils.CommonUtils;
import com.ddaqe.utils.SeleniumUtils;

public class CompanyResultsPage {

	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.ID, using = "lblPageSizeGT1000Msg")
	private WebElement pageSizeGT1000Msg;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ddlSortBy")
	private WebElement sortDropdown;

	@FindBy(how = How.ID, using = "stateFacet_1")
	private WebElement firstState;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'companyName')]")
	private List<WebElement> companyTitles;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkProjects')]//ancestor::Div[contains(@class,'companyResult')]//div[@class='companyName']")
	private List<WebElement> companyTitlesWithProjectLink;

	@FindBy(how = How.XPATH, using = "//div[@class='clearfix']//input[@class='checkbox']")
	private List<WebElement> companyTitleCheckboxes;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'BreadCrumb')]")
	private WebElement BreadCrumbSection;

	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//div[@class='appliedFilter filterPanelCon']")
	private List<WebElement> FilterCrumb_AppliedFilter_List;

	@FindBy(how = How.ID, using = "ctl00_lnkNotes")
	private WebElement notesLink;

	@FindBy(how = How.XPATH, using = "//a[text()='My Companies']/../..//strong[text()='Tracking Lists: ']")
	private List<WebElement> myProjectTrackingNameResultList;

	@FindBy(how = How.CLASS_NAME, using = "listCompanyCount")
	private WebElement companyCountTxt;

	@FindBy(how = How.ID, using = "ctl02_lnkNotes")
	private WebElement notesLinkThird;

	@FindBy(how = How.ID, using = "ctl00_lblTrack")
	private WebElement trackLink;

	@FindBy(how = How.LINK_TEXT, using = "Remove")
	private WebElement removeLink;

	@FindBy(how = How.LINK_TEXT, using = "Alert On")
	private WebElement alertOnLink;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_structuralPropLink")
	private WebElement addProperties;

	@FindBy(how = How.XPATH, using = "//*[@class='trackActions']//span[contains(@id,'lblTrack')]")
	private List<WebElement> trackLinks;

	@FindBy(how = How.ID, using = "lblErr")
	private WebElement errLabel;

	@FindBy(how = How.ID, using = "countryFacet_child_1")
	private WebElement countryUKChkbox;

	@FindBy(how = How.ID, using = ".//*[@id='projectCountryFacet_1']")
	private WebElement ProjectCanadaChkbox;

	@FindBy(how = How.ID, using = "Prev4")
	private WebElement pageNumber4;

	@FindBy(how = How.ID, using = "lnkTrackCompanies")
	private WebElement trackCompanyActionsLink;

	@FindBy(how = How.ID, using = "lnkRemoveCompanies")
	private WebElement lnkRemoveCompanies;

	@FindBy(how = How.ID, using = "lnkHostCompanies")
	private WebElement hostCompaniesActionsLink;
	@FindBy(how = How.XPATH, using = "//a[@class='docupro-popup-close']")
	private WebElement closeDocupropopup;
	@FindBy(how = How.XPATH, using = "//a[@class='docupro-popup-submit']")
	private WebElement transferDocupropopup;
	@FindBy(how = How.ID, using = "lnkHostProject")
	private WebElement hostProjectActionsLink;
	@FindBy(how = How.CSS, using = "#hostToDocuProPopup .header")
	private WebElement hostToDocuProPopupHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkContacts')]")
	private List<WebElement> contactsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkContacts')]//following-sibling::a[contains(@id,'lnkProjects')]//ancestor::Div[contains(@class,'companyResultSection')]//a[contains(@id,'lblCompanyTitle')]")
	private List<WebElement> companyTitleWithContactsAndProjects;

	@FindBy(how = How.XPATH, using = "(.//a[contains(@id,'lnkProjects') and contains(@href,'CompanyProjects.aspx')])[1]")
	private WebElement projectsLink;

	@FindBy(how = How.XPATH, using = " .//*[contains(@id,'lblProjectLabelText') and text()>\"2\"]//..//..//..//..//following-sibling::div[contains(@class,'profileContactsContainer')]//a[contains(@id,'lnkProjects')]")
	private WebElement containsProjectsLink;

	@FindBy(how = How.ID, using = "ctl00_lnkProfile")
	private WebElement companyProfileLink;

	@FindBy(how = How.ID, using = "ctl00_chkCompanySelect")
	private WebElement firstProjectchkBox;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private WebElement rotateLoadingIcon;

	@FindBy(how = How.ID, using = "company-select-all")
	private WebElement projectSelectAll;

	@FindBy(how = How.XPATH, using = "//span[@id='rotate']")
	private List<WebElement> rotateLoadingIconInvisible;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_Publishdate_pubSelector")
	private WebElement PublishRange_DropdownOptionTxt;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']//span[contains(@class,'arrow-down')]")
	private WebElement ProjectGrups_Filter_ArrowDown;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'companyResultSection')][1]//a[contains(@id,'lnkTrackList')]")
	private List<WebElement> firstCompanytrackingListLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkContacts')]//ancestor::div[contains(@class,'companyResultSection')]//div[contains(@class,'companyName')]//a")
	private List<WebElement> companyTitleswithContactsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkProjects')]//ancestor::Div[contains(@class,'companyResultSection')]//a[contains(@id,'CompanyTitle')]")
	private List<WebElement> companyTitlewithProjects;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkNotes')]//ancestor::Div[contains(@class,'companyResultSection')]//a[contains(@id,'CompanyTitle')]")
	private List<WebElement> companyTitlewithNotes;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'indicator')]//img")
	private List<WebElement> trackingIconList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'companyDetails')]//div[1][not(img)]//following-sibling::div[contains(@class,'companyName')]")
	private List<WebElement> companyNotTrackedList;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']//span[contains(@class,'arrow-down')]")
	private WebElement companyGeographyCountryArrowDown;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']")
	private WebElement companyGeographyCountry;

	@FindBy(how = How.XPATH, using = "//label[text()='Canada']/..//input[@type='checkbox']")
	private WebElement canadaCompanyLocChk;

	@FindBy(how = How.CSS, using = ".GEOGRAPHY_COUNTRY div[class*='index']")
	private List<WebElement> companyLocationsCheckBoxes;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CATEGORY']")
	private WebElement companyPrjActionStageFilter;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='ACTION_STAGE_CATEGORY']//span[contains(@class,'arrow-down')]")
	private WebElement companyPrjActionStageArrowDown;

	@FindBy(how = How.XPATH, using = "//label[text()='Design']/..//input[@type='checkbox']")
	private WebElement designPrjActionStageChk;

	@FindBy(how = How.ID, using = "ownershipTypeFacet_1")
	private WebElement prjOwnershipTypeChk;

	@FindBy(how = How.LINK_TEXT, using = "HOME")
	private WebElement homeMenuLink;

	@FindBy(how = How.XPATH, using = "//label[text()='Pre-Design']/..//input[@type='checkbox']")
	private WebElement pre_designPrjActionStageChk;

	// *
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_DELIVERY_SYSTEM']")
	private WebElement ProjectDeliveryFilter;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_CompanyActions_btnCompanyActions")
	private WebElement actionsDropdowm;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_companyNavigator_actionsNav_btnCompanyActions")
	private WebElement actionsDropdowm_companyNavigator;

	@FindBy(how = How.ID, using = "company-select-all")
	private WebElement companySelectAll;

	@FindBy(how = How.ID, using = "ctl00_chkCompanySelect")
	private WebElement companySelectChk;
	@FindBy(how = How.ID, using = "ctl00_chkProjectSelect")
	private WebElement ProjectchkBox;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'chkCompanySelect')]")
	private List<WebElement> companySelectChkList;

	@FindBy(how = How.ID, using = "ctl00_chkProjectSelect")
	private WebElement secondProjectchkBox;
	@FindBy(how = How.ID, using = "ctl02_chkProjectSelect")
	private WebElement thirdProjectchkBox;

	@FindBy(how = How.ID, using = "ctl01_chkCompanySelect")
	private WebElement secondCompanychkBox;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='COMPANY_TYPE_GROUP']//span[contains(@class,'arrow-down')]")
	private WebElement companyTypeArrowDownIcon;

	@FindBy(how = How.ID, using = "ctl00_trackListsLabel")
	private WebElement trackingListLabel;

	@FindBy(how = How.CSS, using = "[id*='ddlPageSize']")
	private List<WebElement> resultsPerPage;

	@FindBy(how = How.LINK_TEXT, using = "Download Companies")
	private WebElement downloadCompaniesMenu;

	@FindBy(how = How.ID, using = "lnkEmailCompanies")
	private WebElement emailCompaniesMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_saveSearchCompany")
	private WebElement saveSearchBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private WebElement LoadingRingOnPopop;

	@FindBy(how = How.XPATH, using = "//div[@id='fancybox-loading']/div")
	private List<WebElement> LoadingRingOnPopopInvisibleCheck;

	@FindBy(how = How.ID, using = "fancybox-close")
	private WebElement seeMoreSpecAlertsCloseIcon;

	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecAlert']//span")
	private List<WebElement> specAlertsSeeMorePopUpProgramsList;

	@FindBy(how = How.ID, using = "fancybox-frame")
	private WebElement PopupFrame;

	@FindBy(how = How.ID, using = "selectAll")
	private WebElement selectAllSeeMoreSpecAlerts;

	@FindBy(how = How.ID, using = "btnUpdateFancyBox")
	private WebElement seeMoreSpecAlertsUpdateBtn;

	@FindBy(how = How.CLASS_NAME, using = "moreFilterPage")
	private WebElement seeMoreSpecAlertsPopUp;

	@FindBy(how = How.LINK_TEXT, using = "Print Company List")
	private WebElement printCompanyListLink;

	@FindBy(how = How.LINK_TEXT, using = "Print Company Details")
	private WebElement printCompanyDetailsLink;

	@FindBy(how = How.XPATH, using = "	//*[@id='pr-page-list']/span")
	private WebElement highlightedpageNumber;

	@FindBy(how = How.ID, using = "lnkTrackCompanies")
	private WebElement trackProjectsMenu;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_listCompanyCountText")
	private WebElement paginationSectionText;

	@FindBy(how = How.XPATH, using = ".//*[@id='company-results-top']/div[4]/span")
	private WebElement paginationPowerBanksectionText;

	@FindBy(how = How.LINK_TEXT, using = "View Companies")
	private WebElement viewCompaniesActionsOptions;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI')]/div[contains(@class,'expendCollaps')]/span[contains(@class,'projFilter')]")
	private List<WebElement> leftProjectFiltersList;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_specAlertFilter']//span[contains(@class,'fright')]")
	private WebElement leftSpecAlertsDefaultCollapse;

	@FindBy(how = How.ID, using = "rbCSV")
	private WebElement csvRadioBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_specAlertFilter']/div[contains(@class,'expendCollaps ')]/a/img")
	private WebElement specAlertsSeeMoreIcon;

	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecAlert']/div[@class='facetCheckBoxes']//span")
	private List<WebElement> specAlertSeeMorePopUpList;

	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecAlert']/div[@class='facetCheckBoxes']//input")
	private List<WebElement> specAlertSeeMorePopUpListChkBox;

	@FindBy(how = How.XPATH, using = "//div[@id='dvSpecAlert']//div[@class='lastLevelChkBox']//input[@type='checkbox']")
	private List<WebElement> specAlertSeeMoreListChkBox;

	@FindBy(how = How.XPATH, using = "//div[@class='excludeProjects']//label")
	private WebElement specAlertSeeMoreExcludeProjects;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//a[contains(@class,'grpItems')]")
	private WebElement specAlertAppliedFilter;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//a[contains(@class,'grpItems')]//ancestor::div[contains(@class,'appliedFilter ')]/a[contains(@class,'removeIcon')]")
	private WebElement specAlertAppliedFilterRemoveIcon;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'appliedFilter')]//span[@title='Spec Alert']")
	private List<WebElement> specAlertFewAppliedFilter;

	@FindBy(how = How.LINK_TEXT, using = "Alert Off")
	private WebElement alertoff;

	@FindBy(how = How.LINK_TEXT, using = "Alert On")
	private WebElement alerton;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_CompanyResult_dvCompresults']//img")
	private WebElement alertIndicatorIcon;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ProjectHeader_HeaderActions_lnkPrintProjectDetails")
	private WebElement printProjectDetailsLink;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'searchText')]")
	private WebElement searchTxtField;

	@FindBy(how = How.ID, using = "power-rank-results")
	private WebElement powerRank;

	@FindBy(how = How.XPATH, using = "//div[@class='power-rank-text-con']/span")
	private WebElement powerRankedResults_txt;

	@FindBy(how = How.ID, using = "powerRankingSteps")
	private WebElement powerRankModalmessageDialogBox;

	@FindBy(how = How.ID, using = "powerRankingLoadingPopup")
	private WebElement powerRankingLoadingPopup;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_btnSearch")
	private WebElement searchButton;

	@FindBy(how = How.ID, using = "powerRankingLoadingPopup")
	private List<WebElement> powerRankingLoadingPopupInvisible;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='COMPANY_TYPE_GROUP']")
	private WebElement CompanyTypeFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='COMPANY_TYPE_GROUP']/a/img")
	private WebElement CompanyTypeFilterSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='COMPANY_TYPE_GROUP']//span[contains(@class,'arrow-up')]")
	private WebElement CompanyTypeFilter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'compTypeGroupFacet')]")
	private List<WebElement> CompanyTypeGrp_LHS_ParentFilterList;
	@FindBy(how = How.ID, using = "primaryComp")
	private WebElement PrimaryCompany_Type_cbk;

	@FindBy(how = How.XPATH, using = "//div[@class='COMPANY_TYPE_GROUP']//label/span")
	private List<WebElement> COMPANY_TYPE_GROUP_ProjectCountNextToElement_List;

	@FindBy(how = How.CLASS_NAME, using = "power-rank-text-con")
	private WebElement powerRankText;

	@FindBy(how = How.CSS, using = "div[id$='resultcontent']")
	private WebElement resultContent;

	@FindBy(how = How.CLASS_NAME, using = "appliedFilter")
	private WebElement filterSavedSearch;

	@FindBy(how = How.ID, using = "aClearAll")
	private WebElement clearAllSearch;

	@FindBy(how = How.ID, using = ".//*[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_geoProjectCountryCon']/div[1]/span[2]")
	private WebElement clickonProjectLocationDrpdwnbtn;

	@FindBy(how = How.XPATH, using = "//div[@class='COMPANY_TYPE_GROUP']//label")
	private List<WebElement> COMPANY_TYPE_GROUP_LHS_lbl_list;

	@FindBy(how = How.XPATH, using = "//div[@class='subSectionLabel']/span")
	private List<WebElement> COMPANY_TYPE_GROUP_PopupParentFilter_lblList;

	@FindBy(how = How.XPATH, using = "//span[@class='listCompanyCountText']")
	private WebElement CompanyResultCount;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_SavedSearchDisplay_lblSavedSearchName")
	private WebElement savedSearchName;
	@FindBy(how = How.XPATH, using = "//div[@class='facetSelectAllLabel']/span")
	private WebElement popupSelectall_lbl;
	@FindBy(how = How.ID, using = "Prev_Previous_Page")
	private WebElement Previous_Page;
	@FindBy(how = How.ID, using = "Prev_Next_Page")
	private WebElement Next_Page;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblAddress')]")
	private List<WebElement> Companylocation;

	@FindBy(how = How.CSS, using = "span[id*='lblAddress']")
	private List<WebElement> companyAddress;

	@FindBy(how = How.CSS, using = "span[id*='lblPhoneText']")
	private List<WebElement> companyPhone;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_structuralPropLink")
	private WebElement StructuralPropLink;

	@FindBy(how = How.ID, using = "companyStateNav")
	private WebElement geographyPopupStateLink;
	@FindBy(how = How.ID, using = "companyCountyNav")
	private WebElement GEOGRAPHYPopupprojectCountyNavLink;
	@FindBy(how = How.XPATH, using = "//*[@id='dvCompanyState']//input[@id='selectAll']")
	private WebElement StateRegionPopupSelectAllStates_checkbox;
	@FindBy(how = How.XPATH, using = "//*[@id='dvCompanyCounty']//input[@id='selectAll']")
	private WebElement CountyPopupSelectAllStates_checkbox;

	@FindBy(how = How.XPATH, using = "//img[@src='images/map_icon.png']")
	private WebElement mapIconLink;
	@FindBy(how = How.XPATH, using = "//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']//div[@class='appliedFilter filterPanelCon']/span")
	private List<WebElement> FilterCrumb_AppliedFilter_TitleList;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_COUNTRY']")
	private WebElement ProjectLocationFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_COUNTRY']/a/img")
	private WebElement ProjectLocationFilterSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_COUNTRY']//span[contains(@class,'arrow-up')]")
	private WebElement ProjectLocation_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'projectCountryFacet')]")
	private List<WebElement> ProjectLocation_LHS_ParentFilterList;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_STATE']")
	private WebElement ProjectStateFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_STATE']/a/img")
	private WebElement ProjectStateFilterSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_STATE']//span[contains(@class,'arrow-up')]")
	private WebElement ProjectState_Filter_ArrowUp;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_GEOGRAPHY_STATE']/a/img")
	private WebElement ProjectLocationStateFilterSeeMore_Btn;
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and starts-with(@id,'projTypeCatFacet')]")
	private List<WebElement> ProjectType_LHS_ParentFilterList;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_PUBLISH_RANGE']")
	private WebElement ProjPublishDateFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_PUBLISH_RANGE']//span[contains(@class,'arrow-up')]")
	private WebElement ProjPUBLISH_Date_Filter_ArrowUp;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SEARCH_COMPANY_FIELD']")
	private WebElement SearchCompanyFiledsFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='SEARCH_COMPANY_FIELD']//span[contains(@class,'arrow-up')]")
	private WebElement SearchCompanyFileds_ArrowUp;
	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_companyName")
	private WebElement CompanyName_textbox;
	@FindBy(how = How.XPATH, using = "//a[@class='buttonClass fright']")
	private WebElement CompanySearch_button;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_geoCountryCon']//span[contains(@class,'arrow-up')]")
	private WebElement companyLocationFilter_Arrowup;

	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_geoCountryCon>div:nth-of-type(1)")
	private WebElement companyLocationFilter;
	@FindBy(how = How.CSS, using = "#ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_geoCountryCon span[class*='arrow-down']")
	private WebElement companyLocationFilterArrowDownIcon;

	@FindBy(how = How.ID, using = "power-rank-results")
	private WebElement powerRankResultsBtn;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblValuationLabelText')]")
	private List<WebElement> valuationTextList;

	@FindBy(how = How.XPATH, using = "//span[contains(@id,'lblProjectLabelText')]")
	private List<WebElement> totalProjectCountList;

	@FindBy(how = How.ID, using = "	filter")
	private WebElement filters;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchSpan")
	private WebElement mySearchesDropDown;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesSpecAlertDD")
	private WebElement mySearchesSpecAlerts;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesTrackingListDD")
	private WebElement mySearchesTrackingLists;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_companytypeConWrapper']/div[1]/label")
	private WebElement PrimaryCompanyType;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_mySearchesSavedSearchDD")
	private WebElement mySearchesSaveSearches;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_geoProjectCountryCon']/div[1]/span[2]")
	private WebElement ProjectLocationDropdown;

	@FindBy(how = How.XPATH, using = "//div[@class='paginationWrapper']")
	private List<WebElement> paginationSectionOnPage;

	@FindBy(how = How.ID, using = "Prev1")
	private WebElement companyFirstPageNavigation;

	@FindBy(how = How.XPATH, using = ".//*[@id='ctl00_contentPlaceHolderHeader_filternavUI_filterSectionContainer']/div/div[2]/div[1]/span/span/a")
	private WebElement CompanyBreadcrumbFilterlink;

	@FindBy(how = How.XPATH, using = ".//*[@id='filterpop']")
	private WebElement FilterslinkPopup;

	@FindBy(how = How.ID, using = "Prev2")
	private WebElement companySecondPageNavigation;

	@FindBy(how = How.ID, using = "Prev3")
	private WebElement companyThirdPageNavigation;

	@FindBy(how = How.ID, using = "Prev4")
	private WebElement companyFourPageNavigation;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/arrow-left.png']")
	private WebElement previousCompanyPage;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/arrow-right.png']")
	private WebElement nextCompanyPage;

	@FindBy(how = How.XPATH, using = "//img[@src='Images/arrow-double-left.png']")
	private WebElement firstPageCompanyPageBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_controlWrapper']//div[@id='ctl00_contentPlaceHolderHeader_pcTop_selectAllWrapperCon']")
	private WebElement selectAllRedesignWrap;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_controlWrapper']//div[@id='ctl00_contentPlaceHolderHeader_pcTop_ActionWrapperCon']")
	private WebElement actionDropdownRedesignWrap;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_pcTop_controlWrapper']//div[@id='ctl00_contentPlaceHolderHeader_pcTop_sortDropDownWrapper']")
	private WebElement sortDropdownRedesignWrap;

	@FindBy(how = How.XPATH, using = "//div[@expandpref='GEOGRAPHY_COUNTRY']")
	private WebElement companyFilter;

	@FindBy(how = How.XPATH, using = "//div[@id='additionalFilter']")
	private WebElement addtionalFilter;

	@FindBy(how = How.ID, using = "actionStageCatFacet_1")
	private WebElement FilterActionStage1;

	@FindBy(how = How.ID, using = "actionStageCatFacet_2")
	private WebElement FilterActionStage2;

	@FindBy(how = How.ID, using = "actionStageCatFacet_3")
	private WebElement FilterActionStage3;

	@FindBy(how = How.ID, using = "//label[contains(@for,'actionStageCatFacet')]/..//input[@type='checkbox']")
	private List<WebElement> FilterActionStage4;

	@FindBy(how = How.XPATH, using = "//div[@id='ctl00_contentPlaceHolderHeader_LeftPanelCompanyFiltersUI_projectFilter']")
	private WebElement leftNavFilter;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_MySearchesDropDown_dropdownList")
	private WebElement mySearchesdropDownListBtn;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_keywordSearch_searchwrapper")
	private WebElement keyWordSearchTextArea;

	@FindBy(how = How.CSS, using = "[class*='powerRankCancel']>a")
	private WebElement powerRankCancelBtn;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']")
	private WebElement expandProjectGroup;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='PROJECT_TYPE_CATEGORY']//span[contains(@class,'arrow-up')]")
	private WebElement ProjectType_Filter_ArrowUp;
	@FindBy(how = How.ID, using = "projTypeCatFacet_6")
	private WebElement clickonCommercialchbox;
	@FindBy(how = How.ID, using = "projTypeCatFacet_4")
	private WebElement clickonBridgechbox;
	@FindBy(how = How.ID, using = "projTypeCatFacet_5")
	private WebElement clickonApartments_Condominiumschbox;
	@FindBy(how = How.ID, using = "projTypeCatFacet_3")
	private WebElement clickonAnimal_Plant_Fish_Facilitieschbox;
	@FindBy(how = How.ID, using = "projTypeCatFacet_2")
	private WebElement clickonAirPollutionControlchbox;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_pcTop_ddlSortBy")
	private WebElement clickOnSortDropDownButton;
	@FindBy(how = How.NAME, using = "ctl00$contentPlaceHolderHeader$pcTop$ddlSortBy")
	private WebElement selectOption;

	@FindBy(how = How.XPATH, using = ".//div[@class='appliedFilter filterPanelCon']//span//span")
	private List<WebElement> filterCrumbDisplayed;

	@FindBy(how = How.CSS, using = "a[id*='lblCompanyTitle']")
	private List<WebElement> getCompanyNameList;

	@FindBy(how = How.CSS, using = "span[id*='lblProjectLabelTe']")
	private List<WebElement> getNumberOfProjectList;

	@FindBy(how = How.CSS, using = "span[id*='lblValuationLabelText']")
	private List<WebElement> getProjectValuatonList;

	@FindBy(how = How.XPATH, using = ".//div[@class='appliedFilter filterPanelCon']//span//span//a[@class='grpItems']")
	private WebElement showMoreControl;

	@FindBy(how = How.LINK_TEXT, using = "PROJECTS")
	private WebElement projectsLinkTab;

	@FindBy(how = How.CSS, using = "span[class='fleft filterHeader']")
	private List<WebElement> FilterOnCompanyResultPage;

	@FindBy(how = How.XPATH, using = ".//div[@class='popupResults']//span//span")
	private List<WebElement> popUpFilterCrumbDisplayed;

	@FindBy(how = How.ID, using = "PowTitle")
	private WebElement powerRankButtonHoverText;

	@FindBy(how = How.XPATH, using = ".//*[@id='divCompanyDetails']/div[3]/div[6]/a[2]/span")
	private WebElement leftNavAfterPoweRanked;

	@FindBy(how = How.ID, using = "PowTitleDrawer")
	private WebElement leftNavAfterPoweRankedHoverText;

	@FindBy(how = How.ID, using = "lnkDownloadCompanies")
	private WebElement downloadCompanyActionMenu;

	private List<WebElement> Link4;

	// Bidding Within
	@FindBy(how = How.XPATH, using = "//div[@expandpref='OWNERSHIP_TYPE']")
	private WebElement prjOwnerTypeFilter;
	@FindBy(how = How.XPATH, using = "//div[@expandpref='OWNERSHIP_TYPE']//span[contains(@class,'arrow-down')]")
	private WebElement prjOwnerTypeFilterArrowDownIcon;

	@FindBy(how = How.XPATH, using = ".//*[@id='divCompanyDetails']/div[3]/div[6]/a[2]")
	private WebElement PowerLinKCanNotClick;

	public void clickOnResultLink() {
		extentTest.log(Status.INFO, "Click on first check box");
		FilterActionStage1.click();
		FilterActionStage2.click();
		FilterActionStage3.click();
	}

	public void ClickOncrumbSecondFilterLink() {
		extentTest.log(Status.INFO, "Click on second crumb filter");
		Link4.get(0).click();
	}

	public void clickOnCompanyLocationFilter() {
		extentTest.log(Status.INFO, "Clicking on company location Filter");
		SeleniumUtils.isVisible(companyLocationFilter_Arrowup, driver);
		if (CommonUtils.checkElementExist(companyLocationFilter_Arrowup, driver)) {
			companyLocationFilter.click();
			waitforLoadingRing();
		}
	}

	public void clickOnUKCountryChkbox() {
		extentTest.log(Status.INFO, "Clicking on UK Country checkbox.");
		countryUKChkbox.click();
	}

	public void clickOnPowerRankResultNotFiltered() {
		extentTest.log(Status.INFO, "Click on power rank link");
		PowerLinKCanNotClick.click();
	}

	public void clickOnFirstStateChkbox() {
		extentTest.log(Status.INFO, "Clicking on first state.");
		firstState.click();
	}

	public void clickOnCanadaChkbox() {
		extentTest.log(Status.INFO, "Clicking on Afghanistan checkbox.");
		ProjectCanadaChkbox.click();
	}

	public String getPublishRange_DropDown_OptionText() {
		extentTest.log(Status.INFO, "Get drop down option text");
		return PublishRange_DropdownOptionTxt.getText();
	}

	public int getFilterCrumb_AppliedFilterList_Size() {
		extentTest.log(Status.INFO, "Get FilterCrumb_AppliedFilter_List size");
		return FilterCrumb_AppliedFilter_List.size();
	}

	public String getTextEnteredInTheSearchBox() {
		extentTest.log(Status.INFO, "Getting the text entered in the search box");
		SeleniumUtils.scrollToView(driver, searchTxtField);
		return searchTxtField.getAttribute("value");
	}

	public void enteredInTheSearchBox(String str) {
		extentTest.log(Status.INFO, "enter value in the search box");
		SeleniumUtils.scrollToView(driver, searchTxtField);
		searchTxtField.sendKeys(str);

	}

	public List<String> VerifyResultDropdownValues() {
		Select select = new Select(resultsPerPage.get(0));
		List<WebElement> options = select.getOptions();
		List<String> dropdownValues = new ArrayList<String>();
		for (WebElement we : options) {
			dropdownValues.add(we.getText());
		}
		return dropdownValues;
	}

	public CompanyResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Company Results Page");
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public CompanyPage clickOnFirstCompanyTitle() {
		extentTest.log(Status.INFO, "Clicking on First Company Title");
		SeleniumUtils.isVisible(companyTitles.get(0), driver);
		companyTitles.get(0).click();
		return new CompanyPage(driver);
	}

	// Clicking on First Company Title with project link.
	public CompanyPage clickOnFirstCompanyTitleWithProjectLink() {
		extentTest.log(Status.INFO, "Clicking on First Company Title with project link.");
		companyTitlesWithProjectLink.get(0).click();
		return new CompanyPage(driver);
	}

	public void clickOnClearAllSearch() {
		extentTest.log(Status.INFO, "Clicking on Clear All Search");
		clearAllSearch.click();
	}

	public boolean checkClearAllSearchIsDisplayed() {
		extentTest.log(Status.INFO, "Clicking on Clear All Search");
		return SeleniumUtils.isVisible(clearAllSearch, driver);
	}

	public boolean checkFilterslinkpopupIsDisplayed() {
		extentTest.log(Status.INFO, "Clicking on Clear All Search");
		return FilterslinkPopup.isDisplayed();
	}

	public boolean isSavedSearchFilterDisplayed() {
		extentTest.log(Status.INFO, "Check if Saved Search Filter is displayed");
		return filterSavedSearch.isDisplayed();
	}

	public void selectCompaniesByIndex(List<Integer> companyIndexes) {
		for (Integer companyIndex : companyIndexes) {
			if (companyIndex < companyTitleCheckboxes.size()) {
				companyTitleCheckboxes.get(companyIndex).click();
			}
		}
	}

	public CompanyPage clickOnCompanyTitleByIndex(int index) {
		extentTest.log(Status.INFO, "Clicking on Company Title by index.");
		companyTitles.get(index).click();
		return new CompanyPage(driver);
	}

	public CompanyPage clickOnThirdCompanyTitle() {
		extentTest.log(Status.INFO, "Clicking on Third Company Title");
		companyTitles.get(2).click();
		return new CompanyPage(driver);
	}

	public CompanyCommonContainerPage clickOnFirstCompanyTitleCommon() {
		extentTest.log(Status.INFO, "Clicking on First Company Title");
		companyTitles.get(0).click();
		return new CompanyCommonContainerPage(driver);
	}

	public void clickOnSearchButton() {
		extentTest.log(Status.INFO, "Clicking on Search Button");
		searchButton.click();
	}

	public CompanyPage clickOnCompanyTitleWithProjects() {
		extentTest.log(Status.INFO, "Clicking on Company Title which has projects enabled");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(companyTitlewithProjects));
		companyTitlewithProjects.get(0).click();
		return new CompanyPage(driver);
	}

	public CompanyPage clickOnCompanyTitleWithoutNotes() {
		extentTest.log(Status.INFO, "Clicking on Company Title without Notes");
		companyTitles.get(1).click();
		return new CompanyPage(driver);
	}

	public CompanyPage clickOnCompanyTitleWithoutTracked() {
		extentTest.log(Status.INFO, "Clicking on Company Title without Tracked");
		SeleniumUtils.scrollToView(this.driver, companyNotTrackedList.get(0));
		companyNotTrackedList.get(0).click();
		return new CompanyPage(driver);
	}

	public CompanyNotesPage clickOnNotesLink() {
		extentTest.log(Status.INFO, "Click on the Notes Link");
		notesLink.click();
		return new CompanyNotesPage(driver);
	}

	public CompanyContactsPage clickOnCompanyContactsLink() {
		extentTest.log(Status.INFO, "Click on the Company Contacts Link");
		contactsLink.get(0).click();
		return new CompanyContactsPage(driver);
	}
	
	public CompanyPage clickOnCompanyTitleWithContactsAndProjects() {
		extentTest.log(Status.INFO, "Click on the Company link with Contacts and Projects.");
		companyTitleWithContactsAndProjects.get(0).click();
		return new CompanyPage(driver);
	}
	

	public CompanyProjectsPage clickOnCompanyProjectsLink() {
		extentTest.log(Status.INFO, "Click on the Company Projects Link");
		projectsLink.click();
		return new CompanyProjectsPage(driver);
	}

	public CompanyProjectsPage clickOnCompanythatcontainsProjectsLink() {
		extentTest.log(Status.INFO, "Click on the Company Projects Link");
		containsProjectsLink.click();
		return new CompanyProjectsPage(driver);
	}

	public CompanyPage clickOnCompanyProfileLink() {
		extentTest.log(Status.INFO, "Click on the Company Profile Link");
		companyProfileLink.click();
		return new CompanyPage(driver);
	}

	// Check Company Profile Link is displayed.
	public boolean isCompanyProfileLinkDisplayed() {
		extentTest.log(Status.INFO, "Check Company Profile Link is displayed.");
		return companyProfileLink.isDisplayed();
	}

	public TrackPopUpPage clickOnTrackCompanyActionsLink() {
		extentTest.log(Status.INFO, "Click on the Track Company Actions Link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyActionsLink));
		trackCompanyActionsLink.click();
		return new TrackPopUpPage(driver);
	}

	public boolean isTrackCompanyActionsLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of Track Companies Actions Link");
		return trackCompanyActionsLink.isDisplayed();
	}

	public boolean isRemoveCompaniesActionsLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of Remove Companies Actions Link");
		return lnkRemoveCompanies.isDisplayed();
	}

	public void clickOnRemoveCompaniesActionsLink() {
		extentTest.log(Status.INFO, "Click on Remove Companies Actions Link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(lnkRemoveCompanies));
		lnkRemoveCompanies.click();
		;
	}

	public boolean isViewCompaniesActionsLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of View Companies Actions Link");
		return viewCompaniesActionsOptions.isDisplayed();
	}

	public void clickOnPageNumber4() {
		extentTest.log(Status.INFO, "Click on Fourth Page number.");
		pageNumber4.click();
		waitforLoadingRing();

	}

	public void CloseProjectGroupsFilter() {
		extentTest.log(Status.INFO, "Close PROJECT Group_Filter accordion");
		ProjectGrups_Filter_ArrowDown.click();
	}

	public void clickOnSelectAllProjects() {
		extentTest.log(Status.INFO, "Click on Select All in the Projects List");
		if (!projectSelectAll.isSelected()) {
			projectSelectAll.click();
		}
	}

	public void clickOnFilterslinkinBreadcrumb() {
		extentTest.log(Status.INFO, "Click on filterlinkinsearch");
		CompanyBreadcrumbFilterlink.click();
	}

	public TrackPopUpPage clickOnTrackLink() {
		extentTest.log(Status.INFO, "Click on the Track Link from the Company Summary");
		trackLink.click();
		return new TrackPopUpPage(driver);
	}

	public void clickOnSelectAllCompany() {
		extentTest.log(Status.INFO, "Click on Select All in the Company List");
		companySelectAll.click();
	}

	// Check Select All company is selected.
	public boolean checkSelectAllCompanyIsSelected() {
		extentTest.log(Status.INFO, "Check Select All company is selected.");
		return companySelectAll.isSelected();
	}

	public void clickOnActionsDropdown() {
		extentTest.log(Status.INFO, "Click on Actions Dropdown");
		actionsDropdowm.click();
	}

	public void clickOnFirstCompanyChkBox() {
		extentTest.log(Status.INFO, "Click on First Check Box in the Company List");
		if (!companySelectChk.isSelected()) {
			companySelectChk.click();
		}
	}

	public void clickOnSecondCompanyChkBox() {
		extentTest.log(Status.INFO, "Click on Second Check Box in the Company List");
		secondCompanychkBox.click();
	}

	public void clickOnSecondProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on second project checkbox");
		SeleniumUtils.isVisible(secondCompanychkBox, driver);
		if (!secondCompanychkBox.isSelected()) {
			secondCompanychkBox.click();
		}
	}

	public String getURL() {
		extentTest.log(Status.INFO, "Get URL");
		return driver.getCurrentUrl();
	}

	public Boolean IsPageNumber4HighLightedInBold() {
		extentTest.log(Status.INFO, "Check if Page number 4 is highlighted in bold");
		return CommonUtils.isFontBold(driver, pageNumber4);

	}

	public String getNoteslinkTooltipText(String noteText) throws InterruptedException {
		Actions ToolTip = new Actions(driver);
		SeleniumUtils.scrollToView(driver, notesLinkThird);
		ToolTip.clickAndHold(notesLinkThird).build().perform();
		// waiting for dynamic div which fetches tooltip data to load
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.attributeContains(notesLinkThird, "original-title", noteText));
		String ToolTipText = notesLinkThird.getAttribute("original-title");
		return ToolTipText;
	}

	public boolean verifyFirstTrackingListNameExists(String TrackName) {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		CommonUtils.IterateWebElementsList(firstCompanytrackingListLink);
		return CommonUtils.getListFromWebElements(firstCompanytrackingListLink).contains(TrackName);
	}

	public boolean verifyFirstExistingTrackingListNameExists(List<String> TrackName) {
		extentTest.log(Status.INFO, "Verify Selected Tracking List Name");
		CommonUtils.IterateWebElementsList(firstCompanytrackingListLink);
		return CommonUtils.getListFromWebElements(firstCompanytrackingListLink).contains(TrackName);
	}

	public boolean isTrackingIconDisplayed(int index) {
		extentTest.log(Status.INFO, "Verify Tracking Icon displayed");
		return trackingIconList.get(index).isDisplayed();
	}

	public boolean isDownLoadCompaniesMenuDisplayed() {
		extentTest.log(Status.INFO, "Verify if Download companies is displayed");
		return downloadCompaniesMenu.isDisplayed();
	}

	public Boolean isBreadCrumbSectionDisplayed() {
		if (BreadCrumbSection.isDisplayed())
			return true;
		else
			return false;

	}

	public Boolean mouseOverActionandChecktrackCompaniesDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Track Company is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackCompanyActionsLink));
		return trackCompanyActionsLink.isDisplayed();
	}

	public DownloadCompanies mouseOverActionandClickDownloadCompanies() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Download Companies is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(downloadCompaniesMenu));
		downloadCompaniesMenu.click();

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("rbPdf")));
		return new DownloadCompanies(driver);
	}

	public void clickOnDownloadCompaniesActionMenu() {
		extentTest.log(Status.INFO, "Click on Download companies link.");
		SeleniumUtils.isClickable(downloadCompaniesMenu, driver);
		downloadCompaniesMenu.click();
	}

	public DownloadCompanies clickOnDownloadCompaniesActionMenuwithReturn() {
		extentTest.log(Status.INFO, "Click on Download companies link.");
		downloadCompaniesMenu.click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(csvRadioBtn));
		return new DownloadCompanies(driver);
	}

	public boolean isErrorMessageDisplayed() {
		return errLabel.isDisplayed();
	}

	public String getErrorMessage() {
		extentTest.log(Status.INFO, "Get the error message");
		SeleniumUtils.isVisible(errLabel, driver);
		return errLabel.getText().trim();
	}

	// returns the isDisplayed status of arrow down icon for company type filter
	public boolean checkCompanyTypeArrowDown() {
		return CommonUtils.checkElementExist(companyTypeArrowDownIcon, driver);
	}

	public void selectResultsPerPage(String value) {
		SeleniumUtils.isVisible(resultsPerPage.get(0), driver);
		Select select = new Select(resultsPerPage.get(0));
		select.selectByVisibleText(value);
		waitforLoadingRing();
	}

	public String getResultDropdownSelectedValue() {
		Select select = new Select(resultsPerPage.get(0));
		return select.getFirstSelectedOption().getText().trim();
	}

	public CompanyResultsPage selectResultsPerPageUpdatedResultPage(String value) {
		Select select = new Select(resultsPerPage.get(0));
		select.selectByVisibleText(value);
		waitforLoadingRing();
		return new CompanyResultsPage(driver);
	}

	public boolean isSaveSearchButtonDisplayed() {
		extentTest.log(Status.INFO, "Verify if save search buton is displayed or not.");
		if (saveSearchBtn.isDisplayed())
			return true;
		else
			return false;
	}

	public PrintCompanyListPage clickOnPrintCompanyListUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Company List' under Actions Dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(printCompanyListLink));
		printCompanyListLink.click();
		waitforLoadingRing();
		return new PrintCompanyListPage(driver);
	}

	public boolean isPrintCompanyListDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Print Company List link is present");
		return printCompanyListLink.isDisplayed();
	}

	public boolean isPrintCompanyDetailsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Print Company Details link is present");
		return printCompanyDetailsLink.isDisplayed();
	}

	public boolean verifyIsTrackingListBold() {
		extentTest.log(Status.INFO, "Verify whether the tracking list label is displayed in bold?");
		return CommonUtils.isFontBold(driver, trackingListLabel);
	}

	public TrackPopUpPage clickOnTrackCompanies() {
		extentTest.log(Status.INFO, "Clicking on Track Companies under 'Actions' dropdown");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackProjectsMenu));
		trackProjectsMenu.click();
		return new TrackPopUpPage(driver);
	}

	public boolean isMEnuExistsInLeftPane(String menuName) {
		extentTest.log(Status.INFO, "Verify if menu '" + menuName + "' exists in the left pane");

		for (WebElement webElement : leftProjectFiltersList) {
			if (webElement.getText().equalsIgnoreCase(menuName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isLeftSpecAlertCollapsedDefault() {
		extentTest.log(Status.INFO, "Verify if SpecAlerts collapsed by default in the left pane");
		return leftSpecAlertsDefaultCollapse.getAttribute("class").contains("arrow");
	}

	public void clickOnSeeMoreSpecAlertsIcon() {
		extentTest.log(Status.INFO, "Clicking on the 'See More' icon for SpecAlerts in the left pane");
		specAlertsSeeMoreIcon.click();
	}

	public boolean isSeeMoreSpecALertsSorted() {
		List<String> listElements = CommonUtils.getListFromWebElements(specAlertSeeMorePopUpList);
		List<String> listSorted = CommonUtils.sortWebElements(listElements, false);
		return CommonUtils.CompareTwoList(listElements, listSorted);
	}

	public void clickOnSelectAllInSeeMoreSpecAlerts() {
		extentTest.log(Status.INFO,
				"Clicking on the 'Select All' in the 'See More' icon for SpecAlerts in the left pane");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(selectAllSeeMoreSpecAlerts));
		selectAllSeeMoreSpecAlerts.click();
	}

	public boolean isAllCheckboxSelectedInSeeMoreSpecAlerts() {
		extentTest.log(Status.INFO, "Verify if all the specalerts selected in the see more pop up");
		boolean isSelected = false;
		for (WebElement webElement : specAlertSeeMoreListChkBox) {
			if (webElement.isSelected()) {
				isSelected = true;
			} else {
				isSelected = false;
			}
		}
		return isSelected;
	}

	public boolean isExcludeProjectsInSeeMoreSpecAlertsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the excluded projects displayed in the see more spec alerts");
		return specAlertSeeMoreExcludeProjects.isDisplayed();
	}

	public void clickOnCloseIconForSeeMoreSpecAlerts() {
		extentTest.log(Status.INFO, "Clicking on the close icon for the see more spec alerts");
		seeMoreSpecAlertsCloseIcon.click();
	}

	public boolean isSeeMoreSpecAlertsPopUpDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'See More' Spec Alerts popup displayed");
		try {
			return seeMoreSpecAlertsPopUp.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isPowerRankTextDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Power Rank' results  displayed");
		try {
			return powerRankText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSpecAlertsFilterDisplayed() throws InterruptedException {
		extentTest.log(Status.INFO, "Verify if the Spec Alerts filter crumb displayed");
		Thread.sleep(5000);
		try {
			return specAlertAppliedFilter.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void SwitchToFrame() {
		SeleniumUtils.switchToFrame(driver, PopupFrame);
	}

	public void SwitchToParent() {
		SeleniumUtils.switchToParent(driver);
	}

	public void clickOnSpecAlertsFilterRemove() {
		extentTest.log(Status.INFO, "Click on specalerts applied filter remove icon");
		specAlertAppliedFilterRemoveIcon.click();
		waitforLoadingRing();
	}

	public ProjectPage clickOnViewCompaniesFromSpecAlertsActions() {
		extentTest.log(Status.INFO, "Clicking on View Projects under Actions Dropdown in SpecAlerts Results Page");
		viewCompaniesActionsOptions.click();
		return new ProjectPage(driver);
	}

	public CompanyPage clickOnViewCompaniesUnderActions() {
		extentTest.log(Status.INFO, "Clicking on View Companies under Actions Dropdown");
		viewCompaniesActionsOptions.click();
		return new CompanyPage(driver);
	}

	public void waitforLoadingRing() {
		SeleniumUtils.isVisible(rotateLoadingIcon, driver);
		SeleniumUtils.isLoadingIconInvisible(rotateLoadingIconInvisible, driver);
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

	public boolean isAlertoffDisplayed() {
		extentTest.log(Status.INFO, "check if alert off link is displayed");
		return alertoff.isDisplayed();

	}

	public void clickOnFistProjectCheckbox() {
		extentTest.log(Status.INFO, "Clicking on first project checkbox");
		SeleniumUtils.isVisible(firstProjectchkBox, driver);
		if (!firstProjectchkBox.isSelected()) {
			firstProjectchkBox.click();
		}
	}

	public void clickAlertOffLink() {
		extentTest.log(Status.INFO, "click on alertOff link for project");
		alertoff.click();

	}

	public void clickAlertOnLink() {
		extentTest.log(Status.INFO, "click on alertOn link for project");
		alerton.click();

	}

	public Boolean isalertOnIconDisplayed() {
		extentTest.log(Status.INFO, "check if alert on icon is displayed");
		if (alertIndicatorIcon.getAttribute("src").contains("Images/star-alert-icon.PNG"))
			return true;
		else
			return false;

	}

	public Boolean isTrackIconDisplayed() {
		extentTest.log(Status.INFO, "check if track icon is displayed");
		if (alertIndicatorIcon.getAttribute("src").contains("Images/star-icon.PNG"))
			return true;
		else
			return false;

	}

	public List<String> getSpecAlertsSeeMorePopUpProgramsList() {
		extentTest.log(Status.INFO, "Get the list of SPecAlerts Program from the 'see more' specalerts popup");
		return CommonUtils.getListFromWebElements(specAlertsSeeMorePopUpProgramsList);
	}

	public PrintProjectDetailsPage clickOnPrintProjectDetailsUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Project Details' under Actions Dropdown");
		if (SeleniumUtils.isVisible(printProjectDetailsLink, driver))
			printProjectDetailsLink.click();
		return new PrintProjectDetailsPage(driver);
	}

	public PrintCompanyDetailsPage clickOnPrintCompanyDetailsUnderActions() {
		extentTest.log(Status.INFO, "Clicking on 'Print Project Details' under Actions Dropdown");
		if (SeleniumUtils.isVisible(printCompanyDetailsLink, driver))
			printCompanyDetailsLink.click();
		return new PrintCompanyDetailsPage(driver);
	}

	public boolean isPrintProjectDetailsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the 'Print Company Details link present under actions dropdown'");
		return printCompanyDetailsLink.isDisplayed();
	}

	public String getPaginationSectionText() {
		extentTest.log(Status.INFO, "Get Pagination Section Text");
		return paginationSectionText.getText();
	}

	public String getPaginationSectionPowerBankText() {
		extentTest.log(Status.INFO, "Get Power Bank pagination text");
		return paginationPowerBanksectionText.getText();
	}

	public String getcrumbFirstFiltertxt() {
		extentTest.log(Status.INFO, "Get the first applied filter text");
		return FilterCrumb_AppliedFilter_List.get(0).getText();
	}

	public Boolean isSelectAllcheckboxDisplayed() {
		if (projectSelectAll.isDisplayed())
			return true;
		else
			return false;

	}

	public Boolean isActionsDropdownDisplayed() {
		if (actionsDropdowm.isDisplayed())
			return true;
		else
			return false;

	}

	public Boolean isResultsDropdownDisplayed() {
		if (resultsPerPage.get(0).isDisplayed())
			return true;
		else
			return false;

	}

	public Boolean isPaginationSectionDisplayed() {
		if (paginationSectionText.isDisplayed())
			return true;
		else
			return false;

	}

	public Boolean isSortDropdownDisplayed() {
		if (sortDropdown.isDisplayed())
			return true;
		else
			return false;

	}

	// Set the value under Sort dropdown on company result.
	public void selectValueFromSortDropdown(String value) {
		extentTest.log(Status.INFO, "Set the value under Sort dropdown on company result.");
		Select select = new Select(sortDropdown);
		select.selectByVisibleText(value);
	}

	public void switchTabs(String currentWindowHandle) {
		extentTest.log(Status.INFO, "Switch tab.");
		Set<String> tabs = driver.getWindowHandles();
		for (String tab : tabs) {
			if (!tab.equals(currentWindowHandle)) {
				driver.switchTo().defaultContent();
				driver.switchTo().window(tab);
				break;
			}
		}
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public EmailAlertsPage clickEmailCompaniesLinkUnderActionsDrpDwn() {
		extentTest.log(Status.INFO, "Click email companies under Actions menu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailCompaniesMenu));
		emailCompaniesMenu.click();
		return new EmailAlertsPage(driver);
	}

	public boolean isEmailCompaniesLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify presence of Email Companies link.");
		return emailCompaniesMenu.isDisplayed();
	}

	public void clickPowerRank() {
		extentTest.log(Status.INFO, "Click Power Rank Button");
		SeleniumUtils.scrollToView(driver, powerRank);
		powerRank.click();
		waitforLoadingPowerRank();
	}

	public void waitforLoadingPowerRank() {
		SeleniumUtils.isVisible(powerRankingLoadingPopup, driver);
		SeleniumUtils.isLoadingIconInvisible(powerRankingLoadingPopupInvisible, driver);
	}

	// Scroll down till result per page at bottom.
	public void scrollResultPerPageAtBottom() {
		extentTest.log(Status.INFO, "Scroll down till result per page at bottom.");
		CommonUtils.scrollDownTillElement(resultsPerPage.get(1), driver);
	}

	// Return the list of Company Project titles present on project page.
	public List<String> getListCompanyProjectTitle() {
		extentTest.log(Status.INFO, "Return the list of Company Project titles present on project page.");
		SeleniumUtils.isVisible(companyTitles.get(0), driver);
		return CommonUtils.getListFromWebElements(companyTitles);
	}

	public CompanyPage clickOnCompanyTitle(int index) {
		extentTest.log(Status.INFO, "Clicking on Company Title with index: " + index);
		companyTitles.get(index).click();
		return new CompanyPage(driver);
	}

	public boolean isCompanyTitleWithNotesPresent() {
		extentTest.log(Status.INFO, "Check if a company title is present having Notes.");
		if (companyTitlewithNotes.size() > 0)
			return true;
		return false;
	}

	public Integer getTrackedProjectCount() {
		return myProjectTrackingNameResultList.size();
	}

	public TrackPopUpPage clickOnTrackLinkFromSummary(int index) {
		extentTest.log(Status.INFO, "Clicking on Track Link from Companies Summary");
		trackLinks.get(index).click();
		return new TrackPopUpPage(driver);
	}

	public void clickOnCompanyTypeFilter() {
		extentTest.log(Status.INFO, "Clicking on Company Type Filter");
		if (CommonUtils.checkElementExist(CompanyTypeFilter_ArrowUp, driver)) {
			CompanyTypeFilter.click();
			waitforLoadingRing();
		}
	}

	public boolean isResultContentDisplayed() {
		extentTest.log(Status.INFO, "verify if project result displayed");
		return resultContent.isDisplayed();
	}

	public void clickOnCompanyTypeFilterSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on company type See More Btn");
		CompanyTypeFilterSeeMore_Btn.click();
	}

	public void SelectOptionsFromTheList(int cout, String ParentList) throws InterruptedException {
		switch (ParentList) {
		case "CompanyTypeGrp_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, CompanyTypeGrp_LHS_ParentFilterList);
			break;
		case "ProjectLocation_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, ProjectLocation_LHS_ParentFilterList);
			break;
		case "companyTitleCheckboxes":
			ClickOnMultipleOptionFromTheList(cout, companyTitleCheckboxes);
			break;
		case "ProjectType_LHS_ParentFilterList":
			ClickOnMultipleOptionFromTheList(cout, ProjectType_LHS_ParentFilterList);
			break;
		case "companySelectChkList":
			ClickOnMultipleOptionFromTheList(cout, companySelectChkList);
		}
	}

	// Click On Multiple Option From The List
	private void ClickOnMultipleOptionFromTheList(int cout, List<WebElement> eList) throws InterruptedException {
		extentTest.log(Status.INFO, "Click on first " + cout + " options from the list");
		int count = 1;
		for (WebElement ParentFilter : eList) {
			if (count <= cout) {
				SeleniumUtils.isVisible(ParentFilter, driver);
				SeleniumUtils.isClickable(ParentFilter, driver);
				ParentFilter.click();
				count++;
			}
			if (count > cout) {
				break;
			}
		}
	}

	public int get_LHS_FilterWiseCompanyCount(int idx, String ParentList) throws InterruptedException {
		int i = 0;
		switch (ParentList) {
		case "COMPANY_TYPE_GROUP_ProjectCountNextToElement_List":
			return getCompanyCount(idx, COMPANY_TYPE_GROUP_ProjectCountNextToElement_List);
		}
		return i;
	}

	public int getCompanyCount(int idx, List<WebElement> eList) {
		return Integer.parseInt(eList.get(idx).getText().replaceAll("\\D+", ""));
	}

	public int getExactCompanyResultCount() {
		String count = CompanyResultCount.getText();
		String[] parts = count.split("f");
		String part2 = parts[1];
		return Integer.parseInt(part2.replace(" ", "").replace(",", ""));
	}

	public String get_powerRankedResults_txt() {
		extentTest.log(Status.INFO, "Get powerRankedResults txt");
		return powerRankedResults_txt.getText();
	}

	public boolean Check_ResultCountLessThan_10000(int count) {
		if (count < 10000) {
			return true;
		}
		return false;
	}

	public String get_LHS_Filter_lbl(int idx, String ParentList) throws InterruptedException {
		String str = "null";
		switch (ParentList) {
		case "COMPANY_TYPE_GROUP_LHS_lbl_list":
			return get_LHS_lbl(idx, COMPANY_TYPE_GROUP_LHS_lbl_list);
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

	public void clickOnStructuralPropLink() {
		extentTest.log(Status.INFO, "Click on Structural Prop Link");
		StructuralPropLink.click();
	}

	public void ScrolltoStructuralPropLink() {
		extentTest.log(Status.INFO, "Scroll to Structural Prop Link");
		SeleniumUtils.scrollToView(driver, StructuralPropLink);

	}

	public String get_savedSearchName() {
		extentTest.log(Status.INFO, "Get saved Search Name");
		return savedSearchName.getText();
	}

	public boolean Check_popupSelectall_lbl_color() {
		extentTest.log(Status.INFO, "Ensuer that Select all text should be bold and font color should be #646267");
		return popupSelectall_lbl.getCssValue("color").contains("rgba(100, 98, 103, 1)");
	}

	public void ClickOngeographyPopupStateLink() {
		extentTest.log(Status.INFO, "Click On state navigation link from geography Popup");
		waitforLoadingRing();
		SeleniumUtils.isVisible(geographyPopupStateLink, driver);
		SeleniumUtils.isClickable(geographyPopupStateLink, driver);
		geographyPopupStateLink.click();
		waitforLoadingRing();
	}

	public void ClickOnGEOGRAPHYPopupprojectCountyNavLink() {
		extentTest.log(Status.INFO, "Click On county navigation link from geography Popup");
		waitforLoadingRing();
		CommonUtils.scrollDownTillElement(GEOGRAPHYPopupprojectCountyNavLink, driver);
		SeleniumUtils.isVisible(GEOGRAPHYPopupprojectCountyNavLink, driver);
		SeleniumUtils.isClickable(GEOGRAPHYPopupprojectCountyNavLink, driver);
		GEOGRAPHYPopupprojectCountyNavLink.click();
		waitforLoadingRing();
	}

	public boolean verifyCompanyTypeSortingAscending() {
		extentTest.log(Status.INFO,
				"Ensure that the organization of Company Type values are in alphabetical order ascending");
		List<String> listElements = CommonUtils.getListFromWebElements(COMPANY_TYPE_GROUP_PopupParentFilter_lblList);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public void clickon_PrimaryCompany_Type_cbk() {
		extentTest.log(Status.INFO, "Click On primary type checkbox");
		PrimaryCompany_Type_cbk.click();
		waitforLoadingRing();
	}

	public void ClickOn_Previous_Page() {
		extentTest.log(Status.INFO, "Click On previous page");
		Previous_Page.click();
		waitforLoadingRing();
	}

	public void ClickOn_Next_Page() {
		extentTest.log(Status.INFO, "Click On Next_Page");
		Next_Page.click();
		waitforLoadingRing();
	}

	public boolean verifyCompanyLocationSortingAscending() {
		List<String> listElements = CommonUtils.getListFromWebElements(Companylocation);
		List<String> listArc = CommonUtils.sortWebElements(listElements, true);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public List<String> getCompanyLocations() {
		return CommonUtils.getListFromWebElements(Companylocation);
	}

	public boolean Is_searchButton_displayed() {
		extentTest.log(Status.INFO, "Verify search button box is displayed");
		return searchButton.isDisplayed();
	}

	public String get_getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public boolean Is_projectsLink_displayed() {
		extentTest.log(Status.INFO, "Verify project link is displayed");
		return projectsLink.isDisplayed();
	}

	public boolean Is_CompanyTypeFilter_Displayed() {
		extentTest.log(Status.INFO, "Verify company type filter is displayed");
		return CompanyTypeFilter.isDisplayed();
	}

	public boolean Is_PrimaryCompany_Type_cbk_Selected() {
		extentTest.log(Status.INFO, "Verify PrimaryCompany_Type_cbk is displayed");
		return PrimaryCompany_Type_cbk.isSelected();
	}

	public boolean Check_ListOption_Selecteded(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check list options are visible");
		boolean isSelected = false;
		switch (ParentList) {
		case "CompanyTypeGrp_LHS_ParentFilterList":
			isSelected = ListOptionSelected(CompanyTypeGrp_LHS_ParentFilterList);
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

	public boolean Is_CompanyTypeFilterSeeMore_Btn_Displayed() {
		extentTest.log(Status.INFO, "Verify CompanyTypeFilterSeeMore_Btn is displayed");
		return CompanyTypeFilterSeeMore_Btn.isDisplayed();
	}

	public void clickOnStructuralAddProperties() {
		extentTest.log(Status.INFO, "Click on AddProperties Link");
		SeleniumUtils.isClickable(addProperties, driver);
		addProperties.click();
	}

	public void clickOnProjectLocationFilter() {
		extentTest.log(Status.INFO, "Click on the ProjectLocationFilter");
		CommonUtils.scrollDownTillElement(ProjectLocationFilter, driver);
		if (CommonUtils.checkElementExist(ProjectLocation_Filter_ArrowUp, driver)) {
			ProjectLocationFilter.click();
			waitforLoadingRing();
		}
	}

	public void clickOnProjectLocationFilterSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on ProjectLocationFilterSeeMore_Btn");
		ProjectLocationFilterSeeMore_Btn.click();
	}

	public void clickOnProjPublishDateFilter() {
		extentTest.log(Status.INFO, "Click on the Proj Publish Date Filter");
		if (CommonUtils.checkElementExist(ProjPUBLISH_Date_Filter_ArrowUp, driver)) {
			ProjPublishDateFilter.click();
			waitforLoadingRing();
		}
	}

	public boolean IsProjPublishDateFilter_Displayed() {
		extentTest.log(Status.INFO, "Verify ProjPublishDateFilter is displayed");
		return ProjPublishDateFilter.isDisplayed();
	}

	public boolean IsPrimarycompanyType_Displayed() {
		extentTest.log(Status.INFO, "Verify ProjPublishDateFilter is displayed");
		return PrimaryCompanyType.isDisplayed();
	}

	public void clickOnSearchCompanyFiledsFilter() {
		extentTest.log(Status.INFO, "Click on the SearchCompanyFiledsFilter");
		if (CommonUtils.checkElementExist(SearchCompanyFileds_ArrowUp, driver)) {
			SearchCompanyFiledsFilter.click();
			waitforLoadingRing();
		}
	}

	public boolean IsSearchCompanyFiledsFilter_Displayed() {
		extentTest.log(Status.INFO, "Verify SearchCompanyFiledsFilter is displayed");
		return SearchCompanyFiledsFilter.isDisplayed();
	}

	public boolean IsProjectLocationFilter_Displayed() {
		extentTest.log(Status.INFO, "Verify ProjectLocationFilter is displayed");
		return ProjectLocationFilter.isDisplayed();
	}

	public void clickOnCompanyGeoghphyLocFilter() {
		if (!CommonUtils.checkElementExist(companyGeographyCountryArrowDown, driver)) {
			companyGeographyCountry.click();
		}
	}

	// Clicking on Canda company location checkbox
	public void clickOnCanadaCompanyLocationCheckbox() {
		extentTest.log(Status.INFO, "Clicking on Canda company location checkbox");
		clickOnCompanyGeoghphyLocFilter();
		SeleniumUtils.isVisible(canadaCompanyLocChk, driver);
		canadaCompanyLocChk.click();
		waitforLoadingRing();
	}

	public void clickOnCompanyProjActionStageFilter() {
		if (!CommonUtils.checkElementExist(companyPrjActionStageArrowDown, driver)) {
			companyPrjActionStageFilter.click();
		}
	}

	public boolean isActionStageFilterDisplayed() {
		extentTest.log(Status.INFO, "Verify the Action Stage filter is displayed");
		return companyPrjActionStageFilter.isDisplayed();
	}

	// Click on Design Proj Action Stage Checkbox
	public void clickOnDesignProjActionStageCheckbox() {
		extentTest.log(Status.INFO, "Click on Design Proj Action Stage Checkbox");
		clickOnCompanyProjActionStageFilter();
		SeleniumUtils.isVisible(designPrjActionStageChk, driver);
		designPrjActionStageChk.click();
		waitforLoadingRing();
	}

	// Check Power rank result button display
	public void isPowerRankResultBtnDisplay() {
		extentTest.log(Status.INFO, "Check Power rank result button display.");
		powerRankResultsBtn.click();
	}

	// Project Location DropDown
	public ProjectBiddersPage clickProjectLocationDropDown() {
		extentTest.log(Status.INFO, "Clicking on projectLocation Dropdown");
		ProjectLocationDropdown.click();
		return new ProjectBiddersPage(driver);
	}

	// Click on Power rank result button.
	public void clickOnPowerRankResultBtn() {
		extentTest.log(Status.INFO, "Click on Power rank result button.");
		powerRankResultsBtn.click();
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.id("powerRankingLoadingPopup")));
	}

	// Get the valuation amount from the company page with mention company index
	// from the list.
	public String getValuationTextFromValuationList(int valuationRankIndex) {
		extentTest.log(Status.INFO,
				"Get the valuation amount from the company page with mention company index from the list.");
		return valuationTextList.get(valuationRankIndex).getText().replace("$", "").replace(",", "").trim();
	}

	// Get the total project count from the company page with mention company
	// index from the list.
	public String getTotalProjectCountFromProjectList(int projectTextIndex) {
		extentTest.log(Status.INFO,
				"Get the valuation text from the company page with mention company index from the list.");
		return totalProjectCountList.get(projectTextIndex).getText().trim();
	}

	public boolean Check_DefaultStatusOf_CheckboxList(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check by default filter checkboxs should not be selected");
		boolean checkboxStatus = false;
		switch (ParentList) {
		case "ProjectLocation_LHS_ParentFilterList":
			checkboxStatus = Default_CheckboxStatus(ProjectLocation_LHS_ParentFilterList);
			break;
		}
		return checkboxStatus;
	}

	public boolean Default_CheckboxStatus(List<WebElement> Checkboxlist) {
		extentTest.log(Status.INFO, "Verify default checkbox list status");
		return CommonUtils.isCheckboxListSelected(Checkboxlist);
	}

	// Check map icon displayed on the company result page.
	public boolean isMapIconLinkDisplayed() {
		extentTest.log(Status.INFO, "Check map icon displayed on the company result page.");
		return CommonUtils.checkElementExist(mapIconLink, driver);
	}

	public boolean Check_First9_OptionsAreVisible(String ParentList) throws InterruptedException {
		extentTest.log(Status.INFO, "Check first 9 options are visible");
		boolean isVisible = false;
		switch (ParentList) {
		case "ProjectLocation_LHS_ParentFilterList":
			isVisible = Are_First9_OptionsVisible(ProjectLocation_LHS_ParentFilterList);
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
		case "Proje" + "ctLocation_LHS_ParentFilterList":
			isVisible = Is10th_OptionVisible(ProjectLocation_LHS_ParentFilterList);
			break;
		}
		return isVisible;
	}

	public boolean Is10th_OptionVisible(List<WebElement> Checkboxlist) {
		return SeleniumUtils.isVisible(Checkboxlist.get(9), driver);
	}

	public void Insert_CompanyName_textbox(String name) {
		extentTest.log(Status.INFO, "Insert company name");
		CompanyName_textbox.sendKeys(name);
	}

	// Check if any of company search filter is present.
	public boolean isCompanySearchFilterPresent() {
		extentTest.log(Status.INFO, "Check if any of company search filter is present.");
		return CommonUtils.checkElementExist(filters, driver);
	}

	public void ClickOn_CompanySearch_button() {
		extentTest.log(Status.INFO, "Click on search button");
		CompanySearch_button.click();
		waitforLoadingRing();
	}

	public String getCompanyTitle(int idx) {
		extentTest.log(Status.INFO, "Get company title from the list");
		return companyTitles.get(idx).getText();
	}

	public void clickOnProjectStateFilter() {
		extentTest.log(Status.INFO, "Click on the ProjectStateFilter");
		if (CommonUtils.checkElementExist(ProjectState_Filter_ArrowUp, driver)) {
			ProjectStateFilter.click();
			waitforLoadingRing();
		}
	}

	public void clickOnProjectStateFilterSeeMore_Btn() {
		extentTest.log(Status.INFO, " Click on ProjectStateFilterSeeMore_Btn");
		ProjectStateFilterSeeMore_Btn.click();
	}

	public void clickProjectLocationDrpdwn() {
		extentTest.log(Status.INFO, " Click on ProjectStateFilterSeeMore_Btn");
		clickonProjectLocationDrpdwnbtn.click();
	}

	public String get_AppliedFilter_lbl(int idx) {
		extentTest.log(Status.INFO, "Get applied filter " + idx + " text");
		return FilterCrumb_AppliedFilter_TitleList.get(idx).getText();
	}

	public boolean ismySearchesDropDownDisplayed() {
		extentTest.log(Status.INFO, "Check mysearches dropdown displayed");
		return CommonUtils.checkElementExist(mySearchesDropDown, driver);
	}

	// Click on My Searches dropDown
	public void clickOnMySearchesDropDown() {
		extentTest.log(Status.INFO, "Click on My Searches dropDown");
		mySearchesDropDown.click();
	}

	// Verify if the specalerts displayed in the my searches dropdown
	public boolean isMySearchesSpecAlertsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the specalerts displayed in the my searches dropdown");
		return CommonUtils.checkElementExist(mySearchesSpecAlerts, driver);
	}

	// Click on specalerts displayed in the my searches dropdown
	public SpecAlertsResultsPage clickOnMySearchesSpecAlerts() {
		extentTest.log(Status.INFO, "Click on specalerts displayed in the my searches dropdown");
		return new SpecAlertsResultsPage(driver);
	}

	// Verify if the Tracking Lists displayed in the my searches dropdown
	public boolean isMySearchesTrackingListsDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Tracking Lists displayed in the my searches dropdown");
		return CommonUtils.checkElementExist(mySearchesTrackingLists, driver);
	}

	// Verify if the Save Searches displayed in the my searches dropdown
	public boolean isMySearchesSaveSearchesDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Save Searches displayed in the my searches dropdown");
		return CommonUtils.checkElementExist(mySearchesSaveSearches, driver);
	}

	// Check for Select All redesign wrap.
	public boolean checkSelectAllRedesignWrap() {
		extentTest.log(Status.INFO, "Check for Select All redesign wrap.");
		return CommonUtils.checkElementExist(selectAllRedesignWrap, driver);
	}

	// Check for Action dropdown redesign wrap.
	public boolean checkActionDropdownRedesignWrap() {
		extentTest.log(Status.INFO, "Check for Action dropdown redesign wrap.");
		return CommonUtils.checkElementExist(actionDropdownRedesignWrap, driver);
	}

	// Check for Sort dropdown redesign wrap.
	public boolean checkSortDropdownRedesignWrap() {
		extentTest.log(Status.INFO, "Check for Sort dropdown redesign wrap.");
		return CommonUtils.checkElementExist(sortDropdownRedesignWrap, driver);
	}

	public boolean checkSortDropdownNotEmpty() {
		extentTest.log(Status.INFO, "Check for Sort dropdown is not empty");
		Select select = new Select(sortDropdown);
		List<WebElement> options = select.getOptions();
		return options.size() > 0;
	}

	// Check Pagination section present at top and bottom of page.
	public boolean checkPaginationSectionPresentOnPage() {
		extentTest.log(Status.INFO, "Check Pagination section present at top and bottom of page.");
		return paginationSectionOnPage.size() == 2;
	}

	// Click on Second company page.
	public void clickOnSecondCompanyPage() {
		extentTest.log(Status.INFO, "Click on Second company page.");
		companySecondPageNavigation.click();
		waitTillRingDisappear();
	}

	// Check after Click on Second company page is become active
	public boolean isSecondCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on Second company page is become active.");
		return companySecondPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	// Click on third company page.
	public void clickOnThirdCompanyPage() {
		extentTest.log(Status.INFO, "Click on third company page..");
		companyThirdPageNavigation.click();
		waitTillRingDisappear();
	}

	// Check after Click on third company page is become active
	public boolean isThirdCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on third company page is become active.");
		return companyThirdPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	// Click on Previous company page.
	public void clickOnPreviousCompanyPage() {
		extentTest.log(Status.INFO, "Click on Previous company page..");
		previousCompanyPage.click();
		waitTillRingDisappear();
	}

	// Click on Next company page.
	public void clickOnNextCompanyPage() {
		extentTest.log(Status.INFO, "Click on Next company page..");
		nextCompanyPage.click();
		waitTillRingDisappear();
	}

	// Check after Click on first company page is become active
	public boolean isFirstCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on first company page is become active.");
		return companyFirstPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	public HomePage clickOnHomeTab() {
		extentTest.log(Status.INFO, "Clicking on Home Tab");
		homeMenuLink.click();
		return new HomePage(driver);

	}

	// Click on first page button company page.
	public void clickOnFirstPageButton() {
		extentTest.log(Status.INFO, "Click on first page button company page.");
		firstPageCompanyPageBtn.click();
		waitTillRingDisappear();
	}

	// Check after Click on fourth company page is become active
	public boolean isFourCompanyPageActive() {
		extentTest.log(Status.INFO, "Check after Click on four company page is become active.");
		return companyFourPageNavigation.getAttribute("class").trim().contains("inActivePagination");
	}

	public void waitTillRingDisappear() {
		SeleniumUtils.isVisible(LoadingRingOnPopop, driver);
		SeleniumUtils.isLoadingIconInvisible(LoadingRingOnPopopInvisibleCheck, driver);
	}

	// Check if company address displayed.
	public boolean isCompanyAddressDisplayed() {
		extentTest.log(Status.INFO, "Check if company address displayed.");
		return !companyAddress.isEmpty();
	}

	// Check if company phone displayed.
	public boolean isCompanyPhoneDisplayed() {
		extentTest.log(Status.INFO, "Check if company phone displayed.");
		return !companyPhone.isEmpty();
	}

	// Check all the company checkbox are selected.
	public boolean checkAllCompanyChkboxIsSelected() {
		extentTest.log(Status.INFO, "Check all the company checkbox are selected.");
		boolean result = true;
		for (WebElement companySelectChkbox : companySelectChkList) {
			if (!companySelectChkbox.isSelected()) {
				result = false;
				break;
			}
		}
		return result;
	}

	// Check if My Searches drop down is vertically-aligned with the right
	// border of the left-nav filter
	public boolean isMySearchesVerticallyAlignedWithLeftNav() {
		extentTest.log(Status.INFO,
				"Check if right border of the 'My Searches' button is vertically-aligned with the right border of the left-nav filter");

		if (mySearchesdropDownListBtn.getLocation().getX() == leftNavFilter.getLocation().getX()) {
			if (mySearchesdropDownListBtn.getSize().getWidth() == leftNavFilter.getSize().getWidth() - 1) {
				return true;
			}
		}
		return false;
	}

	// Check if keyword search text box is left-aligned (with the left-border
	// pagination header)
	public boolean isKeywordSearchTextBoxLeftAligned() {
		extentTest.log(Status.INFO, "Check if keyword search text box is left-aligned ");

		return (keyWordSearchTextArea.getLocation().getX() == paginationSectionText.getLocation().getX());
	}

	// Check if modal message dialog is visible or not

	public void clickOnProjectGroupTab() {
		extentTest.log(Status.INFO, "Click on the ProjectLocationFilter");
		if (CommonUtils.checkElementExist(ProjectType_Filter_ArrowUp, driver)) {
			expandProjectGroup.click();

		}
	}

	public void clickOnCommercialcheckbox() {
		extentTest.log(Status.INFO, "Click On Commercial Check box");
		clickonCommercialchbox.click();
		waitforLoadingRing();
	}

	// Click on Pre_Design Proj Action Stage Checkbox
	public void clickOnPre_DesignProjActionStageCheckbox() {
		extentTest.log(Status.INFO, "Click on Pre_Design Proj Action Stage Checkbox");
		clickOnCompanyProjActionStageFilter();
		SeleniumUtils.isVisible(pre_designPrjActionStageChk, driver);
		pre_designPrjActionStageChk.click();
		waitforLoadingRing();
	}

	public void clickOnSortDropDown() {
		extentTest.log(Status.INFO, "Click On Sort DropDown button");
		clickOnSortDropDownButton.click();
	}

	public void clickOnappropriateOptionFromSortDropDown(int a) {
		extentTest.log(Status.INFO, "Click On Sort DropDown");
		Select dropdown = new Select(selectOption);
		dropdown.selectByIndex(a);
	}

	public void selectappropriateOptionFromSortDropDown(String option) {
		extentTest.log(Status.INFO, "Click On Sort DropDown");
		Select dropdown = new Select(selectOption);
		dropdown.selectByVisibleText(option);
	}

	public List<String> verifySortDropDown() {
		extentTest.log(Status.INFO, " Get List Of String");
		Select dropdown = new Select(selectOption);
		List<WebElement> selectOpt = dropdown.getOptions();
		List<String> option = new ArrayList<String>();
		for (WebElement lst : selectOpt) {
			option.add(lst.getText());
		}
		return option;
	}

	public List<String> getFilterCrumbDisplayed() {
		extentTest.log(Status.INFO, "Verify Filter Crumb is displayed");
		List<String> filterCrumb = new ArrayList<String>();
		for (WebElement filter : filterCrumbDisplayed) {
			filterCrumb.add(filter.getText());
		}
		return filterCrumb;
	}

	public List<String> getPopUpFilterCrumbDisplayed() {
		extentTest.log(Status.INFO, "Verify Filter Crumb is displayed");
		List<String> filterCrumbPopUp = new ArrayList<String>();
		for (WebElement filter : popUpFilterCrumbDisplayed) {
			filterCrumbPopUp.add(filter.getText());
		}
		return filterCrumbPopUp;
	}

	public List<String> getSortCompanyName() {
		extentTest.log(Status.INFO, "Verify Filter Crumb is displayed");
		List<String> companyNameList = new ArrayList<String>();
		for (WebElement filter : getCompanyNameList) {
			companyNameList.add(filter.getText());
		}
		return companyNameList;
	}

	public List<String> getSortNumberOfProject() {
		extentTest.log(Status.INFO, "Verify Filter Crumb is displayed");
		List<String> numberOfProjectList = new ArrayList<String>();
		for (WebElement filter : getNumberOfProjectList) {
			numberOfProjectList.add(filter.getText());
		}
		return numberOfProjectList;
	}

	public List<String> getSortProjectvaluation() {
		extentTest.log(Status.INFO, "Verify Filter Crumb is displayed");
		List<String> projectValuationList = new ArrayList<String>();
		for (WebElement filter : getProjectValuatonList) {
			projectValuationList.add(filter.getText());
		}
		return projectValuationList;
	}

	public void ClickOnProjectPopupStateLink() {
		extentTest.log(Status.INFO, "Click On state navigation link from geography Popup");
		waitforLoadingRing();
		SeleniumUtils.isVisible(ProjectLocationStateFilterSeeMore_Btn, driver);
		SeleniumUtils.isClickable(ProjectLocationStateFilterSeeMore_Btn, driver);
		ProjectLocationStateFilterSeeMore_Btn.click();
		waitforLoadingRing();
	}

	public boolean checkProjectPopupStateLink() {
		extentTest.log(Status.INFO, "Click On state navigation link from geography Popup");
		waitforLoadingRing();
		SeleniumUtils.isVisible(ProjectLocationStateFilterSeeMore_Btn, driver);
		return ProjectLocationStateFilterSeeMore_Btn.isDisplayed();
	}

	public void clickOnShowMore() {
		extentTest.log(Status.INFO, "Click On Sort DropDown button");
		showMoreControl.click();
	}

	public boolean isPopUpDisplayed() {
		extentTest.log(Status.INFO, "filter pop up is displayed");
		return showMoreControl.isDisplayed();
	}

	public ProjectResultsPage clickOnTheProjectsLink() {
		extentTest.log(Status.INFO, "Click on Projects Link");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(projectsLinkTab));
		projectsLinkTab.click();
		return new ProjectResultsPage(driver);
	}

	public List<String> getFilterListCompanyResultPage() {
		extentTest.log(Status.INFO, "Getting Filter list.");
		List<String> filterList = new ArrayList<String>();
		for (WebElement filter : FilterOnCompanyResultPage) {
			filterList.add(filter.getText());
		}
		return filterList;
	}

	public boolean isPowerrankModalMessagedialogBoxDisplayed() {
		extentTest.log(Status.INFO, "Check if power rank modal message dialog  displayed");
		return powerRankModalmessageDialogBox.isDisplayed();
	}

	public void clickOnPrjOwnerTypeFilter() {
		if (!CommonUtils.checkElementExist(prjOwnerTypeFilterArrowDownIcon, driver)) {
			prjOwnerTypeFilter.click();
			waitforLoadingRing();
		}
	}

	// Click on Project Ownership Type Checkbox
	public void clickOnProjOwnershipTypeCheckbox() {
		extentTest.log(Status.INFO, "Click on Project Ownership Type Checkbox");
		clickOnPrjOwnerTypeFilter();
		SeleniumUtils.isVisible(prjOwnershipTypeChk, driver);
		prjOwnershipTypeChk.click();
		waitforLoadingRing();
	}

	public String getPowerRankButtonHoverText() {
		extentTest.log(Status.INFO, "Getting the hover text on Power Rank Button");
		SeleniumUtils.isVisible(powerRank, driver);

		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		builder.moveToElement(powerRank).build().perform();

		return powerRankButtonHoverText.getText();
	}

	public String getLeftNavHoverTextAfterPowerRanked() {
		extentTest.log(Status.INFO, "Getting the hover text on Crumb Menu after Power Rank");
		SeleniumUtils.isVisible(leftNavAfterPoweRanked, driver);

		Actions builder = new Actions(driver);
		builder.moveToElement(leftNavAfterPoweRanked).build().perform();

		return leftNavAfterPoweRankedHoverText.getText();
	}

	public WebElement getWebElement(String filterName) {
		WebElement element = null;
		switch (filterName.toUpperCase()) {
		case "COMPANY LOCATION":
			element = companyFilter;
			break;
		case "ADDTIONAL FILTER":
			element = addtionalFilter;
			break;
		}
		return element;
	}

	public void dragDrop(String sourceFilterName, String destinationFilterName) {
		new Actions(driver).dragAndDrop(getWebElement(sourceFilterName), getWebElement(destinationFilterName)).build();
	}

	public void ClickOn_StateRegionPopupSelectAllStates_checkbox() {
		extentTest.log(Status.INFO, "From state panel, click on SelectAll");
		StateRegionPopupSelectAllStates_checkbox.click();
	}

	public boolean Is_StateRegionPopupSelectAllStates_checkboxSelected() {
		extentTest.log(Status.INFO, "Check SelectAll checkbox is selected");
		return StateRegionPopupSelectAllStates_checkbox.isSelected();
	}

	public void ClickOn_CountyPopupSelectAllStates_checkbox() {
		extentTest.log(Status.INFO, "From county panel, click on SelectAll in state panel");
		CountyPopupSelectAllStates_checkbox.click();
	}

	public boolean Is_CountyPopupSelectAllStates_checkboxSelected() {
		extentTest.log(Status.INFO, "Check SelectAll checkbox is selected in county panel");
		CommonUtils.scrollDownTillElement(CountyPopupSelectAllStates_checkbox, driver);
		return CountyPopupSelectAllStates_checkbox.isSelected();
	}

	public String getPublishRange_DropdownOptionTxt() {
		extentTest.log(Status.INFO, "Get default text from the dropdown");
		return PublishRange_DropdownOptionTxt.getText();
	}

	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Click on Actions Dropdown");
		actionsDropdowm.click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(downloadCompaniesMenu));

	}

	// Download Company action menu is displayed.
	public boolean isDownloadCompanyActionMenuDisplayed() {
		extentTest.log(Status.INFO, "Download Company action menu is displayed.");
		return CommonUtils.checkElementExist(downloadCompanyActionMenu, driver);
	}

	public void selectSortingOption(String sortType) {
		extentTest.log(Status.INFO, "Select sort option" + sortType);
		Select select = new Select(sortDropdown);
		select.selectByVisibleText(sortType);
	}

	public boolean verifyProjectTypeSorting(boolean order) {
		extentTest.log(Status.INFO, "Ensure that the projects are sorted based on Project Type");
		List<String> listElements = CommonUtils.getListFromWebElements(CompanyTypeGrp_LHS_ParentFilterList);
		List<String> listArc = CommonUtils.sortWebElements(listElements, order);
		return CommonUtils.CompareTwoList(listElements, listArc);
	}

	public void collapseCompanyLocationFilterPane() {
		extentTest.log(Status.INFO, "Collapse Company location filter pane.");
		if (!CommonUtils.checkElementExist(companyLocationFilterArrowDownIcon, driver)) {
			companyLocationFilter.click();
			waitforLoadingRing();
		}
	}

	public boolean isCompanyLocationFilterinCollapsedState() {
		extentTest.log(Status.INFO, "Verifing company location Filter collapsed state.");
		boolean isDisplayed = false;
		if (CommonUtils.checkElementExist(companyLocationFilter_Arrowup, driver)) {
			isDisplayed = companyLocationFilter_Arrowup.isDisplayed();
			waitforLoadingRing();
		}
		return isDisplayed;
	}

	public boolean isCompanyLocationFilterinExpandedState() {
		extentTest.log(Status.INFO, "Verifing company location Filter expanded state.");
		boolean isDisplayed = false;
		if (CommonUtils.checkElementExist(companyLocationFilterArrowDownIcon, driver)) {
			isDisplayed = companyLocationFilterArrowDownIcon.isDisplayed();
			waitforLoadingRing();
		}
		return isDisplayed;
	}

	public void clickPowerRankCancelBtn() {
		extentTest.log(Status.INFO, "Click Power Rank cancel button.");
		powerRankCancelBtn.click();
	}

	public Boolean mouseOverActionandCheckHostCompaniesDisplayed() {
		Actions action = new Actions(driver);
		action.clickAndHold(actionsDropdowm).build().perform();
		extentTest.log(Status.INFO, "Check if Host Companies is displayed");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(hostCompaniesActionsLink));
		return hostCompaniesActionsLink.isDisplayed();
	}

	public CompanyPage clickOnFirstCompanyTitleWithContacts() {
		extentTest.log(Status.INFO, "Click on Company Title With Contacts");
		companyTitleswithContactsLink.get(0).click();
		waitforLoadingRing();
		return new CompanyPage(driver);
	}

	public boolean Is_hostCompaniesActionsLink_Visible() {
		extentTest.log(Status.INFO, "Ensure hostCompaniesActionsLink is visible");
		return SeleniumUtils.isVisible(hostCompaniesActionsLink, driver);
	}

	public void ClickOn_hostCompaniesActionsLink() {
		extentTest.log(Status.INFO, "Click on hostCompaniesActionsLink");
		hostCompaniesActionsLink.click();
	}

	public void ClickOn_closeDocupropopup() {
		extentTest.log(Status.INFO, "Click on closeDocupropopup");
		closeDocupropopup.click();
	}

	public void clickOnactionsDropdowm_companyNavigator() {
		extentTest.log(Status.INFO, "Click on actionsDropdowm_companyNavigator");
		actionsDropdowm_companyNavigator.click();
	}

	public void ClickOn_hostProjectActionsLink() {
		extentTest.log(Status.INFO, "Click on hostProjectActionsLink");
		hostProjectActionsLink.click();
	}

	public boolean Is_hostToDocuProPopupHeader_Displayed() {
		extentTest.log(Status.INFO, "Ensure hostToDocuProPopupHeader is displayed");
		return hostToDocuProPopupHeader.isDisplayed();
	}

	public void clickOnthirdProjectchkBox() {
		extentTest.log(Status.INFO, "Clicking on thirdProjectchkBox checkbox");
		thirdProjectchkBox.click();
	}

	public boolean Is_hostProjectActionsLink_Visible() {
		extentTest.log(Status.INFO, "Ensure hostProjectActionsLink is visible");
		return SeleniumUtils.isVisible(hostProjectActionsLink, driver);
	}

	public void clickOnProjectchkBox() {
		extentTest.log(Status.INFO, "Clicking on ProjectchkBox checkbox");
		ProjectchkBox.click();
	}

	public boolean isPageSizeGT1000MessageDisplayed() {
		extentTest.log(Status.INFO, "Verify message if page size is greater than 1000.");
		return pageSizeGT1000Msg.isDisplayed();
	}

	public void clickOnCompanyLocChkBoxWithMaxValue(final int maxLimit) {
		extentTest.log(Status.INFO, "Click on company location check box have values less than " + maxLimit);
		clickOnCompanyGeoghphyLocFilter();
		waitforLoadingRing();
		final List<WebElement> locChkList = new ArrayList<WebElement>();
		locChkList.addAll(companyLocationsCheckBoxes);
		String countText;
		int count = 0;
		for (final WebElement location : locChkList) {
			countText = location.findElement(By.cssSelector("label span")).getText().replace(" ", "").replace(",", "");
			count = Integer.parseInt(countText.substring(countText.indexOf("(") + 1, countText.indexOf(")")));
			if (count <= maxLimit) {
				final WebElement locCheckBox = location.findElements(By.cssSelector("input")).get(0);
				if (!locCheckBox.isSelected()) {
					locCheckBox.click();
					waitforLoadingRing();
				}
				break;
			}
		}
	}

	public boolean verifyZipCodeInAddress(String address) {
		boolean isVerified = false;

		try {
			Pattern zipPattern = Pattern.compile("(\\d{5}[\\-]?\\d{4})?");
			Matcher zipMatcher = zipPattern.matcher(address);
			if (zipMatcher.find()) {

				isVerified = true;
			}
		} catch (Exception ex) {
			return false;
		}
		return isVerified;
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

	public boolean isRemoveLinkPresent() {
		extentTest.log(Status.INFO, "Verify if remove Link is displayed for the hidden projects");
		return SeleniumUtils.isVisible(removeLink, driver);
	}

	public boolean isAlertOnLinkPresent() {
		extentTest.log(Status.INFO, "Verify if Alert ON Link is displayed for the hidden projects");
		return SeleniumUtils.isVisible(alertOnLink, driver);
	}

	public boolean isTrackLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if Track Link is displayed in the Project Summary");
		return trackLink.isDisplayed();
	}

	public EmailCompanyPage clickOnEmailCompanyActionMenu() {
		extentTest.log(Status.INFO, "Click on Eamil Company action menu on Company Result page.");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailCompaniesMenu));
		emailCompaniesMenu.click();
		return new EmailCompanyPage(driver);
	}
}
