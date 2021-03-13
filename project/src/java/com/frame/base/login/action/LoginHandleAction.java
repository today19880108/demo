package com.frame.base.login.action;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.login.service.LoginService;
import com.frame.base.user.model.UserInfo;
import com.frame.util.contextUtil.ContextUtil;
import com.frame.util.date.DateFormat;

/**
 * 
 * <p>[登录处理类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/base")
public class LoginHandleAction {
	
	@javax.annotation.Resource(name = "loginService")
	private LoginService loginService;
	
	/**
	 * 
	 * <p>[系统首页]</p>
	 * @author yushp
	 */
	@RequestMapping("/index")
	public String index(ModelMap model, HttpServletRequest request){
		model.put("showTime", DateFormat.getStringCurrentTime(new Date(), "yyyy年MM月dd日 EEEE"));
		return "web/base/login/jsp/index";
	}
	
	/**
	 * 
	 * <p>[内容首页]</p>
	 * @author yushp
	 */
	@RequestMapping("/indexHome")
	public String indexPage(HttpServletRequest request,HttpServletResponse response){
		return "web/base/login/jsp/indexHome";
	}
	
	/**
	 * 
	 * <p>[获得系统菜单树形数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getSysMenuTreeData")
	public ModelMap getSysMenuTreeData(ModelMap model, HttpServletRequest request){
		UserInfo user = (UserInfo) ContextUtil.getSessionAttribute(request.getSession(), "user");
		String USER_ID = user.getUSER_ID();
		
		List<Map<String, ?>> list = loginService.getSysMenuTreeData(USER_ID);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[修改密码页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getSetPwdPage")
	public String getSetPwdPage(HttpServletRequest request,HttpServletResponse response){
		return "web/base/login/jsp/setPwdPage";
	}
	
	/**
	 * 
	 * <p>[保存密码修改]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/saveSetPwd")
	public ModelMap saveSetPwd(ModelMap model, HttpServletRequest request) {
		String NEW_USER_PWD = request.getParameter("NEW_USER_PWD");
		
		UserInfo user = (UserInfo) ContextUtil.getSessionAttribute(request.getSession(), "user");
		String USER_ID = user.getUSER_ID();
		loginService.saveUserAddUdp(USER_ID, NEW_USER_PWD);
		
		model.put("res", true);
		return model;
	}
}
