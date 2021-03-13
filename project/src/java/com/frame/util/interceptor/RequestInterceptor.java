package com.frame.util.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.frame.base.user.model.UserInfo;
import com.frame.util.contextUtil.ContextUtil;

/**
 * 
 * <p>[请求记录拦截器]</p>
 * @author yushp
 *
 */
public class RequestInterceptor implements HandlerInterceptor {
	
	private static Logger log = Logger.getLogger(RequestInterceptor.class);
	
	private Date startDate;
	private Date endDate;
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		endDate = new Date();
		UserInfo user = (UserInfo) ContextUtil.getSessionAttribute(request.getSession(), "user");
		if(user!=null){
			log.debug("--->"+user.getUSER_NAME()+"("+user.getUSER_LOGIN()+")"+"请求【"+request.getRequestURL()+"】, 开始时间："+startDate+"，结束时间："+endDate+"，共用"+(endDate.getTime()-startDate.getTime())+"毫秒");
		}else{
			log.debug("--->未登录用户请求【"+request.getRequestURL()+"】, 开始时间："+startDate+"，结束时间："+endDate+"，共用"+(endDate.getTime()-startDate.getTime())+"毫秒");
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		startDate = new Date();
		return true;
	}
}
