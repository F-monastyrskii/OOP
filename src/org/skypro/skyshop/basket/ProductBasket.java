package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int BASKET_CAPACITY = 5; //константа максимальной вместимости корзины
    private final Product[] products; //приватный массив для хранения продуктов
    private int productCount; //счетчик добавленных продуктов

    //конструктор корзины, который инициализирует массив продуктов и счетчик
    public ProductBasket() {//Создаём массив фиксированного размера
        this.products = new Product[BASKET_CAPACITY];
        this.productCount = 0;
    }

    //метод для добавления продукта в корзину.
    //проверяет есть ли свободное место в корзине
    //param product - продукт для добавления
    public void addProduct(Product product) {
        //проверяем не переполнена ли корзина
        if (productCount < BASKET_CAPACITY) {
            //если в корзине есть место , то добавляем продукт в первую свободную ячейку
            products[productCount] = product;
            productCount++;// увеличиваем счетчик
            System.out.println("Продукт " + product.getName() + " Добавлен в корзину");
        } else {
            //в противном случае сообщаем , что корзина полна
            System.out.println("Невозможно добавить продукт");
        }
    }
    //метод для расчёта общей стоимости всех продуктов в корзине. return общая стоимость корзины
    public  int getTotalCost(){
        int total = 0;
        for (int i=0; i<productCount; i++) {
            total += products[i].getPrice();//суммируем цены
        }
        return total;
    }
    // метод для печати содержимого корзины в консоль. Форматирует вывод согласно требованиям.
    public void printBasketContents() {
        //проверяем не пустая ли корзина
        if(productCount ==0) {
            System.out.println("в корзине пусто ");
            return;
        }
        System.out.println("Содержимое корзины:");
        // Выводим каждый продукт в формате "имя продукта: стоимость"
        for (int i = 0; i < productCount; i++) {
            Product product = products[i];
            System.out.println(product.getName() + ": " + product.getPrice());
        }
        // Выводим итоговую стоимость
        System.out.println("Итого: " + getTotalCost());
    }
    //метод для проверки наличия продукта в корзине по имени (независимо от регистра)
    public boolean containsProduct(String productName) {
        // Проходим по всем продуктам в корзине
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(productName)) {
                return true;//продукт найден
            }
        }
        return false;// продукт не найден
    }

    //метод для очистки корзины. Проставляет всем элементам null и сбрасываем счетчик
    public void clearBasket(){
        for (int i = 0; i < BASKET_CAPACITY; i++) {
            products[i] = null;//Очищаем ячейку
        }
        productCount = 0;//сбрасываем счетчик
        System.out.println("корзина очищена");
    }
    //вспомогательный метод для получения текущего количества продуктов
    public int getProductCount() {
        return productCount;
    }
}

