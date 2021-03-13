package com.frame.business.projectstage.model;

/**
 * @function	项目阶段实体bean  与数据库对应
 * @author castle-lin
 *
 */
public class Projectstage {

	//阶段
	public	String stage;
	//负责人
	public  String responsibleperson;
	//开始时间
	public String starttime;
	//结束时间
	public String endtime;
	//备注
	public String remarks;
	//唯一标示符
	public  String stageid;
    //阶段总结
	public String stagesummary;
	
	
	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String projectid;
	
	public String sidx;
	public String sord;
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

	public String getStagesummary() {
		return stagesummary;
	}

	public void setStagesummary(String stagesummary) {
		this.stagesummary = stagesummary;
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

	public String getResponsibleperson() {
		return responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStageid() {
		return stageid;
	}

	public void setStageid(String stageid) {
		this.stageid = stageid;
	}

	 
}
