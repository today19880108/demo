package com.frame.base.role.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.role.model.RoleInfo;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

/**
 * <p>[角色DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="roleDao")
public class RoleDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得角色列表]</p>
	 * @param role
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getRoleList(RoleInfo role) {
		return this.queryPage("base_role.getRoleList", "base_role.getRoleListCount", role, role.getPage(), role.getRows());
	}
	
	/**
	 * 
	 * <p>[新增角色]</p>
	 * @param role
	 * @author yushp
	 */
	public void insertRole(RoleInfo role) {
		this.insert("base_role.insertRole", role);
	}

	/**
	 * 
	 * <p>[修改角色]</p>
	 * @param role
	 * @author yushp
	 */
	public void updateRole(RoleInfo role) {
		this.update("base_role.updateRole", role);
	}

	/**
	 * 
	 * <p>[根据ID查询角色信息]</p>
	 * @param ID
	 * @return
	 * @author yushp
	 */
	public RoleInfo getRoleUserById(String ID) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ID", ID);
		
		return this.queryForObject("base_role.getRoleUserById", map);
	}

	/**
	 * 
	 * <p>[删除角色]</p>
	 * @param iD
	 * @author yushp
	 */
	public void delRole(String[] ID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: ID){
			Map<String, String> map = new HashMap<String, String>();
			map.put("ID", id);
			list.add(map);
		}
		
		this.batchDelete("base_role.delRole", list);
	}
	
	/**
	 * 
	 * <p>[根据角色删除角色权限]</p>
	 * @param ROLE_ID
	 * @author yushp
	 */
	public void delMenu2RoleByRoleId(String ROLE_ID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ROLE_ID", ROLE_ID);
		
		this.delete("base_role.delMenu2RoleByRoleId", map);
	}

	/**
	 * 
	 * <p>[插入角色分配权限]</p>
	 * @param ROLE_ID
	 * @param MENU_ID
	 * @author yushp
	 */
	public void insertMenu2Role(String ROLE_ID, String MENU_ID) {
		List<Map<String, Object>> MENU_IDList = new ArrayList<Map<String, Object>>();
		String[] MENU_ID_ = MENU_ID.split(",", -1);
		for(String m: MENU_ID_){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ROLE_ID", ROLE_ID);
			map.put("MENU_ID", m);
			System.out.println(m);
			MENU_IDList.add(map);
		}
		
		this.batchInsert("base_role.insertMenu2Role", MENU_IDList);
	}

	/**
	 * 
	 * <p>[获得角色权限树形数据]</p>
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getMenu2RoleTreeData(String ROLE_ID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ROLE_ID", ROLE_ID);
		
		return this.queryForList("base_role.getMenu2RoleTreeData", map);
	}

}
