package eiffle.PandaMeiyaReykaSuki.http;

public class VoteResponse {
	public final String response;
	public final int httpCode;
	
	public VoteResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	public VoteResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "VoteResponse(" + response + ")";
	}
	
}
