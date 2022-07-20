package com.shopura.Shopura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.shopura.Shopura.configuration", "com.shopura.Shopura.controller", "com.shopura.Shopura.entity", "com.shopura.Shopura.filter", "com.shopura.Shopura.repository", "com.shopura.Shopura.service", "com.shopura.Shopura.util" })
public class ShopuraApplication {



	public static void main(String[] args) {
		SpringApplication.run(ShopuraApplication.class, args);
	}

}
