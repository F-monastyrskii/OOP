package org.skypro.skyshop.product;

public abstract class Product {
    protected String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean isSpecial();

    @Override
    public String toString() {
        return name + " (" + getPrice() + " руб.)";
    }
}
