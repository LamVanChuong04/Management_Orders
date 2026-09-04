package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final IOrderService service;

    @PostMapping()
    public BaseResponse<OrderRes> create(@RequestBody OrderReq req) {
        return BaseResponse.ofSuccess(service.create(req));
    }

    @GetMapping()
    public BaseResponse<List<OrderRes>> getAllOrders(@RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam(defaultValue = "id") String sort,
                                                     @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(sortDirection, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return BaseResponse.ofSuccess(service.findAll(pageable));
    }
}
