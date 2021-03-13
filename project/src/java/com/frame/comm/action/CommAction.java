package com.frame.comm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.comm.service.CommService;
import com.frame.util.ConfigManager;

/**
 * 
 * <p>[公共控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/comm")
public class CommAction{
	
	@Resource(name="commService")
	CommService commService;
	
	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getCode")
	public ModelMap getCode(ModelMap model, HttpServletRequest request){
		String table_name = request.getParameter("name");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", table_name);
		List<Map<String, ?>> list = commService.getCode(map);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[获得字典表数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getCodeInAllTable")
	public ModelMap getCodeInAllTable(ModelMap model, HttpServletRequest request){
		String code_type = request.getParameter("code_type");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", code_type);
		List<Map<String, ?>> list = commService.getCodeInAllTable(map);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[获得树形数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getTreeData")
	public ModelMap getTreeData(ModelMap model, HttpServletRequest request){
		String table_name = request.getParameter("name");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", table_name);
		List<Map<String, ?>> list = commService.getTreeData(map);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[获得树形根节点数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getTreeDataOfTop")
	public ModelMap getTreeDataOfTop(ModelMap model, HttpServletRequest request){
		String table_name = request.getParameter("name");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", table_name);
		List<Map<String, ?>> list = commService.getTreeDataOfTop(map);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[根据父节点获得树形节点数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getTreeDataByPid")
	public ModelMap getTreeDataByPid(ModelMap model, HttpServletRequest request){
		String table_name = request.getParameter("name");
		String pid = request.getParameter("pid");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", table_name);
		map.put("pid", pid);
		List<Map<String, ?>> list = commService.getTreeDataByPid(map);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[根据配置标签路径获取配置值]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getConfigManagerPath")
	public ModelMap getConfigManagerPath(String tempPath){
		String path = ConfigManager.getItemValue(tempPath);
		
		ModelMap model = new ModelMap();
		model.put("path", path);
		
		return model;
	}
}
