package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.ShopReq;
import com.example.tracking_order.dto.response.ShopRes;
import com.example.tracking_order.entity.ShopEntity;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.mapper.ShopMapper;
import com.example.tracking_order.repository.ShopRepository;
import com.example.tracking_order.service.IShopService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopSeriviceImp implements IShopService {
    private final ShopRepository repo;
    private final ShopMapper mapper;


    @Override
    @Transactional
    public ShopRes createShop(ShopReq req) {
        ShopEntity shop = repo.findByShopName(req.getShopName());
        if(shop==null){
            ShopEntity entity = mapper.fromCreate(req);
            repo.save(entity);
            return mapper.toResponse(entity);
        }else {
            throw new BusinessException("Shop already exists");
        }
    }

    @Override
    @Transactional
    public ShopRes updateShop(UUID id, ShopReq req) {
        ShopEntity shop = repo.findById(id).orElseThrow(() -> new BusinessException("Shop not found"));
        ShopEntity shopNew =  mapper.fromUpdate(req,shop);
        repo.save(shopNew);
        return mapper.toResponse(shopNew);
    }

    @Override
    @Transactional
    public void deleteShop(UUID id) {
        ShopEntity shop = repo.findById(id).orElseThrow(() -> new BusinessException("Shop not found"));
        shop.setIsDeleted(true);
        repo.save(shop);
    }

    @Override
    public ShopRes getShop(UUID id) {
        ShopEntity shop = repo.findById(id).orElseThrow(() -> new BusinessException("Shop not found"));
        return mapper.toResponse(shop);
    }

    @Override
    public List<ShopRes> getAllShops() {
        List<ShopEntity> shops = repo.findAll();
        return shops.stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
