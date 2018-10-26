package com.hotent.Stukq.service.Stukq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Stukq.model.Stukq.Xskqb;
import com.hotent.Stukq.dao.Stukq.XskqbDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.Stukq.model.Stuzh.Xskqzhb;
import com.hotent.Stukq.dao.Stuzh.XskqzhbDao;


@Service
public class XskqbService extends BaseService<Xskqb>
{
	@Resource
	private XskqbDao dao;
	
	@Resource
	private XskqzhbDao xskqzhbDao;
	public XskqbService()
	{
	}
	
	@Override
	protected IEntityDao<Xskqb,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param id
	 */
	private void delByPk(Long id){
		xskqzhbDao.delByMainId(id);
	}
	
	/**
	 * 删除数据 包含相应子表记录
	 * @param lAryId
	 */
	public void delAll(Long[] lAryId) {
		for(Long id:lAryId){	
			delByPk(id);
			dao.delById(id);	
		}	
	}
	
	/**
	 * 添加数据 
	 * @param xskqb
	 * @throws Exception
	 */
	public void addAll(Xskqb xskqb) throws Exception{
		add(xskqb);
		addSubList(xskqb);
	}
	
	/**
	 * 更新数据
	 * @param xskqb
	 * @throws Exception
	 */
	public void updateAll(Xskqb xskqb) throws Exception{
		update(xskqb);
		delByPk(xskqb.getId());
		addSubList(xskqb);
	}
	
	/**
	 * 添加子表记录
	 * @param xskqb
	 * @throws Exception
	 */
	public void addSubList(Xskqb xskqb) throws Exception{
		List<Xskqzhb> xskqzhbList=xskqb.getXskqzhbList();
		if(BeanUtils.isNotEmpty(xskqzhbList)){
			for(Xskqzhb xskqzhb:xskqzhbList){
				xskqzhb.setRefId(xskqb.getId());
				Long id=UniqueIdUtil.genId();
				xskqzhb.setId(id);
				xskqzhbDao.add(xskqzhb);
			}
		}
	}
	public void addzhb(List<Xskqzhb> xskqzhbList,Long id)
	{
		if(BeanUtils.isNotEmpty(xskqzhbList)){
			for(Xskqzhb xskqzhb:xskqzhbList){
				xskqzhb.setRefId(id);
				Long zid=UniqueIdUtil.genId();
				xskqzhb.setId(zid);
				xskqzhbDao.add(xskqzhb);
			}
		}
	}
	
	/**
	 * 根据外键获得学生考勤综合表列表
	 * @param id
	 * @return
	 */
	public List<Xskqzhb> getXskqzhbList(Long id) {
		return xskqzhbDao.getByMainId(id);
	}
}
