package Page_Object;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    // Handle popup (cookie / login)++
    public void handleOverlay() {
        try {
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".close"))
            );
            closeBtn.click();
        } catch (Exception e) {
            System.out.println("No popup displayed");
        }
    }

    // Enter city
    public void enterCity(String city) {

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("keyword2"))
        );

        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(city);
    }

    // Handle auto-suggestion properly
    public void selectFromAutoSuggestion(String expectedCity) {

        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//[contains(@class,'suggest')]//[self::li or self::div]")
                )
        );

        for (WebElement option : suggestions) {
            if (option.getText().toLowerCase().contains(expectedCity.toLowerCase())) {
                option.click();
                return;
            }
        }

        throw new RuntimeException("City not found in suggestions");
    }


    public void selectSuggestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for suggestion list to appear
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                (By.xpath("//li[@id='0']"))
        ));

        firstSuggestion.click();
    }

    // Click search button
    public void clickSearch() {

        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        (By.xpath("//div[@class='inPageSearchBox__searchBtn']"))
                )
        );

        searchBtn.click();
    }
}