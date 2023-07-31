package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    private By LoginPageLink = new By.ByCssSelector("img[alt='Sales staging']");
    private By clickSales =  new By.ByXPath("(//a[@class='nav-link'])[17]");
    private By clickReports = new By.ByLinkText("Reports");



   public LoginPage GoToLoginPage() {
       driver.findElement(LoginPageLink).click();
       return new LoginPage(driver);
   }
    public PosPage GoToPosPage() {
        driver.findElement(clickSales).click();
        return new PosPage(driver);
    }
    public PartyBalancePage GoToPartyBalancePage(){
       driver.findElement(clickReports).click();
       return new PartyBalancePage(driver);
    }




}
