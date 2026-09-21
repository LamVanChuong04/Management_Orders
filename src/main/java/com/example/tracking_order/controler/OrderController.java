package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.request.OrderReviewReq;
import com.example.tracking_order.dto.request.UpdateStatusReq;
import com.example.tracking_order.dto.response.*;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.service.IOrderService;
import com.example.tracking_order.service.impl.TestRaceCondittion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@Tag(name = "Order Controller")
public class OrderController {
    private final IOrderService service;
    private final TestRaceCondittion race;

    @Operation(method = "POST", summary = "Add new order", description = "Send a request via this API to create new order")
    @PostMapping("/checkout")
    public ResponseEntity<BaseResponse<String>> create(@AuthenticationPrincipal UserEntity user, @RequestBody OrderReq req) {
        UUID id = user.getId();
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.checkout(id, req)), HttpStatus.CREATED);
    }

    @Operation(method = "GET", summary = "Get all order", description = "Get all order and pageable")
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

    @Operation(method = "POST", summary = "Checkout review order", description = "Send a request via this API to checkout review order")
    @PostMapping("/sumary")
    public ResponseEntity<BaseResponse<OrderReviewRes>> sumary(@AuthenticationPrincipal UserEntity user) {
        UUID id = user.getId();
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.sumaryOrder(id)), HttpStatus.OK);
    }

    // update status order
    @PatchMapping("/update-status/{id}")
    public ResponseEntity<BaseResponse<?>> update(@RequestBody @Valid UpdateStatusReq req, @PathVariable UUID id) {
        service.updateStatus(id, req);
        return new ResponseEntity<>(BaseResponse.ofSuccess("UPDATED"), HttpStatus.OK);
    }
    // get detail order
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderRes>> getOrder(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getOrderDetail(id)),HttpStatus.OK);
    }

    @GetMapping("/order-dashboard")
    public ResponseEntity<BaseResponse<OrderDBRes>> getDashboard() {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getDB()), HttpStatus.OK);
    }

    @GetMapping("/recent-order")
    public ResponseEntity<BaseResponse<List<OrderRecentRes>>> getRecentOrder(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllRecentOrder(pageable)), HttpStatus.OK);
    }

    @GetMapping("/pending-order")
    public ResponseEntity<BaseResponse<List<OrderConfirmRes>>> getPendingOrder(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getAllPendingOrder(pageable)), HttpStatus.OK);
    }
    @GetMapping("/statistic/{date}")
    public ResponseEntity<BaseResponse<StatisticRes>> getPendingOrder(@PathVariable LocalDate date) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getStatistic(date)), HttpStatus.OK);
    }



    @Operation(method = "POST", summary = "Add new order", description = "Send a request via this API to create new order")
    @PostMapping("/optimistic")
    public ResponseEntity<BaseResponse<?>> testOptimistic(@AuthenticationPrincipal UserEntity user, @RequestBody OrderReq req) throws InterruptedException {
        UUID id = user.getId();
        return new ResponseEntity<>(BaseResponse.ofSuccess(race.testOptimistic(id, req)), HttpStatus.CREATED);
    }
}
