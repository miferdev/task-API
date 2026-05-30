package com.miguel.api_tareas.infrastructure.adapters.out.persistence;

import com.miguel.api_tareas.domain.model.Tarea;
import com.miguel.api_tareas.domain.ports.out.TareaRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class JpaTareaRepositoryAdapter implements TareaRepositoryPort {

    private final SpringDataTareaRepository jpaRepository;

    public JpaTareaRepositoryAdapter(SpringDataTareaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    // Convertidor: De Entidad JPA a Record de Dominio
    private Tarea toDomain(TareaEntity entity) {
        return new Tarea(entity.getId(), entity.getTitle(), entity.getCompletada());
    }

    // Convertidor: De Record de Dominio a Entidad JPA
    private TareaEntity toEntity(Tarea domain) {
        TareaEntity entity = new TareaEntity();
        entity.setId(domain.id()); // Sintaxis de Record
        entity.setTitle(domain.title());
        entity.setCompletada(domain.completada());
        return entity;
    }

    @Override
    public List<Tarea> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Tarea save(Tarea tarea) {
        TareaEntity saved = jpaRepository.save(toEntity(tarea));
        return toDomain(saved);
    }

    @Override
    public Optional<Tarea> findById(Long id) { return jpaRepository.findById(id).map(this::toDomain); }

    @Override
    public boolean existsById(Long id) { return jpaRepository.existsById(id); }

    @Override
    public void deleteById(Long id) { jpaRepository.deleteById(id); }

    @Override
    public List<Tarea> findByTitle(String title) {
        return jpaRepository.findByTitleContainingIgnoreCase(title).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Tarea> findByCompletada(Boolean completada) {
        return jpaRepository.findByCompletada(completada).stream().map(this::toDomain).toList();
    }
}