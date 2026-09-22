package com.example.tracking_order.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.example.tracking_order.dto.response.ReturnExcel;
import com.example.tracking_order.entity.OrderReturnEntity;
import com.example.tracking_order.repository.OrderReturnRepository;
import com.example.tracking_order.service.IExportExcelService;
import com.example.tracking_order.service.IOrderReturnService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExportExcelServiceImp implements IExportExcelService {
    private final IOrderReturnService service;
    @Override
    public void exportExcelC1(HttpServletResponse response) throws IOException {
        Long startTime = System.currentTimeMillis();
        // 1. get user
        List<ReturnExcel> data = service.getReturnsForExport();
        log.info("get order returns: {} ms", System.currentTimeMillis() - startTime);
        if(data.isEmpty()) return;
        // 2. process data
        String fileName = "Danh_Sach_Hoan_Tra_" + System.currentTimeMillis() + ".xlsx";
        // set header
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename*=utf-8''" + fileName);

        ExcelWriterBuilder excelWriterBuilder = EasyExcelFactory.write(response.getOutputStream(), ReturnExcel.class);
        excelWriterBuilder.registerWriteHandler(new WriteHandler() {}).excelType(ExcelTypeEnum.XLSX).sheet().doWrite(data);
        log.info("get order returns: {} ms", System.currentTimeMillis() - startTime);
    }
}
