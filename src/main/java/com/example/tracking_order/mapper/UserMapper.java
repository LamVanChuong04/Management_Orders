package com.example.tracking_order.mapper;

import com.example.tracking_order.dto.request.UserRequest;
import com.example.tracking_order.dto.response.UserResponse;
import com.example.tracking_order.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userDtoToUserEntity(UserRequest userRequest);
    @Mapping(target = "fullName", expression = "java(userEntity.getLastName() + \" \" + userEntity.getFirstName())")
    UserResponse userEntityToUserResp(UserEntity userEntity);

    // Thêm hàm này để map đè dữ liệu từ DTO vào Entity có sẵn
    void updateUserFromDto(UserRequest userRequest, @MappingTarget UserEntity userEntity);
}
