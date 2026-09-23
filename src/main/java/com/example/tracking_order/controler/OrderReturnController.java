package com.example.tracking_order.controler;

import com.alibaba.excel.EasyExcel;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.request.OrderReturnReq;
import com.example.tracking_order.dto.response.OrderRefundRes;
import com.example.tracking_order.dto.response.ReturnDetailRes;
import com.example.tracking_order.dto.response.ReturnExcel;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.enums.ReturnStatus;
import com.example.tracking_order.service.IExportExcelService;
import com.example.tracking_order.service.IOrderReturnService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order-return")
@AllArgsConstructor
@Tag(name = "Order Return Controller")
public class OrderReturnController {

    private final IOrderReturnService service;
    private final IExportExcelService excelService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> createOrderReturn(@AuthenticationPrincipal UserEntity user,
                                                             @RequestBody OrderReturnReq req) {
        UUID userId = user.getId();
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.createOrderReturn(userId, req)), HttpStatus.CREATED);
    }

    @GetMapping("/{status}")
    public ResponseEntity<BaseResponse<List<OrderRefundRes>>>  getAllOrderReturns(@RequestParam int page,
                                                                                  @RequestParam int pageSize,
                                                                                  @RequestParam(required = false) ReturnStatus status) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getOrderReturnsByStatus(pageable, status)), HttpStatus.OK);
    }

    @GetMapping("/count-pending")
    public ResponseEntity<BaseResponse<Integer>> countPendingReturns(){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.countReturnPending(ReturnStatus.Pending)), HttpStatus.OK);
    }

    @GetMapping("/total-refund")
    public ResponseEntity<BaseResponse<BigDecimal>> totalReturns(){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.totalRefund()), HttpStatus.OK);
    }

    @GetMapping("/active-return")
    public ResponseEntity<BaseResponse<Integer>> getActiveReturns(){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.countAllReturns()), HttpStatus.OK);
    }

    @GetMapping("/return-detail/{id}")
    public ResponseEntity<BaseResponse<ReturnDetailRes>> getById(@PathVariable UUID id){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getReturnDetail(id)), HttpStatus.OK);
    }

    @GetMapping("/export")
    public void downloadOrderReturns(HttpServletResponse response) throws IOException {
        excelService.exportExcelC1(response);
    }

    @GetMapping("/export2")
    public void downloadOrderReturns2(HttpServletResponse response) throws IOException {
        excelService.exportExcelC2(response);
    }


}
