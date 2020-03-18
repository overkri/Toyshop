package com.example.toyshop.services;

import com.example.toyshop.entity.AvailableProduct;
import com.example.toyshop.exceptions.IdNotFoundException;
import com.example.toyshop.repository.AvailableProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvailableProductService {

    public static final String MESSAGE = "Product was not found for this id :: ";

    @Autowired
    public AvailableProductService(@NonNull AvailableProductRepository availableProductRepository) {
        this.availableProductRepository = availableProductRepository;
    }

    private final AvailableProductRepository availableProductRepository;

    /**
     * Returns list of all products from database table
     * @return list of all products
     */
    public List<AvailableProduct> getAllAvailableProducts() {
        return (List<AvailableProduct>) availableProductRepository.findAll();
    }

    /**
     * Get a product by Id
     * @param availableProductId a long value of id needed to get
     * @return AvailableProduct object with needed id if present
     */
    public AvailableProduct getAvailableProductById(Long availableProductId) {
        return availableProductRepository.findById(availableProductId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + availableProductId));
    }

    /**
     * Adds a AvailableProduct object to products table
     * @param availableProduct an example of object
     * @return saved product to further put it to response entity
     */
    public AvailableProduct addAvailableProduct(AvailableProduct availableProduct) {
        return availableProductRepository.save(availableProduct);
    }

    /**
     * updates existing product using long id to identify product and changes it characteristics with values from available product Details
     * @param availableProductId      an id of product that needs to be changed
     * @param availableProductDetails an object of AvailableProduct type with new values
     * @return saved availableProduct
     */
    public AvailableProduct updateAvailableProduct(Long availableProductId, AvailableProduct availableProductDetails) {
        AvailableProduct availableProduct = availableProductRepository.findById(availableProductId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + availableProductId));
        availableProduct.setProductLocation(availableProductDetails.getProductLocation());
        availableProduct.setQuantity(availableProductDetails.getQuantity());
        availableProduct.setProduct(availableProductDetails.getProduct());
        return availableProductRepository.save(availableProduct);

    }

    /**
     * Deletes  product using given Id from available products table and returns a hash map response
     * @param availableProductId long value of id of product that needs to be deleted
     * @return hash map with responses like : "deleted, true"
     */
    public Map<String, Boolean> deleteAvailableProduct(Long availableProductId) {
        AvailableProduct availableProduct = availableProductRepository.findById(availableProductId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + availableProductId));
        availableProductRepository.delete(availableProduct);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
