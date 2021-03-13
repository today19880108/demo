package com.frame.base.code.model;


/**
 * 
 * <p>[字典信息类]<p>
 * @author yushp
 *
 */
public class CodeInfo {
	private String CODE_ID;
	private String ID;
	private String NAME;
	private String TYPE;
	private String ORD;
	
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
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getCODE_ID() {
		return CODE_ID;
	}
	public void setCODE_ID(String cODE_ID) {
		CODE_ID = cODE_ID;
	}
	public String getORD() {
		return ORD;
	}
	public void setORD(String oRD) {
		ORD = oRD;
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
