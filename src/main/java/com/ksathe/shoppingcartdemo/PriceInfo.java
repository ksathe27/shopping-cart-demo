package com.ksathe.shoppingcartdemo;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceInfo {
    private final int SCALE = 4;
    private final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
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

    private final Character productCode;
    private final BigDecimal noOfUnits;
    private final BigDecimal totalPrice;
    private final BigDecimal pricePerUnit;

    public PriceInfo(Character code, String units, String price) {
        productCode = code;
        noOfUnits = new BigDecimal(units);
        totalPrice = new BigDecimal(price);
        pricePerUnit = totalPrice.divide(noOfUnits, SCALE, ROUNDING_MODE);
    }
}
