package com.tangu.tanguStudy;


import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tangu.tanguStudy.beanDefinitionScanner.autoScan.TanguScan;
import com.tangu.tanguStudy.configurationProperties.StudyPeoperties;

import java.util.TimeZone;

@SpringBootApplication
@TanguScan("com.tangu.tanguStudy.beanDefinitionScanner.autowire")
@EnableConfigurationProperties({StudyPeoperties.class,StudyPeoperties.class})
public class StudyApplication  {

	@PostConstruct
	void statred(){
		TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
	}

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
