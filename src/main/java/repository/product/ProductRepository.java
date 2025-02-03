package repository.product;

import entity.Product;
import repository.BaseRepository;

import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product> {

    void create(Product obj);

    Optional<Product> getById(Long id);

    Product update(Long id, Product obj);

    void delete(Long id);
}
