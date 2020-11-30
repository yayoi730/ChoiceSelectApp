package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;

public class CreateTeamRequest {
	ArrayList<Member> members = new ArrayList<>();
	Choice choice;
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public CreateTeamRequest() {}
	
	public CreateTeamRequest(ArrayList<Member> members,Choice choice) {
		this.members = members;
		this.choice = choice;
	}

	public ArrayList<Member> getMembers(){return members;}
	public void setMembers(ArrayList<Member> newMem) { this.members = newMem; }
	
	public Choice getChoice() {return choice;}
	public void setChoice(Choice newChoice) { this.choice = newChoice; }
}
