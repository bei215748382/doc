package com.mlnx.doc.video;

public class ConsultationRequest {
	// 等待审核,审核通过,拒绝,正在会诊,会诊结束,未知
	public enum Statu {
		CHECK, PASS, DENY, CONSULTATION, DONE, UNKNOWN;
		public static Statu parse(String str) {
			if ("0".equals(str))
				return CHECK;
			if ("1".equals(str))
				return PASS;
			if ("2".equals(str))
				return DENY;
			if ("3".equals(str))
				return CONSULTATION;
			if ("4".equals(str))
				return DONE;
			return UNKNOWN;
		}
	}

	private int ID;
	private Hospital hospital;
	private Patient patient;
	private User requester;
	private String time;
	private String type;
	private Statu statu = Statu.UNKNOWN;

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Patient getPatient() {
		if (patient == null)
			patient = new Patient();
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public String getTime() {
		if (time == null)
			time = "";
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		if (type == null)
			type = "";
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Statu getStatu() {
		return statu;
	}

	public void setStatu(Statu statu) {
		this.statu = statu;
	}

}
