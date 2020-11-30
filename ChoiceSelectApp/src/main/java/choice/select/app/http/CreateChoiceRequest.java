package choice.select.app.http;

import java.util.ArrayList;

public class CreateChoiceRequest {

	
	String description;
	ArrayList<String> alternatives;
	int teamSize;

	public CreateChoiceRequest() {}
	
	public CreateChoiceRequest(String description) {
		this.description = description;
	}
	
	public void setDescription(String d) {this.description = d;}
	public String getDescription() {return this.description;}
	
	public int getTeamSize () {return this.teamSize;}
	public void setTeamSize (int size) {this.teamSize = size;}

	
	public ArrayList<String> getAlternatives() { return alternatives; }
	public void setAlternatives(ArrayList<String> alts) { this.alternatives = alts; }
	
	public ArrayList<String> getAlts(){return this.alternatives;}
	
	public String toString() {return "CreateChoice(" + description + "," + alternatives.toString() + ")";}

}
