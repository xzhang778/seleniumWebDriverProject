package com.projectStore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class CheckoutPage extends LoadableComponent<CheckoutPage>{
	
	private final WebDriver driver;
	private final LoadableComponent<?> parent;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Update' and @name='submit']")
	@CacheLookup
	private WebElement buttonUpdate;

	@FindBy(how = How.XPATH, using = "//input[@value='Remove' and @name='submit']")
	@CacheLookup
	private WebElement buttonRemove;
	
	
	@FindBy(how = How.LINK_TEXT, using = "Continue")
	@CacheLookup
	private WebElement buttonContinue;
	
	/*
	@FindBy(how = How.CSS, using = "a.step2 > span")
	@CacheLookup
	private WebElement buttonContinue;*/
	
	@FindBy(how = How.NAME, using = "quantity")
	@CacheLookup
	private WebElement txtBoxQuantity;

	@FindBy(how = How.XPATH, using = "//div[@id='checkout_page_container']/div/table/tbody/tr[2]/td[4]/span")
	@CacheLookup
	private WebElement txtPrice;

	@FindBy(how = How.CSS, using = "span.pricedisplay > span.pricedisplay")
	@CacheLookup
	private WebElement txtTotal;

	public CheckoutPage(WebDriver driver,LoadableComponent<?> parent){
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
	
	public void clickButtonUpdate() {
		buttonUpdate.click();
	}

	public CheckoutPurchasePage clickButtonContinue() {
		buttonContinue.click();
		return new CheckoutPurchasePage(driver,this);
	}
	
	public void clickButtonRemove() {
		buttonRemove.click();
	}
	
	public String getTxtBoxQuantity(){

		return txtBoxQuantity.getAttribute("value");
	}
	
	public String getPrice(){

		return txtPrice.getText();
	}
	
	public String getTotal(){

		return txtTotal.getText();
	}
	
}
