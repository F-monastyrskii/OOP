package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductBasket {

//    private int productCount = 0;

    private final LinkedList<Product> products = new LinkedList<>();

    public void addProduct(Product product) {

        products.add(product);
        System.out.println("Продукт " + product.getName() + " Добавлен в корзину");
    }

    public int getTotalCost() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    private int getSpecialProductCount() {
        int specialCount = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    public void printBasketContents() {

        if (products.isEmpty()) {
            System.out.println("в корзине пусто ");
            return;
        }
        System.out.println("Содержимое корзины:");

        for (Product product : products) {
            System.out.println(product.toString());
        }

        System.out.println("Итого: " + getTotalCost());

        System.out.println("Специальных товаров: " + getSpecialProductCount());
    }

    public boolean containsProduct(String productName) {

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName))
                return true;
        }
        return false;
    }

    public void clearBasket() {
        products.clear();

        System.out.println("корзина очищена");
    }

    public int getProductCount() {
        return products.size();
    }

    private final List<String> removedProducts = new ArrayList<>();

    public List<String> deleteProductByName(String name) {
        if (name == null || name.isEmpty()){
            return null;
        }
        List<Product> tempProducts = new ArrayList<>(products);
        boolean isRemovedProduct=false;

        Iterator<Product> iterator = tempProducts.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (name.equalsIgnoreCase (product.getName())){
                products.remove(product);
                removedProducts.add(product.getName());
                isRemovedProduct=true;
            }
        }

        return isRemovedProduct ?removedProducts:null;
    }

    public List<String> getRemovedProducts(){
        return removedProducts;
    }

    public void historyRemovedProduct() {
        removedProducts.clear();
    }


}

