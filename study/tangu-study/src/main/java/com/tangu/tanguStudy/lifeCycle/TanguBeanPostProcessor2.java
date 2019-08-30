package com.tangu.tanguStudy.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TanguBeanPostProcessor2 implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguBeanPostProcessor2 postProcessBeforeInitialization --------"+beanName);
			if(bean instanceof LifeCycleBean){
				// ........
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguBeanPostProcessor2 postProcessAfterInitialization --------"+beanName);
			if(bean instanceof LifeCycleBean){
				// ........
			}
		}
		return bean;
	}
}
