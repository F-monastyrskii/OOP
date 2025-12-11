package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {

    private final Map< String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Невозможно добавить null");
        }
        if (products.containsKey(product.getName())) {
            products.get(product.getName()).add(product);
        } else {
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            products.put(product.getName(), productList);
        }
    }

    public int getTotalCost() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }


    public void printBasketContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто ");
            return;
        }else{
        System.out.println("Содержимое корзины: ");
        products.values().stream()
                .flatMap(Collection::stream)
                        .forEach(product -> System.out.println(product));
            System.out.println("Итого: " + getTotalCost());
            System.out.println("Специальных товаров:" + getSpecialCount());
        }
    }



    public List<Product> deleteProductByName(String name) {
        return products.remove(name);
    }


    public boolean containsProduct(String productName) {
        return products.containsKey(productName);
    }

    public void clearBasket() {
        products.clear();

        System.out.println("корзина очищена");
    }

    public int getProductCount() {
        return (int) products.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    private final List<Product> removedProducts = new ArrayList<>();



    public List<Product> getRemovedProducts(){
        return removedProducts;
    }

    public void historyRemovedProduct() {
        removedProducts.clear();
    }


}

