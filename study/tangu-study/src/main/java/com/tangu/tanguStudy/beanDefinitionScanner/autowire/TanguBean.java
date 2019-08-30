package com.tangu.tanguStudy.beanDefinitionScanner.autowire;

import javax.annotation.PostConstruct;

import com.tangu.tanguStudy.beanDefinitionScanner.auto.Tangu;

@Tangu
public class TanguBean {

	@PostConstruct
	public void init(){
		System.out.println("TanguBean");
	}
	
}
