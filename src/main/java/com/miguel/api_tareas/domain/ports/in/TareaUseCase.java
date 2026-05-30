package com.miguel.api_tareas.domain.ports.in;

import com.miguel.api_tareas.domain.model.Tarea;
import java.util.List;
import java.util.Optional;

public interface TareaUseCase {

    List<Tarea> listarTodas();

    Tarea crear(Tarea tarea);

    Optional<Tarea> obtenerPorId(Long id);

    Optional<Tarea> actualizar(Long id, Tarea tarea);

    boolean eliminar(Long id);

    List<Tarea> buscarPorTitulo(String title);

    List<Tarea> filtrarPorEstado(Boolean completada);
}