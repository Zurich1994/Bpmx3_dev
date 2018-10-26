package com.hotent.definedAtomForm.service.definedAtomForm;
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
import com.hotent.definedAtomForm.model.definedAtomForm.DefinedAtomForm;
import com.hotent.definedAtomForm.dao.definedAtomForm.DefinedAtomFormDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DefinedAtomFormService extends BaseService<DefinedAtomForm>
{
	@Resource
	private DefinedAtomFormDao dao;
	
	public DefinedAtomFormService()
	{
	}
	
	@Override
	protected IEntityDao<DefinedAtomForm,Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 根据id获取formKey
	 * @param id
	 *            
	 * @return
	 * */
	public String getFormKeyById(Long id) {
		return dao.getFormKeyById(id);
	}
}
