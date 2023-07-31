package Page;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PosPage {
    WebDriver driver;

    public PosPage(WebDriver driver) {
        this.driver = driver;
    }

    private By posPage = new By.ByCssSelector("a[href='https://pos-staging.wizcoders.com/sales/pos']");
    private By showText = new By.ByTagName("h1");
    private By dropdown = new By.ById("customer");
    private By name = new By.ByCssSelector("input[placeholder='Customer name']");
    private By phone = new By.ByCssSelector("input[placeholder='Customer phone']");
    private By checkInvoiceText = new By.ById("show_invoice");
    private By checkHideCustomer = new By.ById("hide_customer");
    private By checkHideLogo = new By.ById("hide_logo");
    private By addCart = new By.ByClassName("user-cart");
    private By billSummary = new By.ById("cash_recieved");
    private By selectFreight = new By.ById("courier");
    private By addFreight = new By.ById("freight_input");
    private By addFreightDesc = new By.ByCssSelector("textarea[placeholder='Enter Description']");
    private By submit = new By.ById("submit");
    private By selectProd = new By.ById("reponcsedata");

    private By showPrice = new By.ById("price");
    private By showStock = new By.ById("stock");
    private By search = new By.ById("search");
    private By getProdFromSearch = new By.ByXPath("//div[@id='reponcsedata']");
    private By selectVariant = new By.ByClassName("variant");
    private By cancel = new By.ByXPath("//span[normalize-space()='×']");
    private By noProductAlert = new By.ByCssSelector(".alert.alert-danger");
    private By noPrice = new By.ById("data_pos_35");
    private By product = new By.ById("data_pos_43");

    private By unit = new By.ByXPath("//select[@id='unit_id']");

    private By qty = new By.ByXPath("(//input[@name='quantity[]'])[1]");
    private By discount = new By.ByXPath("(//input[@name='discount[]'])[1]");
    private By cancelBtn = new By.ById("cancel");

    private By barcode = new By.ById("barcodeinput");
    private By cashAlert = new By.ByClassName("toast-body");




    public void PosPage() {
        driver.findElement(posPage).click();
    }

    public String getTitlePos() {
        return driver.findElement(showText).getText();
    }

    //--Show All DropDown Options
    public void showDropdownoptions() {
        Select select = new Select(driver.findElement(dropdown));
        List<WebElement> list = select.getOptions();
        for (WebElement we : list) {
            System.out.println(we.getText());
        }
    }
    //--Select One Option from dropdown
    public void selectOptions(int index) {
        Select select = new Select(driver.findElement(dropdown));
        select.selectByIndex(index);
    }
    //--Get the selected dropdown Option
    public String getSlectedOption() {
        Select select = new Select(driver.findElement(dropdown));
        return select.getFirstSelectedOption().getText();
    }
     //--Enter the Customer Name and Phone Number
    public void enterCustName(String custName) {
        driver.findElement(name).sendKeys(custName);
    }

    public void enterCustPhone(String custPhone) {
        driver.findElement(phone).sendKeys(custPhone);
    }
    public String getCustPhone(){
        return driver.findElement(phone).getAttribute("value");
    }

     //--Checked the Initials of the receipt
    public void CheckedReceiptInitials() {
        driver.findElement(checkInvoiceText).click();
        driver.findElement(checkHideCustomer).click();
        driver.findElement(checkHideLogo).click();
    }
    //---Show all products of the Pos that appears on the page
    public void showAllProducts() {

        WebElement selectDiv = driver.findElement(selectProd);
        List<WebElement> options = selectDiv.findElements(By.className("productdata"));
        for (WebElement option : options) {
            System.out.println(option.getText());

        }
    }
    //---Show Price of the Product
    public String getProductPrice() {
        return driver.findElement(showPrice).getText();
    }

    //---Show Stock of the Product
    public String getProductStock() {
        return driver.findElement(showStock).getText();
    }

    //---Show the cart details
    public String getCartProduct(){
        return driver.findElement(addCart).getText();
    }
    //---Get Bill Summary
    public String getbillSummary(){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.elementToBeClickable(billSummary)).click();
        return driver.findElement(billSummary).getText();
    }

    //---Select Freight from dropdown
    public void selectFreight(int index) {
        Select select = new Select(driver.findElement(selectFreight));
        select.selectByIndex(index);
    }
    //---Get Selected Freight form dropdown
    public String getSelectedFreight(){
        Select select = new Select(driver.findElement(selectFreight));
        return select.getFirstSelectedOption().getText();
    }
    //---Add the Freight amount
    public void addFreight (String num){
        driver.findElement(addFreight).clear();
        driver.findElement(addFreight).sendKeys(num);
    }
    //---get the freight amount that added
    public String getFreightPrice() {
        return driver.findElement(addFreight).getAttribute("value");
    }

    //---Enter the freight description
    public void addFreightDescription(String desc){
        driver.findElement(addFreightDesc).sendKeys(desc);
    }
    //---Get the freight description
    public String getFreightDescription(){
        return driver.findElement(addFreightDesc).getAttribute("value");
    }
    //---Click Save & Print Button
    public void clickSave(){
        driver.findElement(submit).click();
    }
    //---Enter the Price in Alerts
    public void enterPriceAlerts(int price){
        driver.switchTo().alert().sendKeys(String.valueOf(price));
    }
    //---Get the Entered price
    public String getEnteredPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
       return alert.getText();
    }

    public String getPriceAlert(){
        return driver.switchTo().alert().getText();
    }
    //---Click on Ok Button on Alerts
    public void okPriceAlerts(){
        driver.switchTo().alert().accept();
    }

    //--------------Search Product and Add to cart--------------------//

    public void searchAndAddToCart(String prodName) {
        By searchLocator = By.id("search");
        By productLocator = By.id("data_pos_36");

        // Enter the product name in the search box
        WebElement searchBox = driver.findElement(searchLocator);
        searchBox.sendKeys(prodName);
        searchBox.sendKeys(Keys.ENTER);

        // Wait for the product to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

        // Click on the product
        WebElement product = driver.findElement(productLocator);
        product.click();

    }
    //Get the Selected product from search
    public String getSelectedSearchProduct() {
        return driver.findElement(getProdFromSearch).getText();
    }
    public String getSelectedProduct(){
        return driver.findElement(selectProd).getText();
    }

    //--- Select Products With Variants ---//
    public void selectProductsWithVariant() {

        WebElement selectDiv = driver.findElement(selectProd);
        List<WebElement> options = selectDiv.findElements(By.className("productdata"));
        int count = 0;
        for (WebElement option : options) {
            if (count <= 3) {
                System.out.println(option.getText());
                option.click();
                WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(selectVariant));
                wait.click();
                driver.findElement(cancel).click();
                count++;
            } else {
                break; // Exit the loop if the count exceeds 3
            }
           System.out.println("Product selected with variant: " + getSelectedVariant());
        }
    }
    public String getSelectedVariant() {
        return driver.findElement(selectVariant).getText();
    }

    public void cancelVariantPage() {
        driver.findElement(cancel).click();
    }


    //--- Select Products Without Variants ---//
    public void selectProdWithoutVariant() {
        WebElement selectDiv = driver.findElement(selectProd);
        List<WebElement> options = selectDiv.findElements(By.className("productdata"));
        int startIndex = 8;
        int endIndex = 19;

        for (int i = startIndex; i <= endIndex && i < options.size(); i++) {
            WebElement option = options.get(i);
            //System.out.println(options.size());
            option.getText();
            option.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(selectProd)).click();
            System.out.println("Product selected: " + option.getText());
        }
    }

    //---Product is not selected---//
    public String productNotSelected(){
      return driver.findElement(noProductAlert).getText();
    }

    //---Product without price is not selected---//
    public void productWithNoPrice() {
        driver.findElement(noPrice).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public String getProductWithNoPrice(){
        return driver.findElement(noPrice).getText();
    }
    public void selectUnit(int index){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.elementToBeClickable(product)).click();
        System.out.println("You selected a product: " + driver.findElement(product).getText());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(unit));
        Select select = new Select(driver.findElement(unit));
        select.selectByIndex(index);
    }
    public String getUnit(){
        Select select = new Select(driver.findElement(unit));
        return select.getFirstSelectedOption().getText();
    }
    public void clearDisc(){
        driver.findElement(discount).clear();
    }
    public void selectQty(String quantity){
        driver.findElement(qty).clear();
        driver.findElement(qty).sendKeys(quantity);
    }
    public String getQty(){
        return driver.findElement(qty).getAttribute("value");
    }
    public void enterDiscount(String dis){
        driver.findElement(discount).sendKeys(dis);
    }
    public String getDiscount(){
        return driver.findElement(discount).getAttribute("value");
    }
    public void clickCancelBtn(){
        driver.findElement(cancelBtn).click();
        driver.switchTo().alert().accept();
    }
    public void selectProdWithBarcode(String bar){
        WebElement barcodeField = driver.findElement(barcode);
        barcodeField.sendKeys(bar);
        barcodeField.sendKeys(Keys.ENTER);
    }
    public String showCashAlert(){
       return driver.findElement(cashAlert).getText();
    }

    public void screenShot() throws IOException {
        // Capture screenshot and Save in a file
        Screenshot s=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(s.getImage(),"PNG",new File("C://Users//Aymen Liaquat//Desktop//Automation_Receipt//s11.png"));

    }





}
