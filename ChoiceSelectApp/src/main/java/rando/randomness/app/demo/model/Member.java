package rando.randomness.app.demo.model;

public class Member {
	String mID;
	String name;
	String password;
	
	public Member(String name, String mID) {
		this.name = name;
		this.mID = mID;
	}
	public Member(String name, String password , String mID) {
		this.name = name;
		this.password = password;
		this.mID = mID;
	}
	public String getName() {return this.name;}
	public String getPassword() {return this.password;}
	public String getMID() {return this.mID;}
	public void setMID(String mID) {this.mID = mID;}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Member) {
			if(((Member) o).getMID() == this.mID) {
				return true;
			}
		}
		return false;
	}
}
