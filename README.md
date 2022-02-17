# shopping-cart-demo
Shopping cart demo. This demonstrates shopping cart functionality.
Please use swagger ui link below to try out apis.

## Notes and assumptions
The problem assumes price-code with single character, but most fo the examples are designed to support
changes in item-code from character to string or more complex use cases. 
But for simplicity I kept calculate api as single get for all items-codes, which represents all items in the cart.

# List of api
1. Set price-info : Set prices by item-code for one and/or multiple units
   - http-get http://localhost:8080/api/priceinfo
2. Get price-info : Get prices by item-code for one and/or multiple units
   - http-post http://localhost:8080/api/priceinfo (json data as bodyparam)
3. Calculate total price for given list of items.
   - http-get : http://localhost:8080/api/calculate?itemsByCode="<item-codes>"

# Tech specs for running locally 
Mac OS, apache mvn 3.8.4, Java 11, Intelij 2021.3.2

# Pre-requisites
- Install brew
- Install maven (using brew) :
- Install java (using brew) : https://mkyong.com/java/how-to-install-java-on-mac-osx/

# Run Locally
1. Compile : mvn install
2. Run tests : mvn test
3. Run Locally :  mvn spring-boot:run
4. Verify (by visiting following)
   1. http://localhost:8080/v2/api-docs
   2. http://localhost:8080/swagger-ui.html

# Test using examples
1. Input for priceinfo
```
[ { "itemCode" : "A", "price" : "1.25", "quantity" : 1 },
  { "itemCode" : "A", "price" : "3", "quantity" : 3 },
  { "itemCode" : "B", "price" : "4.25", "quantity" : 1 },
  { "itemCode" : "C", "price" : "1", "quantity" : 1 },
  { "itemCode" : "C", "price" : "5.00", "quantity" : 6 },
  { "itemCode" : "D", "price" : "0.75", "quantity" : 1 }
]
```
2. Cart service calculate
     http://localhost:8080/api/calculate?itemsByCode=AAAA"


