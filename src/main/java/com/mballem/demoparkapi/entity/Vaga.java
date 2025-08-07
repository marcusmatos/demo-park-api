package com.mballem.demoparkapi.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vagas")
@Getter
@Setter
@NoArgsConstructor

class Vaga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "codigo", nullable = false, length = 4)
    private String codigo;
    @Column(name = "status", nullable = false, length = 25)
    private String status;
    @Column(name = "criadoPor")
    private String criadoPor;
    @Column(name = "dataCriacao")
    private String dataCriacao;
    @Column(name = "dataModificacao")
    private String dataModificacao;
    @Column(name = "modificadoPor")
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return Objects.equals(id, vaga.id) && Objects.equals(codigo, vaga.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}
