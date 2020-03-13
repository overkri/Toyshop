package com.example.toyshop.repository;

import com.example.toyshop.entity.SoldProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SoldProductRepository extends PagingAndSortingRepository<SoldProduct, Long> {
}
