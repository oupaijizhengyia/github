package com.tangu.tanguStudy.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class TanguBeanOrder1 {
	
	public TanguBeanOrder1(){
		System.out.println("TanguBeanOrder @Order3");
	}

}
