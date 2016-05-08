/**
 * 
 */
package com.tomframework.core.entity;

/**
 * @author tom
 *
 */
/**
 * 文件基本对象（封装各个模块通用的Fileobj对象属性）
 * 
 */
public class SimpleFileObj extends BaseEntity {

	private static final long serialVersionUID = 7246794717766828626L;

	public SimpleFileObj() {
		super();
	}

	public SimpleFileObj(Long id) {
		super(id);
	}


	/**
	 * 文件对象名称
	 */
	private String name;

	private Long refId;

	/**
	 * 文件contentType类型
	 */
	private String type;

	/**
	 * 大小
	 */
	private long size;

	public boolean isImage() {
		return (this.getType() != null) && (this.getType().equalsIgnoreCase("image/gif")
				|| this.getType().equalsIgnoreCase("image/jpeg")
				|| this.getType().equalsIgnoreCase("image/x-windows-bmp")
				|| this.getType().equalsIgnoreCase("image/tiff")
				|| this.getType().equalsIgnoreCase("image")
				|| this.getType().equalsIgnoreCase("image/png"));
	}
	
	public boolean isHasRef() {
		return (this.getRefId() != null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}

