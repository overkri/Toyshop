package com.example.toyshop.repository;

import com.example.toyshop.entity.AddedProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddedProductRepository extends PagingAndSortingRepository<AddedProduct, Long> {
}
