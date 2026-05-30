package com.miguel.api_tareas.domain.ports.out;

import com.miguel.api_tareas.domain.model.Tarea;
import java.util.List;
import java.util.Optional;

public interface TareaRepositoryPort {

    List<Tarea> findAll();

    Tarea save(Tarea tarea);

    Optional<Tarea> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Tarea> findByTitle(String title);

    List<Tarea> findByCompletada(Boolean completada);
}