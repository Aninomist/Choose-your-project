package eiffle.PandaMeiyaReykaSuki.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {
	public final String voteID;
	public final String altID;
	public final String username;
	public final boolean isUpVote;
	public final String dateCreated;
	
	public Vote(String altID, String username, boolean isUpVote) {
		this.altID = altID;
		this.username = username;
		this.isUpVote = isUpVote;
		this.voteID = UUID.randomUUID().toString();
		this.dateCreated = LocalDateTime.now().toString();
	}
}
