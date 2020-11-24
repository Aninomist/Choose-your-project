package eiffle.PandaMeiyaReykaSuki.http;

public class RegisterOrSignInRequest {

	public String choiceID;
	public String username;
	public String password;
	
	public String getChoiceID() { return choiceID; }
	public void setChoiceID(String id) { this.choiceID=id; }
	
	public String getUsername() { return username; }
	public void setUsername(String um) { this.username=um; }
	
	public String getPassword() { return password; }
	public void setPassword(String pw) { this.password=pw; }
	
	public RegisterOrSignInRequest() {}
	
	public RegisterOrSignInRequest(String choiceID, String username, String password) {
		this.choiceID = choiceID;
		this.username = username;
		this.password = password;
	}
	
	public RegisterOrSignInRequest(String choiceID, String username) {
		this.choiceID = choiceID;
		this.username = username;
	}
}
