package cl.duoc.eft.reparto.service;

import cl.duoc.eft.reparto.model.Reparto;
import cl.duoc.eft.reparto.repository.RepartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RepartoService {

    @Autowired
    private RepartoRepository repository;

    public List<Reparto> findAll() {
        return repository.findAll();
    }

    public Optional<Reparto> findById(Long id) {
        log.info("Buscando registro por ID: {}", id);
        return repository.findById(id);
    }

    public Reparto save(Reparto entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
