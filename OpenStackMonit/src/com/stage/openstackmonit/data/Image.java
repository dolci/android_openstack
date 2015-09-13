package com.stage.openstackmonit.data;

public class Image {
	private String name;
	private String id;
	private int size;
	private int minRam;
	private int minDisk;
	private String created;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getMinRam() {
		return minRam;
	}
	public void setMinRam(int minRam) {
		this.minRam = minRam;
	}
	public int getMinDisk() {
		return minDisk;
	}
	public void setMinDisk(int minDisk) {
		this.minDisk = minDisk;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	

}
