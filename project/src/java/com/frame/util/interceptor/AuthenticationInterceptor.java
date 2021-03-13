package com.frame.util.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.frame.base.user.model.UserInfo;

/**
 * 
 * <p>[认证拦截器]<p>
 * @author yushp
 *
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger log = Logger.getLogger(AuthenticationInterceptor.class);
	
    private List<String> mapping = new ArrayList<String>();

    public List<String> getMapping(){
        return mapping;
    }

    public void setMapping(List<String> mapping){
        this.mapping = mapping;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception{
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception{
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {
        boolean ret = true;
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        
        String url = request.getServletPath();
        // "-"为无登录查询用户名
        if(url.equals("/base/index.do")&&(session.getAttribute("user")==null||"-".equals(((UserInfo)session.getAttribute("user")).getUSER_LOGIN()))){
        	ret = false;
        	response.sendRedirect(path + "/welcome/login.do");
        }else if(session.getAttribute("user")==null){
        	ret = false;
        	response.sendRedirect(path + "/welcome/timeOut.do");
        	log.debug("--->请求【"+request.getRequestURL()+"】, 请求超时，Session失效");
        }
        return ret;
    }

}
