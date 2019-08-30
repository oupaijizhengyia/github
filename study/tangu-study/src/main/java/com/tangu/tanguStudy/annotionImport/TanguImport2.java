package com.tangu.tanguStudy.annotionImport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanNameAware;

public class TanguImport2 implements BeanNameAware{

	@PostConstruct
	public void init(){
		System.out.println("TanguImport2");
	}
	
	@Override
	public void setBeanName(String name) {
		System.out.println("-----------      "+name);
	}
}
