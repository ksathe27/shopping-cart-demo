package com.ksathe.shoppingcartdemo.dto;

public class PriceInfoInput {
    Character itemCode;
    String price;
    String quantity;

    public void setItemCode(Character itemCode) {
        this.itemCode = itemCode;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
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
