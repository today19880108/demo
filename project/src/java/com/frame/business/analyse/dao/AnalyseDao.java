package com.frame.business.analyse.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.util.dao.BaseDaoImpl;

/**
 * <p>[投资项目分析DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="analyseDao")
public class AnalyseDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得年总投资额数据列表]</p>
	 * @param year_s
	 * @param year_e
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getYearAnalyseData(String year_s, String year_e) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("year_s", year_s);
		map.put("year_e", year_e);
		
		return this.queryForList("business_analyse.getYearAnalyseData", map);
	}

	/**
	 * 
	 * <p>[获得项目类型投资数据列表]</p>
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getProjectTypeAnalyseData() {
		return this.queryForList("business_analyse.getProjectTypeAnalyseData", null);
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
		Map<String, String> map = new HashMap<String, String>();
		map.put("year_s", year_s);
		map.put("year_e", year_e);
		
		return this.queryForList("business_analyse.getYearProjectTypeAnalyseData", map);
	}

	/**
	 * 
	 * <p>[根据ID获得资金来源投资数据列表]</p>
	 * @param PROJECTID
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getZJAnalyseDataById(String PROJECTID) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PROJECTID", PROJECTID);
		
		return this.queryForList("business_analyse.getZJAnalyseDataById", map);
	}

	/**
	 * 
	 * <p>[根据ID获得年度投资数据列表]</p>
	 * @param PROJECTID
	 * @return
	 * @author yushp
	 */
	public List<Map<String, ?>> getNFXAnalyseDataById(String PROJECTID) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("PROJECTID", PROJECTID);
		
		return this.queryForList("business_analyse.getNFXAnalyseDataById", map);
	}

	
}
