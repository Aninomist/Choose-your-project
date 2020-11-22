package eiffle.PandaMeiyaReykaSuki.http;

public class CreateChoiceRequest {

	public String choiceID;
	public int limitMember;
	public int numAlt;
	public String localDateTime;
	public String description;
	
	public String getChoiceID( ) { return choiceID; }
	public void setChoiceID(String choiceID) { this.choiceID = choiceID; }
	
	public int getLimitMember( ) { return limitMember; }
	public void setLimitMember(int numMember) { this.limitMember = numMember; }
	
	public int getNumAlt() { return numAlt; }
	public void setNumAlt(int numAlt) { this.numAlt = numAlt; }
	
	public String getLocalDateTime() { return localDateTime;}
	public void setLocalDateTime(String localDateTime) {this.localDateTime = localDateTime;}
	
	public String description() { return description;}
	public void setDescription(String description) {this.description = description;}

	
	public CreateChoiceRequest() {}
}
