package com.tangu.tanguStudy.beanDefinitionScanner.autoScan;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TanguScanRegistrar.class)
public @interface TanguScan {

	String[] value() default {};
	
	String[] basePackages() default {};
	
	Class<? extends Annotation> annotationClass() default Annotation.class;
	
}