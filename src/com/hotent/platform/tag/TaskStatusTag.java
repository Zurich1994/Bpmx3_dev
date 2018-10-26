package com.hotent.platform.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.hotent.core.util.ContextUtil;

/**
 * 流程任务审批状态
 * 
 * @author zxh
 * 
 */
public class TaskStatusTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspTagException {

		try {
			String str = getTaskStatus(this.status,this.flag);
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * 获取流程状态
	 * 
	 * @param status
	 * @param flag 
	 * @return 流程状态
	 */
	public static String getTaskStatus(Short status, int flag) {
		switch (status) {
			case -2://尚未审批
				return "<font color='red'>尚未审批</font>";
			case -1://尚未处理(0) || 正在审批(1)
				return (flag==0?"尚未处理":("<font color='red'>正在审批</font>"));
			case 0://弃权
				return "<font color='red'>弃权</font>";
			case 1://同意
				return "<font color='green'>同意</font>";
			case 2://反对
				return "<font color='orange'>反对</font>";
			case 3://驳回
				return "<font color='red'>驳回</font>";
			case 4://撤销
				return "<font color='red'>撤销</font>";
			case 5://会签通过
				return "<font color='green'>会签通过</font>";
			case 6://会签不通过
				return "<font color='red'>会签不通过</font>";
			case 7://知会意见
				return "<font color='red'>知会意见</font>";
			case 8://更改执行路径
				return "<font color='red'>更改执行路径</font>";
			case 9://终止
				return "<font color='red'>终止</font>";
			case 10://沟通意见
				return "<font color='red'>沟通意见</font>";
			case 14://终止
				return "<font color='red'>终止</font>";
			case 15://沟通
				return "<font color='red'>沟通 </font>";
			case 16://办结转发
				return "<font color='orange'>办结转发</font>";
			case 17://撤销
				return "<font color='orange'>撤销</font>";
			case 18://删除
				return "<font color='orange'>删除</font>";
			case 19://抄送
				return "<font color='orange'>抄送</font>";
			case 20://沟通反馈
				return "<font color='green'>沟通反馈</font>";
			case 21://转办
				return "<font color='red'>转办</font>";
			case 22://取消转办
				return "<font color='red'>取消转办</font>";
			case 23://更改执行人
				return "<font color='red'>更改执行人</font>";
			case 24://驳回到发起人
				return "<font color='red'>驳回到发起人</font>";
			case 25://撤销(撤销到发起人)
				return "<font color='red'>撤销(撤销到发起人)</font>";
			case 26://代理
				return "<font color='brown'>代理</font>";
			case 27://取消代理
				return "<font color='green'>取消代理</font>";
			case 28://表单意见
				return "<font color='green'>表单意见</font>";	
			case 29://表单意见
				return "<font color='green'>驳回取消</font>";	
			case 30://撤销取消
				return "<font class='brown'>撤销取消</font>";
			case 31://通过取消
				return "<font class='brown'>通过取消</font>";
			case 32://反对取消
				return "<font class='brown'>反对取消</font>";
			case 33://提交
				return "<font class='green'>提交</font>";
			case 34://重新提交
				return "<font class='green'>重新提交</font>";
			case 35://干预
				return "<font class='brown'>干预</font>";
			case 36://重启任务
				return "<font class='brown'>重启任务</font>";
			case 38://流转
				return "<font color='green'>流转</font>";
			case 40://代提交
				return "<font color='red'>代提交</font>";
			case 42://取消流转
				return "<font color='red'>取消流转</font>";
			case 43://添加流转人
				return "<font color='red'>添加流转人</font>";
			case 44://补签
				return "<font color='red'>补签</font>";
			case 45://追回
				return "<font color='red'>追回</font>";
			default:
				break;
		}
		return "";
	}

	private Short status;
	//标记是否审批
	private int flag = 0;

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

}
