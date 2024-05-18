package mk.ukim.finki.mk.ordermanagement.domain.repository;

import mk.ukim.finki.mk.ordermanagement.domain.models.Order;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
