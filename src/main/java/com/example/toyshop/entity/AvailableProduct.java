package com.example.toyshop.entity;


import javax.persistence.*;


@Entity
@Table(name = "available_products")

public class AvailableProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "AVAILABLE_PRODUCT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "AVAILABLE_PRODUCT_GENERATOR", allocationSize = 1, sequenceName = "available_product_id_seq")
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_location")
    private String productLocation;

    public AvailableProduct (){}

    public AvailableProduct(long id, long productId,  int quantity,  String productLocation) {
        this.productId = productId;
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String product_location) {
        this.productLocation = product_location;
    }
}


