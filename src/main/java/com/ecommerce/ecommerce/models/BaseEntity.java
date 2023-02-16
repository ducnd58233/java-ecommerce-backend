package com.ecommerce.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.transaction.Status.STATUS_ACTIVE;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity extends DateAudit {
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    private int status = STATUS_ACTIVE;
}
