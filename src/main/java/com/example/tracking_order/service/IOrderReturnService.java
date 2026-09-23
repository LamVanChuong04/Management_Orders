package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.OrderReturnReq;
import com.example.tracking_order.dto.response.OrderRefundRes;
import com.example.tracking_order.dto.response.ReturnDetailRes;
import com.example.tracking_order.dto.response.ReturnExcel;
import com.example.tracking_order.enums.ReturnStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IOrderReturnService {
    ReturnDetailRes createOrderReturn(UUID userId, OrderReturnReq req);

    Page<OrderRefundRes> getOrderReturnsByStatus(Pageable pageable, ReturnStatus status);

    int countReturnPending(ReturnStatus status);
    BigDecimal totalRefund();
    int countAllReturns();

    ReturnDetailRes getReturnDetail(UUID returnId);

    List<ReturnExcel> getReturnsForExport();

    List<ReturnExcel> getReturnsForExport2(UUID id, Pageable pageable);
}
