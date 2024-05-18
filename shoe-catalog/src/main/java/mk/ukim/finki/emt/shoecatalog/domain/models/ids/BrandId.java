package mk.ukim.finki.emt.shoecatalog.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class BrandId extends DomainObjectId {
    protected BrandId(@NonNull String uuid) {
        super(uuid);
    }
}