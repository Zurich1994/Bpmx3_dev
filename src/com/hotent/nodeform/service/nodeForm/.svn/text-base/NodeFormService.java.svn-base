package com.hotent.nodeform.service.nodeForm;
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
import com.hotent.nodeform.model.nodeForm.NodeForm;
import com.hotent.nodeform.dao.nodeForm.NodeFormDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class NodeFormService extends BaseService<NodeForm>
{
	@Resource
	private NodeFormDao dao;
	
	public NodeFormService()
	{
	}
	
	@Override
	protected IEntityDao<NodeForm,Long> getEntityDao() 
	{
		return dao;
	}

	public Long getFormID(String string, String string2,
			String string3) {
		// TODO Auto-generated method stub
		return dao.getFormID(string, string2,string3);
	}
	
	public Long getTableID(String string, String string2,
			String string3){
		
		Long formID =getFormID(string, string2, string3);
		return dao.getTableID(formID);
	}
	
	public String getTableName(String string, String string2,
			String string3){
		Long tableID = getTableID(string, string2, string3);
		String tableName = "w_"+dao.getTableName(tableID);
		return tableName;
	}
	
}
