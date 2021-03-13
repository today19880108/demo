package com.frame.business.projectstage.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.business.project.model.project;
import com.frame.business.project.service.ProjectService;
import com.frame.business.projectstage.model.Projectstage;
import com.frame.business.projectstage.service.ProjectstageSerivce;
import com.frame.comm.service.CommService;
import com.frame.util.fileUploadDownUtil.BuildExcelTools;
import com.frame.util.page.Page;

@Controller
@RequestMapping("/business")
public class ProjectstageAction {

	@javax.annotation.Resource(name = "projectstageSerivce")
	private ProjectstageSerivce projectstageSerivce;
	
	@javax.annotation.Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "commService")
	CommService commService;
	
	/**
	 * @function	项目阶段管理页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/stageManagePage")
	public String departManage(ModelMap model, HttpServletRequest request){
		return "web/business/projectstage/jsp/stagePage";
	}
	
	/**
	 * @function	新增项目阶段页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addStagePage")
	public String addDepartPage(ModelMap model, HttpServletRequest request,Projectstage stage2){
		String id = stage2.getStageid();
		Projectstage stage=new Projectstage();
		if(null!=id&&!"".equals(id)){
			 stage = projectstageSerivce.getProjectstageByid(id);
			 model.put("select", stage);
		}
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("type", "7");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map2);
		model.put("stages", list1);
		
		String projectid=request.getParameter("projectid");
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("projectid", projectid);
		project p = projectService.queryProjectDetail(map3);
		model.put("selectpr", p);
		return "web/business/projectstage/jsp/stageAddUpdPage";
	}
	
	/**
	 * @function	保存项目阶段（新增或删除）
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveStage")
	public ModelMap saveDepartment(Projectstage stage,HttpServletRequest request)
	{
		String result=	projectstageSerivce.saveProjectstage(stage);
		ModelMap model =new ModelMap();
		model.put("result", result);
		return model;
	}
	
	
	/**
	 * @function	删除项目阶段
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delStage")
	public ModelMap delDepartment(ModelMap model, HttpServletRequest request)
	{
		String []id=request.getParameterValues("stageid[]");
		String result=projectstageSerivce.delProjectstage(id);
		model.put("result", result);
		return model;
	}
	
	/**
	 * @function	获取项目阶段列表
	 * @param stage
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStageList")
	public ModelMap getDepartList(Projectstage stage) {
		if(null==stage.getSidx()||"".equals(stage.getSidx())){
			stage.setSidx("STAGE");
			stage.setSord("ASC");
		}
		int rowInt = 10;
		int pageInt = 1;
		if (stage.getRows()==0) {
			stage.setRows(rowInt);
		}
		if (stage.getPage()==0) {
			stage.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = projectstageSerivce.getProjectstageList(stage);
		ModelMap model = new ModelMap();
		model.put("page", page);
		return model;
	}
	
	/**
	 * @function	导出建设excel
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/exportProjectstage")
	public void exportProjectstage(Projectstage v, HttpServletRequest request,HttpServletResponse response) {
		String projectid =request.getParameter("projectid");
		v.setProjectid(projectid);
	
		if(null==v.getSidx()||"".equals(v.getSidx())){
			v.setSidx("STAGE");
			v.setSord("ASC");
		}
		
		List<Map<String, ?>> data = null;
		data = projectService.searchData("business.queryProjecstagetList", v);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectid", projectid);
		project p2 = projectService.queryProjectDetail(map);
		
		ArrayList<Object> exportData = new ArrayList<Object>();
		ArrayList<Object> exportInfo = new ArrayList<Object>();
		if (data != null){
			exportData.add(data);
			Map<String, Object> dataInfo = new HashMap<String, Object>();
			dataInfo.put("SHEET_NAME", "第一页");
			dataInfo.put("SHEET_TITLE", "建设列表（"+p2.getProjectname()+"）");
			dataInfo.put("BODY_TITLE", createProjectstageMap());
			exportInfo.add(dataInfo);
		}
		
		BuildExcelTools.buildExcel(request, response, exportData, exportInfo, "建设列表.xls");
	}
	
	public LinkedHashMap<String, String> createProjectstageMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("STAGE","建设阶段");
		map.put("RESPONSIBLEPERSON","负责人");
		map.put("STARTTIME","开始日期");
		map.put("ENDTIME","结束日期");
		map.put("STAGESUMMARY","阶段总结");
		map.put("REMARKS","备注");
		return map;
	}
	
}
