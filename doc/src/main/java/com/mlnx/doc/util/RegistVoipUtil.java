package com.mlnx.doc.util;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.mlnx.doc.entity.Doctor;
import com.mlnx.doc.repository.DoctorDao;
import com.mlnx.doc.util.EnumCollection.ResponseCode;

public class RegistVoipUtil {
	
	public static String[] registVoip(String friendName){
		String voipAccount = null;
		String voipPassword = null;
		String subAccountSid = null;
		String subToken = null;
		String dateCreated = null;
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(StringUtil.accountSid, StringUtil.accountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(StringUtil.appId);// 初始化应用ID
		result = restAPI.createSubAccount(friendName);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				String[] s = object.toString().split(",");
				String[] subAccountSidStr = s[0].split("=");
				String[] subTokenStr = s[4].split("=");
				String[] voipAccountStr = s[1].split("=");
				String[] voipPwdtStr = s[3].split("=");
				String[] dateCreateStr = s[2].split("=");
				voipAccount = voipAccountStr[1];
				voipPassword = voipPwdtStr[1];
				subAccountSid = subAccountSidStr[1];
				subToken = subTokenStr[1];
				dateCreated = dateCreateStr[1];
			}
			return new String[]{voipAccount,voipPassword,subAccountSid,subToken,dateCreated};
		} else {
			return new String[]{""};
		}
	}
	public static Response registDoctor(Doctor doctor,DoctorDao doctorDao){
		Response response = new Response();
		// 注册移动端 voip
		String friendName = UUID.randomUUID().toString();
		String[] str = RegistVoipUtil.registVoip(friendName);
		if (str.length == 5) {
			doctor.setVoipAccount(str[0]);
			doctor.setVoipPassword(str[1]);
			doctor.setSubAccountSid(str[2]);
			doctor.setSubToken(str[3]);
			doctor.setDateCreated(str[4]);
			doctor.setFriendName(friendName);
		} else {
			// 异常返回输出错误码和错误信息
			response.setResponseCode(EnumCollection.ResponseCode.VOIP_EXIST
					.getCode());
			response.setMsg(EnumCollection.ResponseCode.VOIP_EXIST.getMsg());
			return response;
		}

		// 注册PC端 voip
		String friendName2 = UUID.randomUUID().toString();
		String[] str2 = RegistVoipUtil.registVoip(friendName2);
		if (str.length == 5) {
			doctor.setVoipAccount2(str2[0]);
			doctor.setVoipPassword2(str2[1]);
			doctor.setSubAccountSid2(str2[2]);
			doctor.setSubToken2(str2[3]);
		} else {
			// 异常返回输出错误码和错误信息
			response.setResponseCode(EnumCollection.ResponseCode.VOIP_EXIST
					.getCode());
			response.setMsg(EnumCollection.ResponseCode.VOIP_EXIST.getMsg());
			return response;
		}
		try {
			doctorDao.save(doctor);
			response.setResponseCode(ResponseCode.SUCCESS.getCode());
			response.setMsg(ResponseCode.SUCCESS.getMsg());
		} catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR.getCode());
			response.setMsg(ResponseCode.ERROR.getMsg());
		}
		return response;
	}
}
