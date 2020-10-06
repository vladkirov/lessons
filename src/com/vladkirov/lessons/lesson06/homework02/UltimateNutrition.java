package com.vladkirov.lessons.lesson06.homework02;

public class UltimateNutrition {
    public static void main(String[] args) {
        Product water     = new Product("water");
        Product avocado   = new Product("avocado", 5.3f, 50);
        Product meat      = new Product("meat",65.3f, 35.0f, 250);
        Product chocolate = new Product("chocolate", 20.5f, 45.6f, 90.1f, 555);
        Product millet     = new Product("millet", 10.5f, 19.6f, 5.1f, 55);

        ListAllowedProducts listAllowedProducts = new ListAllowedProducts(3, 35.0f, 20.0f, 60.0f, 300 );

        listAllowedProducts.addProduct(water, avocado, meat);
        listAllowedProducts.List();

        listAllowedProducts.addProduct(chocolate);
        listAllowedProducts.List();

        listAllowedProducts.addProduct(millet);
        listAllowedProducts.List();
    }
}
