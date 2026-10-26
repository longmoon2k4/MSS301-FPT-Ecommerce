package vn.edu.fpt.mss301.catalog.domain.repository;

import vn.edu.fpt.mss301.catalog.domain.model.Product;
import vn.edu.fpt.mss301.catalog.domain.model.SkuCode;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    boolean existsBySkuCode(SkuCode skuCode);
}
