
package com.hotent.support.dao.catelog;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.support.model.catelog.FileCatalog;

@Repository
public class FileCatalogDao extends BaseDao<FileCatalog>
{
	@Override
	public Class<?> getEntityClass()
	{
		return FileCatalog.class;
	}
public List<FileCatalog> getByIdFromFilecatalog(Long productid){
	HashMap<String, Object> map =new HashMap<String, Object>();
	map.put("productid", productid);
	String statement = getIbatisMapperNamespace() + "." + "getByIdFromFilecatalog";
	return getSqlSessionTemplate().selectList(statement,map); 
}
public List<FileCatalog> getCatalog(String productid){
	HashMap<String, Object> map =new HashMap<String, Object>();
	map.put("productid", productid);
	System.out.println(productid);
	return (List<FileCatalog>) getListBySqlKey("getByIdFromFilecatalog", map);
}
}
