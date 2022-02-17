package com.ksathe.shoppingcartdemo.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartPriceDetails {

    final String cartItemCodes;
    final BigDecimal totalAmount;
    final String currency;

    public ShoppingCartPriceDetails(String codes, BigDecimal amount, String curr) {
        cartItemCodes = codes;
        totalAmount = amount;
        currency = curr;
    }

    public String getCartItemCodes() {
        return cartItemCodes;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCurrency() {
        return currency;
    }
}
