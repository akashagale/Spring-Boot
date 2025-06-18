package com.app.ecommerce;

import com.app.ecommerce.entity.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		System.out.println("=======Spring boot=========>");
		ZonedDateTime now = ZonedDateTime.now();
		System.out.println(now);
	}
}
