package com.zerobase.tableservice.dto;

import com.zerobase.tableservice.entity.StoreEntity;
import lombok.Data;

@Data
public class Store {

    private String storeName;

    private String location;

    private String explanation;

    public StoreEntity toEntity() {
        return StoreEntity.builder()
                .storeName(this.storeName)
                .location(this.location)
                .explanation(this.explanation)
                .build();

    }
}
