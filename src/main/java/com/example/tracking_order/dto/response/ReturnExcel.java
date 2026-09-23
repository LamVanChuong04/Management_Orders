package com.example.tracking_order.dto.response;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.ReturnStatus;
import com.example.tracking_order.utils.OriginTypeConverter;
import com.example.tracking_order.utils.ReturnStatusConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ColumnWidth(20)
public class ReturnExcel {
    @ExcelProperty("Mã Hoàn Trả")
    private String returnCode;

    @ExcelProperty("Mã Đơn Hàng")
    private String orderCode;

    @ExcelProperty("Tên khách hàng")
    private String fullName;

    @ExcelProperty("Tiền Hoàn")
    private BigDecimal refundAmount;

    @ExcelProperty("Lý Do")
    @ColumnWidth(30)
    private String reason;

    @ExcelProperty(value = "Trạng Thái", converter = ReturnStatusConverter.class)
    private ReturnStatus status;

    @ExcelProperty("Ngày Tạo")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @ExcelProperty(value = "Origin type", converter = OriginTypeConverter.class)
    private OriginType type;
}
