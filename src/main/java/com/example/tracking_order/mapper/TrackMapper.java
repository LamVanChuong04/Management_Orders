package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.response.TrackRes;
import com.example.tracking_order.entity.TrackLogEnitty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.sound.midi.Track;

@Mapper(componentModel = "spring")
public interface TrackMapper {
    @Mapping(source = "updatedAt", target = "dateTime")
    TrackRes toTrackRes(TrackLogEnitty entity);
}
