package com.frame.util.contextUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * <p>[WEB应用启动监听器]<p>
 * @author yushp
 *
 */
public class ContextListener implements ServletContextListener{
	
	/**
	 * 
	 * <p>[初始化方法]<p>
	 * @author yushp
	 * @param event
	 */
	public void contextInitialized(ServletContextEvent event){
		ContextWeb.setServletContext(event.getServletContext());
		ContextWeb.setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
	}
	
	/**
	 * 
	 * <p>[注销方法]<p>
	 * @author yushp
	 * @param event
	 */
	public void contextDestroyed(ServletContextEvent event){
		ContextWeb.setServletContext(null);
		ContextWeb.setApplicationContext(null);
	}
}
