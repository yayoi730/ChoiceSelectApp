package rando.randomness.app.demo.model;

import java.sql.Timestamp;
import java.util.Iterator;

public class Choice {
	Iterator<Alternative> iterator;
	String id;
	String description;
	Timestamp creationDate;
	Timestamp completionDate;
	int finalChoice;
	public Choice(String id, String description, Timestamp creationDate) {
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
	}
	public String getID() {return this.id;}
	public String getDescription() {return this.description;}
	public Timestamp getCreationDate() {return this.creationDate;}
	
}
