package testsPos;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class VerifySearchAndCartProduct extends BaseTest {
    @Test
    public void VerifySearchAndCartProduct() throws IOException {
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


        //-- Search Product--//
        sale.searchAndAddToCart("Anycom Kit");
        sale.getSelectedSearchProduct();
        System.out.println("Product Selected is " + sale.getSelectedSearchProduct());

        //--Get Bill Summary and Add Freight--//
        sale.getbillSummary();
        System.out.println("Product Bill Summary is " + sale.getbillSummary());
        sale.selectFreight(1);
        sale.getSelectedFreight();
        System.out.println("Freight Selected is " + sale.getSelectedFreight());
        sale.addFreight("50");
        Assert.assertEquals(sale.getFreightPrice(), "50", "50");
        System.out.println("You entered Freight: " +sale.getFreightPrice());
        sale.addFreightDescription("Testing Freight Description");
        Assert.assertEquals(sale.getFreightDescription(), "Testing Freight Description");
        System.out.println("You entered: " + sale.getFreightDescription());


        sale.clickSave();
        sale.enterPriceAlerts(50000);
        //Assert.assertEquals(sale.getPriceAlert(), "50000", "Amount isn't correct");
        sale.getEnteredPrice();
        System.out.println("You entered price: " +sale.getEnteredPrice());
        sale.okPriceAlerts();
        sale.getPriceAlert();
        System.out.println("Alert: " + sale.getPriceAlert());
        sale.okPriceAlerts();
        sale.screenShot();

    }
    }
