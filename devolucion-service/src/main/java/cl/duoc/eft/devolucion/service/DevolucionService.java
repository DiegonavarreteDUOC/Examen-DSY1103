package cl.duoc.eft.devolucion.service;

import cl.duoc.eft.devolucion.model.Devolucion;
import cl.duoc.eft.devolucion.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository repository;

    public List<Devolucion> findAll() {
        return repository.findAll();
    }

    public Optional<Devolucion> findById(Long id) {
        log.info("Buscando registro por ID: {}", id);
        return repository.findById(id);
    }

    public Devolucion save(Devolucion entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
