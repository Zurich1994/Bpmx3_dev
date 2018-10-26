package com.hotent.platform.service.system;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.UserPosition;

/**
 *<pre>
 * 对象功能:系统岗位表，实际是部门和职务的对应关系表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 *</pre>
 */
@Service
public class PositionService extends BaseService<Position>
{
	@Resource
	private PositionDao dao;
	@Resource
	UserPositionDao userPositionDao;
	@Resource 
	UserPositionService userPositionService;
	public PositionService()
	{
	}
	
	@Override
	protected IEntityDao<Position, Long> getEntityDao() 
	{
		return dao;
	}
	
	 /**
		 * 根据用户id获取主岗位。
		 * @param userId
		 * @return
		 */
	    public Position getPrimaryPositionByUserId(Long userId) {
	    	return this.dao.getPrimaryPositionByUserId(userId);
	    }
	
		/**
		 * 根据用户id获取岗位列表。
		 * @param userId
		 * @return
		 */
		public List<Position> getByUserId(Long userId){
			return dao.getByUserId(userId);
		}
		
		/**
		 * 根据岗位名称获得岗位信息
		 * @param posName
		 * @return
		 */
		public List<Position> getByPosName(String posName) {
			return dao.getByPosName(posName);
		}
		
		/**
	     * 取得某个用户的岗位id
	     * 
	     * @param userId 用户id
	     * @return
	     */
	    public List<Long> getPositonIdsByUserId(Long userId) {
	    	List<Long> list=new ArrayList<Long>();
	        List<Position> positionList = this.dao.getByUserId(userId);
	        for (Position pos : positionList) {
	            list.add(pos.getPosId());
	        }
	        return list;
	    }
	    
	    /**
		 * 添加岗位和人员。
		 * 	1.如果当前人员是主岗位，则将该人员的之前的岗位修改为非主岗位。
		 * @param position
		 * @param upList
		 * @throws Exception 
		 */
		public void add(Position position,List<UserPosition> upList) throws Exception{
			this.add(position);
			if(BeanUtils.isEmpty(upList)) return;
			for(UserPosition up:upList){
				Long posId=position.getPosId();
				Long userId=position.getPosId();
				
				boolean isPrimary=up.getIsPrimary()==UserPosition.PRIMARY_YES;
				if(isPrimary){
					userPositionDao.updNotPrimaryByUser(userId);
				}
				up.setPosId(posId);
				up.setUserPosId(UniqueIdUtil.genId());
				userPositionDao.add(up);
			}
		}
		
		/**
		 * 根据组织id串得到 组织及岗位的关系
		 * @author hjx
		 * @version 创建时间：2013-11-27  下午3:16:17
		 * @param orgIds
		 * @return
		 */
		public List<Position> getOrgPosListByOrgIds(String orgIds){
			return  dao.getOrgPosListByOrgIds(orgIds);
		}
		

		/**
		 * 根据组织id串得到 组织集合
		 * 只用于 组织岗位树
		 * @author hjx
		 * @version 创建时间：2013-11-27  下午3:16:17
		 * @param orgIds
		 * @return
		 */
		public List<Position> getOrgListByOrgIds(String orgIds){
			return  dao.getOrgListByOrgIds(orgIds);
		}
		
		/**
		 * 删除岗位，实际是修改标志位<br>
		 * 需要顺带将岗位下的人员标志删除
		 * @author hjx
		 * @version 创建时间：2013-12-4  上午10:50:27
		 * @param posId
		 */
		public void deleteByUpdateFlag(Long posId){
			  dao.deleteByUpdateFlag(posId);
			  userPositionService.delByPosId(posId);
		}
		
		/**
		 * 根据用户ID获取默认的的岗位。
		 * @param userId
		 * @return
		 */
		public Position getDefaultPosByUserId(Long userId) {

			List<UserPosition> list = userPositionDao.getByUserId(userId);
			Long posId = 0L;
			// 个人不属于任何一个部门。
			if (BeanUtils.isEmpty(list))
				return null;
			if (list.size() == 1) {
				posId = list.get(0).getPosId();
			} else {
				// 获取主要的组织。
				for (UserPosition userPosition : list) {
					if (userPosition.getIsPrimary() == 1) {
						posId = userPosition.getPosId();
						break;
					}
				}
				// 没有获取到主组织，从列表中获取一个组织作为当前组织。
				if (posId == 0L)
					posId = list.get(0).getPosId();
			}
			return dao.getById(posId);
		}
		
		/**
		 * 通过岗位代码获取岗位
		 * @param posCode 岗位代码
		 * @return
		 */
		public Position getByPosCode(String posCode){
			return dao.getByPosCode(posCode);
		}
			
			public List<Position> getBySupOrgId(QueryFilter filter) {
				return dao.getBySupOrgId(filter);
			}
		
}
