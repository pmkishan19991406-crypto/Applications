package com.kishan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.beans.Flipcart;

@SpringBootApplication(scanBasePackages = {"com.beans", "com.kishan"})
public class SetterInjectionApp01Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SetterInjectionApp01Application.class, args);
		Flipcart flipkart = applicationContext.getBean("flipkart", Flipcart.class);
		flipkart.order();
		applicationContext.close();
		
	}

}
