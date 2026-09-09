package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.UpdateQuantityReq;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.entity.CartItemEntity;
import com.example.tracking_order.entity.InventoryEntity;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.CartItemMapper;
import com.example.tracking_order.repository.CartItemRepository;
import com.example.tracking_order.repository.InventoryRepository;
import com.example.tracking_order.service.ICartItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImp implements ICartItemService {
    @Autowired
    private CartItemRepository repo;
    @Autowired
    private CartItemMapper mapper;

    @Autowired
    private InventoryRepository irepo;


    @Override
    public List<CartItemRes> toResponseList(List<CartItemEntity> reqs) {
        List<CartItemEntity> list = repo.findByIsDeletedFalse();
        return list.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartItemRes updateQuantity(UpdateQuantityReq req) {
        CartItemEntity entity = repo.findByCartIdAndProductVariantId(req.getCartId(), req.getProductVarianId())
                .orElseThrow(()-> new BusinessException("Cart item id not found"));
        Integer newQuantity = req.getQuantity();
        // check quantity -> xóa khỏi cartItem
        if(newQuantity == 0){
            repo.delete(entity);
            return null;
        }
        // kiem tra so luong ton kho
        UUID variantId = entity.getProductVariant().getId();
        InventoryEntity inventory = irepo.findByProductVariantId(variantId)
                .orElseThrow(()-> new ResourceNotfoundException());
        if(inventory.getQuantityInStock() < newQuantity){
            throw new RuntimeException("Số lượng tồn kho không đủ (Chỉ còn "
                    + inventory.getQuantityInStock() + " sản phẩm khả dụng)");
        }
        // cap nhat so luong trong gio hang
        entity.setQuantity(newQuantity);
        repo.save(entity);
        return mapper.toResponse(entity);
    }

}
