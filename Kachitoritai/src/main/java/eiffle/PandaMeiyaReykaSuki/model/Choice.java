package eiffle.PandaMeiyaReykaSuki.model;

import java.util.List;
import java.util.UUID;

public class Choice {
	public final String choiceID;
	public final int limitMember;
	public final int numAlt;
	public final String description;
	public final String dateCreated;
	public int currentMember;
	public boolean completed;
	
	public List<String> altDescription;

	
	public Choice(String choiceID, int limitMember, int numAlt, String description, String localDateTime) {
		this.choiceID = choiceID;
		this.limitMember = limitMember;
		this.numAlt = numAlt;
		this.description = description;
		this.dateCreated = localDateTime;
		this.currentMember = 0;
		this.completed = false;
	}
	
	public Choice(String choiceID, String description, int numberOfAlternative, String dateCreated, int userLimit, int userRegistered, boolean completed) {
		this.choiceID = choiceID;
		this.limitMember = userLimit;
		this.numAlt = numberOfAlternative;
		this.description = description;
		this.dateCreated = dateCreated;
		this.currentMember = userRegistered;
		this.completed = completed;
	}
	
}
