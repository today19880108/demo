package com.frame.base.projecttype.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.projecttype.model.ProjectType;
import com.frame.base.projecttype.service.ProjecttypeService;
import com.frame.util.page.Page;

@Controller
@RequestMapping("/base")
public class ProjectTypeAction {
	
	@javax.annotation.Resource(name = "projecttypeService")
	private ProjecttypeService projecttypeService;
	
	/**
	 * @function	项目类型管理页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/projecttypeManagePage")
	public String ProjecttypeManage(ModelMap model, HttpServletRequest request){
		return "web/base/projecttype/jsp/projecttypePage";
	}
	
	/**
	 * @function	新增项目类型页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addProjecttypePage")
	public String addProjecttypePage(ModelMap model, HttpServletRequest request){
		String id = request.getParameter("ID");
		if(null!=id&&!"".equals(id)){
			ProjectType projecttype = projecttypeService.getProjecttypDetailByid(id);
			model.put("projecttype", projecttype);
		}
		return "web/base/projecttype/jsp/projecttypeAddUpdPage";
	}
	
	/**
	 * @function	保存项目类型（新增或删除）
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveProjecttype")
	public ModelMap saveProjecttype(ProjectType projecttype)
	{
		String result=	projecttypeService.saveProjecttypment(projecttype);
		ModelMap model =new ModelMap();
		model.put("result", result);
		return model;
	}
	
	
	/**
	 * @function	删除项目类型
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delProjecttype")
	public ModelMap delProjecttype(ModelMap model, HttpServletRequest request)
	{
		String []id=request.getParameterValues("ID[]");
		String result=projecttypeService.delProjecttyp(id);
		model.put("result", result);
		return model;
	}
	
	/**
	 * @function	获取项目类型列表
	 * @param projecttype
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProjecttypeList")
	public ModelMap getProjecttypeList(ProjectType projecttype) {
		if(null==projecttype.getSidx()||"".equals(projecttype.getSidx())){
			projecttype.setSidx("PID");
			projecttype.setSord("ASC");
		}
		
		int rowInt = 10;
		int pageInt = 1;
		if (projecttype.getRows()==0) {
			projecttype.setRows(rowInt);
		}
		if (projecttype.getPage()==0) {
			projecttype.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = projecttypeService.getProjecttypList(projecttype);
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
}
