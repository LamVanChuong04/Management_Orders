package com.example.tracking_order.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.tracking_order.dto.response.ReturnExcel;
import com.example.tracking_order.entity.OrderReturnEntity;
import com.example.tracking_order.repository.OrderReturnRepository;
import com.example.tracking_order.service.IExportExcelService;
import com.example.tracking_order.service.IOrderReturnService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExportExcelServiceImp implements IExportExcelService {
    private final IOrderReturnService service;
    private final OrderReturnRepository repo;

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

    @Override
    public void exportExcelC2(HttpServletResponse response) throws IOException {
        int batchSize = 1000;
        UUID lastId = new UUID(0L, 0L); // UUID: 00000000-0000-0000-0000-00000000000
        boolean flag = true;

        String fileName = "Danh_Sach_Hoan_Tra_" + System.currentTimeMillis() + ".xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename*=utf-8''" + fileName);

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), ReturnExcel.class)
                .excelType(ExcelTypeEnum.XLSX)
                .autoCloseStream(false)
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet("Users").build();

        while (flag) {
            //
            Pageable pageable = PageRequest.of(0, batchSize);
            // chỉ lấy batchSize record
            List<ReturnExcel> data = service.getReturnsForExport2(lastId, pageable);
            if (data.isEmpty()) {
                flag = false;
            } else {
                excelWriter.write(data, writeSheet);
                // cập nhật lastId bằng ID cuối cùng của batch
                lastId = repo.findNextBatch(lastId, pageable)
                        .get(data.size() - 1)
                        .getId();
                log.info("lastId: {}", lastId);
            }
        }
        excelWriter.finish();
    }

}
