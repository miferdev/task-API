package com.miguel.api_tareas.infrastructure.adapters.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataTareaRepository extends JpaRepository<TareaEntity, Long> {

    // Tus métodos de búsqueda personalizados trabajando con la Entidad
    List<TareaEntity> findByTitleContainingIgnoreCase(String title);

    List<TareaEntity> findByCompletada(Boolean completada);
}