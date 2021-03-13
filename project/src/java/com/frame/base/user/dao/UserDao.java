package com.frame.base.user.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.user.model.UserInfo;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

/**
 * <p>[用户DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="userDao")
public class UserDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得用户列表]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getUserList(UserInfo user) {
		return this.queryPage("base_user.getUserList", "base_user.getUserListCount", user, user.getPage(), user.getRows());
	}
	
	/**
	 * 
	 * <p>[根据ID获得用户对象]</p>
	 * @param USER_ID
	 * @return
	 * @author yushp
	 */
	public UserInfo getUserById(String USER_ID) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("USER_ID", USER_ID);
		
		return this.queryForObject("base_user.getUserById", map);
	}
	
	/**
	 * 
	 * <p>[查询登录名唯一性]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public int searchUserOnly(UserInfo user) {
		return this.queryForObject("base_user.searchUserOnly", user);
	}

	/**
	 * 
	 * <p>[插入用户]</p>
	 * @param user
	 * @author yushp
	 */
	public void insertUser(UserInfo user) {
		this.insert("base_user.insertUser", user);
	}

	/**
	 * 
	 * <p>[修改用户]</p>
	 * @param user
	 * @author yushp
	 */
	public void updateUser(UserInfo user) {
		this.update("base_user.updateUser", user);
	}

	/**
	 * 
	 * <p>[删除用户]</p>
	 * @param uSER_ID
	 * @author yushp
	 */
	public void delUser(String[] USER_ID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: USER_ID){
			Map<String, String> map = new HashMap<String, String>();
			map.put("USER_ID", id);
			list.add(map);
		}
		
		this.batchDelete("base_user.delUser", list);
	}

	/**
	 * 
	 * <p>[获得用户分配角色列表]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getRoleListByUserID(UserInfo user) {
		return this.queryPage("base_user.getRoleListByUserID", "base_user.getRoleListByUserIDCount", user, user.getPage(), user.getRows());
	}

	/**
	 * 
	 * <p>[插入用户分配角色]</p>
	 * @param uSER_ID
	 * @param rOLE_ID
	 * @author yushp
	 */
	public void insertRole2User(String USER_ID, String[] ROLE_ID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: ROLE_ID){
			Map<String, String> map = new HashMap<String, String>();
			map.put("USER_ID", USER_ID);
			map.put("ROLE_ID", id);
			list.add(map);
		}
		
		this.batchDelete("base_user.insertRole2User", list);
	}

	/**
	 * 
	 * <p>[删除用户分配角色]</p>
	 * @param uSER_ID
	 * @param rOLE_ID
	 * @author yushp
	 */
	public void delRole2User(String USER_ID, String[] ROLE_ID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: ROLE_ID){
			Map<String, String> map = new HashMap<String, String>();
			map.put("USER_ID", USER_ID);
			map.put("ROLE_ID", id);
			list.add(map);
		}
		
		this.batchDelete("base_user.delRole2User", list);
	}

}
