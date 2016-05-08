package com.tomframework.core.entity;

import java.io.Serializable;


/**
 * 统一定义id的entity基类.<br>
 * ID为Long型<br>
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.<br>
 * 子类可重载getId()函数重定义id的列名映射和生成策略.<br>
 * JSONFieldBridge将Long型值转换为String传入到js中
 * @author Tom
 */
//@MappedSuperclass
public abstract class IdEntity implements Serializable{

	private static final long serialVersionUID = -205166679120870421L;

	protected Long id;

//	@Id
//	@GeneratedValue(generator = "longIdGenerator")
//	@GenericGenerator(name = "longIdGenerator", strategy = "com.weaver.teams.core.orm.entity.LongIdGenerator")
//	@JSONFieldBridge
//	@JsonSerialize(using=ToStringSerializer.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
