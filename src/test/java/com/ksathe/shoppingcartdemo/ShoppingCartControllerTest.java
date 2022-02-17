package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.ksathe.shoppingcartdemo.ShoppingCartHelperFunctions.buildPriceInfoInputSet1;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartControllerTest {
    ShoppingCartController controller = new ShoppingCartController();

    @Test
    void shoppingCartDemo() {
        String result = controller.ShoppingCartDemo();
        //assert
        assertEquals(result, "ShoppingCartController");
    }

    @Test
    void getCurrPriceInfo() {
        //
        Map<Character, List<PriceInfo>>  result = controller.updateCurrPriceInfo(buildPriceInfoInputSet1());
        // get etmpy price info
        assertTrue(result.isEmpty());
    }

    // get and update priceinfo


    // empty shopping cart returns 0

}