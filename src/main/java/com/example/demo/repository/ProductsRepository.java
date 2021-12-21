package com.example.demo.repository;

import com.example.demo.model.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductsRepository {

  private static final Logger log = LoggerFactory.getLogger(ProductsRepository.class);

  private static final Map<String, Product> menu = new HashMap<>();

  public void initDB() {
    Product hamburger = new Product();
    hamburger.setName("Burger");
    hamburger.setPrice(10.50);
    menu.put(hamburger.getName(), hamburger);

    Product drink = new Product();
    drink.setName("Coca-Cola");
    drink.setPrice(3.5);
    menu.put(drink.getName(), drink);

    Product beer = new Product();
    beer.setName("Heineken");
    beer.setPrice(4.0);
    menu.put(beer.getName(), beer);

    Product fries = new Product();
    fries.setName("Fries");
    fries.setPrice(4.5);
    menu.put(fries.getName(), fries);

    Product pizza = new Product();
    pizza.setName("Pizza");
    pizza.setPrice(8.5);
    menu.put(pizza.getName(), pizza);

    Product cake = new Product();
    cake.setName("Cheesecake");
    cake.setPrice(5.0);
    menu.put(cake.getName(), cake);

    Product pasta = new Product();
    pasta.setName("Paste bolognese");
    pasta.setPrice(7.5);
    menu.put(pasta.getName(), pasta);
  }

  public Product getProduct(String product) {
    initDB();
    Set<String> products = menu.keySet();
    for (String prod : products) {
      if (prod.toLowerCase().contains(product.toLowerCase())) {
        return menu.get(prod);
      }
    }
    return new Product("We don't have that", null);
  }

  public String getMenu() {
    initDB();
    return menu.keySet().toString();
  }
}
