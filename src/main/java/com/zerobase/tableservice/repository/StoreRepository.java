package com.zerobase.tableservice.repository;

import com.zerobase.tableservice.dto.Store;
import com.zerobase.tableservice.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity,Long> {
    boolean existsByStoreName(String storeName);

    void deleteByStoreName(String storeName);

    Optional<StoreEntity> findById(Long id);

}
