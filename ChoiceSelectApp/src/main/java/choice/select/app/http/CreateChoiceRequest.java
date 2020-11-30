package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Alternative;

public class CreateChoiceRequest {
	
	String description;
	ArrayList<Alternative> alternatives;
	int teamSize;

	public CreateChoiceRequest() {}
	
	public CreateChoiceRequest(String description) {
		this.description = description;
	}
	
	public void setDescription(String d) {this.description = d;}
	public String getDescription() {return this.description;}
	
	public int getTeamSize () {return this.teamSize;}
	public void setTeamSize (int size) {this.teamSize = size;}

	
	public ArrayList<Alternative> getAlternatives() { return alternatives; }
	public void setAlternatives(ArrayList<Alternative> alts) { this.alternatives = alts; }
	
	public ArrayList<Alternative> getAlts(){return this.alternatives;}
	
	public String toString() {return "CreateChoice(" + description + "," + alternatives.toString() + ")";}
}
