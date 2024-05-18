package mk.ukim.finki.emt.shoecatalog.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.BrandId;
import mk.ukim.finki.emt.shoecatalog.domain.models.ids.ShoeId;

@Entity
public class Brand extends AbstractEntity<BrandId> {

    private  String name;

    private  String description;


    protected Brand() {
        super(DomainObjectId.randomId(BrandId.class));
    }

}
