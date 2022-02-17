package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;
import com.ksathe.shoppingcartdemo.dto.ShoppingCartPriceDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartScannerService {
    private Map<Character, List<PriceInfo>> priceInfoMap;
    private Map<Character, Integer> inputMap;

    public CartScannerService() {
        //Initialize maps
        priceInfoMap = new HashMap<>();
        inputMap = new HashMap<>();
    }

    public Map<Character, List<PriceInfo>> getpriceInfoMap() {
        return priceInfoMap;
    }

    public void updatePriceInfoMap(List<PriceInfoInput> input) {
        priceInfoMap.clear();
        input.forEach( rec -> {
            PriceInfo info = new PriceInfo(rec.getItemCode(), rec.getPrice(), rec.getQuantity());
            List<PriceInfo> curr = priceInfoMap.getOrDefault(rec.getItemCode(), new ArrayList<>());
            curr.add(info);
            priceInfoMap.put(rec.getItemCode(), curr);
        });
        priceInfoMap.values().forEach( priceInfos -> priceInfos.sort(Comparator.comparing(PriceInfo::getPricePerUnit)));
    }

    public ShoppingCartPriceDetails calculate(List<Character> allItems) {
        inputMap.clear();
        for(Character itemCode : allItems) {
            add(itemCode);
        }
        BigDecimal sum = new BigDecimal(0);
        for(Map.Entry<Character, Integer> codeByCount : inputMap.entrySet()) {
            sum = sum.add(calculateForCode(codeByCount));
        }
        String allItemsAsString = allItems.stream().map(String::valueOf).collect(Collectors.joining());
        return new ShoppingCartPriceDetails(allItemsAsString, sum, "USD");
    }

    private void add(Character input) {
        inputMap.put(input, inputMap.getOrDefault(input, 0) + 1);
    }

    private BigDecimal calculateForCode(Map.Entry<Character, Integer> codeByCount) {
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
