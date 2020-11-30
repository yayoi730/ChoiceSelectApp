package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;

public class CreateTeamRequest {
	ArrayList<Member> members = new ArrayList<>();
	Choice choice;
	int teamSize;
	
	public int getTeamSize() {return teamSize;}
	public void setTeamSize(int s) {this.teamSize = s;}
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public CreateTeamRequest() {}
	
	public CreateTeamRequest(ArrayList<Member> members,Choice choice, int nm) {
		this.members = members;
		this.choice = choice;
		this.teamSize = nm;
	}


	public ArrayList<Member> getMembers(){return this.members;}
	public Choice getChoice() {return this.choice;}

	public void setMembers(ArrayList<Member> newMem) { this.members = newMem; }
	public void setChoice(Choice newChoice) { this.choice = newChoice; }

}
