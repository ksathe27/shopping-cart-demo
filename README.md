# shopping-cart-demo
Shopping cart demo

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
    [ { "itemCode" : "A", "price" : "1.25", "quantity" : 1 },
      { "itemCode" : "A", "price" : "3", "quantity" : 3 },
      { "itemCode" : "B", "price" : "4.25", "quantity" : 1 },
      { "itemCode" : "C", "price" : "1", "quantity" : 1 },
      { "itemCode" : "C", "price" : "5.00", "quantity" : 6 },
      { "itemCode" : "D", "price" : "0.75", "quantity" : 1 }
    ]

2. Cart service input



