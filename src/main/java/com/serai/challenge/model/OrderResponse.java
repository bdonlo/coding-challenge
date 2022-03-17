package com.serai.challenge.model;

import com.serai.challenge.jpa.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private List<Violation> violations;
    private Order order;

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderResponse() {
        this.violations = new ArrayList<>();
    }
}
