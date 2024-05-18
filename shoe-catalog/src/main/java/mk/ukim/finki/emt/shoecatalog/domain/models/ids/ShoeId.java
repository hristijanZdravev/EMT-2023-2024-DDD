package mk.ukim.finki.emt.shoecatalog.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ShoeId extends DomainObjectId {
    protected ShoeId(@NonNull String uuid) {
        super(uuid);
    }
}
