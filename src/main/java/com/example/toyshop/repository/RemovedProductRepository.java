package com.example.toyshop.repository;

import com.example.toyshop.entity.RemovedProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RemovedProductRepository extends PagingAndSortingRepository<RemovedProduct, Long> {
}
