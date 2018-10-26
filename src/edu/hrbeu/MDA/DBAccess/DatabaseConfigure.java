package edu.hrbeu.MDA.DBAccess;

public class DatabaseConfigure {
	
	private static DatabaseConfigure instance = null;

	private String databaseName = "user10";
	private String datebasePassword = "123";
	private String databaseURL = "jdbc:mysql://192.168.4.157/wtestbpmx33?characterEncoding=utf8";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	/**
	 * 构造函数
	 */
	private DatabaseConfigure() {
	}

	/**
	 * 单例函数
	 * 
	 * @return 一个实例
	 */
	public static DatabaseConfigure getInstance() {
		if (instance == null) {
			instance = new DatabaseConfigure();
		}
		return instance;
	}

	public String getDatabaseName() {
		return this.databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatebasePassword() {
		return this.datebasePassword;
	}

	public void setDatebasePassword(String datebasePassword) {
		this.datebasePassword = datebasePassword;
	}

	public String getDatabaseURL() {
		return this.databaseURL;
	}

	public void setDatabaseURL(String databaseURL) {
		this.databaseURL = databaseURL;
	}

	public String getJdbcDriver() {
		return this.jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	
}
