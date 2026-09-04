package com.example.tracking_order.service;

import com.example.tracking_order.dto.request.AddressReq;
import com.example.tracking_order.dto.response.AddressRes;

import java.util.UUID;

public interface IAddressService {
    AddressRes create(AddressReq req);
    AddressRes update(UUID id, AddressReq req);
    void delete(UUID id);
    AddressRes findById(UUID id);
}
