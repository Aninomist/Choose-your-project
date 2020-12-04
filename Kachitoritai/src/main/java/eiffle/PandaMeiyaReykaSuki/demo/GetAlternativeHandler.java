package eiffle.PandaMeiyaReykaSuki.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.GetAlternativeRequest;
import eiffle.PandaMeiyaReykaSuki.http.GetAlternativeResponse;

public class GetAlternativeHandler implements RequestHandler<GetAlternativeRequest, GetAlternativeResponse>{
	LambdaLogger logger;
	public GetAlternativeHandler() {}
	
	@Override
	public GetAlternativeResponse handleRequest(GetAlternativeRequest req, Context context) {
	
		logger = context.getLogger();
		logger.log(req.toString());
		
		GetAlternativeResponse response;
		
		try {
			
			response = new GetAlternativeResponse(
					new AlternativeController().getListAlternativesWithFeedback(req.choiceID));
			
		} catch(Exception e) {
			response = new GetAlternativeResponse("Unable to get Alternatives: " + req.choiceID + "(" + e.getMessage() + ")", 400);
		}
		
		
		return response;
	}
}
