package cl.duoc.eft.devolucion.controller;

import cl.duoc.eft.devolucion.model.Devolucion;
import cl.duoc.eft.devolucion.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devolucions")
public class DevolucionController {

    @Autowired
    private DevolucionService service;

    @GetMapping
    public ResponseEntity<List<Devolucion>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Devolucion> create(@RequestBody Devolucion entity) {
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> update(@PathVariable Long id, @RequestBody Devolucion entity) {
        return service.findById(id).map(existing -> {
            entity.setId(existing.getId());
            return ResponseEntity.ok(service.save(entity));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
