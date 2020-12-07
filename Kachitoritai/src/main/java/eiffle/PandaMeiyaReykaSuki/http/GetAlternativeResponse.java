package eiffle.PandaMeiyaReykaSuki.http;

import java.util.List;

import eiffle.PandaMeiyaReykaSuki.model.Alternative;

public class GetAlternativeResponse {
	public final String response;
	public final List<Alternative> alt;
	public final boolean completed;
	public final int httpCode;
	
	public GetAlternativeResponse(List<Alternative> alt, boolean completed) {
		this.response = "Success";
		this.alt = alt;
		this.completed = completed;
		this.httpCode = 200;
	}
	
	public GetAlternativeResponse(String response, int httpCode) {
		this.response = response;
		this.alt = null;
		this.httpCode = httpCode;
		this.completed = true;
	}
}
 