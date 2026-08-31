package cl.duoc.eft.inventario.service;

import cl.duoc.eft.inventario.model.Inventario;
import cl.duoc.eft.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repository;

    public List<Inventario> findAll() {
        return repository.findAll();
    }

    public Optional<Inventario> findById(Long id) {
        return repository.findById(id);
    }

    public Inventario save(Inventario entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
