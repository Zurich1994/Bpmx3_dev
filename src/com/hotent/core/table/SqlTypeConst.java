package com.hotent.core.table;

public class SqlTypeConst {
	public static final String ORACLE = "oracle";
	public static final String MYSQL = "mysql";
	public static final String SQLSERVER = "mssql";
	
///raise add by raise -B
	public static final String DB2 = "db2";
	public static final String DERBY = "derby";
	public static final String HBASE = "hbase";
	public static final String HIVE = "hive";
	public static final String H2 = "h2";
	public static final String JTDS = "jtds";
	public static final String MOCK = "mock";
	public static final String HSQL = "hsql";
	public static final String POSTGRESQL = "postgresql";
	public static final String SYBASE = "sybase";
	public static final String SQLITE="sqlite";
	public static final String MCKOI= "mckoi";
	public static final String CLOUDSCAPE="cloudscape";
	public static final String INFORMIX="informix";
	public static final String TIMESTEN="timesten";
	public static final String AS400="as400";
	public static final String SAPDB="sapdb";
	public static final String JSQLCONNECT="JSQLConnect";
	public static final String JTURBO="JTurbo";
	public static final String FIREBIRDSQL="firebirdsql";
	public static final String INTERBASE="interbase";
	public static final String POINTBASE= "pointbase";
	public static final String EDBC= "edbc";
	public static final String MIMER= "mimer";
	public static final String DM = "dm";
	private static final String INGRES = "ingres";
	/**
	 * 能过JDBC连接URL，取得数据库类型
	 * @param rawUrl
	 * @return
	 */
	public static String getDbType(String rawUrl) {
		if (rawUrl == null) {
			return null;
		}
		if (rawUrl.startsWith("jdbc:derby:")) {
			return DERBY;
		} else if (rawUrl.startsWith("jdbc:mysql:")) {
			return MYSQL;
		} else if (rawUrl.startsWith("jdbc:oracle:")) {
			return ORACLE;
		} else if (rawUrl.startsWith("jdbc:microsoft:")||rawUrl.startsWith("jdbc:sqlserver:")) {
			return SQLSERVER;
		} else if (rawUrl.startsWith("jdbc:sybase:Tds:")) {
			return SYBASE;
		} else if (rawUrl.startsWith("jdbc:jtds:")) {
			return JTDS;
		} else if (rawUrl.startsWith("jdbc:fake:") || rawUrl.startsWith("jdbc:mock:")) {
			return MOCK;
		} else if (rawUrl.startsWith("jdbc:postgresql:")) {
			return POSTGRESQL;
		} else if (rawUrl.startsWith("jdbc:hsqldb:")) {
			return HSQL;
		} else if (rawUrl.startsWith("jdbc:db2:")) {
			return DB2;
		} else if (rawUrl.startsWith("jdbc:sqlite:")) {
			return SQLITE;
		} else if (rawUrl.startsWith("jdbc:ingres:")) {
			return INGRES;
		} else if (rawUrl.startsWith("jdbc:h2:")) {
			return H2;
		} else if (rawUrl.startsWith("jdbc:mckoi:")) {
			return MCKOI;
		} else if (rawUrl.startsWith("jdbc:cloudscape:")) {
			return CLOUDSCAPE;
		} else if (rawUrl.startsWith("jdbc:informix-sqli:")) {
			return INFORMIX;
		} else if (rawUrl.startsWith("jdbc:timesten:")) {
			return TIMESTEN;
		} else if (rawUrl.startsWith("jdbc:as400:")) {
			return AS400;
		} else if (rawUrl.startsWith("jdbc:sapdb:")) {
			return SAPDB;
		} else if (rawUrl.startsWith("jdbc:JSQLConnect:")) {
			return JSQLCONNECT;
		} else if (rawUrl.startsWith("jdbc:JTurbo:")) {
			return JTURBO;
		} else if (rawUrl.startsWith("jdbc:firebirdsql:")) {
			return FIREBIRDSQL;
		} else if (rawUrl.startsWith("jdbc:interbase:")) {
			return INTERBASE;
		} else if (rawUrl.startsWith("jdbc:pointbase:")) {
			return POINTBASE;
		} else if (rawUrl.startsWith("jdbc:edbc:")) {
			return EDBC;
		} else if (rawUrl.startsWith("jdbc:mimer:multi1:")) {
			return MIMER;
		} else if (rawUrl.startsWith("jdbc:dm:")){
			return DM;
		}else {
			return null;
		}
	}
///raise add by raise -E
}
