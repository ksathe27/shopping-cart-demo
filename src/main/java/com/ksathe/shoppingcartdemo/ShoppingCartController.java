package com.ksathe.shoppingcartdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

    @GetMapping("/shopping-cart-demo")
    public String ShoppingCartDemo() {
        return "ShoppingCartController";
    }
}
