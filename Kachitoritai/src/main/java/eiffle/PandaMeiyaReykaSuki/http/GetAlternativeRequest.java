package eiffle.PandaMeiyaReykaSuki.http;

public class GetAlternativeRequest {
	public String choiceID;
	public int noAlter;
	
	public String getChoiceID() { return choiceID; }
	public void setChoiceID(String id) { this.choiceID=id; }
	
	public int getNoAlter() { return noAlter; }
	public void setNoAlter(int noAlter) { this.noAlter=noAlter; }
	
	public GetAlternativeRequest(String choiceID, int noAlter) {
		this.choiceID = choiceID;
		this.noAlter = noAlter;
	}
}
