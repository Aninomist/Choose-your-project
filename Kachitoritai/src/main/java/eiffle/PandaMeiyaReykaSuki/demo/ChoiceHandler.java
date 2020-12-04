package eiffle.PandaMeiyaReykaSuki.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import eiffle.PandaMeiyaReykaSuki.db.AlternativeDao;
import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.model.Choice;
import eiffle.PandaMeiyaReykaSuki.demo.AlternativeController;

public class ChoiceHandler implements RequestHandler<ChoiceRequest, ChoiceResponse> {

	LambdaLogger logger;
	
	String choiceID;
    
    public ChoiceHandler() {}

  
    boolean checkChoiceExist(String choiceID) throws Exception{
    	if (logger != null) { logger.log("in checkChoiceExist"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	return dao.existChoice(choiceID);
    }
    
    boolean createChoice(int limitMember, int numAlt, String description) throws Exception{
    	if (logger != null) { logger.log("in createChoice"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	
    	choiceID = UUID.randomUUID().toString();
//    	boolean exist = dao.getChoice(choiceID);
    	
//    	if(exist) {return false;}
    	
    	
    	System.out.println("dao acquired in Create Choice");
    	if(numAlt >= 2 && numAlt <= 5) {
    		LocalDateTime time = LocalDateTime.now();
    		Choice choice = new Choice(choiceID, limitMember, numAlt, description, time.toString());
    		return dao.addChoice(choice);
    	} else {
    		return false;
    	}	
    }
    

    

	@Override
	public ChoiceResponse handleRequest(ChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		ChoiceResponse response;
		
		try {
			if(req.choiceID.equals("newChoice")) {
				if(createChoice(req.limitMember, req.numAlt, req.description)) {
					if(new AlternativeController().createAlternatives(choiceID, req.numAlt, req.altDescription)) {
						response = new ChoiceResponse(choiceID);
					} else {
					response = new ChoiceResponse("Failed on Create alternatives", 400);
					}
				} else {
					response = new ChoiceResponse("Unable to create choice, number of alternatives can only be between 2 and 5", 406);
				}
			} else {		
				if(checkChoiceExist(req.choiceID)) {
					response = new ChoiceResponse(req.choiceID);
				} else {
					response = new ChoiceResponse("Unable to find choice: " + req.choiceID, 404);
				}
			}	
		} catch (Exception e) {
			response = new ChoiceResponse("Unable to create choice: " + req.choiceID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}