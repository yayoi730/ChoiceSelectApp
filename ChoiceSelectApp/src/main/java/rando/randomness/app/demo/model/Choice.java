package rando.randomness.app.demo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Choice {
	ArrayList<Alternative> alternatives = new ArrayList<>();
	String id;
	String tID;
	String description;
	Timestamp creationDate;
	Timestamp completionDate;
	int finalChoice;

	public Choice(String description, Timestamp creationDate) {
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
	}
	
	public String getID() {return this.id;}
	public String getDescription() {return this.description;}
	public Timestamp getCreationDate() {return this.creationDate;}
	public Timestamp getCompletionDate() {return this.completionDate;}
	public void setCompletionDate(Timestamp newTimestamp) {this.completionDate = newTimestamp;}
	
	public ArrayList<Alternative> getAlternativeList(){ return this.alternatives; }
	public void addAlternative(Alternative a) { this.alternatives.add(a); }
	public Alternative getAlt(int altNum) {
		for (int i = 0; i < alternatives.size(); i++) {
			if (altNum == i+1) {
				return alternatives.get(i);
			}
		}
		return null;
	}
	
	public void setFinalChoice(int choiceNum) {this.finalChoice = choiceNum;}
	public int getFinalChoice(int choiceNum) {return choiceNum;}
	
	public void completeChoice(int choiceNum) { // basically a setter, needs clarification
		this.finalChoice = choiceNum;
		this.completionDate = new Timestamp(System.currentTimeMillis());
	}

	public void setID(String cID) {this.id = cID;}
	
	public String getTID() {return this.tID;}
	public void setTID(String tID) {this.tID = tID;}
	
}
