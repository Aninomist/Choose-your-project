package eiffle.PandaMeiyaReykaSuki.http;

public class ChoiceResponse {
	public final String response;
	public final int httpCode;
	
	public ChoiceResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	public ChoiceResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
	
	
}
