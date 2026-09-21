package com.example.tracking_order.entity;

import com.example.tracking_order.common.BaseEntity;
import com.example.tracking_order.enums.OriginType;
import com.example.tracking_order.enums.ReturnStatus;
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
@Entity
@Table(name = "orders_return")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnEntity extends BaseEntity implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(columnDefinition = "BINARY(16)")
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, length = 20)
    private String code;
    @Column(nullable = false, length = 50)
    private String reason;

    @Enumerated(EnumType.STRING)
    private ReturnStatus returnStatus;
    @Enumerated(EnumType.STRING)
    private OriginType originType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private BigDecimal totalRefund;


    @OneToMany(mappedBy = "orderReturn")
    private List<OrderItemReturnEntity> items = new ArrayList<>();
}
