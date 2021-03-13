package com.frame.base.menu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.menu.model.MenuInfo;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

/**
 * <p>[菜单DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="menuDao")
public class MenuDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得菜单列表]</p>
	 * @param user
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getMenuList(MenuInfo menu) {
		return this.queryPage("base_menu.getMenuList", "base_menu.getMenuListCount", menu, menu.getPage(), menu.getRows());
	}

	/**
	 * 
	 * <p>[新增菜单]</p>
	 * @param menu
	 * @author yushp
	 */
	public void insertMenu(MenuInfo menu) {
		this.insert("base_menu.insertMenu", menu);
	}

	/**
	 * 
	 * <p>[修改菜单]</p>
	 * @param menu
	 * @author yushp
	 */
	public void updateMenu(MenuInfo menu) {
		this.update("base_menu.updateMenu", menu);
	}

	/**
	 * 
	 * <p>[根据ID查询菜单信息]</p>
	 * @param ID
	 * @return
	 * @author yushp
	 */
	public MenuInfo getMenuUserById(String ID) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ID", ID);
		
		return this.queryForObject("base_menu.getMenuUserById", map);
	}

	/**
	 * 
	 * <p>[删除菜单]</p>
	 * @param iD
	 * @author yushp
	 */
	public void delMenu(String[] ID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: ID){
			Map<String, String> map = new HashMap<String, String>();
			map.put("ID", id);
			list.add(map);
		}
		
		this.batchDelete("base_menu.delMenu", list);
	}

}
