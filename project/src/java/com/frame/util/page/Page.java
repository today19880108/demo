package com.frame.util.page;

import java.util.List;

/**
 * 
 * <p>[分页页面信息类]</p>
 * @author yushp
 *
 * @param <T>
 */
public class Page<T> {
	/**
	 * 当前页数
	 */
	private Integer currentPage = 1;
	/**
	 * 总页数
	 */
	private Integer totalPage = 1;
	/**
	 * 总记录数
	 */
	private Integer totalRecords = 0;
	/**
	 * 当前页记录
	 */
	private List<T> records;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
}
