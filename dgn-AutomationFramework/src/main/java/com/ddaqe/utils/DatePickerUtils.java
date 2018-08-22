package com.ddaqe.utils;

import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

/**
 * The Class DatePickerUtils.
 */
public class DatePickerUtils {

	/** The driver. */
	private WebDriver driver;

	/** The extent test. */
	private ExtentTest extentTest;

	/**
	 * Instantiates a new date picker utils.
	 *
	 * @param driver
	 *            the driver
	 */
	public DatePickerUtils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/** The navigate calender next month. */
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'ui-icon-circle-triangle-e')]")
	private WebElement navigateCalenderNextMonth;

	/** The navigate calender prev month. */
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'ui-icon-circle-triangle-w')]")
	private WebElement navigateCalenderPrevMonth;

	/** The navigate calender month. */
	@FindBy(how = How.XPATH, using = "//span[@class='ui-datepicker-month']")
	private WebElement navigateCalenderMonth;

	/** The navigate calender year. */
	@FindBy(how = How.XPATH, using = "//span[@class='ui-datepicker-year']")
	private WebElement navigateCalenderYear;

	/** The navigate calender month year spec alerts. */
	@FindBy(how = How.XPATH, using = "//div[@id='dp-popup']//h2")
	private WebElement navigateCalenderMonthYearSpecAlerts;

	/** The calender date. */
	@FindBy(how = How.XPATH, using = "//a[@class='ui-state-default']")
	private List<WebElement> calenderDate;

	/** The calender date spec alerts. */
	@FindBy(how = How.XPATH, using = "//div[@id='dp-popup']//table//td[contains(@class,'current-month')]")
	private List<WebElement> calenderDateSpecAlerts;

	/** The navigate calender prev year. */
	@FindBy(how = How.CLASS_NAME, using = "dp-nav-prev-year")
	private WebElement navigateCalenderPrevYear;

	/** The navigate calender next year. */
	@FindBy(how = How.CLASS_NAME, using = "dp-nav-next-year")
	private WebElement navigateCalenderNextYear;

	/** The navigate calender prev month spec alerts. */
	@FindBy(how = How.CLASS_NAME, using = "dp-nav-prev-month")
	private WebElement navigateCalenderPrevMonthSpecAlerts;

	/** The navigate calender next month spec alerts. */
	@FindBy(how = How.CLASS_NAME, using = "dp-nav-next-month")
	private WebElement navigateCalenderNextMonthSpecAlerts;

	/**
	 * Sets the date.
	 *
	 * @param datePicker
	 *            the date picker
	 * @param dateToSet
	 *            the date to set
	 */
	public void setDate(WebElement datePicker, String dateToSet) {
		String day = "";
		String month = "";
		String year = "";

		String[] dateSplit = dateToSet.split("/");

		month = new DateFormatSymbols().getMonths()[Integer.parseInt(dateSplit[0]) - 1].trim();
		day = dateSplit[1].trim();
		year = dateSplit[2].trim();

		datePicker.click();
		navigateTillDate(month, year);

		for (WebElement date : calenderDate) {
			if (date.getText().toUpperCase().equals(day) || ("0" + date.getText().toUpperCase()).equals(day)) {
				date.click();
				break;
			}
		}
	}

	/**
	 * Navigate till date.
	 *
	 * @param month
	 *            the month
	 * @param year
	 *            the year
	 */
	private void navigateTillDate(String month, String year) {
		String expConcateStr = month + " " + year;
		String actConcateStr = "";
		do {
			actConcateStr = navigateCalenderMonth.getText().trim() + " " + navigateCalenderYear.getText().trim();

			if (Integer.parseInt(navigateCalenderYear.getText().trim()) >= Integer.parseInt(year.trim())) {
				if (Integer.parseInt(navigateCalenderYear.getText().trim()) == Integer.parseInt(year.trim())) {
					navigateToMonth(month);
					break;
				}
				navigateCalenderPrevMonth.click();
			} else {
				if (Integer.parseInt(navigateCalenderYear.getText().trim()) == Integer.parseInt(year.trim())) {
					navigateToMonth(month);
					break;
				}
				navigateCalenderNextMonth.click();
			}
		} while (!actConcateStr.toUpperCase().equals(expConcateStr));
	}

	/**
	 * Sets the date spec alerts.
	 *
	 * @param datePicker
	 *            the date picker
	 * @param dateToSet
	 *            the date to set
	 * @return true, if successful
	 */
	public boolean setDateSpecAlerts(WebElement datePicker, String dateToSet) {
		String day = "";
		String month = "";
		String year = "";

		String[] dateSplit = dateToSet.split("/");

		month = new DateFormatSymbols().getMonths()[Integer.parseInt(dateSplit[0]) - 1].trim();
		day = dateSplit[1].trim();
		year = dateSplit[2].trim();

		datePicker.click();
		boolean isSelected = navigateTillDateSpecAlerts(month, year);

		for (WebElement date : calenderDateSpecAlerts) {
			if (date.getText().toUpperCase().equals(day) || ("0" + date.getText().toUpperCase()).equals(day)) {
				date.click();
				break;
			}
		}
		return isSelected;
	}

	/**
	 * Gets the calendar month year.
	 *
	 * @return the calendar month year
	 */
	public String[] getCalendarMonthYear() {
		String actConcateStr = navigateCalenderMonthYearSpecAlerts.getText().trim();
		return actConcateStr.split("\\s+");
	}

	/**
	 * Navigate till date spec alerts.
	 *
	 * @param month
	 *            the month
	 * @param year
	 *            the year
	 * @return true, if successful
	 */
	private boolean navigateTillDateSpecAlerts(String month, String year) {
		String expConcateStr = month + " " + year;
		do {

			String[] splitByMonthYear = getCalendarMonthYear();
			if (splitByMonthYear.length > 0) {
				if (Integer.parseInt(splitByMonthYear[1]) >= Integer.parseInt(year.trim())) {
					if (Integer.parseInt(splitByMonthYear[1]) == Integer.parseInt(year.trim())) {
						navigateToMonthSpecAlerts(month);
						return false;
					}
					if (navigateCalenderPrevYear.getAttribute("class").contains("disabled")) {
						return false;
					} else {
						navigateCalenderPrevYear.click();

					}
				} else {
					if (Integer.parseInt(navigateCalenderYear.getText().trim()) == Integer.parseInt(year.trim())) {
						navigateToMonthSpecAlerts(month);
						return false;
					}
					if (navigateCalenderNextYear.getAttribute("class").contains("disabled")) {
						return false;
					} else {
						navigateCalenderNextYear.click();
					}
				}
			}
		} while (!navigateCalenderMonthYearSpecAlerts.getText().trim().toUpperCase().equals(expConcateStr));
		return true;
	}

	/**
	 * Navigate to month.
	 *
	 * @param month
	 *            the month
	 * @return true, if successful
	 */
	private boolean navigateToMonth(String month) {
		for (int i = 0; i < 12; i++) {
			if (getMonthNumber(navigateCalenderMonth.getText().trim()) <= getMonthNumber(month)) {
				if (getMonthNumber(navigateCalenderMonth.getText().trim()) == getMonthNumber(month)) {
					return false;
				}
				if (navigateCalenderPrevMonth.getAttribute("class").contains("disabled")) {
					return false;
				} else {
					navigateCalenderPrevMonth.click();
				}
			} else {
				if (navigateCalenderNextMonth.getAttribute("class").contains("disabled")) {
					return false;
				} else {
					navigateCalenderNextMonth.click();

				}
			}
		}
		return true;
	}

	/**
	 * Navigate to month spec alerts.
	 *
	 * @param month
	 *            the month
	 * @return true, if successful
	 */
	private boolean navigateToMonthSpecAlerts(String month) {
		for (int i = 0; i < 12; i++) {

			String[] splitByMonthYear = getCalendarMonthYear();
			if (splitByMonthYear.length > 0) {
				if (getMonthNumber(splitByMonthYear[0]) <= getMonthNumber(month)) {
					if (getMonthNumber(splitByMonthYear[0]) == getMonthNumber(month)) {
						return false;
					}
					if (navigateCalenderPrevMonthSpecAlerts.getAttribute("class").contains("disabled")) {
						return false;
					} else {
						navigateCalenderPrevMonthSpecAlerts.click();
					}
				} else {
					if (navigateCalenderNextMonthSpecAlerts.getAttribute("class").contains("disabled")) {
						return false;
					} else {
						navigateCalenderNextMonthSpecAlerts.click();
					}
				}
			}
		}
		return true;
	}

	/**
	 * Gets the month number.
	 *
	 * @param monthName
	 *            the month name
	 * @return the month number
	 */
	private int getMonthNumber(String monthName) {
		return Month.valueOf(monthName.toUpperCase()).getValue();
	}

}
