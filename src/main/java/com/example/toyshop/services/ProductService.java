package com.example.toyshop.services;

import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductType;
import com.example.toyshop.exceptions.IdNotFoundException;
import com.example.toyshop.repository.ProductRepository;
import com.example.toyshop.elasticSearchRepository.ProductSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    public static final String MESSAGE = "Product was not found for this id :: ";

    @Autowired
    public ProductService(@NonNull ProductRepository productRepository, @NonNull ProductSearchRepository productSearchRepository) {
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
    }

    private final ProductRepository productRepository;

    private final ProductSearchRepository productSearchRepository;

    /**
     * Returns list of all products from database table
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Get a product by Id
     * @param productId a long value of id needed to get
     * @return Product object with needed id if present
     */
    public Product getProductById(Long productId) {
        return  productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + productId));
    }

    /**
     * Adds a Product object to products table
     * @param product an example of object
     * @return saved product to further put it to response entity
     */
    public Product addProduct(Product product) {
        productSearchRepository.save(product);
        return productRepository.save(product);
    }

    /**
     * updates existing product using long id to identify product and changes it characteristics with values from product Details
     * @param productId an id of product that needs to be changed
     * @param productDetails an object of Product type with new values
     * @return saved product
     */
    public Product updateProduct(Long productId,Product productDetails)  {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + productId));
        product.setProductName(productDetails.getProductName());
        product.setType(productDetails.getType());
        product.setCost(productDetails.getCost());
        productSearchRepository.save(product);
        return productRepository.save(product);

    }

    /**
     * Deletes  product using given Id from products table and returns a hash map response
     * @param productId long value of id of product that needs to be deleted
     * @return hash map with responses like : "deleted, true"
     */
    public Map<String, Boolean> deleteProduct(Long productId)
    {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + productId));
        productRepository.delete(product);
        productSearchRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Product> findProductsByName(String name){
        return productSearchRepository.findByProductName(name);
    }

    /**
     *
     * @param type
     * @return
     */
    public List<Product> findProductsBType(ProductType type){
        return productSearchRepository.findByType(type);
    }
}
