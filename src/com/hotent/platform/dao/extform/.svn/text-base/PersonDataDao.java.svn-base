
package com.hotent.platform.dao.extform;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.extform.PersonData;
import java.util.*;
import com.hotent.core.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import com.hotent.core.util.ContextUtil;

@Repository
public class PersonDataDao extends BaseDao<PersonData>
{
	@Override
	public Class<?> getEntityClass()
	{
		return PersonData.class;
	}
	
	
	/**
	* 新增数据
	* @param cmd
	* @throws Exception
	*/
		@SuppressWarnings("unchecked")
		public void add(ProcessCmd cmd) throws Exception
		{
			PersonData personData = new PersonData();
			Map map= cmd.getFormDataMap();
			Long id=UniqueIdUtil.genId();
			personData.setId(id);
			personData.setUsername(map.get("username").toString());
			//personData.setApplydays(map.get("applydays").toString());
			//personData.setApprovedays(map.get("approvedays").toString());
			this.add(personData);
			//写回主键，这个数据将保存到流程中。
			cmd.setBusinessKey(id.toString());
		}
		
		
		/**
		* 更新数据
		* @param cmd
		* @throws Exception
		*/
		@SuppressWarnings("unchecked")
		public void updateById(ProcessCmd cmd) throws Exception
		{
			Map map= cmd.getFormDataMap();
			String id=(String) map.get("businessKey");
			PersonData personData = this.getById(new Long(id));
			personData.setUsername(map.get("username").toString());
			//personData.setApplydays(map.get("applydays").toString());
			//personData.setApprovedays(map.get("approvedays").toString());
			this.update(personData);
		}

}
