package mk.ukim.finki.mk.ordermanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObject;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import java.util.UUID;

public class OrderItemId extends DomainObjectId {
    protected OrderItemId() {
        super(UUID.randomUUID().toString());
    }

    public OrderItemId(@NonNull String uuid) {
        super(uuid);
    }

}
