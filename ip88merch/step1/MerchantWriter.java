package com.scb.scroe.gateway.ip88merch.step1;

import org.hibernate.SessionFactory;
import org.springframework.batch.item.database.HibernateItemWriter;

import com.scb.scroe.gateway.entity.MerchantLandingEntity;

public class MerchantWriter extends HibernateItemWriter<MerchantLandingEntity>{

	public MerchantWriter(SessionFactory sessionFactory) {
		super();
		this.setSessionFactory(sessionFactory);
		
	}
}
