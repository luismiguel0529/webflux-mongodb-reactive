package com.java.reactive.repository;

import com.java.reactive.dto.ProductDto;
import com.java.reactive.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Optional<Flux<ProductDto>> findByPriceBetween(Range<Double> priceRange);
}
