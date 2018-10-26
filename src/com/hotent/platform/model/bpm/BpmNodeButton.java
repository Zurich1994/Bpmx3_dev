package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.UniqueIdUtil;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:自定义工具条 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * <pre>
 * 启动节点按钮类型对应数字：
 * 启动流程：1
 * 流程图     ：2
 * 打印          ：3
 * 保存表单：6
 * Web签章：14
 * 手写签章：15
 * 
 * 其他节点按钮类型对应数字：
 * 同意：1
 * 反对：2
 * 弃权：3
 * 驳回：4
 * 驳回到发起人：5
 * 交办：6
 * 补签：7
 * 保存表单：8
 * 流程图：9
 * 打印：10
 * 审批历史：11
 * Web签章：14
 * 手写签章：15
 * 沟通意见:16
 * 加签：17
 * 
 * 内部按钮：
 * 行内：
 * 编辑 edit 19
 * 删除 del  20
 * 明细 detail 21
 * 单行启动流程 start 22
 * 单行重启流程 restart 23
 * 行内自定义 diyinside 24
 * 行外：
 * 新增 add 25
 * 批量删除  delall 26 
 * 导出 export 27
 * 导入 import 28
 * 打印 print 29
 * 批量启动流程 starts 30
 * 批量重启流程 restarts 31
 * 行外自定义 diyoutside 32
 * </pre>
 * 创建时间:2012-07-25 18:26:11
 */
@XmlRootElement(name = "bpmNodeButton")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNodeButton extends BaseModel
{

	public BpmNodeButton(){
		
	}
	
	public BpmNodeButton(String actdefid,Long defId,String nodeid,String btnname,Integer operatortype,Integer nodetype) throws Exception{
		this.id=UniqueIdUtil.genId();
		this.actdefid=actdefid;
		this.defId=defId;
		this.defbbId=defbbId;
		this.nodeid=nodeid;
		this.btnname=btnname;
		this.operatortype=operatortype;
		this.nodetype=nodetype;
		this.isstartform=0;
		this.sn=this.id;
	}
	
	public BpmNodeButton(String actdefid,Long defId,String btnname,Integer operatortype) throws Exception{
		this.id=UniqueIdUtil.genId();
		this.actdefid=actdefid;
		this.defId=defId;
		this.defbbId=defbbId;
		this.btnname=btnname;
		this.operatortype=operatortype;
		this.isstartform=1;
		this.sn=this.id;
	}
	
	
	//开始按钮

	/**
	 * 提交
	 */
	public final static  int START_BUTTON_TYPE_START = 1;
		
	/**
	 * 流程示意图
	 */
	public final static  int START_BUTTON_TYPE_IMAGE = 2;
	
	/**
	 * 打印
	 */
	public final static  int START_BUTTON_TYPE_PRINT = 3;
	
	/**
	 * 保存草稿
	 */
	public final static  int START_BUTTON_TYPE_SAVE = 6;
	
	//end 开始按钮

	//任务按钮
	/**
	 * 同意
	 */
	public final static  int NODE_BUTTON_TYPE_COMPLETE = 1;
	
	/**
	 * 反对
	 */
	public final static  int NODE_BUTTON_TYPE_OPPOSE = 2;
	
	/**
	 * 弃权
	 */
	public final static  int NODE_BUTTON_TYPE_ABSTENT = 3;
	
	/**
	 * 驳回
	 */
	public final static  int NODE_BUTTON_TYPE_BACK = 4;
	
	/**
	 * 驳回到发起人
	 */
	public final static  int NODE_BUTTON_TYPE_BACKTOSTART = 5;
	
	/**
	 * 转办
	 */
	public final static  int NODE_BUTTON_TYPE_ASSIGNEE = 6;
	
	/**
	 * 补签
	 */
	public final static  int NODE_BUTTON_TYPE_ADDSIGN = 7;
	
	/**
	 * 保存表单
	 */
	public final static  int NODE_BUTTON_TYPE_SAVEFORM = 8;
	
	/**
	 * 流程示意图
	 */
	public final static  int NODE_BUTTON_TYPE_IMAGE = 9;
	
	/**
	 * 打印
	 */
	public final static  int NODE_BUTTON_TYPE_PRINT = 10;
	
	/**
	 * 审批历史
	 */
	public final static  int NODE_BUTTON_TYPE_HIS = 11;
	
	/**
	 * 终止
	 */
	public final static  int NODE_BUTTON_TYPE_END = 15;
	
	/**
	 * 沟通
	 */
	public final static  int NODE_BUTTON_TYPE_COMMU = 16;
	
	
	/**
	 * 转办管理员
	 */
	public final static  int NODE_BUTTON_TYPE_ASSIGNEEMANAGE = 17;
	/**
	 * 沟通反馈
	 */
	public final static  int NODE_BUTTON_TYPE_FEEDBACK = 18;
	/**
	 * 撤销
	 */
	public final static  int NODE_BUTTON_TYPE_REVERT = 43;
	
	/**
	 *  主键
	 */
	@XmlAttribute
	protected Long  id=0L;
	/**
	 *  流程定义ID
	 */
	@XmlAttribute
	protected String  actdefid;
	/**
	 * 流程定义ID
	 */
	@XmlAttribute
	protected Long  defId=0L;
	/**
	 * 节点流程定义ID
	 */
	@XmlAttribute
	protected Long  defbbId=0L;
	/**
	 *  流程启动表单 0,启动表单1,任务表单
	 */
	@XmlAttribute
	protected Integer  isstartform=0;
	/**
	 *  节点ID
	 */
	@XmlAttribute
	protected String  nodeid;
	/**
	 *  按钮名称
	 */
	@XmlAttribute
	protected String  btnname;
	/**
	 *  图标样式
	 */
	@XmlAttribute
	protected String  iconclsname;
	/**
	 *  操作类型
	 */
	@XmlAttribute
	protected Integer  operatortype=0;
	/**
	 *  前置脚本
	 */
	@XmlAttribute
	protected String  prevscript;
	/**
	 *  后置脚本
	 */
	@XmlAttribute
	protected String  afterscript;
	/**
	 *  节点类型 0，普通节点1，会签节点
	 */
	@XmlAttribute
	protected Integer  nodetype=-1;
	/**
	 *  排序
	 */
	@XmlAttribute
	protected Long  sn=0L;
	/**
	 *  下一节点id
	 */
	@XmlAttribute
	protected String nnid;
	/**
	 * 备注
	 */
	@XmlAttribute
	protected String btdes;
	
	public String getNnid() {
		return nnid;
	}

	public void setNnid(String nnid) {
		this.nnid = nnid;
	}

	public String getBtdes() {
		return btdes;
	}

	public void setBtdes(String btdes) {
		this.btdes = btdes;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setActdefid(String actdefid) 
	{
		this.actdefid = actdefid;
	}
	
	
	
	public Long getDefbbId() {
		return defbbId;
	}

	public void setDefbbId(Long defbbId) {
		this.defbbId = defbbId;
	}

	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getActdefid() 
	{
		return this.actdefid;
	}
	public Long getDefId() {
		return defId;
	}
	public void setDefId(Long defId) {
		this.defId = defId;
	}
	public void setIsstartform(Integer isstartform) 
	{
		this.isstartform = isstartform;
	}
	/**
	 * 返回 流程启动表单
	 *	1,启动表单
	 *	0,任务表单
	 * @return
	 */
	public Integer getIsstartform() 
	{
		return this.isstartform;
	}
	public void setNodeid(String nodeid) 
	{
		this.nodeid = nodeid;
	}
	/**
	 * 返回 节点ID
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
	public void setBtnname(String btnname) 
	{
		this.btnname = btnname;
	}
	/**
	 * 返回 按钮名称
	 * @return
	 */
	public String getBtnname() 
	{
		return this.btnname;
	}
	public void setIconclsname(String iconclsname) 
	{
		this.iconclsname = iconclsname;
	}
	/**
	 * 返回 图标样式
	 * @return
	 */
	public String getIconclsname() 
	{
		return this.iconclsname;
	}
	public void setOperatortype(Integer operatortype) 
	{
		this.operatortype = operatortype;
	}
	/**
	 * 返回 操作类型
	 * @return
	 */
	public Integer getOperatortype() 
	{
		return this.operatortype;
	}
	public void setPrevscript(String prevscript) 
	{
		this.prevscript = prevscript;
	}
	/**
	 * 返回 前置脚本
	 * @return
	 */
	public String getPrevscript() 
	{
		return this.prevscript;
	}
	public void setAfterscript(String afterscript) 
	{
		this.afterscript = afterscript;
	}
	/**
	 * 返回 后置脚本
	 * @return
	 */
	public String getAfterscript() 
	{
		return this.afterscript;
	}
	public void setNodetype(Integer nodetype) 
	{
		this.nodetype = nodetype;
	}
	/**
	 * 返回 节点类型0，普通节点1，会签节点
	 * @return
	 */
	public Integer getNodetype() 
	{
		return this.nodetype;
	}
	public void setSn(Long sn) 
	{
		this.sn = sn;
	}
	/**
	 * 返回 排序
	 * @return
	 */
	public Long getSn() 
	{
		return this.sn;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmNodeButton)) 
		{
			return false;
		}
		BpmNodeButton rhs = (BpmNodeButton) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actdefid, rhs.actdefid)
		.append(this.isstartform, rhs.isstartform)
		.append(this.nodeid, rhs.nodeid)
		.append(this.btnname, rhs.btnname)
		.append(this.iconclsname, rhs.iconclsname)
		.append(this.operatortype, rhs.operatortype)
		.append(this.prevscript, rhs.prevscript)
		.append(this.afterscript, rhs.afterscript)
		.append(this.nodetype, rhs.nodetype)
		.append(this.sn, rhs.sn)
		.append(this.nnid, rhs.nnid)
		.append(this.btdes, rhs.btdes)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.actdefid) 
		.append(this.isstartform) 
		.append(this.nodeid) 
		.append(this.btnname) 
		.append(this.iconclsname) 
		.append(this.operatortype) 
		.append(this.prevscript) 
		.append(this.afterscript) 
		.append(this.nodetype) 
		.append(this.sn)
		.append(this.nnid)
		.append(this.btdes)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("actdefid", this.actdefid) 
		.append("isstartform", this.isstartform) 
		.append("nodeid", this.nodeid) 
		.append("btnname", this.btnname) 
		.append("iconclsname", this.iconclsname) 
		.append("operatortype", this.operatortype) 
		.append("prevscript", this.prevscript) 
		.append("afterscript", this.afterscript) 
		.append("nodetype", this.nodetype) 
		.append("sn", this.sn) 
		.append("nnid",this.nnid)
		.append("btdes",this.btdes)
		.toString();
	}
   
  

}