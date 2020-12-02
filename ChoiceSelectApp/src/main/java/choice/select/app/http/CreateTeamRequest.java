package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;

public class CreateTeamRequest {
	String name;
	String password;
	String cDesc;
	ArrayList<String> altDesc;
	int teamSize;
	
	public String toString() {
		return name + "," + password + "," + cDesc + "," + altDesc + "," + teamSize;
	}
	
	public int getTeamSize() {return teamSize;}
	public void setTeamSize(int s) {this.teamSize = s;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getPassword() {return this.password;}
	public void setPassword(String pass) {this.password = pass;}
	
	public String getcDesc() {return cDesc;}
	public void setcDesc(String cDesc) {this.cDesc = cDesc;}
	
	public ArrayList<String> getAltDesc() {return this.altDesc;}
	public void setAltDesc(ArrayList<String> alts) {this.altDesc = alts;}
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public CreateTeamRequest() {}
	
	public CreateTeamRequest(String name, String pass, String cDesc, ArrayList<String> alts, int nm) {
		this.name = name;
		this.teamSize = nm;
		this.cDesc = cDesc;
		this.altDesc = alts;
		this.password = pass;
	}

	

}
