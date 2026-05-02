package com.miguel.api_tareas.Repository;

import com.miguel.api_tareas.Model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
//PERMITE INTERACTUAR CON BD A TRAVÉS DE LA TABLA Y SU PK
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
