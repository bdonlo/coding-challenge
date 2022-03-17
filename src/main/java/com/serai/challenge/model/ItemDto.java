package com.serai.challenge.model;

import com.serai.challenge.jpa.Order;

import java.util.ArrayList;
import java.util.List;

public class ItemDto {

    private int qty;

    private Pizza pizza;

    private Long size;

    private List<Integer> toppings;

    public ItemDto(){
        this.toppings = new ArrayList<>();
    }
    public ItemDto(Pizza pizza, int qty, Long size) {
        this.qty = qty;
        this.pizza = pizza;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<Integer> getToppings() {
        return toppings;
    }

    public void setToppings(List<Integer> toppings) {
        this.toppings = toppings;
    }
}
