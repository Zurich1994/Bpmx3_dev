
package com.hotent.support.dao.catelog;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.support.model.catelog.Products;

@Repository
public class ProductsDao extends BaseDao<Products>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Products.class;
	}
	public List<Products> getByCATEGORYID(String CATEGORYID){
		return (List<Products>) getListBySqlKey("getByCATEGORYID", CATEGORYID);
	}

}
