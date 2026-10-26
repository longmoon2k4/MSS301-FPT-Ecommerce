package vn.edu.fpt.mss301.catalog.domain.model;

import lombok.Getter;
import vn.edu.fpt.mss301.catalog.domain.exception.DomainException;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Product {
    private final UUID id;
    private final SkuCode skuCode;
    private String name;
    private String description;
    private Money price;
    private int stockQuantity;
    private ProductStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    public Product(UUID id, SkuCode skuCode, String name, String description, Money price, int stockQuantity, ProductStatus status, Instant createdAt, Instant updatedAt) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Product name must not be blank");
        }
        if (stockQuantity <= 0) {
            throw new DomainException("Product stock quantity must not be negative");
        }
        this.id = id;
        this.skuCode = skuCode;
        this.name = name.trim();
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status != null ? status : ProductStatus.DISCONTINUED;
        this.createdAt = createdAt != null ? createdAt : Instant.now();
        this.updatedAt = updatedAt;
    }
    public static Product createNew(SkuCode skuCode, String name, String description, Money price, int stockQuantity) {
        ProductStatus statusInit = stockQuantity > 0 ? ProductStatus.ACTIVE : ProductStatus.OUT_OF_STOCK;
        Instant now = Instant.now();
        return new Product(UUID.randomUUID(), skuCode, name, description, price, stockQuantity, statusInit, now, now);
    }
    public void adjustStockQuantity(int amount) {
        if (this.status == ProductStatus.DISCONTINUED) {
            throw  new DomainException("Product is already discontinued");
        }
        int newStock = this.stockQuantity + amount;
        if (newStock <= 0) {
            this.status = ProductStatus.DISCONTINUED;
            throw new DomainException("Product is already discontinued");
        }
        this.stockQuantity = newStock;
        this.updatedAt = Instant.now();
    }
}
