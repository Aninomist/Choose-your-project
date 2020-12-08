package eiffle.PandaMeiyaReykaSuki.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import eiffle.PandaMeiyaReykaSuki.db.AlternativeDao;
import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.http.CreateFeedBackResponse;
import eiffle.PandaMeiyaReykaSuki.http.FinalizeAlternativeRequest;
import eiffle.PandaMeiyaReykaSuki.http.FinalizeAlternativeResponse;
import eiffle.PandaMeiyaReykaSuki.http.GetAlternativeResponse;

public class FinalizeAlternativeHandler implements RequestHandler<FinalizeAlternativeRequest, FinalizeAlternativeResponse>{
	LambdaLogger logger;
	boolean FinalizeAlternative(String choiceID,String altID) throws Exception{
		if(logger != null) {logger.log("in finalizeAlternative");}
		AlternativeDao daoA = new AlternativeDao();
		return daoA.finalizeAlt(altID);
	}

	@Override 
	public FinalizeAlternativeResponse handleRequest(FinalizeAlternativeRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		FinalizeAlternativeResponse response;
		AlternativeDao daoA = new AlternativeDao();
		try {
			if(daoA.finalizeAlt(req.altID) && daoA.getChoiceID(req.altID).equals(req.choiceID)) {
				response = new FinalizeAlternativeResponse();
			}
			response = new FinalizeAlternativeResponse("Something went wrong, failed to finalize Alternative",400);
			
		}
		catch (Exception e) {
			response = new FinalizeAlternativeResponse("Unable to fianlize Alternatives: " + req.altID + "(" + e.getMessage() + ")", 400);
		}
		return response;
		}
}
