/**
 * 
 */
package com.tomframework.core.entity;

import com.tomframework.core.entity.BaseEntity;

import java.util.Date;



/**
 * @author tom
 *
 */
public class BaseDomainEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseDomainEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BaseDomainEntity(Long id){
		super(id);
	}
	
	//

	private String name;
	//
	private Long createdBy = 0L ;

	private Long updatedBy = 0L ;
	//
	private Date dateCreatec =new Date();
	//
	private Date lastUpdated = new Date();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getDateCreatec() {
		return dateCreatec;
	}

	public void setDateCreatec(Date dateCreatec) {
		this.dateCreatec = dateCreatec;
	}
}
