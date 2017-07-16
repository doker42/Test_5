package myproject.automation.hometask5.tests;

import myproject.automation.hometask5.BaseTest;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void checkSiteVersion() {
        actions.openSite();
    }

    @Test
    public void createNewOrder() {
        actions.openRandomProduct();
        actions.getOpenedProductInfo();
        actions.buyRandomProduct();
        actions.issueProduct();
        actions.checkOrder();
        actions.checkQtyProduct();
    }

}
