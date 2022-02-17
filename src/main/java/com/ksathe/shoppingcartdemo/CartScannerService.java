package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;
import org.springframework.stereotype.Service;

import java.util.*;

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
}
