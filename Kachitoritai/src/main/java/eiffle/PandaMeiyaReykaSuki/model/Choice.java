package eiffle.PandaMeiyaReykaSuki.model;

import java.util.UUID;

public class Choice {
	public final UUID choiceID;
	public final int numMember;
	public final int numAlt;

	
	public Choice(int numMember, int numAlt) {
		this.choiceID = UUID.randomUUID();
		this.numMember = numMember;
		this.numAlt = numAlt;
	}
	
}
