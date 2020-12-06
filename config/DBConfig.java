package com.scb.scroe.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;
//avoid using spring keyword
@Configuration
public class DBConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.dialect}")
    private String dialect;

//Driver Manager
    @Bean
    public DataSource createDatasource() {
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .driverClassName(driver)
                .url(url)
                .build();
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.dialect", dialect); //DB name

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setPackagesToScan("com.sc.liquidity.scroe.gateway");
        return sessionFactory;
    }

    
     
     @Bean("commonTxnMgr")
     public PlatformTransactionManager getTransactionManager(LocalSessionFactoryBean dbSessionFactory) {
         HibernateTransactionManager transactionManager = new HibernateTransactionManager();
         transactionManager.setSessionFactory(dbSessionFactory.getObject());
         return transactionManager;
     }

}