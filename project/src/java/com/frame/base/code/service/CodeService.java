package com.frame.base.code.service;


import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.base.code.dao.CodeDao;
import com.frame.base.code.model.CodeInfo;
import com.frame.util.UUIDUtil;
import com.frame.util.page.Page;

/**
 * 
 * <p>[字典业务类]</p>
 * @author yushp
 *
 */
@Service("codeService")
public class CodeService {
	
	@javax.annotation.Resource(name = "codeDao")
	private CodeDao codeDao;

	/**
	 * 
	 * <p>[获得字典列表]</p>
	 * @param code
	 * @return
	 * @author yushp
	 */
	public Page<HashMap<String, Object>> getCodeList(CodeInfo code) {
		return codeDao.getCodeList(code);
	}
	
	/**
	 * 
	 * <p>[保存字典]</p>
	 * @param user
	 * @author yushp
	 */
	@Transactional
	public void saveCodeAddUdp(CodeInfo code) {
		if(code.getCODE_ID()==null||"".equals(code.getCODE_ID())){
			code.setCODE_ID(UUIDUtil.getUUID());
			codeDao.insertCode(code);
		}else{
			codeDao.updateCode(code);
		}
	}

	/**
	 * 
	 * <p>[根据ID查询信字典息]</p>
	 * @param ID
	 * @return
	 * @author yushp
	 */
	public CodeInfo getCodeById(String CODE_ID) {
		return codeDao.getCodeById(CODE_ID);
	}

	/**
	 * 
	 * <p>[删除字典]</p>
	 * @param ID
	 * @author yushp
	 */
	@Transactional
	public void delCode(String[] CODE_ID) {
		codeDao.delCode(CODE_ID);
	}

	/**
	 * 
	 * <p>[验证字典唯一性]</p>
	 * @param code
	 * @return
	 * @author yushp
	 */
	public boolean searchCodeOnly(CodeInfo code) {
		int count = codeDao.searchCodeOnly(code);
		if(count > 0){
			return false;
		}
		return true;
	}
}
