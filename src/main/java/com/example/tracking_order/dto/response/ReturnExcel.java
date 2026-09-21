package com.example.tracking_order.dto.response;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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

    @ExcelProperty("Mã Khách Hàng")
    private Long userId;

    @ExcelProperty("Tên Sản Phẩm")
    @ColumnWidth(30)
    private String productName;

    @ExcelProperty("Số Lượng")
    private Integer quantity;

    @ExcelProperty("Tiền Hoàn")
    private BigDecimal refundAmount;

    @ExcelProperty("Lý Do")
    @ColumnWidth(30)
    private String reason;

    @ExcelProperty("Trạng Thái")
    private String status;

    @ExcelProperty("Ngày Tạo")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createdAt;
}
