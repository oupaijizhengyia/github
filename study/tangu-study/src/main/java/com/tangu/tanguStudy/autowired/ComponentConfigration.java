package com.tangu.tanguStudy.autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfigration {

	@Bean
	public ComponentPO createComponentByTypePO(){
		return new ComponentPO(this.getClass().getName() + ":ByType");
	}
	
	@Bean("componentPOByName")
//	@Bean({"componentPOByName","componentPOByName2"})
	public ComponentPO createComponentByNamePO(){
		return new ComponentPO(this.getClass().getName() + ":ByName");
	}
	
}
