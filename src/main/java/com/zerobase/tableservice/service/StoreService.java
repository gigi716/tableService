package com.zerobase.tableservice.service;

import com.zerobase.tableservice.dto.Store;
import com.zerobase.tableservice.entity.StoreEntity;
import com.zerobase.tableservice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    // 매장 등록
    public StoreEntity register(Store store) {
        // 매장명 중복 체크
        boolean exists = this.storeRepository.existsByStoreName(store.getStoreName());
        if (exists) {
            throw new RuntimeException("이미 사용중인 매장명 입니다.");
        }

        // 객체로 반환하여 DB에 저장
        StoreEntity save = this.storeRepository.save(store.toEntity());

        return save;
    }

    // 매장 정보 업데이트
    public Optional<StoreEntity> updateStore(Store store, Long id) {
        Optional<StoreEntity> storeEntity = this.storeRepository.findById(id);

        storeEntity.get().setStoreName(store.getStoreName());
        storeEntity.get().setLocation(store.getLocation());
        storeEntity.get().setExplanation(store.getExplanation());

        return storeEntity;
    }

    // 매장 삭제
    public void deleteStore(String storeName) {
        // 매장명을 통해 매장 삭제
        this.storeRepository.deleteByStoreName(storeName);

        boolean exists = this.storeRepository.existsByStoreName(storeName);
        if (exists) {
            throw new RuntimeException("이미 삭제된 매장 입니다.");
        }

    }

}
