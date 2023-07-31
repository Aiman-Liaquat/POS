package TestsPartyBalance;

import Base.BaseTest;
import org.testng.annotations.Test;

public class VerifyPartyBalance extends BaseTest {
    @Test
    public void VerifyPartyBalance(){
        //---Login
        var login = homePage.GoToLoginPage();
        login.enterUserName("test@passu.tech");
        login.enterPassword("Quality@123");
        login.clickLoginBtn();
        login.getMessageAfterLogin();
        System.out.println(login.getMessageAfterLogin());
        //---Party Balance Page
        var partyBlnc = homePage.GoToPartyBalancePage();
        partyBlnc.clickPartyBalance();
        partyBlnc.getPartyBalanceText();
        System.out.println(partyBlnc.getPartyBalanceText());
        partyBlnc.selectEntriesFromOptions("100");
        partyBlnc.back();
      //  partyBlnc.displayContactOpeningClosingBalances();
       // partyBlnc.clickPartySales();
       // partyBlnc.enterPartyContact();
        //partyBlnc.enterDate();
       // partyBlnc.clickSubmitBtn();
        partyBlnc.comparePartyBlnAndPartySales();
    }
}
