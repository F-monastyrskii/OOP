package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discountPercent;

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name);
        this.basePrice = basePrice;
        if (basePrice < 0){
            throw new IllegalArgumentException("Цена продукта должна быть строго больше нуля. Передано: " + basePrice);
        }
        this.discountPercent = discountPercent;
        if (discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("Процент должен быть числом в диапазоне от 0 до 100 включительно. Передано: " + discountPercent);
        }
    }

    @Override
    public int getPrice(){
        int discountedPrice = basePrice * (100 - discountPercent)/100;
        return discountedPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discountPercent + "%)";
    }

}
