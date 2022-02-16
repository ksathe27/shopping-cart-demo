package com.ksathe.shoppingcartdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartControllerTest {

    @Test
    void shoppingCartDemo() {
        ShoppingCartController controller = new ShoppingCartController();
        String result = controller.ShoppingCartDemo();
        //assert
        assertEquals(result, "ShoppingCartController");
    }

    @Test
    void getCurrPriceInfo() {
    }
}