package com.frame.business.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frame.business.project.model.Investment;
import com.frame.business.project.model.project;
import com.frame.util.page.Page;

public interface ProjectDao {
	/**
	 * @author linym
	 * @function 插入项目记录
	 * @param map
	 */
	public void insertNewProject(Map<String,String> map);
	
	/**
	 * @function 	插入项目投资
	 * @param map
	 */
	public void insertInvestment(Map<String,String> map);
	
	/**
	 * @function	插入附件
	 * @param att
	 */
    public	void insertAttachment(Map<String,String> map);
	
	/**
	 * @function  查询项目
	 * @param map
	 * @return
	 */
	public Page<HashMap<String, Object>> queryProject(project p,int pagenum,int rownum);
	
	/**
	 * @function  查询项目详情
	 * @param map
	 * @return
	 */
	public project queryProjectDetail(Map<String,String> map);
	
	/**
	 * @function  查询投资
	 * @param map
	 * @return
	 */
	public Page<HashMap<String, Object>> queryInvest(Investment v,int pagenum,int rownum);
   
	/**
	 * @function  查询投资详情
	 * @param map
	 * @return
	 */
	public Investment queryInvestDetail(Map<String,String> map);
	
	/**
	 * @function  更新项目
	 * @param map
	 */
	public void updateProject(Map<String,String> map);
	
	/**
	 * @function  更新投资
	 * @param map
	 */
	public  void updateInvest(Map<String,String> map);
	
	/**
	 * @function  删除项目
	 * @param map
	 */
	public void delProject(List<Map<String,String>> list);
	
	/**
	 * @function  删除投资
	 * @param map
	 */
	public void delInvest(List<Map<String,String>> list);
	
	/**
	 * @function	文件导入
	 */
	public void insertFileImport(Map<String,Object> map);

	/**
	 * 
	 * <p>[查询Excel导出列表]</p>
	 * @param sqlMapId
	 * @param p
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> searchData(String sqlMapId, Object p);

	/**
	 * 
	 * <p>[增加投资附件]</p>
	 * @param map
	 * @author yushp
	 */
	public void insertInvestmentAttachment(Map<String, String> map);

	/**
	 * 
	 * <p>[根据审批id获取投资的附件列表]</p>
	 * @param map
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getAttchListByInid(Map<String, String> map);

	/**
	 * 
	 * <p>[删除投资附件]</p>
	 * @author yushp
	 */
	public void infileDelById(Map<String, String> map);

}
