package com.miguel.api_tareas.infrastructure.adapters.out.persistance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas") // Nombre de tu tabla en MySQL/H2
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean completada;

    // Constructor vacío obligatorio para JPA
    public TareaEntity() {}

    // Constructor completo útil para mapeos
    public TareaEntity(Long id, String title, Boolean completada) {
        this.id = id;
        this.title = title;
        this.completada = completada;
    }

    // Getters y Setters estándar para Hibernate
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Boolean getCompletada() { return completada; }
    public void setCompletada(Boolean completada) { this.completada = completada; }
}