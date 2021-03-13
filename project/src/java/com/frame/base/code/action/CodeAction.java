package com.frame.base.code.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.code.model.CodeInfo;
import com.frame.base.code.service.CodeService;
import com.frame.comm.service.CommService;
import com.frame.util.page.Page;

/**
 * 
 * <p>[字典控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/base")
public class CodeAction {
	
	@Resource(name="commService")
	CommService commService;
	
	@javax.annotation.Resource(name = "codeService")
	private CodeService codeService;
	
	/**
	 * 
	 * <p>[请求字典页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/codePage")
	public String codePage(ModelMap model, HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", "code_type");
		List<Map<String, ?>> list = commService.getCode(map);
		model.put("types", list);
		
		return "web/base/code/jsp/codePage";
	}
	
	/**
	 * 
	 * <p>[获得字典列表]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getCodeList")
	public ModelMap getCodeList(CodeInfo code) {
		if(null==code.getSidx()||"".equals(code.getSidx())){
			code.setSidx("TYPE_ORD, ORD");
			code.setSord("ASC");
		}

		int rowInt = 10;
		int pageInt = 1;
		if (code.getRows()==0) {
			code.setRows(rowInt);
		}
		if (code.getPage()==0) {
			code.setPage(pageInt);
		}
		
		Page<HashMap<String, Object>> page = codeService.getCodeList(code);
		
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[请求新增修改字典页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getCodeAddUpdPage")
	public String getCodeAddUpdPage(ModelMap model, HttpServletRequest request){
		String CODE_ID = request.getParameter("CODE_ID");
		if(null!=CODE_ID&&!"".equals(CODE_ID)){
			CodeInfo code = codeService.getCodeById(CODE_ID);
			model.put("codeObj", code);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", "code_type");
		List<Map<String, ?>> list = commService.getCode(map);
		model.put("types", list);
		
		return "web/base/code/jsp/codeAddUpdPage";
	}
	
	/**
	 * 
	 * <p>[保存字典]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/saveCodeAddUdp")
	public ModelMap saveCodeAddUdp(CodeInfo code) {
		ModelMap model = new ModelMap();
		
		if(codeService.searchCodeOnly(code)){
			codeService.saveCodeAddUdp(code);
			model.put("only", "yes");
			model.put("CODE_ID", code.getCODE_ID());
		}else{
			model.put("only", "no");
		}
		
		return model;
	}
	
	/**
	 * 
	 * <p>[删除角色]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/delCode")
	public ModelMap delCode(ModelMap model, HttpServletRequest request){
		String[] CODE_ID = request.getParameterValues("CODE_ID[]");
		codeService.delCode(CODE_ID);
		model.put("res", true);
		
		return model;
	}
}
