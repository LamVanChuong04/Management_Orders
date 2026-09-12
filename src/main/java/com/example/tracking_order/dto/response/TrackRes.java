package com.example.tracking_order.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TrackRes {
    private String location;
    private String note;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String updatedBy;
}
