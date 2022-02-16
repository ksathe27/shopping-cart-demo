package com.ksathe.shoppingcartdemo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartScannerService {
    private final Map<Character, List<PriceInfo>> priceInfoMap;
    private final Map<Character, Integer> inputMap;

    public CartScannerService() {
        Map<Character, List<String>> prices = buildPriceInfoMap();
        //Initialize map
        priceInfoMap = new HashMap<>();
        prices.forEach((key, value) -> {
            List<PriceInfo> priceInfos = new ArrayList<>();
            for (String priceInfoInput : value) {
                String[] inputs = priceInfoInput.split(",");
                if (inputs.length == 2) {
                    PriceInfo info = new PriceInfo(key, inputs[0], inputs[1]);
                    priceInfos.add(info);
                }
            }
            priceInfos.sort(Comparator.comparing(PriceInfo::getPricePerUnit));
            priceInfoMap.put(key, priceInfos);
        });
        inputMap = new HashMap<>();
    }

    public Map<Character, List<PriceInfo>> getpriceInfoMap() {
        return priceInfoMap;
    }
    /* Format
    // <code>  List of String each containing pair <quantity>,<price-per-quantity>
    // "A", [ "1,1.25", "3,3.00" ]
    // "B", [ "1,4.25" ]
    // "C", [ "1,1.00", "6,5.00" ]
    // "D", [ "1,0.75" ]
     */
    private Map<Character, List<String>> buildPriceInfoMap() {
        Map<Character, List<String>> input = new HashMap<>();
        input.put('A', Arrays.asList("1,1.25", "3,3.00"));
        input.put('B', List.of("1,4.25"));
        input.put('C', Arrays.asList("1,1.00", "6,5.00"));
        input.put('D', List.of("1,0.75"));
        return input;
    }
}
