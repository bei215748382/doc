package com.mlnx.doc.video;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mlnx.doc.video.ConsultationRequest.Statu;
import com.mlnx.doc.video.Patient.Sex;
import com.mlnx.doc.video.User.UserStatu;


public class MedicalServer {
	
	private MedicalServer server;
	private Doctor user;
	
	private static final Logger log = LoggerFactory
			.getLogger(MedicalServer.class);
	
	public enum LoginResult {
		SUCCESS, ID_ERROR, PASS_ERROR, NET_ERROR, IDORPASS_ERROR, SYS_ERROR, SYS_BUSY
	}

	private String ip;
	private int port;

	public MedicalServer(){
		String ip = "112.4.17.103";
		int port = 8009;
		if(server==null){
			server = new MedicalServer(ip,port);
		}
		String name = "wxl";
		String password = "12345";
		user = new Doctor(name,password);
		log.info("video server started, 造影服务启动:"+server.userLogin(user));
	}
	
	public MedicalServer(String ip, int port) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
		this.port = port;
	}

	public LoginResult userLogin(User user) {
		String url = "http://" + ip + ":" + port
				+ "/WSForMedicalApp.asmx/LoginCheck";
		// Log.d("debug", "user login:url->" + url);

		HttpClient httpclient = new DefaultHttpClient();
		// 创建POST连接
		HttpPost httpPost = new HttpPost(url);
		// 去掉post请求时请求头与请求体分开发送的功能
		httpPost.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Language", "zh-CN");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("DNT", "1");
		httpPost.addHeader("Cache-Control", "no-cache");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", user.getID()));
		params.add(new BasicNameValuePair("userPwd", user.getPass()));
 
		try {
			// //使用PSOT方式，必须用NameValuePair数组传递参数
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httpPost);
			// Log.d("debug", "response is :"
			// + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				return LoginResult.SYS_BUSY;

			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();

			byte[] buff = new byte[(int) httpEntity.getContentLength()];
			// Log.d("debug","encode->"+httpEntity.getContentEncoding());

			in.read(buff);
			String resStr = new String(buff);
			// Log.d("debug","login resStr "+resStr);
			if (resStr.indexOf("true") > 0)
				return LoginResult.SUCCESS;
			else
				return LoginResult.IDORPASS_ERROR;

		} catch (Exception e) {
			e.printStackTrace();
			return LoginResult.NET_ERROR;
		}

	}

	// 取会诊请求信息
	public ArrayList<ConsultationRequest> getConsultationRequest(Doctor doctor,
			String startTime, String endTime, int pageSize, int page) {

		if (doctor.getStatu() == UserStatu.OUTLINE)
			return null;

		String url = "http://" + ip + ":" + port
				+ "/WSForMedicalApp.asmx/GetMedicalApplyList";
		HttpClient httpclient = new DefaultHttpClient();
		// 创建POST连接
		HttpPost httpPost = new HttpPost(url);
		httpPost.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Language", "zh-CN");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("DNT", "1");
		httpPost.addHeader("Cache-Control", "no-cache");

		String selectString = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"
				+ "<ParmRoot>\r\n" + "<startTime>" + startTime
				+ "</startTime>\r\n" + "<endTime>" + endTime + "</endTime>\r\n"
				+ "<pageSize>" + pageSize + "</pageSize>\r\n"
				+ "<pageIndex>2</pageIndex>\r\n" + "</ParmRoot>";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("xmlParm", selectString));

		try {
			// //使用POST方式，必须用NameValuePair数组传递参数
			// HttpEntity entity = new StringEntity(selectString, "UTF-8");
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				return null;

			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			int xmlLen = (int) httpEntity.getContentLength();
			byte[] buff = new byte[xmlLen];
			int readLen = 0;
			while (readLen < xmlLen)
				readLen += in.read(buff, readLen, xmlLen - readLen);
			String xmlStr = new String(buff, 0, xmlLen);

			xmlStr = xmlStr.substring(xmlStr.indexOf("&lt;?"), xmlStr.length()
					- "</string>".length());

			// Log.d("debug", "readLen:" + readRes + " xmlStr:" +
			// xmlStr.length()
			// + " selectRes:" + xmlStr);
			return domParseConsultationRequest(xmlStr);// 解析会诊请求数据
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 取病人信息
	public ArrayList<Patient> getPatientList(Doctor doctor, String startTime,
			String endTime) {

		if (doctor.getStatu() == UserStatu.OUTLINE)
			return null;

		String url = "http://" + ip + ":" + port
				+ "/WSForMedicalApp.asmx/GetHealthRecordsList";

		HttpClient httpclient = new DefaultHttpClient();
		// 创建POST连接
		HttpPost httpPost = new HttpPost(url);
		httpPost.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Language", "zh-CN");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("DNT", "1");
		httpPost.addHeader("Cache-Control", "no-cache");

		String selectString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<ParmRoot>" + "<startTime>" + startTime + "</startTime>"
				+ "<endTime>" + endTime + "</endTime>" + "</ParmRoot>";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("xmlParm", selectString));

		try {
			// //使用POST方式，必须用NameValuePair数组传递参数
			// HttpEntity entity = new StringEntity(selectString, "UTF-8");
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpPost);
			System.out.println("response is :"
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				return null;

			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			System.out.println(
					"httpEntity.getContentLength():"
							+ httpEntity.getContentLength());

			int xmlLen = (int) httpEntity.getContentLength();
			byte[] buff = new byte[xmlLen];

			int readLen = 0;
			while (readLen < xmlLen)
				readLen += in.read(buff, readLen, xmlLen - readLen);
			String xmlStr = new String(buff, 0, xmlLen);
			xmlStr = xmlStr.substring(xmlStr.indexOf("&lt;?"), xmlStr.length()
					- "</string>".length());
			// Log.d("debug", "xmlStr:" + xmlStr);
			return domParsePatientList(xmlStr);// 解析病人数据
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 取会场信息
	public ArrayList<MeetingRoom> getMeetingRoomList(Doctor doctor,
			String matchName) {
		if (doctor == null || doctor.getStatu() == UserStatu.OUTLINE)
			return null;

		String url = "http://" + ip + ":" + port
				+ "/WSForMedicalApp.asmx/GetDeviceList";

		HttpClient httpclient = new DefaultHttpClient();
		// 创建POST连接
		HttpPost httpPost = new HttpPost(url);
		httpPost.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Language", "zh-CN");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("DNT", "1");
		httpPost.addHeader("Cache-Control", "no-cache");

		String selectString = "<?xml version='1.0' encoding='utf-8'?><ParmRoot><MeetingPlaceName>"
				+ matchName + "</MeetingPlaceName></ParmRoot>";
		// Log.d("debug", "------=--------->" + selectString);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("xmlParm", selectString));

		try {
			// //使用POST方式，必须用NameValuePair数组传递参数
			// HttpEntity entity = new StringEntity(selectString, "UTF-8");
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				return null;

			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			// Log.d("debug",
			// "httpEntity.getContentLength():"
			// + httpEntity.getContentLength());
			int xmlLen = (int) httpEntity.getContentLength();
			byte[] buff = new byte[xmlLen];
			int readLen = 0;
			while (readLen < xmlLen)
				readLen += in.read(buff, readLen, xmlLen - readLen);
			String xmlStr = new String(buff, 0, xmlLen);
			System.out.println("xmlStr:" + xmlStr);
			xmlStr = xmlStr.substring(xmlStr.indexOf("&lt;?"), xmlStr.length()
					- "</string>".length());

			return domParseMeetingRoomList(xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 解析诊请求信息
	private ArrayList<ConsultationRequest> domParseConsultationRequest(
			String xml) {
		if (xml == null)
			return null;
		xml = xml.replace("&lt;", "<");
		xml = xml.replace("&gt;", ">");
		xml = xml.replace("&amp;", "&");
		xml = xml.replace("&apos;", "'");
		xml = xml.replace("&quot;", "\"");
		System.out.println("xml->" + xml);
		ArrayList<ConsultationRequest> listResult = new ArrayList<ConsultationRequest>();

		// 得到 DocumentBuilderFactory 对象, 由该对象可以得到 DocumentBuilder 对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 得到DocumentBuilder对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 得到代表整个xml的Document对象
			Document document = builder.parse(new ByteArrayInputStream(xml
					.getBytes()));
			// 得到 "根节点"
			Element root = document.getDocumentElement();
			// 获取根节点的所有items的节点
			NodeList items = root.getElementsByTagName("Row");
			// 遍历所有节点
			for (int i = 0; i < items.getLength(); i++) {
				ConsultationRequest conReq = new ConsultationRequest();
				Patient patient = new Patient();
				User requster = new User();
				Hospital hospital = new Hospital();
				conReq.setPatient(patient);
				conReq.setRequester(requster);
				conReq.setHospital(hospital);
				Element item = (Element) items.item(i);
				NodeList nodes = item.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node node = nodes.item(j);
					String nodeName = node.getNodeName();
					String value = node.getTextContent();
					if (value != null) {
						if (nodeName.equals("RequestHospitalID")) {
							if (value.length() > 0)
								hospital.setID(Integer.parseInt(value));
							else
								hospital.setID(-1);
						} else if (nodeName.equals("RequestHospitalName")) {
							hospital.setName(value);
						} else if (nodeName.equals("YuYueApplyID")) {
							if (value.length() > 0)
								conReq.setID(Integer.valueOf(value));
							else
								conReq.setID(-1);
						} else if (nodeName.equals("HZType")) {
							conReq.setType(value);
						} else if (nodeName.equals("QiWangData")) {
							conReq.setTime(value);
						} else if (nodeName.equals("ApplyName")) {
							requster.setName(value);
						} else if (nodeName.equals("SickName")) {
							patient.setName(value);
						} else if (nodeName.equals("SickSex")) {
							if ("男".equals(value))
								patient.setSex(Sex.MAN);
							else
								patient.setSex(Sex.WOMAN);
						} else if (nodeName.equals("SickAge")) {
							if (value.length() > 0)
								patient.setAge(Integer.valueOf(value));
							else
								patient.setAge(-1);
						} else if (nodeName.equals("State")) {
							if (value.length() > 0)
								conReq.setStatu(Statu.parse(value));
							else
								conReq.setStatu(Statu.UNKNOWN);
						} else if (nodeName.equals("SickID")) {
							if (value.length() > 0)
								patient.setID(Integer.valueOf(value));
							else
								patient.setID(-1);
						}
					}
				}
				listResult.add(conReq);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	// 解析病人信息
	private ArrayList<Patient> domParsePatientList(String xml) {
		if (xml == null)
			return null;
		xml = xml.replace("&lt;", "<");
		xml = xml.replace("&gt;", ">");
		xml = xml.replace("&amp;", "&");
		xml = xml.replace("&apos;", "'");
		xml = xml.replace("&quot;", "\"");
		// Log.d("debug", "xml->" + xml);
		ArrayList<Patient> listResult = new ArrayList<Patient>();

		// 得到 DocumentBuilderFactory 对象, 由该对象可以得到 DocumentBuilder 对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 得到DocumentBuilder对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 得到代表整个xml的Document对象
			Document document = builder.parse(new ByteArrayInputStream(xml
					.getBytes()));
			// 得到 "根节点"
			Element root = document.getDocumentElement();
			// 获取根节点的所有items的节点
			NodeList items = root.getElementsByTagName("Row");
			// 遍历所有节点
			for (int i = 0; i < items.getLength(); i++) {
				Patient patient = new Patient();// 创建一个病人对像
				Element item = (Element) items.item(i);
				NodeList nodes = item.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node node = nodes.item(j);
					String nodeName = node.getNodeName();
					String value = node.getTextContent();
					System.out.println("deubg"+nodeName + "---->" + value);
					if (value != null) {
						if (nodeName.equals("SickID")) {
							patient.setID(Integer.parseInt(value));
						} else if (nodeName.equals("SickName")) {
							patient.setName(value);
						} else if (nodeName.equals("SickAge")) {
							patient.setAge(Integer.parseInt(value));
						} else if (nodeName.equals("SickNumber")) {
							patient.setNumber(value);
						} else if (nodeName.equals("SickTele")) {
							patient.setPhoneNum(value);
						} else if (nodeName.equals("SickReason")) {
							patient.setDescriptor(value);
						}
					}
				}
				listResult.add(patient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	// 取摄像机信息
	public ArrayList<IpCamera> getCamera(User user, int ID) {

		if (user.getStatu() == UserStatu.OUTLINE)
			return null;

		String url = "http://" + ip + ":" + port
				+ "/WSForMedicalApp.asmx/GetDeviceInfo";

		HttpClient httpclient = new DefaultHttpClient();
		// 创建POST连接
		HttpPost httpPost = new HttpPost(url);
		httpPost.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Language", "zh-CN");
		httpPost.addHeader("Accept-Encoding", "gzip, deflate");
		httpPost.addHeader("DNT", "1");
		httpPost.addHeader("Cache-Control", "no-cache");

		String selectString = "<?xml version='1.0' encoding='utf-8'?><ParmRoot><deviceID>"
				+ ID + "</deviceID></ParmRoot>";
		// Log.d("debug","------------------------------->"+selectString);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("xmlParm", selectString));

		try {
			// //使用POST方式，必须用NameValuePair数组传递参数
			// HttpEntity entity = new StringEntity(selectString, "UTF-8");
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				return null;

			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			int xmlLen = (int) httpEntity.getContentLength();
			byte[] buff = new byte[xmlLen];
			int readLen = 0;
			while (readLen < xmlLen)
				readLen += in.read(buff, readLen, xmlLen - readLen);
			String xmlStr = new String(buff, 0, xmlLen);
			System.out.println("xmlStr:" + xmlStr);
			xmlStr = xmlStr.substring(xmlStr.indexOf("&lt;?"), xmlStr.length()
					- "</string>".length());
			// Log.d("debug", "readLen:" + readRes + " xmlStr:" +
			// xmlStr.length()
			// + " selectRes:" + xmlStr);
			return domParseCameraList(xmlStr);// 解析会诊请求数据

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 解析摄像机信息
	private ArrayList<IpCamera> domParseCameraList(String xml) {
		if (xml == null)
			return null;
		xml = xml.replace("&lt;", "<");
		xml = xml.replace("&gt;", ">");
		xml = xml.replace("&amp;", "&");
		xml = xml.replace("&apos;", "'");
		xml = xml.replace("&quot;", "\"");
		System.out.println("debug xml->" + xml);
		ArrayList<IpCamera> listResult = new ArrayList<IpCamera>();

		// 得到 DocumentBuilderFactory 对象, 由该对象可以得到 DocumentBuilder 对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 得到DocumentBuilder对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 得到代表整个xml的Document对象
			Document document = builder.parse(new ByteArrayInputStream(xml
					.getBytes()));
			// 得到 "根节点"
			Element root = document.getDocumentElement();
			// 获取根节点的所有items的节点
			NodeList items = root.getElementsByTagName("Row");
			// 遍历所有节点
			for (int i = 0; i < items.getLength(); i++) {
				IpCamera camera = new IpCamera();

				Element item = (Element) items.item(i);
				NodeList nodes = item.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node node = nodes.item(j);
					String nodeName = node.getNodeName();
					String value = node.getTextContent();
					System.out.println("deubg"+ nodeName + "---->" + value);
					if (value != null) {
						if (nodeName.equals("Deviceid")) {
							camera.setId(Integer.parseInt(value));
						} else if (nodeName.equals("DeviceName")) {
							camera.setName(value);
						} else if (nodeName.equals("DeviceAddress")) {
							camera.setIp(value);
						} else if (nodeName.equals("DevicePort")) {
							camera.setPort(Integer.parseInt(value));
						} else if (nodeName.equals("DeviceUser")) {
							camera.setUser(value);
						} else if (nodeName.equals("DevicePsd")) {
							camera.setPass(value);
						} else if (nodeName.equals("DeviceChanel")) {
							camera.setChannel(Integer.valueOf(value));
						} else if (nodeName.equals("DeviceLocation")) {
							camera.setLocation(value);
						}
					}
				}
				listResult.add(camera);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	// 解析会场机信息
	private ArrayList<MeetingRoom> domParseMeetingRoomList(String xml) {
		if (xml == null)
			return null;
		xml = xml.replace("&lt;", "<");
		xml = xml.replace("&gt;", ">");
		xml = xml.replace("&amp;", "&");
		xml = xml.replace("&apos;", "'");
		xml = xml.replace("&quot;", "\"");
		System.out.println("xml->" + xml);
		ArrayList<MeetingRoom> listResult = new ArrayList<MeetingRoom>();

		// 得到 DocumentBuilderFactory 对象, 由该对象可以得到 DocumentBuilder 对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 得到DocumentBuilder对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 得到代表整个xml的Document对象
			Document document = builder.parse(new ByteArrayInputStream(xml
					.getBytes()));
			// 得到 "根节点"
			Element root = document.getDocumentElement();
			// 获取根节点的所有items的节点
			NodeList items = root.getElementsByTagName("Row");
			// 遍历所有节点
			for (int i = 0; i < items.getLength(); i++) {
				MeetingRoom meetingRoom = new MeetingRoom();

				Element item = (Element) items.item(i);
				NodeList nodes = item.getChildNodes();
				for (int j = 0; j < nodes.getLength(); j++) {
					Node node = nodes.item(j);
					String nodeName = node.getNodeName();
					String value = node.getTextContent();
					if (value != null) {
						if (nodeName.equals("Deviceid")) {
							meetingRoom.setCameraID(Integer.parseInt(value));
						} else if (nodeName.equals("DeviceName")) {
							meetingRoom.setCameraName(value);
						} else if (nodeName.equals("MeetingPlaceID")) {
							if (value.length() > 0)
								meetingRoom.setID(Integer.parseInt(value));
							else {
								meetingRoom.setID(-1);
							}
						} else if (nodeName.equals("MeetingPlaceName")) {
							meetingRoom.setName(value);
						}
					}
				}
				listResult.add(meetingRoom);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}
	
	
	public static void main(String[] args) {
		String ip = "112.4.17.103";
		int port = 8009;
		MedicalServer server = new MedicalServer(ip,port);
		String name = "wxl";
		String password = "12345";
		Doctor user = new Doctor(name,password);
//		System.out.println(server.userLogin(user));
//		System.out.println(server.getMeetingRoomList(user, ""));
//		ArrayList<MeetingRoom> list = server.getMeetingRoomList(user, "");
		ArrayList<IpCamera> ipc = server.getCamera(user, 145);
//		System.out.println(ipc.toString());
	}

	public MedicalServer getServer() {
		return server;
	}

	public void setServer(MedicalServer server) {
		this.server = server;
	}

	public Doctor getUser() {
		return user;
	}

	public void setUser(Doctor user) {
		this.user = user;
	}
	
	
}
