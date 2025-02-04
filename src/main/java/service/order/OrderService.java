package service.order;

import entity.Order;
import service.BaseService;

import java.util.Optional;

public interface OrderService extends BaseService<Order> {
    void create(Order obj);

    Optional<Order> getById(Long id);

    Order update(Long id, Order obj);

    void delete(Long id);
}
