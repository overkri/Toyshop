package com.example.toyshop.repository;

import com.example.toyshop.entity.Location;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
    Optional<Location> findById(long locationId);
}
