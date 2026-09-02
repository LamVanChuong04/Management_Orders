package com.example.tracking_order.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CategoryResponse {
    private UUID id;
    private String name;
    private String parentId;
    private List<ProductResponse> products;
}
