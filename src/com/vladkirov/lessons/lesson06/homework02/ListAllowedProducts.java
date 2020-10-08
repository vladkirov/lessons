package com.vladkirov.lessons.lesson06.homework02;

import com.vladkirov.lessons.lesson06.homework01.Climber;

public class ListAllowedProducts {
    private float proteins;
    private float fats;
    private float carbohydrates;
    private int calories;
    private Product[] products;
    private int countProducts;

    public ListAllowedProducts(int amountProducts, float proteins, float fats, float carbohydrates, int calories) {
        if (amountProducts < 1)
            throw new IllegalArgumentException("A amount of Products greater than 0 is required.");

        products = new Product[amountProducts];
        setProteins(proteins);
        setFats(fats);
        setCarbohydrates(carbohydrates);
        setCalories(calories);
    }

    public int getCountProducts() {
        return countProducts;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    private String isProper(Product product) {
        String strOut = "";
        if (product.getProteins() > proteins) strOut += "protein limit exceeded; ";
        if (product.getFats() > fats) strOut += "fats limit exceeded; ";
        if (product.getCarbohydrates() > carbohydrates) strOut += "carbohydrates limit exceeded; ";
        if (product.getCalories() > calories) strOut += "calories limit exceeded; ";
        return strOut;
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Object product is null.");
        if (countProducts == products.length) System.out.println("List full");
        else {
            String resultCheckProduct = isProper(product);
            if (resultCheckProduct.equals("")) products[countProducts++] = product;
            else System.out.println("Product " + product.getName() + " can't be added: " + resultCheckProduct);
        }
    }

    public void addProduct(Product... products) {
        for (Product product : products) {
            addProduct(product);
        }
    }

    public void List() {
        System.out.println("List allowed products:");
        for (Product product : products) {
            if (product != null)
                System.out.println(product.toString());
        }
    }

}
