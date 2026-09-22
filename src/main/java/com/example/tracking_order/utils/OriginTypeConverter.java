package com.example.tracking_order.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.ReturnStatus;

public class OriginTypeConverter implements Converter<OriginType> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return OriginType.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<OriginType> context) {
        OriginType value = context.getValue();
        if (value == null) {
            return new WriteCellData<>("");
        }
        // hiển thị tên enum, ví dụ "Pending", "In_Transit"...
        return new WriteCellData<>(value.name());
    }
}
