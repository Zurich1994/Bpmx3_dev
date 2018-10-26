package com.hotent.core.bpm;

/**
 * 数据类型。
 * <pre> 
 * 构建组：x5-bpmx-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-6-5-上午10:52:19
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum DataType {
	/**字符串*/
	STRING("string","字符串"),
	/**数字*/
	NUMBER("number","数字"),
	/**日期*/
	DATE("date","日期");
	
	// 键
	private String key = "";
	// 值
	private String value = "";

	// 构造方法
	private DataType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	// =====getting and setting=====
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return key;
	}

	/**
	 * 通过key获取对象
	 * 
	 * @param key
	 * @return
	 */
	public static DataType fromKey(String key) {
		for (DataType c : DataType.values()) {
			if (c.getKey().equalsIgnoreCase(key))
				return c;
		}
		throw new IllegalArgumentException(key);
	}

}
