
package com.hotent.Markovchain.dao.Markovchain;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.Markovchain.model.Markovchain.Markovchain;

@Repository
public class MarkovchainDao extends BaseDao<Markovchain>
{
	private static final List<Markovchain> String = null;

	@Override
	public Class<?> getEntityClass()
	{
		return Markovchain.class;
	}

	/**
	 * 根据流程ID获得马尔科夫链
	 * @author 魏嫚 
	 *
	 */
	public List<Markovchain> getByDefId(Long defId){
		return getBySqlKey("getByDefId", defId);
	}
	public void copyByIds(Long[] lAryId) {
		// TODO Auto-generated method stub
		
	}

	public String getmarkXmlById(Long id) {
		// TODO Auto-generated method stub
		List<Markovchain> list=getBySqlKey("getmarkXmlById", id);
		Markovchain markovchain=list.get(0);
		if(markovchain.getMarkovchainXML()!=null)
			return markovchain.getMarkovchainXML();
		else 
			return null;
	}


}