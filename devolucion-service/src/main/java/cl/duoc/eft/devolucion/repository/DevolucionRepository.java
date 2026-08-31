package cl.duoc.eft.devolucion.repository;

import cl.duoc.eft.devolucion.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
}
