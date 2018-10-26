package com.hotent.formParcel.service.formParcel;
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
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.dao.formParcel.FormParcelDao;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class FormParcelService extends BaseService<FormParcel>
{
	@Resource
	private FormParcelDao dao;
	
	public FormParcelService()
	{
	}
	
	@Override
	protected IEntityDao<FormParcel,Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 通过FormName查找
	 * 
	 * @param FormName 自定义表单名称
	 * @return
	 */
	public List<FormParcel> getByFormName(String F_FORM_NAME)
	{
		return dao.getByFormName(F_FORM_NAME);
	}
	
}
