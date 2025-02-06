package service.product;

import entity.Product;
import repository.product.ProductRepository;
import repository.product.ProductRepositoryImpl;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public void create(Product obj) {
        productRepository = new ProductRepositoryImpl();
        productRepository.create(obj);

    }

    @Override
    public Optional<Product> getById(Long id) {
        productRepository = new ProductRepositoryImpl();
        return productRepository.getById(id);
    }

    @Override
    public Product update(Long id, Product obj) {
        productRepository = new ProductRepositoryImpl();
        return productRepository.update(id, obj);
    }

    @Override
    public void delete(Long id) {
        productRepository = new ProductRepositoryImpl();
        productRepository.delete(id);
    }
}
