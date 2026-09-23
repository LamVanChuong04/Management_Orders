package com.example.tracking_order.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IExportExcelService {
    void exportExcelC1(HttpServletResponse response) throws IOException;
    void exportExcelC2(HttpServletResponse response) throws IOException;
}
