package com.tangu.tanguStudy.beanDefinitionScanner.auto;


import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import com.tangu.tanguStudy.beanDefinitionScanner.TanguClassPathBeanDefinitionScanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.context.annotation.Configuration
public class TanguBeanAutoScan {

	public static class AutoConfiguredTanguBeanAutoScanRegistrar 
		implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware {
		  
		private BeanFactory beanFactory;

		private ResourceLoader resourceLoader;
		
		@Override
		public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
				BeanDefinitionRegistry registry) {
			// registry  BeanDefinition仓库
			TanguClassPathBeanDefinitionScanner scanner = new TanguClassPathBeanDefinitionScanner(registry);
	        if (this.resourceLoader != null) {
	        	scanner.setResourceLoader(this.resourceLoader);
            }
	        //这行代码报了BUG    因为和 @spring boot冲突了
//            List<String> packages = AutoConfigurationPackages.get(this.beanFactory); //base-package()
            scanner.setAnnotationClass(Tangu.class);
            scanner.registerFilters();
            scanner.doScan("com.tangu");
		}

		@Override
		public void setResourceLoader(ResourceLoader resourceLoader) {
			this.resourceLoader = resourceLoader;
		}

		@Override
		public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
			this.beanFactory = beanFactory;
		}
    }
	
	@org.springframework.context.annotation.Configuration
	//@Import 注册AutoConfiguredMapperScannerRegistrar.class到IOC容器中
	//使用 @Import 需继承 ImportBeanDefinitionRegistrar
	@Import({ AutoConfiguredTanguBeanAutoScanRegistrar.class })
	public static class TanguBeanAutoScanConfiguration {

	    @PostConstruct
	    public void afterPropertiesSet() {
	    	log.info("AutoConfiguredTanguBeanAutoScanRegistrar import");
	   }
	}
}
