package com.mballem.demoparkapi.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    public void setCreatedAt(Object entity) {
        if (entity instanceof BaseEntity auditavel) {
            LocalDateTime now = LocalDateTime.now();
            auditavel.setCreatedAt(now);
            auditavel.setUpdatedAt(now);
        }
    }

    @PreUpdate
    public void setUpdatedAt(Object entity) {
        if (entity instanceof BaseEntity auditavel) {
            auditavel.setUpdatedAt(LocalDateTime.now());
        }
    }
}
