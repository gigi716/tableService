package com.zerobase.tableservice.controller;

import com.zerobase.tableservice.dto.Store;
import com.zerobase.tableservice.entity.StoreEntity;
import com.zerobase.tableservice.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    // 매니저 권한을 가진 회원만 매장을 등록할수 있음
    @PostMapping("/reg")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> registerStore(@RequestBody Store store) {
        StoreEntity result = this.storeService.register(store);
        return ResponseEntity.ok(result);
    }

    // 매니저 권한을 가진 회원만 매장을 수정할수 있음
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateStore(@RequestBody Store store, @PathVariable Long id) {
        Optional<StoreEntity> result = this.storeService.updateStore(store, id);
        return ResponseEntity.ok(result);
    }

    // 매니저 권한을 가진 회원만 매장을 삭제할수 있음
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteStore(@RequestBody String storeName) {
        this.storeService.deleteStore(storeName);
    }

}
