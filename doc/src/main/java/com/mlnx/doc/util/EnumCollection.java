package com.mlnx.doc.util;

public class EnumCollection {
	public enum ResponseCode {
		EXIST("000001", "注册用户已存在"), SUCCESS("000002", "用户注册成功"), PROVINCE_REGISTER_SUCCESS(
				"000003", "注册城市成功"), CITY_REGISTER_SUSSESS("000004", "注册城市成功"), HOSPITAL_REGISTER_SUSSESS(
				"000005", "注册医院成功"), PATIENT_REGISTER_SUSSESS("000006",
				"注册病人成功"),PATIENT_MODIFY_SUSSESS("000007","修改病人成功"),ORDER_REGISTER_SUSSESS("000008","注册预约成功");
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
