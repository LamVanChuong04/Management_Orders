package com.example.tracking_order.service.impl;

import com.example.tracking_order.dto.request.OrderReq;
import com.example.tracking_order.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class TestRaceCondittion {
    private final IOrderService orderService;

    public String testOptimistic(OrderReq req) throws InterruptedException {
        Thread th1 = new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+ " is creating order");
                orderService.checkout(req);
                System.out.println(Thread.currentThread().getName()+ "created order successful with version ");
            }catch (Exception ex){
                System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
            }
        });

        Thread th2 = new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+ " is creating order");
                orderService.checkout(req);
                System.out.println(Thread.currentThread().getName()+ " created order successful with version ");
            }catch (Exception ex){
                System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
            }
        });
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        return "Test optimistic successful";
    }
}
