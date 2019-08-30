package com.tangu.tanguStudy.lifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleConfiguration {
	
	@Bean(initMethod="initMethod", destroyMethod="destroyMethod")
	public LifeCycleBean lifeCycleBean1() {
		LifeCycleBean l = new LifeCycleBean();
		return l;
	}
	
	@Bean(initMethod="initMethod", destroyMethod="destroyMethod")
	public LifeCycleBean lifeCycleBean2() {
		LifeCycleBean l = new LifeCycleBean();
		return l;
	}
	
}
