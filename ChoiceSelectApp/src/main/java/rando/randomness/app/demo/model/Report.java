package rando.randomness.app.demo.model;

public class Report {
	String choiceID;
	String description;
	String creationDate;
	boolean completed;
	
	public Report(String choiceID, String description, String creationDate, boolean completed) {
		this.choiceID = choiceID;
		this.description = description;
		this.creationDate = creationDate;
		this.completed = completed;
	}
	
	public String toString() {
		return "Report("+ this.choiceID + ", " + this.description + ", "+this.creationDate + ", " + this.completed+ ")";
	}
	public String getChoiceID() {return this.choiceID;}
	public String getCreationDate() {return this.creationDate;}
	public boolean getCompleted() {return this.completed;}
	public String getDescription() {return this.description;}
	
	public void setChoiceID(String id) { this.choiceID = id;}
	public void setCreationDate(String date) { this.creationDate = date;}
	public void setCompleted(boolean b) { this.completed = b;}
	public void setDescription(String desc) { this.description = desc;}
}
