package com.frame.comm.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>[公共DAO接口]</p>
 * @author yushp
 *
 */
public interface CommDao {

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	List<Map<String, ?>> getCode(Map<String, String> map);

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	List<Map<String, ?>> getCodeInAllTable(Map<String, String> map);

	/**
	 * 
	 * <p>[获得树形数据]</p>
	 * @author yushp
	 */
	List<Map<String, ?>> getTreeData(Map<String, String> map);

	/**
	 * 
	 * <p>[获得树形根节点数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	List<Map<String, ?>> getTreeDataOfTop(Map<String, String> map);

	/**
	 * 
	 * <p>[根据父节点获得树形节点数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	List<Map<String, ?>> getTreeDataByPid(Map<String, String> map);

}
