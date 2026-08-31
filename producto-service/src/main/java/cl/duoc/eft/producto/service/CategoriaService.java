package cl.duoc.eft.producto.service;

import cl.duoc.eft.producto.model.Categoria;
import cl.duoc.eft.producto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    public Categoria save(Categoria entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
