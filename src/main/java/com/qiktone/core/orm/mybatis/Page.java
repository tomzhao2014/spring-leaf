package com.qiktone.core.orm.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Mybatis分页参数及查询结果封装.
 * 注意所有序号从1开始.
 * 
 * @param <T> Page中记录的类型.
 */
@JsonIgnoreProperties(value = { "offset", "limit", "autoCount" })
public class Page<T> extends RowBounds implements Serializable {
	private static final long serialVersionUID = -250197829757983113L;
	public static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 15;

	// -- 分页参数 --//
	protected Integer pageNo;
	protected Integer pageSize;
	protected boolean autoCount = true;
	private int offset;
	private int limit;

	protected  String preCss;

	protected String nextCss;
	  
	// -- 返回结果 --//
	protected List<T> result = new ArrayList<T>();
	protected long totalCount = 0;



	// -- 构造函数 --//
	public Page() {
		this(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE);
	}

	public Page(Integer pageNo) {
		this(pageNo, DEFAULT_PAGE_SIZE);
	}

	public Page(Integer pageNo, Integer pageSize) {
		//super((pageNo - 1) * pageSize, pageSize);   RowBounds父类没有序列化 不能远程调用
		this.pageNo = pageNo==null?DEFAULT_PAGE_NO:pageNo;
		this.offset = (this.pageNo - 1) * pageSize;
		this.limit = pageSize;
		this.pageSize = pageSize;
		this.nextCss="";
		this.preCss="";
	}
	
	


	// -- 访问查询参数函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// -- 访问查询结果函数 --//

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为0.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if ((totalCount % pageSize) > 0) {
			count++;
		}
		return count;
	}
	
	public long getStartRow(){
		return (pageNo-1)*pageSize+1;
	}
	
	public long getEndRow(){
		return pageNo*pageSize;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1) <= getTotalPages();
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1) >= 1;
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public int getLimit() {
		return limit;
	}


	public String getNextCss() {
		if(!isHasNext()){
			nextCss="disabled";
		}
		return nextCss;
	}


	public String getPreCss() {
		if(!isHasPre()){
			preCss="disabled";
		}
		return preCss;
	}





}
