package testsPos;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class VerifyUnitDiscountQtyProducts extends BaseTest {
    @Test
    public void VerifyUnitDiscountQtyProducts() throws IOException {
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



        //---Select the Unit/Discount/Qty---//
       sale.selectUnit(0);
        System.out.println("You Select Unit: " + sale.getUnit());
        sale.selectQty("2");
        System.out.println("You Entered Qunatity: " + sale.getQty());
        sale.clearDisc();
        sale.enterDiscount("100");
        System.out.println("You entered Discount: " +sale.getDiscount());


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

