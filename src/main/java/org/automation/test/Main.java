package org.automation.test;
import jdk.jfr.Timespan;
import org.openqa.selenium.*;
import  org.openqa.selenium.chrome.ChromeDriver;
import  io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.swing.*;
import java.sql.Time;
import java.time.Duration;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        sliderScenario(driver);
        formScenario(driver);

        driver.quit();
    }

    static void waitPageLoad(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }
    static void sliderScenario(WebDriver driver){
        driver.get("https://demo.automationtesting.in/Slider.html");
        waitPageLoad(driver);
        WebElement slider = driver.findElement(By.id("slider")).findElement(By.tagName("a"));
        for (int i = 0; i != 50; i++){
            slider.sendKeys(Keys.ARROW_RIGHT);
            new Actions(driver).pause(20).perform();
        }
        new Actions(driver).pause(3000);
    }
    static void formScenario(WebDriver driver){
        driver.get("https://demo.automationtesting.in/Register.html");
        waitPageLoad(driver);
        WebElement firstName =  driver.findElement(By.cssSelector("input[ng-model=FirstName]"));
        WebElement lastName =  driver.findElement(By.cssSelector("input[ng-model=LastName]"));
        WebElement address =  driver.findElement(By.cssSelector("textarea[ng-model=Adress]"));

        firstName.sendKeys("Raphael");
        new Actions(driver).pause(150).perform();
        lastName.sendKeys("Oliveira Gonçalves");
        new Actions(driver).pause(150).perform();
        address.sendKeys("Rua das covinhas, Praia Grande, Brasil, São Paulo, 197");
        new Actions(driver).pause(5000).perform();
        new Actions(driver).pause(3000);
    }
}