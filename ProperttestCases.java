package TestCases;

import Page_Object.BuyPage;
import Page_Object.HomePage;
import Page_Object.Property_page;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProperttestCases extends BaseTest {

    // TC_99A_003 - Validate filters applied and first property opens
    @Test(priority = 1)
    public void validateFilterAndOpenProperty() {
        HomePage homePage = new HomePage(driver);
        homePage.enterCity("Mumbai");
        homePage.selectSuggestion();
        homePage.clickSearch();

        BuyPage buyPage = new BuyPage(driver);
        buyPage.applyPropertyTypeFilter();
        buyPage.applyBhkFilter();

        buyPage.switchToNewTab();

        Assert.assertTrue(driver.getCurrentUrl().contains("99acres"),
                "Property detail page did not load. URL: " + driver.getCurrentUrl());
    }

    // TC_99A_004 - Capture from card then match with detail page
    @Test(priority = 2)
    public void validateCardAndDetailPageMatch() {
        // Step 1 - Search
        HomePage homePage = new HomePage(driver);
        homePage.enterCity("Mumbai");
        homePage.selectSuggestion();
        homePage.clickSearch();

        // Step 2 - Apply filters
        BuyPage buyPage = new BuyPage(driver);
        buyPage.applyPropertyTypeFilter();
        buyPage.applyBhkFilter();

        // Step 3 - Capture from first card BEFORE clicking
        String titleFromCard    = buyPage.getFirstCardTitle();
        String priceFromCard    = buyPage.getFirstCardPrice();
        String locationFromCard = buyPage.getFirstCardLocation();

        System.out.println("=== FROM CARD ===");
        System.out.println("Title    : " + titleFromCard);
        System.out.println("Price    : " + priceFromCard);
        System.out.println("Location : " + locationFromCard);

        // Step 4 - Click first property and switch tab

        buyPage.switchToNewTab();

        // Step 5 - Capture from detail page
        Property_page propertyPage = new Property_page(driver);
        String titleFromDetail    = propertyPage.getTitle();
        String priceFromDetail    = propertyPage.getPrice();
        String locationFromDetail = propertyPage.getLocation();

        System.out.println("=== FROM DETAIL PAGE ===");
        System.out.println("Title    : " + titleFromDetail);
        System.out.println("Price    : " + priceFromDetail);
        System.out.println("Location : " + locationFromDetail);

        // Step 6 - Assert card matches detail page
        Assert.assertEquals(titleFromDetail, titleFromCard,
                "Title mismatch! Card: " + titleFromCard + " | Detail: " + titleFromDetail);
        Assert.assertEquals(priceFromDetail, priceFromCard,
                "Price mismatch! Card: " + priceFromCard + " | Detail: " + priceFromDetail);
        Assert.assertEquals(locationFromDetail, locationFromCard,
                "Location mismatch! Card: " + locationFromCard + " | Detail: " + locationFromDetail);
    }
}