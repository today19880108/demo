package com.frame.base.role.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.base.role.dao.RoleDao;
import com.frame.base.role.model.RoleInfo;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;

/**
 * 
 * <p>[角色业务类]</p>
 * @author yushp
 *
 */
@Service("roleService")
public class RoleService {
	
	@javax.annotation.Resource(name = "roleDao")
	private RoleDao roleDao;

	/**
	 * 
	 * <p>[获得角色列表]</p>
	 * @param role
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getRoleList(RoleInfo role) {
		return roleDao.getRoleList(role);
	}
	
	/**
	 * 
	 * <p>[保存角色]</p>
	 * @param user
	 * @author yushp
	 */
	@Transactional
	public void saveRoleAddUdp(RoleInfo role) {
		if(role.getID()==null||"".equals(role.getID())){
			role.setID(UUIDUtil.getUUID());
			roleDao.insertRole(role);
		}else{
			roleDao.updateRole(role);
		}
	}

	/**
	 * 
	 * <p>[根据ID查询角色信息]</p>
	 * @param ID
	 * @return
	 * @author yushp
	 */
	public RoleInfo getRoleUserById(String ID) {
		return roleDao.getRoleUserById(ID);
	}

	/**
	 * 
	 * <p>[删除角色]</p>
	 * @param ID
	 * @author yushp
	 */
	@Transactional
	public void delRole(String[] ID) {
		roleDao.delRole(ID);
	}

	/**
	 * 
	 * <p>[保存角色分配权限]</p>
	 * @param ROLE_ID
	 * @param MENU_ID
	 * @author yushp
	 */
	@Transactional
	public void saveMenu2Role(String ROLE_ID, String MENU_ID) {
		roleDao.delMenu2RoleByRoleId(ROLE_ID);
		roleDao.insertMenu2Role(ROLE_ID, MENU_ID);
	}

	/**
	 * 
	 * <p>[获得角色权限树形数据]</p>
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getMenu2RoleTreeData(String ROLE_ID) {
		return roleDao.getMenu2RoleTreeData(ROLE_ID);
	}

}
