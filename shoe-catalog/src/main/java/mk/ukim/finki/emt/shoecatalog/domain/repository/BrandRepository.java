package mk.ukim.finki.emt.shoecatalog.domain.repository;

import mk.ukim.finki.emt.shoecatalog.domain.models.Brand;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.BrandId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, BrandId> {
}
