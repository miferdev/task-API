package com.miguel.api_tareas.Repository;

import com.miguel.api_tareas.domain.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//PERMITE INTERACTUAR CON BD A TRAVÉS DE LA TABLA Y SU PK
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByTitleContainingIgnoreCase(String title);
    List<Tarea> findByCompletada(Boolean completada);
}