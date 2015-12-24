package com.mlnx.doc.util;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

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
		}
		return new String[]{voipAccount,voipPassword,subAccountSid,subToken};
	}
}
