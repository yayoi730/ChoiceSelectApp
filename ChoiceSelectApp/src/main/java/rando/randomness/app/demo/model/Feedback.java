package rando.randomness.app.demo.model;

import java.sql.Timestamp;

public class Feedback {
	Timestamp timestamp;
	String description;
	String creator;
	public Feedback(Timestamp timestamp, String description, String creator) {
		this.timestamp = timestamp;
		this.description = description;
		this.creator = creator;
	}
	public Timestamp getTimestamp() {return this.timestamp;}
	public String getDescription() {return this.description;}
	public String getCreator() {return this.creator;}
}
