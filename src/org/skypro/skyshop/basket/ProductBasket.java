package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int BASKET_CAPACITY = 5;
    private final Product[] products;
    private int productCount;

    public ProductBasket() {
        this.products = new Product[BASKET_CAPACITY];
        this.productCount = 0;
    }

    public void addProduct(Product product) {

        if (productCount < BASKET_CAPACITY) {

            products[productCount] = product;
            productCount++;
            System.out.println("Продукт " + product.getName() + " Добавлен в корзину");
        } else {

            System.out.println("Невозможно добавить продукт");
        }
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    private int getSpecialProductCount() {
        int specialCount = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    public void printBasketContents() {

        if (productCount == 0) {
            System.out.println("в корзине пусто ");
            return;
        }
        System.out.println("Содержимое корзины:");

        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].toString());
        }

        System.out.println("Итого: " + getTotalCost());

        System.out.println("Специальных товаров: " + getSpecialProductCount());
    }

    public boolean containsProduct(String productName) {

        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < BASKET_CAPACITY; i++) {
            products[i] = null;
        }
        productCount = 0;
        System.out.println("корзина очищена");
    }

    public int getProductCount() {
        return productCount;
    }
}

