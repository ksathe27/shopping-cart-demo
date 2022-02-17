package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;
import com.ksathe.shoppingcartdemo.dto.ShoppingCartPriceDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/*
    This class implements logic to calculate total price of items in the shopping cart.
    PriceInfoMap stores map of itemcode as keys and PriceInfo values as list.
    PriceInfo value contains prices per unit price OR price by volume (larger than 1)
    List of priceinfo per item-code will sorted in descending order of price-per-unit for that PriceInfo
    This service provides 3 apis
    1) getPriceInfo
    2) setPriceInfo
    3) Calculate for given list of item-codes
 */
@Service
public class CartScannerService {
    private Map<Character, List<PriceInfo>> priceInfoMap;
    private Map<Character, Integer> inputMap;

    public CartScannerService() {
        //Initialize maps
        priceInfoMap = new HashMap<>();
        inputMap = new HashMap<>();
    }

    /*
        Get price-info map of all item-codes
        @return map of item-code as key and  list of PriceInfo (sorted in descending order of price per unit) as values
     */
    public Map<Character, List<PriceInfo>> getpriceInfoMap() {
        return priceInfoMap;
    }

    /*
        Set price-info map of all item-codes
        @return map of item-code as key and  list of PriceInfo (sorted in descending order of price per unit) as values
     */
    public void updatePriceInfoMap(final List<PriceInfoInput> input) {
        priceInfoMap.clear();
        input.forEach( rec -> {
            PriceInfo info = new PriceInfo(rec.getItemCode(), rec.getPrice(), rec.getQuantity());
            List<PriceInfo> curr = priceInfoMap.getOrDefault(rec.getItemCode(), new ArrayList<>());
            curr.add(info);
            priceInfoMap.put(rec.getItemCode(), curr);
        });
        priceInfoMap.values().forEach( priceInfos -> priceInfos.sort(Comparator.comparing(PriceInfo::getPricePerUnit)));
    }

    /*
        Caculate total for all items in a given shopping cart
        @param List of all items (as item-codes) in a shopping cart
        @return ShoppingCartPriceDetails contains
            - total amount for shopping cart upto 4 decimal places
            - currency (set to USD as default right now)
            - Input list of item-codes
     */
    public ShoppingCartPriceDetails calculate(final List<Character> allItems) {
        inputMap.clear();
        for(Character itemCode : allItems) {
            add(itemCode);
        }
        BigDecimal sum = new BigDecimal(0).setScale(4, RoundingMode.HALF_UP);
        for(Map.Entry<Character, Integer> codeByCount : inputMap.entrySet()) {
            sum = sum.add(calculateForCode(codeByCount));
        }
        String allItemsAsString = allItems.stream().map(String::valueOf).collect(Collectors.joining());
        return new ShoppingCartPriceDetails(allItemsAsString, sum, "USD");
    }

    private void add(final Character input) {
        inputMap.put(input, inputMap.getOrDefault(input, 0) + 1);
    }

    private BigDecimal calculateForCode(final Map.Entry<Character, Integer> codeByCount) {
        BigDecimal total = new BigDecimal(0);
        Integer quantity = codeByCount.getValue();
        List<PriceInfo> ll = priceInfoMap.getOrDefault(codeByCount.getKey(), new ArrayList<>());
        for (PriceInfo priceInfo: ll) {
            if (quantity >= priceInfo.getNoOfUnits().intValue()) {
                int groupCount = quantity / priceInfo.getNoOfUnits().intValue();
                BigDecimal currPrice = priceInfo.getTotalPrice().multiply(BigDecimal.valueOf(groupCount));
                total = total.add(currPrice);
                quantity = quantity % priceInfo.getNoOfUnits().intValue();
            }
        }
        return total;
    }
}
