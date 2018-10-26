
package com.hotent.formParcel.dao.formParcel;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.platform.model.form.BpmFormField;

@Repository
public class FormParcelDao extends BaseDao<FormParcel>
{
	@Override
	public Class<?> getEntityClass()
	{
		return FormParcel.class;
	}
	/**
	 * 通过FormName查找
	 * @param FormName
	 * @return
	 */
	public List<FormParcel> getByFormName(String F_FORM_NAME) {
		return this.getBySqlKey("getByFormName", F_FORM_NAME);
	}

}
