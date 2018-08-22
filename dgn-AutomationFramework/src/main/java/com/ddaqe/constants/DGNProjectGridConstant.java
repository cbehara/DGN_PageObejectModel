package com.ddaqe.constants;

import java.util.ArrayList;
import java.util.List;

public class DGNProjectGridConstant {

	public static String RESET_TO_DEFAULT = "RESET TO DEFAULT";

	public static String DCS_OPTION_STREET_ADDRESS = "Street Address";
	public static String DCS_OPTION_CITY = "City";
	public static String DCS_OPTION_COUNTY = "County";
	public static String DCS_OPTION_STATE = "State";
	public static String DCS_OPTION_ZIP_CODE = "Zip Code";
	public static String DCS_OPTION_VALUATION = "Valuation";
	public static String DCS_OPTION_BID_DATE = "Bid Date";
	public static String DCS_OPTION_PUBLISH_DATE = "Publish Date";
	public static String DCS_OPTION_ACTION_STAGE = "Action Stage";
	public static String DCS_OPTION_PROJECT_TYPE = "Project Type";
	public static String DCS_OPTION_OWNER_NAME = "Owner Name";
	public static String DCS_OPTION_STATUS = "Status";
	public static String DCS_OPTION_DODGE_REPORT_NUMBER = "DR Number";
	public static String DCS_OPTION_DODGE_REPORT_VERSION = "Version";

	public static String GRID_TOGGLE_TITLE = "Grid";

	public static String GRID_DEFAULT_COLUMN_PROJECT = "PROJECT";
	public static String GRID_DEFAULT_COLUMN_CITY = "CITY";
	public static String GRID_DEFAULT_COLUMN_STATE = "STATE";
	public static String GRID_DEFAULT_COLUMN_VALUATION = "VALUATION";
	public static String GRID_DEFAULT_COLUMN_BID_DATE = "BID DATE";
	public static String GRID_DEFAULT_COLUMN_ACTION_STAGE = "ACTION STAGE";
	public static String GRID_DEFAULT_COLUMN_PROJECT_TYPE = "PROJECT TYPE";
	public static String GRID_DEFAULT_COLUMN_PUBLISH_DATE = "PUBLISH DATE";
	
	public static String GRID_NON_DEFAULT_COLUMN_STATUS = "STATUS";
	
	public static String ACTIONS_DROPDOWN_OPTION_VIEW_PROJECTS = "View Projects";
	public static String ACTIONS_DROPDOWN_OPTION_DOWNLOAD_PROJECTS = "Download Projects";
	public static String ACTIONS_DROPDOWN_OPTION_DOWNLOAD_FIRMS = "Download Firms";
	public static String ACTIONS_DROPDOWN_OPTION_EMAIL_PROJECTS = "Email Projects";
	public static String ACTIONS_DROPDOWN_OPTION_TRACK_PROJECTS = "Track Projects";
	public static String ACTIONS_DROPDOWN_OPTION_HIDE_PROJECTS = "Hide Projects";
	public static String ACTIONS_DROPDOWN_OPTION_PRINT_PROJECT_DETAILS = "Print Project Details";
	public static String ACTIONS_DROPDOWN_OPTION_PRINT_PROJECT_LIST = "Print Project List";
	
	public static String PROJECT_DR_PAGE_LINK_FIRMS =  "Firms";
	public static String PROJECT_DR_PAGE_LINK_PLANHOLDERS_BIDDERS="Planholders/Bidders";
	public static String PROJECT_DR_PAGE_LINK_PLANS="Plans";
	public static String PROJECT_DR_PAGE_LINK_SPECS= "Specs";
	public static String PROJECT_DR_PAGE_LINK_ADDENDA= "Addenda";
	public static String PROJECT_DR_PAGE_LINK_DESIGNALERTS= "DesignAlerts";
	public static String PROJECT_DR_PAGE_LINK_NOTES="Notes";
	
	public static String GRID_PROJECT_OOS_COLUMN_PROJECT = "PROJECT";
	public static String GRID_PROJECT_OOS_COLUMN_VERSION = "VERSION";
	public static String GRID_PROJECT_OOS_COLUMN_DR_NUMBER = "DR NUMBER";
	public static String GRID_PROJECT_OOS_COLUMN_PUBLISH_DATE = "PUBLISH DATE";
	
	public static List<String> getProjectOOSColumnHeadingList() {
		List<String> columnHeadingList = new ArrayList<String>();
		columnHeadingList.add(GRID_PROJECT_OOS_COLUMN_PROJECT);
		columnHeadingList.add(GRID_PROJECT_OOS_COLUMN_VERSION);
		columnHeadingList.add(GRID_PROJECT_OOS_COLUMN_DR_NUMBER);
		columnHeadingList.add(GRID_PROJECT_OOS_COLUMN_PUBLISH_DATE);
		return columnHeadingList;
	}
	
	public static List<String> getProjectDRLinkList() {
		List<String> projectDrLinkList = new ArrayList<String>();
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_FIRMS);
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_PLANHOLDERS_BIDDERS);
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_PLANS);
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_SPECS);
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_ADDENDA);
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_DESIGNALERTS);
		projectDrLinkList.add(PROJECT_DR_PAGE_LINK_NOTES);
		return projectDrLinkList;
	}
	
	public static List<String> getDCSCheckBoxLabelList() {
		List<String> dcsCheckBoxLabelList = new ArrayList<String>();
		dcsCheckBoxLabelList.add(DCS_OPTION_STREET_ADDRESS);
		dcsCheckBoxLabelList.add(DCS_OPTION_CITY);
		dcsCheckBoxLabelList.add(DCS_OPTION_COUNTY);
		dcsCheckBoxLabelList.add(DCS_OPTION_STATE);
		dcsCheckBoxLabelList.add(DCS_OPTION_ZIP_CODE);
		dcsCheckBoxLabelList.add(DCS_OPTION_VALUATION);
		dcsCheckBoxLabelList.add(DCS_OPTION_BID_DATE);
		dcsCheckBoxLabelList.add(DCS_OPTION_PUBLISH_DATE);
		dcsCheckBoxLabelList.add(DCS_OPTION_ACTION_STAGE);
		dcsCheckBoxLabelList.add(DCS_OPTION_PROJECT_TYPE);
		dcsCheckBoxLabelList.add(DCS_OPTION_OWNER_NAME);
		dcsCheckBoxLabelList.add(DCS_OPTION_STATUS);
		dcsCheckBoxLabelList.add(DCS_OPTION_DODGE_REPORT_NUMBER);
		dcsCheckBoxLabelList.add(DCS_OPTION_DODGE_REPORT_VERSION);
		return dcsCheckBoxLabelList;
	}

	public static List<String> getColumnHeadingList() {
		List<String> columnHeadingList = new ArrayList<String>();
		columnHeadingList.add(GRID_DEFAULT_COLUMN_PROJECT);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_CITY);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_STATE);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_VALUATION);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_BID_DATE);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_ACTION_STAGE);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_PROJECT_TYPE);
		columnHeadingList.add(GRID_DEFAULT_COLUMN_PUBLISH_DATE);
		return columnHeadingList;
	}

	public static List<String> getActionDropdownOptions() {
		List<String> actionDropdownOptions = new ArrayList<String>();
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_VIEW_PROJECTS);
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_DOWNLOAD_PROJECTS);
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_DOWNLOAD_FIRMS);
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_EMAIL_PROJECTS);
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_TRACK_PROJECTS);
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_HIDE_PROJECTS);
		actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_PRINT_PROJECT_DETAILS);
		//actionDropdownOptions.add(ACTIONS_DROPDOWN_OPTION_PRINT_PROJECT_LIST);
		return actionDropdownOptions;
	}

}
