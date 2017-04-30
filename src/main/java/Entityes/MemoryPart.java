package Entityes;

import java.util.Date;

public class MemoryPart {
		
	private int id;
	private String FIO;
	private Date date;
	private String event;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFIO() {
		return FIO;
	}
	
	public void setFIO(String fIO) {
		FIO = fIO;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public MemoryPart(String fIO, Date date, String event) {
		FIO = fIO;
		this.date = date;
		this.event = event;
	}
	
	public MemoryPart() {
		date = new Date();
	}
	
}
