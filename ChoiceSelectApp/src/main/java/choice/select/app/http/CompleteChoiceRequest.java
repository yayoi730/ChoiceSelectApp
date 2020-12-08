package choice.select.app.http;

import java.sql.Date;
import java.sql.Timestamp;

public class CompleteChoiceRequest {
	String tID;
	int choiceNum;
	
	public CompleteChoiceRequest(){}
	
	public CompleteChoiceRequest(String tID){this.tID = tID;}
	
	public void settID(String tID) {
		this.tID = tID;
	}
	public void setChoiceNum(int choiceNum) {
		this.choiceNum = choiceNum;
	}
	
	public String gettID() {
		return this.tID;
	}
	public int getChoiceNum() {
		return this.choiceNum;
	}
	
	public String toString() {
		return this.tID + " completed";
	}
	

}
