package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends BasePage {
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[contains(.,'Zarządzanie projektami - firma')]")
    private WebElement projectsHeader;

    @FindBy(xpath = "//span[@class='-totalPages']")
    private WebElement totalPagesCount;



}
