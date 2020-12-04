package eiffle.PandaMeiyaReykaSuki.demo;

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
	
	
	boolean deleteNumDaysOldChoices(int num) {
		//TODO
		return false;
	}
	
	
	List<Choice> adminListChoices() throws Exception {
		if (logger != null) { logger.log("in adminListChoices"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	
    	return dao.getAllChoices();
	}
	
	@Override
	public AdminChoiceResponse handleRequest(AdminChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		AdminChoiceResponse response;
		
		try {
			if(req.daysToDelete == 0) {
				response = new AdminChoiceResponse(adminListChoices());
			} else {
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
