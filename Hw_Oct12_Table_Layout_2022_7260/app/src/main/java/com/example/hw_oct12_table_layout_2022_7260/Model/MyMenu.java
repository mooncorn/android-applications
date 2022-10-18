package com.example.hw_oct12_table_layout_2022_7260.Model;

import android.graphics.Color;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class MyMenu implements Serializable {

    private String description;
    private int textColor;
    private int backgroundColor;

    public MyMenu(String description, int textColor, int backgroundColor) {
        this.description = description;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

//    public MyMenu(String description, int textColor) {
//        this.description = description;
//        this.textColor = textColor;
//        this.backgroundColor = Color.WHITE;
//    }
//
//    public MyMenu(String description) {
//        this.description = description;
//        this.textColor = Color.BLACK;
//        this.backgroundColor = Color.WHITE;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @NotNull
    @Override
    public String toString() {
        return description;
    }
}
