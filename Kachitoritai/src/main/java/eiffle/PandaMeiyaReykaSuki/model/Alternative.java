package eiffle.PandaMeiyaReykaSuki.model;

public class Alternative {
	public final String altID;
	public final String choiceID;
	public final boolean isFinalAlternative;
	
	
	Alternative(String altID, String choiceID, boolean isFinalAlt) {
		this.altID = altID;
		this.choiceID = choiceID;
		this.isFinalAlternative = isFinalAlt;
	}
}
