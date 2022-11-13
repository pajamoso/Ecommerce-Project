package dao.event;

import java.util.Date;

public class eventInfo {
	
	private int eID;
	private String eName;
	private String esDate;
	private String edDate;
	private String ecID;
	
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getEsDate() {
		return esDate;
	}
	public void setEsDate(String esDate) {
		this.esDate = esDate;
	}
	public String getEdDate() {
		return edDate;
	}
	public void setEdDate(String edDate) {
		this.edDate = edDate;
	}
	public String getEcID() {
		return ecID;
	}
	public void setEcID(String ecID) {
		this.ecID = ecID;
	}
}
