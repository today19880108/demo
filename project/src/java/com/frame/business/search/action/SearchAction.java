package com.frame.business.search.action;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.base.user.model.UserInfo;
import com.frame.comm.service.CommService;
import com.frame.util.date.DateFormat;

/**
 * 
 * <p>[无登录查询控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/search")
public class SearchAction {
	
	@Resource(name = "commService")
	CommService commService;
	
	/**
	 * 
	 * <p>[无登录查询页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/searchNologin")
	public String searchNologin(ModelMap model, HttpServletResponse response){
		model.put("showTime", DateFormat.getStringCurrentTime(new Date(), "yyyy年MM月dd日 EEEE"));
		return "web/business/search/jsp/searchNologin";
	}
	
	/**
	 * 
	 * <p>
	 * [请求项目页面]
	 * </p>
	 * 
	 * @author linym
	 */
	@RequestMapping("/projectManagePage")
	public String projectManagePage(ModelMap model, HttpServletRequest request) {
		//设置无登录查询用户
		UserInfo user = new UserInfo();
		user.setUSER_NAME("无登录查询临时用户");
		user.setUSER_LOGIN("-");
		request.getSession().setAttribute("user", user);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		List<Integer> now_yearList = new ArrayList<Integer>();
		for(int i = year; i>=2000; i--){
			now_yearList.add(i);
		}
		model.put("now_yearList", now_yearList);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("type", "3");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map2);
		map2.put("type", "4");
		List<Map<String, ?>> list2 = commService.getCodeInAllTable(map2);
		map2.put("table_name", "projecttype");
		List<Map<String, ?>> list3 = commService.getTreeDataOfTop(map2);
		map2.put("type", "8");
		List<Map<String, ?>> list4 = commService.getCodeInAllTable(map2);
		map2.put("type", "7");
		List<Map<String, ?>> list5 = commService.getCodeInAllTable(map2);
		model.put("localgoverments", list1);
		model.put("constructionNatures", list2);
		model.put("projecttype", list3);
		model.put("managementoffices", list4);
		model.put("projectstageList", list5);
		
		return "web/business/project/jsp/projectManage";
	}
	
}
