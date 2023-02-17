package com.ecommerce.ecommerce.models;

import com.ecommerce.ecommerce.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User extends BaseEntity {
    @Column(nullable=false)
    private String firstname;
    @Column(nullable=false)
    private String lastname;
    @Column(nullable=false)
    private String phone;
    @Column(nullable=false)
    private String address;
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
}
