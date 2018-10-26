package com.hotent.platform.model.share;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:数据功能权限分享 Model对象
 */
public class SysShareRights extends BaseModel
{
	private static final long serialVersionUID = 1L;
	public static final short YES = 1;
	public static final short NO = 0;
	
	protected Long id;
	/**
	 *名称
	 */
	protected String  name;
	/**
	 *共享类型
	 */
	protected String  shareItem;
	/**
	 *是否启用
	 */
	protected short  enable;
	/**
	 *是否同步
	 */
	protected short  sync;
	/**
	 *是否全部
	 */
	protected short  isAll;
	/**
	 *关联数据的ID
	 */
	protected Long  pkid;
	/**
	 * 被共享的原
	 */
	protected String  source;
	/**
	 *共享的目标
	 */
	protected String  target;
	/**
	 *共享条目
	 */
	protected String  shares;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setEnable(short enable) 
	{
		this.enable = enable;
	}
	/**
	 * 返回 是否启用
	 * @return
	 */
	public short getEnable() 
	{
		return this.enable;
	}
	public void setSync(short sync) 
	{
		this.sync = sync;
	}
	/**
	 * 返回 是否同步
	 * @return
	 */
	public short getSync() 
	{
		return this.sync;
	}
	public void setIsAll(short isAll) 
	{
		this.isAll = isAll;
	}
	/**
	 * 返回 是否全部
	 * @return
	 */
	public short getIsAll() 
	{
		return this.isAll;
	}
	public void setPkid(Long pkid) 
	{
		this.pkid = pkid;
	}
	/**
	 * 返回 关联数据的ID
	 * @return
	 */
	public Long getPkid() 
	{
		return this.pkid;
	}
	public void setSource(String source) 
	{
		this.source = source;
	}
	/**
	 * 返回  被共享的原
	 * @return
	 */
	public String getSource() 
	{
		return this.source;
	}
	public void setTarget(String target) 
	{
		this.target = target;
	}
	/**
	 * 返回 共享的目标
	 * @return
	 */
	public String getTarget() 
	{
		return this.target;
	}
	public void setShares(String shares) 
	{
		this.shares = shares;
	}
	/**
	 * 返回 共享条目
	 * @return
	 */
	public String getShares() 
	{
		return this.shares;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysShareRights)) 
		{
			return false;
		}
		SysShareRights rhs = (SysShareRights) object;
		return new EqualsBuilder()
		.append(this.id,rhs.id)
		.append(this.name, rhs.name)
		.append(this.enable, rhs.enable)
		.append(this.sync, rhs.sync)
		.append(this.isAll, rhs.isAll)
		.append(this.pkid, rhs.pkid)
		.append(this.source, rhs.source)
		.append(this.target, rhs.target)
		.append(this.shares, rhs.shares)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.name) 
		.append(this.enable) 
		.append(this.sync) 
		.append(this.isAll) 
		.append(this.pkid) 
		.append(this.source) 
		.append(this.target) 
		.append(this.shares) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("name", this.name) 
		.append("enable", this.enable) 
		.append("sync", this.sync) 
		.append("isAll", this.isAll) 
		.append("pkid", this.pkid) 
		.append("source", this.source) 
		.append("target", this.target) 
		.append("shares", this.shares) 
		.toString();
	}
	public String getShareItem() {
		return shareItem;
	}
	public void setShareItem(String shareItem) {
		this.shareItem = shareItem;
	}

}