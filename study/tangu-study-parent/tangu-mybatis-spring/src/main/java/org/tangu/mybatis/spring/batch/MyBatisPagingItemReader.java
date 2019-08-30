package org.tangu.mybatis.spring.batch;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ClassUtils.getShortName;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.tangu.mybatis.spring.SqlSessionTemplate;

/**
 * SqlSessionTemplate 分页读取的封装
 */
public class MyBatisPagingItemReader<T> extends AbstractPagingItemReader<T> {

  private String queryId;

  private SqlSessionFactory sqlSessionFactory;

  private SqlSessionTemplate sqlSessionTemplate;

  private Map<String, Object> parameterValues;

  public MyBatisPagingItemReader() {
    setName(getShortName(MyBatisPagingItemReader.class));
  }

  /**
   * Public setter for {@link SqlSessionFactory} for injection purposes.
   *
   * @param sqlSessionFactory a factory object for the {@link SqlSession}.
   */
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  /**
   * Public setter for the statement id identifying the statement in the SqlMap
   * configuration file.
   *
   * @param queryId the id for the statement
   */
  public void setQueryId(String queryId) {
    this.queryId = queryId;
  }

  /**
   * The parameter values to be used for the query execution.
   *
   * @param parameterValues the values keyed by the parameter named used in
   * the query string.
   */
  public void setParameterValues(Map<String, Object> parameterValues) {
    this.parameterValues = parameterValues;
  }

  /**
   * Check mandatory properties.
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    super.afterPropertiesSet();
    notNull(sqlSessionFactory);
    sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    notNull(queryId);
  }

  @Override
  protected void doReadPage() {
    Map<String, Object> parameters = new HashMap<String, Object>();
    if (parameterValues != null) {
      parameters.putAll(parameterValues);
    }
    parameters.put("_page", getPage());
    parameters.put("_pagesize", getPageSize());
    parameters.put("_skiprows", getPage() * getPageSize());
    if (results == null) {
      results = new CopyOnWriteArrayList<T>();
    } else {
      results.clear();
    }
    results.addAll(sqlSessionTemplate.<T> selectList(queryId, parameters));
  }

  @Override
  protected void doJumpToPage(int itemIndex) {
      // Not Implemented
  }

}
