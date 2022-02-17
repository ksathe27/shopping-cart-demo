package com.ksathe.shoppingcartdemo.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartPriceDetails {

    final List<Character> cartItemCodes;
    final BigDecimal totalAmount;
    final String currency;

    public ShoppingCartPriceDetails(List<Character> codes, BigDecimal amount, String curr) {
        cartItemCodes = codes;
        totalAmount = amount;
        currency = curr;
    }

    public List<Character> getCartItemCodes() {
        return cartItemCodes;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCurrency() {
        return currency;
    }
}
