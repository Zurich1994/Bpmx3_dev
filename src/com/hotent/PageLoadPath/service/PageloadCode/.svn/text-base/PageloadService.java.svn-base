package com.hotent.PageLoadPath.service.PageloadCode;
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
import com.hotent.PageLoadPath.model.PageloadCode.Pageload;
import com.hotent.PageLoadPath.dao.PageloadCode.PageloadDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class PageloadService extends BaseService<Pageload>
{
	@Resource
	private PageloadDao dao;
	
	public PageloadService()
	{
	}
	
	@Override
	protected IEntityDao<Pageload,Long> getEntityDao() 
	{
		return dao;
	}
	public void delByIds(String defid,String nodeid){
		dao.delByIds(defid, nodeid);
    }
}
