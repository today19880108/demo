package com.frame.base.login.action;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.base.login.service.LoginService;
import com.frame.base.user.model.UserInfo;
import com.frame.util.Base64;
import com.frame.util.Md5;
import com.frame.util.contextUtil.ContextUtil;

/**
 * 
 * <p>[登录、退出控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/welcome")
public class LoginAction {
	
	@javax.annotation.Resource(name = "loginService")
	private LoginService loginService;
	
	/**
	 * 
	 * <p>[系统登录]</p>
	 * @author yushp
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "web/base/login/jsp/login";
	}
	
	/**
	 * 
	 * <p>[系统超时]</p>
	 * @author yushp
	 */
	@RequestMapping("/timeOut")
	public String timeOut(HttpServletRequest request,HttpServletResponse response){
		return "timeOut";
	}
	
	/**
	 * 
	 * <p>[用户登录]</p>
	 * @author yushp
	 */
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request,HttpServletResponse response){
		try {
			String USER_LOGIN = ContextUtil.getRequestParamNoNull(request, "USER_LOGIN"); 
			String USER_PWD = ContextUtil.getRequestParamNoNull(request, "USER_PWD");
			//密码开始解码
			USER_PWD = new String(Base64.decode(USER_PWD));
			//密码结束解码
			String path = request.getContextPath();
			UserInfo user = loginService.getUserByLoginId(USER_LOGIN);
			
			UserInfo user_temp = new UserInfo();
			if(user==null){
				user_temp.setUSER_LOGIN(USER_LOGIN);
				user_temp.setUSER_PWD(USER_PWD);
				user_temp.setLoginError("1");
				ContextUtil.setSessionAttribute(request.getSession(), "user_temp", user_temp);
				response.sendRedirect(path + "/welcome/login.do");
			}else if(user.getUSER_PWD().equals(Md5.getMD5(USER_PWD))){
				ContextUtil.setSessionAttribute(request.getSession(), "user", user);
				response.sendRedirect(path + "/base/index.do");
			}else{
				user_temp.setUSER_LOGIN(USER_LOGIN);
				user_temp.setUSER_PWD(USER_PWD);
				user_temp.setLoginError("2");
				ContextUtil.setSessionAttribute(request.getSession(), "user_temp", user_temp);
				response.sendRedirect(path + "/welcome/login.do");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>[用户退出]</p>
	 * @author yushp
	 */
	@RequestMapping("/signOut")
	public void exit(HttpServletRequest request,HttpServletResponse response){
		try {
			//注销用户信息
			ContextUtil.setSessionAttribute(request.getSession(), "user", null);
			//注销用户临时信息
			ContextUtil.setSessionAttribute(request.getSession(), "user_temp", null);
			String path = request.getContextPath();
			response.sendRedirect(path + "/welcome/login.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
