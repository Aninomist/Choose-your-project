package eiffle.PandaMeiyaReykaSuki.http;

public class CreateChoiceRequest {

	public String choiceID;
	public int numMember;
	public int numAlt;
	
	public String getChoiceID( ) { return choiceID; }
	public void setChoiceID(String choiceID) { this.choiceID = choiceID; }
	
	public int getNumMember( ) { return numMember; }
	public void setNumMember(int numMember) { this.numMember = numMember; }
	
	public int getNumAlt() { return numAlt; }
	public void setNumAlt(int numAlt) { this.numAlt = numAlt; }

	
	public CreateChoiceRequest() {}
}
