package com.frame.base.user.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.user.model.UserInfo;
import com.frame.base.user.service.UserService;
import com.frame.comm.service.CommService;
import com.frame.util.page.Page;

/**
 * 
 * <p>[用户控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/base")
public class UserAction {
	
	@Resource(name="commService")
	CommService commService;
	
	@javax.annotation.Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 
	 * <p>[请求用户页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/userPage")
	public String userPage(ModelMap model, HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "1");
		List<Map<String, ?>> list = commService.getCodeInAllTable(map);
		
		model.put("ISUSERList", list);
		return "web/base/user/jsp/userPage";
	}
	
	/**
	 * 
	 * <p>[获得用户列表]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getUserList")
	public ModelMap getUserList(UserInfo user) {
		if(null==user.getSidx()||"".equals(user.getSidx())){
			user.setSidx("ADD_TIME");
			user.setSord("ASC");
		}

		int rowInt = 10;
		int pageInt = 1;
		if (user.getRows()==0) {
			user.setRows(rowInt);
		}
		if (user.getPage()==0) {
			user.setPage(pageInt);
		}
		
		Page<HashMap<String, Object>> page = userService.getUserList(user);
		
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[请求新增修改用户页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getUserAddUpdPage")
	public String getUserAddUpdPage(ModelMap model, HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "1");
		List<Map<String, ?>> list = commService.getCodeInAllTable(map);
		
		model.put("ISUSERList", list);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("type", "2");
		List<Map<String, ?>> list2 = commService.getCodeInAllTable(map2);
		model.put("SEXList", list2);
		
		String USER_ID = request.getParameter("USER_ID");
		if(null!=USER_ID&&!"".equals(USER_ID)){
			UserInfo user = userService.getUserById(USER_ID);
			model.put("userObj", user);
		}
		
		return "web/base/user/jsp/userAddUpdPage";
	}
	
	/**
	 * 
	 * <p>[保存用户]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/saveUserAddUdp")
	public ModelMap saveUserAddUdp(UserInfo user) {
		ModelMap model = new ModelMap();
		// 查询登录名唯一性
		if(userService.searchUserOnly(user)){
			userService.saveUserAddUdp(user);
			model.put("only", "yes");
			model.put("USER_ID", user.getUSER_ID());
		}else{
			model.put("only", "not");
		}
		
		return model;
	}
	
	/**
	 * 
	 * <p>[删除用户]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/delUser")
	public ModelMap delUser(ModelMap model, HttpServletRequest request){
		String[] USER_ID = request.getParameterValues("USER_ID[]");
		userService.delUser(USER_ID);
		model.put("res", true);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[请求用户分配角色页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getSetRole2UserPage")
	public String getSetRole2UserPage(ModelMap model, HttpServletRequest request){
		return "web/base/user/jsp/setRole2UserPage";
	}
	
	/**
	 * 
	 * <p>[获得用户分配角色列表]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getRoleListByUserID")
	public ModelMap getRoleListByUserID(UserInfo user) {
		if(null==user.getSidx()||"".equals(user.getSidx())){
			user.setSidx("ORD");
			user.setSord("ASC");
		}

		int rowInt = 10;
		int pageInt = 1;
		if (user.getRows()==0) {
			user.setRows(rowInt);
		}
		if (user.getPage()==0) {
			user.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = userService.getRoleListByUserID(user);
		
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[保存用户分配角色]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/oprateRole2User")
	public ModelMap oprateRole2User(ModelMap model, HttpServletRequest request) {
		String USER_ID = request.getParameter("USER_ID");
		String[] ROLE_ID = request.getParameterValues("ROLE_ID[]");
		String oprate = request.getParameter("oprate");
		
		userService.oprateRole2User(USER_ID, ROLE_ID, oprate);
		model.put("res", true);
		return model;
	}
	
}
