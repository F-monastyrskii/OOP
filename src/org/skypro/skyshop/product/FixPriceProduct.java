package org.skypro.skyshop.product;

public class FixPriceProduct extends Product{
    private static final int FIXED_PRICE = 50;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return FixPriceProduct.FIXED_PRICE;
    }

    public static int getFixedPrice(){
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial(){
        return true;
    }

    @Override
    public String toString(){
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
