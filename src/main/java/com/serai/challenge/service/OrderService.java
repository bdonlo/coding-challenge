package com.serai.challenge.service;

import com.serai.challenge.jpa.Order;
import com.serai.challenge.model.OrderDto;
import com.serai.challenge.model.OrderRequest;
import com.serai.challenge.model.OrderResponse;

public interface OrderService {
    public OrderDto generateOrder();
    public OrderResponse processOrder(OrderDto orderDto);
    public Order save(Order order);
    public OrderResponse placeOrder(Order order);
}
