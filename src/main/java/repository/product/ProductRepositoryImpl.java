package repository.product;

import config.HibernateConfig;
import entity.Order;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class.getName());

    @Override
    public void create(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Order orderFromProduct = product.getOrder();
            if (orderFromProduct == null || orderFromProduct.getId() == null) {
                throw new IllegalArgumentException("Product must be associated with an existing order.");
            }
            Order order = session.get(Order.class, orderFromProduct.getId());  //change it
            if (order == null) {
                throw new IllegalArgumentException("Order with ID " + orderFromProduct.getId() + " not found.");
            }
            product.setOrder(order);
            if (order.getProducts() == null) {
                order.setProducts(new ArrayList<>());
            }
            order.getProducts().add(product);
            session.save(product);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public Optional<Product> getById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);

            return Optional.of(product);
        } catch (Exception e) {
            transaction.rollback();
            logger.severe(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Product update(Long id, Product obj) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setName(obj.getName());
            product.setCost(obj.getCost());
            product.setOrder(obj.getOrder());
            session.update(product);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            logger.severe(e.getMessage());
        }
        return obj;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            logger.severe(e.getMessage());
        }
    }
}
