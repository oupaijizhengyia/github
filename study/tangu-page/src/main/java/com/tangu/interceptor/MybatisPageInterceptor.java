package com.tangu.interceptor;

import com.tangu.config.*;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * FileName: MybatisPageInterceptor
 * Author: yeyang
 * Date: 2019/9/6 10:20
 */

@Intercepts(@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}))
public class MybatisPageInterceptor implements Interceptor {
    private final String LIMIT = "limit";
    private final String FROM = "from";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Page page = DynamicPage.get();
        RoutingStatementHandler routHandler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler handler = (StatementHandler) RefUtil.getFieldValue(routHandler,"delegate");
        //对应Mapper语句
        MappedStatement statement = (MappedStatement)RefUtil.getFieldValue(handler,"mappedStatement");
        //参数
        Object parameterObject = handler.getParameterHandler().getParameterObject();
        //若带有分页关键字  则直接返回
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        String sqlClone = new String(sql).toLowerCase();
        if(sqlClone.contains(LIMIT)){
            return invocation.proceed();
        }
        //开始分页
        if(page != null && page.getFlag()){
            //获取方法上的注解
            Method method = getMethodById(statement.getId());
            Separate tanguPage = method.getAnnotation(Separate.class);
            if(tanguPage != null){
                //总数查询

                Integer countNumber = getCountNumber(tanguPage.value(),statement,(Connection)invocation.getArgs()[0],parameterObject);
                page.setCountNumber(countNumber);
                Integer pageSize = page.getPageSize();
                Integer record = countNumber / pageSize;
                if(countNumber % pageSize != 0){
                    record++;
                }
                page.setPageRecord(record);
                DynamicPage.set(page);
                Integer start = pageSize * (page.getTargetIndex() - 1);
                if(countNumber < start - 1){
                    throw new IllegalArgumentException("非法的目标页码");
                }
                //加入分页参数 继续执行
                StringBuffer sb = new StringBuffer(sql);
                sb.append(" "+LIMIT+" ");
                sb.append(start);
                sb.append(",");
                sb.append(pageSize);
                statement.getResultSets();
                RefUtil.setFieldValue(boundSql,"sql",sb.toString());
            }
        }
        return invocation.proceed();
    }

    private Integer getCountNumber(FromSide fromSide,MappedStatement statement, Connection connection, Object para) throws SQLException {
        //获取原参数 组装统计sql并执行
        BoundSql boundSql = statement.getBoundSql(para);
        String countSql = getCountSql(fromSide,boundSql.getSql());
        BoundSql countBoundSql = new BoundSql(statement.getConfiguration(),countSql,boundSql.getParameterMappings(),para);
        //封装参数处理器
        ParameterHandler parameterHandler = new DefaultParameterHandler(statement,para,countBoundSql);
        PreparedStatement ppst = null;
        ResultSet rs = null;
        try {
            ppst = connection.prepareStatement(countSql);
            parameterHandler.setParameters(ppst);
            rs = ppst.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
            throw new SQLException("SQL 语法出错");
        }finally {
            try {
                if(ppst != null){
                    ppst.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    private String getCountSql(FromSide fromSide,String sql) {
        // todo 这可以重新写 不需要判断from的前后  直接一把把原sql拿出来 然后再count [yy]
        if(sql.contains(sql)){
            String sqlClone = new String(sql);
            Integer index = 0;
            switch (fromSide){
                case FIRST: index = sqlClone.toLowerCase().indexOf(FROM);break;
                case LAST: index = sql.toLowerCase().lastIndexOf(FROM);break;
                default:;
            }
            sql = sql.substring(index);
            sql = "select count(*) "+sql;
            return sql;
        }else {
            return null;
        }
    }

    /**
     * 根据statementId 获取方法
     * @param id
     * @return
     * @throws ClassNotFoundException
     */
    private Method getMethodById(String id) throws ClassNotFoundException {
        Method method = null;
        String className = id.substring(0,id.lastIndexOf("."));
        String methodName = id.substring(id.lastIndexOf(".")+1);
        Class c = Class.forName(className);
        for (Method m : c.getMethods()){
            if (methodName.equals(m.getName())){
                return m;
            }
        }
        return method;
    }


    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
