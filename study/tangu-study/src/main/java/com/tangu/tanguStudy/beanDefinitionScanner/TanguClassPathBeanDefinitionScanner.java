package com.tangu.tanguStudy.beanDefinitionScanner;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

public class TanguClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner{
	
	private Class<? extends Annotation> annotationClass;

	public TanguClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
	    super(registry, false);
	}
	
	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
	    this.annotationClass = annotationClass;
	}
	
	public void registerFilters() {
		boolean acceptAllInterfaces = true;
	    if (this.annotationClass != null) {
	    	// 注解过滤器
	        addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
	        acceptAllInterfaces = false;
	    }
	    if (acceptAllInterfaces) {
	        // default include filter that accepts all classes
	        addIncludeFilter(new TypeFilter() {
	            @Override
	            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
	            	// 熟悉MetadataReader的朋友可以在这里写一些骚操作
					
	                return true;
	            }
	        });
        }
	    // 黑名单
	    addExcludeFilter(new TypeFilter() {
	        @Override
	        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
	            String className = metadataReader.getClassMetadata().getClassName();
	            return className.endsWith("package-info");
	        }
	    });
	}
	
	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> set = super.doScan(basePackages);
	    return set;
	}
	
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
	    return beanDefinition.getMetadata().isIndependent();
	}

}
