package com.tangu.tanguStudy.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class TanguBeanOrder2 {
	
	public TanguBeanOrder2(){
		System.out.println("TanguBeanOrder @Order2");
	}

}
