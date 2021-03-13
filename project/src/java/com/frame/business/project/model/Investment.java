package com.frame.business.project.model;

public class Investment {
	/**
	 * 唯一标示符
	 */
	public String investmentid;
	
	/**
	 * 所属项目id
	 */
	public String projectid;
	
	/**
	 * 下达时间
	 */
	public String investmenttime;

	/**
	 * 资金来源
	 */
	public String funding;

	/**
	 * 投资计划
	 */
//	public String planinvest;

	/**
	 * 实际投资
	 */
	public String actualinvest;
	
	/**
	 * 投资目标
	 */
//	public String investtarget;
	
	/**
	 * 文件名称
	 */
	public String referencename;
	
	/**
	 * 文号
	 */
	public String referencecode;
	
	/**
	 * 备注
	 */
	public String remarks;
	
	private String filepath;
	
	public int rows;
	public int page;
	private String sidx;
	private String sord;
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getReferencename() {
		return referencename;
	}
	public void setReferencename(String referencename) {
		this.referencename = referencename;
	}
	public String getReferencecode() {
		return referencecode;
	}
	public void setReferencecode(String referencecode) {
		this.referencecode = referencecode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	public String getInvestmentid() {
		return investmentid;
	}
	public void setInvestmentid(String investmentid) {
		this.investmentid = investmentid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getInvestmenttime() {
		return investmenttime;
	}
	public void setInvestmenttime(String investmenttime) {
		this.investmenttime = investmenttime;
	}
	public String getFunding() {
		return funding;
	}
	public void setFunding(String funding) {
		this.funding = funding;
	}
	public String getActualinvest() {
		return actualinvest;
	}
	public void setActualinvest(String actualinvest) {
		this.actualinvest = actualinvest;
	}

}
