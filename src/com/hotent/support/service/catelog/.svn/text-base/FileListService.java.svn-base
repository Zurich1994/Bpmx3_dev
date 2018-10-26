package com.hotent.support.service.catelog;
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
import com.hotent.support.model.catelog.FileList;
import com.hotent.support.dao.catelog.FileListDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.BpmNodeSet;


@Service
public class FileListService extends BaseService<FileList>
{
	@Resource
	private FileListDao dao;
	
	public FileListService()
	{
	}
	
	@Override
	protected IEntityDao<FileList,Long> getEntityDao() 
	{
		return dao;
	}
	public List<FileList> getByFILEID(long fileID) {
		return dao.getByFILEID(fileID);
	}
    public List<FileList> getLi(HashMap<String, Object> map){
    	return dao.getLi(map);
    }

    public List<FileList> getp(HashMap<String, Object> map){
    	return dao.getp(map);
    }
    public List<FileList> gety(HashMap<String, Object> map){
    	return dao.gety(map);
    }
}
