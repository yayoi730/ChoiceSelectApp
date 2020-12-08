package rando.randomness.app.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Choice {
	ArrayList<Alternative> alternatives = new ArrayList<>();
	String cid;
	String description;
	Timestamp creationDate;
	Timestamp completionDate;
	boolean completed;
	int finalChoice;

	public Choice(String description, Timestamp creationDate) {
		this.description = description;
		this.creationDate = creationDate;
	}
	
	public Choice(String description, ArrayList<Alternative> alternatives, Timestamp creationDate) {
		this.description = description;
		this.alternatives = alternatives;
		this.creationDate = creationDate;
	}
	
	public void setID(String cID) {this.cid = cID;}
	public String getID() {return this.cid;}
	
	public void setCompleted(boolean b) {this.completed = b;}
	public boolean getCompleted() {return this.completed;}
	
	public String getDescription() {return this.description;}
	public void setDescription(String desc) {
		this.description = desc;
	}
	
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
	public int getFinalChoice() {return this.finalChoice;}
	
	public void completeChoice(int choiceNum) { // basically a setter, needs clarification
		this.finalChoice = choiceNum;
		this.completionDate = new Timestamp(System.currentTimeMillis());
	}
	
}
