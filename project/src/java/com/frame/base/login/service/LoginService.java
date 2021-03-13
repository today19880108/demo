package com.frame.base.login.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.login.dao.LoginDao;
import com.frame.base.user.model.UserInfo;
import com.frame.util.ConfigManager;
import com.frame.util.Md5;

/**
 * 
 * <p>[登录业务类]</p>
 * @author yushp
 *
 */
@Service("loginService")
public class LoginService {
	
	@javax.annotation.Resource(name = "loginDao")
	private LoginDao loginDao;

	/**
	 * 
	 * <p>[获得系统菜单树形数据]</p>
	 * @param USER_ID
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getSysMenuTreeData(String USER_ID) {
		return loginDao.getSysMenuTreeData(USER_ID);
	}

	/**
	 * 
	 * <p>[根据登录名查询用户]</p>
	 * @param USER_LOGIN
	 * @return
	 * @author yushp
	 */
	public UserInfo getUserByLoginId(String USER_LOGIN) {
		return loginDao.getUserByLoginId(USER_LOGIN);
	}

	/**
	 * 
	 * <p>[保存密码修改]</p>
	 * @param NEW_USER_PWD
	 * @author yushp
	 */
	public void saveUserAddUdp(String USER_ID, String NEW_USER_PWD) {
		NEW_USER_PWD = Md5.getMD5(NEW_USER_PWD);
		loginDao.saveUserAddUdp(USER_ID, NEW_USER_PWD);
	}
}
