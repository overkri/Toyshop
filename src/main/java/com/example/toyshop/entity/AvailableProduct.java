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

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_location_id")
    private Location productLocation;

    public AvailableProduct (){}

    public AvailableProduct(long id, Product product,  int quantity,  Location productLocation) {
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        this.product = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Location getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(Location productLocation) {
        this.productLocation = productLocation;
    }
}


