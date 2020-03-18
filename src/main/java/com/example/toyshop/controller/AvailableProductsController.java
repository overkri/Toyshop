package com.example.toyshop.controller;

import com.example.toyshop.entity.AvailableProduct;
import com.example.toyshop.exceptions.IdNotFoundException;
import com.example.toyshop.services.AvailableProductService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController

@RequestMapping("/api/v4")

@Api(value = "Available product Management System")

public class AvailableProductsController {

    private final AvailableProductService availableProductService;

    public AvailableProductsController(AvailableProductService availableProductService) {
        this.availableProductService = availableProductService;
    }

    @ApiOperation(value = "View a list of available products", response = List.class)
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

    @GetMapping("/available-products")
    public List<AvailableProduct> getAllAvailableProducts() {
        return availableProductService.getAllAvailableProducts();
    }

    @ApiOperation(value = "Get an available product by Id")
    @GetMapping("/available-products/{id}")
    public ResponseEntity<AvailableProduct> getAvailableProductById(
            @PathVariable(value = "id") Long availableProductId) {
        AvailableProduct availableProduct = availableProductService.getAvailableProductById(availableProductId);
        return ResponseEntity.ok().body(availableProduct);
    }

    @ApiOperation(value = "Add an available product")
    @PostMapping("/available-products")
    public AvailableProduct addAvailableProduct(
            @ApiParam(value = "Available product object store in database table", required = true) @Valid @RequestBody AvailableProduct availableProduct) {
        return availableProductService.addAvailableProduct(availableProduct);
    }

    @ApiOperation(value = "Update an available product")
    @PutMapping("available-products/{id}")
    public ResponseEntity<AvailableProduct> updateAvailableProduct(
            @ApiParam(value = "Available product Id to update object", required = true) @PathVariable(value = "id") Long availableProductId,
            @ApiParam(value = "Update Available product object", required = true) @Valid @RequestBody AvailableProduct availableProductDetails) {
        AvailableProduct updatedAvailableProduct = availableProductService.updateAvailableProduct(availableProductId, availableProductDetails);
        return ResponseEntity.ok(updatedAvailableProduct);
    }

    @ApiOperation(value = "Delete an available product")
    @DeleteMapping("/available-products/{id}")
    public Map<String, Boolean> deleteAvailableProduct(
            @ApiParam(value = "Available product Id from which object will be deleted from database table", required = true) @PathVariable(value = "id") Long availableProductId) {
        return availableProductService.deleteAvailableProduct(availableProductId);
    }
}