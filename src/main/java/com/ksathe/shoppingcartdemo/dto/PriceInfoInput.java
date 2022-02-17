package com.ksathe.shoppingcartdemo.dto;

public class PriceInfoInput {
    Character itemCode;
    String price;
    String quantity;

    public PriceInfoInput(Character itemCode, String price, String quantity) {
        this.itemCode = itemCode;
        this.price = price;
        this.quantity = quantity;
    }

    public Character getItemCode() {
        return itemCode;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
