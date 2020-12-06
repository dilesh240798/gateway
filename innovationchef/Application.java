package com.innovationchef;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
public class Application {

    @Autowired
    private ShowMatrix show;

    public static void main(String... args) {
        SpringApplication.run(Application.class);
        System.out.println("Hello World");
    }

	

}