
package com.hotent.definedAtomForm.dao.definedAtomForm;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.definedAtomForm.model.definedAtomForm.DefinedAtomForm;

@Repository
public class DefinedAtomFormDao extends BaseDao<DefinedAtomForm>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DefinedAtomForm.class;
	}
	/**
	 * 根据id获取formKey
	 * @param id
	 *            
	 * @return
	 * */
	public String getFormKeyById(Long id)
	{
		return (String) this.getOne("getFormKeyById", id);
	}
}
