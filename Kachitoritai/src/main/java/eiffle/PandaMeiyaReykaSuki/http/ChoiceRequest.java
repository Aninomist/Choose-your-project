package eiffle.PandaMeiyaReykaSuki.http;

import java.util.List;

public class ChoiceRequest {

	public String choiceID;
	public int limitMember;
	public int numAlt;
	public String description;
	public List<String> altDescription;
	
	public String getChoiceID( ) { return choiceID; }
	public void setChoiceID(String choiceID) { this.choiceID = choiceID; }
	
	public int getLimitMember( ) { return limitMember; }
	public void setLimitMember(int numMember) { this.limitMember = numMember; }
	
	public int getNumAlt() { return numAlt; }
	public void setNumAlt(int numAlt) { this.numAlt = numAlt; }
	
//	public String getLocalDateTime() { return localDateTime;}
//	public void setLocalDateTime(String localDateTime) {this.localDateTime = localDateTime;}
	
	public String getDescription() { return description;}
	public void setDescription(String description) {this.description = description;}
	
	public List<String> getAltDescription() { return altDescription;}
	public void setAltDescription(List<String> altDescription) {this.altDescription = altDescription;}

	
	public ChoiceRequest() {}
	
	public ChoiceRequest(String choiceID, int limitMember, int numAlt, String description, List<String> altDescription) {
		this.choiceID = choiceID;
		this.limitMember = limitMember;
		this.numAlt = numAlt;
		this.description = description;
		this.altDescription = altDescription;
	}
}
