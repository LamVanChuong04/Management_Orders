package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.request.OrderReviewReq;
import com.example.tracking_order.dto.response.OrderRes;
import com.example.tracking_order.dto.response.OrderReviewRes;
import com.example.tracking_order.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final IOrderService service;

    @PostMapping("/checkout")
    public ResponseEntity<BaseResponse<OrderRes>> create(@RequestBody OrderReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<OrderRes>>> getAllOrders(@RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam(defaultValue = "id") String sort,
                                                     @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(sortDirection, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll(pageable)),  HttpStatus.OK);
    }
    @PostMapping("/checkout/review")
    public ResponseEntity<BaseResponse<OrderReviewRes>> checkout(@RequestBody OrderReviewReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.review(req)), HttpStatus.OK);
    }
}
