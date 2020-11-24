package eiffle.PandaMeiyaReykaSuki.http;

public class RegisterOrSignInResponse {

	public final String response;
	public final int httpCode;
	
	public RegisterOrSignInResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}

	public RegisterOrSignInResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
