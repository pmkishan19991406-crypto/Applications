package com.kishan;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kishan.bean.Flipcart;

@SpringBootApplication
public class SetterInjectionApp01Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SetterInjectionApp01Application.class, args);
		Flipcart flipkart = applicationContext.getBean("flipkart", com.kishan.bean.Flipcart.class);
		flipkart.order();
		applicationContext.close();
		
	}

}
