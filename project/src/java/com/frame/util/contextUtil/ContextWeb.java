package com.frame.util.contextUtil;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * <p>[应用上下文类]<p>
 * @author yushp
 *
 */
public class ContextWeb {
	
	/**
	 * 应用上下文变量
	 */
	private static ServletContext servletContext;
	
	/**
	 * Spring上下文变量
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 
	 * <p>[获得应用上下文变量]<p>
	 * @author yushp
	 * @return
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * 
	 * <p>[设置应用上下文变量]<p>
	 * @author yushp
	 * @param servletContext
	 */
	public static void setServletContext(ServletContext servletContext) {
		ContextWeb.servletContext = servletContext;
	}

	/**
	 * 
	 * <p>[获得Spring上下文]<p>
	 * @author yushp
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		if(applicationContext==null){
			setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(servletContext));
		}
		return applicationContext;
	}

	/**
	 * 
	 * <p>[设置Spring上下文]<p>
	 * @author yushp
	 * @param applicationContext
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ContextWeb.applicationContext = applicationContext;
	}
	
}
