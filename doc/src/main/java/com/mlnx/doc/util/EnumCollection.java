package com.mlnx.doc.util;

public class EnumCollection {
	public enum ResponseCode {
		EXIST("000001", "注册用户已存在"), SUCCESS("000002", "用户注册成功"), PROVINCE_REGISTER_SUCCESS(
				"000003", "注册城市成功"), CITY_REGISTER_SUCCESS("000004", "注册城市成功"), HOSPITAL_REGISTER_SUCCESS(
				"000005", "注册医院成功"), PATIENT_REGISTER_SUCCESS("000006",
				"注册病人成功"), PATIENT_MODIFY_SUCCESS("000007", "修改病人成功"), ORDER_REGISTER_SUCCESS(
				"000008", "注册预约成功"), VOIP_EXIST("000009", "容联云申请子账户名称重复"), LOGIN_SUCCESS(
				"000010", "登入成功"), LOGIN_PASSWORD_ERROR("000011", "用户名密码错误"), LOGIN_USERNAME_NOT_EXIST(
				"000012", "用户名不存在"), ADD_FRIENDS_SUCCESS("000013", "添加好友成功"), PROVINCE_DELETE_SUCCESS(
				"000014", "删除省成功"), ORDER_REMIND_SUCCESS("000015", "设置提醒成功"), DOCTOR_MODIFY_SUCCESS(
				"000016", "修改医生信息成功"), DOMAIN_REGISTER_SUCCESS("000017",
				"注册病区成功"), ROOM_REGISTER_SUCCESS("000018", "注册房间成功"), BED_REGISTER_SUCCESS(
				"000019", "注册床号成功"), DELETE_DOCTOR_SUCCESS("000020", "删除医生成功"), DELETE_PATIENT_SUCCESS(
				"000021", "删除病人成功"), UPDATE_DOCTOR_SUCCESS("000022", "修改医生信息成功"), DELETE_ORDER_SUCCESS(
				"000023", "删除预约成功"), UPDATE_ORDER_SUCCESS("000024", "更新预约表成功"), ADD_ORDER_SUCCESS(
				"000025", "添加预约成功"), UPDATE_ORDER_STATE_SUCCESS("000026",
				"更新预约成功"), ADD_LOG_SUCCESS("000027", "客户端上传错误日志成功"), DELETE_LOG_SUCCESS(
				"000028", "删除客户端错误日志成功"), ERROR("000029", "用户注册失败"), FIND_DOCTOR_STATE_SUCCESS(
				"000030", "获取用户登入状态成功"), FIND_DOCTOR_STATE_ERROR("000031",
				"获取用户登入状态失败"), UPDATE_DOCTOR_STATE_SUCCESS("000032",
				"更新医生登入状态成功"), UPDATE_DOCTOR_STATE_ERROR("000033", "更新医生登入状态失败"), MODIFY_PASSWORD_OLD_PASSWORD_NOT_RIGHT(
				"000034", "修改密码原密码不正确"), MODIFY_PASSWORD_SUCCESS("000035",
				"修改密码成功"), MODIFY_PASSWORD_ERROR("000036", "修改密码失败");
		// 成员变量
		private String code;
		private String msg;

		// 构造方法
		private ResponseCode(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		// 覆盖方法
		@Override
		public String toString() {
			return this.code + "_" + this.msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	public static void main(String[] args) {
		System.out.println(ResponseCode.EXIST.toString());
	}
}
