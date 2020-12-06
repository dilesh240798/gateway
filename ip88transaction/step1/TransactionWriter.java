package com.scb.scroe.gateway.ip88transaction.step1;

import org.hibernate.SessionFactory;
import org.springframework.batch.item.database.HibernateItemWriter;

import com.scb.scroe.gateway.entity.IP88TransactionLandingEntity;

public class TransactionWriter extends HibernateItemWriter<IP88TransactionLandingEntity> {
    public TransactionWriter(SessionFactory sessionFactory) {
        super();
        setSessionFactory(sessionFactory);
    }
}