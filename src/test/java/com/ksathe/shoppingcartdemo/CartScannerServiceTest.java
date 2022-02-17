package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;
import com.ksathe.shoppingcartdemo.dto.ShoppingCartPriceDetails;
import org.junit.jupiter.api.Test;

import static com.ksathe.shoppingcartdemo.ShoppingCartHelperFunctions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartScannerServiceTest {
    CartScannerService service = new CartScannerService();

    @Test
    public void testEmptyGetInfo() {
        //
        Map<Character, List<PriceInfo>> result = service.getpriceInfoMap();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetInfoCreation() {
        service.updatePriceInfoMap(buildPriceInfoInputSet1());
        Map<Character, List<PriceInfo>> result = service.getpriceInfoMap();
        testSet1(result);
        // verify its idempotent
        service.updatePriceInfoMap(buildPriceInfoInputSet1());
        Map<Character, List<PriceInfo>> result2 = service.getpriceInfoMap();
        testSet1(result2);
    }

    @Test
    public void testCalculateSet1() {
        //add price info
        service.updatePriceInfoMap(buildPriceInfoInputSet1());
        //calculate and verify
        String input1 = "ABCDABA";
        BigDecimal expected1 = new BigDecimal("13.25");
        ShoppingCartPriceDetails  result1 = service.calculate(convertToCharList(input1));
        assertEquals(input1, result1.getCartItemCodes());
        assertEquals(0, expected1.compareTo(result1.getTotalAmount()));
        assertEquals("USD", result1.getCurrency());

        //example-2
        String input2 = "CCCCCCC";
        BigDecimal expected2 = new BigDecimal("6");
        ShoppingCartPriceDetails  result2 = service.calculate(convertToCharList(input2));
        assertEquals(input2, result2.getCartItemCodes());
        assertEquals(0, expected2.compareTo(result2.getTotalAmount()));
        assertEquals("USD", result2.getCurrency());

        //example-3
        String input3 = "ABCD";
        BigDecimal expected3 = new BigDecimal("7.25");
        ShoppingCartPriceDetails  result3 = service.calculate(convertToCharList(input3));
        assertEquals(input3, result3.getCartItemCodes());
        assertEquals(0, expected3.compareTo(result3.getTotalAmount()));
        assertEquals("USD", result3.getCurrency());
    }

    @Test
    public void testEdgeCasePricingScenario() {
        //add price info
        service.updatePriceInfoMap(buildPriceInfoInputSet2());
        //calculate and verify
        String input1 = "AAAA";
        BigDecimal expected1 = new BigDecimal("4.01");
        ShoppingCartPriceDetails  result1 = service.calculate(convertToCharList(input1));
        assertEquals(input1, result1.getCartItemCodes());
        assertEquals(0, expected1.compareTo(result1.getTotalAmount()));
        assertEquals("USD", result1.getCurrency());
    }

    private List<Character> convertToCharList(String input) {
        return input.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
    }
}
