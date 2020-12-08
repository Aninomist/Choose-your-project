package eiffle.PandaMeiyaReykaSuki.http;

import java.util.List;

import eiffle.PandaMeiyaReykaSuki.model.Alternative;

public class FinalizeAlternativeResponse {
	public final String response;
	public final int httpCode;
	
	public FinalizeAlternativeResponse(String response) {
		this.response = response;
		this.httpCode = 200;
	}
	
	public FinalizeAlternativeResponse(String response, int httpCode) {
		this.response = response;
		this.httpCode = httpCode;
	}
}
