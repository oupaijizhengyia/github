package com.tangu.tanguStudy.lifeCycle;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

@Component
public class TanguInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguInstantiationAwareBeanPostProcessor postProcessBeforeInitialization --------"+beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguInstantiationAwareBeanPostProcessor postProcessAfterInitialization --------"+beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguInstantiationAwareBeanPostProcessor postProcessBeforeInstantiation --------"+beanName);
		}
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if(beanName.equals("lifeCycleBean")){
			System.out.println("--------TanguInstantiationAwareBeanPostProcessor postProcessAfterInstantiation --------"+beanName);
		}
		return super.postProcessAfterInstantiation(bean, beanName);
	}

}
