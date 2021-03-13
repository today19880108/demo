package com.frame.base.menu.action;


import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.menu.model.MenuInfo;
import com.frame.base.menu.service.MenuService;
import com.frame.comm.service.CommService;
import com.frame.util.page.Page;

/**
 * 
 * <p>[菜单控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/base")
public class MenuAction {
	
	@Resource(name="commService")
	CommService commService;
	
	@javax.annotation.Resource(name = "menuService")
	private MenuService menuService;
	
	/**
	 * 
	 * <p>[请求菜单页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/menuPage")
	public String menuPage(ModelMap model, HttpServletRequest request){
		return "web/base/menu/jsp/menuPage";
	}
	
	/**
	 * 
	 * <p>[获得菜单列表]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getMenuList")
	public ModelMap getMenuList(MenuInfo menu) {
		if(null==menu.getSidx()||"".equals(menu.getSidx())){
			menu.setSidx("PID, ORD");
			menu.setSord("ASC");
		}

		int rowInt = 10;
		int pageInt = 1;
		if (menu.getRows()==0) {
			menu.setRows(rowInt);
		}
		if (menu.getPage()==0) {
			menu.setPage(pageInt);
		}
		
		Page<HashMap<String, Object>> page = menuService.getMenuList(menu);
		
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[请求新增修改菜单页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getMenuAddUpdPage")
	public String getMenuAddUpdPage(ModelMap model, HttpServletRequest request){
		String ID = request.getParameter("ID");
		if(null!=ID&&!"".equals(ID)){
			MenuInfo menu = menuService.getMenuUserById(ID);
			model.put("menuObj", menu);
		}
		return "web/base/menu/jsp/menuAddUpdPage";
	}
	
	/**
	 * 
	 * <p>[保存菜单]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/saveMenuAddUdp")
	public ModelMap saveMenuAddUdp(MenuInfo menu) {
		ModelMap model = new ModelMap();
		menuService.saveMenuAddUdp(menu);
		
		model.put("ID", menu.getID());
		return model;
	}
	
	/**
	 * 
	 * <p>[删除菜单]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/delMenu")
	public ModelMap delMenu(ModelMap model, HttpServletRequest request){
		String[] ID = request.getParameterValues("ID[]");
		menuService.delMenu(ID);
		model.put("res", true);
		
		return model;
	}
	
}
