package com.serai.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum Pizza {
    PIZZA_1("Pizza Name #1", 99.99),
    PIZZA_2("Pizza Name #2", 99.99),
    PIZZA_3("Pizza Name #3", 99.99);
    private String name;
    private double price;
    private Pizza(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    private static final List<Pizza> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Pizza random(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
