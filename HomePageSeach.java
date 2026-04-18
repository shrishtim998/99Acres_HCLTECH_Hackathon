package TestCases;

import Page_Object.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageSeach extends BaseTest {


   @Test()
    public void validateHomePageLoads() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("99acres"),
                "Home page did not load. Title was: " + title);
    }


    // TC_99A_002 - Validate Buy option selection and city search with auto-suggestion
    @Test()
    public void validateBuyOptionAndCitySearch() {
        HomePage homePage = new HomePage(driver);
        String city = "Delhi";
        //homePage.clickBuyoptn();

        homePage.enterCity(city);
        homePage.selectSuggestion();
        homePage.clickSearch();
       // homePage.FirstSuggestion();
/**
        String searchbarText = driver.findElement(
                By.xpath("//div[@id='d_landmark_inPageSearchBox']")
        ).getText();
        Assert.assertTrue(searchbarText.contains("Mumbai"),
                "Location was not filled correctly. Found: " + searchbarText);
    }
}
*/
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectedCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-label='CATEGORY']//span[contains(text(),'" + city + "')]")
        ));

        Assert.assertTrue(selectedCity.isDisplayed(),
                "City suggestion was not selected correctly. Expected: " + city);
    }
}