package vn.edu.fpt.mss301.catalog.domain.model;

import vn.edu.fpt.mss301.catalog.domain.exception.DomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount,String currency) {
    public Money{
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("Giá tiền không thể để trống hoặc âm");
        }
        if (currency == null || currency.isEmpty()) {
            currency = "VND";
        }
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        currency = currency.trim().toUpperCase();
    }
    public static Money ofVND(BigDecimal amount) {
        return new Money(amount, "VND");
    }
}
