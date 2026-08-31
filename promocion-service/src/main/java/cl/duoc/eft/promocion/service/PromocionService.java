package cl.duoc.eft.promocion.service;

import cl.duoc.eft.promocion.model.Promocion;
import cl.duoc.eft.promocion.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionService {

    @Autowired
    private PromocionRepository repository;

    public List<Promocion> findAll() {
        return repository.findAll();
    }

    public Optional<Promocion> findById(Long id) {
        return repository.findById(id);
    }

    public Promocion save(Promocion entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
