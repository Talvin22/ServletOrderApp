package repository.order;

import entity.Order;
import repository.BaseRepository;

import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order> {
    void create(Order obj);

    Optional<Order> getById(Long id);

    Order update(Long id, Order obj);

    void delete(Long id);
}
