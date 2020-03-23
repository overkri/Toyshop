package com.example.toyshop.controller;

import com.example.toyshop.DTO.Invoice;
import com.example.toyshop.entity.ChangedProduct;
import com.example.toyshop.entity.Product;
import com.example.toyshop.exceptions.IdNotFoundException;
import com.example.toyshop.services.ChangedProductService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController

@RequestMapping("/api/v2")

@Api(value = "Changed product Management System")



public class ChangedProductsController {

    private final ChangedProductService changedProductService;

    public ChangedProductsController(ChangedProductService changedProductService) {
        this.changedProductService = changedProductService;
    }

    @ApiOperation(value = "View a list of changed products", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

    })

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleIdNotFoundException() {
        return "meters/notfound";
    }

    @GetMapping("/changed-products")
    public List<ChangedProduct> getAllChangedProducts() {
        return changedProductService.getAllChangedProducts();
    }

    @ApiOperation(value = "Get a changed product by Id")
    @GetMapping("/changed-products/{id}")
    public ResponseEntity<ChangedProduct> getChangedProductById(
            @PathVariable(value = "id") Long changedProductId){
        ChangedProduct changedProduct = changedProductService.getChangedProductById(changedProductId);
        return ResponseEntity.ok().body(changedProduct);
    }

    @ApiOperation(value = "Add a changed product")
    @PostMapping("/changed-products")
    public ChangedProduct addChangedProduct(
            @ApiParam(value = "Removed product object store in database table", required = true) @Valid @RequestBody ChangedProduct changedProduct) {
        return changedProductService.addChangedProduct(changedProduct);
    }

    @Transactional
    @ApiOperation(value = "Remove products by invoice")
    @PostMapping("/remove-product-invoice")
    public Map<Long, Boolean> removeProductInvoice(
            @ApiParam(value = "Removed product from invoice in database table", required = true) @Valid @RequestBody Invoice invoice) {
        return changedProductService.removeProductInvoice(invoice);
    }

    @Transactional
    @ApiOperation(value = "Confirm remove")
    @PostMapping("/confirm-remove")
    public boolean confirmRemove(@ApiParam(value = "Removed product from invoice in available products database table", required = true) @Valid @RequestBody long invoiceID){
        return changedProductService.confirmRemove(invoiceID);
    }

    @Transactional
    @ApiOperation(value = "Cancel remove")
    @PostMapping("/cancel-remove")
    public Map<Long, Boolean> cancelRemove(@ApiParam(value = "Removed product from invoice in available products database table", required = true) @Valid @RequestBody List<Product> productList, long invoiceID){
        return changedProductService.cancelRemove(productList, invoiceID);
    }

    @Transactional
    @ApiOperation(value = "Add products from invoice")
    @PostMapping("/add-product-invoice")
    public Map<Long, Boolean> addProductInvoice(
            @ApiParam(value = "Added product object store in database table", required = true) @Valid @RequestBody Invoice invoice){
        return changedProductService.addProductInvoice(invoice);
    }

    @Transactional
    @ApiOperation(value = "Remove sold products from invoice")
    @PostMapping("/sold-product-invoice")
    public Map<Long, Boolean> addProductSoldInvoice(
            @ApiParam(value = "Sold product object store in database table", required = true) @Valid @RequestBody Invoice invoice){
        return changedProductService.addProductSoldInvoice(invoice);
    }

    @ApiOperation(value = "Update a changed product")
    @PutMapping("/changed-product/{id}")
    public ResponseEntity<ChangedProduct> updateChangedProduct(
            @ApiParam(value = "Changed product Id to update object", required = true) @PathVariable(value = "id") Long changedProductId,
            @ApiParam(value = "Update changed product object", required = true) @Valid @RequestBody ChangedProduct changedProductDetails){
        ChangedProduct updatedChangedProduct = changedProductService.updateChangedProduct(changedProductId, changedProductDetails);
        return ResponseEntity.ok(updatedChangedProduct);
    }

    @ApiOperation(value = "Delete a changed product")
    @DeleteMapping("/change-products/{id}")
    public Map<String, Boolean> deleteChangedProduct(
            @ApiParam(value = "Changed product Id from which object will be deleted from database table", required = true) @PathVariable(value = "id") Long changedProductId) {
        return changedProductService.deleteChangedProduct(changedProductId);
    }
}