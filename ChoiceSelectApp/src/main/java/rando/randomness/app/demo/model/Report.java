package rando.randomness.app.demo.model;

public class Report {
	String choiceID;
	String creationDate;
	boolean completed;
	
	public Report(String choiceID, String creationDate, boolean completed) {
		this.choiceID = choiceID;
		this.creationDate = creationDate;
		this.completed = completed;
	}
	
	public String toString() {
		return "Report("+ this.choiceID + ", " +this.creationDate + ", " + this.completed+ ")";
	}
	public String getChoiceID() {return this.choiceID;}
	public String getCreationDate() {return this.creationDate;}
	public boolean getCompleted() {return this.completed;}
	
	public void setChoiceID(String id) { this.choiceID = id;}
	public void setCreationDate(String date) { this.creationDate = date;}
	public void setCompleted(boolean b) { this.completed = b;}
}
