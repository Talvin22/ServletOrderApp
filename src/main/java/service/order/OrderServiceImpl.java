package service.order;

import entity.Order;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryImpl;

import java.security.PrivateKey;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;


    @Override
    public void create(Order obj) {
        orderRepository = new OrderRepositoryImpl();
        orderRepository.create(obj);

    }

    @Override
    public Optional<Order> getById(Long id) {
        orderRepository = new OrderRepositoryImpl();
        return orderRepository.getById(id);
    }

    @Override
    public Order update(Long id, Order obj) {
        orderRepository = new OrderRepositoryImpl();
        return orderRepository.update(id, obj);
    }

    @Override
    public void delete(Long id) {
        orderRepository = new OrderRepositoryImpl();
        orderRepository.delete(id);
    }
}
