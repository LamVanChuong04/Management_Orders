package com.example.tracking_order.repository;

import com.example.tracking_order.entity.CartItemEntity;
import com.example.tracking_order.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    List<CartItemEntity> findByIsDeletedFalse();
    Optional<CartItemEntity> findByCartIdAndProductVariantId(UUID cartId, UUID productVariantId);
    //Optional<CartItemEntity> findByCartId(UUID cartId);

    Optional<ProductVariantEntity> findByProductVariantId(UUID id);
    void deleteByProductVariantId(UUID id);

    @Modifying
    @Query("delete from CartItemEntity c where c.productVariant.id in :variantIds")
    void deleteByProductVariantIds(@Param("variantIds") List<UUID> variantIds);

    // solution 1: join fetch
    @Query("select ci from CartItemEntity ci " +
            "join fetch ci.productVariant " +
            "where ci.cart.id = :cartId")
    List<CartItemEntity> findCartItems(@Param("cartId")  UUID cartId);

    @Query("select ci from CartItemEntity ci " +
            "join fetch ci.productVariant pv join fetch pv.product " +
            "where ci.cart.id = :cartId")
    List<CartItemEntity> findCartItemsAndProduct(@Param("cartId")  UUID cartId);

    // solution 2: entity graph
    @EntityGraph(attributePaths = {"productVariant"})
    List<CartItemEntity> findByCartId(@Param("cartId") UUID cartId);
}

