package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

import static com.ksathe.shoppingcartdemo.ShoppingCartHelperFunctions.buildPriceInfoInputSet1;
import static com.ksathe.shoppingcartdemo.ShoppingCartHelperFunctions.testSet1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShoppingCartControllerTest {
    CartScannerService cartScannerService;
    ShoppingCartController controller;

    @BeforeAll
    public void init() {
        cartScannerService = new CartScannerService();
        controller = new ShoppingCartController();
        controller.setCartScannerService(cartScannerService);
    }

    @Test
    public void shoppingCartDemo() {
        String result = controller.ShoppingCartDemo();
        //assert
        assertEquals(result, "ShoppingCartController");
    }

    @Test
    public void getCurrPriceInfo() {
        //
        Map<Character, List<PriceInfo>>  result = controller.updateCurrPriceInfo(buildPriceInfoInputSet1());
        // get etmpy price info
        assertFalse(result.isEmpty());
        testSet1(result);
    }

    // get and update priceinfo

}