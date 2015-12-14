package com.projectStore.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TransactionResultsPage extends LoadableComponent<TransactionResultsPage>{

	private final WebDriver driver;
	private final LoadableComponent<?> parent;

	@FindBy(how = How.CLASS_NAME, using = "entry-title")
	@CacheLookup
	private WebElement transactionResultLabel;

	@FindBy(how = How.CLASS_NAME, using = "wpsc-purchase-log-transaction-results")
	@CacheLookup
	private WebElement purchaseResultTable;

	@FindBy(how = How.LINK_TEXT, using = "Continue")
	@CacheLookup
	private WebElement buttonContinue;
	
	@FindBy(how = How.XPATH, using = "//article[@id='post-30']/div/div[2]/p[3]")
	@CacheLookup
	private WebElement totalResultLabel;
	
	


	public TransactionResultsPage(WebDriver driver,LoadableComponent<?> parent){
		this.parent = parent;
		this.driver = driver;
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get("http://store.demoqa.com/products-page/checkout/");
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url,"http://store.demoqa.com/products-page/checkout/");
		//assertTrue("Not on the issue entry page: " + url, url.endsWith("/entry"));
	}

	public String getPageTitle(){

		return driver.getTitle();
	}
	
	public String getCurrentUrl(){

		return driver.getCurrentUrl();
	}

	public String getColumnName(int row, int column){
		// Now get all the TR elements from the table
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 30); 
		wait.until(ExpectedConditions.visibilityOfAllElements(purchaseResultTable.findElements(By.tagName("tr")))); 
		
		List<WebElement> allRows = purchaseResultTable.findElements(By.tagName("tr"));
		List<WebElement> columns = allRows.get(row).findElements(By.xpath("td"));
		return columns.get(column).getText();
		/*
	        for(WebElement trElement : allRows)
	        {
	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
	            System.out.println("NUMBER OF COLUMNS="+td_collection.size());
	            col_num=1;
	            for(WebElement tdElement : td_collection)
	            {
	                System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
	                col_num++;
	            }
	            row_num++;
	        }*/ 		
	}


	public String getTotalResultLabel() {
		
		return totalResultLabel.getText();
	}



}
