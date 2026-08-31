package cl.duoc.eft.promocion.repository;

import cl.duoc.eft.promocion.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {
}
