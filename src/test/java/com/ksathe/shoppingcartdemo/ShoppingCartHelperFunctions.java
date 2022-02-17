package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;

import java.util.Arrays;
import java.util.List;

public class ShoppingCartHelperFunctions {


    public static List<PriceInfoInput> buildPriceInfoInputSet1() {
        List<PriceInfoInput> input = Arrays.asList(new PriceInfoInput('A', "1.25", "1"),
                new PriceInfoInput('A', "3", "3"),
                new PriceInfoInput('B', "4.25", "1"),
                new PriceInfoInput('C', "1", "1"),
                new PriceInfoInput('C', "5", "6"),
                new PriceInfoInput('D', "0.75", "1"));
        return input;
    }

    public static List<PriceInfoInput> buildPriceInfoInputSet2() {
        List<PriceInfoInput> input = Arrays.asList(new PriceInfoInput('A', "1.01", "1"),
                new PriceInfoInput('A', "3", "3"),
                new PriceInfoInput('D', "0.75", "1"));
        return input;
    }
}
