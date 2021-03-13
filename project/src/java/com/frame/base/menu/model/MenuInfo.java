package com.frame.base.menu.model;


/**
 * 
 * <p>[用户信息类]<p>
 * @author yushp
 *
 */
public class MenuInfo {
	private String ID;
	private String PID;
	private String PID_NAME;
	private String PID2;
	private String PID_NAME2;
	private String NAME;
	private String PATH;
	private String REMARK;
	private String ORD;
	private String ADD_TIME;
	
	private int rows;
	private int page;
	private String sidx;
	private String sord;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getPID_NAME() {
		return PID_NAME;
	}
	public void setPID_NAME(String pID_NAME) {
		PID_NAME = pID_NAME;
	}
	public String getPID2() {
		return PID2;
	}
	public void setPID2(String pID2) {
		PID2 = pID2;
	}
	public String getPID_NAME2() {
		return PID_NAME2;
	}
	public void setPID_NAME2(String pID_NAME2) {
		PID_NAME2 = pID_NAME2;
	}
	public String getORD() {
		return ORD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public void setORD(String oRD) {
		ORD = oRD;
	}
	public String getADD_TIME() {
		return ADD_TIME;
	}
	public void setADD_TIME(String aDD_TIME) {
		ADD_TIME = aDD_TIME;
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
