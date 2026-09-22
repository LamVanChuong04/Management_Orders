package com.example.tracking_order.repository;

import com.example.tracking_order.entity.TrackLogEnitty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TrackLogRepository extends JpaRepository<TrackLogEnitty, UUID> {
    @Query("select t from TrackLogEnitty t where t.order.id = :orderId order by t.updatedAt asc ")
    List<TrackLogEnitty> findByOrderId(UUID orderId);
}
