package testsPos;

import Base.BaseTest;
import org.testng.annotations.Test;

public class LoginTestWithValidInputs extends BaseTest {
    @Test

    public void verifyLogin() {
        var loginPage = homePage.GoToLoginPage();
        loginPage.enterUserName("test@passu.tech");
        loginPage.enterPassword("Quality@123");
        //Assert.assertEquals(loginPage.enterUserName(), "qa@wizcoders.com", "qa@wizcoders.com");
        loginPage.clickLoginBtn();
      //loginPage.getAlertMessage();
     //System.out.println(loginPage.getAlertMessage());
        loginPage.getMessageAfterLogin();
      System.out.println(loginPage.getMessageAfterLogin());
        //loginPage.clickLogoutBtn();
    }


}
