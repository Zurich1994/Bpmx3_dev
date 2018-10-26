package edu.hrbeu.MDA.DBAccess;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;

import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;
/**
 * 操作数据库
 * @author 王乙闲
 *
 */

public class DataResourceImp2 {

	DatabaseConfigure databaseConfigure = DatabaseConfigure.getInstance();
	DatabaseBean databaseBean = new DatabaseBean(databaseConfigure);
	//子流程id
	String defID = null;
	//用户节点绑定的表单
	List<String> formKey = new ArrayList<String>();
	//表单信息
	String formdefid =null;
	//事件按钮相关（事件按钮F_eventID,事件发生概率F_probability,事件绑定的事物图F_defbID）
	List<String> eventInfo = new ArrayList<String>();
	
	/**
	 * 根据subDefKey得到子流程id
	 * @param subDefKey
	 * @return defID
	 */
	public String getDefId(String subDefKey) {
		// TODO Auto-generated method stub
		try {
			databaseBean.getConnect();
			String sql="SELECT bpm_definition.DEFID FROM bpm_definition " +
						"WHERE bpm_definition.DEFKEY=?" +
								"AND bpm_definition.VERSIONNO=" +
									"(select MAX(bpm_definition.VERSIONNO) " +
										"FROM bpm_definition where bpm_definition.DEFKEY=?)";
			PreparedStatement ps = databaseBean.createprepareStatement(sql);
			ps.setString(1, subDefKey);
			ps.setString(2, subDefKey);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				defID = rs.getString("DEFID");
			}
			databaseBean.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defID;
	}
	
	/**
	 * 根据用户节点的节点id查找表单信息FORMKEY
	 * @param defid
	 * @return formKey
	 */
	public List<String> getFormKey(Long defid) {
		// TODO Auto-generated method stub
		try {
			databaseBean.getConnect();
			String sql="select distinct FORMKEY from bpm_node_set where DEFID=?";
			PreparedStatement ps = databaseBean.createprepareStatement(sql);
			ps.setLong(1, defid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String temp= rs.getString("FORMKEY");
				formKey.add(temp);
			}
			databaseBean.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formKey;
	}
	
	/**
	 * 根据formKey获取表单信息
	 * @param formKey
	 * @return
	 */
	public String getForms(String formKey) {
		// TODO Auto-generated method stub
		try {
			databaseBean.getConnect();
			String sql="select * from bpm_form_def where FORMKEY = ?";
			PreparedStatement ps = databaseBean.createprepareStatement(sql);
			ps.setString(1, formKey);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				formdefid = rs.getString("FORMDEFID");
			}
			databaseBean.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formdefid;
	}
	
	/**
	 * 根据流程id和节点id查找事件按钮相关（事件按钮F_eventID,事件发生概率F_probability,事件绑定的事物图F_defbID）
	 * @param defid nodeid
	 * @return ArrayList<Eventgraphrelation>
	 */
	public ArrayList<Eventgraphrelation> getEventInfo(Long defid,String nodeid) {
		ArrayList<Eventgraphrelation> result =new ArrayList<Eventgraphrelation>();
		try {
			databaseBean.getConnect();
			String sql="select F_defbID,F_eventID,F_probability from w_eventgraphrelation " +
					"where F_defID=? and F_nodeID = ?";
			PreparedStatement ps = databaseBean.createprepareStatement(sql);
			ps.setLong(1, defid);
			ps.setString(2, nodeid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Eventgraphrelation eventgraphrelation = new Eventgraphrelation();
				eventgraphrelation.setDefbID(rs.getString("F_defbID"));
				eventgraphrelation.setEventID(rs.getString("F_eventID"));
				eventgraphrelation.setProbability(rs.getString("F_probability"));
		 		result.add(eventgraphrelation);
				
			}
			databaseBean.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
