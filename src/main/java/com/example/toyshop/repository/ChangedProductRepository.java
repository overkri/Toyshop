package com.example.toyshop.repository;

import com.example.toyshop.entity.ChangedProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChangedProductRepository extends PagingAndSortingRepository<ChangedProduct, Long> {
    List<ChangedProduct> findAllByInvoiceId(long invoiceId)
            ;
}
