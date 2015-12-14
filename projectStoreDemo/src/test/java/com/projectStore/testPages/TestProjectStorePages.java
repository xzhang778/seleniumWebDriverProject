package com.projectStore.testPages;



import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.projectStore.pages.CheckoutPage;
import com.projectStore.pages.CheckoutPurchasePage;
import com.projectStore.pages.HomePage;
import com.projectStore.pages.ProductCategoryAccessoriesPage;
import com.projectStore.pages.TransactionResultsPage;

public class TestProjectStorePages {

	WebDriver driver;
	HomePage homePage;
	ProductCategoryAccessoriesPage accessoriesPage;
	CheckoutPage checkoutPage;
	CheckoutPurchasePage checkoutPurchasePage;
	TransactionResultsPage transactionResultsPage;
	Logger logger;
	public static final String productName = "Magic Mouse";
	public static final String productPrice = "$150.00";
	public static final String productQuantity= "1";
	public static final String totalProductPrice= "$150.00";
	public static final String confirmMessage = "You just added \""+ productName+ "\" to your cart.";
	public static final String finalResult = "Total Shipping: $10.00\nTotal: $160.00";

	@BeforeClass
	public void Setup() {
		logger = LogManager.getLogger(TestProjectStorePages.class);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
 
	/*TestPlan ProjectStoreDemoQA --> Test Case Id 1*/
	@Test 
	public void testOpenDemoQAHomePage() {
		logger.info("start test case testOpenDemoQAHomePage");
		homePage = new HomePage(driver);
		homePage.get();
		logger.info("open the URL "+homePage.getCurrentUrl());
		Assert.assertEquals(homePage.getPageTitle(),"ONLINE STORE | Toolsqa Dummy Test site");
		Assert.assertEquals(homePage.getCurrentUrl(),"http://store.demoqa.com/");
		logger.info("end test case testOpenDemoQAHomePage");
	}
    
	/*TestPlan ProjectStoreDemoQA --> Test Case Id 2*/
	@Test(dependsOnMethods={"testOpenDemoQAHomePage"})
	public void testMenuItemAccessories() {
		logger.info("start test case testMenuItemAccessories");
		homePage.hoverOverMenuProductCategory();
		logger.info("click menu item accessories");
		accessoriesPage = homePage.clickMenuItemAccessories();
		logger.info("The current page url is: "+accessoriesPage.getCurrentUrl());
		Assert.assertEquals(accessoriesPage.getPageTitle(),"Accessories | ONLINE STORE");
		Assert.assertEquals(accessoriesPage.getCurrentUrl(),"http://store.demoqa.com/products-page/product-category/accessories/");
		logger.info("end test case testOpenDemoQAHomePage");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 3*/
	@Test(dependsOnMethods={"testMenuItemAccessories"})
	public void testButtonAddToCart() {
		logger.info("start test case testButtonAddToCart");
		logger.info("click button to add the item to the Cart");
		accessoriesPage.clickButtonAddMagicToCart();
		logger.info("The confirm message on the popup windows: "+accessoriesPage.getConfirmCheckoutMessage());
		Assert.assertEquals(accessoriesPage.getConfirmCheckoutMessage(),confirmMessage);
		logger.info("end test case testButtonAddToCart");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 4*/
	@Test(dependsOnMethods={"testButtonAddToCart"})
	public void testButtonCheckOut() {
		logger.info("start test case testButtonCheckOut");
		logger.info("click button check out");
		checkoutPage = accessoriesPage.clickButtonGoToCheckout();
		Assert.assertEquals(checkoutPage.getPageTitle(),"Checkout | ONLINE STORE");
		logger.info("The current page url is: "+checkoutPage.getCurrentUrl());
		Assert.assertEquals(checkoutPage.getCurrentUrl(),"http://store.demoqa.com/products-page/checkout/");
		logger.info("end test case testButtonCheckOut");
	}
 
	/*TestPlan ProjectStoreDemoQA --> Test Case Id 5*/
	@Test(dependsOnMethods={"testButtonAddToCart"})
	public void testCheckOutPageQuantity() {
		logger.info("start test case testCheckOutPageQuantity");
		logger.info("The product quantity on checkout page is: "+checkoutPage.getTxtBoxQuantity());
		Assert.assertEquals(checkoutPage.getTxtBoxQuantity(),productQuantity);
		logger.info("end test case testCheckOutPageQuantity");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 6*/
	@Test(dependsOnMethods={"testButtonAddToCart"})
	public void testCheckOutPageProductPrice() {
		logger.info("start test case testCheckOutPageProductPrice");
		logger.info("The product price on checkout page is: "+checkoutPage.getPrice());
		Assert.assertEquals(checkoutPage.getPrice(), productPrice);
		logger.info("end test case testCheckOutPageProductPrice");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 7*/
	@Test(dependsOnMethods={"testButtonAddToCart"})
	public void testCheckOutPageTotalProductPrice() {
		logger.info("start test case testCheckOutPageTotalProductPrice");
		logger.info("The total product price on checkout page is: "+checkoutPage.getTotal());
		Assert.assertEquals(checkoutPage.getTotal(), totalProductPrice);
		logger.info("end test case testCheckOutPageTotalProductPrice");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 8*/
	@Test(dependsOnMethods={"testButtonCheckOut"})
	public void testButtonContinue() {
		logger.info("start test case testButtonContinue");
		logger.info("click button continue");
		checkoutPurchasePage = checkoutPage.clickButtonContinue();
		logger.info("The current page url is: "+checkoutPage.getCurrentUrl());
		Assert.assertEquals(checkoutPage.getPageTitle(),"Checkout | ONLINE STORE");
		Assert.assertEquals(checkoutPage.getCurrentUrl(),"http://store.demoqa.com/products-page/checkout/");
		checkoutPurchasePage.enterAddress("1000 Airport Road");
		checkoutPurchasePage.enterCity("Toronto");
		checkoutPurchasePage.enterEmailAddress("testing@hotmail.com");
		checkoutPurchasePage.enterFirstName("testFirstName");
		checkoutPurchasePage.enterLastName("testLastName");
		checkoutPurchasePage.enterPhoneNumber("4168046022");
		checkoutPurchasePage.enterProvince("ontario");
		checkoutPurchasePage.selectCountry("Canada");
		logger.info("end test case testButtonContinue");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 9*/
	@Test(dependsOnMethods={"testButtonCheckOut"})
	public void testButtonPurchase() {
		logger.info("start test case testButtonPurchase");
		logger.info("click button purchase");
		transactionResultsPage = checkoutPurchasePage.clickButtonPurchase();
		logger.info("end test case testButtonPurchase");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 10*/
	@Test(dependsOnMethods={"testButtonPurchase"})
	public void testResultPageProductName() {
		logger.info("start test case testResultPageProductName");
		logger.info("The product name on final transaction page is: "+transactionResultsPage.getColumnName(1,0));
		Assert.assertEquals(transactionResultsPage.getColumnName(1,0),productName);
		logger.info("end test case testResultPageProductName");
	}
    
	/*TestPlan ProjectStoreDemoQA --> Test Case Id 11*/
	@Test(dependsOnMethods={"testButtonPurchase"})
	public void testResultPageProductPrice() {
		logger.info("start test case testResultPageProductPrice");
		logger.info("The product price on final transaction page is: "+transactionResultsPage.getColumnName(1,1));
		Assert.assertEquals(transactionResultsPage.getColumnName(1,1),productPrice);
		logger.info("end test case testResultPageProductPrice");
	}
	
	/*TestPlan ProjectStoreDemoQA --> Test Case Id 12*/
	@Test(dependsOnMethods={"testButtonPurchase"})
	public void testResultPageProductQuantity() {
		logger.info("start test case testResultPageProductQuantity");
		logger.info("The product quantity on final transaction page is: "+transactionResultsPage.getColumnName(1,2));
		Assert.assertEquals(transactionResultsPage.getColumnName(1,2),productQuantity);
		logger.info("end test case testResultPageProductQuantity");
	}
  
	/*TestPlan ProjectStoreDemoQA --> Test Case Id 13*/
	@Test(dependsOnMethods={"testButtonPurchase"})
	public void testResultPageProductTotalPrice() {
		logger.info("start test case testResultPageProductTotalPrice");
		logger.info("The total product price on final transaction page is: "+transactionResultsPage.getColumnName(1,3));
		Assert.assertEquals(transactionResultsPage.getColumnName(1,3),totalProductPrice);
		logger.info("end test case testResultPageProductTotalPrice");
	}

	/*TestPlan ProjectStoreDemoQA --> Test Case Id 14*/
	@Test(dependsOnMethods={"testButtonPurchase"})
	public void testResultPageProductFinalCost() {
		logger.info("start test case testResultPageProductFinalCost");
		logger.info("The final result on final transaction page is: "+transactionResultsPage.getTotalResultLabel());
		Assert.assertEquals(transactionResultsPage.getTotalResultLabel(),finalResult);
		logger.info("end test case testResultPageProductFinalCost");
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
