package com.frame.business.analyse.action;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.business.analyse.service.AnalyseService;
import com.frame.comm.service.CommService;

/**
 * 
 * <p>[投资项目分析控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/business")
public class AnalyseAction {
	
	@Resource(name="commService")
	CommService commService;
	
	@Resource(name="analyseService")
	AnalyseService analyseService;
	
	/**
	 * 
	 * <p>[请求分析页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getAnalysePage")
	public String getAnalysePage(ModelMap model, HttpServletRequest request){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		model.put("year", calendar.get(Calendar.YEAR));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", "projecttype");
		List<Map<String, ?>> list = commService.getTreeDataOfTop(map);
		
		JSONArray json = JSONArray.fromObject(list);
		
		model.put("projectTypeList", json.toString());
		return "web/business/analyse/jsp/analysePage";
	}
	
	/**
	 * 
	 * <p>[根据ID请求分析页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getAnalysePageById")
	public String getAnalysePageById(ModelMap model, HttpServletRequest request){
		return "web/business/analyse/jsp/analysePageById";
	}
	
	/**
	 * 
	 * <p>[获得年总投资额数据列表]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getYearAnalyseData")
	public ModelMap getYearAnalyseData(ModelMap model, HttpServletRequest request){
		String year_s = request.getParameter("year_s");
		String year_e = request.getParameter("year_e");
		
		List<Map<String, ?>> list = analyseService.getYearAnalyseData(year_s, year_e);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[获得项目类型投资数据列表]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getProjectTypeAnalyseData")
	public ModelMap getProjectTypeAnalyseData(ModelMap model, HttpServletRequest request){
		List<Map<String, ?>> list = analyseService.getProjectTypeAnalyseData();
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[获得年度项目类型投资数据列表]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getYearProjectTypeAnalyseData")
	public ModelMap getYearProjectTypeAnalyseData(ModelMap model, HttpServletRequest request){
		String year_s = request.getParameter("year_s");
		String year_e = request.getParameter("year_e");
		
		List<Map<String, ?>> list = analyseService.getYearProjectTypeAnalyseData(year_s, year_e);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[根据ID获得资金来源投资数据列表]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getZJAnalyseDataById")
	public ModelMap getZJAnalyseDataById(ModelMap model, HttpServletRequest request){
		String PROJECTID = request.getParameter("PROJECTID");
		
		List<Map<String, ?>> list = analyseService.getZJAnalyseDataById(PROJECTID);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[根据ID获得年度投资数据列表]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getNFXAnalyseDataById")
	public ModelMap getNFXAnalyseDataById(ModelMap model, HttpServletRequest request){
		String PROJECTID = request.getParameter("PROJECTID");
		
		List<Map<String, ?>> list = analyseService.getNFXAnalyseDataById(PROJECTID);
		model.put("list", list);
		
		return model;
	}
}
