package com.ksathe.shoppingcartdemo.dto;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceInfo {
    private final int SCALE = 4;
    private final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private final Character productCode;
    private final BigDecimal noOfUnits;
    private final BigDecimal totalPrice;
    private final BigDecimal pricePerUnit;

    public Character getProductCode() {
        return productCode;
    }

    public BigDecimal getNoOfUnits() {
        return noOfUnits;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public PriceInfo(Character code, String price, String units) {
        productCode = code;
        totalPrice = new BigDecimal(price);
        noOfUnits = new BigDecimal(units);
        pricePerUnit = totalPrice.divide(noOfUnits, SCALE, ROUNDING_MODE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        PriceInfo obj = (PriceInfo) other;
        return this.getNoOfUnits().equals(obj.getNoOfUnits())
                && this.getTotalPrice().compareTo(obj.getTotalPrice()) == 0
                && this.getProductCode().compareTo(obj.getProductCode()) == 0;
    }
}
