package com.ddaqe.utils;

/**
 * The Class DGNEnum.
 */
public class DGNEnum {

	/**
	 * The Enum Plans.
	 */
	public enum Plans {

		/** The title. */
		TITLE,
		/** The architectural. */
		ARCHITECTURAL,
		/** The structural. */
		STRUCTURAL,
		/** The civil. */
		CIVIL,
		/** The mechanical. */
		MECHANICAL,
		/** The electrical. */
		ELECTRICAL,
		/** The plumbing. */
		PLUMBING,
		/** The landscaping. */
		LANDSCAPE,
		/** The otherspecial. */
		OTHERSPECIAL;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return name().toUpperCase();
		}
	}

	/**
	 * The Enum SortOrder.
	 */
	public enum SortOrder {

		/** The ascending. */
		ASCENDING,
		/** The descending. */
		DESCENDING
	}

	/**
	 * The Enum resultPerPageOptionList.
	 */
	public enum resultPerPageOptionList {

		/** The option ten. */
		OPTION_TEN(10),
		/** The option twenty five. */
		OPTION_TWENTY_FIVE(25),
		/** The option fifty. */
		OPTION_FIFTY(50),
		/** The option hundred. */
		OPTION_HUNDRED(100),
		/** The option five hundred. */
		OPTION_FIVE_HUNDRED(500);

		/** The value. */
		private final int value;

		/**
		 * Instantiates a new result per page option list.
		 *
		 * @param newValue
		 *            the new value
		 */
		resultPerPageOptionList(final int newValue) {
			value = newValue;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * The Enum typeFilterAccessSpecifier.
	 */
	public enum typeFilterAccessSpecifier {

		/** The type all. */
		TYPE_ALL("All"),
		/** The type private. */
		TYPE_PRIVATE("Private"),
		/** The type public. */
		TYPE_PUBLIC("Public"),
		/** The type shared. */
		TYPE_SHARED("Shared");

		/** The specifier type. */
		private final String specifierType;

		/**
		 * Instantiates a new type filter access specifier.
		 *
		 * @param type
		 *            the type
		 */
		typeFilterAccessSpecifier(String type) {
			this.specifierType = type;
		}

		/**
		 * Gets the type access spec.
		 *
		 * @return the type access spec
		 */
		public String getTypeAccessSpec() {
			return this.specifierType;
		}
	}

	/**
	 * The Enum usersOptionsLists.
	 */
	public enum usersOptionsLists {

		/** The users all. */
		USERS_ALL("All"),
		/** The users shared. */
		USERS_SHARED("Shared"),
		/** The users not shared. */
		USERS_NOT_SHARED("Not Shared");

		/** The users optios. */
		private final String usersOptios;

		/**
		 * Instantiates a new users options lists.
		 *
		 * @param options
		 *            the options
		 */
		usersOptionsLists(String options) {
			this.usersOptios = options;
		}

		/**
		 * Gets the user options.
		 *
		 * @return the user options
		 */
		public String getUserOptions() {
			return this.usersOptios;
		}
	}

	/**
	 * The Enum SpecAlertsResultsActionsOptions.
	 */
	public enum SpecAlertsResultsActionsOptions {

		/** The view projects. */
		VIEW_PROJECTS(0, "View Projects"),
		/** The download projects. */
		DOWNLOAD_PROJECTS(1, "Download Projects"),
		/** The download firms. */
		DOWNLOAD_FIRMS(2, "Download Firms"),
		/** The email projects. */
		EMAIL_PROJECTS(3, "Email Projects"),
		/** The track projects. */
		TRACK_PROJECTS(4, "Track Projects"),
		/** The print project details. */
		PRINT_PROJECT_DETAILS(5, "Print Project Details"),
		/** The print project list. */
		PRINT_PROJECT_LIST(6, "Print Project List");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new spec alerts results actions options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		SpecAlertsResultsActionsOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum SpecialFiltersOptions.
	 */
	public enum SpecialFiltersOptions {

		/** The All reports. */
		All_Reports(0, "All Reports"),
		/** The Version 1 reports. */
		Version_1_Reports(1, "Version 1 Reports"),
		/** The Reports with plans and spec. */
		Reports_with_Plans_and_Spec(2, "Reports with Plans and Spec"),
		/** The Design alerts only. */
		DesignAlerts_only(3, "DesignAlerts only");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new special filters options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		SpecialFiltersOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum ConstructionTypeDonutLegends.
	 */
	public enum ConstructionTypeDonutLegends {

		/** The alterations. */
		ALTERATIONS(0, "Alterations"),
		/** The new project. */
		NEW_PROJECT(1, "New Project"),
		/** The interiors. */
		INTERIORS(2, "Interiors"),
		/** The additions. */
		ADDITIONS(3, "Additions");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new construction type donut legends.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		ConstructionTypeDonutLegends(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum MyAccountsAdminToolsOptions.
	 */
	public enum MyAccountsAdminToolsOptions {

		/** The license preferences. */
		LICENSE_PREFERENCES(0, "License Preferences"),
		/** The users. */
		USERS(1, "Users"),
		/** The profiles. */
		PROFILES(2, "Profiles"),
		/** The reports. */
		REPORTS(3, "Reports");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my accounts admin tools options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountsAdminToolsOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum MyAccountUserActions.
	 */
	public enum MyAccountUserActions {

		/** The make admin. */
		MAKE_ADMIN(0, "Make Admin"),
		/** The make user. */
		MAKE_USER(1, "Make User"),
		/** The approve. */
		APPROVE(2, "Approve"),
		/** The remove. */
		REMOVE(3, "Remove"),
		/** The allow user bypass. */
		ALLOW_USER_BYPASS(4, "Allow User to Bypass Profile"),
		/** The donot allow bypass. */
		DONOT_ALLOW_BYPASS(5, "Do Not Allow User to Bypass Profile");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my account user actions.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountUserActions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum MyAccountAllowDoNotAllowUserAction.
	 */
	public enum MyAccountAllowDoNotAllowUserAction {

		/** The allow user bypass. */
		ALLOW_USER_BYPASS(1, "Allow User to Bypass Profile"),
		/** The donot allow bypass. */
		DONOT_ALLOW_BYPASS(2, "Do Not Allow User to Bypass Profile");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my account allow do not allow user action.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountAllowDoNotAllowUserAction(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum trackingListSkipString.
	 */
	public enum trackingListSkipString {

		/** The tracking defaultsavesearch. */
		TRACKING_DEFAULTSAVESEARCH("Default Saved Search"),
		/** The tracking hidden projects. */
		TRACKING_HIDDEN_PROJECTS("Hidden Projects"),
		/** The tracking my projects. */
		TRACKING_MY_PROJECTS("My Projects"),
		/** The tracking my companies. */
		TRACKING_MY_COMPANIES("My Companies");

		/** The skip name. */
		private final String skipName;

		/**
		 * Instantiates a new tracking list skip string.
		 *
		 * @param skipName
		 *            the skip name
		 */
		trackingListSkipString(String skipName) {
			this.skipName = skipName;
		}

		/**
		 * Gets the tracking name.
		 *
		 * @return the tracking name
		 */
		public String getTrackingName() {
			return this.skipName;
		}

	}

	/**
	 * The Enum MyAccountsAccountToolsOptions.
	 */
	public enum MyAccountsAccountToolsOptions {

		/** The my specalerts. */
		MY_SPECALERTS(0, "My SpecAlerts"),
		/** The my tracking lists. */
		MY_TRACKING_LISTS(1, "My Tracking Lists"),
		/** The my saved searches. */
		MY_SAVED_SEARCHES(2, "My Saved Searches"),
		/** The my downloads. */
		MY_DOWNLOADS(3, "My Downloads"),
		/** The my purchases. */
		MY_PURCHASES(4, "My Purchases"),
		/** The my shipping addresses. */
		MY_SHIPPING_ADDRESSES(5, "My Shipping Addresses"),
		/** The my registration info. */
		MY_REGISTRATION_INFO(6, "My Registration Info"),
		/** The my preferences. */
		MY_PREFERENCES(7, "My Preferences");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my accounts account tools options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountsAccountToolsOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum UserRoleOptions.
	 */
	public enum UserRoleOptions {

		/** The admin. */
		ADMIN(0, "Admin"),
		/** The user. */
		USER(1, "User");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new user role options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		UserRoleOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum MyAccountUserFilterOptions.
	 */
	public enum MyAccountUserFilterOptions {

		/** The all. */
		ALL(0, "All"),
		/** The admin. */
		ADMIN(1, "Admin"),
		/** The users. */
		USERS(2, "Users"),
		/** The approved. */
		APPROVED(3, "Approved"),
		/** The not approved. */
		NOT_APPROVED(4, "Not Approved");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my account user filter options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountUserFilterOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum MyAccountShowUserOptions.
	 */
	public enum MyAccountShowUserOptions {

		/** The avail assign profile. */
		AVAIL_ASSIGN_PROFILE(0, "Available to assign this profile"),
		/** The currently assigned profile. */
		CURRENTLY_ASSIGNED_PROFILE(1, "Currently assigned this profile");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my account show user options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountShowUserOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum MyAccountReportsOptions.
	 */
	public enum MyAccountReportsOptions {

		/** The active user. */
		ACTIVE_USER(0, "Active User"),
		/** The system usage. */
		SYSTEM_USAGE(1, "System Usage"),
		/** The project download summary. */
		PROJECT_DOWNLOAD_SUMMARY(2, "Project Download Summary"),
		/** The project download detail. */
		PROJECT_DOWNLOAD_DETAIL(3, "Project Download Detail"),
		/** The company download summary. */
		COMPANY_DOWNLOAD_SUMMARY(4, "Company Download Summary"),
		/** The company download detail. */
		COMPANY_DOWNLOAD_DETAIL(5, "Company Download Detail");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new my account reports options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		MyAccountReportsOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum ShippingAddressOptions.
	 */
	public enum ShippingAddressOptions {

		/** The my registration address. */
		MY_REGISTRATION_ADDRESS(0, "My Registration Address"),
		/** The add new shipping address. */
		ADD_NEW_SHIPPING_ADDRESS(1, "Add New Shipping Address");

		/** The status. */
		private final int status;

		/** The description. */
		private final String description;

		/**
		 * Instantiates a new shipping address options.
		 *
		 * @param aStatus
		 *            the a status
		 * @param desc
		 *            the desc
		 */
		ShippingAddressOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		/**
		 * Status.
		 *
		 * @return the int
		 */
		public int status() {
			return this.status;
		}

		/**
		 * Description.
		 *
		 * @return the string
		 */
		public String description() {
			return this.description;
		}

	}

	/**
	 * The Enum addendaActionsOptions.
	 */
	public enum addendaActionsOptions {

		/** The download project. */
		DOWNLOAD_PROJECT("Download Project"),
		/** The download documents. */
		DOWNLOAD_DOCUMENTS("Download Documents"),
		/** The download takeoff file. */
		DOWNLOAD_TAKEOFF_FILE("Download eTakeoff file"),
		/** The project download detail. */
		PROJECT_DOWNLOAD_DETAIL("Project Download Detail"),
		/** The email project. */
		EMAIL_PROJECT("Email Project"),
		/** The add note. */
		ADD_NOTE("Add Note"),
		/** The track project. */
		TRACK_PROJECT("Track Project"),
		/** The hide project. */
		HIDE_PROJECT("Hide Project"),
		/** The print project details. */
		PRINT_PROJECT_DETAILS("Print Project Details"),
		/** The print addenda list. */
		PRINT_ADDENDA_LIST("Print Addenda List"),
		/** The sign up for stack. */
		SIGN_UP_FOR_STACK("sign up for STACK"),
		/** The find out what it can do for you. */
		FIND_OUT_WHAT_IT_CAN_DO_FOR_YOU("find out what it can do for you"),
		/** The install etakeoff. */
		INSTALL_ETAKEOFF("Install eTakeoff");

		/** The actions option. */
		private final String actionsOption;

		/**
		 * Instantiates a new addenda actions options.
		 *
		 * @param option
		 *            the option
		 */
		addendaActionsOptions(String option) {
			this.actionsOption = option;
		}

		/**
		 * Gets the actions option.
		 *
		 * @return the actions option
		 */
		public String getActionsOption() {
			return this.actionsOption;
		}

	}

	/**
	 * The Enum companyActionsOptions.
	 */
	public enum companyActionsOptions {

		DOWNLOAD_COMPANY("Download Company"), EMAIL_COMPANY("Email Company"), ADD_NOTE("Add Note"), TRACK_COMPANY(
				"Track Company"), PRINT_COMPANY_DETAILS("Print Company Details");

		private final String actionsOption;

		companyActionsOptions(String option) {
			this.actionsOption = option;
		}

		public String getActionsOption() {
			return this.actionsOption;
		}
	}

	public enum ActionsDrpDwnOptionsOutOfSubscription {
		PRINT_PLAN_LIST(0, "Print Plan List"), TAKE_OFF(1, "Install eTakeOff"), ADD_ONLINE_PROJECTS(2,
				"Add Online Project"), ORDER_HARD_COPY_DOCUMENTS(3, "Order Hard Copy Documents");

		private final int status;
		private final String description;

		ActionsDrpDwnOptionsOutOfSubscription(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		public int status() {
			return this.status;
		}

		public String description() {
			return this.description;
		}

	}

	public enum ActionsDrpDwnOptions {
		DOWNLOAD_PROJECT(0, "Download Project"), DOWNLOAD_DOCUMENT(1, "Download Documents"), DOWNLOAD_TAKEOFF_FILE(2,
				"Download TakeOff file"), EMAIL_PROJECT(3, "Email Project"), TRACK_PROJECT(4,
						"Track Project"), HIDE_PROJECT(5, "Hide Project"), PRINT_PROJECT_DETAILS(6,
								"Print Project Details"), PRINT_PLAN_LIST(7, "Print Plan List"), TAKE_OFF(8,
										"Install eTakeOff"), ORDER_HARD_COPY_DOCUMENTS(9, "Order Hard Copy Documents");

		private final int status;
		private final String description;

		ActionsDrpDwnOptions(int aStatus, String desc) {
			this.status = aStatus;
			this.description = desc;
		}

		public int status() {
			return this.status;
		}

		public String description() {
			return this.description;
		}

	}

}
