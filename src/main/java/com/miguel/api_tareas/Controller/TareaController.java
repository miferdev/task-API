package com.miguel.api_tareas.Controller;

import com.miguel.api_tareas.Model.Tarea;
import com.miguel.api_tareas.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tareas")
@CrossOrigin(origins = "*")
public class TareaController {
    //VARIABLE PARA PODER ACCEDER A BD
    @Autowired
    private TareaRepository tareaRep;

    //MOSTRAR TODAS LAS TAREAS
    @GetMapping
    public List<Tarea> listarTarea(){
        return tareaRep.findAll();
    }

    //CREAR TAREA
    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea){
        return tareaRep.save(tarea);
    }

    //MOSTRAR TAREA CON EL ID {id}
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id){
        return tareaRep.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ACTUALIZAR TAREA CON EL ID {id}
    @PutMapping("{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada){
        return tareaRep.findById(id)
                .map(tarea -> {
                    tarea.setTitle(tareaActualizada.getTitle());
                    tarea.setCompletada(tareaActualizada.getCompletada());
                    return ResponseEntity.ok(tareaRep.save(tarea));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){
        if (!tareaRep.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        tareaRep.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<Tarea>> buscarTarea(@RequestParam String title){
        List<Tarea> res = tareaRep.findByTitleContainingIgnoreCase(title);

        return ResponseEntity.ok(res);
    }

}
