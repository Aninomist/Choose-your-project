package eiffle.PandaMeiyaReykaSuki.http;

public class VoteRequest {
	public String altID;
	public String username;
	public boolean isUpVote;
	
	public String getAltID( ) { return altID; }
	public void setAltID(String altID) { this.altID = altID; }

	public String getUsername( ) { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public boolean getIsUpVote( ) { return isUpVote; }
	public void setIsUpVote(boolean isUpVote) { this.isUpVote = isUpVote; }
}
