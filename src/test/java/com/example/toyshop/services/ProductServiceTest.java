package com.example.toyshop.services;

import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductType;
import com.example.toyshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    private static final long ID = 1;
    private static final int COST = 200;
    private static final ProductType TYPE = ProductType.DOLL;
    private static final String NAME = "RED DOLL";
    private static final int CHANGED_COST = 1000;
    private static final ProductType CHANGED_TYPE = ProductType.ACTION_FIGURE;
    private static final long PRODUCT_ID = 1L;
    private static final String DELETED = "deleted";

    static ProductService productService;

    @BeforeEach()
     void init(){
        ProductRepository productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
        Product product = getProduct();
        List<Product> products = new ArrayList<>();
        when(productRepository.findById(ID)).thenReturn(java.util.Optional.of(product));
        when(productRepository.findAll(any(Sort.class))).thenReturn(products);
        when(productRepository.save(any())).thenReturn(product);
    }

    private Product getProduct(){
        Product product = new Product();
        product.setId(ID);
        product.setCost(COST);
        product.setType(TYPE);
        product.setProductName(NAME);
        return product;
    }

    @Test
    void getAllProducts() {
        List<Product> products = new ArrayList<>();
        List<Product> returned = productService.getAllProducts();
        assertEquals(products, returned);
    }

    @Test
    void getProductById() {
        Product product = productService.getProductById(ID);
        assertEquals(ID, product.getId());
        assertEquals(COST, product.getCost());
        assertEquals(TYPE, product.getType());
        assertEquals(NAME, product.getProductName());
    }

    @Test
    void addProduct() {
        Product newProduct = getProduct();
        Product product = productService.addProduct(newProduct);
        assertEquals(newProduct.getId(), product.getId());
        assertEquals(newProduct.getCost(), product.getCost());
        assertEquals(newProduct.getType(), product.getType());
        assertEquals(newProduct.getProductName(), product.getProductName());
    }

    @Test
    void updateProduct() {
        Product product = getProduct();
        productService.addProduct(product);
        Product updatedProduct = new Product();
        updatedProduct.setType(CHANGED_TYPE);
        updatedProduct.setCost(CHANGED_COST);
        updatedProduct.setProductName(NAME);
        updatedProduct.setId(ID);
        Product changedProduct = productService.updateProduct(ID, updatedProduct);
        assertEquals(changedProduct.getId(), product.getId());
        assertEquals(changedProduct.getCost(), updatedProduct.getCost());
        assertEquals(changedProduct.getType(), updatedProduct.getType());
        assertEquals(changedProduct.getProductName(), product.getProductName());
    }

    @Test
    void deleteProduct() {
    Map<String, Boolean> response = productService.deleteProduct(PRODUCT_ID);
    assertFalse(response.isEmpty());
    assertTrue(response.get(DELETED));
    }
}