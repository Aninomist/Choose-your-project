package eiffle.PandaMeiyaReykaSuki.http;

import java.util.List;

import eiffle.PandaMeiyaReykaSuki.model.Choice;

public class AdminChoiceResponse {
	public final String response;
	public final int httpCode;
	public final List<Choice> choices;
	
	public AdminChoiceResponse(List<Choice> choices) {
		this.response = "Choices in database";
		this.httpCode = 200;
		this.choices = choices;
	}
	
	public AdminChoiceResponse(String response, int httpCode) {
		this.response = response;
		this.httpCode = httpCode;
		this.choices = null;
	}
}
