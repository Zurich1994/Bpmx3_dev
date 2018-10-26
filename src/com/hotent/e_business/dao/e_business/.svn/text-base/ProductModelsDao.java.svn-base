
package com.hotent.e_business.dao.e_business;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.e_business.model.e_business.ProductModels;

@Repository
public class ProductModelsDao extends BaseDao<ProductModels>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ProductModels.class;
	}
public List<ProductModels> getProductModels(String Q_REGION_S,String Q_KEYWORDS_S,String Q_CATEGORY_S)
	
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("REGION", Q_REGION_S);
		map.put("KEYWORDS", Q_KEYWORDS_S);
		map.put("CATEGORY", Q_CATEGORY_S);
		String statement = getIbatisMapperNamespace() + "." + "getProductModels";
		return getSqlSessionTemplate().selectList(statement,map);
	
	}
public List<ProductModels> getProductModelsall(String Q_REGION_S,String Q_KEYWORDS_S,String Q_CATEGORY_S)

{
	HashMap<String, Object> map =new HashMap<String, Object>();
	map.put("REGION", Q_REGION_S);
	map.put("KEYWORDS", Q_KEYWORDS_S);
	map.put("CATEGORY", Q_CATEGORY_S);
//	map.put("type", Q_CATEGORY_S);
	String statement = getIbatisMapperNamespace() + "." + "getProductModelsall";
	return getSqlSessionTemplate().selectList(statement,map);

}
}
