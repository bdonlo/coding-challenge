package com.serai.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serai.challenge.jpa.Item;
import com.serai.challenge.jpa.Order;
import com.serai.challenge.model.*;
import com.serai.challenge.repo.OrderRepository;
import com.serai.challenge.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceTests.class);
    @Autowired
    private OrderRepository orderRepository;

//    @Test
    public void testInsert() throws Exception {
        Order o = new Order();
        o.setCustomer("Blah");
        o.setAmount(100);
        o.setOrderItems(new ArrayList<>());
        o.getOrderItems().add(new Item(Pizza.random(), 1, Size.random().getId()));
        this.orderRepository.save(o);
    }

    @Test
    public void testFetch() throws Exception {

        //Arrange
        testInsert();

        OrderServiceImpl svc = new OrderServiceImpl(this.orderRepository);
        List<Order> orders = svc.fetchAll();
        assertTrue(orders.size()>0);
    }

    @Test
    public void testGenerateOrder() throws Exception {
        OrderServiceImpl svc = new OrderServiceImpl(this.orderRepository);
        OrderDto orderDto = svc.generateOrder();

        assertNotEquals(null, orderDto.getCustomer());
        assertNotEquals("", orderDto.getCustomer());
        assertNotEquals(orderDto.getAmount(), 0);
        assertTrue(orderDto.getOrderItems().size()>0);
        assertTrue(orderDto.getOrderItems().get(0).getQty()>0);
        assertTrue(orderDto.getOrderItems().get(0).getPizza().getPrice()>0);
        assertNotEquals(orderDto.getOrderItems().get(0).getSize(), null);

        logger.info("testGenerateOrder JSON=" + new ObjectMapper().writeValueAsString(orderDto));
    }

    @Test
    public void testPlaceOrder() throws Exception {
        OrderServiceImpl svc = new OrderServiceImpl(this.orderRepository);
        OrderDto orderDto = svc.generateOrder();

        OrderResponse res = svc.processOrder(orderDto);
        Order o = res.getOrder();

        assertNotEquals(null, o.getCustomer());
        assertNotEquals("", o.getCustomer());
        assertNotEquals(o.getAmount(), 0);
        assertTrue(o.getOrderItems().size()>0);
        assertTrue(o.getOrderItems().get(0).getQty()>0);
        assertTrue(o.getOrderItems().get(0).getPizza().getPrice()>0);
        assertNotEquals(o.getOrderItems().get(0).getSize(), null);
    }

    @Test
    public void testPlaceOrderFail() throws Exception {
        OrderServiceImpl svc = new OrderServiceImpl(this.orderRepository);
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomer(null);
        orderDto.setAmount(-1000);
        orderDto.setOrderItems(new ArrayList<>());
        OrderResponse res = svc.processOrder(orderDto);
        logger.info("Size of res.getViolations()=" + res.getViolations().size());
        assertTrue(res.getViolations().size()==3);
    }
}
