package com.miguel.api_tareas.domain.service;

import com.miguel.api_tareas.domain.model.Tarea;
import com.miguel.api_tareas.domain.ports.in.TareaUseCase;
import com.miguel.api_tareas.domain.ports.out.TareaRepositoryPort;
import java.util.List;
import java.util.Optional;

public class TareaService implements TareaUseCase {

    private final TareaRepositoryPort repositoryPort;

    public TareaService(TareaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Tarea> listarTodas() { return repositoryPort.findAll(); }

    @Override
    public Tarea crear(Tarea tarea) { return repositoryPort.save(tarea); }

    @Override
    public Optional<Tarea> obtenerPorId(Long id) { return repositoryPort.findById(id); }

    @Override
    public boolean eliminar(Long id) {
        if (!repositoryPort.existsById(id)) return false;
        repositoryPort.deleteById(id);
        return true;
    }

    @Override
    public Optional<Tarea> actualizar(Long id, Tarea tareaActualizada) {
        return repositoryPort.findById(id).map(tareaExistente -> {
            // Como Tarea es un record, creamos una nueva instancia inmutable
            Tarea conCambios = new Tarea(
                    tareaExistente.id(),          // Mantenemos el ID original de la BD
                    tareaActualizada.title(),     // Tomamos el nuevo título
                    tareaActualizada.completada() // Tomamos el nuevo estado
            );
            return repositoryPort.save(conCambios);
        });
    }

    @Override
    public List<Tarea> buscarPorTitulo(String title) { return repositoryPort.findByTitle(title); }

    @Override
    public List<Tarea> filtrarPorEstado(Boolean completada) { return repositoryPort.findByCompletada(completada); }
}