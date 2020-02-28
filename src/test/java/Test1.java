import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
    ChromeDriver driver;
    WebDriverWait wait;

    @Before
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mail.yandex.com/?lang=ru");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Яндекс.Почта — бесплатная и надежная электронная почта"));
        wait = new WebDriverWait(driver, 50);
        driver.findElement(By.xpath("//a[@class='button2 button2_size_mail-big button2_theme_mail-white button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow']")).click();
        driver.findElement(By.xpath("//input[@id=\"passp-field-login\"]")).sendKeys("djeeeelik@yandex.ru");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("2601425Djelmc");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nb-3']")));


        //driver.quit();
    }

    @Test
    public void test() {
        clickSettingsButton();
        openLanguageList();
        switchOverLanguageEnglish();
    }

    public void clickSettingsButton() {
        driver.findElement(By.xpath("//a[@id='nb-3']")).click();
        driver.findElement(By.xpath("//span[@class='settings-popup-title-content']")).click();

    }

    public void openLanguageList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")));
        driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).click();
    }

    public void switchOverLanguageEnglish() {
        driver.findElement(By.xpath("//a[contains(text(),'English')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'English')]")));


    }
}