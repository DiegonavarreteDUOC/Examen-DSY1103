package cl.duoc.eft.pago.service;

import cl.duoc.eft.pago.model.Pago;
import cl.duoc.eft.pago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PagoService {

    @Autowired
    private PagoRepository repository;

    public List<Pago> findAll() {
        return repository.findAll();
    }

    public Optional<Pago> findById(Long id) {
        log.info("Buscando registro por ID: {}", id);
        return repository.findById(id);
    }

    public Pago save(Pago entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
