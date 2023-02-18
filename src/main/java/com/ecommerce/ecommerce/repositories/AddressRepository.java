package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserIdOrderByUpdatedAtDesc(Long userId);

    @Query(value = "SELECT * FROM user_address a WHERE a.user_id = :userId ORDER BY a.updated_at DESC LIMIT 1", nativeQuery = true)
    Address getCurrentAddress(Long userId);
}
