package com.frame.base.projecttype.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.projecttype.model.ProjectType;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

/**
 * <p>[部门DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="projecttypeDao")
public class ProjecttypeDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得部门列表]</p>
	 * @param Projecttype
	 * @return
	 * @author linym
	 */
	public Page<HashMap<String, Object>> getProjecttypeList(ProjectType Projecttype) {
		return this.queryPage("ProjectType.getProjecttypeList", "ProjectType.getProjecttypeListCount", Projecttype, Projecttype.getPage(), Projecttype.getRows());
	}
	
	/**
	 * 
	 * <p>[根据ID获得部门对象]</p>
	 * @param id
	 * @return
	 * @author linym
	 */
	public ProjectType getProjecttypeById(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return this.queryForObject("ProjectType.getProjecttypeById", map);
	}
	
	/**
	 * 
	 * <p>[查询登录名唯一性]</p>
	 * @param user
	 * @return
	 * @author linym
	 */
	public int searchProjecttypeOnly(ProjectType Projecttype) {
		return this.queryForObject("ProjectType.searchProjecttypeOnly", Projecttype);
	}

	/**
	 * 
	 * <p>[插入部门]</p>
	 * @param Projecttype
	 * @author linym
	 */
	public void insertProjecttype(ProjectType Projecttype) {
		this.insert("ProjectType.insertProjecttype", Projecttype);
	}

	/**
	 * 
	 * <p>[修改部门]</p>
	 * @param Projecttype
	 * @author linym
	 */
	public void updateProjecttype(ProjectType Projecttype) {
		this.update("ProjectType.updateProjecttype", Projecttype);
	}

	/**
	 * 
	 * <p>[删除部门]</p>
	 * @param Projecttypeid
	 * @author linym
	 */
	public void delProjecttype(String[] Projecttypeid) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: Projecttypeid){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			list.add(map);
		}
		this.batchDelete("ProjectType.delProjecttype", list);
	}

}
