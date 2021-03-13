package com.frame.business.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.business.project.model.Investment;
import com.frame.business.project.model.project;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;
import com.ibatis.sqlmap.client.SqlMapClient;

@Service("ProjectDao")
public class ProjectDaoImpl extends BaseDaoImpl implements ProjectDao {
   
	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;
    
	 /**
	  * 插入一个新的项目
	  */
	@Override
	public void insertNewProject(Map<String, String> map) {
	   this.insert("business.insertNewProject", map);   
	}

	/**
	 * 给项目插入一个新的投资
	 */
	@Override
	public void insertInvestment(Map<String, String> map) {
		this.insert("business.insertInvestment", map);  
	}

	/**
	 * 查询项目
	 */
	@Override
	public Page<HashMap<String, Object>> queryProject(project p,int pagenum,int rownum) {
		return queryPage("business.queryProjectList","business.queryProjectCount", p,pagenum,rownum);
	}

	/**
	 * 查询项目详情
	 */
	@Override
	public project queryProjectDetail(Map<String, String> map) {
		return this.queryForObject("business.queryProjectDetail", map);
	}

	/**
	 * 保存上传的附件
	 */
	@Transactional
	public void insertAttachment(Map<String,String> map) {
		 this.insert("business.delAttachmentByProjectId", map);
		 this.insert("business.insertAttachment", map);
	}

	@Override
	public void updateProject(Map<String, String> map) {
		this.update("business.updateProject", map);
	}

	@Override
	public void updateInvest(Map<String, String> map) {
		this.update("business.updateVestment", map);
	}

	/**
	 * 查询投资列表
	 */
	@Override
	public Page<HashMap<String, Object>> queryInvest(Investment v,int pagenum,int rownum) {
		 
		return queryPage("business.queryInvestByProjectId","business.queryInvestCount", v,pagenum,rownum);
	}

	@Override
	public void delProject(List<Map<String, String>> list) {
		this.batchDelete("business.delProject", list);
	}

	@Override
	public void delInvest(List<Map<String,String>> list) {
		this.batchDelete("business.delInvest", list);
	}

	@Override
	public Investment queryInvestDetail(Map<String, String> map) {
		return this.queryForObject("business.queryInvestDetail", map);
	}

	@Override
	public void insertFileImport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.insert("business.insertFileImport", map);
	}

	/**
	 * 
	 * <p>[查询Excel导出列表]</p>
	 * @param sqlMapId
	 * @param p
	 * @return
	 * @author yushp
	 */
	@Override
	public List<Map<String, ?>> searchData(String sqlMapId, Object p) {
		return queryForList(sqlMapId, p);
	}

	/**
	 * 
	 * <p>[增加投资附件]</p>
	 * @param map
	 * @author yushp
	 */
	public void insertInvestmentAttachment(Map<String, String> map) {
//		this.insert("business.delAttachmentByInvestmentId", map);
		this.insert("business.insertInvestmentAttachment", map);
	}

	/**
	 * 
	 * 根据审批id获取投资的附件列表
	 * @author yushp
	 */
	public List<Map<String, ?>> getAttchListByInid(Map<String, String> map) {
		return queryForList("business.getAttchListByInid", map);
	}

	/**
	 * 
	 * 删除投资附件
	 * @author yushp
	 */
	public void infileDelById(Map<String, String> map) {
		// TODO Auto-generated method stub
		delete("business.infileDelById", map);
	}

}
