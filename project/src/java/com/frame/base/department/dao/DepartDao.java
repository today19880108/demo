package com.frame.base.department.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.department.model.Department;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

/**
 * <p>[部门DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="departDao")
public class DepartDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得部门列表]</p>
	 * @param depart
	 * @return
	 * @author linym
	 */
	public Page<HashMap<String, Object>> getDepartList(Department depart) {
		return this.queryPage("department.getDepartList", "department.getDepartListCount", depart, depart.getPage(), depart.getRows());
	}
	
	/**
	 * 
	 * <p>[根据ID获得部门对象]</p>
	 * @param id
	 * @return
	 * @author linym
	 */
	public Department getDepartById(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return this.queryForObject("department.getDepartById", map);
	}
	
	/**
	 * 
	 * <p>[查询登录名唯一性]</p>
	 * @param user
	 * @return
	 * @author linym
	 */
	public int searchDepartOnly(Department depart) {
		return this.queryForObject("department.searchDepartOnly", depart);
	}

	/**
	 * 
	 * <p>[插入部门]</p>
	 * @param depart
	 * @author linym
	 */
	public void insertDepart(Department depart) {
		this.insert("department.insertDepart", depart);
	}

	/**
	 * 
	 * <p>[修改部门]</p>
	 * @param depart
	 * @author linym
	 */
	public void updateDepart(Department depart) {
		this.update("department.updateDepart", depart);
	}

	/**
	 * 
	 * <p>[删除部门]</p>
	 * @param departid
	 * @author linym
	 */
	public void delDepart(String[] departid) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: departid){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			list.add(map);
		}
		this.batchDelete("department.delDepart", list);
	}

}
