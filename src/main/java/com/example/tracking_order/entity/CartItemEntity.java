package com.example.tracking_order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Table(name = "cart_items")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity extends BaseEntity{
    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(columnDefinition = "BINARY(16)")
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Boolean isSelected = true;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariantEntity productVariant;
}
