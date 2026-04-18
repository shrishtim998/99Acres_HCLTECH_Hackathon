package Page_Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Property_page {

    WebDriver driver;
    WebDriverWait wait;

    public Property_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='banner__projectName']")
    WebElement propertyTitle;

    @FindBy(xpath = "//div[@class='banner__sizePropType']")
    WebElement propertyPrice;

    @FindBy(xpath = "//div[@class='banner__projectLocation']//span")
    WebElement propertyLocation;

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(propertyTitle));
        return propertyTitle.getText().trim();
    }

    public String getPrice() {
        wait.until(ExpectedConditions.visibilityOf(propertyPrice));
        return propertyPrice.getText().trim();
    }

    public String getLocation() {
        wait.until(ExpectedConditions.visibilityOf(propertyLocation));
        return propertyLocation.getText().trim();
    }
}