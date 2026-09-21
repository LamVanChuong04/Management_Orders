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
import java.util.UUID;
@Entity
@Table(name = "order_items_return")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemReturnEntity extends BaseEntity implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(columnDefinition = "BINARY(16)")
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_return_id", nullable = false)
    private OrderReturnEntity orderReturn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_items_id", nullable = false)
    private OrderItemEntity orderItem;

    private Integer quantity;
}
