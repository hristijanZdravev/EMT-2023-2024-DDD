package mk.ukim.finki.mk.ordermanagement.services;

import mk.ukim.finki.mk.ordermanagement.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.mk.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import mk.ukim.finki.mk.ordermanagement.domain.models.Order;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderId;
import mk.ukim.finki.mk.ordermanagement.domain.models.ids.OrderItemId;
import mk.ukim.finki.mk.ordermanagement.services.forms.OrderForm;
import mk.ukim.finki.mk.ordermanagement.services.forms.OrderItemForm;


import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId id);

    void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException;



}

