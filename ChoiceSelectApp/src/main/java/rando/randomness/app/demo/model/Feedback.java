package rando.randomness.app.demo.model;

import java.sql.Timestamp;

public class Feedback {
	String fID;
	Timestamp timestamp;
	String description;
	String creator;
	
	public Feedback() {}
	
	public Feedback(Timestamp timestamp, String description, String creator) {
		this.timestamp = timestamp;
		this.description = description;
		this.creator = creator;
	}
	
	public Feedback(String fID,Timestamp timestamp, String description, String creator) {
		this.fID = fID;
		this.timestamp = timestamp;
		this.description = description;
		this.creator = creator;
	}
	
	public Timestamp getTimestamp() {return this.timestamp;}
	public String getDescription() {return this.description;}
	public String getCreator() {return this.creator;}
	public String getfID() {return this.fID;}
	public void setfID(String fID) {this.fID = fID;}
	public void setTimestamp(Timestamp ts) { this.timestamp = ts;}
	public void setDescription(String desc) { this.description = desc;}
	public void setCreator(String c) { this.creator = c;}
	
	public String toString() {	
		return "("+ this.fID + ", " +this.timestamp.toString()  + ", " + this.creator + ", " + this.description + ")";
	}
}
