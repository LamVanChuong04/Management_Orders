package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.UserReq;
import com.example.tracking_order.dto.response.UserRes;
import com.example.tracking_order.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userDtoToUserEntity(UserReq userReq);
    @Mapping(target = "fullName", expression = "java(userEntity.getLastName() + \" \" + userEntity.getFirstName())")
    UserRes userEntityToUserResp(UserEntity userEntity);

    // Thêm hàm này để map đè dữ liệu từ DTO vào Entity có sẵn
    void updateUserFromDto(UserReq userReq, @MappingTarget UserEntity userEntity);
}
