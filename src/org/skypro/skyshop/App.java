package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngene;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) throws BestResultNotFound {
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

        System.out.println("Демонстрация работы поисковой системы! создание движка");
        SearchEngene searchEngene = new SearchEngene(5);

        System.out.println("Добавление товаров в поисковой движок");
        searchEngene.add(apple);
        searchEngene.add(bread);
        searchEngene.add(milk);
        searchEngene.add(cheese);
        searchEngene.add(butter);
        searchEngene.add(juice);

        System.out.println("Добавление статей в поисковой движок");
        Article article1 = new Article("Польза яблок", "Яблоки очень полезны для здоровья");
        Article article2 = new Article("Вред хлебобулочных изделий", "От хлебобулочных изделий толстеют");
        Article article3 = new Article("Молочные продукты", "Молоко и кисломолочные продукты очень полезны для пищеварения");

        searchEngene.add(article1);
        searchEngene.add(article2);
        searchEngene.add(article3);

        System.out.println("Демонстрация поиска");

        System.out.println("Ищем по слову 'яблоко'");
        Searchable[] results1 = searchEngene.search("яблоко");
        printSearchResults(results1);

        System.out.println("Ищем по слову 'хлеб'");
        Searchable[] results2 = searchEngene.search("хлеб");
        printSearchResults(results2);

        System.out.println("Ищем по слову 'продукты'");
        Searchable[] results3 = searchEngene.search("продукты");
        printSearchResults(results3);

        System.out.println("Ищем по слову 'сыр'");
        Searchable[] results4 = searchEngene.search("сыр");
        printSearchResults(results4);

        System.out.println("Ищем по слову 'молоко'");
        Searchable[] results5 = searchEngene.search("молоко");
        printSearchResults(results5);

        System.out.println("\n Тестирование проверок в продуктах");

        System.out.println(" Создаём продукт с именем null: ");
        try {
            Product nullNameProduct = new SimpleProduct(null, 100 );
            System.out.println("Успешно создан " +  nullNameProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" Создаём продукты с именем из пробелов: ");
        try {
            Product spacesNameProduct = new SimpleProduct("    ", 100 );
            System.out.println("Успешно создан " +  spacesNameProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" Создаём продукты с пустой строкой в имени: ");
        try {
            Product emptyNameProduct = new SimpleProduct("", 100 );
            System.out.println("Успешно создан " +  emptyNameProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Создаём обычные продукты с неправильной ценой");

        System.out.println("Создаём продукт с ценой 0");
        try {
            Product zeroPriceProduct = new SimpleProduct("нулевая цена", 0);
            System.out.println("Успешно создан" + zeroPriceProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Создаём продукт с ценой ниже нуля");
        try {
            Product negativePriceProduct = new SimpleProduct("отрицательная цена", -70);
            System.out.println("Успешно создан" + negativePriceProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Создаём уценённые товары с некорректными данными");

        System.out.println("Создаём уценённый товар с базовой ценой 0");
        try {
            Product zeroBasePriceProduct = new DiscountedProduct ("нулевая базовая цена", 0, 20);
            System.out.println("Успешно создан" + zeroBasePriceProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Создаём уценённый товар с отрицательной базовой ценой 0");
        try {
            Product negativeBasePriceProduct = new DiscountedProduct ("отрицательная базовая цена", -20, 20);
            System.out.println("Успешно создан" + negativeBasePriceProduct);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Создаём уценённый товар с отрицательной скидкой");
        try {
            Product negativeDiscount = new DiscountedProduct ("отрицательная скидка", 20, -20);
            System.out.println("Успешно создан" + negativeDiscount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Создаём уценённый товар со скидкой более 100 процентов");
        try {
            Product tooBigDiscount = new DiscountedProduct ("скидка более 100 процентов", 20, 120);
            System.out.println("Успешно создан" + tooBigDiscount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" \nТестирование поиска наиболее подходящего элемента");
        System.out.println("Создаём движок и добавляем тестовые данные: ");

        SearchEngene searchEngine = new SearchEngene(50);

        searchEngine.add(new SimpleProduct("Яблоко красное сладкое", 50));
        searchEngine.add(new SimpleProduct("Яблоко зелёное кислое", 40));
        searchEngine.add(new SimpleProduct("Банан солёный", 30));
        searchEngine.add(new SimpleProduct("Хлеб пресный", 20));
        searchEngine.add(new SimpleProduct("Хлеб ржаной", 40));

        searchEngine.add(new Article("Рецепт яблочного пирога", "Для пирога понадобятся: Яблоко красное сладкое, мука, сахар. Яблоко порезать и очистить..."));
        searchEngine.add(new Article("Польза хлеба для здоровья", "Хлеб ржаной невероятно полезен для пищеварения"));
        searchEngine.add(new Article("Использование зеленого яблока", "Мало кто знает, что самым первым слоем в салате 'сельдь под шубой' может удачно стать яблоко зеленое кислое. Реально хорошо сочетается"));

        System.out.println("Поиск по слову 'яблоко'");
        try {
            Searchable bestMatch1 = searchEngine.findBestMatch("яблоко");
            System.out.println("Найден лучший результат: " + bestMatch1.getStringRepresentation());
            System.out.println(" Search term: " + bestMatch1.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println(" Ошибка: "+ e.getMessage());
        }

        System.out.println("Поиск по слову 'хлеб'");
        try {
            Searchable bestMatch2 = searchEngine.findBestMatch("хлеб");
            System.out.println("Найден лучший результат: " + bestMatch2.getStringRepresentation());
            System.out.println(" Search term: " + bestMatch2.getSearchTerm());
        } catch (BestResultNotFound e) {
            System.out.println(" Ошибка: "+ e.getMessage());
        }

        System.out.println("Поиск по слову 'банан'");
        try {
            Searchable bestMatch3 = searchEngine.findBestMatch("банан");
            System.out.println("Найден лучший результат: " + bestMatch3.getStringRepresentation());
            System.out.println(" Search term: " + bestMatch3.getSearchTerm());
        }catch (BestResultNotFound e) {
            System.out.println(" Ошибка: "+ e.getMessage());
        }

        System.out.println("Поиск по слову 'крем'");
        try {
            Searchable bestMatch4 = searchEngine.findBestMatch("крем");
            System.out.println("Найден лучший результат: " + bestMatch4.getStringRepresentation());
            System.out.println(" Search term: " + bestMatch4.getSearchTerm());
        }catch (BestResultNotFound e) {
            System.out.println(" Ошибка: "+ e.getMessage());
        }
    }

    private static void printSearchResults(Searchable[] results) {
        boolean haveBeenFound = false;
        for (int i = 0; i < results.length; i++) {
            if (results[i] != null) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
                haveBeenFound = true;
            }
        }
        if (!haveBeenFound) {
            System.out.println("не найдено");
        }
    }
}