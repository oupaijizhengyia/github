package org.tangu.mybatis.spring.batch;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.tangu.mybatis.spring.SqlSessionTemplate;


/**
 * Employee employee = new Employee();
 * employee.setId(8);
 * sqlSessionTemplate.update("com.tangu.dao.EmployeeMapper.modifyEmployee", employee);
 */
public class MyBatisBatchItemWriter<T> implements ItemWriter<T>, InitializingBean {

  private static final Log LOGGER = LogFactory.getLog(MyBatisBatchItemWriter.class);

  private SqlSessionTemplate sqlSessionTemplate;

  // sql语句id
  private String statementId;

  private boolean assertUpdates = true;

  public void setAssertUpdates(boolean assertUpdates) {
    this.assertUpdates = assertUpdates;
  }

  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    if (sqlSessionTemplate == null) {
      this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }
  }

  public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    this.sqlSessionTemplate = sqlSessionTemplate;
  }

  /**
   * SqlMap configuration file --> mapper.dao --> mapper.xml
   * mapper.xml sql语句id  -->  com.tangu.dao.EmployeeMapper.modifyEmployee
   */
  public void setStatementId(String statementId) {
    this.statementId = statementId;
  }

  /**
   * Check mandatory properties - there must be an SqlSession and a statementId.
   */
  @Override
  public void afterPropertiesSet() {
    notNull(sqlSessionTemplate, "A SqlSessionFactory or a SqlSessionTemplate is required.");
    isTrue(ExecutorType.BATCH == sqlSessionTemplate.getExecutorType(), "SqlSessionTemplate's executor type must be BATCH");
    notNull(statementId, "A statementId is required.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  // TODO
  public void write(final List<? extends T> items) {

    if (!items.isEmpty()) {

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Executing batch with " + items.size() + " items.");
      }

      for (T item : items) {
    	// Employee employee = new Employee();
        // employee.setId(8);
    	// sqlSessionTemplate.update("com.tangu.dao.EmployeeMapper.modifyEmployee", employee);
        sqlSessionTemplate.update(statementId, item);
      }

      List<BatchResult> results = sqlSessionTemplate.flushStatements();

      if (assertUpdates) {
        if (results.size() != 1) {
          throw new InvalidDataAccessResourceUsageException("Batch execution returned invalid results. " +
              "Expected 1 but number of BatchResult objects returned was " + results.size());
        }

        int[] updateCounts = results.get(0).getUpdateCounts();

        for (int i = 0; i < updateCounts.length; i++) {
          int value = updateCounts[i];
          if (value == 0) {
            throw new EmptyResultDataAccessException("Item " + i + " of " + updateCounts.length
                + " did not update any rows: [" + items.get(i) + "]", 1);
          }
        }
      }
    }
  }

}
