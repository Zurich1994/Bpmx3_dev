package com.hotent.platform.service.system;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.dao.system.DictionaryDao;
import com.hotent.platform.dao.system.GlobalTypeDao;
import com.hotent.platform.model.system.Dictionary;
import com.hotent.platform.model.system.GlobalType;


/**
 * 对象功能:数据字典 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ljf
 * 创建时间:2011-11-23 11:07:27
 */
@Service
public class DictionaryService extends BaseService<Dictionary>
{
	@Resource
	private DictionaryDao dictionaryDao;
	@Resource
	private GlobalTypeDao globalTypeDao;
	
	
	public DictionaryService()
	{
	}
	
	@Override
	protected IEntityDao<Dictionary, Long> getEntityDao() {
		return dictionaryDao;
	}
	
	/**
	 * 根据分类表的nodekey获取数据字典数据。
	 * @param nodeKey
	 * @return
	 */
	public List<Dictionary> getByNodeKey(String nodeKey){
		GlobalType globalType =globalTypeDao.getByDictNodeKey(nodeKey);
		if (globalType == null){
			return new ArrayList<Dictionary>();
		}
		long typeId=globalType.getTypeId();
		return dictionaryDao.getByTypeId(typeId);
	}
	 
	/**
	 * 根据分类ID获取字典。
	 * 根据分类添加一个根节点。
	 * @param typeId 分类ID
	 * @param needRoot	是否需要根节点。
	 * @return
	 */
	public List<Dictionary> getByTypeId(long typeId,boolean needRoot){
		GlobalType globalType=globalTypeDao.getById(typeId);
		
		List<Dictionary> list=dictionaryDao.getByTypeId( typeId);
		for(Dictionary dic:list){
			dic.setType(globalType.getType());
		}
		if(needRoot){
			Dictionary dictionary=new Dictionary();
			dictionary.setDicId(typeId);
			dictionary.setParentId(0L);
			dictionary.setItemName(globalType.getTypeName());
			dictionary.setType(globalType.getType());
			list.add(0, dictionary);
		}
		return list;
	}
	
	/**
	 * 根据分类ID获取字典。
	 * 根据分类添加一个根节点。
	 * @param typeId 分类ID
	 * @param needRoot	是否需要根节点。
	 * @return
	 */
	public List<Dictionary> getByParentId(long parentId){
		List<Dictionary> list=dictionaryDao.getByParentId( parentId);
		return list;
	}
	/**
	 * 根据字典ID删除字典数据。
	 * @param dicId		字典ID。
	 */
	public void delByDicId(Long dicId){
		Dictionary dictionary= dictionaryDao.getById(dicId);
		String nodePath=dictionary.getNodePath();
		List<Dictionary> list= dictionaryDao.getByNodePath(nodePath);
		for(Dictionary dic:list){
			dictionaryDao.delById(dic.getDicId());
		}
	}
	
	/**
	 * 判断关键字是否存在。用于添加时进行判断。
	 * @param typeId		分类id
	 * @param itemKey		字典key
	 * @return
	 */
	public boolean isItemKeyExists(long typeId,String itemKey)
	{
		return dictionaryDao.isItemKeyExists(typeId, itemKey);
	}
	
	/**
	 * 判断字典关键字是否存在，用于更新是做判断。
	 * @param dicId
	 * @param typeId
	 * @param itemKey
	 * @return
	 */
	public boolean isItemKeyExistsForUpdate(long dicId, long typeId,String itemKey)
	{
		return dictionaryDao.isItemKeyExistsForUpdate(dicId,typeId, itemKey);
	}
	
	
	/**
	 * 更新字典排序。
	 * @param lAryId
	 */
	public void updSn(Long[] lAryId){
		if(BeanUtils.isEmpty(lAryId)) 	return;
		for(int i=0;i<lAryId.length;i++){
			int sn=i+1;
			Long dicId=lAryId[i];
			dictionaryDao.updSn(dicId, sn);
		}
	}
	
	
	
	
	/**
	 * 拖动移动节点。
	 * @param targetId		目标id
	 * @param dragId		拖动的节点id
	 * @param moveType		拖动类型 (prev,next,inner);
	 */
	public void move(Long targetId,Long dragId,String moveType){
		Dictionary target=dictionaryDao.getById(targetId);
		Dictionary dragged=dictionaryDao.getById(dragId);
		
		String nodePath=dragged.getNodePath();
		List<Dictionary> list=dictionaryDao.getByNodePath(nodePath);
		
		//修改拖动节点的父亲
		for(Dictionary dictionary:list){
			
			if ("prev".equals(moveType) || "next".equals(moveType)) {
				String targetPath=target.getNodePath();
				String parentPath=targetPath.endsWith(".")?targetPath.substring(0,targetPath.length()-1):targetPath;
				//这个路径尾部带 "." 。
				parentPath=parentPath.substring(0,parentPath.lastIndexOf(".")+1) ;
				
				if(dictionary.getDicId().equals(dragId)){
					dictionary.setParentId(target.getParentId());
					dictionary.setNodePath(parentPath + dragId +".");
				}
				else{
					String path = dictionary.getNodePath();
					String tmpPath =parentPath + dragId +"." +   path.replaceAll(nodePath, "");
					dictionary.setNodePath(tmpPath);
				}
				if ("prev".equals(moveType)) {
					dictionary.setSn(target.getSn()-1);
				} else {
					dictionary.setSn(target.getSn() + 1);
				}
			}
			else{
				//
				if(dictionary.getDicId().equals(dragId)){
					//修改拖动的分类对象
					//需改父节点
					dictionary.setParentId(targetId);
					//修改nodepath
					dictionary.setNodePath(target.getNodePath() + dictionary.getDicId() +".");
				}
				else{
					//带点的路径
					String path=dictionary.getNodePath();
					//替换父节点的路径。
					String tmpPath=path.replaceAll(nodePath, "");
					//新的父节路径
					String targetPath=target.getNodePath() ;
					//新的父节点 +拖动的节点id + 后缀
					String tmp =targetPath + dragged.getDicId() + "." + tmpPath;
				
					dictionary.setNodePath(tmp);
				}
			}
			
			dictionaryDao.update(dictionary);
		}
		
	}
	
	
}
