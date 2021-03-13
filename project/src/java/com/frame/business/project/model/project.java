package com.frame.business.project.model;

public class project {
	public String projectid;
	public String responsibilityunit;
	public String localgoverment;
	public String localgoverment_name;
	public String constructionnature;
	public String starttime;
	public String projecttype;
	public String projecttype_name;
	public String totalinvestment;
	public String constructioncontent;
//	public int attachmentid;
	public String cityleaders;
	public String projectname;
	
//	public String isyearplan;
	public String planyear;
	public String insteadunit;
	public String managementoffice;
//	public String projecttype2;
	public String endtime;
	public String actualinvest;
	public String remark;
	public String projectstage;
	
	public String replytime_s;
	public String replytime_e;
	
	public int rows;
	public int page;
	private String sidx;
	private String sord;
	
	private String filepath;
	
	public String getProjectid() {
		return projectid;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getLocalgoverment_name() {
		return localgoverment_name;
	}

	public void setLocalgoverment_name(String localgoverment_name) {
		this.localgoverment_name = localgoverment_name;
	}

	public String getProjecttype_name() {
		return projecttype_name;
	}

	public void setProjecttype_name(String projecttype_name) {
		this.projecttype_name = projecttype_name;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	
	public String getProjectstage() {
		return projectstage;
	}

	public void setProjectstage(String projectstage) {
		this.projectstage = projectstage;
	}

	public String getSidx() {
		return sidx;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getReplytime_s() {
		return replytime_s;
	}

	public void setReplytime_s(String replytime_s) {
		this.replytime_s = replytime_s;
	}

	public String getReplytime_e() {
		return replytime_e;
	}

	public void setReplytime_e(String replytime_e) {
		this.replytime_e = replytime_e;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public int getRows() {
		return rows;
	}

	public String getPlanyear() {
		return planyear;
	}

	public void setPlanyear(String planyear) {
		this.planyear = planyear;
	}

	public String getInsteadunit() {
		return insteadunit;
	}

	public void setInsteadunit(String insteadunit) {
		this.insteadunit = insteadunit;
	}

	public String getManagementoffice() {
		return managementoffice;
	}

	public void setManagementoffice(String managementoffice) {
		this.managementoffice = managementoffice;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getActualinvest() {
		return actualinvest;
	}

	public void setActualinvest(String actualinvest) {
		this.actualinvest = actualinvest;
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

	public String getResponsibilityunit() {
		return responsibilityunit;
	}

	public void setResponsibilityunit(String responsibilityunit) {
		this.responsibilityunit = responsibilityunit;
	}

	public String getLocalgoverment() {
		return localgoverment;
	}

	public void setLocalgoverment(String localgoverment) {
		this.localgoverment = localgoverment;
	}

	public String getConstructionnature() {
		return constructionnature;
	}

	public void setConstructionnature(String constructionnature) {
		this.constructionnature = constructionnature;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getTotalinvestment() {
		return totalinvestment;
	}

	public void setTotalinvestment(String totalinvestment) {
		this.totalinvestment = totalinvestment;
	}

	public String getConstructioncontent() {
		return constructioncontent;
	}

	public void setConstructioncontent(String constructioncontent) {
		this.constructioncontent = constructioncontent;
	}

	public String getCityleaders() {
		return cityleaders;
	}

	public void setCityleaders(String cityleaders) {
		this.cityleaders = cityleaders;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

}
