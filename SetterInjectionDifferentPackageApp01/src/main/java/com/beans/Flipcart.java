package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("flipkart")
public class Flipcart {
	
	private Dtdc dtdc;

	public Flipcart() {
		System.out.println("Flipcart Object is Created..");
	}
	@Autowired
	public void setDtdc(Dtdc dtdc) {
		this.dtdc = dtdc;
	}
	
	public void order() {
		System.out.println("Order is placed in Flipcart Application..");
		dtdc.delivery();
	}
	

}
