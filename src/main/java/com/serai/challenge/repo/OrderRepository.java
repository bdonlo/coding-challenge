package com.serai.challenge.repo;

import com.serai.challenge.jpa.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

}
