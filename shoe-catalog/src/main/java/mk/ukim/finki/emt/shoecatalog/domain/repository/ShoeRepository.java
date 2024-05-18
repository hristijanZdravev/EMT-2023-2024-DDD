package mk.ukim.finki.emt.shoecatalog.domain.repository;

import mk.ukim.finki.emt.shoecatalog.domain.models.Shoe;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ShoeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeRepository extends JpaRepository<Shoe, ShoeId> {
}
