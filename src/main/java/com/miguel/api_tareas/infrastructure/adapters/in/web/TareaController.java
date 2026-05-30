package com.miguel.api_tareas.infrastructure.adapters.in.web;

import com.miguel.api_tareas.domain.model.Tarea;
import com.miguel.api_tareas.domain.ports.in.TareaUseCase;
import com.miguel.api_tareas.infrastructure.adapters.in.web.dto.TareaRequest;
import com.miguel.api_tareas.infrastructure.adapters.in.web.dto.TareaResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    private final TareaUseCase tareaUseCase;

    public TareaController(TareaUseCase tareaUseCase) {
        this.tareaUseCase = tareaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<TareaResponse>> listarTarea() {
        List<TareaResponse> response = tareaUseCase.listarTodas().stream()
                .map(t -> new TareaResponse(t.id(), t.title(), t.completada()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TareaResponse> crearTarea(@Valid @RequestBody TareaRequest request) {
        // Al crear, pasamos null en el ID ya que lo autogenera la base de datos
        Tarea nuevaTarea = new Tarea(null, request.title(), request.completada());
        Tarea creada = tareaUseCase.crear(nuevaTarea);
        return ResponseEntity.ok(new TareaResponse(creada.id(), creada.title(), creada.completada()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> obtenerTarea(@PathVariable Long id) {
        return tareaUseCase.obtenerPorId(id)
                .map(t -> ResponseEntity.ok(new TareaResponse(t.id(), t.title(), t.completada())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponse> actualizarTarea(@PathVariable Long id, @Valid @RequestBody TareaRequest request) {
        Tarea datosNuevos = new Tarea(null, request.title(), request.completada());
        return tareaUseCase.actualizar(id, datosNuevos)
                .map(t -> ResponseEntity.ok(new TareaResponse(t.id(), t.title(), t.completada())))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        return tareaUseCase.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TareaResponse>> buscarTarea(@RequestParam String title) {
        List<TareaResponse> response = tareaUseCase.buscarPorTitulo(title).stream()
                .map(t -> new TareaResponse(t.id(), t.title(), t.completada()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<TareaResponse>> filtrarPorEstado(@RequestParam Boolean completada) {
        List<TareaResponse> response = tareaUseCase.filtrarPorEstado(completada).stream()
                .map(t -> new TareaResponse(t.id(), t.title(), t.completada()))
                .toList();
        return ResponseEntity.ok(response);
    }
}