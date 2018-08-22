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
import com.ddaqe.utils.DGNEnum;
import com.ddaqe.utils.SeleniumUtils;

public class EcommercePage {

	private WebDriver driver;
	private ExtentTest extentTest;
	private String projectName;

	@FindBy(how = How.ID, using = "continueLink")
	private WebElement continueLink;

	@FindBy(how = How.ID, using = "checkOutLinkTopPurchase")
	private WebElement checkOutLinkTopPurchase;

	@FindBy(how = How.ID, using = "lnkCancelBtn")
	private WebElement cancelBtn;

	@FindBy(how = How.ID, using = "btnProjectActions")
	private WebElement btnProjectActions;

	@FindBy(how = How.ID, using = "lnkremoveFromCart")
	private WebElement lnkremoveFromCart;

	@FindBy(how = How.ID, using = "purchaseTopLabel")
	private WebElement purchaseTopLabel;

	@FindBy(how = How.ID, using = "project-check-all")
	private WebElement selectAllChk;

	@FindBy(how = How.ID, using = "ctl00_contentPlaceHolderHeader_ecommerceContent")
	private WebElement purchaseTotalsFrame;

	@FindBy(how = How.CLASS_NAME, using = "cartEmptyText")
	private WebElement cartEmptyText;

	@FindBy(how = How.ID, using = "continueShoppingEmptyLink")
	private WebElement continueShoppingEmptyLink;

	@FindBy(how = How.ID, using = "recalculateButton")
	private WebElement recalculateButton;

	@FindBy(how = How.ID, using = "lnkCancelBtn")
	private WebElement lnkCancelBtn;

	@FindBy(how = How.ID, using = "printLink")
	private WebElement printLink;

	@FindBy(how = How.ID, using = "//div[@id='itemsInShoppingCart']//div[contains(@id,'lnkDodgeNumber')]")
	private List<WebElement> itemsInShoppingCart;

	@FindBy(how = How.ID, using = "//div[contains(@id,'HardCopyLabel')]//following-sibling::div[contains(@id,'HardCopyPrice')]//div[@class='cancelLineItem']//a")
	private List<WebElement> removeHardCopy;

	@FindBy(how = How.XPATH, using = "//h3[@id='purchaseTopLabel']//following-sibling::div[contains(.,'Minimum Charge')]")
	private WebElement additionalCharge;

	@FindBy(how = How.ID, using = "shipHardCopy")
	private WebElement shipHardCopy;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'HardCopyLabel')]//following-sibling::div[contains(@id,'HardCopyPrice')]//a[contains(@id,'CancelButton')]")
	private List<WebElement> hardCopyCancelButton;

	@FindBy(how = How.ID, using = "ddlShippingAddress")
	private WebElement ddlShippingAddress;

	@FindBy(how = How.ID, using = "popupAddShippingAddress")
	private WebElement popupAddShippingAddress;

	@FindBy(how = How.ID, using = "addShippingAddress_txtName")
	private WebElement addShippingAddressName;

	@FindBy(how = How.ID, using = "addShippingAddress_txtRecipientFullName")
	private WebElement addShippingAddressRecipientFullName;

	@FindBy(how = How.ID, using = "addShippingAddress_txtRecipientPhoneNumber")
	private WebElement addShippingAddressRecipientPhoneNumber;

	@FindBy(how = How.ID, using = "addShippingAddress_txtCompanyName")
	private WebElement addShippingAddressCompanyName;

	@FindBy(how = How.ID, using = "addShippingAddress_txtAddress")
	private WebElement addShippingAddressTxtAddress;

	@FindBy(how = How.ID, using = "addShippingAddress_txtCity")
	private WebElement addShippingAddressTxtCity;

	@FindBy(how = How.ID, using = "addShippingAddress_stateDropDownList")
	private WebElement addShippingAddressStateDropDownList;

	@FindBy(how = How.ID, using = "addShippingAddress_txtZip")
	private WebElement addShippingAddressTxtZip;

	@FindBy(how = How.ID, using = "addShippingAddressSave")
	private WebElement addShippingAddressSave;

	@FindBy(how = How.ID, using = "addShippingAddressClose")
	private WebElement addShippingAddressClose;

	@FindBy(how = How.XPATH, using = "//div[@class='drNumberChkBox']//input")
	private List<WebElement> chkProjectList;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'priceItemsTop') and  contains(.,'Total')]//following-sibling::div")
	private WebElement totalPriceAmt;

	@FindBy(how = How.XPATH, using = "//div[@id='bottomCheckOutHeader']//div[contains(@id,'priceItems') and  contains(.,'Total')]//following-sibling::div")
	private WebElement totalPriceAmtBottom;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'priceItemsTop') and  contains(.,'Subtotal')]//following-sibling::div")
	private WebElement subTotalPriceAmt;

	@FindBy(how = How.XPATH, using = "//div[@id='bottomCheckOutHeader']//div[contains(@id,'priceItems') and  contains(.,'Subtotal')]//following-sibling::div")
	private WebElement subTotalPriceAmtBottom;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'priceItemsTop') and  contains(.,'Tax')]//following-sibling::div")
	private WebElement taxPriceAmt;

	@FindBy(how = How.XPATH, using = "//div[@id='bottomCheckOutHeader']//div[contains(@id,'priceItems') and  contains(.,'Tax')]//following-sibling::div")
	private WebElement taxPriceAmtBottom;

	@FindBy(how = How.ID, using = "My-Account---My-Purchases")
	private WebElement breadcrumb1;

	@FindBy(how = How.ID, using = "Order-Receipt")
	private WebElement breadcrumb2;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'ItemDescription')]/span")
	private List<WebElement> itemDescription;

	@FindBy(how = How.XPATH, using = "//*[@id='OrderReceipt']//a[contains(.,'Print')]")
	private WebElement printTopLink;

	@FindBy(how = How.ID, using = "optionsLinkTop")
	private WebElement optionsLinkTop;

	@FindBy(how = How.ID, using = "ucHardCopyOption_rbPickUp")
	private WebElement optionPickUp;

	@FindBy(how = How.ID, using = "ucHardCopyOption_lnkSaveOption")
	private WebElement optionPopUpSaveOption;

	@FindBy(how = How.ID, using = "ucHardCopyOption_rbShip")
	private WebElement optionShip;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'billship')]")
	private WebElement billShipAddress;

	@FindBy(how = How.XPATH, using = "//h3[contains(@class,'charged-on')]")
	private WebElement chargedOnCreditCard;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'totals')]")
	private WebElement purchaseTotalsSection;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'lnkReport')]")
	private WebElement projectTitleLink;

	public EcommercePage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Ecommerce Page");
		PageFactory.initElements(driver, this);
	}

	public EcommercePage(WebDriver driver, String name) {
		this.driver = driver;
		this.projectName = name;
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Ecommerce Page");
		PageFactory.initElements(driver, this);
	}

	public String getCurrentURL() {
		extentTest.log(Status.INFO, "Get the Current URL");
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void SwitchToFrame() {
		SeleniumUtils.switchToFrame(driver, purchaseTotalsFrame);
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public ProjectResultsPage clickContinueShoppingBtn() {
		extentTest.log(Status.INFO, "Clicking the Continue shopping button");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(continueLink));
		continueLink.click();
		return new ProjectResultsPage(driver);
	}

	public void clickOnProjectTitleLink() {
		extentTest.log(Status.INFO, "Clicking the Project Title link");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(projectTitleLink));
		projectTitleLink.click();
	}

	public void clickOnActionsDropDown() {
		extentTest.log(Status.INFO, "Clicking the Actions dropdown ");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(btnProjectActions));
		btnProjectActions.click();
	}

	public void clickOnRecalculateButton() {
		extentTest.log(Status.INFO, "Clicking the Recalculate Button ");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(recalculateButton));
		recalculateButton.click();
	}

	public void clickOnSeleclAllChk() {
		extentTest.log(Status.INFO, "Clicking the Selct All CehckBox");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(selectAllChk));
		selectAllChk.click();
	}

	public void clickOnRemoveLinkUnderActionsDropDown() {
		extentTest.log(Status.INFO, "Clicking the remove link under Actions dropdown ");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(lnkremoveFromCart));
		lnkremoveFromCart.click();
	}

	public void clickOnCancelBtnWithNo() {
		extentTest.log(Status.INFO, "Clicking the Cancel Button");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(lnkCancelBtn));
		lnkCancelBtn.click();
		driver.switchTo().alert().dismiss();
	}

	public ProjectResultsPage clickOnCancelBtnWithYes() {
		extentTest.log(Status.INFO, "Click on Cancel button");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(cancelBtn));
		SeleniumUtils.isClickable(cancelBtn, driver);
		cancelBtn.click();
		driver.switchTo().alert().accept();
		return new ProjectResultsPage(driver);
	}

	public boolean isAlertDisplayed() {
		if (new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent()) == null) {
			return false;
		} else {
			return true;
		}
	}

	public void alertAccept() {
		extentTest.log(Status.INFO, "Clicking the accept button in the alert popped up");
		driver.switchTo().alert().accept();
	}

	public boolean isProjectFoundInItemsInShoppingCart() {
		extentTest.log(Status.INFO, "Verify if the project found in the items in the shopping cart");
		return CommonUtils.checkStringContainInCompleteList(projectName, itemsInShoppingCart);
	}

	public void clickRemoveHardCopy() {
		extentTest.log(Status.INFO, "Click to remove the Hard Copy");
		for (int i = 0; i < removeHardCopy.size(); i++) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(removeHardCopy.get(i)));
			removeHardCopy.get(i).click();
			break;
		}
	}

	public void clickContinueShoppingButton() {
		extentTest.log(Status.INFO, "Click the continue shopping button");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(continueShoppingEmptyLink));
		continueShoppingEmptyLink.click();
	}

	public Integer getHardCopyCount() {
		extentTest.log(Status.INFO, "Get the hard copy count");
		return removeHardCopy.size();
	}

	public boolean isAdditionalChargeDisplayed() {
		extentTest.log(Status.INFO, "Verify if the additional charge is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(additionalCharge));
			return additionalCharge.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isRemoveHardCopyDisplayed() {
		extentTest.log(Status.INFO, "Verify if the additional charge is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(removeHardCopy.get(0)));
			return removeHardCopy.get(0).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isProjectTitleLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Project Title Link is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(projectTitleLink));
			return projectTitleLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isContinueShoppingEmptyLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the continue Shopping Empty Link is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(continueShoppingEmptyLink));
			return continueShoppingEmptyLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isShipHardCopyDisplayed() {
		extentTest.log(Status.INFO, "verify if the ship hard copy option is displayed");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(shipHardCopy));
			return shipHardCopy.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isPrintTopLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the print link is displayed or not at the top");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(printTopLink));
			return printTopLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isbillShipAddressDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Billing/Shipping Address is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(billShipAddress));
			return billShipAddress.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isChargedOnCreditCardDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Charged On Credit Card is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(chargedOnCreditCard));
			return chargedOnCreditCard.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isPrintLinkDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Print Link is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(printLink));
			return printLink.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isPurchaseTotalsSectionDisplayed() {
		extentTest.log(Status.INFO, "Verify if the Purchase Totals sections is displayed or not");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(purchaseTotalsSection));
			return purchaseTotalsSection.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public PaymentGatewayCustomerDetailsDGN clickCheckOutLinkTopPurchase() {
		extentTest.log(Status.INFO, "Click on the check out link in the top purchases");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(checkOutLinkTopPurchase));
		checkOutLinkTopPurchase.click();
		return new PaymentGatewayCustomerDetailsDGN(driver);
	}

	public void clickFirstHardCopyCancelButton() {
		extentTest.log(Status.INFO, "Click a hard copy cancel button");
		for (int i = 0; i < hardCopyCancelButton.size(); i++) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(hardCopyCancelButton.get(i)));
			hardCopyCancelButton.get(i).click();
			break;
		}
	}

	public void chkFirstProject() {
		extentTest.log(Status.INFO, "Select the first project");
		for (int i = 0; i < chkProjectList.size(); i++) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(chkProjectList.get(i)));
			chkProjectList.get(i).click();
			break;
		}
	}

	public void clickAllHardCopyCancelButton() {
		extentTest.log(Status.INFO, "Click a hard copy cancel button");
		for (int i = 0; i < hardCopyCancelButton.size(); i++) {
			hardCopyCancelButton.get(i).click();
		}
	}

	public boolean isSetOfItemDescriptionValid() {
		extentTest.log(Status.INFO, "Verify the Item description for set of Items");
		for (int i = 0; i < itemDescription.size(); i++) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(itemDescription.get(i)));
			if (itemDescription.get(i).getText().contains("Complete set of")) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyShippingAddressOptions() {
		extentTest.log(Status.INFO, "Verify the shipping address dropdown options");
		boolean isMatch = false;
		ddlShippingAddress.click();
		Select se = new Select(ddlShippingAddress);
		List<WebElement> options = se.getOptions();
		for (DGNEnum.ShippingAddressOptions opt : DGNEnum.ShippingAddressOptions.values()) {
			if (options.get(0).getText().equals(opt.description())) {
				isMatch = true;
			} else if (options.get(options.size() - 1).getText().equals(opt.description())) {
				isMatch = true;
			}

		}
		return isMatch;

	}

	public void selectAddNewShippingAddress() {
		extentTest.log(Status.INFO, "Failed to select the Add NEw Shipping Address");
		SeleniumUtils.selectByVisibleText(ddlShippingAddress,
				DGNEnum.ShippingAddressOptions.ADD_NEW_SHIPPING_ADDRESS.description());

	}

	public boolean isAddShippingAddressPopUpDisplayed() {
		extentTest.log(Status.INFO, "verify if the Add Shipping Pop Up is displayed");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(popupAddShippingAddress));
			return popupAddShippingAddress.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isShippingAddressDropdwonDisplayed() {
		extentTest.log(Status.INFO, "verify if the Shipping dropdown is displayed");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(ddlShippingAddress));
			return ddlShippingAddress.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isPurchaseTopLabelDisplayed() {
		extentTest.log(Status.INFO, "verify if the Purchase Total at the top is displayed");
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(purchaseTopLabel));
			return purchaseTopLabel.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void populateNewShippingAddressPopUp() {
		extentTest.log(Status.INFO, "Populating the New Shipping Address Dropdown");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(addShippingAddressName));
		addShippingAddressName.sendKeys("John");
		addShippingAddressRecipientFullName.sendKeys("John Watson");
		addShippingAddressRecipientPhoneNumber.sendKeys("9987778999");
		addShippingAddressCompanyName.sendKeys("Harman");
		addShippingAddressTxtAddress.sendKeys("Kika Ct");
		addShippingAddressTxtCity.sendKeys("San Diego");
		SeleniumUtils.selectByVisibleText(addShippingAddressStateDropDownList, "California");
		addShippingAddressTxtZip.sendKeys("11057");
		addShippingAddressSave.click();
	}

	public String getCartEmptyText() {
		extentTest.log(Status.INFO, "Get the cart empty text");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(cartEmptyText));
		return cartEmptyText.getText().trim();
	}

	public double getTotalAmount() {
		extentTest.log(Status.INFO, "Get the total text");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(totalPriceAmt));
		return Double.parseDouble(totalPriceAmt.getText().replaceAll("\\$", ""));
	}

	public boolean isSumTotalAsExpectedAtTop() {
		double subTotal = Double.parseDouble(subTotalPriceAmt.getText().replaceAll("\\$", ""));
		double tax = Double.parseDouble(taxPriceAmt.getText().replaceAll("\\$", ""));
		double total = Double.parseDouble(totalPriceAmt.getText().replaceAll("\\$", ""));
		double sum = subTotal + tax;
		return total == sum;
	}

	public boolean isSumTotalAsExpectedAtBottom() {
		double subTotal = Double.parseDouble(subTotalPriceAmtBottom.getText().replaceAll("\\$", ""));
		double tax = Double.parseDouble(taxPriceAmtBottom.getText().replaceAll("\\$", ""));
		double total = Double.parseDouble(totalPriceAmtBottom.getText().replaceAll("\\$", ""));
		double sum = subTotal + tax;
		return total == sum;
	}

	public String getTextBreadCrumb1() {
		extentTest.log(Status.INFO, "Get the text of the breadcrumb part 1");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(breadcrumb1));
		return breadcrumb1.getText().trim();

	}

	public String getTextBreadCrumb2() {
		extentTest.log(Status.INFO, "Get the text of the breadcrumb part 2");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(breadcrumb2));
		return breadcrumb2.getText().trim();
	}

	public void clickPrintLink() {
		extentTest.log(Status.INFO, "Clicking on the Print Button");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(printLink));
		printLink.click();
	}

	public void clickPrintTopLink() {
		extentTest.log(Status.INFO, "Clicking on the Print Link at the Top");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(printTopLink));
		printTopLink.click();
	}

	public void clickOptionsLinkTop() {
		extentTest.log(Status.INFO, "Clicking on the Options Link ");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(optionsLinkTop));
		optionsLinkTop.click();
	}

	public void clickOptionPickUp() {
		extentTest.log(Status.INFO, "Clicking on the Pick Up Option");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(optionPickUp));
		optionPickUp.click();
	}

	public void clickOptionPopUpSaveButton() {
		extentTest.log(Status.INFO, "Clicking on the Save button under Option popup");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(optionPopUpSaveOption));
		optionPopUpSaveOption.click();
	}

	public void clickOptionShip() {
		extentTest.log(Status.INFO, "Clicking on the Ship Option");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(optionShip));
		optionShip.click();
	}
}
