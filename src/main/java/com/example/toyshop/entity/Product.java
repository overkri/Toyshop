package com.example.toyshop.entity;


import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Document(indexName = "product")
@Table(name = "products")

public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "PRODUCT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PRODUCT_GENERATOR", allocationSize = 1, sequenceName = "product_id_seq")
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @Column(name = "cost")
    private int cost;


    public Product(){}

    public Product( long id,String productName, ProductType type, int cost) {
        this.id = id;
        this.productName = productName;
        this.type = type;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(id, other.getId());
    }

}


