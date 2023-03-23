package org.automation.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        formScenario(driver);
        waitFor(3000,driver);
        frameScenario(driver);
        waitFor(3000,driver);
        datePickerScenario(driver);
        waitFor(3000, driver);
        sliderScenario(driver);
        waitFor(3000,driver);
        driver.quit();
    }




    //Funções de auxilio ----------------------------------------------------------------------

    /*
    * Espera a pagina carregar
    @param driver WebDriver
     */
    static void waitPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State : "
                        + ((JavascriptExecutor) driver).executeScript("return document.readyState"));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    /*
    * Espera por time milesimos de segundo
    @param time milesimos de segundo a esperar.
    @param driver WebDriver
     */
    static void waitFor(int time, WebDriver driver) {
        new Actions(driver).pause(time).perform();
    }




    // Cenarios -----------------------------------------------------------------------


    /*
    * Pagina slider
    @param driver WebDriver
     */

    static void sliderScenario(WebDriver driver) {
        driver.get("https://demo.automationtesting.in/Slider.html");
        waitPageLoad(driver);
        WebElement slider = driver.findElement(By.id("slider")).findElement(By.tagName("a"));
        for (int i = 0; i != 50; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            new Actions(driver).pause(20).perform();
        }
    }


    /*
    * Pagina frame
    @param driver WebDriver
     */
    static void frameScenario(WebDriver driver) {
        driver.get("https://demo.automationtesting.in/Frames.html");
        waitPageLoad(driver);

        WebElement iframe = driver.findElement(By.cssSelector("#Single>iframe"));

        driver.switchTo().frame(iframe);

        driver.findElement(By.tagName("input")).sendKeys("Palmeiras não tem mundial");
    }


    /*
    * Pagina datapicker
    @param driver WebDriver
     */
    static void datePickerScenario(WebDriver driver) {
        driver.get("https://demo.automationtesting.in/Datepicker.html");
        waitPageLoad(driver);


       WebElement datePicker2 = driver.findElement(By.id("datepicker2"));
       datePicker2.sendKeys("08/15/1999");



        WebElement datePicker1 = driver.findElement(By.id("datepicker1"));
        datePicker1.click();
        WebElement prev = driver.findElement(By.cssSelector("a[data-handler=prev]"));
        WebElement monthText = driver.findElement(By.className("ui-datepicker-month"));
        WebElement yearText = driver.findElement(By.className("ui-datepicker-year"));
        waitFor(200, driver);
        while (!monthText.getText().equals("August") || !yearText.getText().equals("1999")) {
            prev.click();
            monthText = driver.findElement(By.className("ui-datepicker-month"));
            yearText = driver.findElement(By.className("ui-datepicker-year"));
            prev = driver.findElement(By.cssSelector("a[data-handler=prev]"));


        }

        driver.findElement(By.className("ui-datepicker-calendar")).findElement(By.linkText("15")).click();

    }


    /*
    * Pagina register
    @param driver WebDriver
     */
    static void formScenario(WebDriver driver) {
        driver.get("https://demo.automationtesting.in/Register.html");
        waitPageLoad(driver);

        //Inputs
        WebElement firstName = driver.findElement(By.cssSelector("input[ng-model=FirstName]"));
        WebElement lastName = driver.findElement(By.cssSelector("input[ng-model=LastName]"));
        WebElement address = driver.findElement(By.cssSelector("textarea[ng-model=Adress]"));
        WebElement email = driver.findElement(By.cssSelector("input[ng-model=EmailAdress]"));
        WebElement gender = driver.findElement(By.cssSelector("input[value=Male]"));
        WebElement phone = driver.findElement(By.cssSelector("input[ng-model=Phone]"));
        WebElement password = driver.findElement(By.id("firstpassword"));
        WebElement confirmPassword = driver.findElement(By.id("secondpassword"));


        //Selects
        WebElement selectSkillsElement = driver.findElement(By.id("Skills"));
        Select selectSkills = new Select(selectSkillsElement);

        WebElement selectCountryElement = driver.findElement(By.id("countries"));
        Select selectCountry = new Select(selectCountryElement);

        WebElement selectYearElement = driver.findElement(By.cssSelector("select[ng-model=yearbox]"));
        Select selectYear = new Select(selectYearElement);

        WebElement selectMonthElement = driver.findElement(By.cssSelector("select[ng-model=monthbox]"));
        Select selectMonth = new Select(selectMonthElement);

        WebElement selectDayElement = driver.findElement(By.cssSelector("select[ng-model=daybox]"));
        Select selectDay = new Select(selectDayElement);

        WebElement selectCountryContained = driver.findElement(By.className("selection"));
        WebElement language = driver.findElement(By.id("msdd"));

        //Campos de texto
        firstName.sendKeys("Raphael");
        waitFor(100, driver);
        lastName.sendKeys("Oliveira Gonçalves");
        waitFor(100, driver);
        address.sendKeys("Rua das covinhas, Praia Grande, Brasil, São Paulo, 197");
        waitFor(100, driver);
        email.sendKeys("Email@gmail.com");
        waitFor(100, driver);
        phone.sendKeys("1234567890");
        waitFor(100, driver);

        //Options e Checkboxes
        gender.click();
        waitFor(100, driver);
        driver.findElement(By.id("checkbox1")).click();
        waitFor(100, driver);
        driver.findElement(By.id("checkbox2")).click();
        waitFor(100, driver);
        driver.findElement(By.id("checkbox3")).click();
        waitFor(100, driver);


        //Opções de linguagem
        language.click();
        waitFor(100, driver);
        List<WebElement> languageOptions = driver.findElements(By.cssSelector("li[list-select]"));

        for (int i = 0; i < languageOptions.size(); i++) {
            WebElement option = languageOptions.get(i);
            try {
                if (option.findElement(By.linkText("Portuguese")) != null) {
                    option.click();
                    break;
                }
            } catch (Exception e) {

            }

        }


        waitFor(100, driver);


        //Selects
        selectSkills.selectByVisibleText("C++");
        waitFor(100, driver);

        //Selecionar país (Não utiliza a tag select )
        selectCountryContained.click();
        waitFor(100, driver);
        List<WebElement> countryContainerOptions = driver.findElement(By.className("select2-results")).findElements(By.tagName("li"));

        for (int i = 0; i < countryContainerOptions.size(); i++) {
            WebElement option = countryContainerOptions.get(i);
            if (option.getText().equals("Australia")) {
                option.click();
                break;
            }
        }
        waitFor(100, driver);


        //Selecionar país (Utiliza a tag select, mas não possui opções)
        selectCountry.selectByVisibleText("Select Country");
        waitFor(100, driver);

        //Data de nascimento
        selectYear.selectByVisibleText("1999");
        waitFor(100, driver);
        selectMonth.selectByVisibleText("August");
        waitFor(100, driver);
        selectDay.selectByVisibleText("15");
        waitFor(100, driver);


        //Senha
        password.sendKeys("Abc1234");
        waitFor(100, driver);
        confirmPassword.sendKeys("Abc1234");
        waitFor(100, driver);
        driver.findElement(By.id("submitbtn")).click();

    }
}