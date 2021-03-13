package com.frame.business.examineStage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.business.examineStage.dao.ExaminestageDao;
import com.frame.business.examineStage.model.Examinestage;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;

@Service("examinestageSerivce")
public class ExaminestageSerivce {

	@javax.annotation.Resource(name = "examinestageDao")
	private ExaminestageDao examinestageDao;

	/**
	 * @function 保存或更新项目审批阶段数据
	 * @author linym
	 * @param d
	 * @return
	 */
	public Map<String, String> saveExaminestage(Examinestage d) {
		Map<String, String> r = new HashMap<String, String>();
		try {
			if (d.getExamineid() != null && !d.getExamineid().equals("")) {
				examinestageDao.updateExaminestage(d);
			} else {
				d.setExamineid(UUIDUtil.getUUID());
				examinestageDao.insertExaminestage(d);
			}
			r.put("result", "s");
			r.put("examineid", d.getExamineid());
			return r;
		} catch (Exception ex) {
			r.put("result", "f");
			return r;
		}
	}

	/**
	 * @function 删除项目审批阶段
	 * @author linym
	 * @param id
	 * @return
	 */
	public String delExaminestage(String[] id) {
		try {
			examinestageDao.delExaminestage(id);
			return "s";
		} catch (Exception ex) {
			return "f";
		}
	}

	/**
	 * @function 根据项目阶段id查询审批项目阶段详情
	 * @param id
	 * @return
	 */
	public Examinestage getExaminestageByid(String id) {
		return examinestageDao.getExaminestageById(id);
	}

	/**
	 * @function 获取审批项目阶段列表
	 * @param stage
	 * @return
	 */
	public Page<HashMap<String, Object>> getExaminestageList(Examinestage stage) {
		return examinestageDao.getExaminestageList(stage);
	}

	public void insertAttachment(Map<String, String> map) {
		examinestageDao.insertAttachment(map);
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
		// TODO Auto-generated method stub
		return examinestageDao.exportExamine(sqlMapId, p);
	}

	/**
	 * 
	 * <p>[根据审批id获取整个审批的附件]</p>
	 * @param map
	 * @return
	 * @author castle-lin
	 */
	public List queryExattByExid(Map<String, String> map) {
		List a = examinestageDao.queryExattByExid(map);
		/*
		 * if(0==a.size()) { return null; } else { Map<String,String> ret=
		 * (Map<String, String>) a.get(0); return ret; }
		 */
		return a;
	}

	/**
	 * 
	 * <p>[删除审批附件]</p>
	 * @author yushp
	 */
	public void exfileDelById(Map<String, String> map) {
		examinestageDao.exfileDelById(map);
	}
}
