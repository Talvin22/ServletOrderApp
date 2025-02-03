package config;

import entity.Order;
import entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class HibernateConfig {
    private static Logger logger = Logger.getLogger(HibernateConfig.class.getName());
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            try {
                Configuration configuration = HibernateConfig.getConfiguration();
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Product.class);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.severe(e.getMessage());
            }

        }
        return sessionFactory;

    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(HibernateConfig.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        properties.put(Environment.JAKARTA_JDBC_DRIVER, properties.getProperty("dbDriver"));
        properties.put(Environment.JAKARTA_JDBC_URL, properties.getProperty("dbUrl"));
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, properties.getProperty("password"));
        properties.put(Environment.JAKARTA_JDBC_USER, properties.getProperty("username"));
        configuration.setProperties(properties);

        return configuration;
    }
}
