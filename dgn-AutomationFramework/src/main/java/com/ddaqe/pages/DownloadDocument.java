package com.ddaqe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class DownloadDocument {
	private WebDriver driver;
	private ExtentTest extentTest;
	
	@FindBy(how = How.ID, using = "rdDownloadNow")
	private WebElement downloadNowRadioBtn;
	
	@FindBy(how = How.ID, using = "rdDownloadLater")
	private WebElement downloadLaterRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//a[@class='downLoadDocumnetSubmit']")
	private WebElement saveDocumentBtn;
	
	@FindBy(how = How.XPATH, using = "//a[@class='downLoadDocumnetClose']")
	private WebElement cancelDocumentBtn;
	
	public DownloadDocument(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		extentTest = TestListener.getExtentTest();
		extentTest.log(Status.INFO, "Navigate to the Help Page");
	}
	
	//Click on Save button and return the object of Project Plan page.
	public ProjectPlansPage clickOnSavePlanDocumentButton(){
		extentTest.log(Status.INFO, "Click on Save button and return the object of Project Plan page.");
		saveDocumentBtn.click();
		return new ProjectPlansPage(driver);
	}
	
	// Click on Save button and return the object of Project Spec page.
	public ProjectSpecsPage clickOnSaveSpecDocumentButton() {
		extentTest.log(Status.INFO, "Click on Save button and return the object of Project Spec page.");
		saveDocumentBtn.click();
		return new ProjectSpecsPage(driver);
	}
	
	// Click on Save button and return the object of Project Addenda page.
	public ProjectAddendaPage clickOnSaveAddendaDocumentButton() {
		extentTest.log(Status.INFO, "Click on Save button and return the object of Project Addenda page.");
		saveDocumentBtn.click();
		return new ProjectAddendaPage(driver);
	}
}
