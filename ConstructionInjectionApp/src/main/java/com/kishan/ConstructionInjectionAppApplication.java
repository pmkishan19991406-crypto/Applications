package com.kishan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kishan.beans.Amazon;

@SpringBootApplication
public class ConstructionInjectionAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ConstructionInjectionAppApplication.class, args);
		Amazon amazon = applicationContext.getBean("amazon", Amazon.class);
		amazon.order();
		
		applicationContext.close();
	}

}
