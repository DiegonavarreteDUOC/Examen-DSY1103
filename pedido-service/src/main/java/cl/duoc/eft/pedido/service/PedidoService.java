package cl.duoc.eft.pedido.service;

import cl.duoc.eft.pedido.model.Pedido;
import cl.duoc.eft.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }

    public Pedido save(Pedido entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
