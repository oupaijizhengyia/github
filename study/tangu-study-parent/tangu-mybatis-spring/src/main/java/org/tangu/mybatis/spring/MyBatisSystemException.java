package org.tangu.mybatis.spring;

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * 见 {@code MyBatisExceptionTranslator}中的使用
 */
public class MyBatisSystemException extends UncategorizedDataAccessException {

  private static final long serialVersionUID = -5284728621670758939L;

  public MyBatisSystemException(Throwable cause) {
    super(null, cause);
  }

}
