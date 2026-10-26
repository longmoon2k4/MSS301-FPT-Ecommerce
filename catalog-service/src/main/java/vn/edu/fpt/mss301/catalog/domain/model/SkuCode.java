package vn.edu.fpt.mss301.catalog.domain.model;

import vn.edu.fpt.mss301.catalog.domain.exception.DomainException;

import java.util.regex.Pattern;

public record SkuCode(String value) {
    private static final Pattern SKU_PATTERN = Pattern.compile("^[A-Z0-9]{3,10}-[A-Z0-9]{3,10}$");
    public SkuCode {
        if (value == null || value.isEmpty()) {
            throw new DomainException("Mã SKU không được để trống");
        }
        String normalized = value.trim().toUpperCase();
        if (!SKU_PATTERN.matcher(normalized).matches()) {
            throw new DomainException("Mã SKU phải có định dạng A1B-XXX");
        }
        value = normalized;
    }
}
