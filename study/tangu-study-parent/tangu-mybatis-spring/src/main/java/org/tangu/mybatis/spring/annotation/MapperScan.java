package org.tangu.mybatis.spring.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;
import org.tangu.mybatis.spring.mapper.MapperFactoryBean;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MapperScannerRegistrar.class)
// 扫描  mybatis.dao
public @interface MapperScan {

  /**
   * .dao 扫描根路径
   * same as basePackages()
   */
  String[] value() default {};

  /**
   * same as value()
   */
  String[] basePackages() default {};

  /**
   * 制定类所在的路径为根路径
   */
  Class<?>[] basePackageClasses() default {};

  /**
   * bean name 生成器
   */
  Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

  /**
   * addIncludeFilter
   * @MapperScan(annotationClass=Mapper.class)
   * 当扫描到的类上有 @Mapper 生成{@link BeanDefinition}
   */
  Class<? extends Annotation> annotationClass() default Annotation.class;

  /**
   * addIncludeFilter
   * @MapperScan(markerInterface=XXX.class)
   * 当扫描到的类上是XXX.class 生成 {@link BeanDefinition}
   */
  Class<?> markerInterface() default Class.class;

  /**
   * sqlSessionTemplate(sqlSessionFactory + 事务)
   */
  String sqlSessionTemplateRef() default "";

  /**
   * sqlSession工厂类
   */
  String sqlSessionFactoryRef() default "";

  /**
   * mapper.dao 的接口文件生成  {@link BeanDefinition}
   * BeanDefinition.setBeanClass(this.mapperFactoryBean.getClass())
   * bean注册的本质是反射, beanDefinition.getBeanClass().newInstance()
   */
  Class<? extends MapperFactoryBean> factoryBean() default MapperFactoryBean.class;

}
