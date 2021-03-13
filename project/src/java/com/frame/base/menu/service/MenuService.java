package com.frame.base.menu.service;


import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.base.menu.dao.MenuDao;
import com.frame.base.menu.model.MenuInfo;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;

/**
 * 
 * <p>[菜单业务类]</p>
 * @author yushp
 *
 */
@Service("menuService")
public class MenuService {
	
	@javax.annotation.Resource(name = "menuDao")
	private MenuDao menuDao;

	/**
	 * 
	 * <p>[获得菜单列表]</p>
	 * @param menu
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getMenuList(MenuInfo menu) {
		return menuDao.getMenuList(menu);
	}
	
	/**
	 * 
	 * <p>[保存菜单]</p>
	 * @param user
	 * @author yushp
	 */
	@Transactional
	public void saveMenuAddUdp(MenuInfo menu) {
		if(menu.getPID2()==null||"".equals(menu.getPID2())){
			menu.setPID2("0");
		}
		if(menu.getID()==null||"".equals(menu.getID())){
			menu.setID(UUIDUtil.getUUID());
			menuDao.insertMenu(menu);
		}else{
			menuDao.updateMenu(menu);
		}
	}

	/**
	 * 
	 * <p>[根据ID查询菜单信息]</p>
	 * @param ID
	 * @return
	 * @author yushp
	 */
	public MenuInfo getMenuUserById(String ID) {
		return menuDao.getMenuUserById(ID);
	}

	/**
	 * 
	 * <p>[删除菜单]</p>
	 * @param ID
	 * @author yushp
	 */
	@Transactional
	public void delMenu(String[] ID) {
		menuDao.delMenu(ID);
	}

}
