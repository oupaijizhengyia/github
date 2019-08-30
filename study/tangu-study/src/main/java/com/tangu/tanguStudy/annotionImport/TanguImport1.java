package com.tangu.tanguStudy.annotionImport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanNameAware;

public class TanguImport1 implements BeanNameAware{

	@PostConstruct
	public void init(){
		System.out.println("TanguImport1");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("-----------      "+name);
	}
}
