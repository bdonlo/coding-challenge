package com.serai.challenge.service.impl;

import com.serai.challenge.jpa.Order;
import com.serai.challenge.jpa.Item;
import com.serai.challenge.model.*;
import com.serai.challenge.repo.OrderRepository;
import com.serai.challenge.service.OrderService;
import com.serai.challenge.util.Constants;
import com.serai.challenge.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> fetchAll(){
        return this.orderRepository.findAll();

    }
    @Override
    public OrderDto generateOrder(){
        Random r = new Random(System.currentTimeMillis());
        OrderResponse o = new OrderResponse();
        OrderDto dto = new OrderDto();
        dto.setCustomer(NameGenerator.generateRandomName(10));

        List<ItemDto> itemsToAdd = new ArrayList();
        int randItem = 0;
        // Enforce non-zero
        while (randItem == 0) randItem = r.nextInt(Constants.MAX_RANDOM_ORDER_ITEM);

        for (int i=0; i<randItem; i++) {
            Pizza pizza = Pizza.random();
            ItemDto item = new ItemDto(pizza, r.nextInt(Constants.MAX_RANDOM_QTY), Size.random().getId());

            //Calculate the grand total of parent order
            dto.setAmount(new BigDecimal(dto.getAmount()).add(BigDecimal.valueOf(pizza.getPrice())).doubleValue());

            int randTopping = r.nextInt(Constants.MAX_RANDOM_TOPPING);
            List<Integer> toppingsToAdd = new ArrayList<Integer>();
            for (int j = 0; j < randTopping; j++) {
                toppingsToAdd.add(Topping.random().getId());
            }
            item.setToppings(toppingsToAdd);
            itemsToAdd.add(item);
        }
        dto.setOrderItems(itemsToAdd);
        return dto;
    }
    @Override
    public OrderResponse processOrder(OrderDto orderDto) {
        OrderResponse res = this.validate(orderDto);
        if (logger.isDebugEnabled())
            logger.debug("Violation size:" + res.getViolations().size());
        if (res.getViolations().size() == 0) {
            // no error
            OrderResponse res2 = this.placeOrder(res.getOrder());
            if (res2.getViolations().size()>0){
                res.getViolations().addAll(res2.getViolations());
            }
        }
        return res;
    }

    private OrderResponse validate(OrderDto orderDto) {
        OrderResponse res = new OrderResponse();
        Order order = new Order();
        // add violations if validate fail
        List<Violation> violations = new ArrayList<>();

        if (orderDto.getCustomer()==null || "".equals(orderDto.getCustomer())){
            if (logger.isDebugEnabled())
                logger.debug("Validate erorr: missing customer");
            violations.add(new Violation(Constants.VIOLATION_MISSING_CUSTOMER, "Missing customer"));
        } else {
            order.setCustomer(orderDto.getCustomer());
        }
        if (orderDto.getAmount()<=0){
            if (logger.isDebugEnabled())
                logger.debug("Validate erorr: wrong amount");
            violations.add(new Violation(Constants.VIOLATION_WRONG_AMOUNT, "Amount is lesser than or equal zero"));
        } else {
            order.setAmount(orderDto.getAmount());
        }

        if (orderDto.getOrderItems().size() == 0){
            if (logger.isDebugEnabled())
                logger.debug("Validate erorr: no item");
            violations.add(new Violation(Constants.VIOLATION_NO_ITEM, "No item in this order"));
        } else {
            order.setOrderItems(new ArrayList<Item>());
            for (ItemDto itemDto : orderDto.getOrderItems()){
                Item item = new Item(itemDto.getPizza(), itemDto.getQty(), itemDto.getSize());
                item.setToppings(itemDto.getToppings());
                order.getOrderItems().add(item);
            }
        }
        res.setOrder(order);
        res.setViolations(violations);
        return res;
    }

    @Override
    public OrderResponse placeOrder(Order order){

        OrderResponse res = new OrderResponse();
        try {
            logger.debug("reqParseOrder" + order.getCustomer());
            this.orderRepository.save(order);
            res.setOrder(order);
        } catch (Exception ex) {
            Violation v = new Violation(Constants.VIOLATION_SQL_ERROR, ex.getMessage());
            res.getViolations().add(v);
        }
        return res;
    }

    @Override
    public Order save(Order order){
        return this.orderRepository.save(order);
    }

}
