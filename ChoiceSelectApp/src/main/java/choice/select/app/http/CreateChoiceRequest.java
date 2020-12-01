package choice.select.app.http;

import java.sql.Timestamp;
import java.util.ArrayList;

import rando.randomness.app.demo.model.Alternative;

public class CreateChoiceRequest {

	String cid;
	String description;
	ArrayList<Alternative> alternatives;
	int teamSize;
	Timestamp creationDate;

	public CreateChoiceRequest() {}
	
	public CreateChoiceRequest(String description) {
		this.description = description;
	}
	
	public void setCid(String cid) {this.cid = cid;}
	public String getCid() {return this.cid;}
	
	public void setDesc(String d) {this.description = d;}
	public String getDesc() {return this.description;}
	

	public ArrayList<Alternative> getAlternatives() { return alternatives; }
	public void setAlternatives(ArrayList<Alternative> alts) { this.alternatives = alts; }
	
	public ArrayList<Alternative> getAlts(){return this.alternatives;} 
	
	public String toString() {return "CreateChoice(" + description + "," + alternatives.toString() + ")";}

}
