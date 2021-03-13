package com.frame.base.department.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.department.model.Department;
import com.frame.base.department.service.DepartService;
import com.frame.util.page.Page;

@Controller
@RequestMapping("/base")
public class DepartAction {
	
	@javax.annotation.Resource(name = "departService")
	private DepartService departService;
	
	/**
	 * @function	部门管理页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/departManagePage")
	public String departManage(ModelMap model, HttpServletRequest request){
		return "web/base/department/jsp/departPage";
	}
	
	/**
	 * @function	新增部门页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addDepartPage")
	public String addDepartPage(ModelMap model, HttpServletRequest request){
		String id = request.getParameter("ID");
		if(null!=id&&!"".equals(id)){
			Department depart = departService.getDepartDetailByid(id);
			model.put("depart", depart);
		}
		return "web/base/department/jsp/departAddUpdPage";
	}
	
	/**
	 * @function	保存部门（新增或删除）
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveDepartment")
	public ModelMap saveDepartment(Department depart)
	{
		String result=	departService.saveDepartment(depart);
		ModelMap model =new ModelMap();
		model.put("result", result);
		return model;
	}
	
	
	/**
	 * @function	删除部门
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delDepartment")
	public ModelMap delDepartment(ModelMap model, HttpServletRequest request)
	{
		String []id=request.getParameterValues("ID[]");
		String result=departService.delDepart(id);
		model.put("result", result);
		return model;
	}
	
	/**
	 * @function	获取部门列表
	 * @param depart
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDepartList")
	public ModelMap getDepartList(Department depart) {
		if(null==depart.getSidx()||"".equals(depart.getSidx())){
			depart.setSidx("PID, ORD");
			depart.setSord("ASC");
		}
		
		int rowInt = 10;
		int pageInt = 1;
		if (depart.getRows()==0) {
			depart.setRows(rowInt);
		}
		if (depart.getPage()==0) {
			depart.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = departService.getDepartList(depart);
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
}
