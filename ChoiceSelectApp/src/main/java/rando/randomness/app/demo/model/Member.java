package rando.randomness.app.demo.model;

public class Member {
	String name;
	String password;
	public Member(String name) {
		this.name = name;
	}
	public Member(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public String getName() {return this.name;}
	public String getPassword() {return this.password;}
	

}
