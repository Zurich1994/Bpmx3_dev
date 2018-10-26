/**
 * 描述：TODO
 * 包名：com.hotent.core.page
 * 文件名：PageList.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2015-1-31-下午4:39:50
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.core.page;

import java.util.ArrayList;

/**
 * <pre> 
 * 描述：继承ArrayList 支持 分页对象。
 * 构建组：bpm33_cf_xianjinui
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2015-1-31-下午4:39:50
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@SuppressWarnings("serial")
public class PageList<E> extends ArrayList<E> {

	private  PageBean pageBean=new PageBean();

	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
	public int getTotalPage(){
		return pageBean.getTotalPage();
	}
	
	public int getTotalCount(){
		return pageBean.getTotalCount();
	}
	
	
	
}
