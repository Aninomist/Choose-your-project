package eiffle.PandaMeiyaReykaSuki.demo;

import java.time.LocalDateTime;
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
import eiffle.PandaMeiyaReykaSuki.db.FeedBackDAO;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.CreateFeedBackRequest;
import eiffle.PandaMeiyaReykaSuki.http.CreateFeedBackResponse;
import eiffle.PandaMeiyaReykaSuki.model.Choice;
import eiffle.PandaMeiyaReykaSuki.model.FeedBack;

public class CreateFeedBackHandller implements RequestHandler<CreateFeedBackRequest, CreateFeedBackResponse>{
	LambdaLogger logger;
	
	boolean createFeedBack(String altID, String username, String description) throws Exception{
    	if (logger != null) { logger.log("in createFeedBack"); }
    	FeedBackDAO dao = new FeedBackDAO();
    	
    	String feedBackID = UUID.randomUUID().toString();
    	String time = LocalDateTime.now().toString();
    	
    	FeedBack feedBack = new FeedBack(feedBackID, username, time, altID, description);
    	
    	return dao.createFeedBack(feedBack);
    }
	
	
	@Override
	public CreateFeedBackResponse handleRequest(CreateFeedBackRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		CreateFeedBackResponse response;
		
		try {
			if(choiceCompleted(req.altID)) {
				response = new CreateFeedBackResponse("Choice Has already concluded:", 405);
			}
			
			else 
			
			if(createFeedBack(req.altID, req.username, req.description)){
				response = new CreateFeedBackResponse("FeedBack successfully creates");
			} else {
				response = new CreateFeedBackResponse("Something went wrong, failed to create feedback", 400);
			}
		} catch (Exception e) {
			response = new CreateFeedBackResponse("Something went wrong: (" + e.getMessage() + ")", 400);
		}
		
		return response;
			
	}
}
