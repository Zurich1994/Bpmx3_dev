package com.hotent.support.service.catelog;
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
import com.hotent.support.model.catelog.Products;
import com.hotent.support.dao.catelog.ProductsDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.bpm.model.ProcessCmd;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;


@Service
public class ProductsService extends BaseService<Products>
{
	@Resource
	private ProductsDao dao;
	
	@Resource
	private ProcessRunService processRunService;
	public ProductsService()
	{
	}
	
	@Override
	protected IEntityDao<Products,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 重写getAll方法绑定流程runId
	 * @param queryFilter
	 */
	public List<Products> getAll(QueryFilter queryFilter){
		List<Products> productsList=super.getAll(queryFilter);
		List<Products> productss=new ArrayList<Products>();
		for(Products products:productsList){
			ProcessRun processRun=processRunService.getByBusinessKey(products.getId().toString());
			if(BeanUtils.isNotEmpty(processRun)){
				products.setRunId(processRun.getRunId());
			}
			productss.add(products);
		}
		return productss;
	}
	
	/**
	 * 流程处理器方法 用于处理业务数据
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd)throws Exception{
		Map data=cmd.getFormDataMap();
		if(BeanUtils.isNotEmpty(data)){
			String json=data.get("json").toString();
			Products products=getProducts(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				products.setId(genId);
				this.add(products);
			}else{
				products.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(products);
			}
			cmd.setBusinessKey(products.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Products对象
	 * @param json
	 * @return
	 */
	public Products getProducts(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Products products = (Products)JSONObject.toBean(obj, Products.class);
		return products;
	}
	public List<Products> getByCATEGORYID(String CATEGORYID){
		return dao.getByCATEGORYID(CATEGORYID);
	}
}
