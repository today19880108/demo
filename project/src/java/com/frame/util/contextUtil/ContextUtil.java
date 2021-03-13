package com.frame.util.contextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * <p>[请求、Session、应用上下文工具类]<p>
 * <p>[控制过程存取值]</p>
 * @author yushp
 *
 */
public class ContextUtil {
	
	/**
	 * 
	 * <p>[获取参数]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Object getRequestParam(HttpServletRequest request, String paramName){
		return request.getParameter(paramName);
	}
	public static String getRequestParamNoNull(HttpServletRequest request, String paramName){
		Object obj = request.getParameter(paramName);
		if(obj==null){
			return "";
		}
		return obj.toString();
	}
	
	/**
	 * 
	 * <p>[设置Request变量]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static void setRequestAttribute(HttpServletRequest request, String AttributeName, Object AttributeVal){
		request.setAttribute(AttributeName, AttributeVal);
	}
	
	/**
	 * 
	 * <p>[获取Request变量]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Object getRequestAttribute(HttpServletRequest request, String AttributeName){
		return request.getAttribute(AttributeName);
	}
	
	/**
	 * 
	 * <p>[设置Session变量]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static void setSessionAttribute(HttpSession session, String AttributeName, Object AttributeVal){
		session.setAttribute(AttributeName, AttributeVal);
	}
	
	/**
	 * 
	 * <p>[获取Request变量]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Object getSessionAttribute(HttpSession session, String AttributeName){
		return session.getAttribute(AttributeName);
	}
	
	/**
	 * 
	 * <p>[设置ServletContext变量]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static void setServletContextAttribute(String AttributeName, Object AttributeVal){
		ContextWeb.getServletContext().setAttribute(AttributeName, AttributeVal);
	}
	
	/**
	 * 
	 * <p>[获取ServletContext变量]<p>
	 * @author yushp
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Object getServletContextAttribute(String AttributeName){
		return ContextWeb.getServletContext().getAttribute(AttributeName);
	}
}
