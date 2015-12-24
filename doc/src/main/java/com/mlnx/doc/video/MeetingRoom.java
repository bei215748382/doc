package com.mlnx.doc.video;

public class MeetingRoom {
	private String name;
	private int ID;
	private String CameraName;
	private int cameraID;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCameraName() {
		return CameraName;
	}
	public void setCameraName(String cameraName) {
		CameraName = cameraName;
	}
	public int getCameraID() {
		return cameraID;
	}
	public void setCameraID(int cameraID) {
		this.cameraID = cameraID;
	}
	@Override
	public String toString() {
		return "MeetingRoom [name=" + name + ", ID=" + ID + ", CameraName="
				+ CameraName + ", cameraID=" + cameraID + "]";
	}
}
