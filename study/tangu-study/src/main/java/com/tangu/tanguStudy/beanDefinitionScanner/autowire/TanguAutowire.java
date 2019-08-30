package com.tangu.tanguStudy.beanDefinitionScanner.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TanguAutowire {

	@Autowired(required=true)
	TanguBean tanguBean;
	
	@Autowired(required=true)
	TanguScanBean tanguScanBean;
	
}
