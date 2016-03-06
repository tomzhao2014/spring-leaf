package com.qiktone.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qiktone.core.orm.mybatis.MybatisType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 统一定义entity基类.<br>
 * 
 * @author Tom
 * @Date
 */
@JsonInclude(Include.NON_NULL)
public class BaseEntity extends IdEntity implements MybatisType {

	private static final long serialVersionUID = 9071309727314398599L;
	protected final Log logger = LogFactory.getLog(getClass());


	public BaseEntity() {
		
	}

	public BaseEntity(Long id) {
		super();
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
