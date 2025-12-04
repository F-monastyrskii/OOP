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

import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) throws BestResultNotFound {
        System.out.println("Демонстрация работы корзины покупок");
        System.out.println("Создание продуктов для демонстрации");
        Product apple = new DiscountedProduct("Яблоко", 50, 50);
        Product bread = new DiscountedProduct("Хлеб", 40, 10);
        Product milk = new FixPriceProduct("Молоко");
        Product cheese = new SimpleProduct("Сыр", 200);
        Product butter = new SimpleProduct("Масло", 120);
        Product juice = new SimpleProduct("Сок", 90);

        System.out.println("Создаём корзину");
        ProductBasket basket = new ProductBasket();

        System.out.println("Добавление продукта в корзину.");
        basket.addProduct(apple);
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(cheese);
        basket.addProduct(butter);

        System.out.println("	Печать содержимого корзины с несколькими товарами.");
        basket.printBasketContents();

        System.out.println("	Получение стоимости корзины с несколькими товарами.");
        int totalCost = basket.getTotalCost();
        System.out.println("Общая стоимость: " + totalCost + " руб.");

        System.out.println("5.	Поиск товара, который есть в корзине.");
        boolean hasBread = basket.containsProduct("Хлеб");
        System.out.println("Результат поиска: " + hasBread);

        System.out.println("	Поиск товара, которого нет в корзине.");
        boolean hasSausage = basket.containsProduct("Колбаса");
        System.out.println("Результат поиска " + hasSausage);

        System.out.println("	Очистка корзины.");
        basket.clearBasket();
        basket.printBasketContents();

        System.out.println("    Печать содержимого пустой корзины.");
        basket.printBasketContents();

        System.out.println("	Получение стоимости пустой корзины.");
        int emptyCost = basket.getTotalCost();
        System.out.println("Стоимость пустой корзины: " + emptyCost + " руб.");

        System.out.println("	Поиск товара по имени в пустой корзине.");
        boolean hasMilkInEmpty = basket.containsProduct("Молоко");
        System.out.println(" Результат поиска: " + hasMilkInEmpty);

        System.out.println("Демонстрация работы поисковой системы! создание движка");
        SearchEngene searchEngene = new SearchEngene();

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
        Set<Searchable> result1 = searchEngene.search("яблоко");
        printSearchResultsSet(result1);

        System.out.println("Ищем по слову 'хлеб'");
        Set<Searchable> result2 = searchEngene.search("хлеб");
        printSearchResultsSet(result2);

        System.out.println("Ищем по слову 'продукты'");
        Set<Searchable> result3 = searchEngene.search("продукты");
        printSearchResultsSet(result3);

        System.out.println("Ищем по слову 'сыр'");
        Set<Searchable> result4 = searchEngene.search("сыр");
        printSearchResultsSet(result4);

        System.out.println("Ищем по слову 'молоко'");
        Set<Searchable> result5 = searchEngene.search("молоко");
        printSearchResultsSet(result5);

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

        SearchEngene searchEngine = new SearchEngene();

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

        System.out.println("Проверка удаления существующих одноименных продуктов из корзины ");

        System.out.println("Добавление продуктов в корзину перед проверкой удаления");
        basket.addProduct(bread);
        basket.addProduct(bread);
        basket.addProduct(bread);

        System.out.println("Удаление одноименных продуктов и Вывод истории удаленных продуктов");
        printHistoryRemovedProducts(basket.deleteProductByName("хлеб"));

        System.out.println("Вывод содержимого корзины с помощью метода printBasket");
        basket.printBasketContents();

        System.out.println("Удаление несуществующего продукт 'крендель' из корзины и вывод истории удаленных продуктов");
        printHistoryRemovedProducts(basket.deleteProductByName("крендель"));

        basket.historyRemovedProduct();
        printHistoryRemovedProducts(basket.getRemovedProducts());

        System.out.println("Вывод содержимого корзины на экран");
        basket.printBasketContents();

    }
    private static void printHistoryRemovedProducts(List<Product> RemovedProductslist) {
        if (RemovedProductslist == null){
            System.out.println("удаление невозможно");
            return;
        }
        if (RemovedProductslist.isEmpty()){
            System.out.println("Список пуст");
            return;
        }
        System.out.println("история удаления " );
        for (Product name : RemovedProductslist) {
            System.out.println(name);
        }
    }

    private static  void printSearchResultsSet(Set<Searchable> result) {
        System.out.println("Результат поиска");
        for (Searchable entry : result) {
            System.out.println(entry.getStringRepresentation());
        }
    }
//    private static void printSearchResultsMap(Map<String, Searchable> results) {
//        if (results.isEmpty()) {
//            System.out.println("Ничего не найдено");
//            return;
//        }
//
//        System.out.println("Найдено результатов: " + results.size());
//        System.out.println("Результаты (отсортированы по имени):");
//
//        for (Map.Entry<String, Searchable> entry : results.entrySet()) {
//            String name = entry.getKey();
//            Searchable searchable = entry.getValue();
//            System.out.println("  • " + name + " [" + searchable.getContentType() + "]");
//            System.out.println("    Поисковый термин: " +
//                    (searchable.getSearchTerm().length() > 50 ?
//                            searchable.getSearchTerm().substring(0, 50) + "..." :
//                            searchable.getSearchTerm()));
//        }
//    }


}