package cl.duoc.eft.carrito.service;

import cl.duoc.eft.carrito.model.Carrito;
import cl.duoc.eft.carrito.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository repository;

    public List<Carrito> findAll() {
        return repository.findAll();
    }

    public Optional<Carrito> findById(Long id) {
        return repository.findById(id);
    }

    public Carrito save(Carrito entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
