package eiffle.PandaMeiyaReykaSuki.http;

public class CreateFeedBackRequest {
	public String username;
	public String altID;
	public String description;
	
	public String getUsername( ) { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getAltID( ) { return altID; }
	public void setAltID(String altID) { this.altID = altID; }
	
	public String getDescription( ) { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public CreateFeedBackRequest() {}
	
	public CreateFeedBackRequest(String username, String altID, String description) {
		this.username = username;
		this.altID = altID;
		this.description = description;
	}
}
