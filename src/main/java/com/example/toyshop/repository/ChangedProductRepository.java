package com.example.toyshop.repository;

import com.example.toyshop.DTO.ChangedProductProjection;
import com.example.toyshop.entity.ChangedProduct;
import com.example.toyshop.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;

public interface ChangedProductRepository extends PagingAndSortingRepository<ChangedProduct, Long> {
    List<ChangedProduct> findAllByInvoiceId(long invoiceId);

    <Optional> ChangedProduct findByProductIdAndInvoiceId(Product product, long invoiceId);

    @Query("SELECT p.productLocation.locationName as locationName, p.product.productName as productName, p.date as date, p.quantity as quantity, p.product.cost as cost FROM ChangedProduct p  WHERE p.status = 'SOLD' ORDER BY p.productLocation.id ASC")

    List<ChangedProductProjection> getSoldProductOrderByProductLocation();

    @Query("SELECT p.productLocation.locationName as location_name, p.product.productName as product_name, p.date as date, p.quantity as quantity, p.product.cost as cost FROM ChangedProduct p  WHERE p.status = 'REMOVED' AND p.date>:minimumDate AND p.date<:maximumDate")

    List<ChangedProductProjection> getRemovedProductsOrderByDate(Date minimumDate, Date maximumDate);

}
