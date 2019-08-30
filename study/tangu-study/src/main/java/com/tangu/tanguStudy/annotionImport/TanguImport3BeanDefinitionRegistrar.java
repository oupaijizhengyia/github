package com.tangu.tanguStudy.annotionImport;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

@Order(-1000)
public class TanguImport3BeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TanguImport3.class);
		System.out.println("TanguImport3BeanDefinitionRegistrar");
	    registry.registerBeanDefinition("tanguImport3", rootBeanDefinition);
	}

}
