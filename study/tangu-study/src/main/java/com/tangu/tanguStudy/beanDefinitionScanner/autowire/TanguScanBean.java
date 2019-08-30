package com.tangu.tanguStudy.beanDefinitionScanner.autowire;

import javax.annotation.PostConstruct;

public class TanguScanBean {
	
	@PostConstruct
	public void init(){
		System.out.println("TanguScanBean");
	}

}