package com.frame.business.examineStage.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.frame.business.examineStage.model.Examinestage;
import com.frame.business.examineStage.service.ExaminestageSerivce;
import com.frame.business.project.model.project;
import com.frame.business.project.service.ProjectService;
import com.frame.comm.service.CommService;
import com.frame.util.ConfigManager;
import com.frame.util.UUIDUtil;
import com.frame.util.fileUploadDownUtil.BuildExcelTools;
import com.frame.util.fileUploadDownUtil.FileDownUtil;
import com.frame.util.page.Page;

@Controller
@RequestMapping("/business")
public class ExamineStageAction {

	@Resource(name = "examinestageSerivce")
	private ExaminestageSerivce examinestageSerivce;

	@Resource(name = "commService")
	CommService commService;

	@javax.annotation.Resource(name = "projectService")
	private ProjectService projectService;

	/**
	 * @function 增加附件
	 * @author linym
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/addExmaineAttach")
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
						String fileName = System.currentTimeMillis()
								+ "_"
								+ name.substring(name.lastIndexOf('\\') + 1,
										name.length());
						// 上传文件以后的存储路径
						String sysPath = request.getSession()
								.getServletContext().getRealPath("/");
						// ConfigManager.getItemValue("/configuration/uploadfile/avatarPath");
						String a = ConfigManager
								.getItemValue("/configuration/uploadfile/examineuploadPath");
						sysPath += a /* File.separator + a */;
						if (!(new File(sysPath).exists())) {
							new File(sysPath).mkdir();
						}
						// 上传文件
						File uploaderFile = new File(sysPath + File.separator
								+ fileName);
						item.write(uploaderFile);
						Map<String, String> map = new HashMap<String, String>();
						String examineid = request.getParameter("examineid");
						map.put("examineid", examineid);
						map.put("attid", UUIDUtil.getUUID());
						map.put("filepath", fileName);
						examinestageSerivce.insertAttachment(map);
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
	 * @function 审批阶段管理页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exstageManagePage")
	public String departManage(ModelMap model, HttpServletRequest request) {
		return "web/business/examinestage/jsp/stagePage";
	}

	/**
	 * @function 新增审批阶段页面跳转
	 * @author linym
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addExmaineStagePage")
	public String addDepartPage(ModelMap model, HttpServletRequest request,
			Examinestage stage2) {
		String id = stage2.getExamineid();
		if (request.getParameter("examineid") != null && id.equals(""))
			id = request.getParameter("examineid");
		Examinestage stage = new Examinestage();
		if (null != id && !"".equals(id)) {
			stage = examinestageSerivce.getExaminestageByid(id);
			model.put("select", stage);
		} else {
			String projectid = request.getParameter("projectid");
			stage.setProjectid(projectid);
			model.put("select", stage);
		}
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("type", "15");
		List<Map<String, ?>> list1 = commService.getCodeInAllTable(map2);
		model.put("stages", list1);

		Map<String, String> map = new HashMap<String, String>();
		map.put("examineid", stage.getExamineid());
		/*
		 * Map<String, String>
		 * selectatt=examinestageSerivce.queryExattByExid(map);
		 * model.put("selectatt", selectatt);
		 */
		return "web/business/examinestage/jsp/stageAddUpdPage";
	}

	/**
	 * @function 保存审批阶段（新增或删除）
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveExmaineStage")
	public ModelMap saveDepartment(Examinestage stage,
			HttpServletRequest request) {
		Map<String, String> result = examinestageSerivce
				.saveExaminestage(stage);
		ModelMap model = new ModelMap();
		model.put("result", result);
		return model;
	}

	/**
	 * @function 删除审批阶段
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delExmaineStage")
	public ModelMap delDepartment(ModelMap model, HttpServletRequest request) {
		String[] id = request.getParameterValues("stageid[]");
		String result = examinestageSerivce.delExaminestage(id);
		model.put("result", result);
		return model;
	}

	/**
	 * @function 获取审批阶段列表
	 * @param stage
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExmaineStageList")
	public ModelMap getDepartList(Examinestage stage) {
		if (null == stage.getSidx() || "".equals(stage.getSidx())) {
			stage.setSidx("ORD");
			stage.setSord("ASC");
		}
		int rowInt = 10;
		int pageInt = 1;
		if (stage.getRows() == 0) {
			stage.setRows(rowInt);
		}
		if (stage.getPage() == 0) {
			stage.setPage(pageInt);
		}
		Page<HashMap<String, Object>> page = examinestageSerivce
				.getExaminestageList(stage);
		ModelMap model = new ModelMap();
		model.put("page", page);
		return model;
	}

	/**
	 * <p>
	 * 导出审批excel
	 * </p>
	 * 
	 * @param v
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportExamine")
	public void exportExamine(Examinestage v, HttpServletRequest request,
			HttpServletResponse response) {
		String projectid = request.getParameter("projectid");
		v.setProjectid(projectid);

		if (null == v.getSidx() || "".equals(v.getSidx())) {
			v.setSidx("ORD");
			v.setSord("ASC");
		}

		List<Map<String, ?>> data = null;
		data = projectService.searchData("business.queryExaminestagetList", v);

		Map<String, String> map = new HashMap<String, String>();
		map.put("projectid", projectid);
		project p2 = projectService.queryProjectDetail(map);

		ArrayList<Object> exportData = new ArrayList<Object>();
		ArrayList<Object> exportInfo = new ArrayList<Object>();
		if (data != null) {
			exportData.add(data);
			Map<String, Object> dataInfo = new HashMap<String, Object>();
			dataInfo.put("SHEET_NAME", "第一页");
			dataInfo.put("SHEET_TITLE", "审批列表（" + p2.getProjectname() + "）");
			dataInfo.put("BODY_TITLE", createInvestTitleMap());
			exportInfo.add(dataInfo);
		}

		BuildExcelTools.buildExcel(request, response, exportData, exportInfo,
				"审批列表.xls");
	}

	public LinkedHashMap<String, String> createInvestTitleMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("STAGE", "审批阶段");
		map.put("DOCUMENTNAME", "文件名称");
		map.put("DNUMBER", "文号");
		map.put("REPLYTIME", "审批时间");
		map.put("REPLYMONEY", "审批金额");
		map.put("REMARK", "备注");
		return map;
	}

	/**
	 * <p>
	 * 根据审批id获取整个审批的附件。
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAttchListByExid")
	public ModelMap getAttchListByExid(ModelMap model, 
			HttpServletRequest request) throws Exception {
		String id = request.getParameter("examineid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("examineid", id);
		List list = (List) examinestageSerivce.queryExattByExid(map);
		
		model.put("list", list);
		return model;
	}

	/**
	 * 
	 * <p>
	 * [下载附件]
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author yushp
	 */
	@RequestMapping("/exdownloadByFilePath")
	public void downloadByFilePath(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String filepath = java.net.URLDecoder.decode(
				request.getParameter("filepath"), "UTF-8");
		String syspath = ConfigManager
				.getItemValue("/configuration/uploadfile/examineuploadPath");
		FileDownUtil.dowloadFile(syspath, filepath, request, response);
	}
	
	/**
	 * 
	 * <p>
	 * [下载附件直接打开]
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author yushp
	 */
	@RequestMapping("/exdownloadByFilePathToOpen")
	public void downloadByFilePathToOpen(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String filepath = java.net.URLDecoder.decode(
				request.getParameter("filepath"), "UTF-8");
		String syspath = ConfigManager
				.getItemValue("/configuration/uploadfile/examineuploadPath");
		FileDownUtil.dowloadFileToOpen(syspath, filepath, request, response);
	}
	
	/**
	 * 
	 * <p>[删除审批附件]</p>
	 * @author yushp
	 */
	@ResponseBody
	@RequestMapping("/exfileDelById")
	public ModelMap exfileDelById(ModelMap model, HttpServletRequest request){
		String ATTID = request.getParameter("ATTID");
		Map<String, String> map = new HashMap<String, String>();
		map.put("ATTID", ATTID);
		
		examinestageSerivce.exfileDelById(map);
		model.put("res", true);
		
		return model;
	}
}
