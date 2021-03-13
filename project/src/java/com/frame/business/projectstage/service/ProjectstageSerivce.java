package com.frame.business.projectstage.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.frame.business.projectstage.dao.ProjectstageDao;
import com.frame.business.projectstage.model.Projectstage;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;


@Service("projectstageSerivce")
public class ProjectstageSerivce {

	@javax.annotation.Resource(name = "projectstageDao")
	private ProjectstageDao projectstageDao; 
	/**
	 * @function	保存或更新项目阶段数据
	 * @author 		linym
	 * @param d
	 * @return
	 */
	public String saveProjectstage(Projectstage d)
	{
		try
		{
		if(d.getStageid()!=null&&!d.getStageid().equals(""))
		{
			projectstageDao.updateProjectstage(d);
		}
		else
		{
			d.setStageid(UUIDUtil.getUUID());
			projectstageDao.insertProjectstage(d);
		}
		return "s";
		}
		catch(Exception ex)
		{
			return "f";
		}
	}
	
	/**
	 * @function	删除项目阶段
	 * @author 		linym
	 * @param id
	 * @return
	 */
	public String delProjectstage(String[] id)
	{
		try
		{
			projectstageDao.delProjectstage(id);
			return "s";
		}
		catch(Exception ex)
		{
			return "f";
		}
	}
	
	/**
	 * @function	根据项目阶段id查询项目阶段详情
	 * @param id
	 * @return
	 */
	public Projectstage getProjectstageByid(String id)
	{
		return projectstageDao.getProjectstageById(id);
	}
	
	/**
	 * @function	获取项目阶段列表
	 * @param stage
	 * @return
	 */
	public Page<HashMap<String, Object>> getProjectstageList(Projectstage stage)
	{
		try{
		return 	projectstageDao.getProjectstageList(stage);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
