package org.tangu.mybatis.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 命名空间处理器 for the MyBatis namespace.
 * 命名空间 是 spring.xml 的一个概念
 * 
 * NamespaceHandlerSupport spring 扩展点
 */
public class NamespaceHandler extends NamespaceHandlerSupport {

  /**
   * {@inheritDoc}
   */
  @Override
  public void init() {
    registerBeanDefinitionParser("scan", new MapperScannerBeanDefinitionParser());
  }

}
