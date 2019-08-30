package org.tangu.mybatis.spring;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.transaction.TransactionException;

/**
 * 默认的 异常转换器
 */
public class MyBatisExceptionTranslator implements PersistenceExceptionTranslator {

  private final DataSource dataSource;

  private SQLExceptionTranslator exceptionTranslator;

  public MyBatisExceptionTranslator(DataSource dataSource, boolean exceptionTranslatorLazyInit) {
    this.dataSource = dataSource;

    if (!exceptionTranslatorLazyInit) {
      this.initExceptionTranslator();
    }
  }

  /**
   * {@inheritDoc}
   * 代码不难, 搞清楚出现的异常即可
   * SQLErrorCodeSQLExceptionTranslator
   * RuntimeException
   * PersistenceException #getCause()
   * SQLException TransactionException
   * MyBatisSystemException
   */
  @Override
  public DataAccessException translateExceptionIfPossible(RuntimeException e) {
    if (e instanceof PersistenceException) {
      // Batch exceptions come inside another PersistenceException
      // recursion has a risk of infinite loop so better make another if
      if (e.getCause() instanceof PersistenceException) {
        e = (PersistenceException) e.getCause();
      }
      if (e.getCause() instanceof SQLException) {
        this.initExceptionTranslator();
        return this.exceptionTranslator.translate(e.getMessage() + "\n", null, (SQLException) e.getCause());
      } else if (e.getCause() instanceof TransactionException) {
        throw (TransactionException) e.getCause();
      }
      return new MyBatisSystemException(e);
    } 
    return null;
  }

  /**
   * Initializes the internal translator reference.
   */
  private synchronized void initExceptionTranslator() {
    if (this.exceptionTranslator == null) {
      this.exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
    }
  }

}
