package eiffle.PandaMeiyaReykaSuki.http;

import java.util.List;

import eiffle.PandaMeiyaReykaSuki.model.Alternative;

public class RegisterOrSignInResponse {

	public final String response;
	public final int httpCode;
	public List<Alternative> alternatives;
	
	public RegisterOrSignInResponse(String s, int code, List<Alternative> alternatives) {
		this.response = s;
		this.httpCode = code;
		this.alternatives = alternatives;
	}
	
	public RegisterOrSignInResponse(String s, List<Alternative> alternatives) {
		this.response = s;
		this.httpCode = 200;
		this.alternatives = alternatives;
	}
	
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
