package com.techelevator.products;

import java.math.BigDecimal;

public class Chips extends Item{

    private String name;
    private BigDecimal price;
    private String type;
    private String code;
    private int quantity;

    public Chips(String code, String name, BigDecimal price , String type , int quantity) {
        super(code, name, price, type, quantity);

    }

    @Override
    public String message() {
        return "Crunch Crunch, Yum!";
    }
}
