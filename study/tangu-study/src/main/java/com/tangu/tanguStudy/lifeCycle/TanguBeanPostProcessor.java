package com.tangu.tanguStudy.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TanguBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguBeanPostProcessor postProcessBeforeInitialization --------"+beanName);
		}else if(beanName.contains("Order")){
			System.out.println("--------TanguBeanPostProcessor postProcessBeforeInitialization --------"+beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguBeanPostProcessor postProcessAfterInitialization --------"+beanName);
		}else if(beanName.contains("Order")){
			System.out.println("--------TanguBeanPostProcessor postProcessBeforeInitialization --------"+beanName);
		}
		return bean;
	}
}
