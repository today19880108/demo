package com.frame.base.login.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.user.model.UserInfo;
import com.frame.util.dao.BaseDaoImpl;

/**
 * 
 * <p>[登录DAO类]</p>
 * @author yushp
 *
 */
@Service("loginDao")
public class LoginDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得系统菜单树形数据]</p>
	 * @param USER_ID
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getSysMenuTreeData(String USER_ID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("USER_ID", USER_ID);
		
		return this.queryForList("base_login.getSysMenuTreeData", map);
	}

	/**
	 * 
	 * <p>[根据登录名查询用户]</p>
	 * @param USER_LOGIN
	 * @return
	 * @author yushp
	 */
	public UserInfo getUserByLoginId(String USER_LOGIN) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("USER_LOGIN", USER_LOGIN);
		
		return this.queryForObject("base_login.getUserByLoginId", map);
	}

	/**
	 * 
	 * <p>[保存密码修改]</p>
	 * @param NEW_USER_PWD
	 * @author yushp
	 */
	public void saveUserAddUdp(String USER_ID, String NEW_USER_PWD) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("USER_ID", USER_ID);
		map.put("USER_PWD", NEW_USER_PWD);
		
		this.update("base_login.saveUserAddUdp", map);
	}
	
	
}
