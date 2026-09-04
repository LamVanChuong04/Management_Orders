package com.example.tracking_order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "product_variants")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantEntity extends BaseEntity implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(columnDefinition = "BINARY(16)")
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, name = "product_variant_price")
    private BigDecimal price;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false, length = 50)
    private String color;
    @Column(nullable = false, length = 3)
    private String size;
    @Column(nullable = false, length = 10)
    private String weight;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "productVariant")
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "productVariant")
    private List<InventoryEntity> inventoryEntities = new ArrayList<>();
}
