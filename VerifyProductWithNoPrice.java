package testsPos;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyProductWithNoPrice extends BaseTest {
    @Test

    public void VerifyProductWithNoPrice(){
        //---Login
        var login = homePage.GoToLoginPage();
        login.enterUserName("test@passu.tech");
        login.enterPassword("Quality@123");
        login.clickLoginBtn();
        login.getMessageAfterLogin();
        System.out.println(login.getMessageAfterLogin());
        //---Point of Sale
        var sale = homePage.GoToPosPage();
        sale.PosPage();
        System.out.println(sale.getTitlePos());
        // sale.showDropdownoptions();
        sale.selectOptions(2);
        System.out.println("Option Selected is " + sale.getSlectedOption());
        sale.enterCustName("TestAbc2");
        sale.enterCustPhone("+12345678900");
        sale.CheckedReceiptInitials();



        //--Product with no price--//
        sale.productWithNoPrice();
        System.out.println(sale.getPriceAlert());
        Assert.assertEquals(sale.getPriceAlert(), "Price is not attached to this product. :)");
        sale.okPriceAlerts();
        sale.getProductWithNoPrice();
        System.out.println("Product Selected is: " + sale.getProductWithNoPrice());

    }

}
