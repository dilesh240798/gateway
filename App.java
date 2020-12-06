package com.scb.scroe.gateway;

import com.scb.scroe.gateway.controller.BatchJobController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	//keep this private, setter injection, constructor field
	@Autowired
    BatchJobController bjc;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("Hello World!");
	}

	@Override
	public void run(String... args) throws Exception {

		this.bjc.transactionJob();

//		bjc.transactionJob2();
	}
}