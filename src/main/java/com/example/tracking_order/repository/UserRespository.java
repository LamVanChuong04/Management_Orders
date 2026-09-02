package com.example.tracking_order.repository;

import com.example.tracking_order.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRespository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findById(UUID id);
    // page: kết quả return
    // pageable: input (size, page)
    @Query("select u from UserEntity u where u.isDeleted = false")
    Page<UserEntity> findAll(Pageable pageable);

    @Query("select u from UserEntity u where u.isDeleted = false")
    List<UserEntity> findAll();


}
