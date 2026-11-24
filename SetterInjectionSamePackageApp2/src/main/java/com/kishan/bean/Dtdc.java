package com.kishan.bean;

import org.springframework.stereotype.Component;

@Component("DTDC")
public class Dtdc {
	public Dtdc() {
		System.out.println("Dtdc object is created..");
	}
	public void delivery() {
		System.out.println("Order id deleiverd by DTDC Service..");
		System.out.println("Order  delivered to the customer location sucessfully..");
	}

}
