package com.example.toyshop.repository;

import com.example.toyshop.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
