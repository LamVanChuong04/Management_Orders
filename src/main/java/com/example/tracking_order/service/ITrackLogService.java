package com.example.tracking_order.service;

import com.example.tracking_order.dto.response.TrackRes;

import java.util.List;
import java.util.UUID;

public interface ITrackLogService {
    List<TrackRes> getTrackLog(UUID orderId);

}
