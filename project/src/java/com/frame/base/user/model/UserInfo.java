package com.frame.base.user.model;


/**
 * 
 * <p>[用户信息类]<p>
 * @author yushp
 *
 */
public class UserInfo {
	private String USER_ID;
	private String USER_NAME;
	private String USER_LOGIN;
	private String USER_PWD;
	private String DEPT;
	private String DEPT_NAME;
	private String DEPT2;
	private String DEPT_NAME2;
	private String USER_CODE;
	private String SEX;
	private String SEX_NAME;
	private String AGE;
	private String TEL;
	private String EMAIL;
	private String REMARK;
	private String SHOW_ORDER;
	private String ISUSER;
	private String ISUSER_NAME;
	private String ADD_TIME;
	
	private String loginError;
	
	private int rows;
	private int page;
	private String sidx;
	private String sord;
	
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getUSER_LOGIN() {
		return USER_LOGIN;
	}
	public void setUSER_LOGIN(String uSER_LOGIN) {
		USER_LOGIN = uSER_LOGIN;
	}
	public String getUSER_PWD() {
		return USER_PWD;
	}
	public String getDEPT2() {
		return DEPT2;
	}
	public void setDEPT2(String dEPT2) {
		DEPT2 = dEPT2;
	}
	public String getDEPT_NAME2() {
		return DEPT_NAME2;
	}
	public void setDEPT_NAME2(String dEPT_NAME2) {
		DEPT_NAME2 = dEPT_NAME2;
	}
	public void setUSER_PWD(String uSER_PWD) {
		USER_PWD = uSER_PWD;
	}
	public String getDEPT() {
		return DEPT;
	}
	public void setDEPT(String dEPT) {
		DEPT = dEPT;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	public String getUSER_CODE() {
		return USER_CODE;
	}
	public void setUSER_CODE(String uSER_CODE) {
		USER_CODE = uSER_CODE;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getSEX_NAME() {
		return SEX_NAME;
	}
	public void setSEX_NAME(String sEX_NAME) {
		SEX_NAME = sEX_NAME;
	}
	public String getAGE() {
		return AGE;
	}
	public void setAGE(String aGE) {
		AGE = aGE;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getSHOW_ORDER() {
		return SHOW_ORDER;
	}
	public void setSHOW_ORDER(String sHOW_ORDER) {
		SHOW_ORDER = sHOW_ORDER;
	}
	public String getISUSER() {
		return ISUSER;
	}
	public void setISUSER(String iSUSER) {
		ISUSER = iSUSER;
	}
	public String getISUSER_NAME() {
		return ISUSER_NAME;
	}
	public void setISUSER_NAME(String iSUSER_NAME) {
		ISUSER_NAME = iSUSER_NAME;
	}
	public String getADD_TIME() {
		return ADD_TIME;
	}
	public void setADD_TIME(String aDD_TIME) {
		ADD_TIME = aDD_TIME;
	}
	public String getLoginError() {
		return loginError;
	}
	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	
}
