package com.frame.comm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.util.dao.BaseDaoImpl;

/**
 * 
 * <p>[公共DAO类]</p>
 * @author yushp
 *
 */
@Service("commDao")
public class CommDaoImpl extends BaseDaoImpl implements CommDao {

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getCode(Map<String, String> map) {
		return queryForList("Comm.getCode", map);
	}

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getCodeInAllTable(Map<String, String> map) {
		return queryForList("Comm.getCodeInAllTable", map);
	}

	/**
	 * 
	 * <p>[获得树形数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getTreeData(Map<String, String> map) {
		return queryForList("Comm.getTreeData", map);
	}

	/**
	 * 
	 * <p>[获得树形根节点数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	@Override
	public List<Map<String, ?>> getTreeDataOfTop(Map<String, String> map) {
		return queryForList("Comm.getTreeDataOfTop", map);
	}

	/**
	 * 
	 * <p>[根据父节点获得树形节点数据]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	@Override
	public List<Map<String, ?>> getTreeDataByPid(Map<String, String> map) {
		return queryForList("Comm.getTreeDataByPid", map);
	}

}
