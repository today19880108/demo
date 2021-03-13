package com.frame.base.department.model;

public class Department {
	//部门id
	public String id;
	//上级部门id
	public String pid;
	//上级部门名称
	public String pid_name;
	//部门名字
	public String name;
	//显示顺序
	public String ord;
	//部门描述
	public String remark;
	
	public int page;
	public int rows;
	public String sidx;
	public String sord;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getId() {
		return id;
	}
	public String getPid_name() {
		return pid_name;
	}
	public void setPid_name(String pid_name) {
		this.pid_name = pid_name;
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
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
