package com.hotent.excelTempData_lc.service.excelTempData;
import java.io.IOException;
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
import com.hotent.excelTempData_lc.model.excelTempData.ExcelsjlsbLc;
import com.hotent.excelTempData_lc.dao.excelTempData.ExcelsjlsbLcDao;
import com.hotent.platform.model.system.SysFile;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ExcelsjlsbLcService extends BaseService<ExcelsjlsbLc>
{
	@Resource
	private ExcelsjlsbLcDao dao;
	
	public ExcelsjlsbLcService()
	{
	}
	
	@Override
	protected IEntityDao<ExcelsjlsbLc,Long> getEntityDao() 
	{
		return dao;
	}

	public List<ExcelsjlsbLc> readExcel(SysFile sysFile) throws IOException {
		// TODO Auto-generated method stub
		
		return dao.readExcel(sysFile);
	}
	public List<ExcelsjlsbLc> readExcel() throws IOException {
		// TODO Auto-generated method stub
		
		return dao.readExcel();
	}
	
}
