//package com.serai.challenge.jpa;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//import javax.persistence.*;
//
////@Entity
////@Table(name="porder_item_topping")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Topping {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="porder_item_topping_id")
//    private Long id;
//
//    @JsonBackReference
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name="porder_item_id")
//    private Item orderItem;
//
//    @Column(name="topping_id")
//    private Long topping;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Item getOrderItem() {
//        return orderItem;
//    }
//
//    public void setOrderItem(Item orderItem) {
//        this.orderItem = orderItem;
//    }
//
//    public Long getTopping() {
//        return topping;
//    }
//
//    public void setTopping(Long topping) {
//        this.topping = topping;
//    }
//
//    public Topping() {
//
//    }
//}
