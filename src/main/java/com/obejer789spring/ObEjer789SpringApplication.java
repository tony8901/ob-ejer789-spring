package com.obejer789spring;

import com.obejer789spring.entities.Laptop;
import com.obejer789spring.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObEjer789SpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObEjer789SpringApplication.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
		laptopRepository.save(new Laptop(1L, "Acer", "Aspire"));
		laptopRepository.save(new Laptop(2L, "Lenovo", "ThinkPad"));
		laptopRepository.save(new Laptop(3L, "HP", "Inspiron"));
		laptopRepository.save(new Laptop(4L, "Dell", "Latitude"));
	}

}
