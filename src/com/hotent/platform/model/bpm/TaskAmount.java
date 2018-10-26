package com.hotent.platform.model.bpm;

public class TaskAmount {
	
	private String typeId;
	
	private int read=0;
	
	private int total=0;
	
	private int notRead=0;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNotRead() {
		return notRead;
	}

	public void setNotRead(int notRead) {
		this.notRead = notRead;
	}
	
	

}
