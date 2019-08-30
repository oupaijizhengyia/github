package com.tangu.tanguStudy.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
// 为什么要加 @Primary  本类的setBeanFactory  调用了beanFactory.getBean(LifeCycleBean.class); 
// getbean()方法等于@Autowire
// @Primary
@Data
@ConfigurationProperties("com.tangu.study.lifeCycle")
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ResourceLoaderAware, InitializingBean{
	
	@Value("123")
	private String syso;

	public LifeCycleBean() {
		System.out.println("-------- LifeCycleBean Constructor --------");
		System.out.println("-------- LifeCycleBean setter --------" + syso);
	}
	
	@Override
	public void setBeanName(String name) {
		System.out.println("-------- LifeCycleBean setBeanName --------" + name);
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("-------- LifeCycleBean setBeanClassLoader --------"+classLoader);
	}
	

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("-------- LifeCycleBean setBeanClassLoader --------"+resourceLoader);		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		LifeCycleBean b = beanFactory.getBean(LifeCycleBean.class);
//		b.setBeanName("LifeCycleBean");
		System.out.println("-------- LifeCycleBean setBeanFactory --------"+beanFactory);
	}

	@PostConstruct
	public void postConstruct(){
		System.out.println("-------- LifeCycleBean postConstruct --------");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("-------- LifeCycleBean afterPropertiesSet --------" + this.syso);
	}
	
	public void initMethod(){
		System.out.println("-------- LifeCycleBean init Method --------");
	}

	@PreDestroy
	public void preDestroy(){
		System.out.println("-------- LifeCycleBean preDestroy --------");
	}
	
	public void destroyMethod(){
		System.out.println("-------- LifeCycleBean destroyMethod --------");
	}
	
	public void setSyso(String syso) {
		System.out.println("-------- LifeCycleBean setSyso --------" + syso);
		this.syso = syso;
	}

	public String getSyso() {
		System.out.println("-------- LifeCycleBean getSyso --------");
		return syso;
	}

}
