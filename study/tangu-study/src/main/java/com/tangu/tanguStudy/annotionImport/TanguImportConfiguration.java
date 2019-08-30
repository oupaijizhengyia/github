package com.tangu.tanguStudy.annotionImport;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

// TanguImport4Configuration 加了注解@Configuration 用不用@Import做导入   运行结果顺序上看没啥区别
//@Import({TanguImport1.class, TanguImport2Selector.class, TanguImport3BeanDefinitionRegistrar.class
//	,TanguImport4Configuration.class})
@Import({TanguImport1.class, TanguImport2Selector.class, TanguImport3BeanDefinitionRegistrar.class})
@Configuration
@Order(1)
public class TanguImportConfiguration {

	@PostConstruct
	public void init(){
		System.out.println("TanguImportConfiguration");
	}
}
