package com.frame.base.department.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.frame.base.department.dao.DepartDao;
import com.frame.base.department.model.Department;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;


@Service("departService")
public class DepartService {

	@javax.annotation.Resource(name = "departDao")
	private DepartDao departDao; 
	
	/**
	 * @function	保存或更新部门数据
	 * @author 		linym
	 * @param d
	 * @return
	 */
	public String saveDepartment(Department d)
	{
		try
		{
		if(d.getPid()==null||"".equals(d.getPid()))
		{
			d.setPid("0");
		}
		if(d.getId()!=null&&!d.getId().equals(""))
		{
			departDao.updateDepart(d);
		}
		else
		{
			d.setId(UUIDUtil.getUUID());
			departDao.insertDepart(d);
		}
		return "s";
		}
		catch(Exception ex)
		{
			return "f";
		}
	}
	
	/**
	 * @function	删除部门
	 * @author 		linym
	 * @param id
	 * @return
	 */
	public String delDepart(String[] id)
	{
		try
		{
			departDao.delDepart(id);
			return "s";
		}
		catch(Exception ex)
		{
			return "f";
		}
	}
	
	/**
	 * @function	根据部门id查询部门详情
	 * @param id
	 * @return
	 */
	public Department getDepartDetailByid(String id)
	{
		return departDao.getDepartById(id);
	}
	
	/**
	 * @function	获取部门列表
	 * @param depart
	 * @return
	 */
	public Page<HashMap<String, Object>> getDepartList(Department depart)
	{
		return 	departDao.getDepartList(depart);
	}
}
