package com.example.toyshop.repository;

import com.example.toyshop.entity.AvailableProduct;
import com.example.toyshop.entity.Location;
import com.example.toyshop.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface AvailableProductRepository extends PagingAndSortingRepository<AvailableProduct, Long> {

    Optional<AvailableProduct> findByProductAndProductLocation(
            Product product,
            Location productLocation
    );
}
