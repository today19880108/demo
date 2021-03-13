package com.frame.util.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.frame.util.page.Page;

/**
 * 
 * <p>[数据库基础操作接口]</p>
 * @author yushp
 *
 */
public interface IBaseDao {
	/**
	* 
	* <p>[插入数据]</p>
	* @author yushp
	*/
	public void insert(String statementName, Object parameterObject)
	        throws DataAccessException;
	
	/**
	* 
	* <p>[修改数据]</p>
	* @author yushp
	*/
	public void update(String statementName, Object parameterObject)
	        throws DataAccessException;
	
	/**
	 * 
	 * <p>[删除数据]</p>
	 * @author yushp
	 */
	public void delete(String statementName, Object parameterObject)
	        throws DataAccessException;
	
	/**
	 * 批量插入数据
	 *
	 * @param sqlId
	 * @param list
	 * @throws DataAccessException
	 */
	public void batchInsert(final String sqlId, final List<? extends Object> list)
	        throws DataAccessException;
	
	/**
	 * 批量修改数据
	 *
	 * @param sqlId
	 * @param list
	 * @throws DataAccessException
	 */
	public void batchUpdate(final String sqlId, final List<? extends Object> list)
	        throws DataAccessException;
	
	/**
	 * 批量删除数据
	 *
	 * @param sqlId
	 * @param list
	 * @throws DataAccessException
	 */
	public void batchDelete(final String sqlId, final List<? extends Object> list)
			throws DataAccessException;
	    
	   /**
	* 
	* <p>[查询序列号]</p>
	* @author yushp
	*/
	public Integer queryForSeq(String statementName, Object parameterObject)
	        throws DataAccessException;
	
	/**
	 * 
	 * <p>[查询单个对象]</p>
	 * @author yushp
	 */
	public <T> T queryForObject(String statementName, Object parameterObject)
	        throws DataAccessException;
	
	/**
	 * 
	 * <p>[查询所有记录]</p>
	 * @author yushp
	 */
	public <T> List<T> queryForList(String statementName,
	                                   Object parameterObject) throws DataAccessException;
	/**
	 * 
	 * <p>[分页查询记录]</p>
	 * @author yushp
	 */
	public <V> List<V> queryPageForList(String statementName,
	                                       Object parameterObject, int skipResults, int maxResults);
	
	/**
	 * 
	 * <p>[封装分页查询]</p>
	 * @author yushp
	 */
	public <T> Page<T> queryPage(String statementNameForList, String statementNameForCount, Object parameterObject, 
	                                    int pageNum, int maxResults);

}
