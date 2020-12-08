package choice.select.app.http;

public class DeleteChoiceRequest {
	
	public int daysOld;
	
	public DeleteChoiceRequest(){}
	
	public DeleteChoiceRequest(int daysOld){this.daysOld = daysOld;}
	
	public void setDaysOld(int daysOld) {
		this.daysOld = daysOld;
	}
	
	public int getDaysOld() {
		return this.daysOld;
	}
	
	public String toString() {
		return "Delete " + this.daysOld;
	}
	
}
