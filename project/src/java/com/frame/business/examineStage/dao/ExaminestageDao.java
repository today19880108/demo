package com.frame.business.examineStage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.business.examineStage.model.Examinestage;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

@Service(value = "examinestageDao")
public class ExaminestageDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>
	 * [获得项目阶段列表]
	 * </p>
	 * 
	 * @param ProjectstageAction
	 * @return
	 * @author linym
	 */
	public Page<HashMap<String, Object>> getExaminestageList(Examinestage stage) {
		return this.queryPage("business.queryExaminestagetList",
				"business.queryExaminestageCount", stage, stage.getPage(), stage.getRows());
	}

	/**
	 * 
	 * <p>
	 * [根据ID获得项目阶段对象]
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author linym
	 */
	public Examinestage getExaminestageById(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return this.queryForObject("business.queryExaminestageDetail", map);
	}

	/**
	 * 
	 * <p>
	 * [插入项目阶段]
	 * </p>
	 * 
	 * @param ProjectstageAction
	 * @author linym
	 */
	public void insertExaminestage(Examinestage stage) {
		this.insert("business.insertExaminestage", stage);
	}

	/**
	 * 
	 * <p>
	 * [修改项目阶段]
	 * </p>
	 * 
	 * @param ProjectstageAction
	 * @author linym
	 */
	public void updateExaminestage(Examinestage stage) {
		this.update("business.updateExaminestage", stage);
	}

	/**
	 * 
	 * <p>
	 * [删除项目阶段]
	 * </p>
	 * 
	 * @param stageid
	 * @author linym
	 */
	public void delExaminestage(String[] stageid) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (String id : stageid) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			list.add(map);
		}
		this.batchDelete("business.delExaminestage", list);
	}

	/**
	 * 保存上传的附件
	 */
	public void insertAttachment(Map<String, String> map) {
		// TODO Auto-generated method stub
		// this.delete("business.delAttachmentByExamineId", map);
		this.insert("business.insertExamineAttachment", map);
	}

	/**
	 * 
	 * <p>
	 * [查询Excel导出列表]
	 * </p>
	 * 
	 * @param sqlMapId
	 * @param p
	 * @return
	 * @author castle-lin
	 */
	public List<Map<String, ?>> exportExamine(String sqlMapId, Object p) {
		return queryForList(sqlMapId, p);
	}

	/**
	 * <p>
	 * 根据examineid来查询是否有 附件
	 * </p>
	 * 
	 * @author castle-lin
	 * @param map
	 * @return
	 */
	public List<Map<String, ?>> queryExattByExid(Map<String, String> map) {
		return queryForList("business.queryExattByExid", map);
	}

	/**
	 * 
	 * <p>[删除审批附件]</p>
	 * @author yushp
	 */
	public void exfileDelById(Map<String, String> map) {
		delete("business.exfileDelById", map);
	}

}
