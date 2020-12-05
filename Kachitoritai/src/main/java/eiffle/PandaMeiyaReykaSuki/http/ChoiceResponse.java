package eiffle.PandaMeiyaReykaSuki.http;

public class ChoiceResponse {
	public final String response;
	public final int httpCode;
	public final String choiceDes;
	
	public ChoiceResponse(String res, int code) {
		this.response = res;
		this.httpCode = code;
		this.choiceDes = null;
	}

	public ChoiceResponse(String res, String choiceDes) {
		this.response = res;
		this.httpCode = 200;
		this.choiceDes = choiceDes;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
	
	
}
