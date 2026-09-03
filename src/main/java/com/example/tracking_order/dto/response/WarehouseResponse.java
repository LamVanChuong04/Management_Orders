package com.example.tracking_order.dto.response;

import lombok.Data;

@Data
public class WarehouseResponse {
    private String name;
    private String province;
    private String district;
    private String ward;
    private String street;
//    private List<InventoryEntity> inventory = new ArrayList<>();
}
