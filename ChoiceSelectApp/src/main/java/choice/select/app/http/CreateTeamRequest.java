package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Member;

public class CreateTeamRequest {
	ArrayList<Member> members = new ArrayList<>();
	Choice choice;
	
	public CreateTeamRequest() {}
	
	public CreateTeamRequest(ArrayList<Member> members,Choice choice) {
		this.members = members;
		this.choice = choice;
	}

	public ArrayList<Member> getMembers(){return this.members;}
	public Choice getChoice() {return this.choice;}
}
