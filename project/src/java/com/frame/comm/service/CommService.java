package com.frame.comm.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>[公共业务接口]</p>
 * @author yushp
 *
 */
public interface CommService{
	
	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getCode(Map<String, String> map);

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getCodeInAllTable(Map<String, String> map);

	/**
	 * 
	 * <p>[获得树形数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getTreeData(Map<String, String> map);

	/**
	 * 
	 * <p>[获得树形根节点数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getTreeDataOfTop(Map<String, String> map);

	/**
	 * 
	 * <p>[根据父节点获得树形节点数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getTreeDataByPid(Map<String, String> map);
}
