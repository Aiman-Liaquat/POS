package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    private By emailTextBox = new By.ById("email");
    private By passwordTextbox= new By.ById("password");
    private By logInBtn = new By.ByCssSelector("button[type='submit']");
    private By messageAfterLogin = new By.ByLinkText("Dashboard");
    private By logoutBtn = new By.ByXPath("//p[normalize-space()='Logout']");
    private By alertMessage = new By.ByCssSelector("span[role='alert'] strong");

    public void enterUserName(String text){
        driver.findElement(emailTextBox).sendKeys(text);
    }
    public void enterPassword(String text){
        driver.findElement(passwordTextbox).sendKeys(text);
    }
    public void clickLoginBtn(){
        driver.findElement(logInBtn).click();
    }
    public String getMessageAfterLogin(){
        return driver.findElement(messageAfterLogin).getText();

    }
    public void clickLogoutBtn(){
        driver.findElement(logoutBtn).click();
    }
    public String getAlertMessage(){
        return driver.findElement(alertMessage).getText();
    }








}
