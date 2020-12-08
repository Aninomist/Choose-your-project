package eiffle.PandaMeiyaReykaSuki.http;

public class FinalizeAlternativeRequest {
	public String altID;
	public String choiceID;
	public String getAltID() { return altID; }
	public String getchoiceID() {return choiceID;}
	public void setAltID(String id) { this.altID=id; }
	public void setChoiceID(String id) {this.choiceID=id;}
	

	public FinalizeAlternativeRequest(String choiceID,String altID) {
		this.altID = altID;
		this.choiceID = choiceID;
	}
	
	public FinalizeAlternativeRequest() {}
}
