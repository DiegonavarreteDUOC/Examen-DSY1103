package cl.duoc.eft.promocion.controller;

import cl.duoc.eft.promocion.model.Promocion;
import cl.duoc.eft.promocion.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promocions")
public class PromocionController {

    @Autowired
    private PromocionService service;

    @GetMapping
    public ResponseEntity<List<Promocion>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocion> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Promocion> create(@RequestBody Promocion entity) {
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> update(@PathVariable Long id, @RequestBody Promocion entity) {
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
