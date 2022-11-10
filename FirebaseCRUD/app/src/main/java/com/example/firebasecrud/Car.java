package com.example.firebasecrud;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Car {
    private int id;
    private String brand;
    private boolean used;
    private double price;

    public Car() {}

    public Car(int id, String brand, boolean used, double price) {
        this.id = id;
        this.brand = brand;
        this.used = used;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
