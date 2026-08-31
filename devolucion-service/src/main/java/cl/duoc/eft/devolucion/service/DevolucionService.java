package cl.duoc.eft.devolucion.service;

import cl.duoc.eft.devolucion.model.Devolucion;
import cl.duoc.eft.devolucion.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository repository;

    public List<Devolucion> findAll() {
        return repository.findAll();
    }

    public Optional<Devolucion> findById(Long id) {
        return repository.findById(id);
    }

    public Devolucion save(Devolucion entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
