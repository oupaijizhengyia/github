package com.tangu.tanguStudy.autowired;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.tangu.tanguStudy.autowired.ComponentPO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AnotationAutoWaireTests {
	
	@Autowired
	private ComponentPO componentPO;
	
	@Autowired
	@Qualifier("createComponentByTypePO")
	private ComponentPO componentPOByType;
	
	@Autowired
	@Qualifier("componentPOByName")
	private ComponentPO componentPOByBean;
	
	@Test
	public void AutowiredByTestTest() {
		log.info("Object:{}, hashCode:{}", componentPO, componentPO.hashCode());
		log.info("Object:{}, hashCode:{}", componentPOByType, componentPOByType.hashCode());
		log.info("Object:{}, hashCode:{}", componentPOByBean, componentPOByBean.hashCode());
		Assert.isTrue(componentPOByType != componentPOByBean, "componentPOByType  componentPOByBean 是同一个对象");
	}
	
}
