package com.hotent.platform.service.bpm;

import java.util.List;
import java.util.Set;

import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.ContextUtil;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;

/**
 * 计算人员接口。
 * 人员计算使用策略模式，方便对新增的策略进行扩展。
 * @author raise.
 *
 */
public interface  IBpmNodeUserCalculation {

	public class PreViewModel{
		/** 发起人*/
		public final static int START_USER=1;
		/**上个任务执行人*/
		public final static int PRE_VIEW_USER=2;
		/**表单用户变量*/
		public final static int FORM_USER=3;
		/**表单组织变量*/
		public final static int FORM_ORG=4;		
		/**表单职务变量*/
		public final static int FORM_JOB=5;	
		/**表单岗位变量*/
		public final static int FORM_POS=6;	
		/** 表单角色变量*/
		public final static int FORM_ROLE=7;
		/**发起人的组织*/
		public final static int START_ORG=8;
		/**上一个执行人的部门*/
		public final static int PRE_ORG=9;
		/**发起人的岗位*/
		public final static int START_POS=10;
		/**发起人的职务*/
		public final static int START_JOB=11;
		//预览标题
		private String title="";
		//类型
		private int type=0;
		/**
		 * 预览标题
		 * @return
		 */
		public String getTitle() {	
			switch (this.type) {
				case START_USER://发起人
					title ="发起人";
					break;
				case PRE_VIEW_USER://上个任务执行人
					title = "上个任务执行人";
					break;
				case FORM_USER://表单用户变量
					title = "表单用户变量";
					break;
				case FORM_ORG://表单组织变量
					title = "表单组织变量";
					break;
				case FORM_POS://表单岗位变量
					title = "表单岗位变量";
					break;
				case FORM_JOB://表单职务
					title = "表单职务变量";
					break;
				case FORM_ROLE://表单角色变量
					title = "表单岗位变量";
					break;
				case START_ORG://发起人的组织
					title = "发起人的组织";
					break;	
				case START_POS://发起人的组织
					title = "发起人的岗位";
					break;	
				case START_JOB://发起人的组织
					title = "发起人的职务";
					break;	
				case PRE_ORG://上一个执行人的部门
					title = "上一个执行人的部门";
					break;	
				default:
					break;
			}
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + type;
			return result;
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PreViewModel other = (PreViewModel) obj;
			if (type != other.type)
				return false;
			return true;
		}
		
		
	}
	

	
	/**
	 * 用于预览。
	 * @param bpmNodeUser
	 * @param vars
	 * @return
	 */
	List<SysUser> getExecutor(BpmNodeUser bpmNodeUser,CalcVars vars);
	
	/**
	 * 用于流程任务。
	 * @param bpmNodeUser
	 * @param vars
	 * @return
	 */
	Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser,CalcVars vars);
	
	/**
	 * 获取流程标题
	 * @return
	 */
	String getTitle();
	
	
	/**
	 * 预览是模拟用户。
	 * @return
	 */
	boolean supportMockModel();
	
	/**
	 * 返回模拟用户
	 * @param bpmNodeUser
	 * @param vars
	 * @return
	 */
	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser);
	
	/**
	 * 是否支持人员预览
	 * @return
	 */
	public boolean supportPreView();

}
