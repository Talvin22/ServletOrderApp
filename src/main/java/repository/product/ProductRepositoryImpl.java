package repository.product;

import config.HibernateConfig;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public void create(Product obj) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
        }

    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product update(Product obj) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
