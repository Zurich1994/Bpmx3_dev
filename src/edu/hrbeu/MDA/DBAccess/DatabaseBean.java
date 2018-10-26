package edu.hrbeu.MDA.DBAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseBean {
	private DatabaseConfigure databaseConfigure = null;

    public Connection conn = null;

    public Statement stmt = null;

    private CallableStatement cstmt = null;

    public DatabaseBean(DatabaseConfigure databaseConfigure) {
        this.databaseConfigure = databaseConfigure;
    }

    /**
     * 获取当前连接
     * 
     * @return 返回当前连接connection
     */
    public Connection getConnection(){
        return this.conn;
    }
    
    /**
     * 连接数据库
     * 
     * @throws Exception 连接失败异常
     */
    public void getConnect()throws Exception{
        
        if(this.conn==null){
            try {
                Class.forName(databaseConfigure.getJdbcDriver());
            } catch (ClassNotFoundException e) {
                System.out.println(" class not found: " + e.getMessage());
                e.printStackTrace();
                throw new SQLException("-Database driver notFind ");
            }
    
            try {
                conn = DriverManager.getConnection(databaseConfigure
                        .getDatabaseURL(), databaseConfigure
                        .getDatabaseName(), databaseConfigure
                        .getDatebasePassword());
            } catch (SQLException sqlex) {
                System.err.println("DatabaseBean connection error"
                        + sqlex.getMessage());
                sqlex.printStackTrace();
                throw new SQLException("-Database connection error ");
            }
        }
    }
      
    public void disconnect() throws SQLException {
        try {
            if (stmt != null) {
                stmt.close();
                //注意调用close后，该变量不为null，如果再以stmt!=null为条件，将引发错误
                stmt=null;
            }

            if (conn != null) {
                conn.close();
                //注意调用close后，与数据库的连接返还给了数据库连接池，但该变量不为null，
                //如果再以判断conn!=null为条件，将引发错误
                conn=null;
            }
        } catch (SQLException e) {
            System.err.println("DatabaseBean disconnect error");
            e.printStackTrace();
            throw new SQLException("-Database disConnection error ");
        }
    }

    public boolean isConnected() {
        return (conn != null);
    }

    public ResultSet excutequery(String sql) throws SQLException {
        ResultSet rs1 = null;
        try {
            if (!isConnected())
                this.getConnect();
            if (this.stmt == null)
                stmt = conn.createStatement();
            rs1 = stmt.executeQuery(sql);
            return rs1;
        } catch (Exception e) {
            System.err.println("DatabaseBean query error" + e.getMessage());
            e.printStackTrace();
            throw new SQLException("-Query error ");
        }
    }

    public boolean execute(String sql) throws SQLException {
        try {
            if (!isConnected())
                this.getConnect();
            if (this.stmt == null)
                stmt = conn.createStatement();
            return stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("DatabaseBean execute error" + e.getMessage());
            e.printStackTrace();
            throw new SQLException("-Execute error ");
        }
    }

    public void statementClose() {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet callQueryProcedure(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            if (!isConnected())
                this.getConnect();
            if (this.cstmt == null)
                cstmt = conn.prepareCall(sql);
            cstmt.executeQuery();
        } catch (Exception e) {
            System.err.println("DatabaseBean callQueryProcedure error"
                    + e.getMessage());
            e.printStackTrace();
            throw new SQLException("-CallQueryProcedure error ");
        }
        return rs;
    }

    public boolean callExecuteProcedure(String sql) throws SQLException {
        try {
            if (!isConnected())
                this.getConnect();
            if (this.cstmt == null)
                cstmt = conn.prepareCall(sql);
            return cstmt.execute();
        } catch (Exception e) {
            System.err.println("DatabaseBean callExecuteProcedure error"
                    + e.getMessage());
            e.printStackTrace();
            throw new SQLException("-CallExecuteProcedure error ");
        }
    }

    public void executeBatch(ArrayList<String> sqlBatch) throws SQLException {
        try {
            if (!isConnected())
                this.getConnect();
            conn.setAutoCommit(false);
            if (this.stmt == null)
                stmt = conn.createStatement();
            for (int i = 0; i < sqlBatch.size(); i++) {
                stmt.addBatch((String) sqlBatch.get(i));
            }
            stmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println("DatabaseBean executeBatch error"
                    + e.getMessage());
            e.printStackTrace();
            throw new SQLException("-ExecuteBatch error ");
        }
    }

    public PreparedStatement createprepareStatement(String paramSQL)
            throws SQLException {
        try {
            if (!isConnected())
                this.getConnect();
            return conn.prepareStatement(paramSQL);

        } catch (Exception e) {
            System.err.println("DatabaseBean PreparedStatement error");
            e.printStackTrace();
            throw new SQLException("-DatabaseBean PreparedStatement error ");
        }
    }

    public void transactionStart() throws SQLException {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("DatabaseBean transactionStart");
            e.printStackTrace();
            throw new SQLException("-Transaction start error ");
        }
    }

    public void transactionEnd() throws SQLException {
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("DatabaseBean transactionEnd error");
            e.printStackTrace();
            throw new SQLException("-Transaction end error:");
        }
    }

    public void transactionRollback() throws SQLException {
        try {
            conn.rollback();
        } catch (SQLException e) {
            System.err.println("DatabaseBean transactionRollback error");
            e.printStackTrace();
            throw new SQLException("-Transaction rollback error ");
        }
    }

}
