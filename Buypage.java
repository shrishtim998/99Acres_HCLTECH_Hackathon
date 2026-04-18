package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BuyPage {

    WebDriver driver;
    WebDriverWait wait;

    public BuyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void applyPropertyTypeFilter() {
        WebElement propertyType = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Residential Apartment']")
        ));
        propertyType.click();
        System.out.println("[INFO] Residential Apartment filter applied");
    }

    public void applyBhkFilter() {
        WebElement bhk = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='2 BHK']")
        ));
        bhk.click();
        System.out.println("[INFO] 2 BHK filter applied");
    }

    public void waitForResultsToLoad() {
        // Wait for skeleton to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class,'skeleton') or contains(@class,'Skeleton')]")
        ));
        // Wait for actual cards to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='tupleNew__contentWrap']")
        ));
        System.out.println("[INFO] Results loaded successfully");
    }

    public String getFirstCardTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='tupleNew__contentWrap']//div[contains(@class,'projectName')])[1]")
        ));
        String text = title.getText().trim();
        System.out.println("[INFO] Card Title: " + text);
        return text;
    }

    public String getFirstCardPrice() {
        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='tupleNew__priceAreaWrap'])[1]")
        ));
        String text = price.getText().trim();
        System.out.println("[INFO] Card Price: " + text);
        return text;
    }

    public String getFirstCardLocation() {
        WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='tupleNew__contentWrap']//div[contains(@class,'localityName')])[1]")
        ));
        String text = location.getText().trim();
        System.out.println("[INFO] Card Location: " + text);
        return text;
    }

    public void openFirstProperty(String cardTitle) {
        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='tupleNew__contentWrap']//*[contains(text(),'" + cardTitle + "')]")
        ));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", card);
        System.out.println("[INFO] Clicked property: " + cardTitle);
    }

    public void switchToNewTab() {
        String parentWindow = driver.getWindowHandle();
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        System.out.println("[INFO] Switched to new tab");
    }
}