package com.vladkirov.lessons.lesson06.homework01;

public class Mountain {
    private String name;
    private String country;
    private int height;

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 4)
            throw new IllegalArgumentException("Name is empty or length < 4.");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (name == null || name.trim().length() < 4)
            throw new IllegalArgumentException("Country is empty or length < 4.");
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 100)
            throw new IllegalArgumentException("Hight mountain < 100.");
        this.height = height;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}
