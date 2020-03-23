package com.example.toyshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sold_invoice_info")
public class SoldInvoiceInfo {


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "SOLD_INFO_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SOLD_INFO_GENERATOR", allocationSize = 1, sequenceName = "sold_info_id_seq")
    private long id;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "sum")
    private int sum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_location_id")
    private Location productLocation;

    @Column(name = "invoice_id")
    private long invoiceId;

    public SoldInvoiceInfo(Date date, int sum, Location productLocation, long invoiceId) {
        this.date = date;
        this.sum = sum;
        this.productLocation = productLocation;
        this.invoiceId = invoiceId;
    }

    public SoldInvoiceInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
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