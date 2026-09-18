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
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImp implements ICartService {
    private final CartRepository repo;
    private final CartItemRepository itemRepo;
    private final ProductVariantRepository  variantRepo;
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
    @Transactional
    public CartDetailRes getById(UUID userId) {

        // lấy cart theo user id
        CartEntity userCart = repo.findByUserId(userId)
                .orElseGet(() -> createEmptyCart(userId));
        // join fetch
        List<CartItemEntity> cartItems = itemRepo.findCartItemsAndProduct(userCart.getId());

        List<UUID> variantIds = cartItems.stream()
                .map(ci -> ci.getProductVariant().getId())
                .collect(Collectors.toList());

        // batch load inventory
        Map<UUID, InventoryEntity> inventoryMap = inventoryRepo.findByProductVariantIdIn(variantIds).stream()
                .collect(Collectors.toMap(inv -> inv.getProductVariant().getId(), inv -> inv));

        int totalQuantity = 0;
        List<CartItemRes> items = new ArrayList<>();

        for (CartItemEntity item : cartItems) {
            ProductVariantEntity variant = item.getProductVariant();
            InventoryEntity inventory = inventoryMap.get(variant.getId());
            String statusInventory = inventory.getQuantityInStock() < item.getQuantity()
                    ? "SOLD OUT" : "IN STOCK";

            totalQuantity += item.getQuantity();

            items.add(CartItemRes.builder()
                    .variantId(variant.getId())
                    .productName(variant.getProduct().getProductName())
                    .color(variant.getColor())
                    .size(variant.getSize())
                    .unitPrice(variant.getPrice())
                    .quantity(item.getQuantity())
                    .statusInventory(statusInventory)
                    .build());
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
        String statusInventory = new String();
        // 2. Kiểm tra sự tồn tại của ProductVariant
        ProductVariantEntity variant = variantRepo.findById(req.getProductVariantId())
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
            statusInventory = "SOLD OF STOCK";
        }
        statusInventory = "IN STOCK";
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

        return CartItemRes.builder()
                .variantId(variant.getId())
                .productName(variant.getProduct().getProductName())
                .color(variant.getColor())
                .size(variant.getSize())
                .unitPrice(variant.getPrice())
                .quantity(savedItem.getQuantity())
                .statusInventory(statusInventory)
                .build();
    }


    private CartEntity createEmptyCart(UUID userId) {
        CartEntity cart = new CartEntity();
        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotfoundException());
        cart.setUser(user);
        return repo.save(cart);
    }
}
