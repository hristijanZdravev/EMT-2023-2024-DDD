package mk.ukim.finki.mk.ordermanagement.domain.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderItemId;
import mk.ukim.finki.mk.ordermanagement.domain.valueobjects.ProductId;
import mk.ukim.finki.mk.ordermanagement.domain.valueobjects.Quantity;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    private Money itemPrice;

    @Column(nullable = false)
    private Quantity quantity;

    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    public OrderItem(@NonNull ProductId productId, @NonNull Money itemPrice, Quantity qty) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.itemPrice = itemPrice;
        this.quantity = qty;
    }

    protected OrderItem() {}



    public Money subtotal() {
        return itemPrice.multiply(quantity.getQuantity());
    }

}

