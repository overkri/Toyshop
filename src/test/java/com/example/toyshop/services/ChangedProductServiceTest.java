package com.example.toyshop.services;

import com.example.toyshop.entity.*;
import com.example.toyshop.repository.AvailableProductRepository;
import com.example.toyshop.repository.ChangedProductRepository;
import com.example.toyshop.repository.LocationRepository;
import com.example.toyshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangedProductServiceTest {

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
    private static final long DATE = 2018;
    private static final long INVOICE_ID = 221;
    private static final ChangeStatus STATUS = ChangeStatus.REMOVED;
    private static final ChangeStatus CHANGED_STATUS = ChangeStatus.SOLD;





    static ChangedProductService changedProductService;

    @BeforeEach()
    void init(){
        ChangedProductRepository changedProductRepository = mock(ChangedProductRepository.class);
        AvailableProductRepository availableProductRepository = mock(AvailableProductRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        LocationRepository locationRepository = mock(LocationRepository.class);

        changedProductService = new ChangedProductService(changedProductRepository,availableProductRepository, locationRepository, productRepository );
        ChangedProduct changedProduct = getChangedProduct();
        List<ChangedProduct> changedProducts = new ArrayList<>();
        when(changedProductRepository.findById(ID)).thenReturn(java.util.Optional.of(changedProduct));
        when(changedProductRepository.findAll(any(Sort.class))).thenReturn(changedProducts);
        when(changedProductRepository.save(any())).thenReturn(changedProduct);
    }

    private ChangedProduct getChangedProduct(){
        ChangedProduct changedProduct = new ChangedProduct();
        Location location = new Location();
        Product product = new Product();
        Date date = new Date(DATE);
        product.setId(PRODUCT_ID);
        product.setCost(PRODUCT_COST);
        product.setType(PRODUCT_TYPE);
        product.setProductName(PRODUCT_NAME);
        location.setId(LOCATION_ID);
        location.setLocationName(LOCATION_NAME);
        changedProduct.setQuantity(QUANTITY);
        changedProduct.setId(ID);
        changedProduct.setProductId(product);
        changedProduct.setProductLocation(location);
        changedProduct.setStatus(STATUS);
        changedProduct.setInvoiceId(INVOICE_ID);
        changedProduct.setDate(date);
        return changedProduct;
    }


    @Test
    void getAllChangedProducts() {
        List<ChangedProduct> changedProducts = new ArrayList<>();
        List<ChangedProduct> returned = changedProductService.getAllChangedProducts();
        assertEquals(changedProducts, returned);
    }

    @Test
    void getChangedProductById() {
        ChangedProduct changedProduct = changedProductService.getChangedProductById(ID);
        Product newProduct = new Product();
        Location newLocation = new Location();
        Date date = new Date(DATE);
        newProduct.setId(PRODUCT_ID);
        newProduct.setCost(PRODUCT_COST);
        newProduct.setType(PRODUCT_TYPE);
        newProduct.setProductName(PRODUCT_NAME);
        newLocation.setId(LOCATION_ID);
        newLocation.setLocationName(LOCATION_NAME);
        assertEquals(ID, changedProduct.getId());
        assertEquals(newProduct, changedProduct.getProductId());
        assertEquals(newLocation, changedProduct.getProductLocation());
        assertEquals(QUANTITY, changedProduct.getQuantity());
        assertEquals(STATUS, changedProduct.getStatus());
        assertEquals(INVOICE_ID, changedProduct.getInvoiceId());
        assertEquals(date, changedProduct.getDate());
    }

    @Test
    void addChangedProduct() {
        ChangedProduct newProduct = getChangedProduct();
        ChangedProduct product = changedProductService.addChangedProduct(newProduct);
        assertEquals(newProduct.getId(), product.getId());
        assertEquals(newProduct.getQuantity(), product.getQuantity());
        assertEquals(newProduct.getProductId(), product.getProductId());
        assertEquals(newProduct.getProductLocation(), product.getProductLocation());
        assertEquals(newProduct.getInvoiceId(), product.getInvoiceId());
        assertEquals(newProduct.getStatus(), product.getStatus());
        assertEquals(newProduct.getDate(), product.getDate());
    }

    @Test
    void updateChangedProduct() {
        ChangedProduct product = getChangedProduct();
        changedProductService.addChangedProduct(product);
        ChangedProduct updatedProduct = new ChangedProduct();
        Product newProduct = new Product();
        Location newLocation = new Location();
        Date date = new Date(DATE);
        newProduct.setId(PRODUCT_ID);
        newProduct.setCost(PRODUCT_COST);
        newProduct.setType(PRODUCT_TYPE);
        newProduct.setProductName(PRODUCT_NAME);
        newLocation.setId(LOCATION_ID);
        newLocation.setLocationName(LOCATION_NAME);
        updatedProduct.setProductLocation(newLocation);
        updatedProduct.setQuantity(CHANGED_QUANTITY);
        updatedProduct.setProductId(newProduct);
        updatedProduct.setId(ID);
        updatedProduct.setDate(date);
        updatedProduct.setInvoiceId(INVOICE_ID);
        updatedProduct.setStatus(CHANGED_STATUS);
        ChangedProduct changedProduct = changedProductService.updateChangedProduct(ID, updatedProduct);
        assertEquals(changedProduct.getId(), updatedProduct.getId());
        assertEquals(changedProduct.getProductId(), updatedProduct.getProductId());
        assertEquals(changedProduct.getQuantity(), updatedProduct.getQuantity());
        assertEquals(changedProduct.getDate(), updatedProduct.getDate());
        assertEquals(changedProduct.getInvoiceId(), updatedProduct.getInvoiceId());
        assertEquals(changedProduct.getStatus(), updatedProduct.getStatus());
        assertEquals(changedProduct.getProductLocation(), updatedProduct.getProductLocation());

    }

    @Test
    void deleteChangedProduct() {
        Map<String, Boolean> response = changedProductService.deleteChangedProduct(PRODUCT_ID);
        assertFalse(response.isEmpty());
        assertTrue(response.get(DELETED));
    }
}