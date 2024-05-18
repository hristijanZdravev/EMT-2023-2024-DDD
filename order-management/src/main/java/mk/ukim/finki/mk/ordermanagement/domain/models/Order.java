package mk.ukim.finki.mk.ordermanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.enums.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.mk.ordermanagement.domain.models.enums.OrderState;
import mk.ukim.finki.mk.ordermanagement.domain.models.enums.PaymentType;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderId;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderItemId;
import mk.ukim.finki.mk.ordermanagement.domain.valueobjects.Quantity;
import mk.ukim.finki.mk.ordermanagement.domain.valueobjects.Shoe;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderOn;

    @Enumerated(EnumType.STRING)
private OrderState orderState;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList = new HashSet<>();


    public Order(Instant now, Currency currency) {
        super(DomainObjectId.randomId(OrderId.class));
        this.orderOn = now;
        this.currency = currency;
    }

    protected Order() {
        super(DomainObjectId.randomId(OrderId.class));
    }

    public Money total() {
        return orderItemList.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0.0), Money::add);
    }

    public OrderItem addItem(@NonNull Shoe product_shoe, Quantity qty) {
        Objects.requireNonNull(product_shoe,"product must not be null");
        var item  = new OrderItem(product_shoe.getId(),product_shoe.getPrice(),qty);
        orderItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemList.removeIf(v->v.getId().equals(orderItemId));
    }

}
