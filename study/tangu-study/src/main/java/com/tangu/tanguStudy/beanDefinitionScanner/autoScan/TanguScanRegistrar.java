package com.tangu.tanguStudy.beanDefinitionScanner.autoScan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import com.tangu.tanguStudy.beanDefinitionScanner.TanguClassPathBeanDefinitionScanner;

public class TanguScanRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(TanguScan.class.getName()));
		TanguClassPathBeanDefinitionScanner scanner = new TanguClassPathBeanDefinitionScanner(registry);
	    if (resourceLoader != null) {
	        scanner.setResourceLoader(resourceLoader);
	    }
	    
	    Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
	    if (!Annotation.class.equals(annotationClass)) {
	        scanner.setAnnotationClass(annotationClass);
	    }
	    
	    List<String> basePackages = new ArrayList<String>();
	    for (String pkg : annoAttrs.getStringArray("basePackages")) {
	        if (StringUtils.hasText(pkg)) {
	        	basePackages.add(pkg);
	        }
	    }
	    
	    for (String pkg : annoAttrs.getStringArray("value")) {
	        if (StringUtils.hasText(pkg)) {
	        	basePackages.add(pkg);
	        }
	    }
	    
	    scanner.registerFilters();
	    scanner.doScan(StringUtils.toStringArray(basePackages));
	}

}
