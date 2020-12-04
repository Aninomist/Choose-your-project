package eiffle.PandaMeiyaReykaSuki.http;

public class CreateFeedBackResponse {
	public final String response;
	public final int httpCode;
	
	public CreateFeedBackResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	public CreateFeedBackResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "CreateFeedBackResponse(" + response + ")";
	}
	
}
