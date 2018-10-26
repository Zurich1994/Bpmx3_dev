package edu.hrbeu.MDA.DBAccess;


import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;
/**
 * 操作数据库的dao类
 * @author 张静
 *
 */

public class DataResourceImp {

	DatabaseConfigure databaseConfigure = DatabaseConfigure.getInstance();
	DatabaseBean databaseBean = new DatabaseBean(databaseConfigure);
	//存储defXml
	String defXml = null;
	//存储actDeployId
	Long actDeployId = null;
	/**
	 * 根据defId得到 ACTDEPLOYID
	 * @param defId
	 * @return
	 */
	public Long getDeployId(Long defId) {
		// TODO Auto-generated method stub
		try {
			databaseBean.getConnect();
			String sql = "SELECT ACTDEPLOYID FROM BPM_DEFINITION WHERE DEFID = ?";
			PreparedStatement ps = databaseBean.createprepareStatement(sql);
			ps.setLong(1, defId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				actDeployId = rs.getLong("ACTDEPLOYID");
			}
			databaseBean.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actDeployId;
	}
	/**
	 * 根据BPM_DEFINITION表中的ACTDEPLOYID得到格式化后的defXml
	 * @param deployId
	 * @return
	 */
	public String getDefXmlByDeployId(Long defId){
		try {
			databaseBean.getConnect();
			String sql = "SELECT ACTDEPLOYID FROM BPM_DEFINITION WHERE DEFID = ?";
			PreparedStatement ps = databaseBean.createprepareStatement(sql);
			ps.setLong(1, defId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				actDeployId = rs.getLong("ACTDEPLOYID");
			}
//			databaseBean.disconnect();
			sql = "select a.* from ACT_GE_BYTEARRAY a where NAME_ LIKE '%bpmn20.xml' and DEPLOYMENT_ID_= ? ";
			final LobHandler lobHandler = new DefaultLobHandler(); // reusable
			final ByteArrayOutputStream contentOs = new ByteArrayOutputStream();
//			databaseBean.getConnect();
			ps = databaseBean.createprepareStatement(sql);
			ps.setString(1, actDeployId.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs, "BYTES_"), contentOs);
			}
			defXml = new String(contentOs.toByteArray(), "UTF-8");
			databaseBean.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defXml;
	}


}
