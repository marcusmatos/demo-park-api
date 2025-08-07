package com.mballem.demoparkapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    @UpdateTimestamp
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    public LocalDateTime getCreatedAt() {
        return dataCriacao;
    }

    public LocalDateTime getUpdatedAt() {
        return dataModificacao;
    }

    //Getters e setter
    protected void setCreatedAt(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    protected void setUpdatedAt(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
