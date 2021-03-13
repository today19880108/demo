package com.frame.comm.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.frame.comm.dao.CommDao;

/**
 * 
 * <p>[公共业务类]</p>
 * @author yushp
 *
 */
@Service("commService")
public class CommServiceImpl implements CommService {
	
	@Resource(name="commDao")
	CommDao commDao;

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getCode(Map<String, String> map) {
		return commDao.getCode(map);
	}

	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getCodeInAllTable(Map<String, String> map) {
		return commDao.getCodeInAllTable(map);
	}

	/**
	 * 
	 * <p>[获得树形数据]</p>
	 * @author yushp
	 */
	public List<Map<String, ?>> getTreeData(Map<String, String> map) {
		return commDao.getTreeData(map);
	}

	/**
	 * 
	 * <p>[获得树形根节点数据]</p>
	 * @author yushp
	 */
	@Override
	public List<Map<String, ?>> getTreeDataOfTop(Map<String, String> map) {
		return commDao.getTreeDataOfTop(map);
	}

	/**
	 * 
	 * <p>[根据父节点获得树形节点数据]</p>
	 * @author yushp
	 */
	@Override
	public List<Map<String, ?>> getTreeDataByPid(Map<String, String> map) {
		return commDao.getTreeDataByPid(map);
	}

}
