package com.frame.business.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.business.project.dao.ProjectDao;
import com.frame.business.project.model.Investment;
import com.frame.business.project.model.project;
import com.frame.util.page.Page;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@javax.annotation.Resource(name="ProjectDao")
	private ProjectDao projectDao;
	
	@Override
	public void insertNewProject(Map<String, String> map) {
		projectDao.insertNewProject(map);
	}

	@Override
	public void insertInvestment(Map<String, String> map) {
		projectDao.insertInvestment(map);
	}

	@Override
	public void insertAttachment(Map<String,String> map) {
		projectDao.insertAttachment(map);
	}

	@Override
	public Page<HashMap<String, Object>> queryProject(project p) {
		 
		return projectDao.queryProject(p,p.page,p.rows);
	}

	@Override
	public project queryProjectDetail(Map<String, String> map) {
		 
		return  projectDao.queryProjectDetail(map);
	}

	@Override
	public void updateProject(Map<String, String> map) {
		projectDao.updateProject(map);
	}

	@Override
	public void updateInvst(Map<String, String> map) {
		projectDao.updateInvest(map);
	}

	@Override
	public Page<HashMap<String, Object>> queryInvest(Investment i) {
		// TODO Auto-generated method stub
		return projectDao.queryInvest(i,i.page,i.rows);
	}

	@Override
	public void delProject(List<Map<String, String>> list) {
		projectDao.delProject(list);
	}

	@Override
	public void delInvest(List<Map<String, String>> list) {
		// TODO Auto-generated method stub
		projectDao.delInvest(list);
	}

	@Override
	public Investment queryInvestDetail(Map<String, String> map) {
		// TODO Auto-generated method stub
		return projectDao.queryInvestDetail(map);
	}
	
	@Override
	public void insertFileImport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		projectDao.insertFileImport(map);
	}

	/**
	 * 
	 * <p>[查询Excel导出列表]</p>
	 * @param sqlMapId
	 * @param p
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> searchData(String sqlMapId, Object p) {
		// TODO Auto-generated method stub
		return projectDao.searchData(sqlMapId, p);
	}

	/**
	 * 
	 * <p>[增加投资附件]</p>
	 * @param map
	 * @author yushp
	 */
	public void insertInvestmentAttachment(Map<String, String> map) {
		projectDao.insertInvestmentAttachment(map);
	}

	/**
	 * 
	 * 根据审批id获取投资的附件列表
	 * @author yushp
	 */
	public List<Map<String, ?>> getAttchListByInid(Map<String, String> map) {
		return projectDao.getAttchListByInid(map);
	}

	/**
	 * 
	 * <p>[删除投资附件]</p>
	 * @author yushp
	 */
	public void infileDelById(Map<String, String> map) {
		projectDao.infileDelById(map);
	}

}
