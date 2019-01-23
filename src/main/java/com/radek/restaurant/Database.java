package com.radek.restaurant;

import com.radek.restaurant.hibernate.Menu;
import com.radek.restaurant.hibernate.MenuItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Optional;

public class Database {

    private static Database instance;
    private static SessionFactory sessionFactory;
    private static Session session;

    public Database() {


        var serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        var metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        sessionFactory = metadata.buildSessionFactory();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void close() {
        if (session != null) {
            session.close();
        }
    }


    public Optional<Menu> getMenuByName(String name) {
        try {
            session = sessionFactory.openSession();

            var builder = session.getCriteriaBuilder();

            var query = builder.createQuery(Menu.class);

            var root = query.from(Menu.class);

            query.select(root).where(
                    builder.equal(root.get("name"), name)
            );

            return session.createQuery(query).uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close();
        }

        return Optional.empty();
    }

    public Optional<MenuItem> getItemByName(String name) {
        try {
            session = sessionFactory.openSession();

            var builder = session.getCriteriaBuilder();

            var query = builder.createQuery(MenuItem.class);

            var root = query.from(MenuItem.class);

            query.select(root).where(
                    builder.equal(root.get("name"), name)
            );

            return session.createQuery(query).uniqueResultOptional();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return Optional.empty();
    }
}
