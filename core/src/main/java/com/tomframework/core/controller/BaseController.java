/**
 * 
 */
package com.tomframework.core.controller;

import com.tomframework.core.entity.BaseEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


/**
 * @author tom
 * 
 */

public abstract class BaseController<T extends BaseEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4122552516065123555L;

	protected final Log logger = LogFactory.getLog(getClass());
	
	
	protected HttpServletRequest request ;
	protected HttpServletResponse response ;
	protected Model model;
	protected HttpSession session;

	/**
	 * 获取request response 和session
	 * @param request
	 * @param response
	 */
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,Model model){
        this.request = request;  
        this.response = response;
        this.model = model;
		this.session = request.getSession();
    }  
	
	/**
	 * 判断当前请求是否是Ajax请求
	 * 
	 * @return
	 */
	public boolean isAjaxRequest() {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
	
	/**
	 * 判断是否是JSON请求
	 * 
	 * @return
	 */
	public boolean isJsonRequest() {
		String uri = request.getRequestURI();
		return uri.endsWith(".json");
	}


	/**
	 *
	 * @param pageNo
	 * @param model
	 * @return
	 */
	public String index(Integer pageNo, Model model){
		return "index";
	}
	
	/**
	 * 详情页面
	 * @return
	 */
	public String show(Long id) {
		return "show";
	}
	
	
	/**
	 * 新增页面
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	public String edit(Long id,Model model){
		return "edit";
	}
	
	/**
	 * 新建操作
	 * @param entity
	 * @return
	 */
	public  void create(T entity) {

	}

	
	

	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public String update(T entity) {
		return "";
	}

	/**
	 * 单个删除
	 * @param id
	 * @return
	 */
	public String delete(Long id) {
		return "";
	}
	
	
	/**
	 * 批量删除
	 * @return
	 */
	public String batchDelete(String ids){
		return "";
	}


}
