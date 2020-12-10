package eiffle.PandaMeiyaReykaSuki.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.db.FeedBackDAO;
import eiffle.PandaMeiyaReykaSuki.http.AdminChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.AdminChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.model.Choice;

public class AdminChoiceHandler implements RequestHandler<AdminChoiceRequest, AdminChoiceResponse>{
	LambdaLogger logger;
	
	
	
	List<Choice> adminListChoices() throws Exception {
		if (logger != null) { logger.log("in adminListChoices"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	
    	List<Choice> choices =  dao.getAllChoices();
    	Collections.sort(choices, (x, y) -> LocalDateTime.parse(x.dateCreated).compareTo(LocalDateTime.parse(y.dateCreated)));
    	return choices;
	}
	
	
	
	boolean deleteNumDaysOldChoices(float daysToDelete) throws Exception {
		if (logger != null) { logger.log("in deleteNumDaysOldChoices"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	
    	List<Choice> choices= dao.getAllChoices();
    	List<String> toDelete = new ArrayList<>();
    	
    	int days = (int) Math.floor(daysToDelete);
    	float remainder = (daysToDelete - days)*24; //in hours
    	int hours = (int) Math.floor(remainder);
    	remainder = (remainder - hours)*60; //in minutes
    	int minutes = (int) Math.floor(remainder);
    	remainder = (remainder - minutes)*60; //in seconds
    	int seconds = (int) Math.floor(remainder);
    	
    	
    	LocalDateTime staleDate = LocalDateTime.now().minusDays(days);
    	staleDate = staleDate.minusHours(hours);
    	staleDate = staleDate.minusMinutes(minutes);
    	staleDate = staleDate.minusSeconds(seconds);

    	for(Choice choice : choices) {
    		if(LocalDateTime.parse(choice.dateCreated).isBefore(staleDate))
    			toDelete.add(choice.choiceID);
    	}
    	
    	for(String choiceID : toDelete) {
    		dao.deleteChoice(choiceID);
    	}
    	
    	return true;
	}
	
	@Override
	public AdminChoiceResponse handleRequest(AdminChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		AdminChoiceResponse response;
		logger.log("$$$$$$$$$$$$$" + req.daysToDelete + "$$$$$$$$$$$$$$");
		
		try {
			if(req.daysToDelete < 0) {
				logger.log("|||||||" + "just listing" + "||||||");
				response = new AdminChoiceResponse(adminListChoices());
			} else {
				logger.log("|||||||" + "deleting" + "||||||");
				if(deleteNumDaysOldChoices(req.daysToDelete))
					response = new AdminChoiceResponse(adminListChoices());
				else 
					response = new AdminChoiceResponse("delete failed", 400);
			}
		} catch(Exception e) {
			response = new AdminChoiceResponse("Unable to use admin get all choices: (" + e.getMessage() + ")", 400);
		}
		
		
		return response;
		
	}
	
}
