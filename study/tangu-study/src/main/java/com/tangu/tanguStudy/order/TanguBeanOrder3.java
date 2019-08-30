package com.tangu.tanguStudy.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class TanguBeanOrder3 {
	
	public TanguBeanOrder3(){
		System.out.println("TanguBeanOrder @Order1");
	}

}
