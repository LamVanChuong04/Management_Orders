package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.DiscountReq;
import com.example.tracking_order.dto.request.ShopDiscount;
import com.example.tracking_order.dto.response.DiscountRes;
import com.example.tracking_order.entity.DiscountEntity;
import com.example.tracking_order.enums.DiscountStatus;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.DiscountMapper;
import com.example.tracking_order.repository.DiscountRepository;
import com.example.tracking_order.service.IDiscountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiscountServiceImp implements IDiscountService {
    private DiscountRepository repo;
    private DiscountMapper mapper;

    @Override
    @Transactional
    public DiscountRes create(DiscountReq req) {
        DiscountEntity discount = mapper.fromCreate(req);
        repo.save(discount);
        return mapper.toResponse(discount);
    }

    @Override
    @Transactional
    public DiscountRes update(UUID id, DiscountReq req) {
        DiscountEntity discount = repo.findById(id).orElseThrow(()-> new ResourceNotfoundException());
        mapper.fromUpdate(req, discount);
        return mapper.toResponse(discount);
    }

    @Override
    public List<DiscountRes> findAll() {
        List<DiscountEntity> discounts = repo.findAll();
        return discounts.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BigDecimal getDiscountValue(ShopDiscount req) {
        if (req == null) return BigDecimal.ZERO;
        DiscountEntity discount = repo.findActiveById(req.getDiscountId())
                .orElseThrow(() -> new BusinessException("Giảm giá không hợp lệ"));

        int updateRow = repo.updateQuantity(discount.getId());
        if (updateRow == 0) {
            throw new BusinessException("Voucher đã hết.");
        }
        return discount.getDiscountValue();
    }

}
