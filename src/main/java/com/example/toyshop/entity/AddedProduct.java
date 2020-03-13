package com.example.toyshop.entity;


import javax.persistence.*;


@Entity
@Table(name = "added_products")

public class AddedProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "ADDED_PRODUCT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ADDED_PRODUCT_GENERATOR", allocationSize = 1, sequenceName = "added_product_id_seq")
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "storage_added_date")
    private java.sql.Date storageAddedDate;


    @Column(name = "quantity")
    private int quantity;


    @Column(name = "product_location")
    private String productLocation;


    public AddedProduct() {
    }

    public AddedProduct(long id, long productId, java.sql.Date storageAddedDate,  int quantity,  String productLocation) {
        this.id = id;
        this.productId = productId;
        this.storageAddedDate = storageAddedDate;
        this.quantity = quantity;
        this.productLocation = productLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public java.sql.Date getStorageAddedDate() {
        return storageAddedDate;
    }

    public void setStorageAddedDate(java.sql.Date storageAddedDate) {
        this.storageAddedDate = storageAddedDate;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

}


