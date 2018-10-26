package edu.hrbeu.snmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * 
 * @author zl
 * @说明 数据获取类，根据指定的ip和OID获取监控指标值
 * 
 */
public class DataAcquisition {

	/**
	 * targetaAddress:目的主机地址 oid：监控oid snmp：snmp协议管理对象,用于发送和接受SNMP的协议数据单元
	 * CommunityTarget:target SNMP目标属性
	 */
	private Address targetAddress;
	private OID oid;
	private Snmp snmp;
	private CommunityTarget target;
	private String community;


	public Address getTargetAddress() {
		return targetAddress;
	}

	public void setTargetAddress(String IP, String port) {
		this.targetAddress = GenericAddress.parse("udp:" + IP +"/"+port);
	}

	public OID getOid() {
		return oid;
	}

	public void setOid(String oid) {

		this.oid = new OID(oid);
	}

	public Snmp getSnmp() {
		return snmp;
	}

	public void setSnmp(Snmp snmp) {
		this.snmp = snmp;
	}

	/**
	 * 初始化并开启监听
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException {

		TransportMapping<?> transport = new DefaultUdpTransportMapping();
		snmp = new Snmp(transport);
		transport.listen();
	}

	/**
	 * 发送SNMP的PDU
	 * 
	 * @param pdu
	 * @return
	 * @throws IOException
	 */
	public ResponseEvent sendPDU(PDU pdu) throws IOException {
		// 设置 target
		target = new CommunityTarget();
		target.setCommunity(new OctetString(community));
		target.setAddress(targetAddress);
		// 通信不成功时的重试次数
		target.setRetries(2);
		// 超时时间
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		// 向Agent发送PDU，并返回Response
		return snmp.send(pdu, target);
	}

	/**
	 * 发送并解析返回报文
	 * @return 结果列表
	 * 
	 * @throws IOException
	 */
	public List<String> getResultList() throws IOException {
		// get PDU
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(oid));
		pdu.setType(PDU.GET);
		return readResponse(sendPDU(pdu));
	}

	/**
	 * 解析返回报文,并返回结果列表
	 * @author zl
	 * @param respEvnt
	 * @return
	 */
	public List<String> readResponse(ResponseEvent respEvnt) {

		// 结果列表
		List<String> resultlist = new ArrayList<String>();
		if (respEvnt != null && respEvnt.getResponse() != null) {
			Vector<? extends VariableBinding> recVBs = respEvnt.getResponse()
					.getVariableBindings();
			// 若为空改用列表方式
			if (recVBs.elementAt(0).getVariable().equals(Null.instance)||recVBs.elementAt(0).getVariable().equals(Null.noSuchInstance)) {

				String[] oids = { oid.toString() };
				OID[] columns = new OID[oids.length];
				for (int i = 0; i < oids.length; i++)
					columns[i] = new OID(oids[i]);
				TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
					public PDU createPDU(Target arg0) {
						PDU request = new PDU();
						request.setType(PDU.GET);
						return request;
					}

					public PDU createPDU(MessageProcessingModel arg0) {
						// TODO Auto-generated method stub
						return null;
					}
				});
				List<TableEvent> list = tableUtils.getTable(target, columns,
						null, null);
				if (list.size() == 1 && list.get(0).getColumns() == null) {
					System.out.println(" null");
				} else {
					for (TableEvent event : list) {
						VariableBinding[] values = event.getColumns();
						if (values != null)
							resultlist.add(values[0].getVariable().toString());
					}
				}
			} else {
				for (int i = 0; i < recVBs.size(); i++) {
					VariableBinding recVB = recVBs.elementAt(i);
					resultlist.add(recVB.getVariable().toString());
				}
			}
		}
		return resultlist;
	}
	
	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCommunity() {
		return community;
	}

	public static void main(String[] args) {
		try {
			DataAcquisition dataAcquisition = new DataAcquisition();
			dataAcquisition.setTargetAddress("192.168.2.171","161");
			dataAcquisition.setCommunity("public");
			dataAcquisition.setOid(".1.3.6.1.2.1.25.4.2.1.2");
			dataAcquisition.init();
			List<String> list = dataAcquisition.getResultList();
			System.err.println(list);
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
