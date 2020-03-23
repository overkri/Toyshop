package com.example.toyshop.controller;

import com.example.toyshop.entity.Product;
import com.example.toyshop.exceptions.IdNotFoundException;
import com.example.toyshop.services.ProductService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;



public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @ApiOperation(value = "View a list of products", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

    })

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "meters/notfound";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @ApiOperation(value = "Get a product by Id")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable(value = "id") Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    @ApiOperation(value = "Add a product")
    @PostMapping("/products")
    public Product addProduct(
            @ApiParam(value = "Product object store in database table", required = true) @Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @ApiOperation(value = "Update a product")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @ApiParam(value = "Product Id to update object", required = true) @PathVariable(value = "id") Long productId,
            @ApiParam(value = "Update product object", required = true) @Valid @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(productId, productDetails);
        return ResponseEntity.ok(updatedProduct);

    }

    @ApiOperation(value = "Delete a product")
    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(
            @ApiParam(value = "Product Id from which object will be deleted from database table", required = true) @PathVariable(value = "id") Long productId) {
        return productService.deleteProduct(productId);
    }
}