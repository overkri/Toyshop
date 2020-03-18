package com.example.toyshop.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "changed_products")

public class ChangedProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "CHANGED_PRODUCT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CHANGED_PRODUCT_GENERATOR", allocationSize = 1, sequenceName = "changed_product_id_seq")
    private long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_location_id")
    private Location productLocation;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ChangeStatus status;

    @Column(name = "invoice_id")
    private long invoiceId;

    public ChangedProduct(){}

    public ChangedProduct(long id, Product product,  Date date,  int quantity, Location productLocation, ChangeStatus status, long invoiceId) {
        this.id = id;
        this.product = product;
        this.date = date;
        this.quantity = quantity;
        this.productLocation = productLocation;
        this.status = status;
        this.invoiceId = invoiceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Product getProductId() {
        return product;
    }

    public void setProductId(Product productId) {
        this.product = productId;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ChangeStatus getStatus() {
        return status;
    }

    public void setStatus(ChangeStatus status) {
        this.status = status;
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

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

}


