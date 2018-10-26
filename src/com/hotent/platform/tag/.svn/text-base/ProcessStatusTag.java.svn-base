package com.hotent.platform.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.hotent.core.util.ContextUtil;


/**
 * 获取流程状态
 * @author zxh
 *
 */
public class ProcessStatusTag  extends BodyTagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspTagException {

		try {
			String str = getProcessStatus(this.status);
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}



	private String getProcessStatus(Short status) {
		switch (status) {	
			case 0:
				return "<font color='red'>挂起</font>";
			case 1:
				return "<font color='green'>正在运行</font>";
			case 2:
				return "<font color='green'>结束</font>";
			case 3:
				return "<font color='red'>人工结束</font>";
			case 4:
				return "<font color='brown'>草稿</font>";
			case 5:
				return "<font color='red'>撤销</font>";
			case 6:
				return "<font color='red'>驳回</font>";
			case 7:
				return "<font color='red'>追回</font>";
			case 10:
				return "<font color='red'>逻辑删除</font>";
			default:
				break;
		}	
		return "";
	}
	
	private Short status;
	

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}



}
