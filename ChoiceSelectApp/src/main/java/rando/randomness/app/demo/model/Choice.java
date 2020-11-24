package rando.randomness.app.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Choice {
	ArrayList<Alternative> alternatives = new ArrayList<>();
	String id;
	String tID;
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
	
	public ArrayList<Alternative> getAlternativeList(){
		return this.alternatives;
	}
	
	public void completeChoice(int choiceNum) { // basically a setter, needs clarification
		this.finalChoice = choiceNum;
	}

	public void setID(String cID) {this.id = cID;}
	
	public String getTID() {return this.tID;}
	public void setTID(String tID) {this.tID = tID;}
	
}
