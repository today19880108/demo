package com.frame.business.project.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.business.project.model.Investment;
import com.frame.business.project.model.project;
import com.frame.business.project.service.ProjectService;
import com.frame.comm.service.CommService;
import com.frame.util.ConfigManager;
import com.frame.util.UUIDUtil;
import com.frame.util.fileUploadDownUtil.BuildExcelTools;
import com.frame.util.fileUploadDownUtil.FileDownUtil;
import com.frame.util.page.Page;

/**
 * @function 项目类（项目增删改查业务逻辑类）
 * @author castle-lin
 * 
 */
@Controller
@RequestMapping("/business")
public class ProjectAction {

	@javax.annotation.Resource(name = "projectService")
	private ProjectService projectService;

	@Resource(name = "commService")
	CommService commService;

	/**
	 * 
	 * <p>
	 * [请求项目页面]
	 * </p>
	 * 
	 * @author linym
	 */
	@RequestMapping("/projectManagePage")
	public String projectManagePage(ModelMap model, HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		List<Integer> now_yearList = new ArrayList<Integer>();
		for(int i = year; i>=2000; i--){
			now_yearList.add(i);
		}
		model.put("now_yearList", now_yearList);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("type", "3");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map2);
		map2.put("type", "4");
		List<Map<String, ?>> list2 = commService.getCodeInAllTable(map2);
		map2.put("table_name", "projecttype");
		List<Map<String, ?>> list3 = commService.getTreeDataOfTop(map2);
		map2.put("type", "8");
		List<Map<String, ?>> list4 = commService.getCodeInAllTable(map2);
		map2.put("type", "7");
		List<Map<String, ?>> list5 = commService.getCodeInAllTable(map2);
		model.put("localgoverments", list1);
		model.put("constructionNatures", list2);
		model.put("projecttype", list3);
		model.put("managementoffices", list4);
		model.put("projectstageList", list5);
		
		return "web/business/project/jsp/projectManage";
	}

	/**
	 * 
	 * <p>
	 * [新增项目页面]
	 * </p>
	 * 
	 * @author linym
	 * @throws Exception
	 */
	@RequestMapping("/addProjectPage")
	public String addProjectPage(ModelMap model, HttpServletRequest request)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		List<Integer> now_yearList = new ArrayList<Integer>();
		for(int i = year; i>=2000; i--){
			now_yearList.add(i);
		}
		model.put("now_yearList", now_yearList);
		
		String projectid = request.getParameter("projectid");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "3");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map);
		map.put("type", "4");
		List<Map<String, ?>> list2 = commService.getCodeInAllTable(map);
		map.put("table_name", "projecttype");
		List<Map<String, ?>> list3 = commService.getTreeDataOfTop(map);
		map.put("type", "8");
		List<Map<String, ?>> list4 = commService.getCodeInAllTable(map);
		map.put("type", "7");
		List<Map<String, ?>> list5 = commService.getCodeInAllTable(map);
		model.put("localgoverments", list1);
		model.put("constructionNatures", list2);
		model.put("projecttype", list3);
		model.put("managementoffices", list4);
		model.put("projectstageList", list5);
		
		if(projectid != null && !"".equals(projectid)) {
			Map<String, String> map3 = new HashMap<String, String>();
			map3.put("projectid", projectid);
			project p2 = projectService.queryProjectDetail(map3);
			
			// 辖区政府名称设置
			Object o = p2.getLocalgoverment();
			if(null!=o&&!"".equals(o)){
				String[] array = o.toString().split(",");
				String str = "";
				for(String oo: array){
					String name = "";
					for(Map<String, ?> ooo: list1){
						if(oo.equals(ooo.get("ID"))){
							name = ooo.get("NAME").toString();
							break;
						}
					}
					if(name.length() > 0){
						str+=name+",";
					}
				}
				if(str.length() > 0){
					str = str.substring(0, str.length()-1);
				}
				p2.setLocalgoverment_name(str);
			}
			
			model.put("selectp", p2);
		}

		return "web/business/project/jsp/addProjectPage";
	}

	/**
	 * 
	 * <p>
	 * [请求新增投资页面]
	 * </p>
	 * 
	 * @author linym
	 * @throws ParseException
	 */
	@RequestMapping("/addIvestmentPage")
	public String addIvestmentPage(ModelMap model, HttpServletRequest request)
			throws ParseException {
		String investmentid = request.getParameter("investmentid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "5");
		List<Map<String, ?>> list4 = commService.getCodeInAllTable(map);
		model.put("fundings", list4);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		model.put("year", calendar.get(Calendar.YEAR));
		
		if (investmentid != null && !"".equals(investmentid)) {
			Map<String, String> map3 = new HashMap<String, String>();
			map3.put("investmentid", investmentid);
			Investment p2 = projectService.queryInvestDetail(map3);
			model.put("selectp", p2);
		}
		String projectid = request.getParameter("projectid");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("projectid", projectid);
		project p = projectService.queryProjectDetail(map2);
		model.put("selectpr", p);
		return "web/business/project/jsp/addInvestmentPage";
	}

	/**
	 * @function 增加项目
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addProject")
	public ModelMap addProject(ModelMap model, HttpServletRequest request) {
		String projectid = "";
		String projectName = "";
		String responsibilityUnit = "";
		String localgoverment = "";
		String constructionNature = "";
		String starttime = "";
		String projecttype = "";
//		String totalinvestment = "";
		String constructionContent = "";
		String cityleaders = "";
//		String isyearplan = "";
		String planyear = "";
		String insteadunit = "";
		String managementoffice = "";
//		String projecttype2 = "";
		String endtime = "";
//		String actualinvest = "";
		String remark = "";
		String projectstage = "";
		
		projectid = request.getParameter("projectid");
		projectName = request.getParameter("projectname");
		responsibilityUnit = request.getParameter("responsibilityunit");
		localgoverment = request.getParameter("localgoverment");
		constructionNature = request.getParameter("constructionnature");
		starttime = request.getParameter("starttime");
		projecttype = request.getParameter("projecttype");
//		totalinvestment = request.getParameter("totalinvestment");
		constructionContent = request.getParameter("constructioncontent");
		cityleaders = request.getParameter("cityleaders");
//		isyearplan = request.getParameter("isyearplan");
		planyear = request.getParameter("planyear");
		insteadunit = request.getParameter("insteadunit");
		managementoffice = request.getParameter("managementoffice");
//		projecttype2 = request.getParameter("projecttype2");
		endtime = request.getParameter("endtime");
//		actualinvest = request.getParameter("actualinvest");
		remark = request.getParameter("remark");
		projectstage = request.getParameter("projectstage");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectname", projectName);
		map.put("responsibilityunit", responsibilityUnit);
		map.put("localgoverment", localgoverment);
		map.put("constructionnature", constructionNature);
		map.put("starttime", starttime);
		map.put("projecttype", projecttype);
//		map.put("totalinvestment", totalinvestment);
		map.put("constructioncontent", constructionContent);
		map.put("cityleaders", cityleaders);
//		map.put("isyearplan", isyearplan);
		map.put("planyear", planyear);
		map.put("insteadunit", insteadunit);
		map.put("managementoffice", managementoffice);
//		map.put("projecttype2", projecttype2);
		map.put("endtime", endtime);
//		map.put("actualinvest", actualinvest);
		map.put("remark", remark);
		map.put("projectstage", projectstage);
		
		if(projectid==null||"".equals(projectid)){
			projectid = UUIDUtil.getUUID();
			map.put("projectid", projectid);
			projectService.insertNewProject(map);
		}else{
			map.put("projectid", projectid);
			projectService.updateProject(map);
		}
		
		model.put("projectid", projectid);
			
		return model;
	}

	/**
	 * @function 增加投资
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addInvest")
	public ModelMap addInvest(ModelMap model, HttpServletRequest request) {
		String investmenttime = request.getParameter("investmenttime");
		String investmentid = request.getParameter("investmentid");
		String projectid = request.getParameter("projectid");
		String funding = request.getParameter("funding");
//		String planinvest = request.getParameter("planinvest");
		String actualinvest = request.getParameter("actualinvest");
//		String investtarget = request.getParameter("investtarget");
		String referencename = request.getParameter("referencename");
		String referencecode = request.getParameter("referencecode");
		String remarks = request.getParameter("remarks");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("investmenttime", investmenttime);
		map.put("projectid", projectid);
		map.put("funding", funding);
//		map.put("planinvest", planinvest);
		map.put("actualinvest", actualinvest);
//		map.put("investtarget", investtarget);
		map.put("referencename", referencename);
		map.put("referencecode", referencecode);
		map.put("remarks", remarks);
		
		if(investmentid==null||"".equals(investmentid)){
			investmentid = UUIDUtil.getUUID();
			map.put("investmentid", investmentid);
			projectService.insertInvestment(map);
		}else{
			map.put("investmentid", investmentid);
			projectService.updateInvst(map);
		}
		
		model.put("investmentid", investmentid);
		return model;
	}

	/**
	 * @function 增加项目附件
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/addAttach")
	public void addAttach(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 构造一个文件上传处理对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			response.setContentType("text/html;charset=UTF-8");
			Iterator items;

			try {
				// 解析表单中提交的所有文件内容
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 取出上传文件的文件名称
						String name = item.getName();
						// 取得上传文件以后的存储路径
						String fileName = System.currentTimeMillis()+"_"+name.substring(name.lastIndexOf('\\') + 1, name.length());
						// 上传文件以后的存储路径
						String sysPath = request.getSession()
								.getServletContext().getRealPath("/");
						String a = ConfigManager.getItemValue("/configuration/uploadfile/avatarPath");
						sysPath += File.separator + a;
						
						if (!(new File(sysPath).exists())) {
							new File(sysPath).mkdir();
						}
						// 上传文件
						File uploaderFile = new File(sysPath + File.separator
								+ fileName);
						item.write(uploaderFile);
						
						Map<String, String> map = new HashMap<String, String>();
						String projectid = request.getParameter("projectid");
						map.put("projectid", projectid);
						map.put("attachmentid", UUIDUtil.getUUID());
						map.put("filepath", fileName);
						projectService.insertAttachment(map);

						response.getWriter().append(fileName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("1");
			} finally {
				response.getWriter().close();
			}
		}

	}

	/**
	 * @function 查询项目
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryProject")
	public ModelMap queryProject(project p) {
		if(null==p.getSidx()||"".equals(p.getSidx())){
			p.setSidx("ORDFLAG,PLANYEAR");
			p.setSord("ASC");
		}
		
		int rowInt = 10;
		int pageInt = 1;

		if (p.getRows() == 0) {
			p.setRows(rowInt);
		}
		if (p.getPage() == 0) {
			p.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = projectService.queryProject(p);
		
		// 辖区政府名称设置
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "3");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map);
		List<HashMap<String, Object>> recList = page.getRecords();
		for(HashMap<String, Object> m: recList){
			Object o = m.get("LOCALGOVERMENT");
			if(null!=o&&!"".equals(o)){
				String[] array = o.toString().split(",");
				String str = "";
				for(String oo: array){
					String name = "";
					for(Map<String, ?> ooo: list1){
						if(oo.equals(ooo.get("ID"))){
							name = ooo.get("NAME").toString();
							break;
						}
					}
					if(name.length() > 0){
						str+=name+",";
					}
				}
				if(str.length() > 0){
					str = str.substring(0, str.length()-1);
				}
				m.put("LOCALGOVERMENT_NAME", str);
			}
		}
		
		ModelMap model = new ModelMap();
		model.put("page", page);
		return model;
	}

	/**
	 * @function 查询投资
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryInvest")
	public ModelMap queryInvest(Investment v, HttpServletRequest request) {
		if(null==v.getSidx()||"".equals(v.getSidx())){
			v.setSidx("INVESTMENTTIME");
			v.setSord("ASC");
		}
		
		int rowInt = 10;
		int pageInt = 1;

		String projectid = request.getParameter("projectid");
		if (projectid != null&&!"".equals(projectid)) {
			v.setProjectid(projectid);
		}
		if (v.getRows()==0) {
			v.setRows(rowInt);
		}
		if (v.getPage()==0) {
			v.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = projectService.queryInvest(v);

		ModelMap model = new ModelMap();
		model.put("page", page);
		return model;
	}

	/**
	 * @function 删除项目
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delectProject")
	public ModelMap delectProject(ModelMap model, HttpServletRequest request) {
		String[] projectids = request.getParameterValues("projectid[]");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (String id : projectids) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("projectid", id);
			list.add(map);
		}
		projectService.delProject(list);
		model.put("res", true);
		return model;
	}

	/**
	 * @function 删除投资
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delectInvestment")
	public ModelMap delectInvestment(ModelMap model, HttpServletRequest request) {
		String[] investmentids = request.getParameterValues("investmentid[]");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (String id : investmentids) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("investmentid", id);
			list.add(map);
		}
		projectService.delInvest(list);
		return model;
	}

	/**
	 * 
	 * <p>[下载附件]</p>
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author yushp
	 */
	@RequestMapping("/downloadByFilePath")
	public void downloadByFilePath(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String filepath = java.net.URLDecoder.decode(request.getParameter("filepath"),"UTF-8");
		String aa = ConfigManager.getItemValue("/configuration/uploadfile/avatarPath");
		FileDownUtil.dowloadFile(aa, filepath, request, response);
	}
	
	/**
	 * @function	文件导入跳转
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/fileImportPage")
	public String fileImportPage(ModelMap model, HttpServletRequest request) {
		return "web/business/project/jsp/fileImportPage";
	}
	
	/**
	 * @function	下载模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadTemplet")
	public void downloadTemplet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String aa = ConfigManager.getItemValue("/configuration/uploadfile/avatarPath");
		FileDownUtil.dowloadFile(aa, "项目信息导入模板下载.xls", request, response);
	}
	
	/**
	 * @function导入文件
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fileImport")
	public void fileImport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 构造一个文件上传处理对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			response.setContentType("text/html;charset=UTF-8");
			Iterator items = null;
			File uploaderFile = null;
			try {
				// 解析表单中提交的所有文件内容
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 取出上传文件的文件名称
						String name = item.getName();
						// 取得上传文件以后的存储路径
						String fileName = System.currentTimeMillis()
								+ "_"
								+ name.substring(name.lastIndexOf('\\') + 1,
										name.length());
						// 上传文件以后的存储路径
						String sysPath = request.getSession()
								.getServletContext().getRealPath("/");
						String a = ConfigManager
								.getItemValue("/configuration/uploadfile/avatarPath");
						sysPath += File.separator + a;
						if (!(new File(sysPath).isDirectory())) {
							new File(sysPath).mkdir();
						}
						// 上传文件
						uploaderFile = new File(sysPath + File.separator
								+ fileName);
						item.write(uploaderFile);
						// 根据其名称获取后缀
						String extension = fileName.substring(fileName
								.lastIndexOf(".") + 1);
						List<project> allList = new ArrayList<project>();
						allList = FileDownUtil.parseAllBill(uploaderFile, extension);
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("allList", allList);
						
						//获取字典表数据
						Map<String, String> map2 = new HashMap<String, String>();
						Map<String, String> gov = new HashMap<String, String>();
						Map<String, String> nature = new HashMap<String, String>();
						Map<String, String> managementoffice = new HashMap<String, String>();
						Map<String, String> projectstage = new HashMap<String, String>();
						Map<String, String> projecttype = new HashMap<String, String>();
						map2.put("type", "3");
						List<Map<String, ?>> list1 = commService.getCodeInAllTable(map2);
						for(int i=0;i<list1.size();i++)
						{
							Map<String,String> m=(Map<String, String>) list1.get(i);
							gov.put(m.get("NAME"), m.get("ID"));
						}
						map2.clear();
						map2.put("type", "4");
						List<Map<String, ?>> list2 = commService.getCodeInAllTable(map2);
						for(int i=0;i<list2.size();i++)
						{
							Map<String,String> m=(Map<String, String>) list2.get(i);
							nature.put(m.get("NAME"), m.get("ID"));
						}
						map2.clear();
						map2.put("type", "8");
						List<Map<String, ?>> list3 = commService.getCodeInAllTable(map2);
						for(int i=0;i<list3.size();i++)
						{
							Map<String,String> m=(Map<String, String>) list3.get(i);
							managementoffice.put(m.get("NAME"), m.get("ID"));
						}
						map2.clear();
						map2.put("table_name", "projecttype");
						List<Map<String, ?>> list4 = commService.getCode(map2);
						for(int i=0;i<list4.size();i++)
						{
							Map<String,String> m=(Map<String, String>) list4.get(i);
							projecttype.put(m.get("NAME"), m.get("ID"));
						}
						map2.clear();
						map2.put("type", "7");
						List<Map<String, ?>> list5 = commService.getCodeInAllTable(map2);
						for(int i=0;i<list5.size();i++)
						{
							Map<String,String> m=(Map<String, String>) list5.get(i);
							projectstage.put(m.get("NAME"), m.get("ID"));
						}
						
						//循环将导入中的列名换成字典表中 对应的id
						for(project t : allList)
						{
							String localgovermentV = t.getLocalgoverment();
							if(localgovermentV!=null&&!localgovermentV.equals(""))
							{
								String res = "";
								String[] localV = localgovermentV.split(",");
								for(String local: localV){
									if(gov.containsKey(local))
									{
										res+=gov.get(local)+",";
									}
								}
								if(res.length() > 0){
									res = res.substring(0, res.length()-1);
								}
								t.setLocalgoverment(res);
							}
							String constructionnatureV = t.getConstructionnature();
							if(constructionnatureV!=null&&!constructionnatureV.equals(""))
							{
								if(nature.containsKey(constructionnatureV))
								{
									String kk=nature.get(constructionnatureV);
									t.setConstructionnature(kk);
								}
								else
								{
									t.setConstructionnature(null);
								}
							}
							String managementofficeV = t.getManagementoffice();
							if(managementofficeV!=null&&!managementofficeV.equals(""))
							{
								if(managementoffice.containsKey(managementofficeV))
								{
									String kk=managementoffice.get(managementofficeV);
									t.setManagementoffice(kk);
								}
								else
								{
									t.setManagementoffice(null);
								}
							}
							String projectstageV = t.getProjectstage();
							if(projectstageV!=null&&!projectstageV.equals(""))
							{
								if(projectstage.containsKey(projectstageV))
								{
									String kk=projectstage.get(projectstageV);
									t.setProjectstage(kk);
								}
								else
								{
									t.setProjectstage(null);
								}
							}
							String projecttypeV = t.getProjecttype();
							if(projecttypeV!=null&&!projecttypeV.equals(""))
							{
								if(projecttype.containsKey(projecttypeV))
								{
									String kk=projecttype.get(projecttypeV);
									t.setProjecttype(kk);
								}
								else
								{
									t.setProjecttype(null);
								}
							}
						}
						
						if(!allList.isEmpty()){
							projectService.insertFileImport(params);
						}
						response.getWriter().append("0");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("1");
			} finally {
				response.getWriter().close();
				if(uploaderFile!=null){
					uploaderFile.delete();
				}
			}
		}
	}
	
	/**
	 * @function	导出项目excel
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/exportProject")
	public void exportProject(project p, HttpServletRequest request,HttpServletResponse response) {
		if(null==p.getSidx()||"".equals(p.getSidx())){
			p.setSidx("ORDFLAG,PLANYEAR");
			p.setSord("ASC");
		}
		
		List<Map<String, ?>> data = null;
		data = projectService.searchData("business.queryProjectList", p);
		// 处理 年度大计划和项目类别
//		for(Map<String, ?> mm : data){
//			Map<String, Object> m = (Map<String, Object>)mm;
//			// 年度大计划
//			String res = "";
//			Object ISYEARPLAN = m.get("ISYEARPLAN");
//			Object ISYEARPLAN_NAME = m.get("ISYEARPLAN_NAME");
//			Object PLANYEAR = m.get("PLANYEAR");
//			if(ISYEARPLAN!=null&&!"".equals(ISYEARPLAN)){
//				if("1".equals(ISYEARPLAN)&&PLANYEAR!=null&&!"".equals(PLANYEAR)){
//					res+=ISYEARPLAN_NAME+"【"+PLANYEAR+"】";
//				}else{
//					res+=ISYEARPLAN_NAME;
//				}
//			}
//			m.put("ISYEARPLAN_NAME", res);
//			// 项目类别
//			String str = "";
//			Object PROJECTTYPE = m.get("PROJECTTYPE");
//			Object PROJECTTYPE_NAME = m.get("PROJECTTYPE_NAME");
//			Object PROJECTTYPE2 = m.get("PROJECTTYPE2");
//			Object PROJECTTYPE2_NAME = m.get("PROJECTTYPE2_NAME");
//			if(PROJECTTYPE!=null&&!"".equals(PROJECTTYPE)){
//				if(PROJECTTYPE2!=null&&!"".equals(PROJECTTYPE2)){
//					str+=PROJECTTYPE_NAME+"【"+PROJECTTYPE2_NAME+"】";
//				}else{
//					str+=PROJECTTYPE_NAME;
//				}
//			}
//			m.put("PROJECTTYPE_NAME", str);
//		}
		// 辖区政府名称设置
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "3");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map);
		for(Map<String, ?> mm : data){
			Map<String, Object> m = (Map<String, Object>)mm;
			Object o = m.get("LOCALGOVERMENT");
			if(null!=o&&!"".equals(o)){
				String[] array = o.toString().split(",");
				String str = "";
				for(String oo: array){
					String name = "";
					for(Map<String, ?> ooo: list1){
						if(oo.equals(ooo.get("ID"))){
							name = ooo.get("NAME").toString();
							break;
						}
					}
					if(name.length() > 0){
						str+=name+",";
					}
				}
				if(str.length() > 0){
					str = str.substring(0, str.length()-1);
				}
				m.put("LOCALGOVERMENT_NAME", str);
			}
		}
		
		ArrayList<Object> exportData = new ArrayList<Object>();
		ArrayList<Object> exportInfo = new ArrayList<Object>();
		if (data != null){
			exportData.add(data);
			Map<String, Object> dataInfo = new HashMap<String, Object>();
			dataInfo.put("SHEET_NAME", "第一页");
			dataInfo.put("SHEET_TITLE", "项目列表");
			dataInfo.put("BODY_TITLE", createProjectTitleMap());
			exportInfo.add(dataInfo);
		}
		
		BuildExcelTools.buildExcel(request, response, exportData, exportInfo, "项目列表.xls");
	}
	

	public LinkedHashMap<String, String> createProjectTitleMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("PROJECTNAME","项目名称");
		map.put("PLANYEAR","年度大计划");
		map.put("CITYLEADERS","分管市领导");
		map.put("MANAGEMENTOFFICE_NAME","责任处室");
		map.put("RESPONSIBILITYUNIT","责任单位");
		map.put("INSTEADUNIT","代建单位");
		map.put("LOCALGOVERMENT_NAME","辖区政府");
		map.put("PROJECTTYPE_NAME","项目类型");
		map.put("CONSTRUCTIONNATURE_NAME","建设性质");
		map.put("PROJECTSTAGE_NAME","建设阶段");
		map.put("STARTTIME","建设开始日期");
		map.put("ENDTIME","建设结束日期");
		map.put("CONSTRUCTIONCONTENT","建设内容");
		map.put("TOTALINVESTMENT","总投资");
		map.put("ACTUALINVEST","已下达资金");
		map.put("REMARK","备注");
		
		return map;
	}

	

	/**
	 * @function	导出投资excel
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/exportInvest")
	public void exportInvest(Investment v, HttpServletRequest request,HttpServletResponse response) {
		String projectid =request.getParameter("projectid");
		v.setProjectid(projectid);
	
		if(null==v.getSidx()||"".equals(v.getSidx())){
			v.setSidx("INVESTMENTTIME");
			v.setSord("ASC");
		}
		
		List<Map<String, ?>> data = null;
		data = projectService.searchData("business.queryInvestByProjectId", v);
//		// 处理 下达资金比例
//		for(Map<String, ?> p : data){
//			Map<String, Object> m = (Map<String, Object>)p;
//			String res = "0";
//			Object ACTUALINVESTTOTAL = m.get("ACTUALINVESTTOTAL");
//			Object ACTUALINVEST = m.get("ACTUALINVEST");
//			if(!"0".equals(ACTUALINVESTTOTAL)&&ACTUALINVEST!=null&&!"".equals(ACTUALINVEST)){
//				res = String.format("%.2f",(Double.valueOf(ACTUALINVEST.toString())/Double.valueOf(ACTUALINVESTTOTAL.toString())*100))+"%";
//			}
//			m.put("SCALECOUNT", res);
//		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectid", projectid);
		project p2 = projectService.queryProjectDetail(map);
		
		ArrayList<Object> exportData = new ArrayList<Object>();
		ArrayList<Object> exportInfo = new ArrayList<Object>();
		if (data != null){
			exportData.add(data);
			Map<String, Object> dataInfo = new HashMap<String, Object>();
			dataInfo.put("SHEET_NAME", "第一页");
			dataInfo.put("SHEET_TITLE", "投资列表（"+p2.getProjectname()+"）");
			dataInfo.put("BODY_TITLE", createInvestTitleMap());
			exportInfo.add(dataInfo);
		}
		
		BuildExcelTools.buildExcel(request, response, exportData, exportInfo, "投资列表.xls");
	}

	public LinkedHashMap<String, String> createInvestTitleMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("REFERENCENAME","文件名称");
		map.put("REFERENCECODE","文号");
		map.put("INVESTMENTTIME","下达时间");
		map.put("FUNDING_NAME","资金来源");
		map.put("ACTUALINVEST","下达资金");
		map.put("SCALECOUNT","下达资金比例");
		map.put("TOTALSCALECOUNT","累计比例");
		map.put("REMARKS","备注");
		return map;
	}
	
	/**
	 * @function 增加投资附件
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/addInvestmentAttach")
	public void addInvestmentAttach(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 构造一个文件上传处理对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			response.setContentType("text/html;charset=UTF-8");
			Iterator items;

			try {
				// 解析表单中提交的所有文件内容
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 取出上传文件的文件名称
						String name = item.getName();
						// 取得上传文件以后的存储路径
						String fileName = System.currentTimeMillis()+"_"+name.substring(name.lastIndexOf('\\') + 1, name.length());
						// 上传文件以后的存储路径
						String sysPath = request.getSession()
								.getServletContext().getRealPath("/");
						String a = ConfigManager.getItemValue("/configuration/uploadfile/avatarPath3");
						sysPath += File.separator + a;
						
						if (!(new File(sysPath).exists())) {
							new File(sysPath).mkdir();
						}
						// 上传文件
						File uploaderFile = new File(sysPath + File.separator
								+ fileName);
						item.write(uploaderFile);
						
						Map<String, String> map = new HashMap<String, String>();
						String investmentid = request.getParameter("investmentid");
						map.put("investmentid", investmentid);
						map.put("attachmentid", UUIDUtil.getUUID());
						map.put("filepath", fileName);
						projectService.insertInvestmentAttachment(map);

						response.getWriter().append(fileName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("1");
			} finally {
				response.getWriter().close();
			}
		}
	}
	
	/**
	 * 
	 * <p>[下载附件]</p>
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author yushp
	 */
	@RequestMapping("/downloadInvestmentByFilePath")
	public void downloadInvestmentByFilePath(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String filepath = java.net.URLDecoder.decode(request.getParameter("filepath"),"UTF-8");
		String aa = ConfigManager.getItemValue("/configuration/uploadfile/avatarPath3");
		FileDownUtil.dowloadFile(aa, filepath, request, response);
	}
	
	/**
	 * 
	 * <p>[下载附件直接打开]</p>
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author yushp
	 */
	@RequestMapping("/downloadInvestmentByFilePathToOpen")
	public void downloadInvestmentByFilePathToOpen(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String filepath = java.net.URLDecoder.decode(request.getParameter("filepath"),"UTF-8");
		String aa = ConfigManager.getItemValue("/configuration/uploadfile/avatarPath3");
		FileDownUtil.dowloadFileToOpen(aa, filepath, request, response);
	}
	
	/**
	 * 
	 * <p>[根据审批id获取投资的附件列表]</p>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/getAttchListByInid")
	public ModelMap getAttchListByInid(ModelMap model, 
			HttpServletRequest request) throws Exception {
		String investmentid = request.getParameter("investmentid");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("investmentid", investmentid);
		List<Map<String, ?>> list = projectService.getAttchListByInid(map);

		model.put("list", list);
		return model;
	}
	
	/**
	 * 
	 * <p>[删除投资附件]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/infileDelById")
	public ModelMap infileDelById(ModelMap model, HttpServletRequest request){
		String ATTACHMENTID = request.getParameter("ATTACHMENTID");
		Map<String, String> map = new HashMap<String, String>();
		map.put("ATTACHMENTID", ATTACHMENTID);
		
		projectService.infileDelById(map);
		model.put("res", true);
		
		return model;
	}
}
