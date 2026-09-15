package com.example.tracking_order.common;

import com.example.tracking_order.entity.OrderEntity;
import com.example.tracking_order.entity.UserEntity;
import com.example.tracking_order.enums.OrderStatus;
import com.example.tracking_order.repository.OrderRepository;
import com.example.tracking_order.service.IMailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ScheduleJob {
    private final OrderRepository repo;
    private final IMailService service;
    //@Scheduled(fixedDelay = 1000)
    public void sendEmail() {
        List<OrderEntity> orShipping = repo.findByStatus(OrderStatus.SHIPPING);
        for (OrderEntity order : orShipping) {
            UserEntity user = order.getUser();
            service.sendMail(user.getEmail(), "Xác nhận đơn hàng #" + order.getId(),
                    "Cảm ơn bạn đã đặt hàng.");
        }

        List<OrderEntity> orDelivered = repo.findByStatus(OrderStatus.DELIVERED);
        for (OrderEntity order : orShipping) {
            UserEntity user = order.getUser();
            service.sendMail(user.getEmail(), "Đơn hàng #" + order.getId() + " đã giao thành công",
                    "Đơn hàng của bạn đã được giao. Cảm ơn bạn đã mua sắm!");
        }
    }
}
