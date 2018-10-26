package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.DesktopColumnDao;
import com.hotent.platform.dao.system.DesktopLayoutDao;
import com.hotent.platform.dao.system.DesktopLayoutcolDao;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.model.system.DesktopLayout;
import com.hotent.platform.model.system.DesktopLayoutcol;
import com.hotent.platform.model.system.DesktopMycolumn;

/**
 * 对象功能:桌面栏目管理表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Service
public class DesktopLayoutcolService extends BaseService<DesktopLayoutcol>
{
	private static Log logger = LogFactory.getLog(DesktopLayoutcolService.class);
	@Resource
	private DesktopLayoutcolDao dao;
	
	@Resource 
	private DesktopColumnService desktopColumnService;
	@Resource
	private DesktopLayoutDao desktopLayoutDao;
	@Resource 
	private FreemarkEngine freemarkEngine;
	public DesktopLayoutcolService()
	{
	}
	
	@Override
	protected IEntityDao<DesktopLayoutcol, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据布局ID删除布局桌面
	 * @param layoutId
	 */
	public void delByLayoutId(Long layoutId) {
		dao.delByLayoutId(layoutId);
	}
	
	/**
	 * 删除非此布局ID的布局桌面
	 * @param layoutId
	 */
	public void delByNoLayoutId(Long layoutId) {
		dao.delByNoLayoutId(layoutId);
	}
	
	/**
	 * 根据布局ID获得布局桌面
	 * @param layoutId
	 * @return
	 */
	public List<DesktopLayoutcol> getByLayoutId(Long layoutId) {
		return dao.getByLayoutId(layoutId);
	}
	
	/**
	 * 编辑桌面栏目数据
	 * @param id
	 * @param desktopColumnmap
	 * @param desktopLayoutmap
	 * @return
	 */
	public DesktopLayoutcol editData(Long id,
			Map<String, String> desktopColumnmap,
			Map<String, String> desktopLayoutmap) {
		DesktopLayoutcol desktopLayoutcol;
		List<DesktopColumn> desktopColumnList = desktopColumnService.getAll();
		for (DesktopColumn dc : desktopColumnList) {
			desktopColumnmap.put("" + dc.getId(), dc.getColumnName());
		}
		List<DesktopLayout> desktopLayoutList = desktopLayoutDao.getAll();
		
		for (DesktopLayout dc : desktopLayoutList) {
			desktopLayoutmap.put("" + dc.getId(), dc.getName());
		}

		if (id != 0) {
			desktopLayoutcol = dao.getById(id);
		} else {
			desktopLayoutcol = new DesktopLayoutcol();
		}
		return desktopLayoutcol;
	}
	
	/**
	 *保存系统布局的栏目设置 
	 * @param list
	 * @param layoutId
	 */
	public void saveCol(List<DesktopLayoutcol> list,Long layoutId){
		dao.delByLayoutId(layoutId);
		for(DesktopLayoutcol desktopLayoutcol:list){
			dao.add(desktopLayoutcol);
		}
	}
	
	/**
	 * 查看桌面显示数据
	 * @param id
	 * @param desktopColumnmap
	 * @param desktopLayoutmap
	 * @return
	 */
	public DesktopLayoutcol showData(Long id,
			Map<String, String> desktopColumnmap,
			Map<String, String> desktopLayoutmap) {
		List<DesktopColumn> desktopColumnList = desktopColumnService.getAll();
		for (DesktopColumn dc : desktopColumnList) {
			desktopColumnmap.put("" + dc.getId(), dc.getColumnName());
		}
		DesktopLayout desktopLayout = desktopLayoutDao.getById(id);
		desktopLayoutmap.put("cols", "" + desktopLayout.getCols());
		desktopLayoutmap.put("id", "" + desktopLayout.getId());
		desktopLayoutmap.put("widths",desktopLayout.getWidth());
		DesktopLayoutcol desktopLayoutcol = dao.getById(id);
		return desktopLayoutcol;
	}
	
	/**
	 * 获取布局列表数据
	 * @param layoutId
	 * @return
	 */
	public List<DesktopLayoutcol> layoutcolData(Long layoutId) {
		List<DesktopLayoutcol> list = dao.getByLayoutId(layoutId);
		for (DesktopLayoutcol dlc : list) {
			if (dlc.getColumnId() != 0L) {
				DesktopColumn desktopColumn=desktopColumnService.getById(dlc.getColumnId());
				dlc.setColumnName(desktopColumn.getColumnName());
				dlc.setColumnUrl(desktopColumn.getColumnUrl());
			}
		}
		return list;
	}
	
	public Map<String,Object> getLayoutData(Long layoutId,String ctxPath) throws Exception{
		//获取栏目布局
		DesktopLayout layout=desktopLayoutDao.getById(layoutId);
		List<DesktopLayoutcol>list=dao.getByLayoutId(layoutId);
		Map mapData = null;
		if(layout!=null){
			//获取栏目的宽度。
			String[] widths = layout.getWidth().split(",");			
			
			String[] columns = new String[layout.getCols()];
			Map map = new HashMap();
			Map widthMap = new HashMap();
			//获取到的列宽和列数不匹配时，以列数为准计算列宽（平均分配宽度）
			if(widths.length<layout.getCols()){
				int count=layout.getCols();
				double width=100/count;
				widths=new String[count];
				for(int x = 0; x < count; x++){
					widths[x]=String.format("%.2f", width);
				}
			}
			for (int i = 1; i <= layout.getCols(); i++) {
				columns[i - 1] = "" + i;
				widthMap.put("" + i, widths[i - 1]);
			}
			//遍历栏目。
			for (DesktopLayoutcol desktopLayoutcol : list) {
				Long columnId=desktopLayoutcol.getColumnId();
				DesktopColumn desktopColumn=desktopColumnService.getById(columnId);
				desktopColumn.setColumnHtml(getColumnHtml(columnId,ctxPath));
				//将解析后数据添加到map中。
				List<DesktopColumn> subList = (List) map.get(desktopLayoutcol.getCol().toString());
				if (subList == null) {
					subList = new ArrayList<DesktopColumn>();
					map.put(desktopLayoutcol.getCol().toString(), subList);
				}
				subList.add(desktopColumn);
			}
			mapData = new HashMap();
			mapData.put("layoutId", layout.getId());
			mapData.put("columns", columns);
			mapData.put("widthMap", widthMap);
			mapData.put("datas", map);
		}
		return mapData;
	}
	
	private String getColumnHtml(Long columnId,String ctxPath)throws Exception{
		DesktopColumn desktopColumn=desktopColumnService.getById(columnId);
		String html="";
		if(desktopColumn.getMethodType()==0){
			String handler=desktopColumn.getServiceMethod();
			//获取具体栏目的数据。
			Object model = desktopColumnService.getModelByHandler(handler);
			if(model!=null){
				Map map = new HashMap();
				map.put("model", model);// 栏目名称
				map.put("ctxPath", ctxPath);
				html = freemarkEngine.parseByStringTemplate(map, desktopColumn.getHtml());
			}
		}else{
			html=desktopColumn.getHtml();
		}
		return html;
	}
	
	
	/**
	 * 初始化默认布局的栏目设置
	 * @throws Exception
	 */
	public void init() throws Exception {
		DesktopLayout desk=desktopLayoutDao.getDefaultLayout();
		if (desk==null) return;
		String[] columnKeys={"getForAgent","getMyProcess","getMessage"};
		List<DesktopLayoutcol>list=dao.getByLayoutId(desk.getId());
		if(list.size()==0){
			DesktopLayoutcol layoutCol=null;
			int columns=desk.getCols();
			for(int i=0;i<columns;i++){
				layoutCol=new DesktopLayoutcol();
				layoutCol.setId(UniqueIdUtil.genId());
				layoutCol.setLayoutId(desk.getId());
				DesktopColumn column=desktopColumnService.getByTemplateId(columnKeys[i]);
				if(column!=null){
					layoutCol.setCol(i+1);
					layoutCol.setColumnId(column.getId());
					layoutCol.setSn(1);
					dao.add(layoutCol);
				}
			}
		}
	}
	
	/**
	 * 初始化我的主页，在服务器启动的时候调用
	 */
	public static void initDefaultDesk() {
		DesktopLayoutcolService desktopLayoutcolService=(DesktopLayoutcolService)AppUtil.getBean(DesktopLayoutcolService.class);
		try {
			desktopLayoutcolService.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<DesktopLayoutcol> getByLayoutIdAndColNum(Long id, int colNum) {
		
		return dao.getByLayoutIdAndColNum(id,colNum);
	}
	
}
