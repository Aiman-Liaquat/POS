package Base;

import Page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


import java.time.Duration;

public class BaseTest {
       public static WebDriver driver;
       protected static HomePage homePage;

       @BeforeClass

       public static void setUp(){
              System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");



           ChromeOptions options = new ChromeOptions();
           options.addArguments("--remote-allow-origins=*");
           options.addArguments("--ignore-certificate-errors");
           DesiredCapabilities cp = new DesiredCapabilities();
           cp.setCapability(ChromeOptions.CAPABILITY, options);
           options.merge(cp);
           driver = new ChromeDriver(options);
           driver.manage().window().maximize();


              //driver.manage().window().setSize(new Dimension(315, 812));
             driver.get("https://pos-staging.wizcoders.com/login");


              System.out.println(driver.getTitle());
              homePage = new HomePage(driver);
       }

       @AfterClass

       public void tearDown(){
              //driver.quit();
       }

}
