package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SealRightDao;
import com.hotent.platform.model.system.SealRight;

/**
 * 对象功能:电子印章权限 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:raise
 * 创建时间:2013-09-17 10:06:00
 */
@Service
public class SealRightService extends BaseService<SealRight>
{
	@Resource
	private SealRightDao sealRightDao;

	
	public SealRightService()
	{
	}
	
	@Override
	protected IEntityDao<SealRight, Long> getEntityDao() 
	{
		return sealRightDao;
	}
	
	@Override
	public void delByIds(Long[] ids) {
		if(BeanUtils.isEmpty(ids)) return;
		for (Long p : ids){
			SealRight seal = this.getById(p);
			delById(p);
		}
	}
	
	
	/**
	 * 按电子印章ID 保存印章对应的对象权限
	 * @param controlTypeOffice 
	 * @return
	 */
	public void saveSealRight(Long sealId,String rightType,String rightIds,String rightNames,Long userId, Short controlType){
		//删除原来的印章权限
		this.delBySealId(sealId,controlType);
		//保存新的印章权限
		Date date = new Date();
		
		if("all".equals(rightType)){
			SealRight sr = new SealRight();
			sr.setId(UniqueIdUtil.genId());
			sr.setSealId(sealId);
			sr.setRightType(rightType);
			sr.setRightId(0l);
			sr.setRightName("所有");
			sr.setCreateUser(userId);
			sr.setCreateTime(date);
			sr.setControlType(controlType);
			sealRightDao.add(sr);
		}else if("not".equals(rightType)){
            logger.info("没有分配权限");
		}else if(StringUtil.isNotEmpty(rightIds)){
			String [] ids = rightIds.split(",");
			String [] names = rightNames.split(",");
			for (int i = 0; i < ids.length; i++)
			{
				SealRight sr = new SealRight();
				sr.setId(UniqueIdUtil.genId());
				sr.setSealId(sealId);
				sr.setRightType(rightType);
				sr.setRightId(Long.parseLong(ids[i]));
				sr.setRightName(names[i]);
				sr.setCreateUser(userId);
				sr.setCreateTime(date);
				sr.setControlType(controlType);
				sealRightDao.add(sr);
			}
		}
	}
	
	/**
	 * 按电子印章ID 获取印章权限
	 * @param controlTypeOffice 
	 * @return
	 */
	public Map getSealRight(Long sealId, Short controlType) {
		Map rightMap = new HashMap();
		List<SealRight> rightList = getRightBySealId(sealId,controlType);
		String rightIds ="";
		String rightNames ="";
		String rightType ="not";
		if(BeanUtils.isNotEmpty(rightList)&&rightList.size()>0){
			for (SealRight sealRight : rightList)
			{
				rightIds += sealRight.getRightId()+",";
				rightNames += sealRight.getRightName()+",";
			}
			rightIds = rightIds.substring(0, rightIds.length()-1);
			rightNames = rightNames.substring(0, rightNames.length()-1);
			rightType = rightList.get(0).getRightType();
		}
		rightMap.put("rightType", rightType);
		rightMap.put("rightIds", rightIds);
		rightMap.put("rightNames", rightNames);
		return rightMap;
	}
	
	
	/**
	 * 按电子印章ID 删除对应的印章权限
	 * @param controlTypeOffice 
	 * @return
	 */
	public int delBySealId(Long sealId, Short controlType) {
		return sealRightDao.delBySealId(sealId,controlType);
	}
	
	
	/**
	 * 按电子印章ID获取对应的印章权限
	 * @return
	 */
	public List<SealRight> getRightBySealId(Long sealId,Short controlType){
		List<SealRight> list = new ArrayList<SealRight>();
		list = sealRightDao.getRightBySealId(sealId,controlType);
		return list;
	}
	
	
	/**
	 * 取分配类型:用户、组织或者角色
	 * @return
	 */
	public List<Map> getRightType(){
		List<Map> list = new ArrayList<Map>();
		
		Map map = new HashMap();
		map = new HashMap();
		map.put("id", "not");
		map.put("name", "没有");
		list.add(map);
		
		map = new HashMap();
		map.put("id", "user");
		map.put("name", "用户");
		list.add(map);
		
		map = new HashMap();
		map.put("id", "role");
		map.put("name", "角色");
		list.add(map);
		
		map = new HashMap();
		map.put("id", "org");
		map.put("name", "组织");
		list.add(map);
		
		map = new HashMap();
		map.put("id", "all");
		map.put("name", "所有");
		list.add(map);
		
		return list;
	}
}
