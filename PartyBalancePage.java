package Page;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PartyBalancePage {
    WebDriver driver;
    public PartyBalancePage(WebDriver driver) {
        this.driver = driver;
    }
    private By clickPB = new By.ByCssSelector("a[href='https://pos-staging.wizcoders.com/report/customer']");
    private By getPBText = new By.ByTagName("h1");
    private By selectEntries = new By.ByCssSelector("select[name='printTable_length']");
    private By getOpeningBlnValue = new By.ByXPath("//*[@id=\"printTable\"]/tbody/tr[1]/td[3]");
    private By getClosingBlnValue = new By.ByXPath("//*[@id=\"printTable\"]/tbody/tr[1]/td[11]");
    private By getContact = new By.ByXPath("//*[@id=\"printTable\"]/tbody/tr[1]/td[1]");
    private By partySales = new By.ByXPath("/html/body/div[2]/aside[1]/div/nav/ul/li[13]/ul/li[4]/a/p");
    private By searchContact = new By.ByCssSelector("input[role='searchbox']");
    private By clickDd = new By.ByCssSelector("#select2-customer_id-container");
    private By enterDateRange = new By.ById("daterange");
    private By submit = new By.ByCssSelector("button[type='submit']");

    public void clickPartyBalance(){
        driver.findElement(clickPB).click();
    }
    public String getPartyBalanceText(){
        return driver.findElement(getPBText).getText();
    }
    public void selectEntriesFromOptions(String value){
        Select select = new Select(driver.findElement(selectEntries));
        select.selectByValue(value);
    }
    public void clickPartySales(){
        driver.findElement(partySales).click();
    }
    public void enterPartyContact(){
        driver.findElement(clickDd).click();
        driver.findElement(searchContact).sendKeys("a", Keys.ENTER);
    }
    public void enterDate(){
        driver.findElement(enterDateRange).clear();
        driver.findElement(enterDateRange).sendKeys("01/06/2020-30/06/2023", Keys.ENTER);

    }
    public void clickSubmitBtn(){
        driver.findElement(submit).click();
    }
    public void back(){
        driver.navigate().back();
    }


    public void displayContactOpeningClosingBalances() {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='printTable']/tbody/tr"));

        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.tagName("td"));

            String contactName = columns.get(0).getText();
            String openingBalance = columns.get(2).getText();

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.display='block';", columns.get(10));
            String closingBalance = columns.get(10).getText();

            System.out.println("Row " + (i+1) + " - Contact: " + contactName + ", Opening Balance: " + openingBalance + ", Closing Balance: " + closingBalance);
        }
    }
    public void comparePartyBlnAndPartySales(){
        // Navigate to the party balance page
        clickPartyBalance();

        // Retrieve the party balance data
        List<WebElement> balanceRows = driver.findElements(By.xpath("//table[@id='printTable']/tbody/tr"));

        // Iterate through the party balance rows
        for (int i = 0; i < balanceRows.size(); i++) {
            WebElement balanceRow = balanceRows.get(i);
            List<WebElement> balanceColumns = balanceRow.findElements(By.tagName("td"));

            String contactName = balanceColumns.get(0).getText();
            String openingBalance = balanceColumns.get(2).getText();
            String closingBalance = balanceColumns.get(10).getText();

            //System.out.println("Take: "+ contactName);

            // Navigate to the party sales page
            back();
            clickPartySales();

            // Locate the dropdown field on the party sales form and paste the contact name
            enterPartyContact();
            enterDate();
           /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdownField = wait.until(ExpectedConditions.elementToBeClickable(clickDd));
            dropdownField.sendKeys(contactName, Keys.ENTER); */

            // Enter the date in the date field

          //  WebElement dateField = driver.findElement(enterDateRange);
          //  dateField.sendKeys("01/06/2020 - 30/06/2023", Keys.ENTER);

            // Click the submit button
            WebElement submitButton = driver.findElement(submit);
            submitButton.click();

            // Wait for the table to appear (if needed)
            // ...

            // Retrieve the opening balance and closing balance from the table
            List<WebElement> salesRows = driver.findElements(By.xpath("//table[@id='partysales']/tbody/tr"));
            if (salesRows.size() > 0) {
                WebElement salesRow = salesRows.get(0);
                List<WebElement> salesColumns = salesRow.findElements(By.tagName("td"));

                String salesOpeningBalance = salesColumns.get(1).getText();
                String salesClosingBalance = salesColumns.get(2).getText();

                // Compare opening balance and closing balance to party balance
                if (!openingBalance.equals(salesOpeningBalance) || !closingBalance.equals(salesClosingBalance)) {
                    System.out.println("Row " + (i+1) + " - Contact: " + contactName + ", Opening Balance (Party): " + openingBalance
                            + ", Opening Balance (Sales): " + salesOpeningBalance + ", Closing Balance (Party): " + closingBalance
                            + ", Closing Balance (Sales): " + salesClosingBalance);
                }
            }
        }

    }
}




