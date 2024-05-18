package mk.ukim.finki.mk.ordermanagement.services.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.mk.ordermanagement.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.mk.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import mk.ukim.finki.mk.ordermanagement.domain.models.Order;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderId;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderItemId;
import mk.ukim.finki.mk.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.mk.ordermanagement.services.OrderService;
import mk.ukim.finki.mk.ordermanagement.services.forms.OrderForm;
import mk.ukim.finki.mk.ordermanagement.services.forms.OrderItemForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final Validator validator;

    public OrderServiceImpl(OrderRepository orderRepository, Validator validator) {
        this.orderRepository = orderRepository;
        this.validator = validator;
    }

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getProduct(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItems().forEach(item -> order.addItem(item.getProduct(), item.getQuantity()));
        return order;
    }
}
