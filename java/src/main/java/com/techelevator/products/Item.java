package com.techelevator.products;

import java.math.BigDecimal;

public abstract class Item {

    public abstract String message();

    private String name;
    private BigDecimal price;
    private String type;
    private String code;
    private int quantity;

    public Item(String code, String name, BigDecimal price , String type , int quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.code = code;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public int getQuantity() {
        if (quantity == 0) {
            
        }
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decrementQuantity(){
        this.quantity -= 1;
    }


}

