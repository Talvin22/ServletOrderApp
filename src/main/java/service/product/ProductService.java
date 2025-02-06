package service.product;

import entity.Product;
import service.BaseService;

import java.util.Optional;

public interface ProductService extends BaseService<Product> {

    void create(Product obj);

    Optional<Product> getById(Long id);

    Product update(Long id, Product obj);

    void delete(Long id);
}
