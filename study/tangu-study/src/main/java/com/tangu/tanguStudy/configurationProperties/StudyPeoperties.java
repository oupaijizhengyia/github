package com.tangu.tanguStudy.configurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("com.tangu.study.peoperties")
public class StudyPeoperties {
	
	private int integer;
	
	private String str;
}
