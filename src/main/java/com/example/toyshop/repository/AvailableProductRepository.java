package com.example.toyshop.repository;

import com.example.toyshop.entity.AvailableProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AvailableProductRepository extends PagingAndSortingRepository<AvailableProduct, Long> {
}
