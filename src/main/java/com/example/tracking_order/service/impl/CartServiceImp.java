package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.AddProductReq;
import com.example.tracking_order.dto.request.CartReq;
import com.example.tracking_order.dto.response.CartDetailRes;
import com.example.tracking_order.dto.response.CartItemRes;
import com.example.tracking_order.dto.response.CartRes;
import com.example.tracking_order.entity.*;
import com.example.tracking_order.exception.BusinessException;
import com.example.tracking_order.exception.ResourceNotfoundException;
import com.example.tracking_order.mapper.CartMapper;
import com.example.tracking_order.repository.*;
import com.example.tracking_order.service.ICartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImp implements ICartService {
    private final CartRepository repo;
    private final CartItemRepository itemRepo;
    private final ProductVariantRepository  productRepo;
    private final InventoryRepository inventoryRepo;
    private final UserRepository userRepo;
    private final CartMapper mapper;
    @Override
    @Transactional
    public CartRes create(CartReq req) {
        CartEntity cart = mapper.fromCreate(req);
        repo.save(cart);
        return mapper.toResponse(cart);
    }

    @Override
    public CartDetailRes getById(UUID userId) {
        CartEntity userCart = repo.findByUserId(userId).orElseGet(()-> createEmptyCart(userId));
        List<CartItemRes> items = new ArrayList<>();
        int totalQuantity = 0;
        if(userCart.getCartItems() != null) {
            for(CartItemEntity item : userCart.getCartItems()) {
                ProductVariantEntity variant = item.getProductVariant();
                totalQuantity += item.getQuantity();
                BigDecimal subTotal = variant.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                CartItemRes res = CartItemRes.builder()
                        //.cartItemId(item.getId())
                        .variantId(variant.getId())
                        //.productId(variant.getProduct().getId())
                        .productName(variant.getProduct().getProductName())
                        .color(variant.getColor())
                        .size(variant.getSize())
                        .price(variant.getPrice())
                        .quantity(item.getQuantity())
                        .subTotal(subTotal)
                        .build();
                items.add(res);
            }
        }
        return CartDetailRes.builder()
                .totalQuantity(totalQuantity)
                .items(items)
                .build();
    }

    @Override
    @Transactional
    public CartItemRes addToCart(CartReq req) {
        // 1. Tìm hoặc Tạo mới Cart cho User
        CartEntity cart = repo.findByUserId(req.getUserId()).orElseGet(()-> createEmptyCart(req.getUserId()));

        // 2. Kiểm tra sự tồn tại của ProductVariant
        ProductVariantEntity variant = productRepo.findById(req.getProductVariantId())
                .orElseThrow(() -> new BusinessException("Sản phẩm không tồn tại"));

        // 3. Kiểm tra sản phẩm đã có trong giỏ hàng chưa
        Optional<CartItemEntity> existingItemOpt = itemRepo
                .findByCartIdAndProductVariantId(cart.getId(), variant.getId());

        int targetQuantity = req.getQuantity();
        if (existingItemOpt.isPresent()) {
            // Nếu đã có -> Số lượng mong muốn = số lượng cũ + số lượng mới thêm
            targetQuantity += existingItemOpt.get().getQuantity();
        }

        // 4. Validate Tồn kho từ bảng Inventory
        InventoryEntity inventory = inventoryRepo.findByProductVariantId(variant.getId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy dữ liệu tồn kho"));

        if (inventory.getQuantityInStock() < targetQuantity) {
            throw new BusinessException("Số lượng trong kho không đủ (Chỉ còn "
                    + inventory.getQuantityInStock() + " sản phẩm khả dụng)");
        }

        // 5. Thêm mới hoặc Cập nhật CartItem
        CartItemEntity cartItem;
        if (existingItemOpt.isPresent()) {
            // TH1: Đã có -> Cập nhật số lượng cộng dồn
            cartItem = existingItemOpt.get();
            cartItem.setQuantity(targetQuantity);
        } else {
            // TH2: Chưa có -> Tạo CartItem mới
            cartItem = new CartItemEntity();
            cartItem.setCart(cart);
            cartItem.setProductVariant(variant);
            cartItem.setQuantity(req.getQuantity());
        }

        CartItemEntity savedItem = itemRepo.save(cartItem);

        BigDecimal subTotal = variant.getPrice().multiply(BigDecimal.valueOf(savedItem.getQuantity()));

        return CartItemRes.builder()
                //.productId(variant.getProduct().getId())
                .variantId(variant.getId())
                .productName(variant.getProduct().getProductName())
                .color(variant.getColor())
                .size(variant.getSize())
                .price(variant.getPrice())
                .quantity(savedItem.getQuantity())
                .subTotal(subTotal)
                .build();
    }


    private CartEntity createEmptyCart(UUID userId) {
        CartEntity cart = new CartEntity();
        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotfoundException());
        cart.setUser(user);
        return repo.save(cart);
    }
}
