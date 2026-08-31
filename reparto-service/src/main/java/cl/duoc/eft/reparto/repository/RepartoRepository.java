package cl.duoc.eft.reparto.repository;

import cl.duoc.eft.reparto.model.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartoRepository extends JpaRepository<Reparto, Long> {
}
