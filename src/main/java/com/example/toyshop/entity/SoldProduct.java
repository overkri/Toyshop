package com.example.toyshop.entity;


import javax.persistence.*;


@Entity
@Table(name = "sold_product")

public class SoldProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "SOLD_PRODUCT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SOLD_PRODUCT_GENERATOR", allocationSize = 1, sequenceName = "sold_product_id_seq")
    private long id;

    @Column(name = "order_number")
    private long orderNumber;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "sold_date")
    private java.sql.Date soldDate;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_location")
    private String productLocation;

    public SoldProduct(){}

    public SoldProduct(long id, long productId,  java.sql.Date soldDate,  int quantity, String productLocation, long orderNumber) {
        this.id = id;
        this.productId = productId;
        this.soldDate = soldDate;
        this.quantity = quantity;
        this.productLocation = productLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public java.sql.Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(java.sql.Date soldDate) {
        this.soldDate = soldDate;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }
}


