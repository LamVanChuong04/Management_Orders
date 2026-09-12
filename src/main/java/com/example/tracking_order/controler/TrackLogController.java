package com.example.tracking_order.controler;

import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.TrackRes;
import com.example.tracking_order.service.ITrackLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/track")
@AllArgsConstructor
@Tag(name = "Track Controller")
public class TrackLogController {
    private final ITrackLogService service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<List<TrackRes>>> getTrackLog(@PathVariable UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.getTrackLog(id)), HttpStatus.OK);
    }
}
