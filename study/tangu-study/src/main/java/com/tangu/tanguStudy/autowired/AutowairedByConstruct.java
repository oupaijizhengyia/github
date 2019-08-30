package com.tangu.tanguStudy.autowired;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.tangu.tanguStudy.configurationProperties.StudyPeoperties;

import lombok.extern.slf4j.Slf4j;

/**
 * 构造函数自动装配, 仅显示声明一个带参构造函数时生效
 * @Configuration @Component @Service ... 
 */

@Slf4j
@Configuration
@SuppressWarnings("unused")
public class AutowairedByConstruct {

	/**
	 * 若声明无参构造函数, 自动装配会失效
	 * public AutowairedByConstruct(){
	 * 
	 * }  
	 */

	public AutowairedByConstruct(
			StudyPeoperties studyPeoperties, 
			@Qualifier("componentPOByName") ComponentPO componentPO,
//			ComponentPO commonComponentPO,  //建议使用ObjectProvider<ComponentPO>替代   ObjectProvider的报错能用try catch捕获
//			ObjectProvider<ComponentPO> opComponentPO //会报错
			@Qualifier("componentPO") ObjectProvider<ComponentPO> opComponentPO
			){
		log.info("--------------------------------------------------------------");
		log.info("AutowairedByConstruct construct Autowaired byType StudyPeoperties: {}, hashCode: {}", studyPeoperties, studyPeoperties.hashCode());
		log.info("AutowairedByConstruct construct Autowaired byName ComponentPO: {}, hashCode: {}", componentPO, componentPO.hashCode());
		try{
			/**
			 * ObjectProvider.class 内置三个方法   getObject(args) getIfAvailable() getIfUnique()
			 * getObject(args) 获取对象的实例，允许根据显式的指定构造器的参数去构造对象
			 * ComponentPO newComponent = opComponentPO.getObject("componentPOBy");
			 * 下面验证  getObject(args) 调用顺序  对装配的影响 
			 */
			getObjectFirst(opComponentPO);
//			getObjectLast(opComponentPO);
		}catch (Exception e) {
			log.error("opComponentPO", e);
		}
		
		/* --------------------------- ComponentPO -----------------------------*/ 
//		try{
//			log.info("AutowairedByConstruct construct Autowaired byName commonComponentPO: {}, hashCode: {}", commonComponentPO, commonComponentPO.hashCode());
//		}catch (Exception e) {
//			log.error(":{}", e);
//		}
		log.info("--------------------------------------------------------------");
	}
	
	private void getObjectFirst(ObjectProvider<ComponentPO> opComponentPO){
		ComponentPO newComponent = opComponentPO.getObject("componentPOBy");
		log.info("AutowairedByConstruct ObjectProvider opComponentPO.getObject(): {}, hashCode: {}", newComponent, newComponent.hashCode());
		log.info("AutowairedByConstruct ObjectProvider opComponentPO.getIfUnique(): {}, hashCode: {}", opComponentPO.getIfUnique(), opComponentPO.getIfUnique().hashCode());
		log.info("AutowairedByConstruct ObjectProvider opComponentPO.getIfAvailable(): {}, hashCode: {}", opComponentPO.getIfAvailable(), opComponentPO.getIfUnique().hashCode());
	}
	
	private void getObjectLast(ObjectProvider<ComponentPO> opComponentPO){
		ComponentPO newComponent = opComponentPO.getObject("componentPOBy");
		log.info("AutowairedByConstruct ObjectProvider opComponentPO.getObject(): {}, hashCode: {}", newComponent, newComponent.hashCode());
		log.info("AutowairedByConstruct ObjectProvider opComponentPO.getIfUnique(): {}, hashCode: {}", opComponentPO.getIfUnique(), opComponentPO.getIfUnique().hashCode());
		log.info("AutowairedByConstruct ObjectProvider opComponentPO.getIfAvailable(): {}, hashCode: {}", opComponentPO.getIfAvailable(), opComponentPO.getIfUnique().hashCode());
	}
}
