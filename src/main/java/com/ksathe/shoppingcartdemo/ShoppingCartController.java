package com.ksathe.shoppingcartdemo;

import com.ksathe.shoppingcartdemo.dto.PriceInfo;
import com.ksathe.shoppingcartdemo.dto.PriceInfoInput;
import com.ksathe.shoppingcartdemo.dto.ShoppingCartPriceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {
    @Autowired
    CartScannerService cartScannerService;

    public void setCartScannerService(CartScannerService cartScannerService) {
        this.cartScannerService = cartScannerService;
    }

    @GetMapping("/shopping-cart-demo")
    public String ShoppingCartDemo() {
        return "ShoppingCartController";
    }

    @GetMapping("/priceinfo")
    public Map<Character, List<PriceInfo>>  getCurrPriceInfo() {
        PriceInfo p = new PriceInfo('A', "10", "10");
        List<PriceInfo> allPriceInfos = cartScannerService.getpriceInfoMap().values().stream()
                .flatMap(List::stream).collect(Collectors.toList());
        return cartScannerService.getpriceInfoMap();
    }

    @PostMapping("/priceinfo")
    public Map<Character, List<PriceInfo>> updateCurrPriceInfo(@RequestBody List<PriceInfoInput> priceInfos) {
        cartScannerService.updatePriceInfoMap(priceInfos);
        return cartScannerService.getpriceInfoMap();
    }

    @GetMapping("/calculate")
    public ShoppingCartPriceDetails calculate(@RequestParam(name = "itemsByCode", defaultValue = "") String itemsByCode) {
        List<Character> allItems = itemsByCode.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
        return cartScannerService.calculate(allItems);
    }
}
