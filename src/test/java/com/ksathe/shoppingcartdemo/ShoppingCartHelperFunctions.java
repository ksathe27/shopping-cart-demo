package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

    public static void testSet1(Map<Character, List<PriceInfo>> result) {
        Arrays.asList('A', 'B', 'C', 'D').forEach(priceCode -> assertTrue(result.containsKey(priceCode)));
        assertTrue(result.get('A').contains(new PriceInfo('A', "1.25", "1")));
        assertTrue(result.get('A').contains(new PriceInfo('A', "3", "3")));
        assertTrue(result.get('B').contains(new PriceInfo('B', "4.25", "1")));
        assertTrue(result.get('C').contains(new PriceInfo('C', "1", "1")));
        assertTrue(result.get('C').contains(new PriceInfo('C', "5.0", "6")));
        assertTrue(result.get('D').contains(new PriceInfo('D', "0.75", "1")));
    }
}
