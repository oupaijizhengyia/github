package com.tangu.tanguStudy.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
//BeanDefinition与BeanFactory扩展
//BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
public class TanguBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor{

	@Override
	//BeanDefinition加载
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		if(registry.containsBeanDefinition("lifeCycleBean")){
			System.out.println("-------- TanguBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry --------");
			registry.registerAlias("lifeCycleBean", "ok");
			// BeanDefinition 
			BeanDefinition lifeCycleBean = registry.getBeanDefinition("lifeCycleBean");
		}
		
		// 1.创建BeanDefinition
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClassName("com.tangu.tanguStudy.lifeCycle.LifeCycleBean");
 
		// 2.设置属性
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("syso", "ok");
        beanDefinition.setPropertyValues(propertyValues);
        
		// 3.注册beanDefinition
        registry.registerBeanDefinition("LifeCycleBeanOk", beanDefinition);
	}
	
	@Override
	//BeanDefinition加载完毕 还没实例化
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if(beanFactory.containsBean("lifeCycleBean")){
			System.out.println("-------- TanguBeanDefinitionRegistryPostProcessor postProcessBeanFactory --------");
			System.out.println("-------- BeanDefinitionRegistryPostProcessor postProcessBeanFactory before BeanFactoryPostProcessor postProcessBeanFactory --------");
		}
	}

}
