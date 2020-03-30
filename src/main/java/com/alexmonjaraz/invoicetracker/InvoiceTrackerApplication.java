package com.alexmonjaraz.invoicetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class InvoiceTrackerApplication {

	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InvoiceTrackerApplication.class, args);
	}

}
