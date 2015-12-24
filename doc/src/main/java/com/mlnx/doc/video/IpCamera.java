package com.mlnx.doc.video;

public class IpCamera {
	private int id;
	private String name;
	private String ip;
	private int port;
	private int channel;
	private String location;
	private String user;
	private String pass;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "IpCamera [id=" + id + ", name=" + name + ", ip=" + ip
				+ ", port=" + port + ", channel=" + channel + ", location="
				+ location + ", user=" + user + ", pass=" + pass + "]";
	}

}
