package com.frame.base.user.service;


import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.base.user.dao.UserDao;
import com.frame.base.user.model.UserInfo;
import com.frame.util.ConfigManager;
import com.frame.util.Md5;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;

/**
 * 
 * <p>[用户业务类]</p>
 * @author yushp
 *
 */
@Service("userService")
public class UserService {
	
	@javax.annotation.Resource(name = "userDao")
	private UserDao userDao;

	/**
	 * 
	 * <p>[获得用户列表]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getUserList(UserInfo user) {
		return userDao.getUserList(user);
	}
	
	/**
	 * 
	 * <p>[根据ID获得用户对象]</p>
	 * @param USER_ID
	 * @return
	 * @author yushp
	 */
	public UserInfo getUserById(String USER_ID) {
		return userDao.getUserById(USER_ID);
	}
	
	/**
	 * 
	 * <p>[查询登录名唯一性]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public boolean searchUserOnly(UserInfo user) {
		int count = userDao.searchUserOnly(user);
		if(count > 0){
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>[保存用户]</p>
	 * @param user
	 * @author yushp
	 */
	@Transactional
	public void saveUserAddUdp(UserInfo user) {
		if(user.getUSER_ID()==null||"".equals(user.getUSER_ID())){
			user.setUSER_ID(UUIDUtil.getUUID());
			user.setUSER_PWD(Md5.getMD5(ConfigManager.getItemValue("/configuration/firtsPassword")));
			userDao.insertUser(user);
		}else{
			userDao.updateUser(user);
		}
	}

	/**
	 * 
	 * <p>[删除用户]</p>
	 * @param uSER_ID
	 * @author yushp
	 */
	@Transactional
	public void delUser(String[] USER_ID) {
		userDao.delUser(USER_ID);
	}

	/**
	 * 
	 * <p>[获得用户分配角色列表]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getRoleListByUserID(UserInfo user) {
		return userDao.getRoleListByUserID(user);
	}

	/**
	 * 
	 * <p>[保存用户分配角色]</p>
	 * @param uSER_ID
	 * @param rOLE_ID
	 * @param oprate
	 * @author yushp
	 */
	@Transactional
	public void oprateRole2User(String USER_ID, String[] ROLE_ID, String oprate) {
		if("1".equals(oprate)){
			userDao.delRole2User(USER_ID, ROLE_ID);
			userDao.insertRole2User(USER_ID, ROLE_ID);
		}else if("3".equals(oprate)){
			userDao.delRole2User(USER_ID, ROLE_ID);
		}
	}

}
