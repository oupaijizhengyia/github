package com.tangu.tanguStudy.annotionImport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(-1000)
public class TanguImport4Configuration implements BeanNameAware{

	@PostConstruct
	public void init(){
		System.out.println("TanguImport4Configuration");
	}
	
	@Bean
	public TanguImport4 tanguImport4(){
		return new TanguImport4();
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("-----------      "+name);
	}
	
}
