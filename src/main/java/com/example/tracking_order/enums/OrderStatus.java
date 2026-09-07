package com.example.tracking_order.enums;

public enum OrderStatus {
    PENDING,      // Chờ xác nhận
    CONFIRMED,    // Đã xác nhận
    PICKING,      // Đang lấy hàng
    SHIPPING,     // Đang giao hàng
    DELIVERED,    // Thành công
    FAILED,       // Giao hàng lỗi
    RETURNING,    // Chuyển hoàn
    REATTEMPT     // Giao lại lần 2
}
