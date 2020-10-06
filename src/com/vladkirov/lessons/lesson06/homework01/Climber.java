package com.vladkirov.lessons.lesson06.homework01;

public class Climber {
    private String name;
    private String adress;

    public Climber(String name, String adress) {
        setName(name);
        setAdress(adress);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 3)
            throw new IllegalArgumentException("Name is empty or length < 3.");
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        if (adress == null || adress.trim().length() < 5)
            throw new IllegalArgumentException("Name is empty or length < 5.");
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Climber{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
