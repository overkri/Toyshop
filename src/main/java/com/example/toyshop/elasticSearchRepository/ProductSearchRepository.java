package com.example.toyshop.elasticSearchRepository;

import com.example.toyshop.entity.Product;
import com.example.toyshop.entity.ProductType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByProductName(String productName);

    List<Product> findByType(ProductType type);
}
