package edu.hrbeu.MDA.DBAccess.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 数据库操作基类。<br>
 * 包含基本的操作：增，查，改，删，列表，分页操作。<br>
 * <b>命名规范:</b><br>
 * 1.<a name="Mapper键值ID">mybatis Mapper文件中的键值ID</a>,如getRole。<br>
 * 2.<a name="完整路径">完整路径</a>,如com.hotent.platform.model.Role.getRole。<br>
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class GenericDao<E, PK extends Serializable> extends BaseMyBatisDao implements IEntityDao<E, PK>
{
	/**
	 * 排序类型
	 * 
	 */
	enum SortBy{
		ASC,//升序
		DESC//降序
	};
	@Resource
	protected JdbcTemplate jdbcTemplate;
	
	
	@Resource
	Properties configproperties;
	
	/**
	 * 获取当前dao 需要访问的model类名，在实现类中实现这个方法。<br>
	 * 主要的功能：用于获取类的完整路径，以提供给mybatis使用。
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract Class getEntityClass();
	
	/**
	 * 获取当前数据库类型。
	 * @return
	 */
	protected String getDbType(){
		return configproperties.getProperty("jdbc.dbType");
	}
	
	

	
	/**
	 * 根据主键取得对象。
	 */
	public E getById(PK primaryKey)
	{
		String getStatement= getIbatisMapperNamespace() + ".getById";
		E object = (E)getSqlSessionTemplate().selectOne(getStatement, primaryKey);
		
		return object;
	}
	/**
	 * 返回一条数据对象。
	 * @param sqlKey  <a href="#Mapper键值ID">Mapper键值ID</a>。
	 * @param params 参数为单个对象或Map类型 
	 * @return
	 */
	public E getUnique(String sqlKey,Object params)
	{
		String getStatement= getIbatisMapperNamespace() + "." + sqlKey;
		E object = (E)getSqlSessionTemplate().selectOne(getStatement,params);
		return object;
	}
	
	
	
	/**
	 * 返回单条数据，如 select count(*) from table_a 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public Object getOne(String sqlKey,Object params)
	{
		String statement=getIbatisMapperNamespace() + "." + sqlKey;
		Object object = getSqlSessionTemplate().selectOne(statement,params);
		return object;
	}
	
	/**
	 * 按SqlKey获取分页的数据。<br>
	 * @param sqlKey   <a href="#Mapper键值ID">Mapper键值ID</a>。
	 * @param params 参数  参数为单个对象或Map类型 ,若要排序，请在该参数Map里加上两参数key为orderField与orderSeq的值对。
	 * @param pb  分页对象 {@link PageBean pageBean}
	 * @return
	 */
	public List getBySqlKey(String sqlKey,Object params,PageBean pageBean)
	{
		String statement= getIbatisMapperNamespace() + "." + sqlKey;
		
		List list=getList(statement, params, pageBean);
		
		return list;
	}
	/**
	 * 按Query Filter进行数据过滤查询，并且可返回对应的页面中的分页结果
	 * @param sqlKey   <a href="#Mapper键值ID">Mapper键值ID</a>
	 * @param queryFilter {@link QueryFilter QueryFilter}
	 * @return
	 */
	public List getBySqlKey(String sqlKey,QueryFilter queryFilter)
	{
		List list= new ArrayList();
		if(queryFilter.getPageBean()==null){
			list=getBySqlKey(sqlKey,queryFilter.getFilters());
		}
		else{
			list=getBySqlKey(sqlKey,queryFilter.getFilters(),queryFilter.getPageBean());
		}
		
		//设置回请求对象
		queryFilter.setForWeb();
		return list;
	}
	
	/**
	 * 按Map.xml中的select指定的id获取数据列表
	 * @param sqlKey  <a href="#Mapper键值ID">Mapper键值ID</a>
	 * @param params 参数类型 可为Object类型，也可为Map类型,若要排序，请在该参数Map里加上两参数key为orderField与orderSeq的值对。
	 * @return
	 */
	public List getBySqlKey(String sqlKey,Object params)
	{
 		String statement= getIbatisMapperNamespace() + "." + sqlKey;
		
		List list=getSqlSessionTemplate().selectList(statement,params);
		
		return list;
	}
	
	/**
	 * 取得列表类型返回不使用范型。
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	@Deprecated
	public List getListBySqlKey(String sqlKey,Object params)
	{
		String statement= getIbatisMapperNamespace() + "." + sqlKey;
		
		List list=getSqlSessionTemplate().selectList(statement,params);
		
		return list;
	}
	
	/**
	 * 按Map.xml中的select指定的id获取数据列表。
	 * 没有参数。
	 * @param sqlKey <a href="#Mapper键值ID">Mapper键值ID</a>
	 * @return
	 */
	public List<E> getBySqlKey(String sqlKey)
	{
		String statement= getIbatisMapperNamespace() + "." + sqlKey;
		
		List<E> list=getSqlSessionTemplate().selectList(statement);
		
		return list;
	}
	
	
	
	
	/**
	 * 根据主键ID删除记录。
	 */
	public int delById(PK id)
	{
		String delStatement=getIbatisMapperNamespace() + ".delById";
		int affectCount = getSqlSessionTemplate().delete(delStatement, id);
		return affectCount;
	}
	
	/**
	 * 按Map.xml中的delete指定的id删除数据
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public int delBySqlKey(String sqlKey,Object params)
	{
		String delStatement=getIbatisMapperNamespace() + "."+ sqlKey;
		int affectCount = getSqlSessionTemplate().delete(delStatement, params);
		return affectCount;
	}

	/**
	 * 增加对象。
	 */
	public void add(E entity)
	{
		String addStatement=getIbatisMapperNamespace() + ".add";
		getSqlSessionTemplate().insert(addStatement, entity);
	}
	
	/**
	 * 更新对象。
	 * @return 返回更新的记录数
	 */
	public int update(E entity)
	{
		String updStatement=getIbatisMapperNamespace() + ".update";
		int affectCount = getSqlSessionTemplate().update(updStatement, entity);
		return affectCount;
	}
	
	/**
	 * 执行更新语句
	 * @param sqlKey 按Map.xml中的update指定的id
	 * @param params 传入的参数 ,一般为map,object
	 * @return 返回更新的语句
	 */
	public int update(String sqlKey,Object params)
	{
		String updStatement=getIbatisMapperNamespace() + "."+ sqlKey;
		int affectCount = getSqlSessionTemplate().update(updStatement, params);
		return affectCount;
	}


	/**
	 * 根据出入的POJO对象获取ibatis的命名空间。<br>
	 * 返回如：com.hotent.platform.model.role.Role
	 * @return
	 */
	public String getIbatisMapperNamespace()
	{
		return getEntityClass().getName();
	}

	/**
	 * 根据mybatis映射文件的ID获取分页数据。
	 * @param statementName <a href="#完整路径">完整路径</a>
	 * @param 过滤参数
	 * 
	 */
	public List getList(String statementName,Object params,PageBean pageBean)
	{
		
		if(pageBean==null) return getList(statementName, params);
		
		Map filters = new HashMap();
		
		if(params!=null){
			if (params instanceof Map){
				filters.putAll((Map) params);
			}
			else{
				Map parameterObject = BeanUtils.describe(params);
				filters.putAll(parameterObject);
			}
		}
		if(pageBean.isShowTotal()){
			//获取SQL语句
			IbatisSql ibatisSql=this.getIbatisSql(statementName, filters);
			
			log.info(ibatisSql.getSql());
			//取得返回数量
			int totalCount=jdbcTemplate.queryForInt(ibatisSql.getCountSql(),ibatisSql.getParameters());
			
			pageBean.setTotalCount(totalCount);
		}
		RowBounds rowBounds=new RowBounds(pageBean.getFirst(),pageBean.getPageSize());
		
		List list = getSqlSessionTemplate().selectList(statementName, filters, rowBounds);
		return list;
	}


	/**
	 * 取得列表数据。
	 * @param statementName <a href="#完整路径">完整路径</a>
	 * @param params 参数可以是map 或者 pojo对象
	 * @return
	 */
	public List getList(String statementName,Object params)
	{
		
		Map filters = new HashMap();
		if(params!=null){
			if (params instanceof Map){
				filters.putAll((Map) params);
			}
			else{
				Map parameterObject = BeanUtils.describe(params);
				filters.putAll(parameterObject);
			}
		}
		//获取SQL语句,下面两句pkq加的，为了查看sql语句
		if(log.isDebugEnabled()){
			IbatisSql ibatisSql=this.getIbatisSql(statementName, filters);				
			log.debug(ibatisSql.getSql());
		}
		List list = getSqlSessionTemplate().selectList(statementName, filters);
		return list;
	}
	
	

	
	/**
	 * 根据QueryFilter 获取列表数据。<br>
	 * 根据QueryFilter决定是否进行分页。
	 * @param statementName <a href="#完整路径">完整路径</a>
	 * @param queryFilter
	 * @return
	 */
	public List getList(String  statementName,QueryFilter queryFilter)
	{
		List list=null;
		PageBean pageBean=queryFilter.getPageBean();
		Object filters=queryFilter.getFilters();
		if(pageBean!=null)
			list=getList(statementName, queryFilter.getFilters(),pageBean);
		else
			list=getList(statementName, filters);
		
		return list;
	}
	
	

	
	/**
	 * 获取所有的记录,不分页。
	 */
	public List<E> getAll()
	{
		String statementName = getIbatisMapperNamespace() + ".getAll";
		return getSqlSessionTemplate().selectList(statementName, null);
	}
	
	
	
	/**
	 * 根据QueryFilter查询记录列表。<br>
	 * 根据QueryFilter决定是否进行分页。
	 * @param queryFilter
	 * @return
	 */
	public List<E> getAll(QueryFilter queryFilter)
	{
		String statementName = getIbatisMapperNamespace() + ".getAll";
		List<E> list= getList(statementName,queryFilter);
		//设置回请求对象
		queryFilter.setForWeb();
		return list;
	}
	
	/**
	 * 执行新增语句
	 * 
	 * @param sqlKey
	 *            按Map.xml中的insert指定的id
	 * @param params
	 *            传入的参数 ,一般为map,object
	 * @return 返回新增的语句
	 */
	public int insert(String sqlKey, Object params) {
		String updStatement = getIbatisMapperNamespace() + "." + sqlKey;
		int affectCount = getSqlSessionTemplate().insert(updStatement, params);
		return affectCount;
	}
	


	
}
