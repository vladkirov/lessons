package com.vladkirov.lessons.lesson06.homework02;

public class Product {
    private String name;
    private float proteins;
    private float fats;
    private float carbohydrates;
    private int calories;

    public Product(String name) {
        setName(name);
    }

    public Product(String name, float proteins, int calories) {
        setName(name);
        setProteins(proteins);
        setCalories(calories);
    }

    public Product(String name, float proteins, float fats, int calories) {
        setName(name);
        setProteins(proteins);
        setFats(fats);
        setCalories(calories);
    }

    public Product(String name, float proteins, float fats, float carbohydrates, int calories) {
        setName(name);
        setProteins(proteins);
        setFats(fats);
        setCarbohydrates(carbohydrates);
        setCalories(calories);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 3)
            throw new IllegalArgumentException("Name is empty or length < 3.");
        this.name = name;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        if (proteins < 0)
            throw new IllegalArgumentException("Negative value proteins.");
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        if (fats < 0)
            throw new IllegalArgumentException("Negative value fats.");
        this.fats = fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        if (carbohydrates < 0)
            throw new IllegalArgumentException("Negative value carbohydrates.");
        this.carbohydrates = carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        if (calories < 0)
            throw new IllegalArgumentException("Negative value calories.");
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "name='" + name + '\'' +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", calories=" + calories +
                '}';
    }
}
