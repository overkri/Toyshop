package com.example.toyshop.services;

import com.example.toyshop.entity.AvailableProduct;
import com.example.toyshop.entity.Location;
import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductType;
import com.example.toyshop.repository.AvailableProductRepository;
import com.example.toyshop.repository.ProductRepository;
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

class AvailableProductServiceTest {

    private static final long ID = 1;
    private static final long CHANGED_ID = 2;
    private static final String PRODUCT_NAME = "RED DOLL";
    private static final int QUANTITY = 10;
    private static final int CHANGED_QUANTITY = 20;
    private static final long PRODUCT_ID = 1;
    private static final int PRODUCT_COST = 1000;
    private static final ProductType PRODUCT_TYPE = ProductType.DOLL;
    private static final long LOCATION_ID = 1;
    private static final String LOCATION_NAME = "Moscow";
    private static final String DELETED = "deleted";

    static AvailableProductService availableProductService;

    @BeforeEach()
    void init(){
        AvailableProductRepository availableProductRepository = mock(AvailableProductRepository.class);
        availableProductService = new AvailableProductService(availableProductRepository);
        AvailableProduct availableProduct = getAvailableProduct();
        List<AvailableProduct> availableProducts = new ArrayList<>();
        when(availableProductRepository.findById(ID)).thenReturn(java.util.Optional.of(availableProduct));
        when(availableProductRepository.findAll(any(Sort.class))).thenReturn(availableProducts);
        when(availableProductRepository.save(any())).thenReturn(availableProduct);
    }

    private AvailableProduct getAvailableProduct(){
        AvailableProduct availableProduct = new AvailableProduct();
        Location location = new Location();
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setCost(PRODUCT_COST);
        product.setType(PRODUCT_TYPE);
        product.setProductName(PRODUCT_NAME);
        location.setId(LOCATION_ID);
        location.setLocationName(LOCATION_NAME);
        availableProduct.setQuantity(QUANTITY);
        availableProduct.setId(ID);
        availableProduct.setProduct(product);
        availableProduct.setProductLocation(location);
        return availableProduct;
    }

    @Test
    void getAllAvailableProducts() {
        List<AvailableProduct> availableProducts = new ArrayList<>();
        List<AvailableProduct> returned = availableProductService.getAllAvailableProducts();
        assertEquals(availableProducts, returned);
    }

    @Test
    void getAvailableProductById() {
        AvailableProduct availableProduct = availableProductService.getAvailableProductById(ID);
        Product newProduct = new Product();
        Location newLocation = new Location();
        newProduct.setId(PRODUCT_ID);
        newProduct.setCost(PRODUCT_COST);
        newProduct.setType(PRODUCT_TYPE);
        newProduct.setProductName(PRODUCT_NAME);
        newLocation.setId(LOCATION_ID);
        newLocation.setLocationName(LOCATION_NAME);
        assertEquals(ID, availableProduct.getId());
        assertEquals(newProduct, availableProduct.getProduct());
        assertEquals(newLocation, availableProduct.getProductLocation());
        assertEquals(QUANTITY, availableProduct.getQuantity());
    }

    @Test
    void addAvailableProduct() {
        AvailableProduct newProduct = getAvailableProduct();
        AvailableProduct product = availableProductService.addAvailableProduct(newProduct);
        assertEquals(newProduct.getId(), product.getId());
        assertEquals(newProduct.getQuantity(), product.getQuantity());
        assertEquals(newProduct.getProduct(), product.getProduct());
        assertEquals(newProduct.getProductLocation(), product.getProductLocation());
    }

    @Test
    void updateAvailableProduct() {
        AvailableProduct product = getAvailableProduct();
        availableProductService.addAvailableProduct(product);
        AvailableProduct updatedProduct = new AvailableProduct();
        Product newProduct = new Product();
        Location newLocation = new Location();
        newProduct.setId(PRODUCT_ID);
        newProduct.setCost(PRODUCT_COST);
        newProduct.setType(PRODUCT_TYPE);
        newProduct.setProductName(PRODUCT_NAME);
        newLocation.setId(LOCATION_ID);
        newLocation.setLocationName(LOCATION_NAME);
        updatedProduct.setProductLocation(newLocation);
        updatedProduct.setQuantity(CHANGED_QUANTITY);
        updatedProduct.setProduct(newProduct);
        updatedProduct.setId(CHANGED_ID);
        AvailableProduct changedProduct = availableProductService.updateAvailableProduct(ID, updatedProduct);
        assertEquals(changedProduct.getId(), product.getId());
        assertEquals(changedProduct.getProduct(), updatedProduct.getProduct());
        assertEquals(changedProduct.getQuantity(), updatedProduct.getQuantity());
        assertEquals(changedProduct.getProductLocation(), product.getProductLocation());
    }

    @Test
    void deleteAvailableProduct() {
        Map<String, Boolean> response = availableProductService.deleteAvailableProduct(PRODUCT_ID);
        assertFalse(response.isEmpty());
        assertTrue(response.get(DELETED));
    }
}