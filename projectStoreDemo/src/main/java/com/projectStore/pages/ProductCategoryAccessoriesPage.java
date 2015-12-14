package com.projectStore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class ProductCategoryAccessoriesPage extends LoadableComponent<ProductCategoryAccessoriesPage>{

	private final WebDriver driver;
	private final LoadableComponent<?> parent;
	
	@FindBy(how = How.NAME, using = "Buy")
	@CacheLookup
	private WebElement buttonAddMagicMouseToCart;

	@FindBy(how = How.LINK_TEXT, using = "Go to Checkout")
	@CacheLookup
	private WebElement buttonGoToCheckout;
	
	@FindBy(how = How.CSS, using = "#fancy_notification_content > span")
	@CacheLookup
	private WebElement confirmCheckoutMessage;
	
	


	public ProductCategoryAccessoriesPage(WebDriver driver,LoadableComponent<?> parent){
		this.parent = parent;
		this.driver = driver;
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get("http://store.demoqa.com/products-page/product-category/accessories/");
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url,"http://store.demoqa.com/products-page/product-category/accessories/");
	}

	public String getPageTitle(){

		return driver.getTitle();
	}
	
	public String getCurrentUrl(){

		return driver.getCurrentUrl();
	}
	
	public String getConfirmCheckoutMessage() {
		return confirmCheckoutMessage.getText();
	}
	
	public void clickButtonAddMagicToCart() {
		buttonAddMagicMouseToCart.click();
	}

	public CheckoutPage clickButtonGoToCheckout() {
		buttonGoToCheckout.click();
		return new CheckoutPage(driver,this);
	}


	

}
