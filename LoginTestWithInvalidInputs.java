package testsPos;

import Base.BaseTest;
import org.testng.annotations.Test;

public class LoginTestWithInvalidInputs extends BaseTest {
    @Test

    public void verifyLogin() {
        var loginPage = homePage.GoToLoginPage();
        loginPage.enterUserName("qa@wizcoders.com");
        loginPage.enterPassword("Quality@13");
        loginPage.clickLoginBtn();
        loginPage.getAlertMessage();
        System.out.println(loginPage.getAlertMessage());
      //  loginPage.clickLogoutBtn();
    }
}
