package repository.product;

import config.HibernateConfig;
import entity.Product;
import org.eclipse.tags.shaded.org.apache.xpath.res.XPATHErrorResources_ru;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.logging.Logger;

public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class.getName());

    @Override
    public void create(Product obj) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();



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
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            logger.severe(e.getMessage());
        }
    }
}
