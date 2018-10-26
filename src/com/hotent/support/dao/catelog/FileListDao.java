
package com.hotent.support.dao.catelog;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.support.model.catelog.FileList;

@Repository
public class FileListDao extends BaseDao<FileList>
{
	@Override
	public Class<?> getEntityClass()
	{
		return FileList.class;
	}
	public List<FileList> getByFILEID(long fileID){
		return getBySqlKey("getByFILEID", fileID);
		}
    public List<FileList> getLi(HashMap<String, Object> map){
    	return (List<FileList>) getListBySqlKey("getByLi",map);
    }
    public List<FileList> getp(HashMap<String, Object> map){
    	return (List<FileList>) getListBySqlKey("getByp",map);
    }
    public List<FileList> gety(HashMap<String, Object> map){
    	return (List<FileList>) getListBySqlKey("getByy",map);
    }
}
