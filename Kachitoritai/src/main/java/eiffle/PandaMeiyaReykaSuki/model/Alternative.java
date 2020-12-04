package eiffle.PandaMeiyaReykaSuki.model;

import java.util.List;

public class Alternative {
	public final String altID;
	public final String choiceID;
	public final boolean isFinalAlternative;
	public final int noAlter;
	public final String description;
	
	public List<FeedBack> feedBacks;
	
	
	
	public Alternative(String altID, String choiceID, boolean isFinalAlt, int noAlter, String description) {
		this.altID = altID;
		this.choiceID = choiceID;
		this.isFinalAlternative = isFinalAlt;
		this.noAlter = noAlter;
		this.description = description;
		this.feedBacks = null;
	}
	
	public Alternative(String altID, String choiceID, boolean isFinalAlt, int noAlter, String description, List<FeedBack> feedBacks) {
		this.altID = altID;
		this.choiceID = choiceID;
		this.isFinalAlternative = isFinalAlt;
		this.noAlter = noAlter;
		this.description = description;
		this.feedBacks = feedBacks;
	}
}
