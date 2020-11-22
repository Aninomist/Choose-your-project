package eiffle.PandaMeiyaReykaSuki.demo;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.http.CreateChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.CreateChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse> {

	LambdaLogger logger;
	
    private AmazonS3 s3 = null;
    
    public CreateChoiceHandler() {}

  
    
    boolean createChoice(int numMember, int numAlt) throws Exception{
    	if (logger != null) { logger.log("in createChoice"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	
    	UUID choiceID = UUID.randomUUID();
    	boolean exist = dao.getChoice(choiceID.toString());
    	
    	if(exist) {return false;}
    	
    	else if(numMember < 2 && numAlt > 5) {
    		Choice choice = new Choice(numMember, numAlt);
    		return dao.addChoice(choice);
    	} else {
    		return false;
    	}	
    }
    	
    

	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		CreateChoiceResponse response;
		
		try {
			if(createChoice(req.numMember, req.numAlt)) {
				response = new CreateChoiceResponse(req.choiceID);
			} else {
				response = new CreateChoiceResponse(req.choiceID, 422);
			}
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.choiceID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}