package com.frame.business.examineStage.model;

/**
 * @function 审批阶段实体bean 与数据库对应
 * @author castle-lin
 * 
 */
public class Examinestage {

	// 项目 id
	public String projectid;
	// 审批阶段
	public String stage;
	// 文件名
	public String documentname;
	// 文号
	public String dnumber;
	// 批复时间
	public String replytime;
	// 批复金额
	public String replymoney;
	// 备注
	public String remark;
	public String examineid;
	public String sidx;
	public String sord;

	public String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
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

	public String getExamineid() {
		return examineid;
	}

	public void setExamineid(String examineid) {
		this.examineid = examineid;
	}

	public int rows;
	public int page;

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

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public String getDnumber() {
		return dnumber;
	}

	public void setDnumber(String dnumber) {
		this.dnumber = dnumber;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	public String getReplymoney() {
		return replymoney;
	}

	public void setReplymoney(String replymoney) {
		this.replymoney = replymoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
