package com.example.tracking_order.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.example.tracking_order.enums.ReturnStatus;

public class ReturnStatusConverter implements Converter<ReturnStatus> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return ReturnStatus.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<ReturnStatus> context) {
        ReturnStatus value = context.getValue();
        if (value == null) {
            return new WriteCellData<>("");
        }
        // hiển thị tên enum, ví dụ "Pending", "In_Transit"...
        return new WriteCellData<>(value.name());
    }


}