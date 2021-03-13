package com.frame.business.demo1.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.util.ConfigManager;

/**
 * 
 * <p>[Demo1控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/business/demo1")
public class Demo1Action {
	
	/**
	 * 
	 * <p>[Demo1页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getDemo1Page")
	public String getDemo1Page(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("avatarPath", ConfigManager.getItemValue("/configuration/upload/avatarPath"));
		return "web/business/demo1/jsp/demo1";
	}
	
}
