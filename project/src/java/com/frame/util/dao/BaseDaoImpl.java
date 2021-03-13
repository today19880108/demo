package com.frame.util.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.frame.util.page.Page;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * 
 * <p>[数据库基础操作类]</p>
 * @author yushp
 *
 */
public abstract class BaseDaoImpl extends SqlMapClientDaoSupport implements IBaseDao {
   
	// 注入sqlMapClient
    @Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;
	
    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }
    
	/**
    * 
    * <p>[插入数据]</p>
    * @author yushp
    */
    public void insert(String statementName, Object parameterObject)
            throws DataAccessException {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, parameterObject);
        // 执行数据库操作
        getSqlMapClientTemplate().insert(statementName, parameterObject);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog(endDate.getTime() - startDate.getTime(), sqlStr);
    }
    
	/**
    * 
    * <p>[修改数据]</p>
    * @author yushp
    */
    public void update(String statementName, Object parameterObject)
            throws DataAccessException {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, parameterObject);
        // 执行数据库操作
        getSqlMapClientTemplate().update(statementName, parameterObject);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog(endDate.getTime() - startDate.getTime(), sqlStr);
    }

    /**
     * 
     * <p>[删除数据]</p>
     * @author yushp
     */
    public void delete(String statementName, Object parameterObject)
            throws DataAccessException {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, parameterObject);
        // 执行数据库操作
        getSqlMapClientTemplate().delete(statementName, parameterObject);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog(endDate.getTime() - startDate.getTime(), sqlStr);
    }
    
    /**
     * 批量插入数据
     *
     * @param sqlId
     * @param list
     * @throws DataAccessException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void batchInsert(final String sqlId, final List<? extends Object> list)
            throws DataAccessException {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)
                    throws SQLException {
            	// 数据库操作结束时间
                Date startDate = new Date();
                executor.startBatch();
                for (Object obj : list) {
                    executor.insert(sqlId, obj);
                }
                executor.executeBatch();
                // 数据库操作结束时间
                Date endDate = new Date();
                // 打印日志
                printSqllog__(endDate.getTime() - startDate.getTime(), getSqlAndParamStr(sqlId, null));
                return null;
            }
        });
    }

    /**
     * 批量修改数据
     *
     * @param sqlId
     * @param list
     * @throws DataAccessException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void batchUpdate(final String sqlId, final List<? extends Object> list)
            throws DataAccessException {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)
                    throws SQLException {
            	// 数据库操作结束时间
                Date startDate = new Date();
                executor.startBatch();
                for (Object obj : list) {
                    executor.update(sqlId, obj);
                }
                executor.executeBatch();
                // 数据库操作结束时间
                Date endDate = new Date();
                // 打印日志
                printSqllog__(endDate.getTime() - startDate.getTime(), getSqlAndParamStr(sqlId, null));
                return null;
            }
        });
    }

    /**
     * 批量删除数据
     *
     * @param sqlId
     * @param list
     * @throws DataAccessException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void batchDelete(final String sqlId, final List<? extends Object> list)
            throws DataAccessException {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)
                    throws SQLException {
            	// 数据库操作结束时间
                Date startDate = new Date();
                executor.startBatch();
                for (Object obj : list) {
                    executor.delete(sqlId, obj);
                }
                executor.executeBatch();
                // 数据库操作结束时间
                Date endDate = new Date();
                // 打印日志
                printSqllog__(endDate.getTime() - startDate.getTime(), getSqlAndParamStr(sqlId, null));
                return null;
            }
        });
    }
    
   /**
    * 
    * <p>[查询序列号]</p>
    * @author yushp
    */
    public Integer queryForSeq(String statementName, Object parameterObject)
            throws DataAccessException {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, null);
        // 执行数据库操作
        Integer obj = (Integer) getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog(endDate.getTime() - startDate.getTime(), sqlStr);

        return obj;
    }

    /**
     * 
     * <p>[查询单个对象]</p>
     * @author yushp
     */
    @SuppressWarnings("unchecked")
	public <T> T queryForObject(String statementName, Object parameterObject)
            throws DataAccessException {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, parameterObject);
        // 执行数据库操作
        T obj = (T) getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog(endDate.getTime() - startDate.getTime(), sqlStr);

        return obj;
    }

    /**
     * 
     * <p>[查询所有记录]</p>
     * @author yushp
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> queryForList(String statementName,
                                       Object parameterObject) throws DataAccessException {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, parameterObject);
        // 执行数据库操作
        List<T> list = getSqlMapClientTemplate().queryForList(statementName,
                parameterObject);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog_(endDate.getTime() - startDate.getTime(), sqlStr, list.size());

        return list;
    }

    /**
     * 
     * <p>[分页查询记录]</p>
     * @author yushp
     */
    @SuppressWarnings("unchecked")
    public <V> List<V> queryPageForList(String statementName,
                                           Object parameterObject, int skipResults, int maxResults) {
        // 数据库操作结束时间
        Date startDate = new Date();
        // 操作SQL
        String[] sqlStr = getSqlAndParamStr(statementName, parameterObject);
        // 执行数据库操作
        List<V> list = getSqlMapClientTemplate().queryForList(statementName, parameterObject, skipResults, maxResults);
        // 数据库操作结束时间
        Date endDate = new Date();
        // 打印日志
        this.printSqllog_(endDate.getTime() - startDate.getTime(), sqlStr, list.size());

        return list;
    }

    /**
     * 
     * <p>[封装分页查询]</p>
     * @author yushp
     */
    public <T> Page<T> queryPage(String statementNameForList, String statementNameForCount, Object parameterObject, 
                                    int pageNum, int rowNum) {
        Page<T> page = new Page<T>();
        int skipResults = 0;
        //总记录数
        int totalRecords = queryForObject(statementNameForCount, parameterObject);
        /*
        1、当总记录数小于等于每页记录数时，将当前页调整为1
        2、当jsp页面中输入的跳转的页数大于最大pageNum时，将当前页调整为1
         */
        if(totalRecords <= rowNum || (rowNum * pageNum - rowNum) > totalRecords){
            // 记录
            List<T> b = queryPageForList(statementNameForList, parameterObject,
                    0 , rowNum);
            page.setRecords(b);
            // 当前页数
            page.setCurrentPage(1);

        }else{
            skipResults = (pageNum - 1) * rowNum;
            // 记录
            List<T> b = queryPageForList(statementNameForList, parameterObject,
                    skipResults, rowNum);
            page.setRecords(b);
            // 当前页数
            page.setCurrentPage(pageNum);
        }
        // 总页数
        page.setTotalPage((totalRecords+rowNum-1)/rowNum);
        // 总记录数
        page.setTotalRecords(totalRecords);

        return page;
    }

    /**
     * 
     * <p>[获得SQL语句]</p>
     * @author yushp
     */
	private String[] getSqlAndParamStr(String sqlId, Object arg) {
	    SqlMapClientImpl sci = (SqlMapClientImpl)this.sqlMapClient;  
	    MappedStatement ms = sci.getMappedStatement(sqlId);  
	    Sql sql = ms.getSql();    
	    SessionScope sessionScope = new SessionScope();       
	    sessionScope.incrementRequestStackDepth();       
	    StatementScope statementScope = new StatementScope(sessionScope);       
	    ms.initRequest(statementScope);      
	    ms.getCacheKey(statementScope, arg);  
	             
	    String sqlString = sql.getSql(statementScope, arg);   
	    Object[] sqlParam = sql.getParameterMap(statementScope, arg).getParameterObjectValues(statementScope, arg);  
		StringBuffer paramStr = new StringBuffer();
	    for(Object obj : sqlParam){
	    	paramStr.append(obj+",");
	    }
	    
	    String[] res = new String[2];
	    res[0] = sqlString;
	    res[1] = paramStr.length() > 0?paramStr.toString().substring(0, paramStr.length()-1):"";
		
        return res;
    }

    /**
     * 
     * <p>[打印SQL日志]</p>
     * @author yushp
     */
    private void printSqllog(long times, String[] sqlStr){
    	System.out.println("用时：" + times + " 毫秒，" + "sql语句：" + sqlStr[0] + "参数值：" + (sqlStr[1].length()>0?sqlStr[1]:"无"));
    }
    
    /**
     * 
     * <p>[打印SQL日志]</p>
     * @author yushp
     */
    private void printSqllog_(long times, String sqlStr[], int size){
    	System.out.println("用时：" + times + " 毫秒，记录条数："+ size + "，sql语句：" + sqlStr[0] + "参数值：" + (sqlStr[1].length()>0?sqlStr[1]:"无"));
    }
    
    /**
     * 
     * <p>[批处理打印SQL日志]</p>
     * @author yushp
     */
    private void printSqllog__(long times, String[] sqlStr){
    	System.out.println("批处理,用时：" + times + " 毫秒，" + "sql语句：" + sqlStr[0]);
    }
}