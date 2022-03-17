package com.serai.challenge.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.serai.challenge.model.Pizza;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long itemId;

    @JsonBackReference
    @JoinColumn(name="porder_id")
    @ManyToOne
    private Order order;

    @Column(name="qty")
    private int qty;

    @Column(name="pizza_id")
    private Pizza pizza;

    @Column(name="size_id")
    private Long size;

//    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")

    @JsonManagedReference
    @ElementCollection
    private List<Integer> toppings;

    public Item(){}
    public Item(Pizza pizza, int qty, Long size) {
        this.pizza = pizza;
        this.qty = qty;
        this.size = size;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public void setToppings(List<Integer> itemToppings) {
        this.toppings = itemToppings;
    }
}
