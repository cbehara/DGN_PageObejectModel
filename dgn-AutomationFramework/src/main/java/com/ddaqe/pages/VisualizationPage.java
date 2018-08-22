package com.ddaqe.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ddaqe.Listener.TestListener;

public class VisualizationPage {
	private WebDriver driver;
	private ExtentTest extentTest;

	@FindBy(how = How.CSS, using = "[class*='fleft '] [class='tileiconparent'] [class='tilesWrap']")
	private List<WebElement> leftSelectionView;
	
	@FindBy(how = How.CSS, using = "[class*='fright '] [class='tileiconparent'] [class='tilesWrap']")
	private List<WebElement> rightSelectionView;
	
	@FindBy(how = How.CSS, using = "[class*='fleft '] a[class*='iconTiles']")
	private WebElement leftTilesIcon;
	
	@FindBy(how = How.CSS, using = "[class*='fright '] a[class*='iconTiles']")
	private WebElement rightTilesIcon;
			
	public VisualizationPage(WebDriver driver) {
		this.driver = driver;
		extentTest = TestListener.getExtentTest();
		PageFactory.initElements(driver, this);
	}

	public ProjectResultsPage selectVisualizationFromLeftSelectionView(String visualizationToBeSelected) {
		extentTest.log(Status.INFO, "Selecting visualization from left selection view.");
		for(WebElement visualization : leftSelectionView){
			if(visualizationToBeSelected.equalsIgnoreCase(visualization.getText())){
				visualization.click();
			}
		}
		return new ProjectResultsPage(driver);
	}
	
	public ProjectResultsPage selectVisualizationFromRightSelectionView(String visualizationToBeSelected) {
		extentTest.log(Status.INFO, "Selecting visualization from right selection view.");
		for(WebElement visualization : rightSelectionView){
			if(visualizationToBeSelected.equalsIgnoreCase(visualization.getText())){
				visualization.click();
			}
		}
		return new ProjectResultsPage(driver);
	}
	
	public String getSelectedVisualizationFromLeftSelectionView() {
		extentTest.log(Status.INFO, "Getting selected visualization from left selection view.");
		String selectedVisualization="";
		for(WebElement visualization : leftSelectionView){
			if(visualization.getAttribute("style").contains("opacity: 0.5")){
				selectedVisualization = visualization.getText();
			}
		}
		return selectedVisualization;
	}
	
	public String getSelectedVisualizationFromRightSelectionView() {
		extentTest.log(Status.INFO, "Getting selected visualization from right selection view.");
		String selectedVisualization="";
		for(WebElement visualization : rightSelectionView){
			if(visualization.getAttribute("style").contains("opacity: 0.5")){
				selectedVisualization = visualization.getText();
			}
		}
		return selectedVisualization;
	}
	
	public VisualizationPage clickLeftTileIcon() {
		extentTest.log(Status.INFO, "Clicking left tile icon.");
		leftTilesIcon.click();
		return new VisualizationPage(driver);
	}
	
	public VisualizationPage clickRightTileIcon() {
		extentTest.log(Status.INFO, "Clicking right tile icon.");
		rightTilesIcon.click();
		return new VisualizationPage(driver);
	}
}
