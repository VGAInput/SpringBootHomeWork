package com.homework.springboothomework.model;

public class Ingredient {
    private String name;
    private int amount;

    private int id;
    private String unitOfMeasure;

    public Ingredient(String name, int amount, String unitOfMeasure, int id) {

        if (name == null || amount == 0 || unitOfMeasure.isEmpty()) {
            throw new IllegalArgumentException("Параметры ингредиента не могут быть пустыми!");
        }

        this.name = name;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
