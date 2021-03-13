package com.frame.business.projectstage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.business.projectstage.model.Projectstage;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;
@Service(value="projectstageDao")
public class ProjectstageDao extends BaseDaoImpl {
 
	/**
	 * 
	 * <p>[获得项目阶段列表]</p>
	 * @param ProjectstageAction
	 * @return
	 * @author linym
	 */
	public Page<HashMap<String, Object>> getProjectstageList(Projectstage stage) {
		return this.queryPage("business.queryProjecstagetList", "business.queryProjectstageCount", stage, stage.getPage(), stage.getRows());
	}
	
	/**
	 * 
	 * <p>[根据ID获得项目阶段对象]</p>
	 * @param id
	 * @return
	 * @author linym
	 */
	public Projectstage getProjectstageById(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return this.queryForObject("business.queryProjectstageDetail", map);
	}
	
	/**
	 * 
	 * <p>[插入项目阶段]</p>
	 * @param ProjectstageAction
	 * @author linym
	 */
	public void insertProjectstage(Projectstage stage) {
		this.insert("business.insertProjectstage", stage);
	}

	/**
	 * 
	 * <p>[修改项目阶段]</p>
	 * @param ProjectstageAction
	 * @author linym
	 */
	public void updateProjectstage(Projectstage stage) {
		this.update("business.updateProjectstage", stage);
	}

	/**
	 * 
	 * <p>[删除项目阶段]</p>
	 * @param stageid
	 * @author linym
	 */
	public void delProjectstage(String[] stageid) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: stageid){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			list.add(map);
		}
		this.batchDelete("business.delProjectstage", list);
	}
	
}
