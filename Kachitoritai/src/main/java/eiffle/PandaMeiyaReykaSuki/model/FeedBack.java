package eiffle.PandaMeiyaReykaSuki.model;

public class FeedBack {
	public final String feedBackID;
	public final String username;
	public final String dateCreated;
	public final String altID;
	public final String description;
	
	public FeedBack(String feedBackID, String username,String dateCreated,String altID, String description) {
		this.feedBackID = feedBackID;
		this.username = username;
		this.dateCreated = dateCreated;
		this.altID = altID;
		this.description = description;
	}
	
}
