package org.tangu.mybatis.spring.batch;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ClassUtils.getShortName;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;


// 对  sqlSession.selectCursor(queryId, parameters) 的封装
public class MyBatisCursorItemReader<T> extends AbstractItemCountingItemStreamItemReader<T> implements InitializingBean {

  private String queryId;

  private SqlSessionFactory sqlSessionFactory;
  private SqlSession sqlSession;

  private Map<String, Object> parameterValues;

  private Cursor<T> cursor;
  private Iterator<T> cursorIterator;

  public MyBatisCursorItemReader() {
    setName(getShortName(MyBatisCursorItemReader.class));
  }

  @Override
  protected T doRead() throws Exception {
    T next = null;
    if (cursorIterator.hasNext()) {
      next = cursorIterator.next();
    }
    return next;
  }

  @Override
  protected void doOpen() throws Exception {
    Map<String, Object> parameters = new HashMap<String, Object>();
    if (parameterValues != null) {
      parameters.putAll(parameterValues);
    }

    sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
    cursor = sqlSession.selectCursor(queryId, parameters);
    cursorIterator = cursor.iterator();
  }

  @Override
  protected void doClose() throws Exception {
    cursor.close();
    sqlSession.close();
    cursorIterator = null;
  }

  /**
   * Check mandatory properties.
   *
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    notNull(sqlSessionFactory);
    notNull(queryId);
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
   *                        the query string.
   */
  public void setParameterValues(Map<String, Object> parameterValues) {
    this.parameterValues = parameterValues;
  }
}
