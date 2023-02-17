package com.ecommerce.ecommerce.models;

import com.ecommerce.ecommerce.common.BaseEntity;
import com.ecommerce.ecommerce.common.DateAudit;
import com.ecommerce.ecommerce.common.Role;
import com.ecommerce.ecommerce.common.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User extends BaseEntity implements UserDetails {
    @Column(length = 100, nullable=false, unique=true)
    private String email;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String firstname;
    @Column(nullable=false)
    private String lastname;
    @Column(nullable=false)
    private String phone;
    @Column(nullable=false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
