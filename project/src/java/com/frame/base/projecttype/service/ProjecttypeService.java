package com.frame.base.projecttype.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.frame.base.projecttype.dao.ProjecttypeDao;
import com.frame.base.projecttype.model.ProjectType;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;


@Service("projecttypeService")
public class ProjecttypeService {

	@javax.annotation.Resource(name = "projecttypeDao")
	private ProjecttypeDao projecttypeDao; 
	
	/**
	 * @function	保存或更新项目类型数据
	 * @author 		linym
	 * @param d
	 * @return
	 */
	public String saveProjecttypment(ProjectType d)
	{
		try
		{
		if(d.getPid()==null||"".equals(d.getPid()))
		{
			d.setPid("0");
		}
		if(d.getId()!=null&&!d.getId().equals(""))
		{
			projecttypeDao.updateProjecttype(d);
		}
		else
		{
			d.setId(UUIDUtil.getUUID());
			projecttypeDao.insertProjecttype(d);
		}
		return "s";
		}
		catch(Exception ex)
		{
			return "f";
		}
	}
	
	/**
	 * @function	删除项目类型
	 * @author 		linym
	 * @param id
	 * @return
	 */
	public String delProjecttyp(String[] id)
	{
		try
		{
			projecttypeDao.delProjecttype(id);
			return "s";
		}
		catch(Exception ex)
		{
			return "f";
		}
	}
	
	/**
	 * @function	根据项目类型id查询项目类型详情
	 * @param id
	 * @return
	 */
	public ProjectType getProjecttypDetailByid(String id)
	{
		return projecttypeDao.getProjecttypeById(id);
	}
	
	/**
	 * @function	获取项目类型列表
	 * @param type
	 * @return
	 */
	public Page<HashMap<String, Object>> getProjecttypList(ProjectType type)
	{
		return 	projecttypeDao.getProjecttypeList(type);
	}
}
