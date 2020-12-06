package com.scb.scroe.gateway.repository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccessObject {
    private SessionFactory sessionFactory;

    public DatabaseAccessObject(SessionFactory sessionFactory) {
        super();
    }
}
