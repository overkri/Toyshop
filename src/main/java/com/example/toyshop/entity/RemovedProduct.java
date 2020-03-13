package com.example.toyshop.entity;


import javax.persistence.*;


@Entity
@Table(name = "removed_products")

public class RemovedProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "REMOVED_PRODUCT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "REMOVED_PRODUCT_GENERATOR", allocationSize = 1, sequenceName = "removed_product_id_seq")
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "storage_removed_date")
    private java.sql.Date storageRemovedDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_location")
    private String productLocation;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private RemovedProductStatus status;

    public RemovedProduct(){}

    public RemovedProduct(long id, long productId,  java.sql.Date storageRemovedDate,  int quantity, String productLocation, RemovedProductStatus status) {
        this.id = id;
        this.productId = productId;
        this.storageRemovedDate = storageRemovedDate;
        this.quantity = quantity;
        this.productLocation = productLocation;
        this.status = status;
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

    public java.sql.Date getStorageRemovedDate() {
        return storageRemovedDate;
    }

    public void setStorageRemovedDate(java.sql.Date storageRemovedDate) {
        this.storageRemovedDate = storageRemovedDate;
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

    public RemovedProductStatus getStatus() {
        return status;
    }

    public void setStatus(RemovedProductStatus status) {
        this.status = status;
    }
}


