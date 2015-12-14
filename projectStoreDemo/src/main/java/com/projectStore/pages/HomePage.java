package com.projectStore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage extends LoadableComponent<HomePage>{

	private final WebDriver driver;
	//private final LoadableComponent<?> parent;

	@FindBy(how = How.LINK_TEXT, using = "Home")
	@CacheLookup
	private WebElement menuHome;

	@FindBy(how = How.LINK_TEXT, using = "All Product")
	@CacheLookup
	private WebElement menuAllProduct;

	@FindBy(how = How.LINK_TEXT, using = "Product Category")
	@CacheLookup
	private WebElement menuProductCategory;

	@FindBy(how = How.LINK_TEXT, using = "Accessories")
	@CacheLookup
	private WebElement menuItemAccessories;

	public HomePage(WebDriver driver){
		//this.parent = parent;
		this.driver = driver;
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get("http://store.demoqa.com/");
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url,"http://store.demoqa.com/");
		//assertTrue("Not on the issue entry page: " + url, url.endsWith("/entry"));
	}

	public String getPageTitle(){

		return driver.getTitle();
	}
	
	public String getCurrentUrl(){

		return driver.getCurrentUrl();
	}

	public void clickMenuAllProduct() {
		menuAllProduct.click();
	}
	
	public void clickMenuHome() {
		menuHome.click();
	}
	
	public void clickMenuProductCategory() {
		menuProductCategory.click();
	}
	
	public ProductCategoryAccessoriesPage clickMenuItemAccessories() {
		menuItemAccessories.click();
		return new ProductCategoryAccessoriesPage(driver,this);
	}
	
	public void hoverOverMenuProductCategory() {
		Actions action = new Actions(driver);
		action.moveToElement(menuProductCategory);
		action.perform();
		
	}

}
