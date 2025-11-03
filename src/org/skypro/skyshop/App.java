package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        System.out.println("демонстрация работы корзины покупок");
        System.out.println("создание продуктов для демонстрации");
        Product apple = new DiscountedProduct("Яблоко", 50, 50);
        Product bread = new DiscountedProduct("Хлеб", 40, 10);
        Product milk = new FixPriceProduct("Молоко");
        Product cheese = new SimpleProduct("Сыр", 200);
        Product butter = new SimpleProduct("Масло", 120);
        Product juice = new SimpleProduct("Сок", 90);

        System.out.println("создаём корзину");
        ProductBasket basket = new ProductBasket();

        System.out.println("1.	Добавление продукта в корзину.");
        basket.addProduct(apple);
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(cheese);
        basket.addProduct(butter);

        System.out.println("2.	Добавление продукта в заполненную корзину, в которой нет свободного места.");
        basket.addProduct(juice);

        System.out.println("3.	Печать содержимого корзины с несколькими товарами.");
        basket.printBasketContents();

        System.out.println("4.	Получение стоимости корзины с несколькими товарами.");
        int totalCost = basket.getTotalCost();
        System.out.println("Общая стоимость: " + totalCost + " руб.");

        System.out.println("5.	Поиск товара, который есть в корзине.");
        boolean hasBread = basket.containsProduct("Хлеб");
        System.out.println("Результат поиска: " + hasBread);

        System.out.println("6.	Поиск товара, которого нет в корзине.");
        boolean hasSausage = basket.containsProduct("Колбаса");
        System.out.println("Результат поиска " + hasSausage);

        System.out.println("7.	Очистка корзины.");
        basket.clearBasket();
        basket.printBasketContents();

        System.out.println("8.	Печать содержимого пустой корзины.");
        basket.printBasketContents();

        System.out.println("9.	Получение стоимости пустой корзины.");
        int emptyCost = basket.getTotalCost();
        System.out.println("Стоимость пустой корзины: " + emptyCost + " руб.");

        System.out.println("10.	Поиск товара по имени в пустой корзине.");
        boolean hasMilkInEmpty = basket.containsProduct("Молоко");
        System.out.println(" Результат поиска: " + hasMilkInEmpty);
    }
}