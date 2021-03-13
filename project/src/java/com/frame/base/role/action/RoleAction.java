package com.frame.base.role.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.base.role.model.RoleInfo;
import com.frame.base.role.service.RoleService;
import com.frame.comm.service.CommService;
import com.frame.util.page.Page;

/**
 * 
 * <p>[角色控制类]</p>
 * @author yushp
 *
 */
@Controller
@RequestMapping("/base")
public class RoleAction {
	
	@Resource(name="commService")
	CommService commService;
	
	@javax.annotation.Resource(name = "roleService")
	private RoleService roleService;
	
	/**
	 * 
	 * <p>[请求角色页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/rolePage")
	public String rolePage(ModelMap model, HttpServletRequest request){
		return "web/base/role/jsp/rolePage";
	}
	
	/**
	 * 
	 * <p>[获得角色列表]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getRoleList")
	public ModelMap getRoleList(RoleInfo role) {
		if(null==role.getSidx()||"".equals(role.getSidx())){
			role.setSidx("ORD");
			role.setSord("ASC");
		}

		int rowInt = 10;
		int pageInt = 1;
		if (role.getRows()==0) {
			role.setRows(rowInt);
		}
		if (role.getPage()==0) {
			role.setPage(pageInt);
		}
		
		Page<HashMap<String, Object>> page = roleService.getRoleList(role);
		
		ModelMap model = new ModelMap();
		model.put("page", page);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[请求新增修改角色页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getRoleAddUpdPage")
	public String getRoleAddUpdPage(ModelMap model, HttpServletRequest request){
		String ID = request.getParameter("ID");
		if(null!=ID&&!"".equals(ID)){
			RoleInfo role = roleService.getRoleUserById(ID);
			model.put("roleObj", role);
		}
		return "web/base/role/jsp/roleAddUpdPage";
	}
	
	/**
	 * 
	 * <p>[保存角色]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/saveRoleAddUdp")
	public ModelMap saveRoleAddUdp(RoleInfo role) {
		ModelMap model = new ModelMap();
		roleService.saveRoleAddUdp(role);
		
		model.put("ID", role.getID());
		return model;
	}
	
	/**
	 * 
	 * <p>[删除角色]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/delRole")
	public ModelMap delRole(ModelMap model, HttpServletRequest request){
		String[] ID = request.getParameterValues("ID[]");
		roleService.delRole(ID);
		model.put("res", true);
		
		return model;
	}
	
	/*分配权限*/
	/**
	 * 
	 * <p>[请求角色分配权限页面]</p>
	 * @author yushp
	 */
	@RequestMapping("/getSetMenu2RolePage")
	public String getSetMenu2RolePage(ModelMap model, HttpServletRequest request){
		return "web/base/role/jsp/setMenu2RolePage";
	}
	
	/**
	 * 
	 * <p>[获得角色权限树形数据]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getMenu2RoleTreeData")
	public ModelMap getMenu2RoleTreeData(ModelMap model, HttpServletRequest request){
		String ROLE_ID = request.getParameter("ROLE_ID");
		
		List<Map<String, ?>> list = roleService.getMenu2RoleTreeData(ROLE_ID);
		model.put("list", list);
		
		return model;
	}
	
	/**
	 * 
	 * <p>[保存角色分配权限]</p>
	 * @param model
	 * @param request
	 * @return
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/saveMenu2Role")
	public ModelMap saveMenu2Role(ModelMap model, HttpServletRequest request) {
		String ROLE_ID = request.getParameter("ROLE_ID");
		String MENU_ID = request.getParameter("MENU_ID");
		
		roleService.saveMenu2Role(ROLE_ID, MENU_ID);
		
		return model;
	}
	/*分配权限*/
}
