package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;


import com.hotent.core.model.BaseModel;

/**
 * 对象功能:流程授权主表明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 09:00:53
 */

public class BpmDefAuthorize extends BaseModel 
{
	
	// id
	protected Long id;
	
	//流程授权类型  : start,management,task,instance
	protected String authorizeTypes;
	
	//流程授权说明
	protected String authorizeDesc;
	
	//授权类型列表
	protected List<BpmDefAuthorizeType> bpmDefAuthorizeTypeList = new ArrayList<BpmDefAuthorizeType>();

	//授权对象列表
	protected List<BpmDefUser> bpmDefUserList = new ArrayList<BpmDefUser>();
	
	//授权流程列表
	protected List<BpmDefAct> bpmDefActList = new ArrayList<BpmDefAct>();
	
	//授权对象名称(仅用于查询)
	protected String ownerName;
	
	//授权流程名称(仅用于查询)
	protected String defName;
	
	//授权对象名称(仅用于存放授权对象的JSON数据)
	/*
	 * {
	 *   "allJson":"N",
	 *   "userJson":"[
	 *                  {\"ownerId\":\"10000011230209\",\"ownerName\":\"审查B\"},
	 *                  {\"ownerId\":\"10000011230207\",\"ownerName\":\"审查C\"}
	 *               ]",
	 *   "roleJson":"[
	 *   				{\"ownerId\":\"10000000080083\",\"ownerName\":\"角色管理员\"},
	 *   				{\"ownerId\":\"10000000440185\",\"ownerName\":\"部门负责人\"}
	 *   			 ]",
	 *   "orgJson":"[   
	 *                  {\"ownerId\":\"10000011230194\",\"ownerName\":\"采购部门B\"}
	 *              ]",
	 *   "grantJson":"[
	 *   				{\"ownerId\":\"10000016090060\",\"ownerName\":\"广州分公司\"}
	 *   			]",
	 *   "positionJson":"[
	 *   				{\"ownerId\":\"10000011230203\",\"ownerName\":\"采购部门A_采购专员\"},
	 *   				{\"ownerId\":\"37\",\"ownerName\":\"广州宏天白云区分公司销售部门_宏天软件工程师\"}
	 *   			]"
	 * }
  	*/
	protected String ownerNameJson;
	
	//授权流程名称(仅用于存放授权流程的JSON数据)
	
  /* 
   * {"defArry":
   * 	  [{ "defId":"10000018130014",
   *         "defKey":"zchz",
   *         "defName":"周程汇总",
   *         "right":{"edit":"N","del":"N","start":"N","set":"N"}
   *       },
   *       {"defId":"10000017980009",
   *        "defKey":"csjdsz",
   *        "defName":"测试节点设置",
   *        "right":{"edit":"N","del":"N","start":"N","set":"N"}
   *       },
   *       {"defId":"10000017860008",
   *        "defKey":"gxzlc",
   *        "defName":"共享子流程",
   *        "right":{"edit":"N","del":"N","start":"N","set":"N"}}]
   *       }
  */	
	protected String defNameJson;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAuthorizeTypes()
	{
		return authorizeTypes;
	}

	public void setAuthorizeTypes(String authorizeTypes)
	{
		this.authorizeTypes = authorizeTypes;
	}

	public String getAuthorizeDesc()
	{
		return authorizeDesc;
	}

	public void setAuthorizeDesc(String authorizeDesc)
	{
		this.authorizeDesc = authorizeDesc;
	}

	public List<BpmDefAuthorizeType> getBpmDefAuthorizeTypeList()
	{
		return bpmDefAuthorizeTypeList;
	}

	public void setBpmDefAuthorizeTypeList(List<BpmDefAuthorizeType> bpmDefAuthorizeTypeList)
	{
		this.bpmDefAuthorizeTypeList = bpmDefAuthorizeTypeList;
		this.authorizeTypes = "";
		if(this.bpmDefAuthorizeTypeList.size()>0){
			for (BpmDefAuthorizeType bpmDefAuthorizeType : bpmDefAuthorizeTypeList){
				this.authorizeTypes += bpmDefAuthorizeType.getAuthorizeType() + ",";
			}
			this.authorizeTypes = this.authorizeTypes.substring(0, this.authorizeTypes.length()-1);
		}
	}

	public List<BpmDefUser> getBpmDefUserList()
	{
		return bpmDefUserList;
	}

	public void setBpmDefUserList(List<BpmDefUser> bpmDefUserList)
	{
		this.bpmDefUserList = bpmDefUserList;
	}

	public List<BpmDefAct> getBpmDefActList()
	{
		return bpmDefActList;
	}

	public void setBpmDefActList(List<BpmDefAct> bpmDefActList)
	{
		this.bpmDefActList = bpmDefActList;
	}

	public String getOwnerName()
	{
		return ownerName;
	}

	public void setOwnerName(String ownerName)
	{
		this.ownerName = ownerName;
	}

	public String getDefName()
	{
		return defName;
	}

	public void setDefName(String defName)
	{
		this.defName = defName;
	}

	public String getOwnerNameJson()
	{
		return ownerNameJson;
	}

	public void setOwnerNameJson(String ownerNameJson)
	{
		this.ownerNameJson = ownerNameJson;
	}

	public String getDefNameJson()
	{
		return defNameJson;
	}

	public void setDefNameJson(String defNameJson)
	{
		this.defNameJson = defNameJson;
	}
		

}