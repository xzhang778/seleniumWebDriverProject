package com.projectStore.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CheckoutPurchasePage extends LoadableComponent<CheckoutPurchasePage>{
	
	private final WebDriver driver;
	private final LoadableComponent<?> parent;
	
	@FindBy(how = How.ID, using = "wpsc_checkout_form_9")
	@CacheLookup
	private WebElement textBoxEmail;

	@FindBy(how = How.ID, using = "wpsc_checkout_form_2")
	@CacheLookup
	private WebElement textBoxFirstName;

	@FindBy(how = How.ID, using = "wpsc_checkout_form_3")
	@CacheLookup
	private WebElement textBoxLastName;
	
	@FindBy(how = How.ID, using = "wpsc_checkout_form_4")
	@CacheLookup
	private WebElement textBoxAddress;
	
	@FindBy(how = How.ID, using = "wpsc_checkout_form_5")
	@CacheLookup
	private WebElement textBoxCity;
	
	@FindBy(how = How.ID, using = "wpsc_checkout_form_6")
	@CacheLookup
	private WebElement textBoxUndefined;

	@FindBy(how = How.ID, using = "wpsc_checkout_form_18")
	@CacheLookup
	private WebElement textBoxPhoneNumber;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Purchase' and @name='submit']")
	@CacheLookup
	private WebElement buttonPurchase;

	@FindBy(how = How.LINK_TEXT, using = "Go Back")
	@CacheLookup
	private WebElement buttonGoBack;
	
	@FindBy(how = How.CSS, using = "span.pricedisplay.checkout-shipping > span.pricedisplay")
	@CacheLookup
	private WebElement labelTotalShippingPrice;
	
	@FindBy(how = How.CSS, using = "tr.total_price.total_item > td.wpsc_totals > span.pricedisplay.checkout-shipping > span.pricedisplay")
	@CacheLookup
	private WebElement labelItemCost;
	
	@FindBy(how = How.CSS, using = "#checkout_total > span.pricedisplay")
	@CacheLookup
	private WebElement labelTotalPrice;
	
	public CheckoutPurchasePage(WebDriver driver,LoadableComponent<?> parent){
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
	
	public String getTotalShippingPrice(){

		return labelTotalShippingPrice.getText();
	}
	
	public String getItemCost(){

		return labelItemCost.getText();
	}
	
	public String getTotalPrice(){

		return labelTotalPrice.getText();
	}
	
	public String getCurrentUrl(){

		return driver.getCurrentUrl();
	}
	
	public void enterEmailAddress(String emailAddress) {
		textBoxEmail.clear();
		textBoxEmail.sendKeys(emailAddress);
	}
	public void enterFirstName(String firstName) {
		textBoxFirstName.clear();
		textBoxFirstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		textBoxLastName.clear();
		textBoxLastName.sendKeys(lastName);
	}
	public void enterAddress(String address) {
		textBoxAddress.clear();
		textBoxAddress.sendKeys(address);
	}
	
	public void enterCity(String city) {
		textBoxCity.clear();
		textBoxCity.sendKeys(city);
	}
	public void enterProvince(String province) {
		textBoxUndefined.clear();
		textBoxUndefined.sendKeys(province);
	}
	
	public void enterPhoneNumber(String phoneNumber) {
		textBoxPhoneNumber.clear();
		textBoxPhoneNumber.sendKeys(phoneNumber);
	}
	
	public void selectCountry(String option){
		Select dropDownListCountry = new Select(driver.findElement(By.id("wpsc_checkout_form_7")));
		dropDownListCountry.selectByIndex(1);

	}
	
	public TransactionResultsPage clickButtonPurchase() {
		buttonPurchase.click();
		return new TransactionResultsPage(driver,this);
	}
	
	public void clickButtonGoBack() {
		buttonGoBack.click();
	}
	
	
}
