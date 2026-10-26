package vn.edu.fpt.mss301.catalog.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID productId) {
        super("Could not find product with id: " + productId);
    }
}
