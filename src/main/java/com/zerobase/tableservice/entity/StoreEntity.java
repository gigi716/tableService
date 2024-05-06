package com.zerobase.tableservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@Setter
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;

    private String location;

    private String explanation;
}
