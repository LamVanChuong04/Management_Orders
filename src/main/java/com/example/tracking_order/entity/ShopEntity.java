package com.example.tracking_order.entity;

import com.example.tracking_order.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopEntity extends BaseEntity implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(columnDefinition = "BINARY(16)")
    @UuidGenerator
    private UUID id;

    private String shopName;
    private String email;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<DiscountEntity> discounts = new ArrayList<>();
}
