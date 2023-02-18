package com.ecommerce.ecommerce.models;

import com.ecommerce.ecommerce.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_address", indexes = {
        @Index(name = "userId", columnList = "userId"),
        @Index(name = "addr", columnList = "addr")
})
public class Address extends BaseEntity {
    @Column(nullable=false)
    private String addr;
    @Column(nullable=false)
    private Long userId;
    @Column(nullable=true)
    private String title;
    @Column(nullable=true)
    private Double lat;
    @Column(nullable=true)
    private Double lng;
}
