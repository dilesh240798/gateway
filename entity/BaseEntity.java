package com.scb.scroe.gateway.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    public LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    public LocalDateTime updatedAt;
}
