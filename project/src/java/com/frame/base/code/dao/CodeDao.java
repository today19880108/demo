package com.frame.base.code.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frame.base.code.model.CodeInfo;
import com.frame.util.dao.BaseDaoImpl;
import com.frame.util.page.Page;

/**
 * <p>[字典DAO类]</p>
 * @author Administrator
 *
 */
@Service(value="codeDao")
public class CodeDao extends BaseDaoImpl {

	/**
	 * 
	 * <p>[获得字典列表]</p>
	 * @param code
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getCodeList(CodeInfo code) {
		return this.queryPage("base_code.getCodeList", "base_code.getCodeListCount", code, code.getPage(), code.getRows());
	}
	
	/**
	 * 
	 * <p>[新增字典]</p>
	 * @param code
	 * @author yushp
	 */
	public void insertCode(CodeInfo code) {
		this.insert("base_code.insertCode", code);
	}

	/**
	 * 
	 * <p>[修改字典]</p>
	 * @param code
	 * @author yushp
	 */
	public void updateCode(CodeInfo code) {
		this.update("base_code.updateCode", code);
	}

	/**
	 * 
	 * <p>[根据ID查询字典信息]</p>
	 * @param ID
	 * @return
	 * @author yushp
	 */
	public CodeInfo getCodeById(String CODE_ID) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("CODE_ID", CODE_ID);
		
		return this.queryForObject("base_code.getCodeById", map);
	}

	/**
	 * 
	 * <p>[删除字典]</p>
	 * @param ID
	 * @author yushp
	 */
	public void delCode(String[] CODE_ID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(String id: CODE_ID){
			Map<String, String> map = new HashMap<String, String>();
			map.put("CODE_ID", id);
			list.add(map);
		}
		
		this.batchDelete("base_code.delCode", list);
	}

	/**
	 * 
	 * <p>[验证字典唯一性]</p>
	 * @param code
	 * @return
	 * @author yushp
	 */
	public int searchCodeOnly(CodeInfo code) {
		return this.queryForObject("base_code.searchCodeOnly", code);
	}

}
