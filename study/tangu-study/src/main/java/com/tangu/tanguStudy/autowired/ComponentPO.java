package com.tangu.tanguStudy.autowired;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.tangu.tanguStudy.configurationProperties.StudyPeoperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
//@Component("tanguComponent")
public class ComponentPO {
	
	private String name;
	
	private StudyPeoperties studyPeoperties;
	
	public ComponentPO(){
		this.name = "ComponentPO";
		log.info("--------------------------------------------------------------");
		log.info("ComponentPO construct hashCode:{}", this.hashCode());
	}
	
	public ComponentPO(String configration){
		this.name = configration;
		log.info("--------------------------------------------------------------");
		log.info("ComponentPO construct: {}, hashCode:{}", configration, this.hashCode());
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("ComponentPO postConstruct hashCode:{}",  this.hashCode());
		log.info("--------------------------------------------------------------");
	}
	
}
