package com.hotent.tpcc.service.tpcc;
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
import com.hotent.tpcc.model.tpcc.District;
import com.hotent.tpcc.dao.tpcc.DistrictDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DistrictService extends BaseService<District>
{
	@Resource
	private DistrictDao dao;
	
	public DistrictService()
	{
	}
	
	@Override
	protected IEntityDao<District,Long> getEntityDao() 
	{
		return dao;
	}
	
	public District orderno(String D_ID,String C_ID){
		return dao.orderno(D_ID,C_ID);
		
	};
	public Integer itemno(String D_ID,String W_ID,String D_NEXT_O_ID,String D_LEVEL)
	{
		return dao.itemno(D_ID,W_ID,D_NEXT_O_ID,D_LEVEL);
		
	};


	public List<District> getorderMsg(String D_ID,String C_ID){
		return dao.getorderMsg(D_ID,C_ID);
		
	};
	public List<District> getorderMsg2(String D_ID,String W_ID){
		return dao.getorderMsg2(D_ID,W_ID);
		
	};
	public void updateDis(Long stockStoreNumber, Long stockAreaNumber,
			Long orderValue) {
		// TODO Auto-generated method stub
		dao.updateDis(stockStoreNumber,stockAreaNumber,orderValue);
	}

	public District getByStoreIdAndAreaId(
			Long stockAreaNumber, Long stockStoreNumber) {
		// TODO Auto-generated method stub
		return dao.getByStoreIdAndAreaId(stockAreaNumber,stockStoreNumber);
	}
}
