package com.example.tracking_order.dto.request;

import com.example.tracking_order.enums.DiscountStatus;
import com.example.tracking_order.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DiscountReq {
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 6, max = 30, message = "Name must certain the least 6 characters")
    private String name;
    @NotEmpty(message = "Description must be not empty")
    @Size(min = 6, max = 100, message = "Name must certain the least 6 characters")
    private String description;
    @NotEmpty(message = "Statuc must be not empty")
    private DiscountStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @NotEmpty(message = "Value must be not empty")
    @Min(0)
    private BigDecimal discountValue;
    @NotEmpty(message = "Discount type must be not empty")
    private DiscountType discountType;
    @NotEmpty(message = "Quantity must be not empty")
    @Min(0)
    private Integer quantity;
}
