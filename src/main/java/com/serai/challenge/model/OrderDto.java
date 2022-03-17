package com.serai.challenge.model;

import java.util.List;

public class OrderDto {

    private String customer;

    private double amount;

    private List<ItemDto> orderItems;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<ItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
