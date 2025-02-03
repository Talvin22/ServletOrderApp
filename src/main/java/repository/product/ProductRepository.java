package repository.product;

import entity.Product;
import repository.BaseRepository;

public interface ProductRepository extends BaseRepository<Product> {

    void create(Product obj);

    Product getById(Long id);

    Product update(Product obj);

    void delete(Long id);
}
