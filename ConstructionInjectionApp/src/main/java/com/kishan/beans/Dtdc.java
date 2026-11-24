package com.kishan.beans;

import org.springframework.stereotype.Component;

@Component("dtdc")
public class Dtdc {

	public Dtdc() {
		System.out.println("DTDC Object is created..");
	}
	public void deliver() {
		System.out.println("Oder is delivered by DTDC service..");
		System.out.println("Order delivered to customer location");
	}
}
