package com.kishan.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("amazon")
public class Amazon {
	private Dtdc dtdc;
	
	@Autowired
	public Amazon(Dtdc dtdc) {
		this.dtdc=dtdc;
		System.out.println("Amazon Object is created..");
		
	}
	
	public void order() {
		System.out.println("Order is placed in the amazon..");
		dtdc.deliver();
	}

}
