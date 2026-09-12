package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.response.TrackRes;
import com.example.tracking_order.entity.TrackLogEnitty;
import com.example.tracking_order.mapper.TrackMapper;
import com.example.tracking_order.repository.TrackLogRepository;
import com.example.tracking_order.service.ITrackLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrackLogServiceImp implements ITrackLogService {
    private final TrackLogRepository repo;
    private final TrackMapper mapper;

    @Override
    public List<TrackRes> getTrackLog(UUID orderId) {
        List<TrackLogEnitty> logs = repo.findByOrderId(orderId);
        return logs.stream().map(mapper::toTrackRes).collect(Collectors.toList());
    }
}
