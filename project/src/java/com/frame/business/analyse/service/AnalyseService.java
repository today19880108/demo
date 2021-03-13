package com.frame.business.analyse.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.business.analyse.dao.AnalyseDao;

/**
 * 
 * <p>[投资项目分析业务类]</p>
 * @author yushp
 *
 */
@Service("analyseService")
public class AnalyseService {
	
	@javax.annotation.Resource(name = "analyseDao")
	private AnalyseDao analyseDao;

	/**
	 * 
	 * <p>[获得年总投资额数据列表]</p>
	 * @param year_s
	 * @param year_e
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getYearAnalyseData(String year_s, String year_e) {
		return analyseDao.getYearAnalyseData(year_s, year_e);
	}

	/**
	 * 
	 * <p>[获得项目类型投资数据列表]</p>
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getProjectTypeAnalyseData() {
		return analyseDao.getProjectTypeAnalyseData();
	}

	/**
	 * 
	 * <p>[获得年度项目类型投资数据列表]</p>
	 * @param year_s
	 * @param year_e
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getYearProjectTypeAnalyseData(String year_s,
			String year_e) {
		return analyseDao.getYearProjectTypeAnalyseData(year_s, year_e);
	}

	/**
	 * 
	 * <p>[根据ID获得资金来源投资数据列表]</p>
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getZJAnalyseDataById(String PROJECTID) {
		return analyseDao.getZJAnalyseDataById(PROJECTID);
	}

	/**
	 * 
	 * <p>[根据ID获得年度投资数据列表]</p>
	 * @param PROJECTID
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getNFXAnalyseDataById(String PROJECTID) {
		return analyseDao.getNFXAnalyseDataById(PROJECTID);
	}

}
